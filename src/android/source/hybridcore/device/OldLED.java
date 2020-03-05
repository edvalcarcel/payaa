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
import com.centerm.smartpos.aidl.oled.AidlOled;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.constant.Constant;

import org.json.JSONArray;

/**
 * @author qiuchunhua@centerm.com
 * @date 2018/9/30 17:44
 */
public class OldLED extends BaseDeviceManager implements IDeviceAction {

    private AidlOled device = null;

    private void show(String s1, String s2, CustomCallBack callBack) {
        try {
            boolean flag = device.show(s1, s2);
            CtCallbackHelper.cbHelper(callBack, true, flag + "");
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void clear(CustomCallBack callBack) {
        try {
            boolean flag = device.clear();
            CtCallbackHelper.cbHelper(callBack, true, flag + "");
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    @Override
    public boolean isAction(String action) {
        return action != null && action.startsWith(ActionCmd.OLDLED);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.OLDLED_INIT) && device == null) {
            callBack.error(CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            case ActionCmd.OLDLED_INIT:
                bindService(context, callBack);
                break;
            case ActionCmd.OLDLED_SHOW:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String s0 = args.optString(0, NULL_STR);
                    String s1 = args.optString(1, NULL_STR);
                    if (NULL_STR.equals(s1) || NULL_STR.equals(s0)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        show(s0, s1, callBack);
                    }
                }
                break;
            case ActionCmd.OLDLED_CLEAR:
                clear(callBack);
                break;
            default:
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_ACTION_ERROR);
                break;
        }
    }

    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        device = AidlOled.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_OLED));
        checkAIDLNotAllNull(device);
    }
}
