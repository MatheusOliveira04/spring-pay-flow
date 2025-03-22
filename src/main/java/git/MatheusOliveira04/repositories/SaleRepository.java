package git.MatheusOliveira04.repositories;

import git.MatheusOliveira04.models.Sale;
import git.MatheusOliveira04.models.enums.StatusSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SaleRepository extends JpaRepository<Sale, UUID> {

    List<Sale> findByStatus(StatusSale status);

}
