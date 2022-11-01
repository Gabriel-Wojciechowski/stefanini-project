package java.domain;

import java.sql.Date;
import java.time.LocalDate;

public class Pedido {

    int id;
    String clientName;
    String tipo;
    String cnpjOuCpf;
    LocalDate dataCompra;
    double valorTotal;

    public Pedido() {
    }

    public Pedido(int id, String clientName, String tipo, String cnpjOuCpf, LocalDate dataCompra, double valorTotal) {
        this.id = id;
        this.clientName = clientName;
        this.tipo = tipo;
        this.cnpjOuCpf = cnpjOuCpf;
        this.dataCompra = dataCompra;
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCnpjOuCpf() {
        return cnpjOuCpf;
    }

    public void setCnpjOuCpf(String cnpjOuCpf) {
        this.cnpjOuCpf = cnpjOuCpf;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
