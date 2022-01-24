package co.civilguruji.Jaihindlms.ApiRespose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MasterRespose {

     String status;
     String message;

    @SerializedName("jobs")
    @Expose
    private JobDetailRespose jobs;

    public JobDetailRespose getJobs() {
        return jobs;
    }

    public void setJobs(JobDetailRespose jobs) {
        this.jobs = jobs;
    }

    ArrayList<MasterSubAll> data;
     ArrayList<MasterSubAll> genders;
     ArrayList<MasterSubAll> maritalStatuses;
      ArrayList<MasterSubAll> jobExperiences;
     ArrayList<MasterSubAll> careerLevels;
     ArrayList<MasterSubAll> industries;
     ArrayList<MasterSubAll> functionalAreas;


    public ArrayList<MasterSubAll> getData() {
        return data;
    }

    public void setData(ArrayList<MasterSubAll> data) {
        this.data = data;
    }

    @SerializedName("citie ")
    @Expose
    private ArrayList<StateRes> cities  = null;

    @SerializedName("state ")
    @Expose
    private ArrayList<StateRes> states = null;

    @SerializedName("nationalities ")
    @Expose
    private ArrayList<StateRes> nationalities  = null;



    public ArrayList<StateRes> getCities() {
        return cities;
    }

    public void setCities(ArrayList<StateRes> cities) {
        this.cities = cities;
    }

    public ArrayList<StateRes> getStates() {
        return states;
    }

    public void setStates(ArrayList<StateRes> states) {
        this.states = states;
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

    public ArrayList<MasterSubAll> getGenders() {
        return genders;
    }

    public void setGenders(ArrayList<MasterSubAll> genders) {
        this.genders = genders;
    }

    public ArrayList<MasterSubAll> getMaritalStatuses() {
        return maritalStatuses;
    }

    public void setMaritalStatuses(ArrayList<MasterSubAll> maritalStatuses) {
        this.maritalStatuses = maritalStatuses;
    }

    public ArrayList<StateRes> getNationalities() {
        return nationalities;
    }

    public void setNationalities(ArrayList<StateRes> nationalities) {
        this.nationalities = nationalities;
    }

    public ArrayList<MasterSubAll> getJobExperiences() {
        return jobExperiences;
    }

    public void setJobExperiences(ArrayList<MasterSubAll> jobExperiences) {
        this.jobExperiences = jobExperiences;
    }

    public ArrayList<MasterSubAll> getCareerLevels() {
        return careerLevels;
    }

    public void setCareerLevels(ArrayList<MasterSubAll> careerLevels) {
        this.careerLevels = careerLevels;
    }

    public ArrayList<MasterSubAll> getIndustries() {
        return industries;
    }

    public void setIndustries(ArrayList<MasterSubAll> industries) {
        this.industries = industries;
    }

    public ArrayList<MasterSubAll> getFunctionalAreas() {
        return functionalAreas;
    }

    public void setFunctionalAreas(ArrayList<MasterSubAll> functionalAreas) {
        this.functionalAreas = functionalAreas;
    }
}
