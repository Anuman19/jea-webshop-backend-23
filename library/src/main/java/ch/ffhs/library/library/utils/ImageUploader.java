package ch.ffhs.library.library.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.multi.MultiListUI;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class ImageUploader {
    // muss noch anders gehandelt werden
    private final String UPLOAD_FOLDER = "C:\\Users\\ninaa\\IdeaProjects\\jea_semesterprojekt_gruppea\\admin\\src\\main\\resources\\static\\img\\image-product";

    public boolean uploadImage(MultipartFile imageProduct){
        boolean isUploaded = false;
        try{
            Files.copy(imageProduct.getInputStream(),
                    Paths.get(UPLOAD_FOLDER + File.separator,
                            imageProduct.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            isUploaded = true;
        }catch (Exception e){
            e.printStackTrace();
        }return isUploaded;
    }

    public boolean checkExisted(MultipartFile imageProduct){
        boolean doesExist = false;
        try {
            File file = new File(UPLOAD_FOLDER + "\\" + imageProduct.getOriginalFilename());
            doesExist = file.exists();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false; // noch wechseln
    }
}
