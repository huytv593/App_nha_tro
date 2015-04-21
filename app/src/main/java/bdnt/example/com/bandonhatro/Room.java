package bdnt.example.com.bandonhatro;

/**
 * Created by NamHoang on 4/21/2015.
 */
public class Room {
    private String urlImg;
    private String address;
    private String price;

    public Room(String urlImg, String address, String price) {
        this.urlImg = urlImg;
        this.address = address;
        this.price = price;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
