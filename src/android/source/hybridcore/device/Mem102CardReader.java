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
import com.centerm.smartpos.aidl.memcard.AidlMem2Card;
import com.centerm.smartpos.aidl.memcard.AidlVerticalMem2Card;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.constant.Constant;
import com.centerm.smartpos.util.HexUtil;

import org.json.JSONArray;

/**
 * @author wangwenxun@centerm.com
 * @date 2018/9/3 16:35
 */
public class Mem102CardReader extends BaseDeviceManager implements IDeviceAction {

    private AidlMem2Card mem2Card;
    private AidlVerticalMem2Card verticalMem2Card;

    private void open(CustomCallBack callBack) {
        try {
            mem2Card.open();
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem102CardReader.class, e);
        }
    }

    private void close(CustomCallBack callBack) {
        try {
            mem2Card.close();
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem102CardReader.class, e);
        }
    }

    private void changePsw(byte[] psw, CustomCallBack callBack) {
        try {
            boolean flag = mem2Card.changePassword(psw);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem102CardReader.class, e);
        }
    }

    private void status(CustomCallBack callBack) {
        try {
            int status = mem2Card.status();
            CtCallbackHelper.cbHelper(callBack, true, status);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem102CardReader.class, e);
        }
    }

    private void reset(CustomCallBack callBack) {
        try {
            boolean flag = mem2Card.reset();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem102CardReader.class, e);
        }
    }

    private void read(int blockNum, int len, CustomCallBack callBack) {
        try {
            byte[] readValue = mem2Card.read(blockNum, len);
            if (null != readValue) {
                CtCallbackHelper.cbHelper(callBack, true, readValue);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_RESULT_NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem102CardReader.class, e);
        }
    }

    private void eraseData(int blockNum, int len, CustomCallBack callBack) {
        try {
            boolean flag = mem2Card.eraseData(blockNum, len);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem102CardReader.class, e);
        }
    }

    private void verifyPassword(byte[] psw, CustomCallBack callBack) {
        try {
            boolean flag = mem2Card.verifyPassword(psw);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem102CardReader.class, e);
        }
    }

    private void write(int blockNum, byte[] data, CustomCallBack callBack) {
        try {
            boolean flag = mem2Card.write(blockNum, data);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem102CardReader.class, e);
        }
    }

    private void verticalOpen(CustomCallBack callBack) {
        try {
            verticalMem2Card.open();
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem102CardReader.class, e);
        }
    }
    private void verticalClose(CustomCallBack callBack) {
        try {
            verticalMem2Card.close();
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem102CardReader.class, e);
        }
    }

    private void verticalStatus(CustomCallBack callBack) {
        try {
            int status = verticalMem2Card.status();
            CtCallbackHelper.cbHelper(callBack, true, status);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem102CardReader.class, e);
        }
    }

    private void verticalReset(CustomCallBack callBack) {
        try {
            boolean flag = verticalMem2Card.reset();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem102CardReader.class, e);
        }
    }

    private void verticalRead(int blockNum, int len, CustomCallBack callBack) {
        try {
            byte[] readValue = verticalMem2Card.read(blockNum, len);
            if (null != readValue) {
                CtCallbackHelper.cbHelper(callBack, true, readValue);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_RESULT_NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem102CardReader.class, e);
        }
    }

    private void verticalVerifyPassword(byte[] psw, CustomCallBack callBack) {
        try {
            boolean flag = verticalMem2Card.verifyPassword(psw);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem102CardReader.class, e);
        }
    }

    private void verticalWrite(int blockNum, byte[] data, CustomCallBack callBack) {
        try {
            boolean flag = verticalMem2Card.write(blockNum, data);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem102CardReader.class, e);
        }
    }

    private void verticalChangePsw(byte[] psw, CustomCallBack callBack) {
        try {
            boolean flag = verticalMem2Card.changePassword(psw);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem102CardReader.class, e);
        }
    }

    private void verticalEraseData(int blockNum, int len, CustomCallBack callBack) {
        try {
            boolean flag = verticalMem2Card.eraseData(blockNum, len);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem102CardReader.class, e);
        }
    }

    private boolean init = false;

    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        verticalMem2Card = AidlVerticalMem2Card.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_VERTICAL_MEM102));
        mem2Card = AidlMem2Card.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_MEM102));
        init = checkAIDLNotAllNull(verticalMem2Card, mem2Card);
    }

    @Override
    public boolean isAction(String action) {
        return action != null && action.startsWith(ActionCmd.MEM102);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.MEM102_INIT) && !init) {
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            case ActionCmd.MEM102_INIT:
                bindService(context, callBack);
                break;
            case ActionCmd.MEM102_OPEN:
                open(callBack);
                break;
            case ActionCmd.MEM102_CLOSE:
                close(callBack);
                break;
            case ActionCmd.MEM102_STATUS:
                status(callBack);
                break;
            case ActionCmd.MEM102_CHANGE_PWD:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String pwd = args.optString(0);
                    if (pwd.isEmpty()) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] psw = HexUtil.hexStringToByte(pwd);
                        changePsw(psw, callBack);
                    }
                }
                break;
            case ActionCmd.MEM102_RESET:
                reset(callBack);
                break;
            case ActionCmd.MEM102_READ:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    int len = args.optInt(1, Integer.MIN_VALUE);
                    if (blockNum == Integer.MIN_VALUE || len == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        read(blockNum, len, callBack);
                    }
                }
                break;
            case ActionCmd.MEM102_ERASE_DATA:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    int len = args.optInt(1, Integer.MIN_VALUE);
                    if (blockNum == Integer.MIN_VALUE || len == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        eraseData(blockNum, len, callBack);
                    }
                }
                break;
            case ActionCmd.MEM102_VERIFY_PWD:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String pwd = args.optString(0);
                    if (pwd.isEmpty()) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] psw = HexUtil.hexStringToByte(pwd);
                        verifyPassword(psw, callBack);
                    }
                }
                break;
            case ActionCmd.MEM102_WRITE:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    String datas = args.optString(1);
                    if (blockNum == Integer.MIN_VALUE || datas.isEmpty()) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        write(blockNum, HexUtil.hexStringToByte(datas), callBack);
                    }
                }
                break;
            case ActionCmd.MEM102_VERTICAL_OPEN:
                verticalOpen(callBack);
                break;
            case ActionCmd.MEM102_VERTICAL_CLOSE:
                verticalClose(callBack);
                break;
            case ActionCmd.MEM102_VERTICAL_STATUS:
                verticalStatus(callBack);
                break;
            case ActionCmd.MEM102_VERTICAL_RESET:
                verticalReset(callBack);
                break;
            case ActionCmd.MEM102_VERTICAL_READ:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    int len = args.optInt(1, Integer.MIN_VALUE);
                    if (blockNum == Integer.MIN_VALUE || len == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        verticalRead(blockNum, len, callBack);
                    }
                }
                break;
            case ActionCmd.MEM102_VERTICAL_VERIFY_PWD:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String pwd = args.optString(0);
                    if (pwd.isEmpty()) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        verticalVerifyPassword(HexUtil.hexStringToByte(pwd), callBack);
                    }
                }
                break;
            case ActionCmd.MEM102_VERTICAL_WRITE:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    String datas = args.optString(1);
                    if (blockNum == Integer.MIN_VALUE || datas.isEmpty()) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        verticalWrite(blockNum, HexUtil.hexStringToByte(datas), callBack);
                    }
                }
                break;
            case ActionCmd.MEM102_VERTICAL_CHANGE_PWD:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String pwd = args.optString(0);
                    if (pwd.isEmpty()) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        verticalChangePsw(HexUtil.hexStringToByte(pwd), callBack);
                    }
                }
                break;
            case ActionCmd.MEM102_VERTICAL_ERASE_DATA:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    int len = args.optInt(1, Integer.MIN_VALUE);
                    if (blockNum == Integer.MIN_VALUE || len == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        verticalEraseData(blockNum, len, callBack);
                    }
                }
                break;
            default:
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_ACTION_ERROR);
                break;
        }
    }
}
