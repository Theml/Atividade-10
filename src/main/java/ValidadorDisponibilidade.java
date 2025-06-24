// Handlers concretos
public class ValidadorDisponibilidade extends ValidadorPedido {

    @Override
    protected boolean validarEspecifico(Pedido pedido) {
        // Simula verificação de disponibilidade
        System.out.println("Verificando disponibilidade dos itens...");
        // Aqui você verificaria se os itens estão disponíveis
        return true; // Assumindo disponível
    }

    @Override
    protected String getMensagemErro() {
        return "Alguns itens não estão disponíveis";
    }
}