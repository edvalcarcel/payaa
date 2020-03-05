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
import com.centerm.smartpos.aidl.lcd.AidlLcd;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.constant.Constant;

import org.json.JSONArray;


/**
 * LCD 控制屏幕亮灭
 *
 * @author qiuchunhua@centerm.com
 * @date 2018/9/30 17:43
 */
public class LCD extends BaseDeviceManager implements IDeviceAction {

    private AidlLcd device;

    private void setScreenOn(boolean on, CustomCallBack callBack) {
        try {
            if (on) {
                device.light();
            } else {
                device.dark();
            }
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
        }
    }

    @Override
    public boolean isAction(String action) {
        return action != null && action.startsWith(ActionCmd.LCD);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.LCD_INIT) && device == null) {
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            case ActionCmd.LCD_INIT:
                bindService(context, callBack);
                break;

            case ActionCmd.LCD_CONTROL:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String s = args.optString(0).toLowerCase();
                    switch (s) {
                        case "on":
                        case "light":
                        case "true":
                            setScreenOn(true, callBack);
                            break;
                        case "off":
                        case "dark":
                        case "false":
                            setScreenOn(false, callBack);
                            break;
                        default:
                            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                            break;
                    }
                }
                break;

            default:
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_ACTION_ERROR);
                break;
        }
    }

    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        device = AidlLcd.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_LCD));
        checkAIDLNotAllNull(device);
    }
}
