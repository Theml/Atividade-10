public class ProcessadorFarmacia extends ProcessadorPedido {

    @Override
    protected void prepararItens(Pedido pedido) {
        System.out.println("Separando medicamentos...");
    }

    @Override
    protected void embalar(Pedido pedido) {
        System.out.println("Embalando medicamentos com cuidado especial");
    }

    @Override
    protected int calcularTempoPreparacao() {
        return 15; // 15 minutos para farmácia
    }

    @Override
    protected boolean precisaValidacaoEspecial() {
        return true; // Farmácia precisa validar receitas
    }

    @Override
    protected void validacaoEspecial(Pedido pedido) {
        System.out.println("Validando receitas médicas...");
    }
}