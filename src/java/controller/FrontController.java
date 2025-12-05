package controller;

import dispatchers.AddCartDispatcher;
import dispatchers.BookDispatcher;
import dispatchers.ContinueDispatcher;
import dispatchers.CheckoutDispatcher;
import dispatchers.IDispatcher;
import dispatchers.UpdateCartDispatcher;
import dispatchers.ViewCartDispatcher;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.annotation.WebServlet;
import model.Book;
import model.CartItem;

/**
 * FrontController class to handle HTTP requests and responses.
 */
@WebServlet(name = "FrontController", urlPatterns = {"/controller"})
public class FrontController extends HttpServlet {
    @PersistenceContext(unitName = "BookShopPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    private final HashMap actions = new HashMap();


    /**
     * Initialize global variables.
     * @param config ServletConfig object
     * @throws ServletException if an error occurs during initialization
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        actions.put(null, new BookDispatcher());
        actions.put("add_to_cart", new AddCartDispatcher());
        actions.put("continue", new ContinueDispatcher());
        actions.put("update_cart", new UpdateCartDispatcher());
        actions.put("view_cart", new ViewCartDispatcher());
        actions.put("checkout",  new CheckoutDispatcher());
    }

    /**
     * Process the HTTP GET request.
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("doGet()");
        // Forward GET requests to doPost method
        doPost(request, response);
    }

    /**
     * Process the HTTP POST request.
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Get the requested action from the request parameters
        String requestedAction = request.getParameter("action");
        HttpSession session = request.getSession();
        String nextPage = "";
        IDispatcher dispatcher = (IDispatcher) actions.get(requestedAction);
        nextPage = dispatcher.execute(request);
        this.dispatch(request, response, nextPage);
    }

    /**
     * Retrieve a book from the list of books stored in the session.
     * @param isbn ISBN of the book
     * @param session HttpSession object
     * @return Book object
     */
    private Book getBookFromList(String isbn, HttpSession session) {
        List<Book> list = (List<Book>) session.getAttribute("books");
        Book aBook = null;
        for (Book book : list) {
            if (isbn.equals(book.getIsbn())) {
                aBook = book;
                break;
            }
        }
        return aBook;
    }

    /**
     * Forward the request to the specified page.
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @param page Page to forward to
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    /**
     * Get Servlet information.
     * @return String servlet info
     */
    public String getServletInfo() {
        return "controller.FrontController Information";
    }

     /**
     * Persist module for database persistence
     */
    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

}

