package ch.ffhs.library.library.dto;

/**
 * This class is a data transfer object that is used in Spring-based applications
 * to transfer data between the frontend and the backend
 */
public class CheckoutItemDto {

    private String productName;
    private int  quantity;
    private double price;
    private long productId;
    private int userId;
    private String image;

    public CheckoutItemDto() {}

    public CheckoutItemDto(String productName, int quantity, double price, long productId, int userId, String image) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.productId = productId;
        this.userId = userId;
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice(){return price;}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long id) {
        this.productId = id;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}