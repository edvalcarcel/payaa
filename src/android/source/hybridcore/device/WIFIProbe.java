package com.centerm.hybridcore.device;

import android.content.Context;
import android.os.RemoteException;

import com.centerm.hybridcore.base.BaseDeviceManager;
import com.centerm.hybridcore.base.IDeviceAction;
import com.centerm.hybridcore.callback.CustomCallBack;
import com.centerm.hybridcore.constant.ActionCmd;
import com.centerm.hybridcore.constant.CommonFlag;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.aidl.wifiprobe.AidlWifiProbe;
import com.centerm.smartpos.aidl.wifiprobe.IProbeInfoCallback;
import com.centerm.smartpos.aidl.wifiprobe.IProbeStateCallback;
import com.centerm.smartpos.aidl.wifiprobe.IProbeSwitchCallback;
import com.centerm.smartpos.constant.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * WIFI探针
 *
 * @author qiuchunhua@centerm.com
 * @date 2018/9/30 17:46
 */
@Deprecated
public class WIFIProbe extends BaseDeviceManager implements IDeviceAction {

    private AidlWifiProbe device = null;

    private void openWifiProbe(CustomCallBack callBack) throws RemoteException {
        device.openWifiProbe(new WifiSwitchCallBack(callBack));
    }

    private void startGetProbeInfo(CustomCallBack callBack) throws RemoteException {
        device.startGetProbeInfo(new WifiInfoCallback(callBack));
    }

    private void stopGetProbeInfo(CustomCallBack callBack) throws RemoteException {
        device.startGetProbeInfo(new WifiInfoCallback(callBack));
    }

    private void closeWifiProbeInfo(CustomCallBack callBack) throws RemoteException {
        device.closeWifiProbeInfo(new WifiSwitchCallBack(callBack));
    }

    private void startGetProbeState(CustomCallBack callBack) throws RemoteException {
        device.startGetProbeState(new WifiStatueCallBack(callBack));
    }

    private void stopGetProbeState(CustomCallBack callBack) throws RemoteException {
        device.stopGetProbeState(new WifiStatueCallBack(callBack));
    }


    @Override
    public boolean isAction(String action) {
        return action != null && action.startsWith(ActionCmd.WIFIPROBE);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.WIFIPROBE_INIT) && device == null) {
            callBack.error(CommonFlag.STR_INIT_FIRST);
            return;
        }
        try {
            switch (action) {
                case ActionCmd.WIFIPROBE_INIT: {
                    bindService(context, callBack);
                    break;
                }
                case ActionCmd.WIFIPROBE_OPEN: {
                    openWifiProbe(callBack);
                    break;
                }
                case ActionCmd.WIFIPROBE_CLOSE: {
                    closeWifiProbeInfo(callBack);
                    break;
                }
                case ActionCmd.WIFIPROBE_START_GET_INFO: {
                    startGetProbeInfo(callBack);
                    break;
                }
                case ActionCmd.WIFIPROBE_STOP_GET_INFO: {
                    stopGetProbeInfo(callBack);
                    break;
                }
                case ActionCmd.WIFIPROBE_START_GET_STATE: {
                    startGetProbeState(callBack);
                    break;
                }
                case ActionCmd.WIFIPROBE_STOP_GET_STATE: {
                    stopGetProbeState(callBack);
                    break;
                }
                default: {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            callBack.error(CommonFlag.STR_EXCEPTION + ":" + e.getMessage());
        }
    }

    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        device = AidlWifiProbe.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_WIFIPROBE));
        checkAIDLNotAllNull(device);
    }

    private class WifiInfoCallback extends IProbeInfoCallback.Stub {

        private CustomCallBack callBack;

        WifiInfoCallback(CustomCallBack callBack) {
            this.callBack = callBack;
            callBack.setKeepCallback(true);
        }

        @Override
        public void getWifiProbeInfo(String mac, String rssi, long time) throws RemoteException {
            JSONObject obj = new JSONObject();
            try {
                obj.put("mac", mac);
                obj.put("rssi", rssi);
                obj.put("time", time);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            callBack.success(obj);
        }
    }

    private class WifiStatueCallBack extends IProbeStateCallback.Stub {
        private CustomCallBack callBack;

        WifiStatueCallBack(CustomCallBack callBack) {
            this.callBack = callBack;
            //callBack.setKeepCallback(true);
        }

        @Override
        public void updateProbeState(int i) throws RemoteException {
            callBack.success(i);
        }
    }

    private class WifiSwitchCallBack extends IProbeSwitchCallback.Stub {

        private CustomCallBack callBack;

        WifiSwitchCallBack(CustomCallBack callBack) {
            this.callBack = callBack;
            callBack.setKeepCallback(true);
        }

        @Override
        public void switchCallback(int i, String s) throws RemoteException {
            JSONObject obj = new JSONObject();
            try {
                obj.put("ret", i);
                obj.put("msg", s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            callBack.success(obj);
        }
    }
}
