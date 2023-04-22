package ibfday24.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Products populateProductPriceIntoForm(List<Products> productList, Products product){
        return ordRepo.populateProductPriceIntoForm(productList, product);
    }

    /* 
    Product inside to see what is the price, price of each individual
    object mapped in orderRepo 
    */ 
    public double unitPrice(Products p){
        p = new Products();
        double costPrice = p.getStdPrice();
        double discount = p.getDiscount();
        double unitPrice = costPrice * discount * (1 + tax);
        return unitPrice;
    }

    public List<OrderDetails> setPriceToOrderDetails(List<Products> prodList){
        // order details map out to each product
        OrderDetails ordDetails = new OrderDetails();
        List<OrderDetails> ordDetailsList = new ArrayList<>();

        /*  OrderDetails(int id, String product, 
            double unit_price, double discount, int quantity) 
            
            set product individually to ordDetails, then add the 
            singular obj to the list   */ 
            /* to check through params in object in list, for loop commonly used */
        for(Products p: prodList){
            ordDetails.setProduct(p.getName());
            ordDetails.setDiscount(p.getDiscount());
            ordDetails.setQuantity(p.getQty());
            ordDetails.setUnit_price(unitPrice(p));

            ordDetailsList.add(ordDetails);
        }

        return ordDetailsList;
    }

    public List<Products> addProductToCart(List<Products> existingCart, Products newPdt){
        for (Products p : existingCart){
            // this for loop tests out the product object present in the cart, and newPdt has to
            // be called to ensure new items added check against the list
            if(p.getName().equalsIgnoreCase(newPdt.getName())){
                int totalQty = p.getQty() + newPdt.getQty();
                p.setQty(totalQty);
                return existingCart;
            } else{
                continue;
            }
        }
        // if no existing cart present, add null
        existingCart.add(newPdt);
        return existingCart;
        }
    }
