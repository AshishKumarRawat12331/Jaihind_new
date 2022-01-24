package co.civilguruji.Jaihindlms.ApiRespose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PartsChannelVideo {


    String upload_video;

    public String getUpload_video() {
        return upload_video;
    }

    public void setUpload_video(String upload_video) {
        this.upload_video = upload_video;
    }

    @SerializedName("option")
    @Expose
    private String option;


      @SerializedName("value")
    @Expose
    private String value;

      String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("title")
    @Expose
    private String title;


    @SerializedName("description")
    @Expose
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
