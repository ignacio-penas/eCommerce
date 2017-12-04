/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import entity.Product;

/**
 *
 * @author juanluis
 */
public class ProductModel {

    UserTransaction utx;
    EntityManager em;

    public ProductModel(EntityManager em, UserTransaction utx) {
        this.utx = utx;
        this.em = em;
    }
    
    public Product retrieve(int product_id) {
        return em.find (Product.class, product_id);
    }

}
