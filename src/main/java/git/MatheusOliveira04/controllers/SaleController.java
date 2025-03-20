package git.MatheusOliveira04.controllers;

import git.MatheusOliveira04.models.Sale;
import git.MatheusOliveira04.models.dtos.reponse.SalePageResponse;
import git.MatheusOliveira04.models.dtos.request.SaleRequest;
import git.MatheusOliveira04.models.mappers.SaleMapper;
import git.MatheusOliveira04.services.SaleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<SalePageResponse> findAll(
            @RequestParam(defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(defaultValue = "10") @Positive @Max(100) int size) {
        Page<Sale> salesFound = saleService.findAll(page, size);

        return ResponseEntity.ok(
                new SalePageResponse(salesFound.toList(), salesFound.getTotalElements(), salesFound.getTotalPages())
        );
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
