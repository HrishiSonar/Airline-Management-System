package com.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SigninResponse {
	private String jwt;
	private Integer customerId;
	
	
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer mesg) {
		this.customerId = mesg;
	}
	public SigninResponse(String jwt, Integer mesg) {
		super();
		this.jwt = jwt;
		this.customerId = mesg;
	}
	public SigninResponse() {
		super();
	}
	
}
