/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import java.sql.ResultSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Book;
import tafe.java.web.TableFormatter;

/**
 * Dispatcher that goes back to the BookPage
 * 
 * @author Aiden Wood
 */
public class ContinueDispatcher implements IDispatcher {
       /**
   * @author Aiden Wood
   * Set session variables and load page
   * @return string of the page to display to the user
   */
    public String execute(HttpServletRequest request) {
        return "/jsp/titles.jsp";
    }
    
}
