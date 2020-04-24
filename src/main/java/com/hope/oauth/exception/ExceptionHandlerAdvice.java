package com.hope.oauth.exception;

import com.hope.oauth.util.ResponseUtil;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

/**
 * ExceptionHandlerAdvice
 *
 * @author 低调小熊猫(aodeng)
 * @date 2020-04-22
 */
@ControllerAdvice(annotations = RestController.class)
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseUtil exception(Exception e, WebRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        responseUtil.setMessage(Throwables.getRootCause(e).getMessage());
        responseUtil.setCode(-1);
        return responseUtil;
    }
}
