import java.util.ArrayList;
import java.util.List;

public class SistemaIFood {
    private final List<Pedido> pedidos = new ArrayList<>();

    private SistemaIFood() {}
    private static SistemaIFood instance = new SistemaIFood();
    public static SistemaIFood getInstance() {
        return instance;
    }
     

    public Pedido criarPedido(PedidoFactory factory, Cliente cliente) {
        Pedido pedido = factory.criarPedido(pedidos.size() + 1, cliente);
        pedidos.add(pedido);
        return pedido;
    }
}