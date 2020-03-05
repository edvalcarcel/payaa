package com.centerm.hybridcore.base;

import android.content.Context;

import com.centerm.hybridcore.callback.CustomCallBack;

import org.json.JSONArray;

/**
 * 项目名称：CpayHybirdSDKDEV
 * 类描述：
 * 创建人：qch
 * 创建时间：2018/9/26 13:48
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface IDeviceAction {

    String NULL_STR = "NULL";

    boolean isAction(String action);

    void execute(String action, JSONArray args, Context context, CustomCallBack callBack);

}
