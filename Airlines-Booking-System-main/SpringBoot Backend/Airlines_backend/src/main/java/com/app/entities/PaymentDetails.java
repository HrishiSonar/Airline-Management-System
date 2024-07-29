package com.app.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.builder.ToStringExclude;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter 
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"customerId", "bookingId"})
@EqualsAndHashCode(callSuper = true)
public class PaymentDetails extends BaseEntity {
    @OneToOne(mappedBy = "paymentID", cascade = CascadeType.ALL)
    private BookingDetails bookingId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private UserDetailsEntity customerId;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Column(nullable = false)
    private double totalAmount;
}
