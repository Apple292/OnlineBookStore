/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Book;
import model.CartItem;
import tafe.java.web.TableFormatter;

/**
 * Dispatcher for adding items to the cart
 *
 * @author Aiden Wood
 */
public class AddCartDispatcher implements IDispatcher {
   /**
   * Set session variables and load page
   * @author Aiden Wood
   * @return string of the page to display to the user
   */
    public String execute(HttpServletRequest request) {

        
         HttpSession session = request.getSession(true);

            
            Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");
            String[] selectedBooks = request.getParameterValues("add");

            // If the cart is null, create a new cart and add selected books
            if (cart == null) {
                cart = new HashMap();

                for (String isbn : selectedBooks) {
                    int quantity = Integer.parseInt(request.getParameter(isbn));
                    Book book = this.getBookFromList(isbn, session);
                    CartItem item = new CartItem(book);
                    item.setQuantity(quantity);
                    cart.put(isbn, item);
                }
                session.setAttribute("cart", cart);
            } else {
                // If the cart already exists, update the quantities of selected books
                for (String isbn : selectedBooks) {
                    int quantity = Integer.parseInt(request.getParameter(isbn));
                    if (cart.containsKey(isbn)) {
                        CartItem item = cart.get(isbn);
                        item.setQuantity(quantity);
                    } else {
                        Book book = this.getBookFromList(isbn, session);
                        CartItem item = new CartItem(book);
                        item.setQuantity(quantity);
                        cart.put(isbn, item);
                    }
                }
            }
        return "/jsp/titles.jsp";

    }
    
     /**
     * Retrieve a book from the list of books stored in the session.
     * @param isbn ISBN of the book
     * @param session HttpSession object
     * @return Book object
     */
    private Book getBookFromList(String isbn, HttpSession session) {
        List<Book> list = (List<Book>) session.getAttribute("books");
        System.out.println(list);
        Book aBook = null;
        for (Book book : list) {
            if (isbn.equals(book.getIsbn())) {
                aBook = book;
                break;
            }
        }
        return aBook;
    }
}
