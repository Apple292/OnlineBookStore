/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Aiden Wood
 */
public class CheckoutDispatcher implements IDispatcher  {
    
   /**
   * @author Aiden Wood
   * Set session variables and load page
   * @return string of the page to display to the user
   */
     public String execute(HttpServletRequest request) {
       return "/jsp/checkout.jsp";
     }
}
