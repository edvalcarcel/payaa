package com.centerm.hybridcore.device;


import android.content.Context;

import com.centerm.hybridcore.base.BaseDeviceManager;
import com.centerm.hybridcore.base.IDeviceAction;
import com.centerm.hybridcore.callback.CtCallbackHelper;
import com.centerm.hybridcore.callback.CustomCallBack;
import com.centerm.hybridcore.constant.ActionCmd;
import com.centerm.hybridcore.constant.CommonFlag;
import com.centerm.hybridcore.util.LogUtil;
import com.centerm.hybridcore.util.ParamUtil;
import com.centerm.smartpos.aidl.qrscan.AidlQuickScanZbar;
import com.centerm.smartpos.aidl.qrscan.AidlScanCallback;
import com.centerm.smartpos.aidl.qrscan.CameraBeanZbar;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.constant.Constant;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;


/**
 * @author wangwenxun
 * @date 2018/9/3
 */
public class Scanner extends BaseDeviceManager implements IDeviceAction {

    private AidlQuickScanZbar aidlQuickScanZbar;

    private void scan(CameraBeanZbar bar, CustomCallBack callBack) {
        try {
            aidlQuickScanZbar.scanQRCode(bar, new AidlScanCallback.Stub() {
                @Override
                public void onCaptured(String result, int i) {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", result);
                        obj.put("code", i);
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                        LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
                    }
                }

                @Override
                public void onFailed(int error) {
                    CtCallbackHelper.cbHelper(callBack, false, error);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void cancel(CustomCallBack callBack) {
        try {
            this.aidlQuickScanZbar.cancelQRscan();
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    @Override
    public boolean isAction(String action) {
        return action != null && action.startsWith(ActionCmd.SCANNER);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.SCANNER_INIT) && aidlQuickScanZbar == null) {
            callBack.error(CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            case ActionCmd.SCANNER_INIT:
                bindService(context, callBack);
                break;
            case ActionCmd.SCANNER_SCAN:
                if (ParamUtil.checkParameters(callBack, args)) {
                    //第一个参数是CameraBeanZbar been对象对应的参数
                    JSONObject jo1 = args.optJSONObject(0);
                    //第二个参数对应的是ExternalMap 输入的参数
                    JSONObject jo2 = args.optJSONObject(1);
                    if (jo1 == null) {
                        jo1 = new JSONObject();
                    }
                    int cameraId = jo1.optInt("cameraId", 0);
                    int width = jo1.optInt("width", 640);
                    int height = jo1.optInt("height", 480);
                    int lightMode = jo1.optInt("lightMode", 4);
                    long time = jo1.optLong("time", Integer.MAX_VALUE);
                    int spinDegree = jo1.optInt("spinDegree", 0);
                    int beep = jo1.optInt("beep", 1);
                    CameraBeanZbar bar = new CameraBeanZbar(cameraId, width, height, lightMode, time, spinDegree, beep);
                    HashMap<String, Object> map = new HashMap<>(1);
                    map.put("ShowPreview", true);
                    if (jo2 != null) {
                        Iterator<String> iterator = jo2.keys();
                        while (iterator.hasNext()) {
                            String key = iterator.next();
                            map.put(key, jo2.opt(key));
                        }
                    }
                    bar.setExternalMap(map);
                    scan(bar, callBack);
                }
                break;
            case ActionCmd.SCANNER_CANCLE:
                cancel(callBack);
                break;
            default:
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_ACTION_ERROR);
                break;
        }
    }


    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        aidlQuickScanZbar = AidlQuickScanZbar.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_QUICKSCAN));
        checkAIDLNotAllNull(aidlQuickScanZbar);
    }
}
