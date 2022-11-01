package java.business;

import java.dao.ItemDAO;
import java.dao.utils.ConnectionFactory;
import java.domain.Item;
import java.util.List;

public class ItemFacade {

    public Item buscar(Item item) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            ItemDAO dao = new ItemDAO(factory.getConnection());

            item = dao.buscar(item);
            if (item == null) {
                throw new RuntimeException();
            }

            return item;
        }
    }


    public List<Item> buscarTodos() {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            ItemDAO dao = new ItemDAO(factory.getConnection());
            List<Item> items = dao.buscarTodos();
            return items;
        }
    }

    public void inserir(Item item) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            ItemDAO dao = new ItemDAO(factory.getConnection());
            dao.inserir(item);
        }
    }

    public void editar(Item item) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            ItemDAO dao = new ItemDAO(factory.getConnection());
            dao.editar(item);
        }
    }

    public void remover(Item item) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            ItemDAO dao = new ItemDAO(factory.getConnection());

            item = dao.buscar(item);
            if (item == null) {
                throw new RuntimeException();
            }
            dao.remover(item);
        }
    }
}
