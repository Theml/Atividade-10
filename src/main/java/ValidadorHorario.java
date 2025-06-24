public class ValidadorHorario extends ValidadorPedido {

    @Override
    protected boolean validarEspecifico(Pedido pedido) {
        System.out.println("Verificando horário de funcionamento...");
        // Verificaria horário de funcionamento
        return true; // Assumindo dentro do horário
    }

    @Override
    protected String getMensagemErro() {
        return "Fora do horário de funcionamento";
    }
}