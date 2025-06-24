import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EstadoAgendado implements PedidoState {
    private final LocalDateTime dataAgendada;

    public EstadoAgendado(LocalDateTime dataAgendada) {
        this.dataAgendada = dataAgendada;
    }

    public void processarPedido(Pedido pedido) {
        System.out.println("Pedido agendado para: " +
                dataAgendada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }

    public String getNomeEstado() {
        return "Agendado";
    }
}