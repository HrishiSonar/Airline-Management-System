package com.app.entities;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.ToStringExclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString(exclude = "paymentID")
public class UserDetailsEntity extends BaseEntity /*THIS IS AUTOMATICALLY IMPLEMENTED WITH SERIALIZABLE DUE TO INHERITANCE (BASE ENTITY)*/{
	
	public UserDetailsEntity( String string2, String string3, String encode, UserRole roleAdmin) {
        name=string2;
		cpass=encode;
		email=string3;
		role=roleAdmin;
    }
	

    @Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false,length=50)
	private String email;

	@Column(nullable = false, length = 500)
	private String cpass;

	@OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_details_id")  // Specify the foreign key column in PassangerDetails
    private List<PassengerDetails> passengerId = new ArrayList<>();

	// @OneToOne(mappedBy = "customer")
	// private GeneralDetails generalDetails;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;

	@OneToMany(mappedBy = "customerId")
	private List<PaymentDetails> paymentID = new ArrayList<>();
	
	public String getCpass() {
		return cpass;
	}
	public void setCpass(String p) {
		cpass=p;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public UserDetailsEntity() {
		super();
	}
	
	
}
