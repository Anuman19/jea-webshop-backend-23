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

/**
 * CategoryController, which is responsible for processing requests related to categories
 */
@Controller
public class CategoryController {
    // this annotation injects the CategoryService which allows the controller
    // to access the service to execute business logic related to categories
    @Autowired
    private CategoryService categoryService;

    /**
     * method is called when an HTTP GET request is sent to the /categories URL
     *
     * @param model represents the data (list with categories) which is used to pass data to the view
     * @param principal represents the current logged-in user
     * @return String with view's name "categories.html"
     */
    @GetMapping("/categories")
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

    /**
     * method is called when an HTTP POST request is sent to the /add-category URL and will add a new category
     *
     * @param category object to save new category
     * @param attributes flash attributes for forwarding success or error messages
     * @return String with attribute or redirected URL
     */
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
        } // returns to /categories URL
        return "redirect:/categories";

    }

    /**
     * method is called when an HTTP PUT or GET request is sent to the /findById URL
     * and is searching for a category by its ID
     *
     * @param id of the category
     * @return category JSON object
     */
    @RequestMapping(value = "/findById", method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseBody
    public Category findById(Long id){
        // will be returned as a String object, attached as payload in the HTTP body as JSON
        return categoryService.findById(id);
    }

    /**
     * method is called when an HTTP GET request is sent to the /update-category URL
     *
     * @param category object to update
     * @param attributes flash attributes for forwarding success or error messages
     * @return String with attribute or redirected URL
     */
    @GetMapping("/updated-category")
    public String update(Category category, RedirectAttributes attributes){
        try{
            // updates an existing category by using the passed Category object
            categoryService.update(category);
            attributes.addFlashAttribute("success", "Updated successfully");
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to update, because there will be a duplicate");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Server error");
        } // returns to /categories URL
        return "redirected:/categories";
    }

    /**
     * method is called when an HTTP PUT or GET request is sent to the /deleted-category URL
     * and deletes a category by its ID
     *
     * @param id of the category
     * @param attributes flash attributes for forwarding success or error messages
     * @return String with attribute or redirected URL
     */
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

    /**
     * method is called when an HTTP PUT or GET request is sent to the /enable-category URL
     * and activates a category by its ID
     *
     * @param id of the category
     * @param attributes flash attributes for forwarding success or error messages
     * @return String with attribute or redirected URL
     */
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
