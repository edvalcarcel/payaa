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
import com.centerm.smartpos.aidl.magcard.AidlMagCard;
import com.centerm.smartpos.aidl.magcard.AidlMagCardListener;
import com.centerm.smartpos.aidl.magcard.TrackData;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.constant.Constant;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 磁条卡设备
 *
 * @author qiuchunhua@centerm.com
 * @date 2018/10/8 9:42
 */
public class MagneticReader extends BaseDeviceManager implements IDeviceAction {
    private AidlMagCard magCard;


    private void open(CustomCallBack callBack) {
        try {
            magCard.open();
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
        }
    }


    private void close(CustomCallBack callBack) {
        try {
            magCard.close();
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
        }
    }

    /**
     * 刷卡
     *
     * @param time     超时时间
     * @param callBack 回调
     */
    private void swipeCard(int time, CustomCallBack callBack) {
        try {
            magCard.swipeCard(time, new AidlMagCardListener.Stub() {
                @Override
                public void onSwipeCardTimeout() throws RemoteException {
                    CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_TIME_OUT);
                }

                @Override
                public void onSwipeCardException(int i) throws RemoteException {
                    CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION + ":" + i);
                }

                @Override
                public void onSwipeCardSuccess(TrackData trackData) throws RemoteException {
                    JSONObject ret = new JSONObject();
                    try {
                        ret.put("cardno", trackData.getCardno());
                        ret.put("encryptCardNo", trackData.getEncryptCardNo());
                        ret.put("firstTrackData", trackData.getFirstTrackData());
                        ret.put("secondTrackData", trackData.getSecondTrackData());
                        ret.put("thirdTrackData", trackData.getThirdTrackData());
                        ret.put("encryptTrackData", trackData.getEncryptTrackData());
                        ret.put("expiryDate", trackData.getExpiryDate());
                        ret.put("serviceCode", trackData.getServiceCode());
                        CtCallbackHelper.cbHelper(callBack, true, ret);
                    } catch (Exception e) {
                        e.printStackTrace();
                        LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }

                @Override
                public void onSwipeCardFail() throws RemoteException {
                    CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
                }

                @Override
                public void onCancelSwipeCard() throws RemoteException {
                    CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_CANCEL);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
        }
    }

    private void stopSwipeCard(CustomCallBack callBack) {
        try {
            magCard.stopSwipeCard();
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
        }
    }

    /**
     * 设置声音
     *
     * @param open     是否打开
     * @param callBack 结果回调
     */
    private void setPromptTone(boolean open, CustomCallBack callBack) {
        try {
            int i = magCard.setPromptTone(open ? 1 : 0);
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

    @Override
    public boolean isAction(String action) {
        return action != null && action.startsWith(ActionCmd.MAG);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.MAG_INIT) && magCard == null) {
            callBack.error(CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            case ActionCmd.MAG_INIT:
                bindService(context, callBack);
                break;
            case ActionCmd.MAG_OPEN:
                open(callBack);
                break;
            case ActionCmd.MAG_CLOSE:
                close(callBack);
                break;
            case ActionCmd.MAG_CLOSE_SOUND:
                setPromptTone(false, callBack);
                break;
            case ActionCmd.MAG_OPEN_SOUND:
                setPromptTone(true, callBack);
                break;
            case ActionCmd.MAG_READ:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int time = args.optInt(0, -1);
                    if (time <= 0) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        swipeCard(time, callBack);
                    }
                }
                break;
            case ActionCmd.MAG_STOP_READ:
                stopSwipeCard(callBack);
                break;
            default:
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_ACTION_ERROR);
                break;
        }
    }

    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        magCard = AidlMagCard.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_MAGCARD));
        checkAIDLNotAllNull(magCard);
    }
}
