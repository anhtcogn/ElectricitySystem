package com.electricitysystem.entity;

import javax.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
@Entity
@Table(name = "invoice")
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "payment_date")
    private Date paymentDate;
    @Column(name = "total_payment")
    private double totalPayment;
    @Column(name = "electric_number")
    private int electricNumber;
    @Column(name = "status")
    private boolean status;
    @Column(name = "vat_amount")
    private double vatAmount;
    @Column(name = "total_amount")
    private double totalAmount;
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "electric_board_id")
    private Integer electricBoardId;
}
