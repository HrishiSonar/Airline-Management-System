package com.app.entities;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
@ToString
@EqualsAndHashCode(callSuper = true)
public class BookingDetails extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "customerId")
    private UserDetailsEntity customerId;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "paymentId")
    private PaymentDetails paymentID;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "flightId")
    private FlightDetails flightId;

    @Column(nullable = false)
    private double farePrice;

    @Column(nullable = true)
    private LocalTime duration;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "passenger_id"))
    private List<PassengerDetails> passengerId = new ArrayList<>();
}
