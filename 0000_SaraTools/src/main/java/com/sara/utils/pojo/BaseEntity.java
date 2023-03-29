package com.sara.utils.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 基础Entity
 * @Author: hujunsong
 * @Date: 2023/3/29 09:21
 * @Version V1.0.0
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1l;

    private Long id;

    private Date dateCreate;

    private Date dateUpdate;

    private String creator;

    private String updater;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}