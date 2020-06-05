package com.cfg.deploytools.common.domain;

import java.util.HashMap;

/**
 * ClassName: AjaxResult
 * Description:
 * date: 2020/6/5 9:23
 *
 * @author CFG
 * @since JDK 1.8
 */
public class AjaxResult extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public AjaxResult() {
    }

    /*
     * @Author wadreamer
     * @Description //TODO 操作失败的默认语句
     * @Date 9:30 2020/6/5
     * @Param []
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    public static AjaxResult error() {
        return error(500, "操作失败");
    }

    /*
     * @Author wadreamer
     * @Description //TODO 自定义操作失败的描述语句
     * @Date 9:30 2020/6/5
     * @Param [msg]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    public static AjaxResult error(String msg) {
        return error(500, msg);
    }

    /*
     * @Author wadreamer
     * @Description //TODO 自定义操作失败的状态码和描述语句
     * @Date 9:31 2020/6/5
     * @Param [code, msg]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    public static AjaxResult error(int code, String msg) {
        AjaxResult json = new AjaxResult();
        json.put("code", code);
        json.put("msg", msg);
        return json;
    }

    /*
     * @Author wadreamer
     * @Description //TODO 操作成功的默认语句
     * @Date 9:38 2020/6/5
     * @Param []
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    public static AjaxResult success() {
        return success("操作成功");
    }
    
    /*
     * @Author wadreamer
     * @Description //TODO 自定义操作成功的描述语句
     * @Date 9:38 2020/6/5
     * @Param [msg]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    public static AjaxResult success(String msg) {
        return success(200, msg);
    }

    /*
     * @Author wadreamer
     * @Description //TODO 自定义操作成功的状态码和描述语句
     * @Date 9:38 2020/6/5
     * @Param [code, msg]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    public static AjaxResult success(int code, String msg) {
        return success(code, msg, null);
    }

    /*
     * @Author wadreamer
     * @Description //TODO 自定义操作成功的状态码和数据
     * @Date 9:42 2020/6/5
     * @Param [code, data]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    public static AjaxResult success(int code, Object data) {
        return success(code, "", data);
    }

    /*
     * @Author wadreamer
     * @Description //TODO 自定义操作成功的状态码、描述语句、数据
     * @Date 9:42 2020/6/5
     * @Param [code, msg, data]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    public static AjaxResult success(int code, String msg, Object data) {
        AjaxResult json = new AjaxResult();
        json.put("code", code);
        json.put("msg", msg);
        json.put("data", data);
        return json;
    }


}
