package com.centerm.hybridcore.device;

import android.content.Context;
import android.os.RemoteException;

import com.centerm.hybridcore.base.BaseDeviceManager;
import com.centerm.hybridcore.base.IDeviceAction;
import com.centerm.hybridcore.callback.CtCallbackHelper;
import com.centerm.hybridcore.callback.CustomCallBack;
import com.centerm.hybridcore.constant.ActionCmd;
import com.centerm.hybridcore.constant.CommonFlag;
import com.centerm.hybridcore.util.LogUtil;
import com.centerm.hybridcore.util.ParamUtil;
import com.centerm.smartpos.aidl.pboc.AidlCheckCardListener;
import com.centerm.smartpos.aidl.pboc.AidlEMVL2;
import com.centerm.smartpos.aidl.pboc.CardInfoData;
import com.centerm.smartpos.aidl.pboc.CardLoadLog;
import com.centerm.smartpos.aidl.pboc.CardTransLog;
import com.centerm.smartpos.aidl.pboc.DRLParam;
import com.centerm.smartpos.aidl.pboc.EmvTransData;
import com.centerm.smartpos.aidl.pboc.PBOCListener;
import com.centerm.smartpos.aidl.pboc.ParcelableTrackData;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.constant.Constant;
import com.centerm.smartpos.util.HexUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;


/**
 * @author wangwenxun@centerm.com
 * @date 2018/9/3 11:11
 */
public class EmvManager extends BaseDeviceManager implements IDeviceAction {

    private AidlEMVL2 device;

