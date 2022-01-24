package co.civilguruji.Jaihindlms.ApiRespose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataUser {

    String contact;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("income")
    @Expose
    private String income;
    @SerializedName("credit")
    @Expose
    private String credit;
    @SerializedName("mode")
    @Expose
    private String mode;
    @SerializedName("last_view")
    @Expose
    private String lastView;
    @SerializedName("view")
    @Expose
    private String view;
    @SerializedName("rate_point")
    @Expose
    private Object ratePoint;
    @SerializedName("rate_count")
    @Expose
    private String rateCount;
    @SerializedName("vendor")
    @Expose
    private String vendor;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("courses")
    @Expose
    private Integer courses;
    @SerializedName("sell")
    @Expose
    private Sell sell;
    @SerializedName("new_sales")
    @Expose
    private Integer newSales;
    @SerializedName("new_messages")
    @Expose
    private Integer newMessages;
    @SerializedName("comments")
    @Expose
    private Integer comments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getLastView() {
        return lastView;
    }

    public void setLastView(String lastView) {
        this.lastView = lastView;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public Object getRatePoint() {
        return ratePoint;
    }

    public void setRatePoint(Object ratePoint) {
        this.ratePoint = ratePoint;
    }

    public String getRateCount() {
        return rateCount;
    }

    public void setRateCount(String rateCount) {
        this.rateCount = rateCount;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getCourses() {
        return courses;
    }

    public void setCourses(Integer courses) {
        this.courses = courses;
    }

    public Sell getSell() {
        return sell;
    }

    public void setSell(Sell sell) {
        this.sell = sell;
    }

    public Integer getNewSales() {
        return newSales;
    }

    public void setNewSales(Integer newSales) {
        this.newSales = newSales;
    }

    public Integer getNewMessages() {
        return newMessages;
    }

    public void setNewMessages(Integer newMessages) {
        this.newMessages = newMessages;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }
}
