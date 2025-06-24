// Strategies concretas
public class PagamentoCartao implements PagamentoStrategy {
    private String numeroCartao;

    public PagamentoCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    @Override
    public boolean processarPagamento(double valor) {
        System.out.println("Processando pagamento de R$" + valor + " no cartão: " + numeroCartao);
        // Lógica específica do cartão
        return true;
    }

    @Override
    public String getTipoPagamento() {
        return "Cartão de Crédito";
    }
}