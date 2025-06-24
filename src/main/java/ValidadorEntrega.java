public class ValidadorEntrega extends ValidadorPedido {

    @Override
    protected boolean validarEspecifico(Pedido pedido) {
        System.out.println("Verificando área de entrega...");
        // Verificaria se entrega na região
        return pedido.getCliente().getEndereco() != null;
    }

    @Override
    protected String getMensagemErro() {
        return "Não entregamos nesta região";
    }
}