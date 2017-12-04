/*
 * Copyright (c) 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software
 * except in compliance with the terms of the license at:
 * http://developer.sun.com/berkeley_license.html
 */

package cart;

import entity.Product;

/**
 *
 * @author juanluis
 */
public class ShoppingCartItem {
    
    Product currentProduct;
    int nElements;
    
    public ShoppingCartItem(Product product){
        this.currentProduct = product;
        int nElements = 1;
    }
    
    
    public Product getProduct(){
        return this.currentProduct;
    }
    
    
    public int getQuantity(){
        return this.nElements;
    }
    
    
    public void setQuantity(int quantity){
        this.nElements = quantity;
    }
    
    
    public double getTotal(){
        double result = this.nElements * this.currentProduct.getPrice();
        return  result;
              
    }
}