// Template Method abstrato
public abstract class ProcessadorPedido {

    // Template Method - define o algoritmo
    public final void processarPedido(Pedido pedido) {
        validarPedido(pedido);
        calcularTempo(pedido);
        prepararItens(pedido);

        if (precisaValidacaoEspecial()) {
            validacaoEspecial(pedido);
        }

        embalar(pedido);
        notificarCliente(pedido);
    }

    // Métodos abstratos - devem ser implementados pelas subclasses
    protected abstract void prepararItens(Pedido pedido);
    protected abstract void embalar(Pedido pedido);
    protected abstract int calcularTempoPreparacao();

    // Hook method - pode ser sobrescrito se necessário
    protected boolean precisaValidacaoEspecial() {
        return false;
    }

    protected void validacaoEspecial(Pedido pedido) {
        // Implementação padrão vazia
    }

    // Métodos concretos - mesma implementação para todos
    private void validarPedido(Pedido pedido) {
        System.out.println("Validando pedido #" + pedido.getId());
        // Validações comuns
    }

    private void calcularTempo(Pedido pedido) {
        int tempo = calcularTempoPreparacao();
        System.out.println("Tempo estimado: " + tempo + " minutos");
    }

    private void notificarCliente(Pedido pedido) {
        System.out.println("Cliente notificado sobre o pedido #" + pedido.getId());
    }
}
