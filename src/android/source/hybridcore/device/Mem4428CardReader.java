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
import com.centerm.smartpos.aidl.memcard.AidlMem4428;
import com.centerm.smartpos.aidl.memcard.AidlVerticalMem4428;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.constant.Constant;
import com.centerm.smartpos.util.HexUtil;

import org.json.JSONArray;

import static com.centerm.hybridcore.constant.CommonFlag.STR_EXCEPTION;
import static com.centerm.hybridcore.constant.CommonFlag.STR_FAIL;
import static com.centerm.hybridcore.constant.CommonFlag.STR_SUCCESS;

/**
 * @author wangwenxun@centerm.com
 * @date 2018/9/3 10:30
 */
public class Mem4428CardReader extends BaseDeviceManager implements IDeviceAction {

    private AidlMem4428 aidlMem4428;
    private AidlVerticalMem4428 aidlVerticalMem4428;

    private void open(CustomCallBack callBack) {
        try {
            boolean flag = aidlMem4428.open();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4428CardReader.class, e);
        }
    }

    private void close(CustomCallBack callBack) {
        try {
            boolean flag = aidlMem4428.close();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4428CardReader.class, e);
        }
    }

    private void reset(CustomCallBack callBack) {
        try {
            boolean flag = aidlMem4428.reset();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4428CardReader.class, e);
        }
    }

    private void status(CustomCallBack callBack) {
        try {
            int status = aidlMem4428.status();
            CtCallbackHelper.cbHelper(callBack, true, status);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4428CardReader.class, e);
        }
    }

    private void verifyPsw(byte[] psw, CustomCallBack callBack) {
        try {
            boolean flag = aidlMem4428.verifyPsw(psw);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4428CardReader.class, e);
        }
    }

    private void protectRead(int blockNum, int length, CustomCallBack callBack) {
        try {
            byte[] res = aidlMem4428.protectRead(blockNum, length);
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4428CardReader.class, e);
        }
    }

    private void protectWrite(int blockNum, byte[] data, CustomCallBack callBack) {
        try {
            boolean flag = aidlMem4428.protectWrite(blockNum, data);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4428CardReader.class, e);
        }
    }

    private void noProtectRead(int blockNum, int length, CustomCallBack callBack) {
        try {
            byte[] res = aidlMem4428.noProtectRead(blockNum, length);
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4428CardReader.class, e);
        }
    }

    private void noProtectWrite(int blockNum, byte[] data, CustomCallBack callBack) {
        try {
            boolean flag = aidlMem4428.noProtectWrite(blockNum, data);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4428CardReader.class, e);
        }
    }


    private void verticalOpen(CustomCallBack callBack) {
        try {
            boolean flag = aidlVerticalMem4428.open();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4428CardReader.class, e);
        }
    }

    private void verticalClose(CustomCallBack callBack) {
        try {
            boolean flag = aidlVerticalMem4428.close();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4428CardReader.class, e);
        }
    }

    private void verticalReset(CustomCallBack callBack) {
        try {
            boolean flag = aidlVerticalMem4428.reset();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4428CardReader.class, e);
        }
    }

    private void verticalStatus(CustomCallBack callBack) {
        try {
            int status = aidlVerticalMem4428.status();
            CtCallbackHelper.cbHelper(callBack, true, status);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4428CardReader.class, e);
        }
    }

    private void verticalVerifyPsw(byte[] psw, CustomCallBack callBack) {
        try {
            boolean flag = aidlVerticalMem4428.verifyPsw(psw);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4428CardReader.class, e);
        }
    }

    private void verticalProtectRead(int blockNum, int length, CustomCallBack callBack) {
        try {
            byte[] res = aidlVerticalMem4428.protectRead(blockNum, length);
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4428CardReader.class, e);
        }
    }

    private void verticalProtectWrite(int blockNum, byte[] data, CustomCallBack callBack) {
        try {
            boolean flag = aidlVerticalMem4428.protectWrite(blockNum, data);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4428CardReader.class, e);
        }
    }

    private void verticalNoProtectRead(int blockNum, int length, CustomCallBack callBack) {
        try {
            byte[] res = aidlVerticalMem4428.noProtectRead(blockNum, length);
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4428CardReader.class, e);
        }
    }

    private void verticalNoProtectWrite(int blockNum, byte[] data, CustomCallBack callBack) {
        try {
            boolean flag = aidlVerticalMem4428.noProtectWrite(blockNum, data);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4428CardReader.class, e);
        }
    }

    private boolean init = false;

    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        aidlMem4428 = AidlMem4428.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_MEM4428));
        aidlVerticalMem4428 = AidlVerticalMem4428.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_VERTICAL_MEM4428));
        init = checkAIDLNotAllNull(aidlVerticalMem4428, aidlMem4428);
    }

    @Override
    public boolean isAction(String action) {
        return action != null && action.startsWith(ActionCmd.MEM4428);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.MEM4428_INIT) && !init) {
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            case ActionCmd.MEM4428_INIT:
                bindService(context, callBack);
                break;
            case ActionCmd.MEM4428_OPEN:
                open(callBack);
                break;
            case ActionCmd.MEM4428_CLOSE:
                close(callBack);
                break;
            case ActionCmd.MEM4428_STATUS:
                status(callBack);
                break;
            case ActionCmd.MEM4428_RESET:
                reset(callBack);
                break;
            case ActionCmd.MEM4428_VERIFY_PWD:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String psws = args.optString(0, NULL_STR);
                    if (NULL_STR.equals(psws)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] psw = HexUtil.hexStringToByte(psws);
                        verifyPsw(psw, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4428_NO_PROTECT_READ:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    int length = args.optInt(1, Integer.MIN_VALUE);
                    if (blockNum == Integer.MIN_VALUE || length == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        noProtectRead(blockNum, length, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4428_PEOTECT_READ:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    int length = args.optInt(1, Integer.MIN_VALUE);
                    if (blockNum == Integer.MIN_VALUE || length == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        protectRead(blockNum, length, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4428_NO_PROTECT_WRITE:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    String datas = args.optString(1, NULL_STR);
                    if (blockNum == Integer.MIN_VALUE || NULL_STR.equals(datas)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] data = HexUtil.hexStringToByte(datas);
                        noProtectWrite(blockNum, data, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4428_PROTECT_WRITE:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    String datas = args.optString(1, NULL_STR);
                    if (blockNum == Integer.MIN_VALUE || NULL_STR.equals(datas)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] data = HexUtil.hexStringToByte(datas);
                        protectWrite(blockNum, data, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4428_VERTICAL_OPEN:
                verticalOpen(callBack);
                break;
            case ActionCmd.MEM4428_VERTICAL_CLOSE:
                verticalClose(callBack);
                break;
            case ActionCmd.MEM4428_VERTICAL_STATUS:
                verticalStatus(callBack);
                break;
            case ActionCmd.MEM4428_VERTICAL_RESET:
                verticalReset(callBack);
                break;
            case ActionCmd.MEM4428_VERTICAL_VERIFY_PWD:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String psws = args.optString(0, NULL_STR);
                    if (NULL_STR.equals(psws)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] psw = HexUtil.hexStringToByte(psws);
                        verticalVerifyPsw(psw, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4428_VERTICAL_NO_PROTECT_READ:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    int length = args.optInt(1, Integer.MIN_VALUE);
                    if (blockNum == Integer.MIN_VALUE || length == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        verticalNoProtectRead(blockNum, length, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4428_VERTICAL_PEOTECT_READ:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    int length = args.optInt(1, Integer.MIN_VALUE);
                    if (blockNum == Integer.MIN_VALUE || length == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        verticalProtectRead(blockNum, length, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4428_VERTICAL_NO_PROTECT_WRITE:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    String datas = args.optString(1, NULL_STR);
                    if (blockNum == Integer.MIN_VALUE || NULL_STR.equals(datas)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] data = HexUtil.hexStringToByte(datas);
                        verticalNoProtectWrite(blockNum, data, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4428_VERTICAL_PROTECT_WRITE:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    String datas = args.optString(1, NULL_STR);
                    if (blockNum == Integer.MIN_VALUE || NULL_STR.equals(datas)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] data = HexUtil.hexStringToByte(datas);
                        verticalProtectWrite(blockNum, data, callBack);
                    }
                }
                break;
            default:
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_ACTION_ERROR);
                break;
        }
    }
}
