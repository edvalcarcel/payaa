package com.centerm.hybridcore.device;

import android.content.Context;
import android.util.Log;

import com.centerm.hybridcore.base.BaseDeviceManager;
import com.centerm.hybridcore.base.IDeviceAction;
import com.centerm.hybridcore.callback.CtCallbackHelper;
import com.centerm.hybridcore.callback.CustomCallBack;
import com.centerm.hybridcore.constant.ActionCmd;
import com.centerm.hybridcore.constant.CommonFlag;
import com.centerm.hybridcore.util.LogUtil;
import com.centerm.hybridcore.util.ParamUtil;
import com.centerm.smartpos.aidl.rfcard.AidlRFCard;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.constant.Constant;
import com.centerm.smartpos.util.HexUtil;

import org.json.JSONArray;


/**
 * RFReader
 *
 * @author qiuchunhua@centerm.com
 * @date 2018/9/30 17:45
 */
public class RFReader extends BaseDeviceManager implements IDeviceAction {
    private AidlRFCard aidlRFCard = null;

    private void open(CustomCallBack callBack) {
        try {
            aidlRFCard.open();
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
        }
    }

    private void reset(CustomCallBack callBack) {
        try {
            byte[] retData = aidlRFCard.reset();
            if (null == retData) {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_RESULT_NULL);
            } else {
                CtCallbackHelper.cbHelper(callBack, true, HexUtil.bytesToHexString(retData));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
        }
    }

    private void status(CustomCallBack callBack) {
        try {
            byte statusVal = aidlRFCard.status();
            if (statusVal == 1) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.RFCARD_IN);
            } else {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.RFCARD_OUT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(RFReader.class, e);
        }
    }

    private void sendApdu(String apduCmd, CustomCallBack callBack) {
        try {
            byte[] result = aidlRFCard.send(HexUtil.hexStringToByte(apduCmd));
            if (null == result) {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_RESULT_NULL);
            } else {
                CtCallbackHelper.cbHelper(callBack, true, HexUtil.bytesToHexString(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(RFReader.class, e);
        }
    }

    private void sendApduSync(String apduCmd, CustomCallBack callBack) {
        try {
            byte[] result = aidlRFCard.sendAsync(HexUtil.hexStringToByte(apduCmd));
            if (null == result) {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_RESULT_NULL);
            } else {
                CtCallbackHelper.cbHelper(callBack, true, HexUtil.bytesToHexString(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printException("RFReader", e);
        }
    }

    private void readCardType(CustomCallBack callBack) {
        try {
            int i = aidlRFCard.readCardType();
            CtCallbackHelper.cbHelper(callBack, true, i);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
        }
    }

    private void setLed(byte a, int b, int c, CustomCallBack callBack) {
        try {
            aidlRFCard.setLed(a, b, c);
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
        }
    }

    private void pwmbeep(byte[] bytes, CustomCallBack callBack) {
        try {
            aidlRFCard.pwmbeep(bytes);
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
        }
    }

    private void halt(CustomCallBack callBack) {
        try {
            aidlRFCard.halt();
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
        }
    }

    private void wupaReset(CustomCallBack callBack) {
        try {
            int i = aidlRFCard.wupaReset();
            if (i == 0) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
        }
    }

    private void operaIDCard(byte[] data, CustomCallBack callBack) {
        try {
            byte[] i = aidlRFCard.operaIDCard(data);
            if (null == i) {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_RESULT_NULL);
            } else {
                CtCallbackHelper.cbHelper(callBack, true, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
        }
    }

    private void close(CustomCallBack callBack) {
        try {
            aidlRFCard.close();
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getInstance().printException("RFReader", e);
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
        }
    }


    @Override
    public boolean isAction(String action) {
        return action != null && action.startsWith(ActionCmd.RF);
    }

    @Override
    public synchronized void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.RF_INIT) && aidlRFCard == null) {
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            case ActionCmd.RF_INIT:
                bindService(context, callBack);
                break;
            case ActionCmd.RF_OPEN:
                open(callBack);
                break;
            case ActionCmd.RF_CLOSE:
                close(callBack);
                break;
            case ActionCmd.RF_RESET:
                reset(callBack);
                break;
            case ActionCmd.RF_STATUS:
                status(callBack);
                break;
            //同步调用--弃用
            case ActionCmd.RF_SEND_APDUSYNC:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String apduCmd = args.optString(0, NULL_STR);
                    if (NULL_STR.equals(apduCmd)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        sendApduSync(apduCmd, callBack);
                    }
                }
                break;
            case ActionCmd.RF_WUPA_RESET:
                wupaReset(callBack);
                break;
            case ActionCmd.RF_HALT:
                halt(callBack);
                break;
            case ActionCmd.RF_SEND_APDU:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String apduCmd = args.optString(0, NULL_STR);
                    if (NULL_STR.equals(apduCmd)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        sendApdu(apduCmd, callBack);
                    }
                }
                break;
            case ActionCmd.RF_PWM_BEEP:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String beep = args.optString(0, "");
                    if (beep.isEmpty()) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                        return;
                    }
                    byte[] bytes = HexUtil.hexStringToByte(beep);
                    pwmbeep(bytes, callBack);
                }
                break;
            case ActionCmd.RF_SET_LED:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int def = Integer.MIN_VALUE;
                    int a = args.optInt(0, def);
                    int b = args.optInt(1, def);
                    int c = args.optInt(2, def);
                    if (a == def || b == def || c == def) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                        return;
                    }
                    setLed((byte) a, b, c, callBack);
                }
                break;
            case ActionCmd.RF_OPEN_ID_CARD:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String input = args.optString(0);
                    if (input.isEmpty()) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                        return;
                    }
                    byte[] data = HexUtil.hexStringToByte(input);
                    operaIDCard(data, callBack);
                }
                break;
            case ActionCmd.RF_READ_CARD_TYPE:
                readCardType(callBack);
                break;
            default:
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_ACTION_ERROR);
                break;

        }
    }

    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        aidlRFCard = AidlRFCard.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_RFCARD));
        checkAIDLNotAllNull(aidlRFCard);
    }
}
