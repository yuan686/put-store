package com.example.common;

import lombok.Data;

/**
 * @author Yuan
 * @description
 * @date 2024/6/25
 */
@Data
public class Result {
    private int code;
    private String msg;
    private Object data;
    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static Result success(String msg, Object data) {
        return new Result(200, msg, data);
    }
    public static Result success(String msg) {
        return new Result(200, msg);
    }
    public static Result error(String msg) {
        return new Result(500, msg);
    }
}
