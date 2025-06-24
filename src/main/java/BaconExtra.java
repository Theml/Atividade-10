public class BaconExtra extends ComplementoDecorator {
    public BaconExtra(ItemPedido item) {
        super(item);
    }

    @Override
    public String getDescricao() {
        return item.getDescricao() + " + Bacon Extra";
    }

    @Override
    public double getPreco() {
        return item.getPreco() + 5.00;
    }
}