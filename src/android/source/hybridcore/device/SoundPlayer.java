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
import com.centerm.smartpos.aidl.soundplayer.AidlSoundPlayer;
import com.centerm.smartpos.aidl.soundplayer.AidlSoundStateChangeListener;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.constant.Constant;
import com.centerm.smartpos.constant.MapBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

/**
 * SoundPlayer
 *
 * @author qiuchunhua@centerm.com
 * @date 2018/9/30 17:45
 */
public class SoundPlayer extends BaseDeviceManager implements IDeviceAction {

    private AidlSoundPlayer soundPlayer;

    private void play(int i, CustomCallBack callBack) {
        try {
            soundPlayer.play(i);
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
        }
    }

    private void commonSound(String s, CustomCallBack callBack) {
        try {
            soundPlayer.commonSound(s, new SoundCallBack(callBack));
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
        }
    }

    private void intelligenceSound(String s, CustomCallBack callBack) {
        try {
            soundPlayer.intelligenceSound(s, new SoundCallBack(callBack));
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
        }
    }

    private void readParam(MapBean mapBean, CustomCallBack callBack) {
        try {
            soundPlayer.readParam(mapBean);
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
        }
    }

    @Override
    public boolean isAction(String action) {
        return action != null && action.startsWith(ActionCmd.SOUND);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.SOUND_INIT) && soundPlayer == null) {
            callBack.error(CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            case ActionCmd.SOUND_INIT:
                bindService(context, callBack);
                break;
            case ActionCmd.SOUND_PLAY:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int i = args.optInt(0, Integer.MIN_VALUE);
                    if (i == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                        return;
                    }
                    play(i, callBack);
                }
                break;
            case ActionCmd.SOUND_COMMON:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String s = args.optString(0);
                    if (s.isEmpty()) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                        return;
                    }
                    commonSound(s, callBack);
                }
                break;
            case ActionCmd.SOUND_CLEVER:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String s = args.optString(0);
                    if (s.isEmpty()) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                        return;
                    }
                    intelligenceSound(s, callBack);
                }
                break;
            case ActionCmd.SOUND_READ_PARAM:
                if (ParamUtil.checkParameters(callBack, args)) {
                    JSONObject object = args.optJSONObject(0);
                    if (object == null) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                        return;
                    }
                    HashMap<String, Object> map = new HashMap<>();
                    Iterator<String> it = object.keys();
                    while (it.hasNext()) {
                        String key = it.next();
                        map.put(key, object.opt(key));
                    }
                    MapBean mapBean = new MapBean();
                    mapBean.setParamMap(map);
                    readParam(mapBean, callBack);
                }
                break;
            default:
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_ACTION_ERROR);
                break;
        }
    }


    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        soundPlayer = AidlSoundPlayer.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_SOUNDPLAYER));
        checkAIDLNotAllNull(soundPlayer);
    }

    private class SoundCallBack extends AidlSoundStateChangeListener.Stub {

        private CustomCallBack callBack;

        SoundCallBack(CustomCallBack callBack) {
            this.callBack = callBack;
            //涉及到多次回调,需要此设置
            callBack.setKeepCallback(true);
        }

        @Override
        public void onSoundFinish() throws RemoteException {
            callBack.success("FINISH");
        }

        @Override
        public void onSoundError(int i) throws RemoteException {
            callBack.error(i);
        }

        @Override
        public void onSounding() throws RemoteException {
            callBack.success("SOUNDING");
        }

        @Override
        public void onSoundPause() throws RemoteException {
            callBack.success("PAUSE");
        }
    }
}
