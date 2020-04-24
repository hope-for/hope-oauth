package com.hope.oauth.util;

import lombok.Data;

/**
 * ResponseUtil
 *
 * @author 低调小熊猫(aodeng)
 * @date 2020-04-22
 */
@Data
public class ResponseUtil {
    private int code = 0;
    private String message;
    private Object data;

    public ResponseUtil(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseUtil(Object data) {
        this.data = data;
    }

    public ResponseUtil() {
        super();
    }
}
