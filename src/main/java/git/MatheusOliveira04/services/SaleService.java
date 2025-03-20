package git.MatheusOliveira04.services;

import git.MatheusOliveira04.models.Sale;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
public interface SaleService {

    Page<Sale> findAll(@PositiveOrZero int page, @Positive @Max(100) int size);

    Sale findById(@NotNull UUID id);

    Sale insert(@Valid Sale sale);

    Sale update(@Valid Sale sale);

    void delete(@NotNull UUID id);

}
