// Decorators concretos
public class QueijoExtra extends ComplementoDecorator {
    public QueijoExtra(ItemPedido item) {
        super(item);
    }

    @Override
    public String getDescricao() {
        return item.getDescricao() + " + Queijo Extra";
    }

    @Override
    public double getPreco() {
        return item.getPreco() + 3.50;
    }
}