package com.example.codingchallenge7principles.config;

import com.example.codingchallenge7principles.address.Address;
import com.example.codingchallenge7principles.address.AddressRepo;
import com.example.codingchallenge7principles.range.AgeDistribution;
import com.example.codingchallenge7principles.range.AgeDistributionRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final AddressRepo addressRepo;
    private final AgeDistributionRepo ageDistributionRepo;
    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.createAgeDistributions();
        if (!DataLoader.isJUnitTest()) {
            this.loadAddresses();
        }
    }

    public static boolean isJUnitTest() {
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            if (element.getClassName().startsWith("org.junit.")) {
                return true;
            }
        }
        return false;
    }

    @Transactional
    public void loadAddresses() throws IOException {
        try {
            if (this.addressRepo.findAll().isEmpty()) {
                ObjectMapper objectMapper = new ObjectMapper();
                InputStream file = new ClassPathResource("data/addresses.json").getInputStream();
                List<Address> addresses = Arrays.asList(objectMapper.readValue(file, Address[].class));
                this.addressRepo.saveAll(addresses);
            }
            logger.info("loaded addresses...");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    public void createAgeDistributions() {
        try {
            if (this.ageDistributionRepo.findAll().isEmpty()) {
                for (int i = 0; i < 130; i += 10) {
                    AgeDistribution ageDistribution = new AgeDistribution();
                    ageDistribution.setLowerLimit(i == 0 ? i : i + 1);
                    ageDistribution.setUpperLimit(i + 10);
                    ageDistribution.setRange((i == 0 ? i : i + 1) + "-" + (i + 10));
                    this.ageDistributionRepo.save(ageDistribution);
                }
                logger.info("created age distributions...");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
