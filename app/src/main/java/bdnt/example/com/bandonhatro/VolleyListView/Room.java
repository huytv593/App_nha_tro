package bdnt.example.com.bandonhatro.VolleyListView;

import bdnt.example.com.bandonhatro.AppConfig;

/**
 * Created by void on 25/04/2015.
 */
public class Room {
    private String id;
    private String title;
    private String create_by;
    private String create_at;
    private String end_at;
    private String price;
    private String city;
    private String district;
    private String street;
    private String address;
    private String area;
    private String info;
    private String imga;
    private String imgb;
    private String imgc;
    private String imgd;
    private String longitude;
    private String latitude;

    public Room() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getEnd_at() {
        return end_at;
    }

    public void setEnd_at(String end_at) {
        this.end_at = end_at;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImga() {
        return AppConfig.ROOT_URL_IMG+imga;
    }

    public void setImga(String imga) {
        this.imga = imga;
    }

    public String getImgb() {
        return AppConfig.ROOT_URL_IMG+imgb;
    }

    public void setImgb(String imgb) {
        this.imgb = imgb;
    }

    public String getImgc() {
        return AppConfig.ROOT_URL_IMG+imgc;
    }

    public void setImgc(String imgc) {
        this.imgc = imgc;
    }

    public String getImgd() {
        return AppConfig.ROOT_URL_IMG+imgd;
    }

    public void setImgd(String imgd) {
        this.imgd = imgd;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
