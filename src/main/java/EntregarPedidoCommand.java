public class EntregarPedidoCommand implements Command {
    private Pedido pedido;
    private PedidoState estadoAnterior;

    public EntregarPedidoCommand(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void execute() {
        estadoAnterior = pedido.getEstado();
        pedido.marcarComoEntregue();
    }

    @Override
    public void undo() {
        if (estadoAnterior != null) {
            pedido.setEstado(estadoAnterior);
        }
    }
}