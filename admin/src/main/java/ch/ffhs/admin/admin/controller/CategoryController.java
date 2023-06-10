package ch.ffhs.admin.admin.controller;

import ch.ffhs.library.library.model.Category;
import ch.ffhs.library.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    // Principal = current logged in user
    public String categories(Model model, Principal principal){
        // if there isn't a user currently logged in, you will be redirected to the login page
        if(principal == null){
            return "redirect:/login";
        }
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categories", categoryList);
        model.addAttribute("size", categoryList.size());
        //will be show in browser tab (<title> tag)
        model.addAttribute("title", "Category");
        model.addAttribute("categoryNew", new Category());
        return "categories";
    }

    @PostMapping("/add-category")
    public String add(@ModelAttribute("cateogoryNew") Category category, RedirectAttributes attributes){
        try{
            categoryService.save(category);
            attributes.addFlashAttribute("success", "Added successfully");
        }catch(DataIntegrityViolationException e){
            attributes.addFlashAttribute("failed", "Failed to add because its a duplicate");
        }
        catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Server error");
        }
        return "redirect:/categories";

    }

    @RequestMapping(value = "/findById", method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseBody
    public Category findById(Long id){
        // will be returned as a String object, attached as payload in the HTTP body as JSON
        return categoryService.findById(id);
    }

    @GetMapping("/updated-category")
    public String update(Category category, RedirectAttributes attributes){
        try{
            categoryService.update(category);
            attributes.addFlashAttribute("success", "Updated successfully");
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to update, because there will be a duplicate");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Server error");
        }
        return "redirected:/categories";
    }

    @RequestMapping(value = "/deleted-category", method = {RequestMethod.PUT, RequestMethod.GET})
    public String delete(Long id, RedirectAttributes attributes){
        try {
            categoryService.deletedById(id);
            attributes.addFlashAttribute("success", "Deleted successfully");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to delete");
        }
        return "redirected:/categories";
    }

    @RequestMapping(value = "/enable-category", method = {RequestMethod.PUT, RequestMethod.GET})
    public String enable(Long id, RedirectAttributes attributes){
        try {
            categoryService.enabledById(id);
            attributes.addFlashAttribute("success", "Enabled successfully");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to enable");
        }
        return "redirected:/categories";
    }



}
