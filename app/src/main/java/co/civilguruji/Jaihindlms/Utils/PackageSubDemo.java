package co.civilguruji.Jaihindlms.Utils;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

public class PackageSubDemo {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("course_tagline")
    @Expose
    private String courseTagline;
    @SerializedName("how_to_use")
    @Expose
    private String howToUse;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("course_id")
    @Expose
    private String courseId;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("mode")
    @Expose
    private String mode;
    @SerializedName("files")
    @Expose
    private Object files;
    @SerializedName("category")
    @Expose
    private String category;

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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourseTagline() {
        return courseTagline;
    }

    public void setCourseTagline(String courseTagline) {
        this.courseTagline = courseTagline;
    }

    public String getHowToUse() {
        return howToUse;
    }

    public void setHowToUse(String howToUse) {
        this.howToUse = howToUse;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Object getFiles() {
        return files;
    }

    public void setFiles(Object files) {
        this.files = files;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
