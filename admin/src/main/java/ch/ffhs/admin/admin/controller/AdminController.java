package ch.ffhs.admin.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/categories")
    public String categories(Model model){
        //will be show in browser tab (<title> tag)
        model.addAttribute("title", "Category");
        return "categories";
    }
}
