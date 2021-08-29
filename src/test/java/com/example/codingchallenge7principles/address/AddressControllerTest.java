package com.example.codingchallenge7principles.address;

import com.example.codingchallenge7principles.CodingChallenge7PrinciplesApplication;
import com.example.codingchallenge7principles.TestHelper;
import com.example.codingchallenge7principles.address.dto.AddressCreateDto;
import com.example.codingchallenge7principles.address.dto.AddressDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CodingChallenge7PrinciplesApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AddressControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AddressService addressService;
    private String baseUrl = "/api/v1/addresses";

    @Test
    public void shouldGetAllWithFilterReturnSuccess() throws Exception {
        // given
        AddressCreateDto addressCreateDto1 = new AddressCreateDto();
        addressCreateDto1.setFirstName("David");
        addressCreateDto1.setLastName("");
        addressCreateDto1.setBirthday(new Date());
        addressCreateDto1.setStreet("");
        addressCreateDto1.setHouseNumber("");
        addressCreateDto1.setPostalCode("");
        addressCreateDto1.setCity("");
        addressCreateDto1.setPhone("");
        addressCreateDto1.setMobile("");
        addressCreateDto1.setEmail("");
        addressCreateDto1.setNewsletter(true);
        this.addressService.create(addressCreateDto1);

        AddressCreateDto addressCreateDto2 = new AddressCreateDto();
        addressCreateDto2.setFirstName("Maria");
        addressCreateDto2.setLastName("");
        addressCreateDto2.setBirthday(new Date());
        addressCreateDto2.setStreet("");
        addressCreateDto2.setHouseNumber("");
        addressCreateDto2.setPostalCode("");
        addressCreateDto2.setCity("");
        addressCreateDto2.setPhone("");
        addressCreateDto2.setMobile("");
        addressCreateDto2.setEmail("");
        addressCreateDto2.setNewsletter(true);
        this.addressService.create(addressCreateDto2);

        AddressCreateDto addressCreateDto3 = new AddressCreateDto();
        addressCreateDto3.setFirstName("Marko");
        addressCreateDto3.setLastName("");
        addressCreateDto3.setBirthday(new Date());
        addressCreateDto3.setStreet("");
        addressCreateDto3.setHouseNumber("");
        addressCreateDto3.setPostalCode("");
        addressCreateDto3.setCity("");
        addressCreateDto3.setPhone("");
        addressCreateDto3.setMobile("");
        addressCreateDto3.setEmail("");
        addressCreateDto3.setNewsletter(true);
        this.addressService.create(addressCreateDto3);

        // when
        String filter = "davi";
        this.mockMvc
                .perform(get(this.baseUrl + "?filter=" + filter))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.length()")
                                .value(1)
                );
    }

    @Test
    public void shouldGetAllWithAgeDistroReturnSuccess() throws Exception {
        // given
        AddressCreateDto addressCreateDto1 = new AddressCreateDto();
        addressCreateDto1.setFirstName("David");
        addressCreateDto1.setLastName("");
        addressCreateDto1.setBirthday(new GregorianCalendar(2015, Calendar.APRIL, 20).getTime());
        addressCreateDto1.setStreet("");
        addressCreateDto1.setHouseNumber("");
        addressCreateDto1.setPostalCode("");
        addressCreateDto1.setCity("");
        addressCreateDto1.setPhone("");
        addressCreateDto1.setMobile("");
        addressCreateDto1.setEmail("");
        addressCreateDto1.setNewsletter(true);
        this.addressService.create(addressCreateDto1);

        AddressCreateDto addressCreateDto2 = new AddressCreateDto();
        addressCreateDto2.setFirstName("Maria");
        addressCreateDto2.setLastName("");
        addressCreateDto2.setBirthday(new GregorianCalendar(2016, Calendar.APRIL, 20).getTime());
        addressCreateDto2.setStreet("");
        addressCreateDto2.setHouseNumber("");
        addressCreateDto2.setPostalCode("");
        addressCreateDto2.setCity("");
        addressCreateDto2.setPhone("");
        addressCreateDto2.setMobile("");
        addressCreateDto2.setEmail("");
        addressCreateDto2.setNewsletter(true);
        this.addressService.create(addressCreateDto2);

        AddressCreateDto addressCreateDto3 = new AddressCreateDto();
        addressCreateDto3.setFirstName("Marko");
        addressCreateDto3.setLastName("");
        addressCreateDto3.setBirthday(new GregorianCalendar(2017, Calendar.APRIL, 20).getTime());
        addressCreateDto3.setStreet("");
        addressCreateDto3.setHouseNumber("");
        addressCreateDto3.setPostalCode("");
        addressCreateDto3.setCity("");
        addressCreateDto3.setPhone("");
        addressCreateDto3.setMobile("");
        addressCreateDto3.setEmail("");
        addressCreateDto3.setNewsletter(true);
        this.addressService.create(addressCreateDto3);

        // when
        this.mockMvc
                .perform(get(this.baseUrl + "/age-distros"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.length()")
                                .value(1)
                )
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[0].ageRange")
                                .value("0-10")
                )
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[0].count")
                                .value(3)
                );
    }

    @Test
    public void shouldGetOneReturnSuccess() throws Exception {
        // given
        AddressCreateDto addressCreateDto1 = new AddressCreateDto();
        addressCreateDto1.setFirstName("David");
        addressCreateDto1.setLastName("");
        addressCreateDto1.setBirthday(new GregorianCalendar(2015, Calendar.APRIL, 20).getTime());
        addressCreateDto1.setStreet("");
        addressCreateDto1.setHouseNumber("");
        addressCreateDto1.setPostalCode("");
        addressCreateDto1.setCity("");
        addressCreateDto1.setPhone("");
        addressCreateDto1.setMobile("");
        addressCreateDto1.setEmail("");
        addressCreateDto1.setNewsletter(true);
        Address address = this.addressService.create(addressCreateDto1);

        // when
        this.mockMvc
                .perform(get(this.baseUrl + "/" + address.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.firstName")
                                .value("David")
                );
    }

    @Test
    public void shouldCreateOneReturnSuccess() throws Exception {
        // given
        AddressCreateDto addressCreateDto1 = new AddressCreateDto();
        addressCreateDto1.setFirstName("David");
        addressCreateDto1.setLastName("");
        addressCreateDto1.setBirthday(new GregorianCalendar(2015, Calendar.APRIL, 20).getTime());
        addressCreateDto1.setStreet("");
        addressCreateDto1.setHouseNumber("");
        addressCreateDto1.setPostalCode("");
        addressCreateDto1.setCity("");
        addressCreateDto1.setPhone("");
        addressCreateDto1.setMobile("");
        addressCreateDto1.setEmail("");
        addressCreateDto1.setNewsletter(true);

        // when
        this.mockMvc
                .perform(post(this.baseUrl)
                        .content(TestHelper.asJsonString(addressCreateDto1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.firstName")
                                .value("David")
                );
    }

    @Test
    public void shouldUpdateOneReturnSuccess() throws Exception {
        // given
        AddressDto addressDto = new AddressCreateDto();
        addressDto.setFirstName("David");
        addressDto.setLastName("");
        addressDto.setBirthday(new GregorianCalendar(2015, Calendar.APRIL, 20).getTime());
        addressDto.setStreet("");
        addressDto.setHouseNumber("");
        addressDto.setPostalCode("");
        addressDto.setCity("");
        addressDto.setPhone("");
        addressDto.setMobile("");
        addressDto.setEmail("");
        addressDto.setNewsletter(true);
        Address address = this.addressService.create((AddressCreateDto) addressDto);
        addressDto.setFirstName("Martin");

        // when
        this.mockMvc
                .perform(put(this.baseUrl + "/" + address.getId())
                        .content(TestHelper.asJsonString(addressDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.firstName")
                                .value("Martin")
                );
    }

    @Test
    public void shouldDeleteOneReturnSuccess() throws Exception {
        // given
        AddressDto addressDto = new AddressCreateDto();
        addressDto.setFirstName("David");
        addressDto.setLastName("");
        addressDto.setBirthday(new GregorianCalendar(2015, Calendar.APRIL, 20).getTime());
        addressDto.setStreet("");
        addressDto.setHouseNumber("");
        addressDto.setPostalCode("");
        addressDto.setCity("");
        addressDto.setPhone("");
        addressDto.setMobile("");
        addressDto.setEmail("");
        addressDto.setNewsletter(true);
        Address address = this.addressService.create((AddressCreateDto) addressDto);

        // when
        this.mockMvc
                .perform(delete(this.baseUrl + "/" + address.getId()))
                .andExpect(status().isAccepted());
    }
}
