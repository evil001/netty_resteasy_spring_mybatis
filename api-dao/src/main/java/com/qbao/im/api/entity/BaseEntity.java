package com.qbao.im.api.entity;

import java.io.Serializable;

/**
 * Created by tangxiaojun on 2017/3/28.
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    private String tabName;
}
