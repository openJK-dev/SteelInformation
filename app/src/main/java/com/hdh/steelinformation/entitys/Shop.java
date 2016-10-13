package com.hdh.steelinformation.entitys;

/**
 * Created by huangdianhua on 2016/10/12 14:34.
 */
public class Shop {
    private String name;//种类（铁，铜，不锈钢等）
    private String area;//区域
    private String time;//时间
    private String stitle;//标题
    private String sname;//品名
    private String ssize;//规格
    private String splace;//交易地
    private String spricet;//今天价
    private String spricey;//昨天价
    private String spriceb;//价格区间
    private String sutil;//单位
    private String sevent;//涨跌情况
    private String sremarks;//备注

    /**
     * 铁
     *
     * @param name
     * @param area
     * @param time
     * @param stitle
     * @param sname
     * @param ssize
     * @param splace
     * @param spricet
     * @param spricey
     * @param sevent
     * @param sremarks
     */
    public Shop(String name, String area, String time, String stitle, String sname, String ssize, String splace, String spricet, String spricey, String sevent, String sremarks) {
        this.name = name;
        this.area = area;
        this.time = time;
        this.stitle = stitle;
        this.sname = sname;
        this.ssize = ssize;
        this.splace = splace;
        this.spricet = spricet;
        this.spricey = spricey;
        this.sevent = sevent;
        this.sremarks = sremarks;

    }

    /**
     * 铜,不锈钢
     *
     * @param name
     * @param area
     * @param time
     * @param stitle
     * @param sname
     * @param ssize
     * @param splace
     * @param spriceb
     * @param sevent
     * @param sremarks
     */
    public Shop(String name, String area, String time, String stitle, String sname, String ssize, String splace, String spriceb, String sevent, String sremarks) {
        this.name = name;
        this.area = area;
        this.time = time;
        this.stitle = stitle;
        this.sname = sname;
        this.ssize = ssize;
        this.splace = splace;
        this.spriceb = spriceb;
        this.sevent = sevent;
        this.sremarks = sremarks;

    }

    /**
     * 铝
     *
     * @param name
     * @param area
     * @param time
     * @param stitle
     * @param sname
     * @param ssize
     * @param splace
     * @param spriceb
     * @param sevent
     * @param sremarks
     * @param sutil
     * @param x        没有任何意义只是为了和铁的构造方法不同
     */
    public Shop(String name, String area, String time, String stitle, String sname, String ssize, String spriceb, String sutil, String sevent, String splace, String sremarks, int x) {
        this.name = name;
        this.area = area;
        this.time = time;
        this.stitle = stitle;
        this.sname = sname;
        this.ssize = ssize;
        this.splace = splace;
        this.spriceb = spriceb;
        this.sevent = sevent;
        this.sremarks = sremarks;
        this.sutil = sutil;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStitle() {
        return stitle;
    }

    public void setStitle(String stitle) {
        this.stitle = stitle;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsize() {
        return ssize;
    }

    public void setSsize(String ssize) {
        this.ssize = ssize;
    }

    public String getSplace() {
        return splace;
    }

    public void setSplace(String splace) {
        this.splace = splace;
    }

    public String getSpricet() {
        return spricet;
    }

    public void setSpricet(String spricet) {
        this.spricet = spricet;
    }

    public String getSpricey() {
        return spricey;
    }

    public void setSpricey(String spricey) {
        this.spricey = spricey;
    }

    public String getSpriceb() {
        return spriceb;
    }

    public void setSpriceb(String spriceb) {
        this.spriceb = spriceb;
    }

    public String getSutil() {
        return sutil;
    }

    public void setSutil(String sutil) {
        this.sutil = sutil;
    }

    public String getSevent() {
        return sevent;
    }

    public void setSevent(String sevent) {
        this.sevent = sevent;
    }

    public String getSremarks() {
        return sremarks;
    }

    public void setSremarks(String sremarks) {
        this.sremarks = sremarks;
    }
}
