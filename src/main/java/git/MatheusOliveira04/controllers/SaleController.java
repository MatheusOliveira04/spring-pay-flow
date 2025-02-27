package git.MatheusOliveira04.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/sale")
@RestController
public class SaleController {

    @GetMapping
    public String get() {
        return "Hello world!";
    }
}
