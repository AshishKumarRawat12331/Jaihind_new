package co.civilguruji.Jaihindlms.ApiRespose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginResponse {


    @SerializedName("status")
    @Expose
    private String  status;

    @SerializedName("msg")
    @Expose
    private String  msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ArrayList<MasterSubAll> jobs;

    public ArrayList<MasterSubAll> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<MasterSubAll> jobs) {
        this.jobs = jobs;
    }

    @SerializedName("error")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private DataLogin data;






     @SerializedName("user")
    @Expose
    private DataLogin user;

    public DataLogin getUser() {
        return user;
    }

    public void setUser(DataLogin user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataLogin getData() {
        return data;
    }

    public void setData(DataLogin data) {
        this.data = data;
    }
}
