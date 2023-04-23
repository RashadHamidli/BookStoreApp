package com.company.entity;


import java.sql.Date;

public class EmploymentHistory {
    private Integer id;
    private String header, jobDescription;
    private Date beginDate, endDate;
    private User user;

    @Override
    public String toString() {
        return "EmploymentHistory{" + "id=" + id + ", header='" + header + '\'' + ", jobDescription='" + jobDescription + '\'' + ", beginDate=" + beginDate + ", endDate=" + endDate + ", user=" + user + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EmploymentHistory(Integer id, String header, String jobDescription, Date beginDate, Date endDate, User user) {
        this.id = id;
        this.header = header;
        this.jobDescription = jobDescription;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.user = user;
    }

    public EmploymentHistory() {
    }
}
