package com.kuma.owspacekt.model.entity;

import java.util.List;

//@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class SplashEntityJ {

//    @com.fasterxml.jackson.annotation.JsonProperty("status")
//?
    private String status;
    private Integer time;
    private Integer count;
    private List<String> images;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
