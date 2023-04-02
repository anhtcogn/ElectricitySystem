package com.electricitysystem.entity;

import javax.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "payment")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double totalAmount;
    private PaymentMethod paymentMode;
    private Date paymentDate;
    private Integer customerId;
}

enum PaymentMethod {
    CASH,
    ONLINE,
}