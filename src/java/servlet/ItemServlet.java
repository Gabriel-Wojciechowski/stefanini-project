package java.servlet;

import java.business.ItemFacade;
import java.domain.Item;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ItemServlet", urlPatterns = {"/ItemServlet"})
public class ItemServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd;

        String action = request.getParameter("action");
        if(action == null) action = "";

        ItemFacade itemFacade = new ItemFacade();
        Item item = new Item();
        switch(action) {
            case "formNew":
                rd = getServletContext().getRequestDispatcher("/manterItem.jsp?action=formNew");
                rd.forward(request, response);

                break;
            case "new":
                item.setDescricao(request.getParameter("descricao"));
                item.setQuantidade(request.getParameter("quantidade"));
                item.setValor(request.getParameter("valor"));

                itemFacade.inserir(item);

                request.setAttribute("itens", itemFacade.buscarTodos());

                rd = getServletContext().getRequestDispatcher("/listarItens.jsp");
                rd.forward(request, response);

                break;
            case "formUpdate":
                item.setId(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("item", itemFacade.buscar(item));

                rd = getServletContext().getRequestDispatcher("/manterItem.jsp?action=formUpdate");
                rd.forward(request, response);

                break;
            case "update":
                item.setId(Integer.parseInt(request.getParameter("id")));
                item.setDescricao(request.getParameter("descricao"));
                item.setQuantidade(request.getParameter("quantidade"));
                item.setValor(request.getParameter("valor"));
                itemFacade.editar(item);

                request.setAttribute("Itens", itemFacade.buscarTodos());

                rd = getServletContext().getRequestDispatcher("/listarItens.jsp");
                rd.forward(request, response);

                break;

            case "remove":
                item.setId(Integer.parseInt(request.getParameter("id")));
                itemFacade.remover(item);

                //Sem break para que o código default seja executado logo em seguida
            default:
                request.setAttribute("Itens", itemFacade.buscarTodos());

                rd = getServletContext().getRequestDispatcher("/listarItens.jsp");
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
