package com.centerm.hybridcore.device;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;

import com.centerm.hybridcore.base.BaseDeviceManager;
import com.centerm.hybridcore.base.IDeviceAction;
import com.centerm.hybridcore.callback.CtCallbackHelper;
import com.centerm.hybridcore.callback.CustomCallBack;
import com.centerm.hybridcore.constant.ActionCmd;
import com.centerm.hybridcore.constant.CommonFlag;
import com.centerm.hybridcore.util.LogUtil;
import com.centerm.hybridcore.util.ParamUtil;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.aidl.sys.AidlSystemSettingService;
import com.centerm.smartpos.aidl.sys.ApnNode;
import com.centerm.smartpos.aidl.sys.ApnNodeNew;
import com.centerm.smartpos.aidl.sys.IPackageInstallListener;
import com.centerm.smartpos.aidl.sys.IPackageUnInstallListener;
import com.centerm.smartpos.aidl.sys.TouchListener;
import com.centerm.smartpos.constant.Constant;
import com.centerm.smartpos.util.HexUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static com.centerm.hybridcore.constant.CommonFlag.STR_EXCEPTION;
import static com.centerm.hybridcore.constant.CommonFlag.STR_FAIL;
import static com.centerm.hybridcore.constant.CommonFlag.STR_OBJECT_NULL;
import static com.centerm.hybridcore.constant.CommonFlag.STR_RESULT_NULL;
import static com.centerm.hybridcore.constant.CommonFlag.STR_SUCCESS;

/**
 * @author qiuchunhua@centerm.com
 * @date 2018/9/30 17:45
 */
public class SystemManager extends BaseDeviceManager implements IDeviceAction {

    private AidlSystemSettingService device;

