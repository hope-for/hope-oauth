package com.hope.oauth.util;

import com.hope.oauth.model.Const.CommonConst;
import com.hope.oauth.model.enums.ResponseStatusEnum;
import com.hope.oauth.model.vo.ResponseVo;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * author: AoDeng
 * date: 2019/12/3 <br>
 */
public class ResultUtil {
    /**
     * ModelAndView
     **/
    public static ModelAndView view(String view) {
        return new ModelAndView(view);
    }

    public static ModelAndView view(String view, Map<String, Object> model) {
        return new ModelAndView(view, model);
    }

    public static ModelAndView redirect(String view) {
        return new ModelAndView("redirect:" + view);
    }

    /**
     * vo
     **/
    public static ResponseVo vo(int code, String message, Object data) {
        return new ResponseVo(code, message, data);
    }


    /**
     * error
     **/
    public static ResponseVo error(int code, String message) {
        return vo(code, message, null);
    }

    public static ResponseVo error(ResponseStatusEnum statusEnum) {
        return vo(statusEnum.getCode(), statusEnum.getMessage(), null);
    }

    public static ResponseVo error(String message) {
        return vo(CommonConst.DEFAULT_ERROR_CODE, message, null);
    }

    /**
     * success
     **/
    public static ResponseVo success(String message, Object data) {
        return vo(CommonConst.DEFAULT_SUCCESS_CODE, message, data);
    }

    public static ResponseVo success(String message) {
        return success(message, null);
    }

    public static ResponseVo success(ResponseStatusEnum statusEnum) {
        return vo(statusEnum.getCode(), statusEnum.getMessage(), null);
    }
}
