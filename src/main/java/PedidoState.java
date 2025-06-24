public interface PedidoState {
    void processarPedido(Pedido pedido);
    String getNomeEstado();
}