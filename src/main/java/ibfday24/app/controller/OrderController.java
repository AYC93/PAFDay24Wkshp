package ibfday24.app.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibfday24.app.model.Products;
import ibfday24.app.service.OrderService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class OrderController {
    
    @Autowired
    OrderService ordSvc;

    @GetMapping("/order")
    public String populateIndexPage(Model model, HttpSession sess){
        // clear data before opening starting page
        sess.invalidate();

        List<Products> pdtList = ordSvc.getAllProducts();

        // display options in html
        model.addAttribute("productList", pdtList);
        model.addAttribute("products", new Products());

        return "index";
    }

}

