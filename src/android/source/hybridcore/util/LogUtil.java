package com.centerm.hybridcore.util;

import android.util.Log;

import com.centerm.hybridcore.constant.SDKConfig;

/**
 * @author wangwenxun
 * @date 2018/9/3
 */
public class LogUtil {
    private static LogUtil logUtil = null;

    public static LogUtil getInstance(){
        if (logUtil == null){
            logUtil = new LogUtil();
        }
        return logUtil;
    }

    public void printLog(String detail){
        if(SDKConfig.OpenLog){
            Log.d(SDKConfig.TAG,detail);
        }
    }

    public void printLogWithClassName(Class cls,String detail){
        if(SDKConfig.OpenLog){
            Log.d(SDKConfig.TAG,cls.getName()+":"+detail);
        }
    }

    public void printException(String postion,Exception e){
        if(SDKConfig.OpenLog){
            Log.e(SDKConfig.TAG,"Exception in "+postion+":"+e.toString());
        }
    }

    public void printExceptionWithClassName(Class cls,Exception e){
        if(SDKConfig.OpenLog){
            Log.e(SDKConfig.TAG,"Exception in "+cls.getName()+":"+e.toString());
        }
    }

    public void printError(String postion,String error){
        if(SDKConfig.OpenLog){
            Log.e(SDKConfig.TAG,"Error in "+postion+":"+error);
        }
    }

    public void printErrorWithClassName(Class cls,String error){
        if (SDKConfig.OpenLog){
            Log.e(SDKConfig.TAG,"Error in "+cls.getName()+":"+ error);
        }
    }
}
