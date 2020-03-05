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
import com.centerm.smartpos.aidl.customerdisplay.AidlCustomerdisplay;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.constant.Constant;

import org.json.JSONArray;


/**
 * 客显设备
 *
 * @author qiuchunhua@centerm.com
 * @date 2018/9/30 17:43
 */
public class CustomDisplayer extends BaseDeviceManager implements IDeviceAction {

    private AidlCustomerdisplay device;

    private void open(CustomCallBack callBack) {
        try {
            boolean flag = device.init();
            if (!flag) {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
            } else {
                flag = device.open();
                if (flag) {
                    CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
                } else {
                    CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }

    private void close(CustomCallBack callBack) {
        try {
            boolean flag = device.close();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }

    private void display(String s, CustomCallBack callBack) {
        try {
            int i = device.display(s);
            CtCallbackHelper.cbHelper(callBack, true, i);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }

    private void clear(CustomCallBack callBack) {
        try {
            int i = device.clear();
            CtCallbackHelper.cbHelper(callBack, true, i);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }

    private void ledCtrl(int in, CustomCallBack callBack) {
        try {
            int i = device.ledctrl(in);
            CtCallbackHelper.cbHelper(callBack, true, i);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }

    @Override
    public boolean isAction(String action) {
        return action != null && action.startsWith(ActionCmd.CUSDISPLAYER);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.CUSDISPLAYER_INIT) && device == null) {
            callBack.error(CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            case ActionCmd.CUSDISPLAYER_INIT:
                bindService(context, callBack);
                break;
            case ActionCmd.CUSDISPLAYER_OPEN:
                open(callBack);
                break;
            case ActionCmd.CUSDISPLAYER_CLOSE:
                close(callBack);
                break;
            case ActionCmd.CUSDISPLAYER_CLEAR:
                clear(callBack);
                break;
            case ActionCmd.CUSDISPLAYER_LED_CTR:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int in = args.optInt(0, Integer.MIN_VALUE);
                    if (in == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        ledCtrl(in, callBack);
                    }
                }
                break;
            case ActionCmd.CUSDISPLAYER_DISPLAY:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String s = args.optString(0, NULL_STR);
                    if (NULL_STR.equals(s)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        display(s, callBack);
                    }
                }
                break;
            default:
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_ACTION_ERROR);
        }
    }

    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        device = AidlCustomerdisplay.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_CUSTOMDISPLAY));
        checkAIDLNotAllNull(device);
    }
}
