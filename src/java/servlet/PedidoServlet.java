package java.servlet;

import java.business.PedidoFacade;
import java.domain.Pedido;
import java.io.IOException;
import java.time.LocalDate;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PedidoServlet", urlPatterns = {"/PedidoServlet"})
public class PedidoServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd;

        String action = request.getParameter("action");
        if(action == null) action = "";

        PedidoFacade pedidoFacade = new PedidoFacade();
        Pedido pedido = new Pedido();
        switch(action) {
            case "formNew":
                rd = getServletContext().getRequestDispatcher("/manterPedido.jsp?action=formNew");
                rd.forward(request, response);

                break;
            case "new":
                pedido.setClientName(request.getParameter("clientName"));
                pedido.setTipo(request.getParameter("tipo"));
                pedido.setCnpjOuCpf(request.getParameter("cpfOuCnpj"));
                pedido.setDataCompra(LocalDate.parse(request.getParameter("dataCompra")));
                pedido.setValorTotal(request.getParameter("valorTotal"));

                pedidoFacade.inserir(pedido);

                request.setAttribute("pedidos", pedidoFacade.buscarTodos());

                rd = getServletContext().getRequestDispatcher("/listarPedidos.jsp");
                rd.forward(request, response);

                break;
            case "formUpdate":
                pedido.setId(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("pedido", pedidoFacade.buscar(pedido));

                rd = getServletContext().getRequestDispatcher("/manterPedido.jsp?action=formUpdate");
                rd.forward(request, response);

                break;
            case "update":
                pedido.setId(Integer.parseInt(request.getParameter("id")));
                pedido.setClientName(request.getParameter("clientName"));
                pedido.setTipo(request.getParameter("tipo"));
                pedido.setCnpjOuCpf(request.getParameter("cpfOuCnpj"));
                pedido.setDataCompra(LocalDate.parse(request.getParameter("dataCompra")));
                pedido.setValorTotal(request.getParameter("valorTotal"));
                pedidoFacade.editar(pedido);

                request.setAttribute("pedidos", pedidoFacade.buscarTodos());

                rd = getServletContext().getRequestDispatcher("/listarPedidos.jsp");
                rd.forward(request, response);

                break;

            case "remove":
                pedido.setId(Integer.parseInt(request.getParameter("id")));
                pedidoFacade.remover(pedido);

                //Sem break para que o código default seja executado logo em seguida
            default:
                request.setAttribute("pedidos", pedidoFacade.buscarTodos());

                rd = getServletContext().getRequestDispatcher("/listarPedidos.jsp");
                rd.forward(request, response);

                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
