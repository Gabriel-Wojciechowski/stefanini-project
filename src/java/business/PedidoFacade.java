package java.business;

import java.dao.PedidoDAO;
import java.dao.utils.ConnectionFactory;
import java.domain.Pedido;
import java.util.List;

public class PedidoFacade {

    public Pedido buscar(Pedido pedido) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            PedidoDAO dao = new PedidoDAO(factory.getConnection());

            pedido = dao.buscar(pedido);
            if (pedido == null) {
                throw new RuntimeException();
            }

            return pedido;
        }
    }

    public List<Pedido> buscarTodos() {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            PedidoDAO dao = new PedidoDAO(factory.getConnection());
            List<Pedido> pedidos = dao.buscarTodos();
            return pedidos;
        }
    }

    public void inserir(Pedido pedido) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            PedidoDAO dao = new PedidoDAO(factory.getConnection());
            dao.inserir(pedido);
        }
    }

    public void editar(Pedido pedido) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            PedidoDAO dao = new PedidoDAO(factory.getConnection());
            dao.editar(pedido);
        }
    }

    public void remover(Pedido pedido) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            PedidoDAO dao = new PedidoDAO(factory.getConnection());

            pedido = dao.buscar(pedido);
            if (pedido == null) {
                throw new RuntimeException();
            }
            dao.remover(pedido);
        }
    }
}
