package com.electricitysystem.entity;

import javax.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@Entity
@Table(name = "invoice")
public class InvoiceEntity {
    @Id
    private Integer id;
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    @Column(name = "total_payment")
    private double totalPayment;
    @Column(name = "electric_number")
    private int electricNumber;
    @Column(name = "status")
    private String status;
    @Column(name = "customer_code")
    private String customerCode;
    @Column(name = "electric_board_id")
    private Integer electricBoardId;
    private String token;
}
