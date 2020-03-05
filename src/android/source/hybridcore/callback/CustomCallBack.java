package com.centerm.hybridcore.callback;


import org.json.JSONArray;
import org.json.JSONObject;


/**
 * 这个自定义回调类是Hybrid多形式SDK的"精髓","精髓","精髓"
 * 所有人都进行回调，如果遇到不同的方案（比如今天用Cordova，明天用其他的React）
 * 那么仅仅对这个类进行修改，满足回调的要求。都会有回调的。除此之外，可以对回调进行再次的封装,以适应不同的接口
 * 这样就可以实现不修改HybridCore，仅仅修改customCallback即可
 *
 * @author wangwenxun
 * @date 2018/9/5
 */
public interface CustomCallBack {

    void success();

    void success(int number);

    void success(String string);

    void success(byte[] bytes);

    void success(JSONObject jsonObject);

    void success(JSONArray jsonArray);

    void error(String string);

    void error(JSONObject jsonObject);

    void error(int error);

    /**
     * 设置回调接口是否持续回调,解决回调接口只能调用一次的问题
     */
    void setKeepCallback(boolean keep);

}
