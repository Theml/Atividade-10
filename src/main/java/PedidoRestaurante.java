import java.time.LocalDateTime;

public class PedidoRestaurante extends Pedido {
    private String observacoesCozinha;

    public PedidoRestaurante(int id, Cliente cliente, LocalDateTime dataAgendamento) {
        super(id, cliente, dataAgendamento);
    }

    public PedidoRestaurante(int id, Cliente cliente) {
        this(id, cliente, LocalDateTime.now().plusHours(3));
    }

    public void preparar() {
        System.out.println("Preparando o pedido do restaurante...");
            if (observacoesCozinha != null) {
                System.out.println("Observações: " + observacoesCozinha);
            }
    }

    public void setObservacoesCozinha(String observacoes) {
        this.observacoesCozinha = observacoes;
    }
}