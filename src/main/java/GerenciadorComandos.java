import java.util.ArrayList;
import java.util.List;

// Invoker
public class GerenciadorComandos {
    private List<Command> historico = new ArrayList<>();
    private int indiceAtual = -1;

    public void executarComando(Command comando) {
        // Remove comandos após o índice atual se existirem
        if (indiceAtual < historico.size() - 1) {
            historico = historico.subList(0, indiceAtual + 1);
        }

        comando.execute();
        historico.add(comando);
        indiceAtual++;
    }

    public void desfazer() {
        if (indiceAtual >= 0) {
            Command comando = historico.get(indiceAtual);
            comando.undo();
            indiceAtual--;
        }
    }

    public void refazer() {
        if (indiceAtual < historico.size() - 1) {
            indiceAtual++;
            Command comando = historico.get(indiceAtual);
            comando.execute();
        }
    }
}