    private void writeStorageLog(int index, byte[] data, CustomCallBack callBack) {
        try {
            int res = device.writeStorageLog(index, data);
            CtCallbackHelper.cbHelper(callBack, true, res);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void readStorageLog(int index, String data, CustomCallBack callBack) {
        try {
            String imei = device.readStorageLog(index, data);
            CtCallbackHelper.cbHelper(callBack, true, imei);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }


    private void getImei(CustomCallBack callBack) {
        try {
            String imei = device.getIMEI();
            CtCallbackHelper.cbHelper(callBack, true, imei);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void getImsi(CustomCallBack callBack) {
        try {
            String imsi = device.getIMSI();
            CtCallbackHelper.cbHelper(callBack, true, imsi);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void flush(CustomCallBack callBack) {
        try {
            boolean flag = device.flush();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void getSdkVersion(CustomCallBack callBack) {
        try {
            String version = device.getCurSdkVer();
            CtCallbackHelper.cbHelper(callBack, true, version);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void getBsVersion(CustomCallBack callBack) {
        try {
            String version = device.getCurrBs();
            callBack.success(version);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void openHid(CustomCallBack callBack) {
        try {
            device.openHID();
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void closeHid(CustomCallBack callBack) {
        try {
            device.closeHID();
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void openWifi(CustomCallBack callBack) {
        try {
            device.openWifi();
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void closeWifi(CustomCallBack callBack) {
        try {
            device.closeWifi();
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void powerReboot(CustomCallBack callBack) {
        try {
            device.powerReboot();
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void openMultiTouch(CustomCallBack callBack) {
        try {
            device.openMultitouch();
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void closeMultiTouch(CustomCallBack callBack) {
        try {
            device.closeMultitouch();
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void startApp(String pkg, CustomCallBack callBack) {
        try {
            boolean flag = device.startApp(pkg);
            CtCallbackHelper.cbHelper(callBack, flag, flag + "");
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void getPsamNo(CustomCallBack callBack) {
        try {
            String psamNo = device.getPsamNo();
            if (psamNo != null) {
                CtCallbackHelper.cbHelper(callBack, true, psamNo);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_RESULT_NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void setSystemTime(JSONArray args, CustomCallBack callBack) {
        try {
            JSONObject obj = args.optJSONObject(0);
            if (obj == null) {
                callBack.error(CommonFlag.STR_INVALID_PARAM);
                return;
            }
            Calendar calendar = Calendar.getInstance();
            //未设置某个域的参数则获取当前时间值作为参数
            int second = obj.optInt("second", calendar.get(Calendar.SECOND));
            int minute = obj.optInt("minute", calendar.get(Calendar.MINUTE));
            int hour = obj.optInt("hour", calendar.get(Calendar.HOUR_OF_DAY));
            int day = obj.optInt("day", calendar.get(Calendar.DAY_OF_MONTH));
            // Calendar返回的月份数字从0开始,故需要加一
            int month = obj.optInt("month", calendar.get(Calendar.MONTH) + 1);
            int year = obj.optInt("year", calendar.get(Calendar.YEAR));
            boolean flag = device.setSystemTime(second, minute, hour, day, month, year);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private TouchListener mTouchListener = null;

    private void registerTouchListener(CustomCallBack callBack) {
        try {
            callBack.setKeepCallback(true);
            if (mTouchListener == null) {
                mTouchListener = new TouchListener.Stub() {
                    @Override
                    public void onTouch(int i) throws RemoteException {
                        CtCallbackHelper.cbHelper(callBack, true, i);
                    }

                    @Override
                    public void onError() throws RemoteException {
                        CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
                    }
                };
                device.registerTouchListener(mTouchListener);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void unRegisterTouchListener(CustomCallBack callBack) {
        try {
            if (mTouchListener != null) {
                device.unregisterTouchListener(mTouchListener);
                mTouchListener = null;
            } else {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void mount(CustomCallBack callBack) {
        try {
            boolean flag = device.mount();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void unmount(CustomCallBack callBack) {
        try {
            boolean flag = device.umount();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void mountUsb(String devId, String inPoint, CustomCallBack callBack) {
        try {
            boolean flag = device.mountUsb(devId, inPoint);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void uninstallApkBack(String pkg, CustomCallBack callBack) {
        try {
            device.uninstallApkBack(pkg, new IPackageUnInstallListener.Stub() {
                @Override
                public void onRemoveFinished() throws RemoteException {
                    CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
                }

                @Override
                public void onRemoveError(int i) throws RemoteException {
                    CtCallbackHelper.cbHelper(callBack, false, "ERROR" + i);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }


    private void installApkBack(String pkg, CustomCallBack callBack) {
        try {
            device.installApkBack(pkg, new IPackageInstallListener.Stub() {
                @Override
                public void onInstallFinished() throws RemoteException {
                    CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
                }

                @Override
                public void onInstallError(int i) throws RemoteException {
                    CtCallbackHelper.cbHelper(callBack, false, "ERROR" + i);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void getMacAddress(CustomCallBack callBack) {
        try {
            String address = device.getLocalMacAddress();
            if (address != null) {
                CtCallbackHelper.cbHelper(callBack, true, address);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_OBJECT_NULL);
            }
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void getIpAddress(CustomCallBack callBack) {
        try {
            String address = device.getLocalIpAddress();
            if (address != null) {
                CtCallbackHelper.cbHelper(callBack, true, address);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_OBJECT_NULL);
            }
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void setVpdn(JSONArray args, CustomCallBack callBack) {
        try {
            String name = args.optString(0);
            String s = args.optString(1);
            boolean flag = device.setVPDN(name, s);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(SystemManager.class, e);

        }
    }

    private void setLed(byte color, int time, int interval, CustomCallBack callBack) {
        try {
            device.setLed(color, time, interval);
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(SystemManager.class, e);

        }
    }

    private void setDeafultApn(int index, CustomCallBack callBack) {
        try {
            boolean flag = device.setDefaultAPN(index);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(SystemManager.class, e);

        }
    }

    private void readSerialNum(CustomCallBack callBack) {
        try {
            String serialNum = device.readSerialNum();
            CtCallbackHelper.cbHelper(callBack, true, serialNum);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(SystemManager.class, e);

        }
    }


    private void insertNewCustomApn(JSONArray args, CustomCallBack callBack) {
        try {
            JSONObject obj = args.optJSONObject(0);
            ApnNodeNew apnNodeNew = new ApnNodeNew();
            if (obj == null) {
                callBack.error(CommonFlag.STR_INVALID_PARAM + ":miss args");
                return;
            }
            apnNodeNew.setName(obj.optString("name"));
            apnNodeNew.setApn(obj.optString("apn"));
            apnNodeNew.setProxy(obj.optString("proxy"));
            apnNodeNew.setPort(obj.optString("port"));
            apnNodeNew.setUser(obj.optString("user"));
            apnNodeNew.setServer(obj.optString("server"));
            apnNodeNew.setPassword(obj.optString("password"));
            apnNodeNew.setMmsc(obj.optString("mmsc"));
            apnNodeNew.setMmsproxy(obj.optString("mmsproxy"));
            apnNodeNew.setMmsport(obj.optString("mmsport"));
            apnNodeNew.setMcc(obj.optString("mcc"));
            apnNodeNew.setMnc(obj.optString("mnc"));
            apnNodeNew.setNumeric(obj.optString("numeric"));
            apnNodeNew.setType(obj.optString("type"));
            apnNodeNew.setProtocol(obj.optString("protocol"));
            apnNodeNew.setRoamingProtocol(obj.optString("roamingProtocol"));
            apnNodeNew.setMvnoType(obj.optString("mvnoType"));
            apnNodeNew.setMvnoMatchData(obj.optString("mvnoMatchData"));
            apnNodeNew.setId(obj.optInt("id"));
            apnNodeNew.setBearer(obj.optInt("bearer"));
            apnNodeNew.setAuthtype(obj.optInt("authtype"));
            apnNodeNew.setCarrierEnabled(obj.optInt("carrierEnabled"));
            JSONObject ojmap = obj.optJSONObject("map");
            if (ojmap != null) {
                Iterator<String> iterator = ojmap.keys();
                HashMap<String, Object> map = new HashMap<>(ojmap.length());
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    map.put(key, obj.opt(key));
                }
                apnNodeNew.setMap(map);
            }
            int res = device.insertNewCustomAPN(apnNodeNew);
            CtCallbackHelper.cbHelper(callBack, true, res);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(SystemManager.class, e);

        }
    }

    private void insertCustomApn(JSONArray args, CustomCallBack callBack) {
        try {
            ApnNode apnNode = new ApnNode();
            JSONObject obj = args.optJSONObject(0);
            if (obj == null) {
                callBack.error(CommonFlag.STR_INVALID_PARAM);
                return;
            }
            apnNode.setApn(obj.optString("apn"));
            apnNode.setId(obj.optInt("id"));
            apnNode.setMcc(obj.optString("mcc"));
            apnNode.setMmsc(obj.optString("mmsc"));
            apnNode.setMmsport(obj.optString("mmsport"));
            apnNode.setName(obj.optString("name"));
            apnNode.setMmsproxy(obj.optString("mmsproxy"));
            apnNode.setMnc(obj.optString("mnc"));
            apnNode.setProxy(obj.optString("proxy"));
            apnNode.setPort(obj.optString("port"));
            apnNode.setUser(obj.optString("user"));
            apnNode.setServer(obj.optString("server"));
            apnNode.setPassword(obj.optString("password"));
            boolean flag = device.insertCustomAPN(apnNode);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(SystemManager.class, e);

        }
    }

    private void insertApn(JSONArray args, CustomCallBack callBack) {
        try {
            if (args == null || args.length() == 0) {
                callBack.error(CommonFlag.STR_INVALID_PARAM);
                return;
            }
            String s1 = args.optString(0);
            String s2 = args.optString(1);
            String s3 = args.optString(2);
            boolean flag = device.insertAPN(s1, s2, s3);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(SystemManager.class, e);

        }
    }

    private void installApk(String path, CustomCallBack callBack) {
        try {
            boolean flag = device.installApk(path);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(SystemManager.class, e);

        }
    }

    private void uninstallApk(String pkg, CustomCallBack callBack) {
        try {
            device.uninstallApk(pkg);
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(SystemManager.class, e);

        }
    }

    private void getNeighborBsList(CustomCallBack callBack) {
        try {
            List<String> neighborBsList = device.getNeighborBsList();
            if (neighborBsList == null) {
                callBack.success("");
                return;
            }
            JSONArray jsonArray = new JSONArray();
            for (String str : neighborBsList) {
                jsonArray.put(str);
            }
            CtCallbackHelper.cbHelper(callBack, true, jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(SystemManager.class, e);

        }
    }

    private void getDeafultApn(CustomCallBack callBack) {
        try {
            ApnNode apnNode = device.getDefaultApn();
            JSONObject obj = new JSONObject();
            try {
                obj.put("id", apnNode.getId());
                obj.put("name", apnNode.getName());
                obj.put("apn", apnNode.getApn());
                obj.put("proxy", apnNode.getProxy());
                obj.put("part", apnNode.getPort());
                obj.put("user", apnNode.getUser());
                obj.put("server", apnNode.getServer());
                obj.put("password", apnNode.getPassword());
                obj.put("mmsc", apnNode.getMmsc());
                obj.put("mmsproxy", apnNode.getMmsproxy());
                obj.put("mmsport", apnNode.getMmsport());
                obj.put("mcc", apnNode.getMcc());
                obj.put("mnc", apnNode.getMnc());
                obj.put("numeric", apnNode.getNumeric());
                obj.put("type", apnNode.getType());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            CtCallbackHelper.cbHelper(callBack, true, obj);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(SystemManager.class, e);

        }
    }

    private void getApnId(JSONArray args, CustomCallBack callBack) {
        try {
            String s = args.optString(0);
            int[] apnid = device.getAPNID(s);
            JSONArray jsonArray = new JSONArray();
            for (int apn : apnid) {
                jsonArray.put(apn);
            }
            CtCallbackHelper.cbHelper(callBack, true, jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(SystemManager.class, e);

        }
    }

    private void deleteApn(int index, CustomCallBack callBack) {
        try {
            boolean flag = device.deleteApn(index);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(SystemManager.class, e);

        }
    }

    private void checkCustomKey(String key, int index, CustomCallBack callBack) {
        try {
            boolean flag = device.CheckCustomKey(key, index);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(SystemManager.class, e);

        }
    }

    @Override
    public boolean isAction(String action) {
        return action != null && action.startsWith(ActionCmd.SYSTEM);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.SYSTEM_INIT) && device == null) {
            callBack.error(CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            case ActionCmd.SYSTEM_INIT: {
                bindService(context, callBack);
                break;
            }
            case ActionCmd.SYSTEM_OPEN_HID: {
                openHid(callBack);
                break;
            }
            case ActionCmd.SYSTEM_CLOSE_HID: {
                closeHid(callBack);
                break;
            }
            case ActionCmd.SYSTEM_OPEN_WIFI: {
                openWifi(callBack);
                break;
            }
            case ActionCmd.SYSTEM_CLOSE_WIFI: {
                closeWifi(callBack);
                break;
            }
            case ActionCmd.SYSTEM_SET_DEFAULT_APN: {
                if (ParamUtil.checkParameters(callBack, args)) {
                    int index = args.optInt(0, Integer.MIN_VALUE);
                    if (index == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        setDeafultApn(index, callBack);
                    }
                }
                break;
            }
            case ActionCmd.SYSTEM_DELETE_APN: {
                if (ParamUtil.checkParameters(callBack, args)) {
                    int index = args.optInt(0, Integer.MIN_VALUE);
                    if (index == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        deleteApn(index, callBack);
                    }
                }
                break;
            }
            case ActionCmd.SYSTEM_FLUSH: {
                flush(callBack);
                break;
            }
            case ActionCmd.SYSTEM_GET_CURRBS: {
                getBsVersion(callBack);
                break;
            }
            case ActionCmd.SYSTEM_GET_NEIGHBOR_BS_LIST: {
                getNeighborBsList(callBack);
                break;
            }
            case ActionCmd.SYSTEM_INSERT_APN: {
                insertApn(args, callBack);
                break;
            }
            case ActionCmd.SYSTEM_INSTALL_APK: {
                if (ParamUtil.checkParameters(callBack, args)) {
                    String path = args.optString(0, NULL_STR);
                    if (NULL_STR.equals(path)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        installApk(path, callBack);
                    }
                }
                break;
            }
            case ActionCmd.SYSTEM_UNINSTALL_APK: {
                if (ParamUtil.checkParameters(callBack, args)) {
                    String pkg = args.optString(0, NULL_STR);
                    if (NULL_STR.equals(pkg)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        uninstallApk(pkg, callBack);
                    }
                }
                break;
            }
            case ActionCmd.SYSTEM_INSTALL_APK_BACK: {
                if (ParamUtil.checkParameters(callBack, args)) {
                    String pkg = args.optString(0, NULL_STR);
                    if (NULL_STR.equals(pkg)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        installApkBack(pkg, callBack);
                    }
                }
                break;
            }
            case ActionCmd.SYSTEM_UNINSTALL_APK_BACK: {
                if (ParamUtil.checkParameters(callBack, args)) {
                    String pkg = args.optString(0, NULL_STR);
                    if (NULL_STR.equals(pkg)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        uninstallApkBack(pkg, callBack);
                    }
                }
                break;
            }
            case ActionCmd.SYSTEM_MOUNT: {
                mount(callBack);
                break;
            }
            case ActionCmd.SYSTEM_UNMOUNT: {
                unmount(callBack);
                break;
            }
            case ActionCmd.SYSTEM_MOUNT_USB: {
                if (ParamUtil.checkParameters(callBack, args)) {
                    String devId = args.optString(0, NULL_STR);
                    String inPoint = args.optString(1, NULL_STR);
                    if (NULL_STR.equals(devId) || NULL_STR.equals(inPoint)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        mountUsb(devId, inPoint, callBack);
                    }
                }
                break;
            }
            case ActionCmd.SYSTEM_SET_SYSTEM_TIME: {
                setSystemTime(args, callBack);
                break;
            }
            case ActionCmd.SYSTEM_SET_VPDN: {
                setVpdn(args, callBack);
                break;
            }
            case ActionCmd.SYSTEM_START_APP: {
                if (ParamUtil.checkParameters(callBack, args)) {
                    String pkg = args.optString(0, NULL_STR);
                    if (NULL_STR.equals(pkg)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        startApp(pkg, callBack);
                    }
                }
                break;
            }
            case ActionCmd.SYSTEM_GET_IP_ADDRESS: {
                getIpAddress(callBack);
                break;
            }
            case ActionCmd.SYSTEM_GET_MAC_ADDRESS: {
                getMacAddress(callBack);
                break;
            }
            case ActionCmd.SYSTEM_GET_IMEI: {
                getImei(callBack);
                break;
            }
            case ActionCmd.SYSTEM_GET_IMSI: {
                getImsi(callBack);
                break;
            }
            case ActionCmd.SYSTEM_GET_PSAMNO: {
                getPsamNo(callBack);
                break;
            }
            case ActionCmd.SYSTEM_GET_SERIAL_NUM: {
                readSerialNum(callBack);
                break;
            }
            case ActionCmd.SYSTEM_POWER_REBOT: {
                powerReboot(callBack);
                break;
            }
            case ActionCmd.SYSTEM_OPEN_MULTI_TOUCH: {
                openMultiTouch(callBack);
                break;
            }
            case ActionCmd.SYSTEM_CLOSE_MULTI_TOUCH: {
                closeMultiTouch(callBack);
                break;
            }
            case ActionCmd.SYSTEM_REGIST_TOUCH_LISTERNER: {
                registerTouchListener(callBack);
                break;
            }
            case ActionCmd.SYSTEM_UNREGIST_TOUCH_LISTERNER: {
                unRegisterTouchListener(callBack);
                break;
            }
            case ActionCmd.SYSTEM_GET_DEFAUT_APN: {
                getDeafultApn(callBack);
                break;
            }
            case ActionCmd.SYSTEM_GET_APNID: {
                getApnId(args, callBack);
                break;
            }
            case ActionCmd.SYSTEM_GET_SDK_VERSION: {
                getSdkVersion(callBack);
                break;
            }
            case ActionCmd.SYSTEM_READ_STORAGE_LOG: {
                if (ParamUtil.checkParameters(callBack, args)) {
                    int index = args.optInt(0, Integer.MIN_VALUE);
                    String data = args.optString(0);
                    if (index == Integer.MIN_VALUE || TextUtils.isEmpty(data.trim())) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        readStorageLog(index, data, callBack);
                    }
                }
                break;
            }
            case ActionCmd.SYSTEM_WRITE_STORAGE_LOG: {
                if (ParamUtil.checkParameters(callBack, args)) {
                    int index = args.optInt(0, Integer.MIN_VALUE);
                    String datas = args.optString(0);
                    if (index == Integer.MIN_VALUE || TextUtils.isEmpty(datas.trim())) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] data = HexUtil.hexStringToByte(datas);
                        writeStorageLog(index, data, callBack);
                    }
                }
                break;
            }
            case ActionCmd.SYSTEM_INSERT_CUSTOM_APN: {
                insertCustomApn(args, callBack);
                break;
            }
            case ActionCmd.SYSTEM_INSERT_NEW_CUSTOM_APN: {
                insertNewCustomApn(args, callBack);
                break;
            }
            case ActionCmd.SYSTEM_CHECK_CUSTOM_KEY: {
                if (ParamUtil.checkParameters(callBack, args)) {
                    String key = args.optString(0, NULL_STR);
                    int index = args.optInt(0, Integer.MIN_VALUE);
                    if (index == Integer.MIN_VALUE || NULL_STR.equals(key)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        checkCustomKey(key, index, callBack);
                    }
                }
                break;
            }
            case ActionCmd.SYSTEM_SET_LED: {
                if (ParamUtil.checkParameters(callBack, args)) {
                    int color = args.optInt(0, Integer.MIN_VALUE);
                    int time = args.optInt(1, Integer.MIN_VALUE);
                    int inteval = args.optInt(2, Integer.MIN_VALUE);
                    if (color == Integer.MIN_VALUE || time == Integer.MIN_VALUE || inteval == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        setLed((byte) color, time, inteval, callBack);
                    }
                }
                break;
            }
            default:
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_ACTION_ERROR);
                break;
        }
    }

    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        device = AidlSystemSettingService.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_SYS));
        checkAIDLNotAllNull(device);
    }
}
