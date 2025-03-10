package git.MatheusOliveira04.controllers;

import git.MatheusOliveira04.models.Payment;
import git.MatheusOliveira04.services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/sale")
@RestController
public class SaleController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public String get() {
        return "Hello world!";
    }

    @PostMapping("/payment-method")
    public ResponseEntity<Payment> payment(@RequestBody @Valid Payment payment) {
        paymentService.pay(payment.getPaymentMethod(), payment.getAmountReceived());
        return ResponseEntity.ok(payment);
    }
}
