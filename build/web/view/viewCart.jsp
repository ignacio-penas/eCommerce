<%-- 
    Document   : cart
    Created on : 03-dic-2017, 19:41:51
    Author     : Ignacio Penas Fernandez
--%>

<%@ page import="cart.ShoppingCart"%>
<%@ page import="cart.ShoppingCartItem"%>  
<%@ page import="entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collection" %>
<%@ page import="entity.Category" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <script src="https://www.paypalobjects.com/api/checkout.js"></script>
        <title>Cart View</title>
    </head>
    <body>
        <%
            String selected_category = (String)request.getAttribute("categoryid");
            List<Category> categories = (List<Category>)request.getAttribute("categories");
            //Shopping cart is special because it should be dinamically modified by end user
            //so we need to retreive the session and the cart from it 
            ShoppingCart shoppingCart = (ShoppingCart)request.getSession().getAttribute("cart");
            int items_on_cart = 0;
            List<ShoppingCartItem> itemList;
            
            if(shoppingCart != null){
                items_on_cart = shoppingCart.getNumberOfItems();
                itemList = shoppingCart.getItems();
            }
            else
            {
                itemList = new ArrayList<ShoppingCartItem>();
            }
            double totalPrice = 0;
        %>

        <h1>Your shopping cart contains <%=items_on_cart%> items</h1>
        <img src="img/cart.gif"> <a href="viewcart.do"><%=items_on_cart%> items on cart</a>
        <p><a href="clearcart.do">Clear cart</a></p>
        <p><a href="init.do">Continue shopping</a></p>
        <div id="paypal-button"></div>
        <script>
            paypal.Button.render({

                env: 'production', // Or 'sandbox',
                

                commit: true, // Show a 'Pay Now' button

                style: {
                    color: 'gold',
                    size: 'small'
                },

                payment: function(data, actions) {
                                    
                },

                onAuthorize: function(data, actions) {
                    /* 
                     * Execute the payment here 
                     */
                },

                onCancel: function(data, actions) {
                    /* 
                     * Buyer cancelled the payment 
                     */
                },

                onError: function(err) {
                    /* 
                     * An error occurred during the transaction 
                     */
                }

            }, '#paypal-button');
        </script>
        <table width="100%" border="1" bordercolordark="#000000" bordercolorlight="#FFFFFF" cellpadding="3" cellspacing="0">
        <tr>
            <th>
                
                
                <table width="100%" border="1" bordercolordark="#000000" bordercolorlight="#FFFFFF" cellpadding="3" cellspacing="0">
                    <%for(ShoppingCartItem currentItem : itemList){
                    Product product = currentItem.getProduct();
                    totalPrice = totalPrice + currentItem.getQuantity()*product.getPrice();%>
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
                                
                                <form action="updatecart.do" method="post">
                                    <input type="hidden" name="productid" value="<%=product.getId()%>">
                                    <input type="text" name="productAmount" value="<%=currentItem.getQuantity()%>">
                                    <input value="Update" type="submit" name="submit">
                                </form>
                            </td>
                        </tr>
                    <% } %>
                </table>
                <h3>Total amount <%=String.format("%.2f", totalPrice)%>€</h3>
            </th>
        </tr>
        </table>
    </body>
</html>
