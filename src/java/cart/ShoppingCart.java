package cart;

import entity.Product;
import java.text.NumberFormat;
import java.util.*;

/**
 *
 * @author juanluis
 */
public class ShoppingCart {
    //Global vatiable for the cart
    int nItems;
    //Set of current items of the cart
    List<ShoppingCartItem> cartList;
    
    public ShoppingCart(){
        //Only variables initialization by now
        int nItems = 0;
        cartList = new ArrayList();
    }
    public synchronized void addItem(Product product){
       //Seek if already added, if yes increment number, else add. 
       if(product == null){
           return;
       }
       for(ShoppingCartItem thisItem : this.cartList){
           if (thisItem.getProduct().getId() == product.getId()){
               thisItem.setQuantity(thisItem.getQuantity() + 1);
               this.nItems ++;
               return;
           }
       }
       ShoppingCartItem temp = new ShoppingCartItem(product);
       temp.setQuantity(1);
       this.cartList.add(temp);
       
       this.nItems ++;
    }
    
    
    public synchronized void update(Product product, String quantity){
        int actualQuantity = Integer.parseInt(quantity);
        
        System.out.print("askdhfl");
        for(ShoppingCartItem thisItem : this.cartList){
           if (thisItem.getProduct().getId() == product.getId()){
               int prevQ = thisItem.getQuantity();
               thisItem.setQuantity(actualQuantity);
               this.nItems = this.nItems + (actualQuantity-prevQ);
               if(thisItem.getQuantity() == 0){
                   this.cartList.remove(thisItem);
               }
               return;
           }
       }
    }
    
    
    public synchronized List<ShoppingCartItem> getItems(){
        return this.cartList;
    }
    
    
    public synchronized int getNumberOfItems(){
        return this.nItems;
    }
    
    
    public synchronized double getTotal(){
        double currentQuantity = 0;
        for(ShoppingCartItem thisItem : this.cartList){
            currentQuantity = currentQuantity + thisItem.getTotal();
        }
        return currentQuantity;
    }
    
    
    public synchronized void clear(){
        this.cartList = new ArrayList();
        this.nItems = 0;
    }
}