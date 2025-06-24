// Implementações concretas
public class ProcessadorRestaurante extends ProcessadorPedido {

    @Override
    protected void prepararItens(Pedido pedido) {
        System.out.println("Preparando pratos na cozinha...");
        // Lógica específica para restaurante
    }

    @Override
    protected void embalar(Pedido pedido) {
        System.out.println("Embalando comida em recipientes térmicos");
    }

    @Override
    protected int calcularTempoPreparacao() {
        return 30; // 30 minutos para restaurante
    }
}