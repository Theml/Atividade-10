import java.time.LocalDateTime;

public class PedidoRestauranteFactory implements PedidoFactory {

    // Utilização de data padrão do Pedido
    public Pedido criarPedido(int id, Cliente cliente) {
        return new PedidoRestaurante(id, cliente);
    }

    // Utilização de data customizada do Pedido
    public Pedido criarPedido(int id, Cliente cliente, LocalDateTime dataAgendamento) {
        return new PedidoRestaurante(id, cliente, dataAgendamento);
    }
}