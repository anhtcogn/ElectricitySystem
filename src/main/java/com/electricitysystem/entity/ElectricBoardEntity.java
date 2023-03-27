package com.electricitysystem.entity;

import jakarta.persistence.*;
import lombok.Data;

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
    private Date timeReadMeter;
    @Column(name = "time_update")
    private Date timeUpdate;
    @Column(name = "customer_id")
    private Integer customerId;

}
