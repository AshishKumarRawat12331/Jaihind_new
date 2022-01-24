package co.civilguruji.Jaihindlms.Banner;

import java.util.ArrayList;

/**
 * Created by Microsoft on 01-09-2017.
 */

public class VideoGalleryResponse {

    private String status;
     private ArrayList<GalleryObject> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<GalleryObject> getData() {
        return data;
    }

    public void setData(ArrayList<GalleryObject> data) {
        this.data = data;
    }
}
