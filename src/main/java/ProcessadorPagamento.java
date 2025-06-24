// Context
public class ProcessadorPagamento {
    private PagamentoStrategy strategy;

    public void setStrategy(PagamentoStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean processarPagamento(double valor) {
        if (strategy == null) {
            throw new IllegalStateException("Estratégia de pagamento não definida");
        }
        return strategy.processarPagamento(valor);
    }
}