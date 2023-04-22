package ibfday24.app.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibfday24.app.model.Products;
import ibfday24.app.service.OrderService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class OrderController {
    
    @Autowired
    OrderService ordSvc;

    @GetMapping
    public String populateIndexPage(Model model, HttpSession sess){
        // clear data before opening starting page
        sess.invalidate();

        // called createfromsqlrowset method which mapped price to each individual product
        List<Products> pdtList = ordSvc.getAllProducts();

        // display options in html
        model.addAttribute("productList", pdtList);
        model.addAttribute("products", new Products());

        return "index";
    }

    @PostMapping("/order")
    public String addOrderToTable(Model model, HttpSession sess, @ModelAttribute Products products){
        // instantiate session and link to html, but cart might have no data associated, therefore need to check
        List<Products> cart = (List<Products>) sess.getAttribute("cart");

        if(cart==null){
            cart = new ArrayList<>();
            // parse in new order into the list if not listed before
            sess.setAttribute("cart", cart);
        }

        Products pdt = ordSvc.populateProductPriceIntoForm(ordSvc.getAllProducts(), products);
 
        cart = ordSvc.addProductToCart(cart, products);
        
        /* for shopping cart, logic should always be to use an unique identifier to check for the 
         * presence of an existing object, and to increment, otherwise to add new.
        */
        
        // takes in input from the html into the controller
        model.addAttribute("products", new Products());
        // populates field of the products
        model.addAttribute("productList", ordSvc.getAllProducts());
        // show existing in cart and display in html format
        model.addAttribute("cart", cart);

        return "index";
    }

    
}

