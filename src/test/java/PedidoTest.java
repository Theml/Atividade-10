import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

class PedidoTest {
    private Cliente cliente;
    private LocalDateTime dataAgendamento;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("João Silva");
        dataAgendamento = LocalDateTime.now().plus(1, ChronoUnit.HOURS);
    }

    @Test
    void testPedidoCriadoComEstadoAgendado() {
        PedidoRestaurante pedido = new PedidoRestaurante(1, cliente, dataAgendamento);
        assertTrue(pedido.getEstado() instanceof EstadoAgendado);
        assertEquals(dataAgendamento, pedido.getDataAgendamento());
    }

    @Test
    void testCriacaoComDataInvalida() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PedidoRestaurante(1, cliente, LocalDateTime.now().minusHours(1));
        });
    }

    // Testes específicos para PedidoRestaurante
    @Test
    void testPreparoPedidoRestaurante() {
        PedidoRestaurante pedido = new PedidoRestaurante(1, cliente);
        pedido.setObservacoesCozinha("Sem cebola");

        assertDoesNotThrow(() -> pedido.preparar());
    }

    @Test
    void testPreparoComObservacoes() {
        PedidoRestaurante pedido = new PedidoRestaurante(1, cliente);
        pedido.setObservacoesCozinha("Sem lactose");
        pedido.preparar();
    }

    // Testes específicos para PedidoMercado
    @Test
    void testPreparoPedidoMercado() {
        PedidoMercado pedido = new PedidoMercado(2, cliente);
        pedido.setPrecisaEmbalagemEspecial(true);

        assertDoesNotThrow(() -> pedido.preparar());
    }

    // Testes específicos para PedidoFarmacia
    @Test
    void testPreparoPedidoFarmacia() {
        PedidoFarmacia pedido = new PedidoFarmacia(3, cliente);
        pedido.setReceitaMedica(true);

        assertDoesNotThrow(() -> {
            pedido.preparar();
            pedido.verificarValidadeMedicamentos();
        });
    }

    // Testes de transição de estados
    @Test
    void testTransicaoParaEntregue() {
        PedidoRestaurante pedido = new PedidoRestaurante(1, cliente, dataAgendamento);
        pedido.marcarComoEntregue();

        assertTrue(pedido.getEstado() instanceof EstadoEntregue);
        assertNotNull(pedido.getDataEntrega());
    }

    @Test
    void testTransicaoParaCancelado() {
        PedidoMercado pedido = new PedidoMercado(2, cliente, dataAgendamento);
        pedido.cancelar();

        assertTrue(pedido.getEstado() instanceof EstadoCancelado);
    }
}