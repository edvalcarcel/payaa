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
import com.centerm.smartpos.aidl.mifare.AidlMifare;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.constant.Constant;
import com.centerm.smartpos.util.HexUtil;

import org.json.JSONArray;

/**
 * Mifare(M1)设备
 *
 * @author qiuchunhua@centerm.com
 * @date 2018/10/10 14:57
 */
public class MifareReader extends BaseDeviceManager implements IDeviceAction {

    private AidlMifare aidlMifareCard;

    private void open(CustomCallBack callBack) {
        try {
            aidlMifareCard.open();
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void reset(CustomCallBack callBack) {
        try {
            byte[] retData = aidlMifareCard.reset();
            if (null == retData) {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_RESULT_NULL);
            } else {
                CtCallbackHelper.cbHelper(callBack, true, HexUtil.bytesToHexString(retData));
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void status(CustomCallBack callBack) {
        byte statusVal;
        try {
            statusVal = aidlMifareCard.status();
            if (statusVal == 1) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.M1CARD_IN);
            } else {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.M1CARD_OUT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void auth(int flag, byte addId, byte[] keyA, byte[] resetData, CustomCallBack callBack) {
        try {
            int ret = aidlMifareCard.auth(flag, addId, keyA, resetData);
            CtCallbackHelper.cbHelper(callBack, true, ret);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void readBlock(byte addId, CustomCallBack callBack) {
        try {
            byte[] wData = new byte[24];
            int ret = aidlMifareCard.readBlock(addId, wData);
            if (ret == 0) {
                CtCallbackHelper.cbHelper(callBack, true, HexUtil.bcd2str(wData));
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void writeBlock(byte addId, byte[] wData, CustomCallBack callBack) {
        try {
            int ret = this.aidlMifareCard.writeBlock(addId, wData);
            if (ret == 0) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void close(CustomCallBack callBack) {
        try {
            aidlMifareCard.close();
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }


    /**
     * 加值操作
     *
     * @param addId    地址
     * @param custom   数据
     * @param redData  数据
     * @param callBack 回调
     */
    private void addValue(byte addId, byte[] custom, byte[] redData, CustomCallBack callBack) {
        try {
            int ret = aidlMifareCard.addValue(addId, custom, redData);
            CtCallbackHelper.cbHelper(callBack, true, ret);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    /**
     * 减值操作
     *
     * @param addId    地址
     * @param custom   数据
     * @param redData  数据
     * @param callBack 回调
     */
    private void reduceValue(byte addId, byte[] custom, byte[] redData, CustomCallBack callBack) {
        try {
            int ret = aidlMifareCard.reduceValue(addId, custom, redData);
            CtCallbackHelper.cbHelper(callBack, true, ret);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void readStorage(byte address, CustomCallBack callBack) {
        try {
            byte[] res = aidlMifareCard.readM1Storage(address);
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_RESULT_NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void writeStorage(byte address, byte data, CustomCallBack callBack) {
        try {
            aidlMifareCard.writeM1Storage(address, data);
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    /**
     * @param hex hex字符串
     * @return bytes
     */
    private byte[] localHexString2Bytes(String hex) {
        if (hex == null || hex.length() < 2 || NULL_STR.equals(hex)) {
            return null;
        }
        byte[] ret = HexUtil.hexStringToByte(hex);
        if (ret.length == 0) {
            return null;
        }
        return ret;
    }

    @Override
    public boolean isAction(String action) {
        return (action != null) && action.startsWith(ActionCmd.MIFARE);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.MIFARE_INIT) && aidlMifareCard == null) {
            callBack.error(CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            case ActionCmd.MIFARE_INIT:
                bindService(context, callBack);
                break;
            case ActionCmd.MIFARE_OPEN:
                open(callBack);
                break;
            case ActionCmd.MIFARE_CLOSE:
                close(callBack);
                break;
            case ActionCmd.MIFARE_RESET:
                reset(callBack);
                break;
            case ActionCmd.MIFARE_STATUS:
                status(callBack);
                break;
            //加值
            case ActionCmd.MIFARE_ADD:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int addId = args.optInt(0, Integer.MIN_VALUE);
                    if (addId == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                        return;
                    }
                    byte[] custom, redData;
                    //接口输入值可以为NULL,JSONArray不存在或者值为NULL时表示输入空值
                    String customStr = args.optString(1, NULL_STR);
                    String redDataStr = args.optString(2, NULL_STR);
                    custom = localHexString2Bytes(customStr);
                    redData = localHexString2Bytes(redDataStr);
                    addValue((byte) addId, custom, redData, callBack);
                }
                break;
            //减值
            case ActionCmd.MIFARE_REDUCE:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int addId = args.optInt(0, Integer.MIN_VALUE);
                    if (addId == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                        return;
                    }
                    //接口输入值可以为NULL,JSONArray不存在或者值为NULL时表示输入空值
                    String customStr = args.optString(1, NULL_STR);
                    String redDataStr = args.optString(2, NULL_STR);
                    byte[] custom = localHexString2Bytes(customStr);
                    byte[] redData = localHexString2Bytes(redDataStr);
                    reduceValue((byte) addId, custom, redData, callBack);
                }
                break;
            case ActionCmd.MIFARE_AUTH:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int flag = args.optInt(0, -1);
                    int addId = args.optInt(1, Integer.MIN_VALUE);
                    String keyAStr = args.optString(2, NULL_STR);
                    String resetDataStr = args.optString(3, NULL_STR);
                    byte[] keyA = localHexString2Bytes(keyAStr);
                    byte[] resetData = localHexString2Bytes(resetDataStr);
                    if (flag == -1 || addId == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        auth(flag, (byte) addId, keyA, resetData, callBack);
                    }
                }
                break;
            case ActionCmd.MIFARE_READ:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int addId = args.optInt(0, Integer.MIN_VALUE);
                    if (addId == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        readBlock((byte) addId, callBack);
                    }
                }
                break;
            case ActionCmd.MIFARE_WRITE:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int addId = args.optInt(0, Integer.MIN_VALUE);
                    String byteStr = args.optString(1);
                    if (addId == Integer.MIN_VALUE || byteStr.isEmpty()) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] wData = HexUtil.hexStringToByte(byteStr);
                        writeBlock((byte) addId, wData, callBack);
                    }
                }
                break;
            case ActionCmd.MIFARE_READ_STORAGE:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int address = args.optInt(0, Integer.MIN_VALUE);
                    if (address == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        readStorage((byte) address, callBack);
                    }
                }
                break;
            case ActionCmd.MIFARE_WRITE_STORAGE:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int address = args.optInt(0, Integer.MIN_VALUE);
                    if (address == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                        return;
                    }
                    int data = args.optInt(1, Integer.MIN_VALUE);
                    if (data == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                        return;
                    }
                    writeStorage((byte) address, (byte) data, callBack);
                }
                break;
            default:
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_ACTION_ERROR);
                break;
        }
    }

    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        aidlMifareCard = AidlMifare.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_MIFARE));
        checkAIDLNotAllNull(aidlMifareCard);
    }
}
