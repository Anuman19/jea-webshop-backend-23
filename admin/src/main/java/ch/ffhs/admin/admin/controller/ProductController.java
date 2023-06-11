package ch.ffhs.admin.admin.controller;

import ch.ffhs.library.library.dto.ProductDto;
import ch.ffhs.library.library.model.Category;
import ch.ffhs.library.library.model.Product;
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

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products")
    // Principal = current logged in user
    public String products(Model model, Principal principal){
        // if there isn't a user currently logged in, you will be redirected to the login page
        if(principal == null){
            return "redirect:/login";
        }
        List<ProductDto> productDtoList = productService.findAll();
        //will be show in browser tab (<title> tag)
        model.addAttribute("title", "Manage Product");
        model.addAttribute("products", productDtoList);
        model.addAttribute("size", productDtoList.size());
        return "products";
    }

    @GetMapping("/products/{pageNo}")
    public String productsPage(@PathVariable("pageNo") int pageNo, Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        Page<ProductDto> products = productService.pageProducts(pageNo);
        model.addAttribute("title", "Manage Product");
        model.addAttribute("size", products.getSize());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/search-result/{pageNo}")
    public String searchProducts(@PathVariable("pageNo") int pageNo, @RequestParam("keyword") String keyword, Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        Page<ProductDto> products = productService.searchProducts(pageNo, keyword);
        model.addAttribute("title", "Search result");
        model.addAttribute("size", products.getSize());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("products", products);
        return "result-products";
    }

    @GetMapping("/add-product")
    public String addProductForm(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        List<Category> categories = categoryService.findAllByActivated();
        model.addAttribute("categories", categories);
        model.addAttribute("product", new ProductDto());
        return "add-product";
    }

    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute("product")ProductDto productDto,
                              @RequestParam("imageProduct")MultipartFile imageProduct,
                              RedirectAttributes attributes){
        try{
            productService.save(imageProduct, productDto);
            attributes.addFlashAttribute("success", "Added successfully");

        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to add product");
        }

        return "redirect:/products";
    }

    @GetMapping("/update-product/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
       model.addAttribute("title", "Update products");
       List<Category> categories = categoryService.findAllByActivated();
       ProductDto productDto = productService.getById(id);
       model.addAttribute("product", productDto);
       model.addAttribute("categories", categories);
       return "update-product";
    }

    @PostMapping("/update-product/{id}")
    public String processUpdated(@PathVariable("id") Long id,
                                 @ModelAttribute("productDto") ProductDto productDto,
                                 @RequestParam("imageProduct") MultipartFile imageProduct,
                                 RedirectAttributes attributes){
        try {
            productService.update(imageProduct, productDto);
            attributes.addFlashAttribute("success", "Update successfully!");

        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to update");
        }
        return "redirect:/products";
    }

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
