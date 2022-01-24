package co.civilguruji.Jaihindlms.ApiRespose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class priceResposeList {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("content_id")
    @Expose
    private String contentId;

    @SerializedName("plan_name")
    @Expose
    private String planName;

    @SerializedName("number_of_entries")
    @Expose
    private String numberOfEntries;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("list_price")
    @Expose
    private String listPrice;

    @SerializedName("final_price")
    @Expose
    private String finalPrice;

    @SerializedName("setup_fee")
    @Expose
    private String setupFee;

    @SerializedName("full_payment_duration")
    @Expose
    private String fullPaymentDuration;

    @SerializedName("tex_rate")
    @Expose
    private String texRate;

    @SerializedName("number_of_days")
    @Expose
    private String numberOfDays;

    @SerializedName("number_of_payment")
    @Expose
    private String numberOfPayment;

    @SerializedName("emi_duration")
    @Expose
    private String emiDuration;

    @SerializedName("payment")
    @Expose
    private String payment;

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("hours")
    @Expose
    private String hours;
    @SerializedName("stop_couses")
    @Expose
    private String stopCouses;
    @SerializedName("stop_days")
    @Expose
    private String stopDays;
    @SerializedName("fixed_date")
    @Expose
    private String fixedDate;
    @SerializedName("days")
    @Expose
    private String days;
    @SerializedName("course_duration")
    @Expose
    private String courseDuration;
    @SerializedName("tex_status")
    @Expose
    private String texStatus;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getNumberOfEntries() {
        return numberOfEntries;
    }

    public void setNumberOfEntries(String numberOfEntries) {
        this.numberOfEntries = numberOfEntries;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getListPrice() {
        return listPrice;
    }

    public void setListPrice(String listPrice) {
        this.listPrice = listPrice;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getSetupFee() {
        return setupFee;
    }

    public void setSetupFee(String setupFee) {
        this.setupFee = setupFee;
    }

    public String getFullPaymentDuration() {
        return fullPaymentDuration;
    }

    public void setFullPaymentDuration(String fullPaymentDuration) {
        this.fullPaymentDuration = fullPaymentDuration;
    }

    public String getTexRate() {
        return texRate;
    }

    public void setTexRate(String texRate) {
        this.texRate = texRate;
    }

    public String getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(String numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getNumberOfPayment() {
        return numberOfPayment;
    }

    public void setNumberOfPayment(String numberOfPayment) {
        this.numberOfPayment = numberOfPayment;
    }

    public String getEmiDuration() {
        return emiDuration;
    }

    public void setEmiDuration(String emiDuration) {
        this.emiDuration = emiDuration;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getStopCouses() {
        return stopCouses;
    }

    public void setStopCouses(String stopCouses) {
        this.stopCouses = stopCouses;
    }

    public String getStopDays() {
        return stopDays;
    }

    public void setStopDays(String stopDays) {
        this.stopDays = stopDays;
    }

    public String getFixedDate() {
        return fixedDate;
    }

    public void setFixedDate(String fixedDate) {
        this.fixedDate = fixedDate;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getTexStatus() {
        return texStatus;
    }

    public void setTexStatus(String texStatus) {
        this.texStatus = texStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
