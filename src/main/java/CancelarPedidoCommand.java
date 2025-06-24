// Concrete Commands
public class CancelarPedidoCommand implements Command {
    private Pedido pedido;
    private PedidoState estadoAnterior;

    public CancelarPedidoCommand(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void execute() {
        estadoAnterior = pedido.getEstado();
        pedido.cancelar();
    }

    @Override
    public void undo() {
        if (estadoAnterior != null) {
            pedido.setEstado(estadoAnterior);
        }
    }
}