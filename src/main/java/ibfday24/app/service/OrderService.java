package ibfday24.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibfday24.app.model.Order;
import ibfday24.app.model.OrderDetails;
import ibfday24.app.model.Products;
import ibfday24.app.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    OrderRepository ordRepo;

    private static final double tax = 0.05;

    public List<Products> getAllProducts(){
        return ordRepo.getAllProducts();
    }

    public OrderDetails setPriceToOrderDetails(Products p){
        // order details map out to each product
        OrderDetails ordDetails = new OrderDetails();

        ordDetails.setProduct(p.getName());
        

        return ordDetails;

    }

    public double unitPrice(Products p,OrderDetails orD, double costPrice){
        p = new Products();
        orD = new OrderDetails();
        costPrice = p.getStdPrice();
        double discount = p.getDiscount();
        double unitPrice = costPrice * discount * (1 + tax);
        return unitPrice;
    }


}
