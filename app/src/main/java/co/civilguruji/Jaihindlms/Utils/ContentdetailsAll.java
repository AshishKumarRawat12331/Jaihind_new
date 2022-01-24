package co.civilguruji.Jaihindlms.Utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ContentdetailsAll {


    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("msg")
    @Expose
    private ArrayList<MsgChannelsub> msg = null;

    @SerializedName("reply")
    @Expose
    private ArrayList<MsgChannelsub> reply = null;

    public ArrayList<MsgChannelsub> getReply() {
        return reply;
    }

    public void setReply(ArrayList<MsgChannelsub> reply) {
        this.reply = reply;
    }

    @SerializedName("data")
    @Expose
    private ArrayList<Channelsub> data = null;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Channelsub> getData() {
        return data;
    }

    public void setData(ArrayList<Channelsub> data) {
        this.data = data;
    }

    public ArrayList<MsgChannelsub> getMsg() {
        return msg;
    }

    public void setMsg(ArrayList<MsgChannelsub> msg) {
        this.msg = msg;
    }
}
