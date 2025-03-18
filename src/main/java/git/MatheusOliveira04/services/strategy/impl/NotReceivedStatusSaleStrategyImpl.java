package git.MatheusOliveira04.services.strategy.impl;

import git.MatheusOliveira04.models.Sale;
import git.MatheusOliveira04.services.exception.IntegrityViolationException;
import git.MatheusOliveira04.services.strategy.StatusSaleStrategy;

public class NotReceivedStatusSaleStrategyImpl implements StatusSaleStrategy {

    @Override
    public void validateStatusSale(Sale sale) {
        checkSalePaymentsIsNotNullOrNotEmpty(sale);
    }

    private void checkSalePaymentsIsNotNullOrNotEmpty(Sale sale) {
        if (!sale.checkPaymentsIsNullOrEmpty()) {
            throw new IntegrityViolationException("Status da venda for NÃO PAGO, não deve ter pagamento");
        }
    }
}
