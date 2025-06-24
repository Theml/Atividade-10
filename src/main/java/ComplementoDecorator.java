// Decorator base
public abstract class ComplementoDecorator extends ItemPedido {
    protected ItemPedido item;

    public ComplementoDecorator(ItemPedido item) {
        this.item = item;
    }

    @Override
    public String getDescricao() {
        return item.getDescricao();
    }

    @Override
    public double getPreco() {
        return item.getPreco();
    }
}