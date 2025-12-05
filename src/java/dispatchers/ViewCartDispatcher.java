/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.CartItem;

/**
 * Dispatcher for viewing items in the cart
 * 
 * @author Aiden Wood
 */
public class ViewCartDispatcher implements IDispatcher  {
       /**
   * @author Aiden Wood
   * Set session variables and load page
   * @return string of the page to display to the user
   */
         public String execute(HttpServletRequest request) {
         HttpSession session = request.getSession(true);
            
            Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");
            if (cart == null) {
                return "/jsp/titles.jsp";
            }else{
            return "/jsp/cart.jsp";
}
         }
}
