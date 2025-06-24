// Configuração da cadeia
public class CadeiaValidacao {

    public static ValidadorPedido criarCadeia() {
        ValidadorPedido disponibilidade = new ValidadorDisponibilidade();
        ValidadorPedido pagamento = new ValidadorPagamento();
        ValidadorPedido entrega = new ValidadorEntrega();
        ValidadorPedido horario = new ValidadorHorario();

        // Configurando a cadeia
        disponibilidade.setProximo(pagamento);
        pagamento.setProximo(entrega);
        entrega.setProximo(horario);

        return disponibilidade;
    }
}