package com.app.dtos;


import com.app.entities.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    @JsonProperty("flightName")
    private String flightName; // not needed to make payment

    @JsonProperty("flightId")
    private Integer flightId;

    @JsonProperty("userName")
    private String userName; // not needed to make payment

    @JsonProperty("status")
    private StatusEnum status;

    @JsonProperty("customerId")
    private Integer customerId;

    @JsonProperty("totalAmount")
    private double totalAmount;
}
