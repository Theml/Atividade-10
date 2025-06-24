import java.util.Observable;
import java.util.Observer;

public class Cliente implements Observer {
    private final String nome;
    private final String endereco;    
    
    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNome() {
        return nome;
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