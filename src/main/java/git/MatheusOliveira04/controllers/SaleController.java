package git.MatheusOliveira04.controllers;

import git.MatheusOliveira04.models.Sale;
import git.MatheusOliveira04.models.dtos.request.SaleRequest;
import git.MatheusOliveira04.models.mappers.SaleMapper;
import git.MatheusOliveira04.services.SaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/sale")
@RestController
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private SaleMapper saleMapper;

    @GetMapping
    public ResponseEntity<List<Sale>> findAll() {
        return ResponseEntity.ok(saleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(saleService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Sale> insert(@RequestBody @Valid SaleRequest saleRequest, UriComponentsBuilder uriComponentsBuilder) {
        Sale sale = saleService.insert(saleMapper.toSale(saleRequest));
        return ResponseEntity.created(uriComponentsBuilder
                        .path("/api/v1/sale/{id}")
                        .buildAndExpand(sale.getId())
                        .toUri())
                .body(sale);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sale> update(@PathVariable UUID id, @RequestBody SaleRequest saleRequest) {
        Sale sale = saleMapper.toSale(saleRequest);
        sale.setId(id);
        return ResponseEntity.ok(saleService.update(sale));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
