import java.time.LocalDateTime;
public interface PedidoFactory {
    // Método para criar um pedido com data padrão
    default Pedido criarPedido(int id, Cliente cliente){
        return criarPedido(id, cliente, LocalDateTime.now().plusHours(1));
    }

    // Método para criar um pedido com data personalizada
    Pedido criarPedido(int id, Cliente cliente, LocalDateTime dataAgendamento);
}