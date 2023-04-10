package com.electricitysystem.controller;

import com.electricitysystem.entity.ElectricBoardEntity;
import com.electricitysystem.entity.PaymentEntity;
import com.electricitysystem.service.ElectricBoardService;
import com.electricitysystem.service.PayWithCashService;
import com.electricitysystem.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class PaymentController {
    public static final String PAYPAL_SUCCESS_URL = "pay/success";
    public static final String PAYPAL_CANCEL_URL = "pay/cancel";


    @Autowired
    private PaypalService paymentService;
    @Autowired
    private ElectricBoardService electricBoardService;
    @Autowired
    private PayWithCashService payWithCashService;
    @PostMapping("/pay")
    public String payment(@RequestParam int electricBoardId) {
        try {
            ElectricBoardEntity electricBoard = electricBoardService.getOneById(electricBoardId);
            Payment payment = paymentService.createPayment(electricBoard.getTotalPayment() / 23447, "USD", "paypal",
                    "sale", "thanh toan tien dien", "http://localhost:9090/" + PAYPAL_CANCEL_URL,
                    "http://localhost:9090/" + PAYPAL_SUCCESS_URL);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return link.getHref();
                }
            }

        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping(value = PAYPAL_CANCEL_URL)
    public String cancelPay() {
        return "payment failed";
    }

    @GetMapping(value = PAYPAL_SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paymentService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    @PostMapping("payWithCash")
    public String payWithCash(
            @RequestParam int electricBoardId
    ) {
        return payWithCashService.pay(electricBoardId);
    }
}
