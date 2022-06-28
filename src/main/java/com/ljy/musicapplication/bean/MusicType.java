package com.ljy.musicapplication.bean;


/*
 * musictype表对应的实体类
 * */
public class MusicType {
    private Integer musictypeId;    // 编号
    private String musictypeName;        // 类别名称
    private String musictypeDepict;   // 类别描述

    public Integer getMusictypeId() {
        return musictypeId;
    }

    public void setMusictypeId(Integer musictypeId) {
        this.musictypeId = musictypeId;
    }

    public String getMusictypeName() {
        return musictypeName;
    }

    public void setMusictypeName(String musictypeName) {
        this.musictypeName = musictypeName;
    }

    public String getMusictypeDepict() {
        return musictypeDepict;
    }

    public void setMusictypeDepict(String musictypeDepict) {
        this.musictypeDepict = musictypeDepict;
    }

    @Override
    public String toString() {
        return "MusicType{" +
                "musictypeId=" + musictypeId +
                ", musictypeName='" + musictypeName + '\'' +
                ", musictypeDepict='" + musictypeDepict + '\'' +
                '}';
    }
}
