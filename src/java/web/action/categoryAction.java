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
/**
 *
 * @author nacho
 */
public class categoryAction extends Action{
    
    CategoryModel categoryModel;
    ProductModel productModel;
    
    public categoryAction(CategoryModel category, ProductModel product ){
        this.categoryModel = category;
        this.productModel = product;
    }
    
    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        List<Category> current_categories = categoryModel.retrieveAll();
        req.setAttribute("categories", current_categories);
        String clicked_category = (String) req.getParameter("categoryid");
        req.setAttribute("categoryid", clicked_category);
        ViewManager.nextView(req, resp, "/view/category.jsp");
    }
}
