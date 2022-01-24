package co.civilguruji.Jaihindlms.Utils;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;


public class PackageRespose {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private ArrayList<PackageSubDemo> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<PackageSubDemo> getData() {
        return data;
    }

    public void setData(ArrayList<PackageSubDemo> data) {
        this.data = data;
    }


}
