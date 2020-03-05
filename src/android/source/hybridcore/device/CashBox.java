package com.centerm.hybridcore.device;

import android.content.Context;

import com.centerm.hybridcore.base.BaseDeviceManager;
import com.centerm.hybridcore.base.IDeviceAction;
import com.centerm.hybridcore.callback.CtCallbackHelper;
import com.centerm.hybridcore.callback.CustomCallBack;
import com.centerm.hybridcore.constant.ActionCmd;
import com.centerm.hybridcore.constant.CommonFlag;
import com.centerm.hybridcore.util.LogUtil;
import com.centerm.smartpos.aidl.cashbox.AidlCashBox;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.constant.Constant;

import org.json.JSONArray;

/**
 * CashBox
 *
 * @author qiuchunhua@centerm.com
 * @date 2018/10/10 16:12
 */
public class CashBox extends BaseDeviceManager implements IDeviceAction {

    private AidlCashBox device;

    @Override
    public boolean isAction(String action) {
        return ActionCmd.CASH_BOX_INIT.equals(action) || ActionCmd.CASH_BOX_OPEN.equals(action);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.CASH_BOX_INIT) && device == null) {
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INIT_FIRST);
            return;
        }
        if (ActionCmd.CASH_BOX_INIT.equals(action)) {
            bindService(context, callBack);
        } else {
            try {
                boolean s = device.popCashBox();
                if (s) {
                    CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
                } else {
                    CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
                }
                callBack.success(s + "");
            } catch (Exception e) {
                e.printStackTrace();
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
            }
        }
    }

    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        device = AidlCashBox.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_CASHBOX));
        checkAIDLNotAllNull(device);
    }
}
