package ibfday24.app.repository;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibfday24.app.model.Order;
import ibfday24.app.model.OrderDetails;
import ibfday24.app.model.Products;

import static ibfday24.app.repository.DBQueries.*;

@Repository
public class OrderRepository {
   
    @Autowired
    JdbcTemplate template;

    KeyHolder keyHolder = new GeneratedKeyHolder();
    
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
    public Products populateProductPriceIntoForm(List<Products> productList, Products product){
        /* 
        The loop iterates over each Product object in the productDB
        list and checks if its name matches the name of the input, to map price and discount only
        Product object using the equalsIgnoreCase method.
        */
        // make sure that the correct item is listed here, otherwise it might return 0 value
        for (Products p : productList)
            if(product.getName().equalsIgnoreCase(p.getName())){
            product.setStdPrice(p.getStdPrice());
            product.setDiscount(p.getDiscount());
            System.out.println("std price = " + product.getStdPrice());
        }
            return product;
        }

    // date added using mysql, customer_name, ship_address, notes, tax, the intValue to be returned in the insertIntoOrderDetails for orderId
    public int addOrder(Order order){
            template.update(conn-> {
                PreparedStatement statement = conn.prepareStatement(INSERT_NEW_ORDER, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, order.getCustomerName());
                statement.setString(2, order.getCustomerName());
                statement.setString(3, order.getNotes());
                return statement;
            }, keyHolder);
    
            BigInteger primaryKey = (BigInteger) keyHolder.getKey();
            
            return primaryKey.intValue();
        }

    // BATCH UPDATE EXAMPLE to insert values into order_details, primarykey to be populated by the streaming of values
    public void insertIntoOrderDetails(List<OrderDetails> orderDetailsList, int orderId){
        List<Object[]> data = orderDetailsList.stream()
                    .map(odl ->{
                        Object[] o = new Object[5]; // define number of params in obj
                        // (product, unit_price, discount, quantity, order_id) 
                        o[0] = odl.getProduct();
                        o[1] = odl.getUnit_price();
                        o[2] = odl.getDiscount();
                        o[3] = odl.getQuantity();
                        o[4] = orderId;
                        return o;
                    }).toList();

        template.batchUpdate(INSERT_NEW_ORDER_DETAILS, data);
    }

}
