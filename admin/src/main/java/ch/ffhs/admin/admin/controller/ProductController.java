package ch.ffhs.admin.admin.controller;

import ch.ffhs.library.library.dto.ProductDto;
import ch.ffhs.library.library.model.Category;
import ch.ffhs.library.library.service.CategoryService;
import ch.ffhs.library.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

/**
 * ProductController is created to map incoming URL request related to the products
 * and is responsible for displaying the appropriate views
 *
 */
@RestController
public class ProductController {
    // injects service for product management
    @Autowired
    private ProductService productService;

    // injects service for category management
    @Autowired
    private CategoryService categoryService;

    /**
     * method is called when an HTTP GET request is sent to the /products URL
     * and checks whether a user is logged-in or not
     *
     * @param model represents the data which is used to pass data to the view
     * @param principal represents the current logged-in user
     * @return String with view's name "products.html" or redirected URL
     */
    @GetMapping("/products")
    public String products(Model model, Principal principal){
        // if there isn't a user currently logged in, you will be redirected to the login page
        /**if(principal == null){
            return "redirect:/login";
        }**/
        List<ProductDto> productDtoList = productService.findAll();
        //will be show in browser tab (<title> tag)
        model.addAttribute("title", "Manage Product");
        model.addAttribute("products", productDtoList);
        model.addAttribute("size", productDtoList.size());
        return "products";
    }

    /**
     * method is called when an HTTP GET request is sent to the /products/{pageNo} URL
     *
     * @param pageNo current page index
     * @param model represents the data which is used to pass data to the view
     * @param principal represents the current logged-in user
     * @return String with view's name "products.html"
     */
    @GetMapping("/products/{pageNo}")
    public String productsPage(@PathVariable("pageNo") int pageNo, Model model, Principal principal){
        /**if(principal == null){
            return "redirect:/login";
        }**/
        Page<ProductDto> products = productService.pageProducts(pageNo);
        //will be shown in browser tab (<title> tag)
        model.addAttribute("title", "Manage Product");
        model.addAttribute("size", products.getSize());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("products", products);
        return "products";
    }

    /**
     * method is called when an HTTP GET request is sent to the /products/{pageNo} URL
     *
     * @param pageNo current page index
     * @param keyword searched term
     * @param model represents the data which is used to pass data to the view
     * @param principal represents the current logged-in user
     * @return String with view's name "result-products.html" or redirected URL
     */
    @GetMapping("/search-result/{pageNo}")
    public String searchProducts(@PathVariable("pageNo") int pageNo, @RequestParam("keyword") String keyword, Model model, Principal principal){
        /**if(principal == null){
            return "redirect:/login";
        }**/
        Page<ProductDto> products = productService.searchProducts(pageNo, keyword);
        //will be shown in browser tab (<title> tag)
        model.addAttribute("title", "Search result");
        model.addAttribute("size", products.getSize());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("products", products);
        return "result-products";
    }

    /**
     * method is called when an HTTP GET request is sent to the /add-product URL
     *
     * @param model represents the data which is used to pass data to the view
     * @param principal represents the current logged-in user
     * @return String with view's name "result-products.html" or redirected URL
     */
    @GetMapping("/add-product")
    public String addProductForm(Model model, Principal principal){
        /**if(principal == null){
            return "redirect:/login";
        }**/
        List<Category> categories = categoryService.findAllByActivated();
        model.addAttribute("categories", categories);
        // adds an empty AdminDto object to the model, which is used for entering product information
        model.addAttribute("product", new ProductDto());
        return "add-product";
    }

    /**
     * method is called when an HTTP POST request is sent to the /save-product URL
     * and saves a new product in database
     *
     * @param productDto data transfer object with product information
     * @param imageProduct file for product image
     * @param attributes flash attributes for forwarding success or error messages
     * @return String with attribute or redirected URL
     */
    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute("product")ProductDto productDto,
                              @RequestParam("imageProduct")MultipartFile imageProduct,
                              RedirectAttributes attributes){
        try{
            productService.save( productDto);
            attributes.addFlashAttribute("success", "Added successfully");

        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to add product");
        }
        return "redirect:/products";
    }

    /**
     * method is called when an HTTP GET request is sent to the /update-product/{id} URL
     * and loads the form for updating an existing product
     *
     * @param id of the product
     * @param model represents the data which is used to pass data to the view
     * @param principal represents the current logged-in user
     * @return String with attribute or redirected URL
     */
    @GetMapping("/update-product/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model, Principal principal){
       /** if(principal == null){
            return "redirect:/login";
        }**/
        //will be shown in browser tab (<title> tag)
        model.addAttribute("title", "Update products");
        List<Category> categories = categoryService.findAllByActivated();
        ProductDto productDto = productService.getById(id);
        model.addAttribute("product", productDto);
        model.addAttribute("categories", categories);
        return "update-product";
    }

    /**
     * method is called when an HTTP POST request is sent to the /update-product/{id} URL
     *
     * @param id of the product
     * @param productDto data transfer object with the product data
     * @param imageProduct file for product image
     * @param attributes flash attributes for forwarding success or error messages
     * @return String with attribute or redirected URL
     */
    @PostMapping("/update-product/{id}") //todo check long id
    public String processUpdated(@PathVariable("id") Long id,
                                 @ModelAttribute("productDto") ProductDto productDto,
                                 @RequestParam("imageProduct") MultipartFile imageProduct,
                                 RedirectAttributes attributes){
        try {
            // updates an existing product by using the passed product object
            productService.update(productDto);
            attributes.addFlashAttribute("success", "Update successfully!");

        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to update");
        }
        return "redirect:/products";
    }

    /**
     * method is called when an HTTP PUT or GET request is sent to the /enabled-product/{id} URL
     * and activates a product by its ID
     *
     * @param id of the product
     * @param attributes flash attributes for forwarding success or error messages
     * @return String with attribute or redirected URL
     */
    @RequestMapping(value = "/enabled-product/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String enabledProduct(@PathVariable("id")Long id, RedirectAttributes attributes){
        try {
            productService.enableById(id);
            attributes.addFlashAttribute("success", "Successfully enabled");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to enable");
        }
        return "redirect:/products";
    }
}
