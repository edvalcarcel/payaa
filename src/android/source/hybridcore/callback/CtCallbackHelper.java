package com.centerm.hybridcore.callback;

import com.centerm.hybridcore.constant.CommonFlag;
import com.centerm.hybridcore.util.LogUtil;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author wangwenxun
 * @date 2018/9/5
 */
public class CtCallbackHelper {

    /**
     * 回调帮助,int 类型
     *
     * @param callbackContext 回调
     * @param success         操作状态
     * @param message         返回消息
     */
    public static void cbHelper(CustomCallBack callbackContext, boolean success, int message) {
        if (callbackContext != null) {
            if (success) {
                callbackContext.success(message);
            } else {
                callbackContext.error(message);
            }
        } else {
            LogUtil.getInstance().printLog("CALLBACK_IS_NULL");
        }
    }


    /**
     * 回调帮助,String类型
     *
     * @param callbackContext 回调
     * @param success         操作状态
     * @param message         返回消息
     */
    public static void cbHelper(CustomCallBack callbackContext, boolean success, String message) {
        if (callbackContext != null) {
            if (success) {
                if (message != null) {
                    callbackContext.success(message);
                } else {
                    callbackContext.success();
                }
            } else {
                if (message != null) {
                    callbackContext.error(message);
                } else {
                    callbackContext.error(CommonFlag.STR_UNKNOWN_ERROR);
                }
            }
        } else {
            LogUtil.getInstance().printLog("CALLBACK_IS_NULL");
        }
    }


    /**
     * 回调帮助，byte[]类型
     *
     * @param callbackContext 回调
     * @param success         操作状态
     * @param message         返回消息
     */
    public static void cbHelper(CustomCallBack callbackContext, boolean success, byte[] message) {
        if (callbackContext != null) {
            if (success) {
                if (message != null) {
                    callbackContext.success(message);
                } else {
                    callbackContext.success();
                }
            } else {
                callbackContext.error(CommonFlag.STR_UNKNOWN_ERROR);
            }
        } else {
            LogUtil.getInstance().printLog("CALLBACK_IS_NULL");
        }
    }

    /**
     * 回调帮助，JSONArray类型
     *
     * @param callbackContext
     * @param success
     * @param jsonArray
     */
    public static void cbHelper(CustomCallBack callbackContext, boolean success, JSONArray jsonArray) {
        if (callbackContext != null) {
            if (success) {
                if (jsonArray != null) {
                    callbackContext.success(jsonArray);
                } else {
                    callbackContext.success();
                }
            } else {
                callbackContext.error(CommonFlag.STR_UNKNOWN_ERROR);
            }
        } else {
            LogUtil.getInstance().printLog("CALLBACK_IS_NULL");
        }
    }

    /**
     * 回调帮助,JSONObject 类型
     *
     * @param callbackContext
     * @param success
     * @param jsonObject
     */
    public static void cbHelper(CustomCallBack callbackContext, boolean success, JSONObject jsonObject) {
        if (callbackContext != null) {
            if (success) {
                if (jsonObject != null) {
                    callbackContext.success(jsonObject);
                } else {
                    callbackContext.success();
                }
            } else {
                if (jsonObject != null) {
                    callbackContext.error(jsonObject);
                } else {
                    callbackContext.error(CommonFlag.STR_UNKNOWN_ERROR);
                }
            }
        } else {
            LogUtil.getInstance().printLog("CALLBACK_IS_NULL");
        }
    }
}
