package com.codezf.vo;

import lombok.Data;

@Data
public class JSON<T> {
    private Integer code;
    private String msg;
    private T data;

    private JSON() {
    }

    public static <T> JSON<T> ok() {
        JSON<T> json = new JSON<>();
        json.setCode(200);
        json.setMsg("success");
        return json;
    }

    public static <T> JSON<T> ok(T data) {
        JSON<T> json = new JSON<>();
        json.setCode(200);
        json.setMsg("success");
        json.setData(data);
        return json;
    }
}