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
import com.centerm.smartpos.aidl.memcard.AidlMemCard;
import com.centerm.smartpos.aidl.memcard.AidlVerticalMemCard;
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
public class Mem4442CardReader extends BaseDeviceManager implements IDeviceAction {

    private AidlMemCard aidlMemCard;
    private AidlVerticalMemCard aidlVerticalMemCard;

    private void open(CustomCallBack callBack) {
        try {
            aidlMemCard.open();
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void close(CustomCallBack callBack) {
        try {
            aidlMemCard.close();
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void status(CustomCallBack callBack) {
        try {
            int status = aidlMemCard.status();
            CtCallbackHelper.cbHelper(callBack, true, status);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void verify(byte ec, byte[] psw, CustomCallBack callBack) {
        try {
            boolean status = aidlMemCard.verify(ec, psw);
            if (status) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void reset(CustomCallBack callBack) {
        try {
            boolean flag = aidlMemCard.reset();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void changePassword(byte[] psw, CustomCallBack callBack) {
        try {
            boolean flag = aidlMemCard.changePassword(psw);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void read(byte blockNum, byte len, CustomCallBack callBack) {
        try {
            byte[] readValue = aidlMemCard.read(blockNum, len);
            if (null != readValue) {
                CtCallbackHelper.cbHelper(callBack, true, readValue);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void readArtData(CustomCallBack callBack) {
        try {
            byte[] readValue = aidlMemCard.readAtrData();
            if (null != readValue) {
                CtCallbackHelper.cbHelper(callBack, true, readValue);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void readData(byte blockNum, int len, CustomCallBack callBack) {
        try {
            byte[] readValue = aidlMemCard.readData(blockNum, len);
            if (null != readValue) {
                CtCallbackHelper.cbHelper(callBack, true, readValue);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void readProtectData(CustomCallBack callBack) {
        try {
            byte[] readValue = aidlMemCard.readProtectData();
            if (null != readValue) {
                CtCallbackHelper.cbHelper(callBack, true, readValue);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void readSecurityData(CustomCallBack callBack) {
        try {
            byte[] readValue = aidlMemCard.readSecurityData();
            if (null != readValue) {
                CtCallbackHelper.cbHelper(callBack, true, readValue);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }


    private void write(byte blockNum, byte data, CustomCallBack callBack) {
        try {
            boolean flag = aidlMemCard.write(blockNum, data);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void writeData(byte blockNum, byte[] data, CustomCallBack callBack) {
        try {
            boolean flag = aidlMemCard.writeData(blockNum, data);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void writeProtectData(byte blockNum, byte data, CustomCallBack callBack) {
        try {
            boolean flag = aidlMemCard.writeProtectData(blockNum, data);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void verticalOpen(CustomCallBack callBack) {
        try {
            aidlVerticalMemCard.open();
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void verticalClose(CustomCallBack callBack) {
        try {
            aidlVerticalMemCard.close();
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void verticalStatus(CustomCallBack callBack) {
        try {
            int status = aidlVerticalMemCard.status();
            CtCallbackHelper.cbHelper(callBack, true, status);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void verticalVerify(byte ec, byte[] pwd, CustomCallBack callBack) {
        try {
            boolean flag = aidlVerticalMemCard.verify(ec, pwd);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void verticalReset(CustomCallBack callBack) {
        try {
            boolean flag = aidlVerticalMemCard.reset();
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void verticalWrite(byte blockNum, byte data, CustomCallBack callBack) {
        try {
            boolean flag = aidlVerticalMemCard.write(blockNum, data);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void verticalWriteData(byte blockNum, byte[] data, CustomCallBack callBack) {
        try {
            boolean flag = aidlVerticalMemCard.writeData(blockNum, data);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void verticalWriteProtectData(byte blockNum, byte data, CustomCallBack callBack) {
        try {
            boolean flag = aidlVerticalMemCard.writeProtectData(blockNum, data);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void verticalChangePassword(byte[] psw, CustomCallBack callBack) {
        try {
            boolean flag = aidlVerticalMemCard.changePassword(psw);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void verticalRead(byte blockNum, byte len, CustomCallBack callBack) {
        try {
            byte[] readValue = aidlVerticalMemCard.read(blockNum, len);
            if (null != readValue) {
                CtCallbackHelper.cbHelper(callBack, true, readValue);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void verticalReadArtData(CustomCallBack callBack) {
        try {
            byte[] readValue = aidlVerticalMemCard.readAtrData();
            if (null != readValue) {
                CtCallbackHelper.cbHelper(callBack, true, readValue);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void verticalReadData(byte blockNum, int len, CustomCallBack callBack) {
        try {
            byte[] readValue = aidlVerticalMemCard.readData(blockNum, len);
            if (null != readValue) {
                CtCallbackHelper.cbHelper(callBack, true, readValue);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void verticalReadProtectData(CustomCallBack callBack) {
        try {
            byte[] readValue = aidlVerticalMemCard.readProtectData();
            if (null != readValue) {
                CtCallbackHelper.cbHelper(callBack, true, readValue);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private void verticalReadSecurityData(CustomCallBack callBack) {
        try {
            byte[] readValue = aidlVerticalMemCard.readSecurityData();
            if (null != readValue) {
                CtCallbackHelper.cbHelper(callBack, true, readValue);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(Mem4442CardReader.class, e);
        }
    }

    private boolean init = false;

    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        aidlMemCard = AidlMemCard.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_MEM4442));
        aidlVerticalMemCard = AidlVerticalMemCard.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_VERTICAL_MEM4442));
        init = checkAIDLNotAllNull(aidlMemCard, aidlVerticalMemCard);
    }

    @Override
    public boolean isAction(String action) {
        return action != null && action.startsWith(ActionCmd.MEM4442);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.MEM4442_INIT) && !init) {
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            case ActionCmd.MEM4442_INIT:
                bindService(context, callBack);
                break;
            case ActionCmd.MEM4442_OPEN:
                open(callBack);
                break;
            case ActionCmd.MEM4442_CLOSE:
                close(callBack);
                break;
            case ActionCmd.MEM4442_CHANGE_PWD:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String psws = args.optString(0, NULL_STR);
                    if (NULL_STR.equals(psws)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] psw = HexUtil.hexStringToByte(psws);
                        changePassword(psw, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4442_READ:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    int length = args.optInt(1, Integer.MIN_VALUE);
                    if (blockNum == Integer.MIN_VALUE || length == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        read((byte) blockNum, (byte) length, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4442_READ_ATR_DATA:
                readArtData(callBack);
                break;
            case ActionCmd.MEM4442_WRITE_PROTECT_DATA:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    int data = args.optInt(1, Integer.MIN_VALUE);
                    if (blockNum == Integer.MIN_VALUE || data == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        writeProtectData((byte) blockNum, (byte) data, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4442_READ_SECURITY_DATA:
                readSecurityData(callBack);
                break;
            case ActionCmd.MEM4442_READ_PROTECT_DATA:
                readProtectData(callBack);
                break;
            case ActionCmd.MEM4442_RESET:
                reset(callBack);
                break;
            case ActionCmd.MEM4442_STATUS:
                status(callBack);
                break;
            case ActionCmd.MEM4442_VIRIFY:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int ec = args.optInt(0, Integer.MIN_VALUE);
                    String datas = args.optString(1, NULL_STR);
                    if (ec == Integer.MIN_VALUE || NULL_STR.equals(datas)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] pwd = HexUtil.hexStringToByte(datas);
                        verify((byte) ec, pwd, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4442_WRITE:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    int data = args.optInt(1, Integer.MIN_VALUE);
                    if (blockNum == Integer.MIN_VALUE || data == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        write((byte) blockNum, (byte) data, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4442_READ_DATA:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    int length = args.optInt(1, Integer.MIN_VALUE);
                    if (blockNum == Integer.MIN_VALUE || length == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        readData((byte) blockNum, length, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4442_WRITE_DATA:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    String datas = args.optString(1, NULL_STR);
                    if (blockNum == Integer.MIN_VALUE || NULL_STR.equals(datas)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] data = HexUtil.hexStringToByte(datas);
                        writeData((byte) blockNum, data, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4442_VERTICAL_CHANGE_PWD:
                if (ParamUtil.checkParameters(callBack, args)) {
                    String psws = args.optString(0, NULL_STR);
                    if (NULL_STR.equals(psws)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] psw = HexUtil.hexStringToByte(psws);
                        verticalChangePassword(psw, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4442_VERTICAL_OPEN:
                verticalOpen(callBack);
                break;
            case ActionCmd.MEM4442_VERTICAL_CLOSE:
                verticalClose(callBack);
                break;
            case ActionCmd.MEM4442_VERTICAL_READ:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    int length = args.optInt(1, Integer.MIN_VALUE);
                    if (blockNum == Integer.MIN_VALUE || length == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        verticalRead((byte) blockNum, (byte) length, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4442_VERTICAL_READ_ATR_DATA:
                verticalReadArtData(callBack);
                break;
            case ActionCmd.MEM4442_VERTICAL_WRITE_PROTECT_DATA:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    int data = args.optInt(1, Integer.MIN_VALUE);
                    if (blockNum == Integer.MIN_VALUE || data == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        verticalWriteProtectData((byte) blockNum, (byte) data, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4442_VERTICAL_READ_SECURITY_DATA:
                verticalReadSecurityData(callBack);
                break;
            case ActionCmd.MEM4442_VERTICAL_READ_PROTECT_DATA:
                verticalReadProtectData(callBack);
                break;
            case ActionCmd.MEM4442_VERTICAL_RESET:
                verticalReset(callBack);
                break;
            case ActionCmd.MEM4442_VERTICAL_STATUS:
                verticalStatus(callBack);
                break;
            case ActionCmd.MEM4442_VERTICAL_VERIFY:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int ec = args.optInt(0, Integer.MIN_VALUE);
                    String datas = args.optString(1, NULL_STR);
                    if (ec == Integer.MIN_VALUE || NULL_STR.equals(datas)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] pwd = HexUtil.hexStringToByte(datas);
                        verticalVerify((byte) ec, pwd, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4442_VERTICAL_WRITE:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    int data = args.optInt(1, Integer.MIN_VALUE);
                    if (blockNum == Integer.MIN_VALUE || data == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        verticalWrite((byte) blockNum, (byte) data, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4442_VERTICAL_READ_DATA:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    int length = args.optInt(1, Integer.MIN_VALUE);
                    if (blockNum == Integer.MIN_VALUE || length == Integer.MIN_VALUE) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        verticalReadData((byte) blockNum, length, callBack);
                    }
                }
                break;
            case ActionCmd.MEM4442_VERTICAL_WRITE_DATA:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int blockNum = args.optInt(0, Integer.MIN_VALUE);
                    String datas = args.optString(1, NULL_STR);
                    if (blockNum == Integer.MIN_VALUE || NULL_STR.equals(datas)) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        byte[] data = HexUtil.hexStringToByte(datas);
                        verticalWriteData((byte) blockNum, data, callBack);
                    }
                }
                break;
            default:
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_ACTION_ERROR);
                break;
        }
    }
}
