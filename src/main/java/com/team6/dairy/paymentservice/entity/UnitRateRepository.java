package com.team6.dairy.paymentservice.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRateRepository extends JpaRepository<UnitRate, Long> {
	UnitRate findByFat(Integer fat);
}