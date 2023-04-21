package ibfday24.app.repository;

// can i parse calculated value from mysql into html

public class DBQueries {
    // no order_id inside
    public static final String GET_ALL_PRODUCTS =  """
                                                    select * from products;
                                                    
                                                    """;

    public static final String INSERT_NEW_ORDER = """
                                                    insert into orders
                                                    (order_date, customer_name, ship_address, notes, tax)
                                                    VALUES
                                                    (?, ?, ?, ?)
                                                """;

    public static final String INSERT_NEW_ORDER_DETAILS = """
                                                        INSERT INTO order_details 
                                                        (product, unit_price, discount, quantity, order_id) 
                                                        VALUES
                                                        (?, ?, ?, ?, ?)
                                                        """;

                                                        
    // select o.id as order_id, DATE_FORMAT(o.order_date, "%d/%m/%Y") as order_date, o.customer_id,
    //                                         sum(od.quantity * od.unit_price) as total_price,
    //                                         sum(od.quantity * od.unit_price * od.discount) as discount,
    //                                         sum(od.quantity * od.unit_price) - sum(od.quantity * od.unit_price * od.discount) as discounted_price,
    //                                         sum(od.quantity * p.standard_cost) as cost_price
    //                                         from Orders o
    //                                         left join Order_details od on o.id = od.order_id
    //                                         left join products p on od.product_id = p.id 
    //                                         where o.id = ?
    //                                         """;
}
/* Create database with 2 tables, orders and order_details. Orders one to many relationship to 
    order_details ie one order can be 
    linked to many order_details
    POST /order x-www-form-urlencoded
    endpoint to insert new order into orders table
    return error msg
    new order to insert one or more record into orders and order_details table
 */