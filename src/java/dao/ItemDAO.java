package java.dao;

import java.domain.Item;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private static final String QUERY_BUSCAR = "SELECT descricao, quantidade, valor, id_pedido FROM item WHERE id = ?;";
    private static final String QUERY_BUSCAR_TODOS = "SELECT id, descricao, quantidade, valor, id_pedido FROM item ORDER BY id ASC;";
    private static final String QUERY_BUSCAR_POR_PEDIDO = "SELECT id, descricao, quantidade, valor, id_pedido FROM item WHERE id_pedido = ? ORDER BY id ASC;";
    private static final String QUERY_INSERIR = "INSERT INTO item(descricao, quantidade, valor, id_pedido) VALUES (?, ?, ?, ?, ?);";
    private static final String QUERY_REMOVER = "DELETE FROM item WHERE id = ?;";
    private static final String QUERY_EDITAR = "UPDATE item SET descricao = ?, quantidade = ?, valor = ?, id_pedido = ? WHERE id = ?;";

    private Connection con = null;

    public ItemDAO(Connection con) {
        if (con == null) {
            throw new RuntimeException("Conexão nula ao criar ItemDAO.");
        }
        this.con = con;
    }

    public Item buscar(Item item) {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setInt(1, item.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                item.setDescricao(rs.getString("client_name"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setValor(rs.getDouble("valor"));

                return item;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro buscando item: " + item.getId(), e);
        }
    }

    public List<Item> buscarTodos() {
        List<Item> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_POR_PEDIDO)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Item item = new Item();

                item.setDescricao(rs.getString("client_name"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setValor(rs.getDouble("valor"));

                lista.add(item);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro buscando todos os pedidos: " + QUERY_BUSCAR_POR_PEDIDO, e);
        }
    }

    public List<Item> buscarPorPedido() {
        List<Item> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Item item = new Item();

                item.setDescricao(rs.getString("client_name"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setValor(rs.getDouble("valor"));

                lista.add(item);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro buscando todos os pedidos: " + QUERY_BUSCAR_TODOS, e);
        }
    }

    public void inserir(Item item) {  // client_name, tipo, cnpj_cpf, data_compra, valor_total
        try (PreparedStatement st = con.prepareStatement(QUERY_INSERIR)) {
            st.setString(1, item.getDescricao());
            st.setInt(2, item.getQuantidade());
            st.setDouble(3, item.getValor());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar item: "
                    + QUERY_INSERIR, e);
        }
    }

    public void editar(Item item) {
        try (PreparedStatement st = con.prepareStatement(QUERY_EDITAR)) {
            st.setString(1, item.getDescricao());
            st.setInt(2, item.getQuantidade());
            st.setDouble(3, item.getValor());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao editar item: "
                    + QUERY_EDITAR, e);
        }
    }

    public void remover(Item item) {
        try (PreparedStatement st = con.prepareStatement(QUERY_REMOVER)) {
            st.setInt(1, item.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar item: "
                    + QUERY_REMOVER, e);
        }
    }
}
