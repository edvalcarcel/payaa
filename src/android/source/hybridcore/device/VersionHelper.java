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
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.aidl.version.AidlVersionHelper;
import com.centerm.smartpos.constant.Constant;

import org.json.JSONArray;

/**
 * @author qiuchunhua@centerm.com
 * @date 2018/9/30 17:46
 */
public class VersionHelper extends BaseDeviceManager implements IDeviceAction {

    private AidlVersionHelper device;

    private void getVersion(String key, CustomCallBack callBack) {
        String result = null;
        key = key == null ? "" : key.toLowerCase();
        try {
            switch (key) {
                case "baseplatform": {
                    result = device.readBasePlatformVer();
                    break;
                }
                case "emv": {
                    result = device.readEmvVer();
                    break;
                }
                case "mid": {
                    result = device.readMidVer();
                    break;
                }
                case "m3": {
                    result = device.readM3Ver();
                    break;
                }
                case "k21": {
                    result = device.readK21Ver();
                    break;
                }
                case "sys": {
                    result = device.readSysVer();
                    break;
                }
                default: {
                    CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INPUT_INVALID);
                    return;
                }
            }
            CtCallbackHelper.cbHelper(callBack, true, (result == null || result.isEmpty()) ? CommonFlag.STR_RESULT_NULL : result);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
        }
    }

    @Override
    public boolean isAction(String action) {
        return action != null && action.startsWith(ActionCmd.VERSION);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.VERSION_INIT) && device == null) {
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            case ActionCmd.VERSION_INIT:
                bindService(context, callBack);
                break;
            case ActionCmd.VERSION_GET:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String key = args.optString(0).toLowerCase();
                    getVersion(key, callBack);
                }
                break;
            default:
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_ACTION_ERROR);
                break;
        }
    }

    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        device = AidlVersionHelper.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_VERSION_HELPER));
        checkAIDLNotAllNull(device);
    }
}
