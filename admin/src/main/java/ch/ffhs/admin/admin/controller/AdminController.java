package ch.ffhs.admin.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * AdminController, which is responsible for processing requests related to categories
 */
@Controller
public class AdminController {
    /**
     * method is called when an HTTP GET request is sent to the /categories URL
     *
     * @param model represents the data which is used to pass data to the view
     * @return String with view's name "categories.html"
     */
    @GetMapping("/categories1")
    public String categories(Model model){
        //will be show in browser tab (<title> tag)
        model.addAttribute("title", "Category");
        return "categories";
    }
}
