package com.electricitysystem.controller;

import com.electricitysystem.entity.ElectricBoardEntity;
import com.electricitysystem.entity.InvoiceEntity;
import com.electricitysystem.repository.InvoiceRepository;
import com.electricitysystem.service.ElectricBoardService;
import com.electricitysystem.service.InvoiceService;
import com.electricitysystem.service.PayWithCashService;
import com.electricitysystem.service.PayWithPaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/")
public class PaymentController {
    public static final String PAYPAL_SUCCESS_URL = "pay/success";
    public static final String PAYPAL_CANCEL_URL = "pay/cancel";

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private PayWithPaypalService payWithPaypalService;
    @Autowired
    private ElectricBoardService electricBoardService;
    @Autowired
    private PayWithCashService payWithCashService;
    @PostMapping("/pay")
    public String payment(@RequestParam int id) {
        String token = "";
        try {
            ElectricBoardEntity electricBoard = electricBoardService.getOneById(id);
            Payment payment = payWithPaypalService.createPayment(electricBoard.getTotalPayment() / 23447, "USD", "paypal",
                    "sale", "thanh toan tien dien"
                    , "http://localhost:9090/" + PAYPAL_CANCEL_URL,
                    "http://localhost:9090/" + PAYPAL_SUCCESS_URL);
            System.out.println(payment);
            InvoiceEntity invoice = invoiceService.getById(id);
            invoice.setStatus("PAYMENT PENDING");
//            invoiceRepository.save(invoice);

            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    String[] s = link.getHref().split("=");
                    token = s[2];
                    invoice.setToken(token);
                    invoiceRepository.save(invoice);
                    return link.getHref();
                }
            }

        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "payment pending";
    }

    @GetMapping(value = PAYPAL_CANCEL_URL)
    public String cancelPay(
            @RequestParam("token") String token
    ) {
        InvoiceEntity invoice = invoiceService.getByToken(token);
        invoice.setPaymentDate(LocalDateTime.now());
        invoice.setStatus("UNPAID");
        invoiceRepository.save(invoice);
        return "payment failed";
    }
    @GetMapping(value = PAYPAL_SUCCESS_URL)
    public String successPay(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("token") String token,
            @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = payWithPaypalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                InvoiceEntity invoice = invoiceService.getByToken(token);
                invoice.setPaymentDate(LocalDateTime.now());
                invoice.setStatus("PAID");
                invoiceRepository.save(invoice);
                return "payment success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "payment success";
    }

    @PostMapping("payWithCash")
    public String payWithCash(
            @RequestParam int id
    ) {
        return payWithCashService.payWithCash(id);
    }
}
