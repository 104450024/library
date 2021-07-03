package com.irm.demo.po;


import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class UserBlog {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    @Basic(fetch = FetchType.EAGER)

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    private Date untilTime;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserBlog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUntilTime() {
        return untilTime;
    }

    public void setUntilTime(Date untilTime) {
        this.untilTime = untilTime;
    }
}
