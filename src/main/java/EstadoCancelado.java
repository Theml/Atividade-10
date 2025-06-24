// Estado de Pedido cancelado - Implementação do padrão State
public class EstadoCancelado implements PedidoState {
    public void processarPedido(Pedido pedido) {
        System.out.println("Pedido #" + pedido.getId() + " cancelado. Reembolsando...");
    }

    public String getNomeEstado() {
        return "Cancelado";
    }
}