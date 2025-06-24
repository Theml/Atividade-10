import java.util.Observable;
import java.util.Observer;

public class Cliente implements Observer {
    private final String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public void update(Observable o, Object arg) {
        System.out.println(nome + " recebeu notificação: " + arg);
    }

    public void cancelarPedido(Pedido pedido) {
        pedido.setEstado(new EstadoCancelado());
    }

    public void entregarPedido(Pedido pedido) {
        pedido.setEstado(new EstadoEntregue());
    }
}