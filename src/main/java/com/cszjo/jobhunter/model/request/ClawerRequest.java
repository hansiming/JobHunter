package com.cszjo.jobhunter.model.request;

/**
 * request for clawer
 * Created by Han on 2017/4/18.
 */
public class ClawerRequest {

    private String keyWord;
    private String area;
    private boolean lagou;
    private boolean job51;
    private boolean chinahr;
    private int page;
    private String experience;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isLagou() {
        return lagou;
    }

    public void setLagou(boolean lagou) {
        this.lagou = lagou;
    }

    public boolean isJob51() {
        return job51;
    }

    public void setJob51(boolean job51) {
        this.job51 = job51;
    }

    public boolean isChinahr() {
        return chinahr;
    }

    public void setChinahr(boolean chinahr) {
        this.chinahr = chinahr;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "ClawerRequest{" +
                "keyWord='" + keyWord + '\'' +
                ", area='" + area + '\'' +
                ", lagou=" + lagou +
                ", job51=" + job51 +
                ", chinahr=" + chinahr +
                ", page=" + page +
                ", experience='" + experience + '\'' +
                '}';
    }
}
