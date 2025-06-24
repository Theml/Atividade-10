import java.time.LocalDateTime;

public class PedidoMercadoFactory implements PedidoFactory {

    //Utilização de data Padrão do Pedido
    public Pedido criarPedido(int id, Cliente cliente) {
        return new PedidoMercado(id, cliente);
    }

    //Utilização de data customizada do Pedido
    public Pedido criarPedido(int id, Cliente cliente, LocalDateTime dataAgendamento) {
        return new PedidoMercado(id, cliente, dataAgendamento);
    }
}