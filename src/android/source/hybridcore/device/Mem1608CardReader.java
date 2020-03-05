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
import com.centerm.smartpos.aidl.memcard.AidlMem1608;
import com.centerm.smartpos.aidl.memcard.AidlVerticalMem1608;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.constant.Constant;
import com.centerm.smartpos.util.HexUtil;

import org.json.JSONArray;

import static com.centerm.hybridcore.constant.CommonFlag.STR_EXCEPTION;
import static com.centerm.hybridcore.constant.CommonFlag.STR_FAIL;
import static com.centerm.hybridcore.constant.CommonFlag.STR_SUCCESS;

/**
 * @author wangwenxun@centerm.com
 * @date 2018/10/23 18:30
 */
public class Mem1608CardReader extends BaseDeviceManager implements IDeviceAction {

    private AidlMem1608 mem1608;
    private AidlVerticalMem1608 vMem1608;


    private void open(CustomCallBack callBack) {
        try {
            boolean flag = mem1608.open();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem1608CardReader.class, e);
        }
    }

    private void reset(CustomCallBack callBack) {
        try {
            boolean flag = mem1608.reset();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem1608CardReader.class, e);
        }
    }

    private void status(CustomCallBack callBack) {
        try {
            byte status = mem1608.status();
            CtCallbackHelper.cbHelper(callBack, true, status);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem1608CardReader.class, e);
        }
    }

    private void read(byte area, byte location, int length, CustomCallBack callBack) {
        try {
            byte[] res = mem1608.read(area, location, length);
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem1608CardReader.class, e);
        }
    }

    private void readAccessAuthority(CustomCallBack callBack) {
        try {
            byte[] res = mem1608.readAccessAuthority();
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem1608CardReader.class, e);
        }
    }

    private void readPswErrorCounter(byte sArea, byte type, CustomCallBack callBack) {
        try {
            byte[] res = mem1608.readPswErrorCounter(sArea, type);
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem1608CardReader.class, e);
        }
    }

    private void write(byte sArea, byte location, byte[] data, CustomCallBack callBack) {
        try {
            boolean result = mem1608.write(sArea, location, data);
            if (result) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem1608CardReader.class, e);
        }
    }

    private void verifyPsw(byte sArea, byte type, byte[] psw, CustomCallBack callBack) {
        try {
            boolean res = mem1608.verifyPsw(sArea, type, psw);
            if (res) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem1608CardReader.class, e);
        }
    }

    private void close(CustomCallBack callBack) {
        try {
            boolean res = mem1608.close();
            if (res) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem1608CardReader.class, e);
        }
    }

    private void verticalOpen(CustomCallBack callBack) {
        try {
            boolean flag = vMem1608.open();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem1608CardReader.class, e);
        }
    }

    private void verticalReset(CustomCallBack callBack) {
        try {
            boolean flag = vMem1608.reset();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem1608CardReader.class, e);
        }
    }

    private void verticalStatus(CustomCallBack callBack) {
        try {
            byte status = vMem1608.status();
            CtCallbackHelper.cbHelper(callBack, true, status);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem1608CardReader.class, e);
        }
    }

    private void verticalRead(byte area, byte location, int length, CustomCallBack callBack) {
        try {
            byte[] res = vMem1608.read(area, location, length);
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem1608CardReader.class, e);
        }
    }

    private void verticalReadAccessAuthority(CustomCallBack callBack) {
        try {
            byte[] res = vMem1608.readAccessAuthority();
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem1608CardReader.class, e);
        }
    }

    private void verticalReadPswErrorCounter(byte sArea, byte type, CustomCallBack callBack) {
        try {
            byte[] res = vMem1608.readPswErrorCounter(sArea, type);
            if (null != res) {
                CtCallbackHelper.cbHelper(callBack, true, res);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem1608CardReader.class, e);
        }
    }

    private void verticalWrite(byte sArea, byte location, byte[] data, CustomCallBack callBack) {
        try {
            boolean result = vMem1608.write(sArea, location, data);
            if (result) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem1608CardReader.class, e);
        }
    }

    private void verticalVerifyPsw(byte sArea, byte type, byte[] psw, CustomCallBack callBack) {
        try {
            boolean res = vMem1608.verifyPsw(sArea, type, psw);
            if (res) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem1608CardReader.class, e);
        }
    }

    private void verticalClose(CustomCallBack callBack) {
        try {
            boolean res = vMem1608.close();
            if (res) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem1608CardReader.class, e);
        }
    }

    private boolean init = false;

    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        mem1608 = AidlMem1608.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_MEM1608));
        vMem1608 = AidlVerticalMem1608.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_VERTICAL_MEM1608));
        init = checkAIDLNotAllNull(mem1608, vMem1608);
    }

    @Override
    public boolean isAction(String action) {
        return action != null && action.startsWith(ActionCmd.MEM1608);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.MEM1608_INIT) && !init) {
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            case ActionCmd.MEM1608_INIT:
                bindService(context, callBack);
                break;
            case ActionCmd.MEM1608_OPEN:
                open(callBack);
                break;
            case ActionCmd.MEM1608_CLOSE:
                close(callBack);
                break;
            case ActionCmd.MEM1608_READ:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int area = args.optInt(0, Integer.MIN_VALUE);
                    int location = args.optInt(1, Integer.MIN_VALUE);
                    int length = args.optInt(2, Integer.MIN_VALUE);
                    if (area == Integer.MIN_VALUE || location == Integer.MIN_VALUE || length == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        read((byte) area, (byte) location, length, callBack);
                    }
                }
                break;
            case ActionCmd.MEM1608_READ_PWD_ERROR_COUNTER:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int area = args.optInt(0, Integer.MIN_VALUE);
                    int type = args.optInt(1, Integer.MIN_VALUE);
                    if (area == Integer.MIN_VALUE || type == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        readPswErrorCounter((byte) area, (byte) type, callBack);
                    }
                }

                break;
            case ActionCmd.MEM1608_WRITE:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int sArea = args.optInt(0, Integer.MIN_VALUE);
                    int location = args.optInt(1, Integer.MIN_VALUE);
                    String datas = args.optString(2, NULL_STR);
                    if (sArea == Integer.MIN_VALUE || location == Integer.MIN_VALUE || NULL_STR.equals(datas)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] data = HexUtil.hexStringToByte(datas);
                        write((byte) sArea, (byte) location, data, callBack);
                    }
                }
                break;
            case ActionCmd.MEM1608_RESET:
                reset(callBack);
                break;
            case ActionCmd.MEM1608_STATUS:
                status(callBack);
                break;
            case ActionCmd.MEM1608_VERIFY_PWD:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int sArea = args.optInt(0, Integer.MIN_VALUE);
                    int type = args.optInt(1, Integer.MIN_VALUE);
                    String psws = args.optString(2, NULL_STR);
                    if (sArea == Integer.MIN_VALUE || type == Integer.MIN_VALUE || NULL_STR.equals(psws)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] psw = HexUtil.hexStringToByte(psws);
                        verifyPsw((byte) sArea, (byte) type, psw, callBack);
                    }
                }
                break;
            case ActionCmd.MEM1608_READ_ACCESS_AUTH:
                readAccessAuthority(callBack);
                break;
            case ActionCmd.MEM1608_VERTICAL_OPEN:
                verticalOpen(callBack);
                break;
            case ActionCmd.MEM1608_VERTICAL_CLOSE:
                verticalClose(callBack);
                break;
            case ActionCmd.MEM1608_VERTICAL_READ_PWD_ERROR_COUNTER:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int sArea = args.optInt(0, Integer.MIN_VALUE);
                    int type = args.optInt(1, Integer.MIN_VALUE);
                    if (sArea == Integer.MIN_VALUE || type == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        verticalReadPswErrorCounter((byte) sArea, (byte) type, callBack);
                    }
                }
                break;
            case ActionCmd.MEM1608_VERTICAL_READ:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int area = args.optInt(0, Integer.MIN_VALUE);
                    int location = args.optInt(1, Integer.MIN_VALUE);
                    int length = args.optInt(2, Integer.MIN_VALUE);
                    if (area == Integer.MIN_VALUE || location == Integer.MIN_VALUE || length == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        verticalRead((byte) area, (byte) location, length, callBack);
                    }
                }
                break;
            case ActionCmd.MEM1608_VERTICAL_WRITE:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int sArea = args.optInt(0, Integer.MIN_VALUE);
                    int location = args.optInt(1, Integer.MIN_VALUE);
                    String datas = args.optString(2, NULL_STR);
                    if (sArea == Integer.MIN_VALUE || location == Integer.MIN_VALUE || NULL_STR.equals(datas)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] data = HexUtil.hexStringToByte(datas);
                        verticalWrite((byte) sArea, (byte) location, data, callBack);
                    }
                }
                break;
            case ActionCmd.MEM1608_VERTICAL_RESET:
                verticalReset(callBack);
                break;
            case ActionCmd.MEM1608_VERTICAL_STATUS:
                verticalStatus(callBack);
                break;
            case ActionCmd.MEM1608_VERTICAL_VERIFY_PWD:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int sArea = args.optInt(0, Integer.MIN_VALUE);
                    int type = args.optInt(1, Integer.MIN_VALUE);
                    String psws = args.optString(2, NULL_STR);
                    if (sArea == Integer.MIN_VALUE || type == Integer.MIN_VALUE || NULL_STR.equals(psws)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] psw = HexUtil.hexStringToByte(psws);
                        verticalVerifyPsw((byte) sArea, (byte) type, psw, callBack);
                    }
                }
                break;
            case ActionCmd.MEM1608_VERTICAL_READ_ACCESS_AUTH:
                verticalReadAccessAuthority(callBack);
                break;
            default:
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_ACTION_ERROR);
                break;
        }
    }
}
