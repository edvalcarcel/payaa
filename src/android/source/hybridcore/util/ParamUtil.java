package com.centerm.hybridcore.util;

import com.centerm.hybridcore.callback.CtCallbackHelper;
import com.centerm.hybridcore.callback.CustomCallBack;
import com.centerm.hybridcore.constant.CommonFlag;

import org.json.JSONArray;

/**
 * @author wangwenxun
 * @date 2018/10/9
 */
public class ParamUtil {

    /**
     * 判断入参是否为空
     * @param customCallBack 自定义回调参数
     * @param args           入参
     * @return
     */
    public static boolean checkParameters(CustomCallBack customCallBack, JSONArray args) {
        if ((args == null) || (args.length() == 0)) {
            CtCallbackHelper.cbHelper(customCallBack, false, CommonFlag.STR_INVALID_PARAM);
            return false;
        } else {
            return true;
        }
    }
}
