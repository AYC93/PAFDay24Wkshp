package ibfday24.app.model;

public class OrderDetails {
    private int id;
    private String product;
    private double unit_price;
    private double discount;
    private int quantity;
    
    public OrderDetails() {
    }

    public OrderDetails(int id, String product, double unit_price, double discount, int quantity) {
        this.id = id;
        this.product = product;
        this.unit_price = unit_price;
        this.discount = discount;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public double getUnit_price() {
        return unit_price;
    }
    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
}
