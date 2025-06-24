import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

class PedidoTest {
    private Cliente cliente;
    private LocalDateTime dataAgendamento;
    private SistemaIFood sistema;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("João Silva", "Rua das Flores, 123");
        dataAgendamento = LocalDateTime.now().plus(1, ChronoUnit.HOURS);
        sistema = SistemaIFood.getInstance();
    }

    // ========== TESTES DE FUNCIONALIDADE DO SISTEMA ==========

    @Test
    void testFluxoCompletoPedidoRestaurante() {
        // Cenário: Cliente faz pedido de restaurante completo
        SistemaIFood sistema = SistemaIFood.getInstance();
        PedidoFactory factory = new PedidoRestauranteFactory();
        
        // 1. Criar pedido
        Pedido pedido = sistema.criarPedido(factory, cliente);
        
        // 2. Adicionar itens com decorators
        ItemPedido pizza = new ItemBasico("Pizza Margherita", 25.0);
        ItemPedido pizzaCompleta = new BaconExtra(new QueijoExtra(pizza));
        
        GerenciadorComandos gerenciador = new GerenciadorComandos();
        gerenciador.executarComando(new AdicionarItemCommand(pedido, pizzaCompleta));
        
        // 3. Validar pedido
        ValidadorPedido validador = CadeiaValidacao.criarCadeia();
        pedido.setValorTotal(pizzaCompleta.getPreco());
        assertTrue(validador.validar(pedido));
        
        // 4. Processar pagamento
        ProcessadorPagamento pagamento = new ProcessadorPagamento();
        pagamento.setStrategy(new PagamentoCartao("1234-5678"));
        assertTrue(pagamento.processarPagamento(pedido.getValorTotal()));
        
        // 5. Processar pedido
        ProcessadorPedido processador = new ProcessadorRestaurante();
        assertDoesNotThrow(() -> processador.processarPedido(pedido));
        
        // 6. Acompanhar estados
        assertTrue(pedido.getEstado() instanceof EstadoAgendado);
        pedido.marcarComoEntregue();
        assertTrue(pedido.getEstado() instanceof EstadoEntregue);
    }

    @Test
    void testCenarioFarmaciaComReceita() {
        // Cenário específico: Pedido de farmácia com validação especial
        SistemaIFood sistema = SistemaIFood.getInstance();
        PedidoFarmacia pedido = (PedidoFarmacia) sistema.criarPedido(
            new PedidoFarmaciaFactory(), cliente);
        
        // Configurar características específicas
        pedido.setReceitaMedica(true);
        
        // Validação específica de farmácia
        ValidadorPedido validador = CadeiaValidacao.criarCadeia();
        pedido.setValorTotal(50.0);
        assertTrue(validador.validar(pedido));
        
        // Processamento com validação especial
        ProcessadorFarmacia processador = new ProcessadorFarmacia();
        assertTrue(processador.precisaValidacaoEspecial());
        assertDoesNotThrow(() -> processador.processarPedido(pedido));
        
        // Verificações específicas
        assertDoesNotThrow(() -> pedido.verificarValidadeMedicamentos());
    }

    @Test
    void testCenarioCancelamentoComUndo() {
        // Cenário: Cliente cancela pedido e depois desfaz
        PedidoRestaurante pedido = new PedidoRestaurante(1, cliente);
        PedidoState estadoOriginal = pedido.getEstado();
        
        GerenciadorComandos gerenciador = new GerenciadorComandos();
        
        // Cancelar pedido
        Command cancelar = new CancelarPedidoCommand(pedido);
        gerenciador.executarComando(cancelar);
        assertTrue(pedido.getEstado() instanceof EstadoCancelado);
        
        // Cliente muda de ideia - desfaz cancelamento
        gerenciador.desfazer();
        assertEquals(estadoOriginal.getClass(), pedido.getEstado().getClass());
        
        // Procede com entrega normal
        pedido.marcarComoEntregue();
        assertTrue(pedido.getEstado() instanceof EstadoEntregue);
    }

    // ========== TESTES DE PADRÕES DE PROJETO ==========

    // Singleton - Apenas o teste de instância única
    @Test
    void testSingletonGaranteInstanciaUnica() {
        SistemaIFood sistema1 = SistemaIFood.getInstance();
        SistemaIFood sistema2 = SistemaIFood.getInstance();
        assertSame(sistema1, sistema2);
    }

    // Factory - Teste de criação de diferentes tipos
    @Test
    void testFactoryCreationDifferentTypes() {
        assertTrue(new PedidoRestauranteFactory().criarPedido(1, cliente) instanceof PedidoRestaurante);
        assertTrue(new PedidoMercadoFactory().criarPedido(1, cliente) instanceof PedidoMercado);
        assertTrue(new PedidoFarmaciaFactory().criarPedido(1, cliente) instanceof PedidoFarmacia);
    }

    // Decorator - Teste de composição múltipla
    @Test
    void testDecoratorComposition() {
        ItemPedido item = new ItemBasico("Hambúrguer", 15.0);
        ItemPedido decorated = new EmbalagemEspecial(new QueijoExtra(new BaconExtra(item)));
        
        assertEquals("Hambúrguer + Bacon Extra + Queijo Extra (Embalagem Especial)", 
                    decorated.getDescricao());
        assertEquals(26.5, decorated.getPreco());
    }

    // Strategy - Teste de troca dinâmica
    @Test
    void testStrategyDynamicChange() {
        ProcessadorPagamento processador = new ProcessadorPagamento();
        
        // Teste com diferentes estratégias
        processador.setStrategy(new PagamentoCartao("1234"));
        assertTrue(processador.processarPagamento(100.0));
        
        processador.setStrategy(new PagamentoPix("user@email.com"));
        assertTrue(processador.processarPagamento(100.0));
    }

    // Chain of Responsibility - Teste de falha na cadeia
    @Test
    void testChainOfResponsibilityFailure() {
        ValidadorPedido cadeia = CadeiaValidacao.criarCadeia();
        PedidoRestaurante pedido = new PedidoRestaurante(1, new Cliente("Test", null));
        pedido.setValorTotal(0); // Falha no pagamento
        
        assertFalse(cadeia.validar(pedido)); // Deve falhar na validação
    }

    // ========== Teste de Demonstração Completa ==========
    // Este teste demonstra todos os padrões de projeto juntos em um cenário real de uso do sistema
    @Test
    void testDemonstracaoCompleta() {
        // 1. SINGLETON - Sistema único
        SistemaIFood sistema = SistemaIFood.getInstance();
        
        // 2. FACTORY - Diferentes tipos de pedido
        PedidoFactory[] factories = {
            new PedidoRestauranteFactory(),
            new PedidoMercadoFactory(), 
            new PedidoFarmaciaFactory()
        };
        
        for (PedidoFactory factory : factories) {
            Pedido pedido = sistema.criarPedido(factory, cliente);
            
            // 3. DECORATOR - Itens com complementos
            ItemPedido item = new ItemBasico("Produto", 20.0);
            ItemPedido itemDecorado = new EmbalagemEspecial(new QueijoExtra(item));
            
            // 4. COMMAND - Operações reversíveis
            GerenciadorComandos gerenciador = new GerenciadorComandos();
            gerenciador.executarComando(new AdicionarItemCommand(pedido, itemDecorado));
            
            // 5. STRATEGY - Diferentes formas de pagamento
            ProcessadorPagamento pagamento = new ProcessadorPagamento();
            pagamento.setStrategy(new PagamentoPix("test@email.com"));
            assertTrue(pagamento.processarPagamento(itemDecorado.getPreco()));
            
            // 6. CHAIN OF RESPONSIBILITY - Validação
            ValidadorPedido validador = CadeiaValidacao.criarCadeia();
            pedido.setValorTotal(itemDecorado.getPreco());
            assertTrue(validador.validar(pedido));
            
            // 7. TEMPLATE METHOD - Processamento específico
            ProcessadorPedido processador = getProcessadorFor(pedido);
            assertDoesNotThrow(() -> processador.processarPedido(pedido));
            
            // 8. STATE - Mudanças de estado
            assertTrue(pedido.getEstado() instanceof EstadoAgendado);
            pedido.marcarComoEntregue();
            assertTrue(pedido.getEstado() instanceof EstadoEntregue);
            
            // 9. OBSERVER - Notificações automáticas (implícito)
            assertDoesNotThrow(() -> pedido.processar());
        }
    }

    private ProcessadorPedido getProcessadorFor(Pedido pedido) {
        if (pedido instanceof PedidoRestaurante) return new ProcessadorRestaurante();
        if (pedido instanceof PedidoMercado) return new ProcessadorMercado();
        if (pedido instanceof PedidoFarmacia) return new ProcessadorFarmacia();
        throw new IllegalArgumentException("Tipo de pedido não suportado");
    }
}