public class PagamentoDinheiro implements PagamentoStrategy {
    @Override
    public boolean processarPagamento(double valor) {
        System.out.println("Pagamento em dinheiro de R$" + valor);
        return true;
    }

    @Override
    public String getTipoPagamento() {
        return "Dinheiro";
    }
}