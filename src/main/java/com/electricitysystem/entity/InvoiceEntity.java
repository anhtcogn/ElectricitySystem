package com.electricitysystem.entity;

import java.util.Date;

public class InvoiceEntity {
    private Integer id;
    private Date paymentDate;
    private double totalPayment;
    private int electricNumber;
    private boolean status;
    private double vatAmount;
    private double totalAmount;
    private Enum paymentMethod;

}
