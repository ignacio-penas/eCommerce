/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import cart.ShoppingCart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.ViewManager;

/**
 *
 * @author nacho
 */
public class clearcartAction extends Action{
    
    ShoppingCart shoppingCart;
    
    public void perform(HttpServletRequest req, HttpServletResponse resp){
        shoppingCart = (ShoppingCart)req.getSession().getAttribute("cart");
        this.shoppingCart.clear();
        req.getSession().setAttribute("cart", shoppingCart);
        ViewManager.nextView(req, resp, "/view/viewCart.jsp");
    }
    
}
