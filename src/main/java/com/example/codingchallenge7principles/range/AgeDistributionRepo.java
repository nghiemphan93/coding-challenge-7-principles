package com.example.codingchallenge7principles.range;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgeDistributionRepo extends JpaRepository<AgeDistribution, Integer> {
}
