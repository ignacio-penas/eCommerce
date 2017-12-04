<%-- 
    Document   : category
    Created on : 28-nov-2017, 19:13:24
    Author     : nacho
    Description: shows all items of one category
--%>

<%@ page import="cart.ShoppingCart"%>  
<%@ page import="entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collection" %>
<%@ page import="entity.Category" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Expires" CONTENT="0">
        <meta http-equiv="Cache-Control" CONTENT="no-cache">
        <meta http-equiv="Pragma" CONTENT="no-cache">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Category display</title>
    </head>
    <body>
        <%
            String selected_category = (String)request.getAttribute("categoryid");
            List<Category> categories = (List<Category>)request.getAttribute("categories");
            Category current_category = categories.get(Integer.parseInt(selected_category)-1);
            Collection<Product> products_on_cat = current_category.getProductCollection();
            //Shopping cart is special because it should be dinamically modified by end user
            //so we need to retreive the session and the cart from it
            ShoppingCart shoppingCart = (ShoppingCart)request.getSession().getAttribute("cart");
            int items_on_cart = 0;
            if(shoppingCart != null){
                items_on_cart = shoppingCart.getNumberOfItems();
            }

        %>
        <h1>Products of <%=current_category.getName()%>!</h1>
        <img src="img/cart.gif"> <a href="viewcart.do"><%=items_on_cart%> items on cart</a>
        
        
    <table width="100%" border="1" bordercolordark="#000000" bordercolorlight="#FFFFFF" cellpadding="3" cellspacing="0">
        <tr>
            <th>
                
                
                <table width="100%" border="1" bordercolordark="#000000" bordercolorlight="#FFFFFF" cellpadding="3" cellspacing="0">
                    <%for(Category category : categories){%>
                        <tr>
                            <td width="14%" valign="center" align="middle">
                                <a href="category.do?categoryid=<%=category.getId()%>">
                                    <%=category.getName()%>
                                </a>
                            </td>
                        </tr>
                    <% } %>
                </table>
            </th>
            <th>
                <table width="100%" border="1" bordercolordark="#000000" bordercolorlight="#FFFFFF" cellpadding="3" cellspacing="0">
                    <%for(Product product : products_on_cat){%>
                        <tr>
                            <td width="14%" valign="center" align="middle">
                                <img src="img/products/<%=product.getName()%>.png"
                                     >
                            </td>
                            <td width="14%" valign="center" align="middle">
                                <h4><%=product.getName()%></h4>
                                <h4><%=product.getDescription()%></h4>
                            </td>
                            <td width="14%" valign="center" align="middle">
                                <%=product.getPrice()%> €
                            </td>
                            <td width="14%" valign="center" align="middle">
                                <form action="neworder.do" method="post">
                                    <input type="hidden" name="productid" value="<%=product.getId()%>">
                                    <input type="hidden" name="categoryid" value="<%=current_category.getId()%>">
                                    <input value="Add to Cart" type="submit" name="submit">
                                </form>
                            </td>
                        </tr>
                    <% } %>
                </table>
            </th>
        </tr>
    </table>        
    </body>
</html>
