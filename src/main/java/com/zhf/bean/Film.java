package com.zhf.bean;

/**
 * Created on 2019/10/24 0024.
 */
public class Film {
    private int fid;
    private String fName;
    private String fType;
    private String fIntroduce;

    public Film() {
    }

    public Film(String fName, String fType, String fIntroduce) {
        this.fName = fName;
        this.fType = fType;
        this.fIntroduce = fIntroduce;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }

    public String getfIntroduce() {
        return fIntroduce;
    }

    public void setfIntroduce(String fIntroduce) {
        this.fIntroduce = fIntroduce;
    }

    @Override
    public String toString() {
        return String.format("%-10d%-25s\t%-10s%s",fid,fName,fType,fIntroduce);
    }
}
