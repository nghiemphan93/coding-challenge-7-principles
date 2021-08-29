package com.example.codingchallenge7principles.address;

import com.example.codingchallenge7principles.address.dto.AddressCreateDto;
import com.example.codingchallenge7principles.address.dto.AddressUpdateDto;
import com.example.codingchallenge7principles.address.dto.AgeDistroDto;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@AllArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>> getAllWithFilter(String filter) {
        return new ResponseEntity<>(this.addressService.getAll(filter), HttpStatus.OK);
    }

    @GetMapping("/age-distros")
    public ResponseEntity<List<AgeDistroDto>> getAllWithAgeDistro() {
        return new ResponseEntity<>(this.addressService.getAllWithAgeDistro(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getOne(@PathVariable int id) throws NotFoundException {
        return new ResponseEntity<>(this.addressService.getOne(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Address> create(@RequestBody @Valid AddressCreateDto addressCreateDto) {
        return new ResponseEntity<>(this.addressService.create(addressCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateOne(@PathVariable int id,
                                             @RequestBody @Valid AddressUpdateDto addressUpdateDto) throws NotFoundException {
        return new ResponseEntity<>(this.addressService.updateOne(id, addressUpdateDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOne(@PathVariable int id) {
        this.addressService.delete(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }
}
