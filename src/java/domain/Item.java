package java.domain;

public class Item {

    int id;
    String descricao;
    int quantidade;
    double valor;
    int idPedido;

    public Item() {
    }

    public Item(int id, String descricao, int quantidade, double valor, int idPedido) {
        this.id = id;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = valor;
        this.idPedido = idPedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
}
