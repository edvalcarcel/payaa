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
import com.centerm.smartpos.aidl.psam.AidlPsam;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.constant.Constant;
import com.centerm.smartpos.util.HexUtil;

import org.json.JSONArray;

import static com.centerm.hybridcore.constant.CommonFlag.STR_EXCEPTION;
import static com.centerm.hybridcore.constant.CommonFlag.STR_FAIL;
import static com.centerm.hybridcore.constant.CommonFlag.STR_SUCCESS;

/**
 * @author wangwenxun@centerm.com
 * @date 2018/9/3 15:15
 */
public class PsamReader extends BaseDeviceManager implements IDeviceAction {

    private AidlPsam psam1;
    private AidlPsam psam2;
    private AidlPsam psam3;

    private AidlPsam getPsam(int index) {
        switch (index) {
            case 2:
                return psam2;
            case 3:
                return psam3;
            default:
                return psam1;
        }
    }

    private void open(int index, CustomCallBack callBack) {
        try {
            boolean success = getPsam(index).open();
            if (success) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PsamReader.class, e);
        }
    }

    private void close(int index, CustomCallBack callBack) {
        try {
            boolean success = getPsam(index).close();
            if (success) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PsamReader.class, e);
        }
    }

    private void reset(int index, CustomCallBack callBack) {
        try {
            byte[] ret = getPsam(index).reset();
            if (null != ret) {
                CtCallbackHelper.cbHelper(callBack, true, HexUtil.bytesToHexString(ret));
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_RESULT_NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PsamReader.class, e);
        }
    }

    private void sendApdu(int index, byte[] cmd, CustomCallBack callBack) {
        try {
            byte[] res = getPsam(index).sendApdu(cmd);
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_RESULT_NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PsamReader.class, e);
        }
    }

    private void setEtu(int index, byte time, CustomCallBack callBack) {
        try {
            boolean flag = getPsam(index).setETU(time);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PsamReader.class, e);
        }
    }

    private boolean init = false;

    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        psam1 = AidlPsam.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_PSAM1));
        psam2 = AidlPsam.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_PSAM2));
        psam3 = AidlPsam.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_PSAM3));
        init = checkAIDLNotAllNull(psam1, psam2, psam3);
    }

    @Override
    public boolean isAction(String action) {
        return action != null && action.startsWith(ActionCmd.PSAM);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.PSAM_INIT) && !init) {
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            case ActionCmd.PSAM_INIT:
                bindService(context, callBack);
                break;
            case ActionCmd.PSAM_OPEN:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int index = args.optInt(0, -1);
                    if (index == -1) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        open(index, callBack);
                    }
                }
                break;
            case ActionCmd.PSAM_SET_ETU:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int index = args.optInt(0, -1);
                    int time = args.optInt(1, Integer.MIN_VALUE);
                    if (index == -1 || time == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        setEtu(index, (byte) time, callBack);
                    }
                }
                break;
            case ActionCmd.PSAM_SEND_APDU:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int index = args.optInt(0, -1);
                    String cmd = args.optString(1);
                    if (index == -1 || cmd.isEmpty()) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        sendApdu(index, HexUtil.hexStringToByte(cmd), callBack);
                    }
                }
                break;
            case ActionCmd.PSAM_RESET:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int index = args.optInt(0, -1);
                    if (index == -1) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        reset(index, callBack);
                    }
                }
                break;
            case ActionCmd.PSAM_CLOSE:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int index = args.optInt(0, -1);
                    if (index == -1) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        close(index, callBack);
                    }
                }
                break;
            default:
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_ACTION_ERROR);
                break;
        }
    }
}
