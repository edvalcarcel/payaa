package com.centerm.hybridcore.device;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.util.Base64;

import com.centerm.hybridcore.base.BaseDeviceManager;
import com.centerm.hybridcore.base.IDeviceAction;
import com.centerm.hybridcore.callback.CtCallbackHelper;
import com.centerm.hybridcore.callback.CustomCallBack;
import com.centerm.hybridcore.constant.ActionCmd;
import com.centerm.hybridcore.constant.CommonFlag;
import com.centerm.hybridcore.util.LogUtil;
import com.centerm.hybridcore.util.ParamUtil;
import com.centerm.smartpos.aidl.pinpad.AidlPinPad;
import com.centerm.smartpos.aidl.pinpad.MacInfo;
import com.centerm.smartpos.aidl.pinpad.OnSignListener;
import com.centerm.smartpos.aidl.pinpad.PinInfo;
import com.centerm.smartpos.aidl.pinpad.PinPadInputPinCallback;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.constant.Constant;
import com.centerm.smartpos.util.HexUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;

import static com.centerm.hybridcore.constant.CommonFlag.STR_EXCEPTION;
import static com.centerm.hybridcore.constant.CommonFlag.STR_FAIL;
import static com.centerm.hybridcore.constant.CommonFlag.STR_RESULT_NULL;
import static com.centerm.hybridcore.constant.CommonFlag.STR_SUCCESS;

/**
 * @author wangwenxun@centerm.com
 * @date 2018/9/3 10:30
 */
public class PinpadHelper extends BaseDeviceManager implements IDeviceAction {

    private AidlPinPad aidlPinPad;

