package co.civilguruji.Jaihindlms.ApiRespose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ChannelVideoResponse {

    String status;
    String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @SerializedName("data")
    @Expose
    private ArrayList<SetChannelVideo> data = null;




    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<SetChannelVideo> getData() {
        return data;
    }

    public void setData(ArrayList<SetChannelVideo> data) {
        this.data = data;
    }
}
