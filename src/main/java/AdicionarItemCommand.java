public class AdicionarItemCommand implements Command {
    private Pedido pedido;
    private ItemPedido item;

    public AdicionarItemCommand(Pedido pedido, ItemPedido item) {
        this.pedido = pedido;
        this.item = item;
    }

    public void execute() {
        pedido.adicionarItem(item);
    }

    public void undo() {
        pedido.removerItem(item);
    }
}