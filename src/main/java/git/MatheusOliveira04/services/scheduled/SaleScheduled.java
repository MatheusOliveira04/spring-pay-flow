package git.MatheusOliveira04.services.scheduled;

import git.MatheusOliveira04.models.Sale;
import git.MatheusOliveira04.models.enums.StatusSale;
import git.MatheusOliveira04.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SaleScheduled {

    @Autowired
    private SaleRepository saleRepository;

    @Scheduled(cron = "0/10 * * * * *")
    @Async
    public void checkExpirationDateToPay() {
        saleRepository.findByStatus(StatusSale.DUE).forEach(
                sale -> {
                    if (checkDateToPayPassed(sale)) {
                        updateStatusSaleForNotReceived(sale);
                        saleRepository.save(sale);
                    }
                }
        );
    }

    private Boolean checkDateToPayPassed(Sale sale) {
        return sale.getDateToPay().isAfter(LocalDate.now()) || sale.getDateToPay().isEqual(LocalDate.now());
    }

    private void updateStatusSaleForNotReceived(Sale sale) {
        sale.setStatus(StatusSale.NOT_RECEIVED);
    }

}
