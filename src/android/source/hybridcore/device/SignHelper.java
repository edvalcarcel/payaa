package com.centerm.hybridcore.device;

import android.content.Context;

import com.centerm.hybridcore.base.BaseDeviceManager;
import com.centerm.hybridcore.base.IDeviceAction;
import com.centerm.hybridcore.callback.CtCallbackHelper;
import com.centerm.hybridcore.callback.CustomCallBack;
import com.centerm.hybridcore.constant.ActionCmd;
import com.centerm.hybridcore.constant.CommonFlag;
import com.centerm.hybridcore.util.LogUtil;
import com.centerm.smartpos.aidl.sign.AidlSign;
import com.centerm.smartpos.aidl.sign.AidlSignCallback;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.constant.Constant;

import org.json.JSONArray;

import static com.centerm.hybridcore.constant.CommonFlag.STR_EXCEPTION;
import static com.centerm.hybridcore.constant.CommonFlag.STR_SUCCESS;

/**
 * @author wangwenxun@centerm.com
 * @date 2018/9/3 14:42
 */
public class SignHelper extends BaseDeviceManager implements IDeviceAction {

    private AidlSign device;

    private void sign(CustomCallBack callBack) {
        try {
            this.device.doSign(new AidlSignCallback.Stub() {
                @Override
                public void onSigned(String path, int i) {
                    CtCallbackHelper.cbHelper(callBack, true, path);
                }

                @Override
                public void onFailed(int i) {
                    CtCallbackHelper.cbHelper(callBack, false, i);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(SignHelper.class, e);
        }
    }

    private void cancel(CustomCallBack callBack) {
        try {
            this.device.cancelSign();
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(SignHelper.class, e);
        }

    }

    @Override
    public boolean isAction(String action) {
        return action != null && action.startsWith(ActionCmd.SIGN);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.SIGN_INIT) && device == null) {
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            case ActionCmd.SIGN_INIT:
                bindService(context, callBack);
                break;
            case ActionCmd.SIGN_DO:
                sign(callBack);
                break;
            case ActionCmd.SIGN_CANCEL:
                cancel(callBack);
                break;
            default:
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_ACTION_ERROR);
                break;
        }
    }

    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        device = AidlSign.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_SIGN));
        checkAIDLNotAllNull(device);
    }
}
