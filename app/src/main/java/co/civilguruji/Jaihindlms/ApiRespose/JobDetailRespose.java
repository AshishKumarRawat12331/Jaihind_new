package co.civilguruji.Jaihindlms.ApiRespose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobDetailRespose {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("company_id")
    @Expose
    private String companyId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("benefits")
    @Expose
    private String benefits;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("state_id")
    @Expose
    private String stateId;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("is_freelance")
    @Expose
    private String isFreelance;
    @SerializedName("career_level_id")
    @Expose
    private String careerLevelId;
    @SerializedName("salary_from")
    @Expose
    private String salaryFrom;
    @SerializedName("salary_to")
    @Expose
    private String salaryTo;
    @SerializedName("hide_salary")
    @Expose
    private String hideSalary;
    @SerializedName("salary_currency")
    @Expose
    private String salaryCurrency;
    @SerializedName("salary_period_id")
    @Expose
    private String salaryPeriodId;
    @SerializedName("functional_area_id")
    @Expose
    private String functionalAreaId;
    @SerializedName("job_type_id")
    @Expose
    private String jobTypeId;
    @SerializedName("job_shift_id")
    @Expose
    private String jobShiftId;
    @SerializedName("num_of_positions")
    @Expose
    private String numOfPositions;
    @SerializedName("gender_id")
    @Expose
    private Object genderId;
    @SerializedName("expiry_date")
    @Expose
    private String expiryDate;
    @SerializedName("degree_level_id")
    @Expose
    private String degreeLevelId;
    @SerializedName("job_experience_id")
    @Expose
    private String jobExperienceId;
    @SerializedName("is_active")
    @Expose
    private String isActive;
    @SerializedName("is_featured")
    @Expose
    private String isFeatured;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("search")
    @Expose
    private String search;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("companie_name")
    @Expose
    private String companieName;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("career_level")
    @Expose
    private String careerLevel;
    @SerializedName("job_type")
    @Expose
    private String jobType;
    @SerializedName("job_shift")
    @Expose
    private String jobShift;
    @SerializedName("job_experience")
    @Expose
    private String jobExperience;
    @SerializedName("degree_level")
    @Expose
    private String degreeLevel;




    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

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

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getIsFreelance() {
        return isFreelance;
    }

    public void setIsFreelance(String isFreelance) {
        this.isFreelance = isFreelance;
    }

    public String getCareerLevelId() {
        return careerLevelId;
    }

    public void setCareerLevelId(String careerLevelId) {
        this.careerLevelId = careerLevelId;
    }

    public String getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(String salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public String getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(String salaryTo) {
        this.salaryTo = salaryTo;
    }

    public String getHideSalary() {
        return hideSalary;
    }

    public void setHideSalary(String hideSalary) {
        this.hideSalary = hideSalary;
    }

    public String getSalaryCurrency() {
        return salaryCurrency;
    }

    public void setSalaryCurrency(String salaryCurrency) {
        this.salaryCurrency = salaryCurrency;
    }

    public String getSalaryPeriodId() {
        return salaryPeriodId;
    }

    public void setSalaryPeriodId(String salaryPeriodId) {
        this.salaryPeriodId = salaryPeriodId;
    }

    public String getFunctionalAreaId() {
        return functionalAreaId;
    }

    public void setFunctionalAreaId(String functionalAreaId) {
        this.functionalAreaId = functionalAreaId;
    }

    public String getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(String jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    public String getJobShiftId() {
        return jobShiftId;
    }

    public void setJobShiftId(String jobShiftId) {
        this.jobShiftId = jobShiftId;
    }

    public String getNumOfPositions() {
        return numOfPositions;
    }

    public void setNumOfPositions(String numOfPositions) {
        this.numOfPositions = numOfPositions;
    }

    public Object getGenderId() {
        return genderId;
    }

    public void setGenderId(Object genderId) {
        this.genderId = genderId;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDegreeLevelId() {
        return degreeLevelId;
    }

    public void setDegreeLevelId(String degreeLevelId) {
        this.degreeLevelId = degreeLevelId;
    }

    public String getJobExperienceId() {
        return jobExperienceId;
    }

    public void setJobExperienceId(String jobExperienceId) {
        this.jobExperienceId = jobExperienceId;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(String isFeatured) {
        this.isFeatured = isFeatured;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCompanieName() {
        return companieName;
    }

    public void setCompanieName(String companieName) {
        this.companieName = companieName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCareerLevel() {
        return careerLevel;
    }

    public void setCareerLevel(String careerLevel) {
        this.careerLevel = careerLevel;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobShift() {
        return jobShift;
    }

    public void setJobShift(String jobShift) {
        this.jobShift = jobShift;
    }

    public String getJobExperience() {
        return jobExperience;
    }

    public void setJobExperience(String jobExperience) {
        this.jobExperience = jobExperience;
    }

    public String getDegreeLevel() {
        return degreeLevel;
    }

    public void setDegreeLevel(String degreeLevel) {
        this.degreeLevel = degreeLevel;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
