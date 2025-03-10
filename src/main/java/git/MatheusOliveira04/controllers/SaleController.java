package git.MatheusOliveira04.controllers;

import git.MatheusOliveira04.models.Payment;
import git.MatheusOliveira04.services.impls.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/sale")
@RestController
public class SaleController {

    @Autowired
    private PaymentServiceImpl paymentMethodService;

    @GetMapping
    public String get() {
        return "Hello world!";
    }

    @GetMapping("/payment-method")
    public ResponseEntity<Payment> payment(@RequestBody Payment payment) {
        paymentMethodService.pay(payment.getPaymentMethod(), payment.getAmountReceived());
        return ResponseEntity.ok(payment);
    }
}
