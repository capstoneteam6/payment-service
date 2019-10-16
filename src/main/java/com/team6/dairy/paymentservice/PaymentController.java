package com.team6.dairy.paymentservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.team6.dairy.paymentservice.entity.PaymentResponse;
import com.team6.dairy.paymentservice.entity.UnitRate;
import com.team6.dairy.paymentservice.entity.UnitRateRepository;

@RestController
public class PaymentController {

	@Autowired
	private UnitRateRepository unitRepo;
	
//	@Autowired
//	private RestTemplate restTemplate;

	@Value("${VDS_COLLECTION_SERVICE_URI:http://localhost:7100}")
	private String vdsCollectionServiceHost;
	private String vdsCollectionServiceUrl = "/dairy/api/collection/vdcs-collection";
	
	@GetMapping("/unit-rate/{fat}")
	public UnitRate retrieveUnitRate(@PathVariable Integer fat) {
		
		UnitRate rate = unitRepo.findByFat(fat);
		return rate;		
	}
	
	@GetMapping("/calculate-payment/farmerId/{farmerId}")
	public List<PaymentResponse> calculatePayment(@PathVariable String farmerId) {
		
		ResponseEntity<PaymentResponse[]> responseEntity = new RestTemplate().getForEntity(vdsCollectionServiceHost + vdsCollectionServiceUrl, PaymentResponse[].class);
		PaymentResponse[] responses = responseEntity.getBody();
		List<PaymentResponse> payments = new ArrayList<>(responses.length);
		for(PaymentResponse pr : responses) {
			if(farmerId != null && (farmerId.equals("all") || farmerId.equals(pr.getFarmerId()))) {
				Integer unitRate = unitRepo.findByFat(pr.getFat()).getRate();
				pr.setRate(unitRate);
				pr.setPayment(pr.getRate()*pr.getQuantity());
				payments.add(pr);
			} 
		}
		return payments;
	}
	
	
}
