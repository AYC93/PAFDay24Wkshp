package ibfday24.app.model;

import java.time.LocalDate;

import jakarta.servlet.http.HttpServletRequest;

public class Order {
    private int orderId;
    private LocalDate orderDate;
    private String customerName;
    private String shipAddress;
    private String notes;
    private double tax;
    
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public LocalDate getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getShipAddress() {
        return shipAddress;
    }
    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public double getTax() {
        return tax;
    }
    public void setTax(double tax) {
        this.tax = tax;
    }
    public Order() {
    }
    
    public Order(int orderId, LocalDate orderDate, String customerName, String shipAddress, String notes, double tax) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.shipAddress = shipAddress;
        this.notes = notes;
        this.tax = tax;
    }
    
    // input customer name, shipping address and notes : customer_name, ship_address, notes
    public Order inputFromHTTP(HttpServletRequest req){
        Order ord = new Order();
        ord.setCustomerName(req.getParameter("customerName"));
        ord.setShipAddress(req.getParameter("shipAddress"));
        ord.setNotes(req.getParameter("notes"));
        return ord;
    }
    
    }

