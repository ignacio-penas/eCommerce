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
import java.util.Collection;
import java.util.ArrayList;
        

/**
 *
 * @author nacho
 */
public class neworderAction extends Action{

    CategoryModel categoryModel;
    ProductModel productModel;
    ShoppingCart shoppingCart;

    public neworderAction(CategoryModel categoryModel, ProductModel productModel) {
        this.categoryModel = categoryModel;
        this.productModel = productModel;
    }

    public void perform(HttpServletRequest req, HttpServletResponse resp) {        
        System.out.print("Principio de neworder");
        //Selected item identifiers
        int productId = new Integer((String)req.getParameter("productid"));
        int categoryId = new Integer((String)req.getParameter("categoryid"));
        
        //Products list handler
        Product product = productModel.retrieve(productId);
        req.setAttribute("products", product);
        
        //Category List handler
        List<Category> current_categories = categoryModel.retrieveAll();
        req.setAttribute("categories", current_categories);
        String clicked_category = (String) req.getParameter("categoryid");
        req.setAttribute("categoryid", clicked_category);
        //Current category handler   
        //Shopping cart handler
        shoppingCart = (ShoppingCart)req.getSession().getAttribute("cart");
        if (shoppingCart == null){
            shoppingCart = new ShoppingCart();
            req.getSession().setAttribute("cart", shoppingCart);
        } 
        shoppingCart.addItem(product);
        System.out.print("Casi al final de neworder");
        ViewManager.nextView(req, resp, "/view/category.jsp");
    }
}
