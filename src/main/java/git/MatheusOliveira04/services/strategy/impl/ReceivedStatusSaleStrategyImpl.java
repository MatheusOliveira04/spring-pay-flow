package git.MatheusOliveira04.services.strategy.impl;

import git.MatheusOliveira04.models.Sale;
import git.MatheusOliveira04.services.exception.IntegrityViolationException;
import git.MatheusOliveira04.services.strategy.StatusSaleStrategy;

import java.time.LocalDate;

public class ReceivedStatusSaleStrategyImpl implements StatusSaleStrategy {

    @Override
    public void validateStatusSale(Sale sale) {
            checkSalePaymentsIsNullOrEmpty(sale);
            setDatePayedAsCurrentDate(sale);
    }

    private void checkSalePaymentsIsNullOrEmpty(Sale sale) {
        if (sale.checkPaymentsIsNullOrEmpty()) {
            throw new IntegrityViolationException("Status da venda for RECEBIDO, deve ter pagamento");
        }
    }

    private void setDatePayedAsCurrentDate(Sale sale) {
        sale.setDatePayed(LocalDate.now());
    }
}
