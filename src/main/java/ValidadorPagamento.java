public class ValidadorPagamento extends ValidadorPedido {

    @Override
    protected boolean validarEspecifico(Pedido pedido) {
        System.out.println("Validando forma de pagamento...");
        // Verificaria se o pagamento é válido
        return pedido.getValorTotal() > 0;
    }

    @Override
    protected String getMensagemErro() {
        return "Problema com a forma de pagamento";
    }
}