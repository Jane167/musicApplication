package com.ljy.musicapplication.bean;

/**
 * music表对应的实体类
 */
public class Music {
    private int musicId;           // 编号
    private String musicName;      // 音乐名称
    private String musicPic;       // 封面图片
    private String singer;         // 歌手
    private MusicType musicType;   // 类别编号,存在关联关系
    private String recordCompany;  // 唱片公司
    private String releaseDate;    // 发行时间
    private String depict;         // 描述
    private Double price;          // 价格
    private int status;            // 状态

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicPic() {
        return musicPic;
    }

    public void setMusicPic(String musicPic) {
        this.musicPic = musicPic;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public MusicType getMusicType() {
        return musicType;
    }

    public void setMusicType(MusicType musicType) {
        this.musicType = musicType;
    }

    public String getRecordCompany() {
        return recordCompany;
    }

    public void setRecordCompany(String recordCompany) {
        this.recordCompany = recordCompany;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Music{" +
                "musicId=" + musicId +
                ", musicName='" + musicName + '\'' +
                ", musicPic='" + musicPic + '\'' +
                ", singer='" + singer + '\'' +
                ", musicType=" + musicType +
                ", recordCompany='" + recordCompany + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", depict='" + depict + '\'' +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
