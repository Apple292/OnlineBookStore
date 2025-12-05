/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import java.sql.ResultSet;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Book;
import tafe.java.web.TableFormatter;

/**
 * Dispatcher for listing books for BOokShop
 * 
 * @author Aiden Wood
 */
public class BookDispatcher implements IDispatcher {

    @PersistenceContext(unitName = "BookShopPU")
    private EntityManager em;

    private EntityManagerFactory emfac;
    
   /**
   * @author Aiden Wood
   * Set session variables and load page
   * @return string of the page to display to the user
   */
    public String execute(HttpServletRequest request) {

       emfac = Persistence.createEntityManagerFactory("BookShopPU");
       em = emfac.createEntityManager();
       
        String nextPage = "";
        try {
            HttpSession session = request.getSession(true);

            List<Book> books = null;
    
         books = em.createNamedQuery("Book.findAll").getResultList();

            session.setAttribute("books", books);
            return "/jsp/titles.jsp";
        } catch (Exception ex) {
            request.setAttribute("result", ex.getMessage());
            return "/jsp/error.jsp";
        }
    }
}
