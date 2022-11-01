package java.dao;

import java.domain.Pedido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    private static final String QUERY_BUSCAR = "SELECT client_name, tipo, cnpj_cpf, data_compra, valor_total FROM pedido WHERE id = ?;";
    private static final String QUERY_BUSCAR_TODOS = "SELECT id, client_name, tipo, cnpj_cpf, data_compra, valor_total FROM pedido ORDER BY id ASC;";
    private static final String QUERY_INSERIR = "INSERT INTO pedido(client_name, tipo, cnpj_cpf, data_compra, valor_total) VALUES (?, ?, ?, ?, ?);";
    private static final String QUERY_REMOVER = "DELETE FROM pedido WHERE id = ?;";
    private static final String QUERY_EDITAR = "UPDATE pedido SET client_name = ?, tipo = ?, cnpj_cpf = ?, data_compra = ?, valor_total = ? WHERE id = ?;";

    private Connection con = null;

    public PedidoDAO(Connection con) {
        if (con == null) {
            throw new RuntimeException("Conexão nula ao criar PedidoDAO.");
        }
        this.con = con;
    }

    public Pedido buscar(Pedido pedido) {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setInt(1, pedido.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                pedido.setClientName(rs.getString("client_name"));
                pedido.setTipo(rs.getString("tipo"));
                pedido.setCnpjOuCpf(rs.getString("cnpj_cpf"));
                pedido.setValorTotal(rs.getDouble("valor_total"));
                Date date = rs.getDate("data_compra");
                if(date != null) pedido.setDataCompra(date.toLocalDate());

                return pedido;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro buscando pedido: " + pedido.getId(), e);
        }
    }

    public List<Pedido> buscarTodos() {
        List<Pedido> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();

                pedido.setId(rs.getInt("id"));
                pedido.setClientName(rs.getString("client_name"));
                pedido.setTipo(rs.getString("tipo"));
                pedido.setCnpjOuCpf(rs.getString("cnpj_cpf"));
                pedido.setValorTotal(rs.getDouble("valor_total"));
                Date date = rs.getDate("data_compra");
                if(date != null) pedido.setDataCompra(date.toLocalDate());

                lista.add(pedido);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro buscando todos os pedidos: "
                    + QUERY_BUSCAR_TODOS, e);
        }
    }

    public void inserir(Pedido pedido) {  // client_name, tipo, cnpj_cpf, data_compra, valor_total
        try (PreparedStatement st = con.prepareStatement(QUERY_INSERIR)) {
            st.setString(1, pedido.getClientName());
            st.setString(2, pedido.getTipo());
            st.setString(3, pedido.getCnpjOuCpf());
            st.setDate(4, Date.valueOf(pedido.getDataCompra()));
            st.setDouble(5, pedido.getValorTotal());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar pedido: "
                    + QUERY_INSERIR, e);
        }
    }

    public void editar(Pedido pedido) {
        try (PreparedStatement st = con.prepareStatement(QUERY_EDITAR)) {
            st.setString(1, pedido.getClientName());
            st.setString(2, pedido.getTipo());
            st.setString(3, pedido.getCnpjOuCpf());
            st.setDate(4, Date.valueOf(pedido.getDataCompra()));
            st.setDouble(5, pedido.getValorTotal());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao editar pedido: "
                    + QUERY_EDITAR, e);
        }
    }

    public void remover(Pedido pedido) {
        try (PreparedStatement st = con.prepareStatement(QUERY_REMOVER)) {
            st.setInt(1, pedido.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar pedido: "
                    + QUERY_REMOVER, e);
        }
    }

}
