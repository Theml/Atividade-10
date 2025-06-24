// Interface Strategy
public interface PagamentoStrategy {
    boolean processarPagamento(double valor);
    String getTipoPagamento();
}
