package com.electricitysystem.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "electric_board")
public class ElectricBoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "meter_code", nullable = false)

    private String meterCode;
    @Column(name = "old_number", nullable = false)
    @Min(0)
    private int oldNumber;
    @Column(name = "new_number", nullable = false)
    @Min(0)
    private int newNumber;
    @Column(name = "total_number", nullable = false)
    @Min(0)
    private int totalNumber;
    @Column(name = "time_read", nullable = false)
    private String timeReadMeter;
    @Column(name = "time_update", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private LocalDateTime timeUpdate;
    @Column(name = "customer_code")
    private String customerCode;
    @Column(name = "total_payment")
    private Double totalPayment;
    @NotEmpty(message = "Ky hoa don khong duoc de trong")
    private String period;
}
