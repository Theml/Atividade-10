public class EmbalagemEspecial extends ComplementoDecorator {
    public EmbalagemEspecial(ItemPedido item) {
        super(item);
    }

    @Override
    public String getDescricao() {
        return item.getDescricao() + " (Embalagem Especial)";
    }

    @Override
    public double getPreco() {
        return item.getPreco() + 2.00;
    }
}