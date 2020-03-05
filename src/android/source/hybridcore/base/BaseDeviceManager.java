package com.centerm.hybridcore.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.centerm.hybridcore.callback.CtCallbackHelper;
import com.centerm.hybridcore.callback.CustomCallBack;
import com.centerm.hybridcore.constant.CommonFlag;
import com.centerm.hybridcore.util.LogUtil;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;

/**
 * 升腾设备管理员
 * 具体的调用思想:
 * 1.设置回调事件
 * 2.点击绑定服务，绑定服务之后会自动进行回调，通知上层服务绑定成功
 * 3.具体的设备获取manager对象
 *
 * @author wangwenxun
 * @date 2018/9/3
 */
public abstract class BaseDeviceManager {

    /**
     * AIDL管理对象
     */
    private static AidlDeviceManager manager = null;

    /**
     * 服务链接桥
     */
    private static ServiceConnection conn = null;

    /**
     * 当AIDLManager已经绑定时调用该函数,子类需要实现以获取AIDL管理对象。
     *
     * @param manager aidlDeviceManager操作对象基类
     * @throws Throwable 所有未捕获异常
     */
    public abstract void onServiceBinded(AidlDeviceManager manager) throws Throwable;

    /**
     * 绑定CpaySDK服务
     * qiuchunhua@centerm.com
     *
     * @param context  上下文对象
     * @param callBack 回调对象
     */
    public synchronized void bindService(Context context, final CustomCallBack callBack) {
        if (context == null) {
            CtCallbackHelper.cbHelper(callBack, false, "CONTEXT_IS_NULL");
            return;
        }
        if (manager == null) {
            Intent intent = new Intent();
            intent.setPackage("com.centerm.smartposservice");
            intent.setAction("com.centerm.smartpos.service.MANAGER_SERVICE");
            conn = new ServiceConnection() {
                @Override
                public void onServiceDisconnected(ComponentName name) {
                    manager = null;
                    CtCallbackHelper.cbHelper(callBack, false, "FAIL_TO_BIND_SERVICE");
                    LogUtil.getInstance().printLog("Fail to bind service");
                }

                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    manager = AidlDeviceManager.Stub.asInterface(service);
                    LogUtil.getInstance().printLog("Success to bind service");
                    if (manager == null) {
                        CtCallbackHelper.cbHelper(callBack, false, "AIDL_MANAGER_IS_NULL");
                    } else {
                        try {
                            onServiceBinded(manager);
                            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
                        } catch (Throwable throwable) {
                            //捕获所有异常(Throwable),增强软件健壮性 20181009 qiuchunhua@centerm.com
                            throwable.printStackTrace();
                            LogUtil.getInstance().printLog(this.getClass().getSimpleName() + ":" + throwable.getMessage());
                            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                        }
                    }
                }
            };
            context.bindService(intent, conn, Context.BIND_AUTO_CREATE);
        } else {
            LogUtil.getInstance().printLog("The aidl manager exits");
            try {
                onServiceBinded(manager);
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } catch (Throwable throwable) {
                //捕获所有异常(Throwable),增强软件健壮性 20181009 qiuchunhua@centerm.com
                throwable.printStackTrace();
                LogUtil.getInstance().printLog(this.getClass().getSimpleName() + ":" + throwable.getMessage());
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            }
        }
    }

    /**
     * 解绑services服务,因为有可能在多线程环境下调用,故加了同步
     * qiuchunhua@certerm.com
     *
     * @param context 上下文对象
     */
    public static synchronized void unbindService(Context context) {
        if (context == null) {
            LogUtil.getInstance().printLog("THE_CONTEXT_INPUT_IS_NULL");
        } else {
            if (conn != null) {
                context.unbindService(conn);
                conn = null;
            }
        }
    }

    /**
     * 检查传入的参数是不是有非NULL对象
     *
     * @param objects 检查对象
     * @return 存在不为null的对象返回true, else throw RuntimeException
     */
    protected static boolean checkAIDLNotAllNull(Object... objects) {
        for (Object object : objects) {
            if (object != null) {
                return true;
            }
        }
        throw new RuntimeException("AIDL_INIT_FAIL");
    }
}
