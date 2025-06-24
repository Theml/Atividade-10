import java.time.LocalDateTime;
import java.util.Observable;

public abstract class Pedido extends Observable {
    protected int id;
    protected Cliente cliente;
    protected PedidoState estado;
    private double valorTotal;
    private LocalDateTime dataAgendamento;
    private LocalDateTime dataEntrega;


    public Pedido(int id, Cliente cliente, LocalDateTime dataAgendamento) {
    if (dataAgendamento == null || dataAgendamento.isBefore(LocalDateTime.now())) {
        throw new IllegalArgumentException("Data de agendamento inválida");
    }
        this.id = id;
        this.cliente = cliente;
        this.addObserver(cliente);
        this.dataAgendamento = dataAgendamento;
        this.estado = new EstadoAgendado(dataAgendamento);
    }

    public void agendar(LocalDateTime novaData) {
        if (novaData.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Data de reagendamento não pode ser no passado");
        }
        this.dataAgendamento = novaData;
        setEstado(new EstadoAgendado(novaData));
    }

    public void marcarComoEntregue() {
        if (!(estado instanceof EstadoAgendado)) {
            throw new IllegalStateException("Só pode entregar pedidos agendados");
        }
        this.dataEntrega = LocalDateTime.now();
        setEstado(new EstadoEntregue());
    }

    public void cancelar() {
        if (!(estado instanceof EstadoAgendado)) {
            throw new IllegalStateException("Só pode cancelar pedidos agendados");
        }
        setEstado(new EstadoCancelado());
    }

    public void setEstado(PedidoState novoEstado) {
        this.estado = novoEstado;
        setChanged();
        notifyObservers("Pedido #" + id + " - " + novoEstado.getNomeEstado());
    }

    public void processar() {
        estado.processarPedido(this);
    }

    // Getters
    public LocalDateTime getDataAgendamento() {
        return dataAgendamento;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public PedidoState getEstado() {
        return estado;
    }

    public double getValorTotal() {
        return valorTotal;
    }
    
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getId(){
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void adicionarItem(ItemPedido item) {
    }

    public void removerItem(ItemPedido item) {
    }
}