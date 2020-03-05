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
import com.centerm.smartpos.aidl.serialport.AidlSerialPort;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.constant.Constant;
import com.centerm.smartpos.util.HexUtil;

import org.json.JSONArray;

import static com.centerm.hybridcore.constant.CommonFlag.STR_EXCEPTION;
import static com.centerm.hybridcore.constant.CommonFlag.STR_INPUT_INVALID;
import static com.centerm.hybridcore.constant.CommonFlag.STR_SUCCESS;

/**
 * 串口设备
 *
 * @author wangwenxun@centerm.com
 * @date 2018/9/3 13:50
 */
public class SerialPortManager extends BaseDeviceManager implements IDeviceAction {

    private AidlSerialPort serialPort1;
    private AidlSerialPort serialPort2;

    /**
     * 打开串口
     *
     * @param port     1或者2
     * @param baudRate 波特率
     * @param callBack 回调
     */
    private void open(int port, int baudRate, CustomCallBack callBack) {
        try {
            if (port == 1) {
                this.serialPort1.open(baudRate);
            } else {
                this.serialPort2.open(baudRate);
            }
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(SerialPortManager.class, e);
        }
    }

    private void close(int port, CustomCallBack callBack) {
        try {
            if (port == 1) {
                this.serialPort1.close();
            } else {
                this.serialPort2.close();
            }
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(SerialPortManager.class, e);
        }
    }

    private void write(int port, String data, boolean blHexWrite, CustomCallBack callBack) {
        byte[] hexByte = data.getBytes();
        if (blHexWrite) {
            if ((data.length() % 2) != 0) {
                LogUtil.getInstance().printError("SerialPorManager", "Input data is wrong");
                CtCallbackHelper.cbHelper(callBack, false, STR_INPUT_INVALID);
                return;
            }
            hexByte = HexUtil.hexStringToByte(data);
            if (hexByte == null) {
                CtCallbackHelper.cbHelper(callBack, false, STR_INPUT_INVALID);
                return;
            }
        }
        try {
            if (port == 1) {
                this.serialPort1.sendData(hexByte);
            } else {
                this.serialPort2.sendData(hexByte);
            }
            CtCallbackHelper.cbHelper(callBack, true, STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(SerialPortManager.class, e);
        }
    }

    private void readData(int port, int timeout, CustomCallBack callBack) {
        try {
            byte[] data;
            if (port == 1) {
                data = this.serialPort1.receiveData(timeout);
            } else {
                data = this.serialPort2.receiveData(timeout);
            }
            if (data == null) {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_RESULT_NULL);
            } else {
                CtCallbackHelper.cbHelper(callBack, true, data);
            }

        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(SerialPortManager.class, e);
        }
    }

    private boolean init = false;

    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        serialPort1 = AidlSerialPort.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_SERIALPORT1));
        serialPort2 = AidlSerialPort.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_SERIALPORT2));
        init = checkAIDLNotAllNull(serialPort1, serialPort2);
    }

    @Override
    public boolean isAction(String action) {
        return action != null && action.startsWith(ActionCmd.SERIAL);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.SERIAL_INIT) && !init) {
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            case ActionCmd.SERIAL_INIT:
                bindService(context, callBack);
                break;
            case ActionCmd.SERIAL_OPEN:
                if (ParamUtil.checkParameters(callBack, args)) {
                    try {
                        int port = args.optInt(0, -1);
                        int baudRate = args.optInt(1, -1);
                        if (port == -1 || baudRate == -1) {
                            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                        } else {
                            open(port, baudRate, callBack);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
                    }
                }
                break;
            case ActionCmd.SERIAL_RECEIVE:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int port = args.optInt(0, -1);
                    int timeout = args.optInt(1, -1);
                    if (port == -1 || timeout == -1) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        readData(port, timeout, callBack);
                    }
                }
                break;
            case ActionCmd.SERIAL_SEND:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int port = args.optInt(0, -1);
                    String data = args.optString(1);
                    boolean blHexWrite = args.optBoolean(2);
                    if (port == -1 || data.isEmpty()) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        write(port, data, blHexWrite, callBack);
                    }
                }
                break;
            case ActionCmd.SERIAL_CLOSE:
                if (ParamUtil.checkParameters(callBack, args)) {
                    int port = args.optInt(0, -1);
                    if (port == -1) {
                        CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    } else {
                        close(port, callBack);
                    }
                }
                break;
            default:
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_ACTION_ERROR);
                break;
        }
    }
}
