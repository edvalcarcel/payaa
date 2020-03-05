package com.centerm.hybridcore.device;


import android.content.Context;
import android.text.TextUtils;

import com.centerm.hybridcore.base.BaseDeviceManager;
import com.centerm.hybridcore.base.IDeviceAction;
import com.centerm.hybridcore.callback.CtCallbackHelper;
import com.centerm.hybridcore.callback.CustomCallBack;
import com.centerm.hybridcore.constant.ActionCmd;
import com.centerm.hybridcore.constant.CommonFlag;
import com.centerm.hybridcore.util.LogUtil;
import com.centerm.hybridcore.util.ParamUtil;
import com.centerm.smartpos.aidl.iccard.AidlICCard;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.constant.Constant;
import com.centerm.smartpos.util.HexUtil;

import org.json.JSONArray;


/**
 * IC设备接口
 *
 * @author qiuchunhua@centerm.com
 * @date 2018/9/30 17:43
 */
public class IcReader extends BaseDeviceManager implements IDeviceAction {
    private AidlICCard device = null;

    /**
     * 关闭
     *
     * @param customCallBack 自定义回调事件
     */
    private void close(CustomCallBack customCallBack) {
        try {
            this.device.close();
            CtCallbackHelper.cbHelper(customCallBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
            CtCallbackHelper.cbHelper(customCallBack, false, CommonFlag.STR_EXCEPTION);
        }
    }

    /**
     * 打开
     *
     * @param customCallBack 自定义回调事件
     */
    private void open(CustomCallBack customCallBack) {
        try {
            this.device.open();
            CtCallbackHelper.cbHelper(customCallBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(customCallBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    /**
     * 中断设备
     *
     * @param customCallBack 自定义回调事件
     */
    private void halt(CustomCallBack customCallBack) {
        try {
            this.device.halt();
            CtCallbackHelper.cbHelper(customCallBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(customCallBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }


    /**
     * 异步发送APDU指令
     * 注意：
     * 对于传入的指令参数，为了保证内核良好的兼容性，这里不进行合法性校验
     * 校验在调用函数前进行校验
     *
     * @param customCallBack 自定义回调事件
     * @param apduCmd        APDU指令
     */
    private void sendAPDUSync(CustomCallBack customCallBack, String apduCmd) {
        byte[] result;
        try {
            result = this.device.sendAsync(HexUtil.hexStringToByte(apduCmd));
            if (null == result) {
                CtCallbackHelper.cbHelper(customCallBack, false, CommonFlag.STR_RESULT_NULL);
            } else {
                CtCallbackHelper.cbHelper(customCallBack, true, HexUtil.bytesToHexString(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(customCallBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }


    /**
     * 发送APDU指令
     * 注意：
     * 对于传入的指令参数，为了保证内核良好的兼容性，这里不进行合法性校验
     * 校验在调用函数前进行校验
     *
     * @param customCallBack 自定义回调事件
     * @param apduCmd        APDU指令
     */
    private void sendAPDU(CustomCallBack customCallBack, String apduCmd) {
        byte[] result;
        try {
            result = this.device.send(HexUtil.hexStringToByte(apduCmd));
            if (null == result) {
                CtCallbackHelper.cbHelper(customCallBack, false, CommonFlag.STR_RESULT_NULL);
            } else {
                CtCallbackHelper.cbHelper(customCallBack, true, HexUtil.bytesToHexString(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(customCallBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }


    /**
     * 卡在位状态查询
     *
     * @param customCallBack 自定义回调事件
     */
    private void status(CustomCallBack customCallBack) {
        byte statusVal;
        try {
            statusVal = this.device.status();
            if (statusVal == 1) {
                CtCallbackHelper.cbHelper(customCallBack, true, CommonFlag.ICCARD_IN);
            } else {
                CtCallbackHelper.cbHelper(customCallBack, true, CommonFlag.ICCARD_OUT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(customCallBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    /**
     * 复位
     *
     * @param customCallBack 自定义回调事件
     */
    private void reset(CustomCallBack customCallBack) {
        byte[] retData;
        try {
            retData = this.device.reset();
            if (null == retData) {
                CtCallbackHelper.cbHelper(customCallBack, false, CommonFlag.STR_RESULT_NULL);
            } else {
                CtCallbackHelper.cbHelper(customCallBack, true, HexUtil.bytesToHexString(retData));
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(customCallBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }


    /**
     * 与上层的衔接接口，用于判断下发的指令的目标是否是本设备
     *
     * @param action 指令
     * @return 判断结果
     */
    @Override
    public boolean isAction(String action) {
        return (null != action) && action.startsWith(ActionCmd.IC);
    }

    /**
     * 执行具体的函数
     *
     * @param action   指令
     * @param args     参数
     * @param context  相关上下文
     * @param callBack 自定义回调
     */
    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.IC_INIT) && device == null) {
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            case ActionCmd.IC_INIT:
                bindService(context, callBack);
                break;

            case ActionCmd.IC_OPEN:
                open(callBack);
                break;

            case ActionCmd.IC_HALT:
                halt(callBack);
                break;

            case ActionCmd.IC_CLOSE:
                close(callBack);
                break;

            case ActionCmd.IC_RESET:
                reset(callBack);
                break;

            case ActionCmd.IC_SEND_APDU:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String cmd = args.optString(0);
                    if (TextUtils.isEmpty(cmd.trim())) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        sendAPDU(callBack, cmd);
                    }
                }
                break;

            case ActionCmd.IC_SEND_APDUSYNC:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String cmd = args.optString(0);
                    if (TextUtils.isEmpty(cmd.trim())) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        sendAPDUSync(callBack, cmd);
                    }
                }
                break;

            case ActionCmd.IC_STATUS:
                status(callBack);
                break;

            default:
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_ACTION_ERROR);
                break;

        }

    }

    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        device = AidlICCard.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_ICCARD));
        checkAIDLNotAllNull(device);
        //在CpaySDK的Demo的基础上进行了修改，将异常改为在外部捕获
    }
}
