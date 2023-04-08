package com.electricitysystem.entity;

import javax.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "electric_board")
public class ElectricBoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "meter_code")
    private String meterCode;
    @Column(name = "old_number")
    private int oldNumber;
    @Column(name = "new_number")
    private int newNumber;
    @Column(name = "total_number")
    private int totalNumber;
    @Column(name = "time_read")
    private String timeReadMeter;
    @Column(name = "time_update")
    private LocalDateTime timeUpdate;
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "total_payment")
    private double totalPayment;
}
