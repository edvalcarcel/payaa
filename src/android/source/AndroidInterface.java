package com.centerm.cordovahelper;

import android.app.Activity;
import android.util.Log;

import com.centerm.hybridcore.base.BaseDeviceManager;
import com.centerm.hybridcore.base.IDeviceAction;
import com.centerm.hybridcore.callback.CustomCallBack;
import com.centerm.hybridcore.device.CashBox;
import com.centerm.hybridcore.device.CustomDisplayer;
import com.centerm.hybridcore.device.EmvManager;
import com.centerm.hybridcore.device.IDCardReader;
import com.centerm.hybridcore.device.IcReader;
import com.centerm.hybridcore.device.LCD;
import com.centerm.hybridcore.device.MagneticReader;
import com.centerm.hybridcore.device.Mem102CardReader;
import com.centerm.hybridcore.device.Mem1608CardReader;
import com.centerm.hybridcore.device.Mem4428CardReader;
import com.centerm.hybridcore.device.Mem4442CardReader;
import com.centerm.hybridcore.device.MifareReader;
import com.centerm.hybridcore.device.OldLED;
import com.centerm.hybridcore.device.PinpadHelper;
import com.centerm.hybridcore.device.Printer;
import com.centerm.hybridcore.device.PsamReader;
import com.centerm.hybridcore.device.RFReader;
import com.centerm.hybridcore.device.Scanner;
import com.centerm.hybridcore.device.SerialPortManager;
import com.centerm.hybridcore.device.SignHelper;
import com.centerm.hybridcore.device.SoundPlayer;
import com.centerm.hybridcore.device.SystemManager;
import com.centerm.hybridcore.device.VersionHelper;
import com.centerm.smartpos.util.CompactUtil;
import com.centerm.smartpos.util.HexUtil;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * 此处仅仅进行的管理，不具体调用接口，接口具体调用写在文件中
 *
 * @author wangwenxun
 * @date 2018/8/31
 */
public class AndroidInterface extends CordovaPlugin {

    private Activity activity;
    private static List<IDeviceAction> devices;

    static {
        devices = new LinkedList<>();
        devices.add(new CashBox());
        devices.add(new IcReader());
        devices.add(new Printer());
        devices.add(new MifareReader());
        devices.add(new RFReader());
        devices.add(new MagneticReader());
        devices.add(new SoundPlayer());
        devices.add(new SystemManager());
        devices.add(new IDCardReader());
        devices.add(new CustomDisplayer());
        devices.add(new OldLED());
        devices.add(new VersionHelper());
        devices.add(new LCD());
        devices.add(new Scanner());
        //devices.add(new WIFIProbe());
        devices.add(new SerialPortManager());
        devices.add(new SignHelper());
        devices.add(new PsamReader());
        devices.add(new Mem102CardReader());
        devices.add(new Mem1608CardReader());
        devices.add(new Mem4428CardReader());
        devices.add(new Mem4442CardReader());
        devices.add(new PinpadHelper());
        devices.add(new EmvManager());
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }


    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        if (cordova.getActivity() != null) {
            this.activity = cordova.getActivity();
            CompactUtil.instance(activity.getApplicationContext());
            setActivity(this.activity);
        }
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callback) {
        Log.d("execute", "action: " + action);
        Log.d("execute", "param: " + args.toString());
        int size = devices.size();
        for (int i = 0; i < size; i++) {
            IDeviceAction deviceAction = devices.get(i);
            if (deviceAction.isAction(action)) {
                //所有的接口都通过异步调用
                cordova.getThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        deviceAction.execute(action, args, cordova.getContext(), setCallback(callback));
                    }
                });
                return true;
            }
        }
        return false;
    }

    /**
     * 设置回调函数
     *
     * @param callbackContext Cordova的回调对象
     * @return
     */
    private CustomCallBack setCallback(CallbackContext callbackContext) {
        return new CustomCallBack() {

            //默认回调接口只能调用一次
            private boolean keep = false;

            @Override
            public void success() {
                PluginResult result = new PluginResult(PluginResult.Status.OK);
                result.setKeepCallback(keep);
                callbackContext.sendPluginResult(result);
            }

            @Override
            public void success(int number) {
                PluginResult result = new PluginResult(PluginResult.Status.OK, number);
                result.setKeepCallback(keep);
                callbackContext.sendPluginResult(result);
            }

            @Override
            public void success(String str) {
                PluginResult result = new PluginResult(PluginResult.Status.OK, str);
                result.setKeepCallback(keep);
                callbackContext.sendPluginResult(result);
            }

            @Override
            public void success(byte[] bytes) {
                String bys = HexUtil.bytesToHexString(bytes);
                PluginResult result = new PluginResult(PluginResult.Status.OK, bys);
                result.setKeepCallback(keep);
                callbackContext.sendPluginResult(result);
            }

            @Override
            public void success(JSONObject obj) {
                PluginResult result = new PluginResult(PluginResult.Status.OK, obj);
                result.setKeepCallback(keep);
                callbackContext.sendPluginResult(result);
            }

            @Override
            public void success(JSONArray jsonArray) {
                PluginResult result = new PluginResult(PluginResult.Status.OK, jsonArray);
                result.setKeepCallback(keep);
                callbackContext.sendPluginResult(result);
            }

            @Override
            public void error(String string) {
                PluginResult result = new PluginResult(PluginResult.Status.ERROR, string);
                result.setKeepCallback(keep);
                callbackContext.sendPluginResult(result);
            }

            @Override
            public void error(JSONObject jsonObject) {
                PluginResult result = new PluginResult(PluginResult.Status.ERROR, jsonObject);
                result.setKeepCallback(keep);
                callbackContext.sendPluginResult(result);
            }

            @Override
            public void error(int error) {
                PluginResult result = new PluginResult(PluginResult.Status.ERROR, error);
                result.setKeepCallback(keep);
                callbackContext.sendPluginResult(result);
            }

            @Override
            public void setKeepCallback(boolean keep) {
                this.keep = keep;
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseDeviceManager.unbindService(cordova.getActivity());
    }
}
