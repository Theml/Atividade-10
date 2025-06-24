public class PagamentoPix implements PagamentoStrategy {
    private String chavePix;

    public PagamentoPix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public boolean processarPagamento(double valor) {
        System.out.println("Processando PIX de R$" + valor + " para: " + chavePix);
        // Lógica específica do PIX
        return true;
    }

    @Override
    public String getTipoPagamento() {
        return "PIX";
    }
}