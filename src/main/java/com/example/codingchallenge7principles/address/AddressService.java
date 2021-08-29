package com.example.codingchallenge7principles.address;

import com.example.codingchallenge7principles.address.dto.AddressCreateDto;
import com.example.codingchallenge7principles.address.dto.AddressDto;
import com.example.codingchallenge7principles.address.dto.AddressUpdateDto;
import com.example.codingchallenge7principles.address.dto.AgeDistroDto;
import com.example.codingchallenge7principles.range.AgeDistribution;
import com.example.codingchallenge7principles.range.AgeDistributionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressService {
    private final AddressRepo addressRepo;
    private final AddressCriteriaRepo addressCriteriaRepo;
    private final AgeDistributionRepo ageDistributionRepo;

    private Address addressDto2address(AddressDto addressDto, Address address) {
        address.setFirstName(addressDto.getFirstName());
        address.setLastName(addressDto.getLastName());
        address.setBirthday(addressDto.getBirthday());
        address.setStreet(addressDto.getStreet());
        address.setHouseNumber(addressDto.getHouseNumber());
        address.setPostalCode(addressDto.getPostalCode());
        address.setCity(addressDto.getCity());
        address.setPhone(addressDto.getPhone());
        address.setMobile(addressDto.getMobile());
        address.setEmail(addressDto.getEmail());
        address.setNewsletter(addressDto.getNewsletter());
        return address;
    }

    public List<Address> getAll(String filter) {
        List<Address> list = this.addressCriteriaRepo.findAllWithFilter(filter);
        list.forEach(item -> System.out.println(item));
        return list;
    }

    public List<AgeDistroDto> getAllWithAgeDistro() {
        List<Address> addresses = this.addressRepo.findAll();
        List<AgeDistribution> ageDistributions = this.ageDistributionRepo.findAll();

        Map<String, Long> ageGroups = addresses
                .stream()
                .collect(
                        Collectors.groupingBy(address -> {
                                    if (Objects.nonNull(address.getBirthday())) {
                                        var age = new Date().getYear() - address.getBirthday().getYear();
                                        return ageDistributions
                                                .stream()
                                                .filter(ageDistro -> age >= ageDistro.getLowerLimit() && age <= ageDistro.getUpperLimit())
                                                .collect(Collectors.toList())
                                                .get(0)
                                                .getRange();
                                    }
                                    return "UNKNOWN";
                                },
                                Collectors.counting())
                );

        return ageGroups
                .entrySet()
                .stream()
                .map(ageGroup -> {
                    AgeDistroDto ageDistroDto = new AgeDistroDto();
                    ageDistroDto.setAgeRange(ageGroup.getKey());
                    ageDistroDto.setCount(ageGroup.getValue());
                    return ageDistroDto;
                })
                .collect(Collectors.toList());
    }

    public Address getOne(int id) {
        return this.addressRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(Address.class.getSimpleName() + " not found"));
    }

    public Address create(AddressCreateDto addressCreateDto) {
        Address address = this.addressDto2address(addressCreateDto, new Address());
        return this.addressRepo.save(address);
    }

    public Address updateOne(int id, AddressUpdateDto addressUpdateDto) {
        Address oldEntity = this.getOne(id);
        Address newEntity = this.addressDto2address(addressUpdateDto, oldEntity);
        return this.addressRepo.save(newEntity);
    }

    public void delete(int id) {
        this.addressRepo.deleteById(id);
    }
}
