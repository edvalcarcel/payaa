package com.centerm.hybridcore.device;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;

import com.centerm.hybridcore.base.BaseDeviceManager;
import com.centerm.hybridcore.base.IDeviceAction;
import com.centerm.hybridcore.callback.CtCallbackHelper;
import com.centerm.hybridcore.callback.CustomCallBack;
import com.centerm.hybridcore.constant.ActionCmd;
import com.centerm.hybridcore.constant.CommonFlag;
import com.centerm.hybridcore.util.LogUtil;
import com.centerm.hybridcore.util.ParamUtil;
import com.centerm.smartpos.aidl.idcard.AidlIdCard3;
import com.centerm.smartpos.aidl.idcard.IOnIDCardReadListener;
import com.centerm.smartpos.aidl.idcard.IdInfoBean;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.constant.Constant;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;


/**
 * 身份证识别接口
 *
 * @author qiuchunhua@centerm.com
 * @date 2018/9/30 17:43
 */
public class IDCardReader extends BaseDeviceManager implements IDeviceAction {

    private AidlIdCard3 device;

    private void searchIDCard(int timeout, CustomCallBack callBack) {
        try {
            device.searchIDCard(timeout, new IOnIDCardReadListener.Stub() {
                @Override
                public void onFindIDCard(IdInfoBean id) {
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("name", id.getName());
                        obj.put("sex", id.getSex());
                        obj.put("mz", id.getMz());
                        obj.put("birth", id.getBirth());
                        obj.put("address", id.getAddress());
                        obj.put("id", id.getId());
                        obj.put("createOrg", id.getCreateOrg());
                        obj.put("validateBegin", id.getValidateBegin());
                        obj.put("vaiidateEnd", id.getVaiidateEnd());
                        obj.put("newAdd", id.getNewAdd());
                        Bitmap bmp = id.getPicture();
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        //100保留原质量
                        bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                        //返回图片的BASE64
                        obj.put("picture", Base64.encode(bos.toByteArray(), Base64.NO_WRAP));
                        CtCallbackHelper.cbHelper(callBack, true, obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                        LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }

                @Override
                public void onTimeout() {
                    CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_TIME_OUT);
                }

                @Override
                public void onError(int i, String s) {
                    CtCallbackHelper.cbHelper(callBack, false, i);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void stopSearch(CustomCallBack callBack) {
        try {
            device.stopSearch();
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void register(CustomCallBack callBack) {
        try {
            boolean flag = device.isRegister();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_TRUE);
            } else {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_FALSE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }


    @Override
    public boolean isAction(String action) {
        return action != null && action.startsWith(ActionCmd.IDCARD);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.CASH_BOX_INIT) && device == null) {
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            case ActionCmd.IDCARD_INIT:
                bindService(context, callBack);
                break;
            case ActionCmd.IDCARD_START_SEARCH:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int timeout = args.optInt(0, -1);
                    if (timeout <= 0) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        searchIDCard(timeout, callBack);
                    }
                }
                break;
            case ActionCmd.IDCARD_STOP_SEARCH:
                stopSearch(callBack);
                break;
            case ActionCmd.IDCARD_ISREGIST:
                register(callBack);
                break;
            default:
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_ACTION_ERROR);
                break;
        }
    }

    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        device = AidlIdCard3.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_RF_IDCARD));
        checkAIDLNotAllNull(device);
    }
}
