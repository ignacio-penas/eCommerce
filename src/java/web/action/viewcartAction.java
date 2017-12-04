/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import javax.servlet.http.*;
import model.CategoryModel;
import model.ProductModel;
import web.ViewManager;
import cart.ShoppingCart;

/**
 *
 * @author nacho
 */
public class viewcartAction extends Action{
    
    CategoryModel categoryModel;
    ProductModel productModel;
    ShoppingCart shoppingCart;
    
    public viewcartAction(){
        System.out.print("ViewCartAction initializad");
    }
    
    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        ViewManager.nextView(req, resp, "/view/viewCart.jsp");
    }
    
}