    private void open(int index, CustomCallBack callBack) {
        try {
            boolean flag = aidlPinPad.open(index);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void close(CustomCallBack callBack) {
        try {
            boolean flag = aidlPinPad.close();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void confirmGetPin(CustomCallBack callBack) {
        try {
            aidlPinPad.confirmGetPin();
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void display(String strOne, String strTwo, CustomCallBack callBack) {
        try {
            boolean flag = aidlPinPad.display(strOne, strTwo);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void disperseMkey(byte[] key, byte index, CustomCallBack callBack) {
        try {
            boolean flag = aidlPinPad.disperseMkey(key, index);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void disperseSM4Mkey(int type, byte[] key, byte index, CustomCallBack callBack) {
        try {
            boolean flag = aidlPinPad.disperseSM4Mkey(type, key, index);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void disperseWkey(byte type, byte[] key, byte mIndex, byte wIndex, CustomCallBack callBack) {
        try {
            boolean flag = aidlPinPad.disperseWkey(type, key, mIndex, wIndex);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void downKEY(byte[] key, CustomCallBack callBack) {
        try {
            boolean flag = aidlPinPad.downKEY(key);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void downloadKekEncryptedMKey(byte tekId, byte mKeyId, byte[] key, byte[] checkValue, int length, CustomCallBack callBack) {
        try {
            boolean flag = aidlPinPad.downloadKekEncryptedMKey(tekId, mKeyId, key, checkValue, length);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void downloadKekEncryptedWKey(byte type, byte tekId, byte mKeyId, byte wKeyId, byte[] key, byte[] checkValue, int length, CustomCallBack callBack) {
        try {
            boolean flag = aidlPinPad.downloadKekEncryptedWKey(type, tekId, mKeyId, wKeyId, key, checkValue, length);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void downTek(byte tekId, byte[] key, byte[] checkValue, CustomCallBack callBack) {
        try {
            boolean flag = aidlPinPad.downTek(tekId, key, checkValue);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void downloadKEKEncryptKEK(byte type, byte tekId, byte mKeyId, byte[] key, byte[] checkValue, CustomCallBack callBack) {
        try {
            boolean flag = aidlPinPad.downloadKEKEncryptKEK(type, tekId, mKeyId, key, checkValue);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void downTMK(byte[] tmk, byte[] checkValue, byte keyId, CustomCallBack callBack) {
        try {
            boolean flag = aidlPinPad.downTMK(tmk, checkValue, keyId);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void enCryptData(byte encryptMode, byte workKeyIndex, byte[] random, byte[] data, CustomCallBack callBack) {
        try {
            byte[] res = aidlPinPad.enCryptData(encryptMode, workKeyIndex, random, data);
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_RESULT_NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void getHardWareSN(CustomCallBack callBack) {
        try {
            byte[] res = aidlPinPad.getHardWareSN();
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_RESULT_NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void getMac(MacInfo macInfo, CustomCallBack callBack) {
        try {
            byte[] res = aidlPinPad.getMac(macInfo);
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_RESULT_NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void getMacForSnk(String data, String random, CustomCallBack callBack) {
        try {
            byte[] res = aidlPinPad.getMacForSNK(data, random);
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_RESULT_NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private class LoaclPinPadInputPinCallback extends PinPadInputPinCallback.Stub {

        private CustomCallBack callBack;

        private LoaclPinPadInputPinCallback(CustomCallBack callBack) {
            callBack.setKeepCallback(true);
            this.callBack = callBack;
        }

        @Override
        public void onReadingPin(int i, String s) {
            try {
                JSONObject obj = new JSONObject();
                obj.put("method", "ON_READING_PIN");
                obj.put("index", i);
                obj.put("pin", s);
                CtCallbackHelper.cbHelper(callBack, true, obj);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.getInstance().printExceptionWithClassName(this.getClass(), e);
                CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            }
        }

        @Override
        public void onReadPinCancel() {
            try {
                JSONObject obj = new JSONObject();
                obj.put("method", "ON_READ_PIN_CANCEL");
                CtCallbackHelper.cbHelper(callBack, true, obj);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.getInstance().printExceptionWithClassName(this.getClass(), e);
                CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            }
        }

        @Override
        public void onReadPinException() {
            try {
                JSONObject obj = new JSONObject();
                obj.put("method", "ON_READ_PIN_EXCEPTION");
                CtCallbackHelper.cbHelper(callBack, true, obj);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.getInstance().printExceptionWithClassName(this.getClass(), e);
                CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            }
        }

        @Override
        public void onReadPinSuccess(byte[] bytes) {
            try {
                JSONObject obj = new JSONObject();
                obj.put("method", "ON_READ_PIN_SUCCESS");
                obj.put("value", HexUtil.bytesToHexString(bytes));
                CtCallbackHelper.cbHelper(callBack, true, obj);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.getInstance().printExceptionWithClassName(this.getClass(), e);
                CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            }
        }

        @Override
        public void onReadPinTimeout() {
            try {
                JSONObject obj = new JSONObject();
                obj.put("method", "ON_READ_PIN_TIME_OUT");
                CtCallbackHelper.cbHelper(callBack, true, obj);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.getInstance().printExceptionWithClassName(this.getClass(), e);
                CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            }
        }

        @Override
        public void onError(int i, String s) {
            try {
                JSONObject obj = new JSONObject();
                obj.put("method", "ON_ERROR");
                obj.put("code", i);
                obj.put("msg", s);
                CtCallbackHelper.cbHelper(callBack, true, obj);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.getInstance().printExceptionWithClassName(this.getClass(), e);
                CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            }
        }
    }

    private void getPin(PinInfo pinInfo, CustomCallBack callBack) {
        try {
            aidlPinPad.getPin(pinInfo, new LoaclPinPadInputPinCallback(callBack));
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void stopGetPin(CustomCallBack callBack) {
        try {
            aidlPinPad.stopGetPin();
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void getPinPadSn(int index, CustomCallBack callBack) {
        try {
            String res = aidlPinPad.getPinPadSn(index);
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_RESULT_NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void getRandom(CustomCallBack callBack) {
        try {
            byte[] res = aidlPinPad.getRandom();
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_RESULT_NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void rfCardApdu(byte[] apdu, CustomCallBack callBack) {
        try {
            byte[] res = aidlPinPad.rfCardApdu(apdu);
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_RESULT_NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void rfCardReset(CustomCallBack callBack) {
        try {
            byte[] res = aidlPinPad.rfCardReset();
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_RESULT_NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void rfCardHalt(CustomCallBack callBack) {
        try {
            boolean flag = aidlPinPad.rfCardHalt();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void rfCardStatus(CustomCallBack callBack) {
        try {
            byte status = aidlPinPad.rfCardStatus();
            CtCallbackHelper.cbHelper(callBack, true, status);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void getPinWithTime(int i, PinInfo pinInfo, CustomCallBack callBack) {
        try {
            aidlPinPad.getPinWithTime(pinInfo, new PinPadInputPinCallback.Stub() {
                @Override
                public void onReadingPin(int i, String s) throws RemoteException {

                }

                @Override
                public void onReadPinCancel() throws RemoteException {

                }

                @Override
                public void onReadPinException() throws RemoteException {

                }

                @Override
                public void onReadPinSuccess(byte[] bytes) throws RemoteException {

                }

                @Override
                public void onReadPinTimeout() throws RemoteException {

                }

                @Override
                public void onError(int i, String s) throws RemoteException {

                }
            }, i);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void openPinpadKeyboard(CustomCallBack callBack) {
        try {
            boolean flag = aidlPinPad.openPinpadKeyboard();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void closePinpadKeyboard(CustomCallBack callBack) {
        try {
            boolean flag = aidlPinPad.closePinpadKeyboard();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void pinpadKeyboardState(CustomCallBack callBack) {
        try {
            int status = aidlPinPad.pinpadKeyboardState();
            CtCallbackHelper.cbHelper(callBack, true, status);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void startSign(String pkg, CustomCallBack callBack) {
        try {
            aidlPinPad.startSign(pkg, new OnSignListener.Stub() {
                @Override
                public void onSignSucceed(String s, Bitmap bitmap) {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", s);
                        if (bitmap != null) {
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            byte[] bytes = baos.toByteArray();
                            obj.put("img", Base64.encode(bytes, Base64.NO_WRAP));
                        }
                        CtCallbackHelper.cbHelper(callBack, true, obj.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }

                @Override
                public void onSignFailed(int i) {
                    CtCallbackHelper.cbHelper(callBack, false, i);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void stopSign(CustomCallBack callBack) {
        try {
            boolean flag = aidlPinPad.stopSign();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void getPinPadSnfromExternal(byte b1, byte b2, CustomCallBack callBack) {
        try {
            byte[] res = aidlPinPad.getPinPadSnfromExternal(b1, b2);
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_RESULT_NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void getPinPadSnFromProject(int n1, int n2, CustomCallBack callBack) {
        try {
            String res = aidlPinPad.getPinPadSnFromProject(n1, n2);
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_RESULT_NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void setPinpadCancelMode(byte type, CustomCallBack callBack) {
        try {
            boolean flag = aidlPinPad.setPinpadCancelMode(type);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void setKeyVoice(boolean open, CustomCallBack callBack) {
        try {
            boolean flag = aidlPinPad.setKeyVoice(open);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    private void setInnerPinpadBeep(boolean open, CustomCallBack callBack) {
        try {
            aidlPinPad.setInnerPinpadBeep(open);
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(PinpadHelper.class, e);
        }
    }

    /**
     * 检查输入的参数是否是默认值
     *
     * @param objs 需要检查的参数
     * @return 检查到有空的值:true
     */
    private boolean checkValues(Object... objs) {
        for (Object obj : objs) {
            if (obj instanceof String) {
                if (NULL_STR.equals(obj)) {
                    return true;
                }
            } else if (obj instanceof Integer) {
                if ((int) obj == Integer.MIN_VALUE) {
                    return true;
                }
            } else if (obj instanceof Long) {
                if ((Long) obj == Long.MIN_VALUE) {
                    return true;
                }
            }
            //其他类型还未出现,出现时再加上
        }
        return false;
    }

    private PinInfo toPinInfo(JSONObject obj) {
        int wkIndex = obj.optInt("wkIndex", Integer.MIN_VALUE);
        int pinInputMode = obj.optInt("pinInputMode", Integer.MIN_VALUE);
        String cardno = obj.optString("cardno", NULL_STR);
        String amount = obj.optString("amount", NULL_STR);
        int minLenth = obj.optInt("minLenth", Integer.MIN_VALUE);
        int maxLenth = obj.optInt("maxLenth", Integer.MIN_VALUE);
        String random = obj.optString("random", NULL_STR);
        int encryMode = obj.optInt("encryMode", Integer.MIN_VALUE);
        int needCardCalc = obj.optInt("needCardCalc", Integer.MIN_VALUE);
        int inputTimes = obj.optInt("inputTimes", Integer.MIN_VALUE);
        if (checkValues(wkIndex, pinInputMode, minLenth, maxLenth, encryMode, needCardCalc, inputTimes)) {
            return null;
        }
        PinInfo p = new PinInfo((byte) wkIndex, (byte) pinInputMode, cardno, amount, minLenth, maxLenth, random, (byte) encryMode, (byte) needCardCalc, (byte) inputTimes);
        //其他单独设置的类型,按需设置,boolean 类型默认是false维持不变,int/byte类型只有设置了才设置
        int cardNoDealType = obj.optInt("cardNoDealType", Integer.MIN_VALUE);
        if (cardNoDealType != Integer.MIN_VALUE) {
            p.setCardNoDealType(cardNoDealType);
        }
        boolean isCanclable = obj.optBoolean("isCanclable", false);
        p.setCanclable(isCanclable);
        boolean isBeep = obj.optBoolean("isBeep", false);
        p.isBeep(isBeep);
        int cancleType = obj.optInt("cancleType", Integer.MIN_VALUE);
        if (cancleType != Integer.MIN_VALUE) {
            p.setCancleType((byte) cancleType);
        }
        int wMode = obj.optInt("wMode", Integer.MIN_VALUE);
        if (wMode != Integer.MIN_VALUE) {
            p.setwMode((byte) wMode);
        }
        boolean isShowInputBox = obj.optBoolean("isShowInputBox", false);
        p.setShowInputBox(isShowInputBox);
        boolean isRandomKeybord = obj.optBoolean("isRandomKeybord", false);
        p.setRandomKeybord(isRandomKeybord);
        boolean isV8Mask = obj.optBoolean("isV8Mask", false);
        p.setV8Mask(isV8Mask);
        /////////////////////////
        //设置been中的map参数,存在时设置
        JSONObject map = obj.optJSONObject("map");
        if (map != null) {
            HashMap<String, Object> haMap = new HashMap<>(map.length());
            Iterator<String> it = map.keys();
            while (it.hasNext()) {
                String key = it.next();
                try {
                    haMap.put(key, map.get(key));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            p.setMap(haMap);
        }
        return p;
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
        return action != null && action.startsWith(ActionCmd.PINPAD);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.PINPAD_INIT) && aidlPinPad == null) {
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            case ActionCmd.PINPAD_INIT:
                bindService(context, callBack);
                break;
            case ActionCmd.PINPAD_STOP_GET_PIN:
                stopGetPin(callBack);
                break;
            case ActionCmd.PINPAD_GET_RANDOM:
                getRandom(callBack);
                break;
            case ActionCmd.PINPAD_DISPERSE_W_KEY:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int type = args.optInt(0, Integer.MIN_VALUE);
                    String keys = args.optString(1, NULL_STR);
                    int mIndex = args.optInt(2, Integer.MIN_VALUE);
                    int wIndex = args.optInt(3, Integer.MIN_VALUE);
                    if (type == Integer.MIN_VALUE || NULL_STR.equals(keys) || mIndex == Integer.MIN_VALUE || wIndex == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] key = localHexString2Bytes(keys);
                        disperseWkey((byte) type, key, (byte) mIndex, (byte) wIndex, callBack);
                    }
                }
                break;
            case ActionCmd.PINPAD_DISPERSE_M_KEY:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int index = args.optInt(1, Integer.MIN_VALUE);
                    String keys = args.optString(0, NULL_STR);
                    if (index == Integer.MIN_VALUE || NULL_STR.equals(keys)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] key = localHexString2Bytes(keys);
                        disperseMkey(key, (byte) index, callBack);
                    }
                }
                break;
            case ActionCmd.PINPAD_DISPERSE_SM4M_KEY:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int type = args.optInt(0, Integer.MIN_VALUE);
                    String keys = args.optString(1, NULL_STR);
                    int mIndex = args.optInt(2, Integer.MIN_VALUE);
                    if (type == Integer.MIN_VALUE || NULL_STR.equals(keys) || mIndex == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] key = localHexString2Bytes(keys);
                        disperseSM4Mkey((byte) type, key, (byte) mIndex, callBack);
                    }
                }
                break;
            case ActionCmd.PINPAD_CONFIRM_GET_PIN:
                confirmGetPin(callBack);
                break;
            case ActionCmd.PINPAD_GET_PIN:
                if (ParamUtil.checkParameters(callBack, args)) {
                    JSONObject obj = args.optJSONObject(0);
                    if (obj == null) {
                        LogUtil.getInstance().printLog("PINPAD_GET_PIN JSONObject null");
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        PinInfo pinInfo = toPinInfo(obj);
                        if (pinInfo == null) {
                            LogUtil.getInstance().printLog("PINPAD_GET_PIN toPinInfo null");
                            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                        } else {
                            getPin(pinInfo, callBack);
                        }
                    }
                }

                break;
            case ActionCmd.PINPAD_GET_MAC:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int wkIndex = args.optInt(0, Integer.MIN_VALUE);
                    int methodType = args.optInt(1, Integer.MIN_VALUE);
                    int encryptMode = args.optInt(2, Integer.MIN_VALUE);
                    String data = args.optString(3, NULL_STR);
                    String random = args.optString(4, NULL_STR);
                    if (checkValues(wkIndex, methodType, encryptMode, data, random)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        MacInfo mac = new MacInfo((byte) wkIndex, (byte) methodType, (byte) encryptMode, data, random);
                        JSONObject map = args.optJSONObject(5);
                        if (map != null) {
                            HashMap<String, Object> haMap = new HashMap<>(map.length());
                            Iterator<String> it = map.keys();
                            while (it.hasNext()) {
                                String key = it.next();
                                try {
                                    haMap.put(key, map.get(key));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            mac.setMap(haMap);
                        }
                        getMac(mac, callBack);
                    }
                }
                break;
            case ActionCmd.PINPAD_ENCRYPT_DATA:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int encryptMode = args.optInt(0, Integer.MIN_VALUE);
                    int workKeyIndex = args.optInt(1, Integer.MIN_VALUE);
                    String keys = args.optString(2, NULL_STR);
                    String randoms = args.optString(3, NULL_STR);
                    if (checkValues(encryptMode, workKeyIndex, keys, randoms)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] random = localHexString2Bytes(randoms);
                        byte[] key = localHexString2Bytes(keys);
                        enCryptData((byte) encryptMode, (byte) workKeyIndex, random, key, callBack);
                    }
                }
                break;
            case ActionCmd.PINPAD_DISPLAY:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String str1 = args.optString(0, NULL_STR);
                    String str2 = args.optString(1, NULL_STR);
                    if (NULL_STR.equals(str1) || NULL_STR.equals(str2)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        display(str1, str2, callBack);
                    }
                }
                break;
            case ActionCmd.PINPAD_GET_PINPAD_SN:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int index = args.optInt(0, Integer.MIN_VALUE);
                    if (index == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        getPinPadSn(index, callBack);
                    }
                }
                break;
            case ActionCmd.PINPAD_OPEN_PINPAD_KEYBOARD:
                openPinpadKeyboard(callBack);
                break;
            case ActionCmd.PINPAD_CLOSE_PINPAD_KEYBOARD:
                closePinpadKeyboard(callBack);
                break;
            case ActionCmd.PINPAD_PINPAD_KEYBOARD_STATE:
                pinpadKeyboardState(callBack);
                break;
            case ActionCmd.PINPAD_OPEN:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int index = args.optInt(0, Integer.MIN_VALUE);
                    if (index == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        open(index, callBack);
                    }
                }
                break;
            case ActionCmd.PINPAD_CLOSE:
                close(callBack);
                break;
            case ActionCmd.PINPAD_RF_CARD_STATUS:
                rfCardStatus(callBack);
                break;
            case ActionCmd.PINPAD_RF_CARD_RESET:
                rfCardReset(callBack);
                break;
            case ActionCmd.PINPAD_RF_CARD_APDU:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String apdus = args.optString(0, NULL_STR);
                    if (NULL_STR.equals(apdus)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] apdu = localHexString2Bytes(apdus);
                        rfCardApdu(apdu, callBack);
                    }
                }
                break;
            case ActionCmd.PINPAD_RF_CARD_HALT:
                rfCardHalt(callBack);
                break;
            case ActionCmd.PINPAD_SET_KEY_VOICE:
                if (ParamUtil.checkParameters(callBack, args)) {
                    boolean voice = args.optBoolean(0, false);
                    setKeyVoice(voice, callBack);
                }
                break;
            case ActionCmd.PINPAD_DOWN_KEY:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String keys = args.optString(0, NULL_STR);
                    if (NULL_STR.equals(keys)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] key = localHexString2Bytes(keys);
                        downKEY(key, callBack);
                    }
                }
                break;
            case ActionCmd.PINPAD_DOWN_TMK:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String tmk = args.optString(0, NULL_STR);
                    String checks = args.optString(1, NULL_STR);
                    int keyId = args.optInt(2, Integer.MIN_VALUE);
                    if (NULL_STR.equals(tmk) || NULL_STR.equals(checks) ||
                            keyId == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] key = localHexString2Bytes(tmk);
                        byte[] check = localHexString2Bytes(checks);
                        downTMK(key, check, (byte) keyId, callBack);
                    }
                }
                break;
            case ActionCmd.PINPAD_DOWN_TEK:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int tekId = args.optInt(0, Integer.MIN_VALUE);
                    String keys = args.optString(1, NULL_STR);
                    String checks = args.optString(2, NULL_STR);
                    if (NULL_STR.equals(keys) || NULL_STR.equals(checks) ||
                            tekId == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] key = localHexString2Bytes(keys);
                        byte[] check = localHexString2Bytes(checks);
                        downTek((byte) tekId, key, check, callBack);
                    }
                }
                break;
            case ActionCmd.PINPAD_DOWNLOAD_KEK_ENCRYPT_KEK:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int type = args.optInt(0, Integer.MIN_VALUE);
                    int tekId = args.optInt(1, Integer.MIN_VALUE);
                    int mKeyId = args.optInt(2, Integer.MIN_VALUE);
                    String keys = args.optString(4, NULL_STR);
                    String checks = args.optString(5, NULL_STR);
                    if (checkValues(type, tekId, mKeyId, keys, checks)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] key = localHexString2Bytes(keys);
                        byte[] check = localHexString2Bytes(checks);
                        downloadKEKEncryptKEK((byte) type, (byte) tekId, (byte) mKeyId, key, check, callBack);
                    }
                }
                break;
            case ActionCmd.PINPAD_GET_PINPAD_SN_FROM_PROJECT:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int n1 = args.optInt(0, Integer.MIN_VALUE);
                    int n2 = args.optInt(1, Integer.MIN_VALUE);
                    if (checkValues(n1, n2)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        getPinPadSnFromProject(n1, n2, callBack);
                    }
                }
                break;
            case ActionCmd.PINPAD_DOWNLOAD_KEK_ENCRYPTED_MKEY:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int tekId = args.optInt(0, Integer.MIN_VALUE);
                    int mKeyId = args.optInt(1, Integer.MIN_VALUE);
                    String keys = args.optString(2, NULL_STR);
                    String checks = args.optString(3, NULL_STR);
                    int length = args.optInt(4, Integer.MIN_VALUE);
                    if (checkValues(tekId, mKeyId, keys, checks, length)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] key = localHexString2Bytes(keys);
                        byte[] check = localHexString2Bytes(checks);
                        downloadKekEncryptedMKey((byte) tekId, (byte) mKeyId, key, check, length, callBack);
                    }
                }
                break;
            case ActionCmd.PINPAD_DOWNLOAD_KEK_ENCRYPTED_WKEY:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int type = args.optInt(0, Integer.MIN_VALUE);
                    int tekId = args.optInt(1, Integer.MIN_VALUE);
                    int mKeyId = args.optInt(2, Integer.MIN_VALUE);
                    int wKeyId = args.optInt(3, Integer.MIN_VALUE);
                    String keys = args.optString(4, NULL_STR);
                    String checks = args.optString(5, NULL_STR);
                    int length = args.optInt(6, Integer.MIN_VALUE);
                    if (checkValues(type, tekId, mKeyId, wKeyId, keys, checks, length)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] key = localHexString2Bytes(keys);
                        byte[] check = localHexString2Bytes(checks);
                        downloadKekEncryptedWKey((byte) type, (byte) tekId, (byte) mKeyId, (byte) mKeyId, key, check, length, callBack);
                    }
                }
                break;
            case ActionCmd.PINPAD_START_SIGN:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String pkg = args.optString(0, NULL_STR);
                    if (checkValues(pkg)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        startSign(pkg, callBack);
                    }
                }
                break;
            case ActionCmd.PINPAD_STOP_SIGN:
                stopSign(callBack);
                break;
            case ActionCmd.PINPAD_GET_PIN_WITH_TIME:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int type = args.optInt(0, Integer.MIN_VALUE);
                    JSONObject obj = args.optJSONObject(1);
                    if (checkValues(type) || obj == null) {
                        LogUtil.getInstance().printLog("PINPAD_GET_PIN_WITH_TIME JSONObject null");
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        PinInfo info = toPinInfo(obj);
                        if (info == null) {
                            LogUtil.getInstance().printLog("PINPAD_GET_PIN_WITH_TIME toPinInfo null");
                            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                        } else {
                            getPinWithTime(type, info, callBack);
                        }
                    }
                }
                break;
            case ActionCmd.PINPAD_GET_PINPAD_SN_FROM_EXTERNAL:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int type = args.optInt(0, Integer.MIN_VALUE);
                    int index = args.optInt(1, Integer.MIN_VALUE);
                    if (checkValues(type, index)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        getPinPadSnfromExternal((byte) type, (byte) index, callBack);
                    }
                }
                break;
            case ActionCmd.PINPAD_SET_PINPAD_CANCEL_MODE:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int type = args.optInt(0, Integer.MIN_VALUE);
                    if (checkValues(type)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        setPinpadCancelMode((byte) type, callBack);
                    }
                }
                break;
            case ActionCmd.PINPAD_GET_HARDWARE_SN:
                getHardWareSN(callBack);
                break;
            case ActionCmd.PINPAD_GET_MAC_FOR_SNK:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String data = args.optString(0, NULL_STR);
                    String random = args.optString(1, NULL_STR);
                    if (checkValues(data, random)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        getMacForSnk(data, random, callBack);
                    }
                }
                break;
            case ActionCmd.PINPAD_SET_INNER_PINPAD_BEEP:
                if (ParamUtil.checkParameters(callBack, args)) {
                    boolean open = args.optBoolean(0, false);
                    setInnerPinpadBeep(open, callBack);
                }
                break;
            default:
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_ACTION_ERROR);
                break;
        }
    }

    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        aidlPinPad = AidlPinPad.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_PINPAD));
        checkAIDLNotAllNull(aidlPinPad);
    }
}
