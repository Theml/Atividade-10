public class ProcessadorMercado extends ProcessadorPedido {

    @Override
    protected void prepararItens(Pedido pedido) {
        System.out.println("Coletando itens nas prateleiras...");
    }

    @Override
    protected void embalar(Pedido pedido) {
        System.out.println("Embalando produtos em sacolas");
    }

    @Override
    protected int calcularTempoPreparacao() {
        return 20; // 20 minutos para mercado
    }
}