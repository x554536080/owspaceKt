package com.kuma.owspacekt.model.entity;

import java.util.List;

public class ResultData {
    private String status;
    private String msg;
    private Integer code;

    public static class ResultDataInherited extends ResultData{
        private List<ItemJ> datas;

        public List<ItemJ> getDatas() {
            return datas;
        }

        public void setDatas(List<ItemJ> datas) {
            this.datas = datas;
        }
    }


    public static class Data<T> extends ResultData{
        private T data;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }



}
