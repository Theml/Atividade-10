public class EstadoEntregue implements PedidoState{

    public void processarPedido(Pedido pedido) {
        System.out.println("Pedido #" + pedido.getId() + " entregue com sucesso!");
        pedido.getDataEntrega();
    }

    public String getNomeEstado() {
        return "Entregue";
    }
}
