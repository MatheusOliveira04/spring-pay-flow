package git.MatheusOliveira04.services;

import git.MatheusOliveira04.models.Sale;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
public interface SaleService {

    List<Sale> findAll();

    Sale findById(@NotNull UUID id);

    Sale insert(@Valid Sale sale);

    Sale update(@Valid Sale sale);

    void delete(@NotNull UUID id);

}
