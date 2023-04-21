package ibfday24.app.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibfday24.app.model.Products;

import static ibfday24.app.repository.DBQueries.*;

@Repository
public class OrderRepository {
   
    @Autowired
    JdbcTemplate template;

    /* 
    sets a list of products with each object having all the 
    information in the product list. individually does not 
    have this info, therefore need to map to them again later
    */
    public List<Products> getAllProducts(){
        List<Products> productList = new LinkedList<>();

        SqlRowSet rs = template.queryForRowSet(GET_ALL_PRODUCTS);
        while(rs.next())
            productList.add(Products.createFromSQLRowset(rs));

        return productList;
    }
    
    /*  Map product to product pricing heree
        createfromSQL does not work the same as it does not tie 
        a specific price to the specific object, but instead reads
        row by row only.
    */
    public Products populateProductPriceIntoForm(List<Products> 
                            productList, Products product){
        /* 
        The loop iterates over each Product object in the productDB
        list and checks if its name matches the name of the input
        Product object using the equalsIgnoreCase method.
        */
        for (Products p : productList)
            if(p.getName().equalsIgnoreCase(product.getName())){
            p.setStdPrice(product.getStdPrice());
            p.setDiscount(product.getDiscount());
        }
            return product;
        }
    
    // to 
}
