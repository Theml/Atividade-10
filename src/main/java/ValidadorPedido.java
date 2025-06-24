// Handler abstrato
public abstract class ValidadorPedido {
    protected ValidadorPedido proximoValidador;

    public void setProximo(ValidadorPedido proximo) {
        this.proximoValidador = proximo;
    }

    public final boolean validar(Pedido pedido) {
        boolean resultado = validarEspecifico(pedido);

        if (resultado && proximoValidador != null) {
            return proximoValidador.validar(pedido);
        }

        return resultado;
    }

    protected abstract boolean validarEspecifico(Pedido pedido);
    protected abstract String getMensagemErro();
}