package com.team6.dairy.paymentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.team6.dairy.paymentservice.entity.UnitRate;
import com.team6.dairy.paymentservice.entity.UnitRateRepository;

@SpringBootApplication
public class PaymentServiceApplication implements CommandLineRunner {

	@Autowired
	private UnitRateRepository unitRepo;

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
	
		long rate_count = unitRepo.count();
		if (rate_count == 0) {
			unitRepo.save(new UnitRate("I0001", 5, 20));
			unitRepo.save(new UnitRate("I0002", 7, 25));
			unitRepo.save(new UnitRate("I0003", 10, 30));
			unitRepo.save(new UnitRate("I0004", 12, 35));
			unitRepo.save(new UnitRate("I0005", 15, 40));
			unitRepo.save(new UnitRate("I0006", 20, 45));
		}

	}

}
