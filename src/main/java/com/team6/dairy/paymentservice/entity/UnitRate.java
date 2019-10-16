package com.team6.dairy.paymentservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UnitRate {

	@Id
	private String id;
	private Integer fat;
	private Integer rate;
	
	public UnitRate() {}
	
	public UnitRate(String id, Integer fat, Integer rate) {
		super();
		this.id = id;
		this.fat = fat;
		this.rate = rate;	
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getFat() {
		return fat;
	}
	public void setFat(Integer fat) {
		this.fat = fat;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}	
}
