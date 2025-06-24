import java.time.LocalDateTime;

public class PedidoFarmacia extends Pedido{
    private boolean receitaMedica;

    // Construtor padrão
    public PedidoFarmacia(int id, Cliente cliente, LocalDateTime dataAgendamento) {
        super(id, cliente, dataAgendamento);
    }

    // Construtor com data personalizada
    public PedidoFarmacia(int id, Cliente cliente) {
        this(id, cliente, LocalDateTime.now().plusHours(3));
    }

    public void preparar() {
        System.out.println("Separando medicamentos...");
        if (receitaMedica) {
            System.out.println("Validando receita médica");
        }
    }

    public void setReceitaMedica(boolean temReceita) {
        this.receitaMedica = temReceita;
    }

    public void verificarValidadeMedicamentos() {
        System.out.println("Verificando prazos de validade");
    }
}
