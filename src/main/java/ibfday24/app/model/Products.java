package ibfday24.app.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Products {
    // INSERT INTO fruits_products (name, standard_price, discount) VALUES

    private int id;
    private String name;
    private double stdPrice;
    private double discount;
    /*  
        added later as wasn't thought of initially, if not mapped
        initially to products, have to be mapped separately
    */ 
    private int qty;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    
    public double getStdPrice() {return stdPrice;}
    public void setStdPrice(double stdPrice) {this.stdPrice = stdPrice;}
    
    public double getDiscount() {return discount;}
    public void setDiscount(double discount) {this.discount = discount;}
    
    public int getQty() {return qty;}
    public void setQty(int qty) {this.qty = qty;}
    
    public Products() {
    }
    
    public Products(int id, String name, double stdPrice, double discount, int qty) {
        this.id = id;
        this.name = name;
        this.stdPrice = stdPrice;
        this.discount = discount;
        this.qty = qty;
    }

    // taken pricing and so on into here
    public static Products createFromSQLRowset(SqlRowSet rs){
        Products p = new Products();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setStdPrice(rs.getDouble("standard_price"));
        p.setDiscount(rs.getDouble("discount"));
        
        return p;
    }
}
