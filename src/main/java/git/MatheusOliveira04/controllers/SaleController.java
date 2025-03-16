package git.MatheusOliveira04.controllers;

import git.MatheusOliveira04.models.Sale;
import git.MatheusOliveira04.models.dtos.request.SaleRequest;
import git.MatheusOliveira04.models.mappers.SaleMapper;
import git.MatheusOliveira04.services.SaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/sale")
@RestController
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private SaleMapper saleMapper;

    @GetMapping
    public String get() {
        return "Hello world!";
    }

    @PostMapping
    public ResponseEntity<Sale> payment(@RequestBody @Valid SaleRequest saleRequest) {
        return ResponseEntity.ok(saleService.insert(saleMapper.toSale(saleRequest)));
    }
}
