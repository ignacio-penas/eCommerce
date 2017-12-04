/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import javax.servlet.http.*;
import model.CategoryModel;
import model.ProductModel;
import java.util.List;
import web.ViewManager;
import entity.Category;
import cart.ShoppingCart;
import entity.Product;
/**
 *
 * @author nacho
 */
public class updatecartAction extends Action{
    
    ProductModel productModel;
    ShoppingCart shoppingCart;
    
    public updatecartAction(ProductModel productModel){
        System.out.print("Object class creater updatecart");
        this.productModel = productModel;
    }

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        //Selected item identifiers
        int productId = Integer.parseInt((String)req.getParameter("productid"));
       
        //Products list handler
        Product product = productModel.retrieve(productId);
        req.setAttribute("products", product);
        
        //Category List handler
        String clicked_category = (String) req.getParameter("categoryid");
        req.setAttribute("categoryid", clicked_category);
        //Current category handler   
        //Shopping cart handler
        shoppingCart = (ShoppingCart)req.getSession().getAttribute("cart");
        if (shoppingCart == null){
            shoppingCart = new ShoppingCart();
            req.getSession().setAttribute("cart", shoppingCart);
        } 
        shoppingCart.update(product, (String)req.getParameter("productAmount"));
        System.out.print("Casi al final de neworder");
        ViewManager.nextView(req, resp, "/view/viewCart.jsp");
    }
}