    private void importAidSelectResult(int index, CustomCallBack callBack) {
        try {
            boolean flag = device.importAidSelectRes(index);
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

    private void abortPboc(CustomCallBack callBack) {
        try {
            device.abortPBOC();
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }

    private void updateCAPK(int index, String data, CustomCallBack callBack) {
        try {
            boolean result = device.updateCAPK(index, data);
            if (result) {
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

    private void aidParamRead(byte[] index, CustomCallBack callBack) {
        try {
            byte[] result = device.aidParamRead(index);
            if (null != result) {
                CtCallbackHelper.cbHelper(callBack, true, result);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }

    private void cancelCheckCard(CustomCallBack callBack) {
        try {
            device.cancelCheckCard();
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }

    private void caPublicKeyParamRead(byte[] index, CustomCallBack callBack) {
        try {
            byte[] result = device.caPublicKeyParamRead(index);
            if (null != result) {
                CtCallbackHelper.cbHelper(callBack, true, result);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }

    private void checkCard(boolean checkMag, boolean checkIc, boolean checkRf, int time, CustomCallBack callBack) {
        try {
            //使其可多次回调
            callBack.setKeepCallback(true);
            device.checkCard(checkMag, checkIc, checkRf, time, new AidlCheckCardListener.Stub() {
                @Override
                public void onFindMagCard(ParcelableTrackData parcelableTrackData) {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", CommonFlag.FIND_MAG_CARD);
                        obj.put("CardNo", parcelableTrackData.getCardNo());
                        obj.put("CardType", parcelableTrackData.getCardType());
                        obj.put("ExpireDate", parcelableTrackData.getExpireDate());
                        obj.put("TrackData1", HexUtil.bcd2str(parcelableTrackData.getFirstTrackData()));
                        obj.put("TrackData2", HexUtil.bcd2str(parcelableTrackData.getSecondTrackData()));
                        obj.put("TrackData3", HexUtil.bcd2str(parcelableTrackData.getThirdTrackData()));
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }

                @Override
                public void onSwipeCardFail() {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", CommonFlag.SWIPE_CARD_FAIL);
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }

                @Override
                public void onFindICCard() {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", CommonFlag.FIND_IC_CARD);
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }

                @Override
                public void onFindRFCard() {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", CommonFlag.FIND_RF_CARD);
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }

                @Override
                public void onTimeout() {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", CommonFlag.TIME_OUT);
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }

                @Override
                public void onCanceled() {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", CommonFlag.SWIPE_CARD_FAIL);
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }

                @Override
                public void onError(int i) {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", CommonFlag.ERROR);
                        obj.put("code", i);
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }

    private void clearKernelICTransLog(CustomCallBack callBack) {
        try {
            boolean flag = device.clearKernelICTransLog();
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

    private void endPboc(CustomCallBack callBack) {
        try {
            device.endPBOC();
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }

    private void importAmount(String amount, CustomCallBack callBack) {
        try {
            boolean flag = device.importAmount(amount);
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


    private void importConfirmCardInfoRes(boolean confirmCardInfoRes, CustomCallBack callBack) {
        try {
            boolean flag = device.importConfirmCardInfoRes(confirmCardInfoRes);
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

    private void importECashTipConfirmRes(boolean eCashTipConfirmResult, CustomCallBack callBack) {
        try {
            boolean flag = device.importECashTipConfirmRes(eCashTipConfirmResult);
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

    private void importOnlineResp(boolean success, String code, String result, CustomCallBack callBack) {
        try {
            device.importOnlineResp(success, code, result);
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }

    private void importPin(String pin, CustomCallBack callBack) {
        try {
            device.importPin(pin);
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }


    private void processPBOC(EmvTransData data, CustomCallBack callBack) {
        try {
            //使其可多次回调
            callBack.setKeepCallback(true);
            device.processPBOC(data, new PBOCListener.Stub() {
                @Override
                public void requestImportAmount(int i) throws RemoteException {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", "REQUEST_IMPORT_AMOUNT");
                        obj.put("data", i);
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }

                @Override
                public void requestTipsConfirm(String s) throws RemoteException {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", "REQUEST_TIPS_CONFIRM");
                        obj.put("tips", s);
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }

                @Override
                public void requestAidSelect(int i, String[] aids) throws RemoteException {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", "REQUEST_AID_SELECT");
                        obj.put("code", i);
                        JSONArray array = new JSONArray();
                        if (aids != null) {
                            for (String s : aids) {
                                array.put(s);
                            }
                        }
                        obj.put("aids", array);
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }

                @Override
                public void requestEcashTipsConfirm() throws RemoteException {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", "REQUEST_ECASH_TIPS_CONFIRM");
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }

                @Override
                public void onConfirmCardInfo(CardInfoData cardInfoData) throws RemoteException {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", "ON_CONFIRM_CARD_INFO");
                        if (cardInfoData != null) {
                            obj.put("card_no", cardInfoData.getCardno());
                        }
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }

                @Override
                public void requestImportPin(int i, boolean b, String ps) throws RemoteException {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", "REQUEST_IMPORT_PIN");
                        obj.put("code", i);
                        obj.put("bol", b);
                        obj.put("ps", ps);
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }

                @Override
                public void requestUserAuth(int i, String auth) throws RemoteException {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", "REQUEST_USER_AUTH");
                        obj.put("code", i);
                        obj.put("auth", auth);
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }

                @Override
                public void onRequestOnline() throws RemoteException {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", "ON_REQUEST_ONLINE");
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }

                @Override
                public void onReadCardOffLineBalance(String s, String s1, String s2, String s3) throws RemoteException {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", "ON_READ_CARD_OFFLINE_BALANCE");
                        obj.put("s0", s);
                        obj.put("s1", s1);
                        obj.put("s2", s2);
                        obj.put("s3", s3);
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }

                @Override
                public void onReadCardTransLog(CardTransLog[] cardTransLogs) throws RemoteException {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", "ON_READ_CARD_TRANS_LOG");
                        JSONArray array = new JSONArray();
                        if (cardTransLogs != null) {
                            for (CardTransLog log : cardTransLogs) {
                                array.put(log);
                            }
                        }
                        obj.put("card_trans_log", array);
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }

                @Override
                public void onReadCardLoadLog(String s, String s1, CardLoadLog[] cardLoadLogs) throws
                        RemoteException {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", "ON_READ_CARD_LOAD_LOG");
                        obj.put("s0", s);
                        obj.put("s1", s1);
                        JSONArray array = new JSONArray();
                        if (cardLoadLogs != null) {
                            for (CardLoadLog log : cardLoadLogs) {
                                array.put(log);
                            }
                        }
                        obj.put("card_load_log", array);
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }

                @Override
                public void onTransResult(byte b) throws RemoteException {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", "ON_TRANS_RESULT");
                        obj.put("code", (int) b);
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }

                @Override
                public void onError(int i) throws RemoteException {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("result", "ON_ERROR");
                        obj.put("code", i);
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }


    private void setIcSlot(byte index, CustomCallBack callBack) {
        try {
            boolean flag = device.setICSlot(index);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }


    private void setParam(byte tag, byte value, CustomCallBack callBack) {
        try {
            boolean flag = device.setParameters(tag, value);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }

    private void importUserAuthRes(boolean success, CustomCallBack callBack) {
        try {
            boolean flag = device.importUserAuthRes(success);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }

    private void importMsgConfirmRes(boolean success, CustomCallBack callBack) {
        try {
            boolean flag = device.importMsgConfirmRes(success);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }


    private void readKernelData(byte[] tagList, byte[] outBuffer, CustomCallBack callBack) {
        try {
            int result = device.readKernelData(tagList, outBuffer);
            if (result > 0) {
                CtCallbackHelper.cbHelper(callBack, true, HexUtil.bcd2str(outBuffer));
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }

    private void resultOfIssuerVoiceReference(byte[] result, CustomCallBack callBack) {
        try {
            boolean flag = device.resultOfIssuerVoiceReference(result);
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

    private void drlParamRead(byte[] data, CustomCallBack callBack) {
        try {
            DRLParam drlParam = device.drlParamRead(data);
            if (null != drlParam) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("DRL", drlParam);
                CtCallbackHelper.cbHelper(callBack, true, jsonObject);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }

    private void updateDrlParam(int index, DRLParam drlParam, CustomCallBack callBack) {
        try {
            boolean flag = device.updateDRLParams(index, drlParam);
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

    private void parseTLV(String tag, String data, CustomCallBack callBack) {
        try {
            String result = device.parseTLV(tag, data);
            if (null != result) {
                CtCallbackHelper.cbHelper(callBack, true, result);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }

    private void setTLV(int index, byte[] data, CustomCallBack callBack) {
        try {
            device.setTLV(index, data);
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }

    private void updateAID(int index, String data, CustomCallBack callBack) {
        try {
            boolean flag = device.updateAID(index, data);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(EmvManager.class, e);
        }
    }


    @Override
    public boolean isAction(String action) {
        return action != null && action.startsWith(ActionCmd.EMV);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.EMV_INIT) && device == null) {
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            case ActionCmd.EMV_INIT:
                bindService(context, callBack);
                break;
            case ActionCmd.EMV_CHECK_CARD:
                if (ParamUtil.checkParameters(callBack, args)) {
                    boolean checkMag = args.optBoolean(0, false);
                    boolean checkIc = args.optBoolean(1, false);
                    boolean checkRf = args.optBoolean(2, false);
                    int time = args.optInt(3, Integer.MIN_VALUE);
                    if (time == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        checkCard(checkMag, checkIc, checkRf, time, callBack);
                    }
                }
                break;
            case ActionCmd.EMV_CANCEL_CHECK_CARD:
                cancelCheckCard(callBack);
                break;
            case ActionCmd.EMV_PROCESS_PBOC:
                if (ParamUtil.checkParameters(callBack, args)) {
                    JSONObject obj = args.optJSONObject(0);
                    if (obj == null) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        /*
                        * keys:transType,requestAmtPosition,isEcashEnable,isSmEnable,isForceOnline,emvFlow,slotType,isTransTypeSimpleFlow,isConfirmCardNo,isOnlyOffline,resv
                        * */
                        int transType = obj.optInt("transType", Integer.MIN_VALUE);
                        int requestAmtPosition = obj.optInt("requestAmtPosition", Integer.MIN_VALUE);
                        boolean isEcashEnable = obj.optBoolean("isEcashEnable", false);
                        boolean isSmEnable = obj.optBoolean("isSmEnable", false);
                        boolean isForceOnline = obj.optBoolean("isForceOnline", false);
                        int emvFlow = obj.optInt("emvFlow", Integer.MIN_VALUE);
                        int slotType = obj.optInt("slotType", Integer.MIN_VALUE);
                        boolean isTransTypeSimpleFlow = obj.optBoolean("isTransTypeSimpleFlow", false);
                        boolean isConfirmCardNo = obj.optBoolean("isConfirmCardNo", false);
                        boolean isOnlyOffline = obj.optBoolean("isOnlyOffline", false);
                        String resvs = obj.optString("resv", NULL_STR);
                        EmvTransData emvData = new EmvTransData();
                        JSONObject map = obj.optJSONObject("map");
                        if (map != null) {
                            Iterator<String> iterator = map.keys();
                            HashMap<String, Object> hmap = new HashMap<>(map.length());
                            while (iterator.hasNext()) {
                                String key = iterator.next();
                                hmap.put(key, map.optString(key));
                            }
                            emvData.setMap(hmap);
                        }
                        if (transType != Integer.MIN_VALUE) {
                            emvData.setTranstype((byte) transType);
                        }
                        if (requestAmtPosition != Integer.MIN_VALUE) {
                            emvData.setRequestAmtPosition((byte) requestAmtPosition);
                        }
                        emvData.setIsEcashEnable(isEcashEnable);
                        emvData.setIsSmEnable(isSmEnable);
                        emvData.setIsForceOnline(isForceOnline);
                        if (emvFlow != Integer.MIN_VALUE) {
                            emvData.setEMVFlow((byte) emvFlow);
                        }
                        if (slotType != Integer.MIN_VALUE) {
                            emvData.setSlotType((byte) slotType);
                        }
                        emvData.setTransTypeSimpleFlow(isTransTypeSimpleFlow);
                        emvData.setConfirmCardNo(isConfirmCardNo);
                        emvData.setOnlyOffline(isOnlyOffline);
                        if (!NULL_STR.equals(resvs)) {
                            byte[] resv = HexUtil.hexStringToByte(resvs);
                            emvData.setResv(resv);
                        }
                        processPBOC(emvData, callBack);
                    }
                }
                break;
            case ActionCmd.EMV_END_PBOC:
                endPboc(callBack);
                break;
            case ActionCmd.EMV_ABORT_PBOC:
                abortPboc(callBack);
                break;
            case ActionCmd.EMV_CLEAR_KERNEL_IC_TRANS_LOG:
                clearKernelICTransLog(callBack);
                break;
            case ActionCmd.EMV_READ_KERNEL_DATA:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String tagLists = args.optString(0, NULL_STR);
                    String outBuffers = args.optString(1, NULL_STR);
                    if (NULL_STR.equals(tagLists) || NULL_STR.equals(outBuffers)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] tagList = HexUtil.hexStringToByte(tagLists);
                        byte[] outBuffer = HexUtil.hexStringToByte(outBuffers);
                        readKernelData(tagList, outBuffer, callBack);
                    }
                }
                break;
            case ActionCmd.EMV_SET_TLV:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int index = args.optInt(0, Integer.MIN_VALUE);
                    String datas = args.optString(1, NULL_STR);
                    if (index == Integer.MIN_VALUE || NULL_STR.equals(datas)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] data = HexUtil.hexStringToByte(datas);
                        setTLV(index, data, callBack);
                    }
                }
                break;
            case ActionCmd.EMV_PARSE_TLV:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String tag = args.optString(0, NULL_STR);
                    String data = args.optString(1, NULL_STR);
                    if (NULL_STR.equals(tag) || NULL_STR.equals(data)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        parseTLV(tag, data, callBack);
                    }
                }
                break;
            case ActionCmd.EMV_IMPORT_AMOUNT:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String amount = args.optString(0, NULL_STR);
                    if (NULL_STR.equals(amount)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        importAmount(amount, callBack);
                    }
                }
                break;
            case ActionCmd.EMV_IMPORT_AID_SELECT_RES:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int index = args.optInt(0, Integer.MIN_VALUE);
                    if (index == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        importAidSelectResult(index, callBack);
                    }
                }
                break;
            case ActionCmd.EMV_IMPORT_CONFIRM_CARD_INFO_RES:
                if (ParamUtil.checkParameters(callBack, args)) {
                    boolean success = args.optBoolean(0, false);
                    importConfirmCardInfoRes(success, callBack);
                }
                break;
            case ActionCmd.EMV_IMPORT_PIN:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String pin = args.optString(0, NULL_STR);
                    if (NULL_STR.equals(pin)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        importPin(pin, callBack);
                    }
                }
                break;
            case ActionCmd.EMV_IMPORT_USER_AUTH_RES:
                if (ParamUtil.checkParameters(callBack, args)) {
                    boolean success = args.optBoolean(0, false);
                    importUserAuthRes(success, callBack);
                }
                break;
            case ActionCmd.EMV_IMPORT_MSG_CONFIRM_RES:
                if (ParamUtil.checkParameters(callBack, args)) {
                    boolean success = args.optBoolean(0, false);
                    importMsgConfirmRes(success, callBack);
                }
                break;
            case ActionCmd.EMV_IMPORT_ECASH_TIP_CONFIRM_RES:
                if (ParamUtil.checkParameters(callBack, args)) {
                    boolean eCashTipConfirmResult = args.optBoolean(0, false);
                    importECashTipConfirmRes(eCashTipConfirmResult, callBack);
                }
                break;
            case ActionCmd.EMV_IMPORT_ONLINE_RESP:
                if (ParamUtil.checkParameters(callBack, args)) {
                    boolean success = args.optBoolean(0, false);
                    String code = args.optString(1, NULL_STR);
                    String result = args.optString(2, NULL_STR);
                    if (NULL_STR.equals(code) || NULL_STR.equals(result)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        importOnlineResp(success, code, result, callBack);
                    }
                }
                break;
            case ActionCmd.EMV_UPDATE_AID:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int index = args.optInt(0, Integer.MIN_VALUE);
                    String data = args.optString(1, NULL_STR);
                    if (index == Integer.MIN_VALUE || NULL_STR.equals(data)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        updateAID(index, data, callBack);
                    }
                }
                break;
            case ActionCmd.EMV_UPDATE_CAPK:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int index = args.optInt(0, Integer.MIN_VALUE);
                    String data = args.optString(1, NULL_STR);
                    if (index == Integer.MIN_VALUE || NULL_STR.equals(data)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        updateCAPK(index, data, callBack);
                    }
                }
                break;
            case ActionCmd.EMV_AID_PARAM_READ:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String indexs = args.optString(0, NULL_STR);
                    if (NULL_STR.equals(indexs)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] index = HexUtil.hexStringToByte(indexs);
                        aidParamRead(index, callBack);
                    }
                }
                break;
            case ActionCmd.EMV_CA_PUBLIC_KEY_PARAM_READ:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String indexs = args.optString(0, NULL_STR);
                    if (NULL_STR.equals(indexs)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] index = HexUtil.hexStringToByte(indexs);
                        caPublicKeyParamRead(index, callBack);
                    }
                }
                break;
            case ActionCmd.EMV_SET_PARAMETERS:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int tag = args.optInt(0, Integer.MIN_VALUE);
                    int value = args.optInt(1, Integer.MIN_VALUE);
                    if (tag == Integer.MIN_VALUE || value == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        setParam((byte) tag, (byte) value, callBack);
                    }
                }
                break;
            case ActionCmd.EMV_UPDATE_DRL_PARAMS:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int index = args.optInt(0, Integer.MIN_VALUE);
                    String datas = args.optString(1, NULL_STR);
                    if (index == Integer.MIN_VALUE || NULL_STR.equals(datas)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] data = HexUtil.hexStringToByte(datas);
                        DRLParam param = new DRLParam(data);
                        updateDrlParam(index, param, callBack);
                    }
                }
                break;
            case ActionCmd.EMV_DRL_PARAM_READ:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String datas = args.optString(0, NULL_STR);
                    if (NULL_STR.equals(datas)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] data = HexUtil.hexStringToByte(datas);
                        drlParamRead(data, callBack);
                    }
                }
                break;
            case ActionCmd.EMV_SET_IC_SLOT:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int index = args.optInt(0, Integer.MIN_VALUE);
                    if (index == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        setIcSlot((byte) index, callBack);
                    }
                }
                break;
            case ActionCmd.EMV_RESULT_OF_ISSUER_VOICE_REFERENCE:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String results = args.optString(0, NULL_STR);
                    if (NULL_STR.equals(results)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] result = HexUtil.hexStringToByte(results);
                        resultOfIssuerVoiceReference(result, callBack);
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
        device = AidlEMVL2.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_PBOC2));
        checkAIDLNotAllNull(device);
    }
}
