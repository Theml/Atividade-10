import java.time.LocalDateTime;

public class PedidoMercado extends Pedido {
    private boolean precisaEmbalagemEspecial;

    public PedidoMercado(int id, Cliente cliente, LocalDateTime dataAgendamento) {
        super(id, cliente, dataAgendamento);
    }

    public PedidoMercado(int id, Cliente cliente){
        this(id, cliente, LocalDateTime.now().plusHours(3));
    }

    public void preparar() {
        System.out.println("Separando itens do mercado...");
        if (precisaEmbalagemEspecial) {
            System.out.println("Usando embalagem especial para produtos frágeis");
        }
    }

    public void setPrecisaEmbalagemEspecial(boolean precisa) {
        this.precisaEmbalagemEspecial = precisa;
    }
}