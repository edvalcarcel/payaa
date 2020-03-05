package com.centerm.hybridcore.device;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.centerm.hybridcore.base.BaseDeviceManager;
import com.centerm.hybridcore.base.IDeviceAction;
import com.centerm.hybridcore.callback.CtCallbackHelper;
import com.centerm.hybridcore.callback.CustomCallBack;
import com.centerm.hybridcore.constant.ActionCmd;
import com.centerm.hybridcore.constant.CommonFlag;
import com.centerm.hybridcore.util.LogUtil;
import com.centerm.smartpos.aidl.printer.AidlPrinter;
import com.centerm.smartpos.aidl.printer.AidlPrinterStateChangeListener;
import com.centerm.smartpos.aidl.printer.PrintDataObject;
import com.centerm.smartpos.aidl.printer.PrinterParams;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.constant.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;


/**
 * 打印设备
 *
 * @author qiuchunhua@centerm.com
 * @date 2018/9/30 17:45
 */
public class Printer extends BaseDeviceManager implements IDeviceAction {

    private AidlPrinter aidlPrinter;

    private void getPrinterState(CustomCallBack callBack) {
        try {
            int i = aidlPrinter.getPrinterState();
            CtCallbackHelper.cbHelper(callBack, true, i);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    /**
     * JSONArray 装换成List<PrintDataObject>
     *
     * @param array 参数
     * @return 参数错误或者异常返回空的List对象, 不会NULL
     */
    private List<PrintDataObject> toPrintDataObjectList(JSONArray array) throws JSONException {
        if (array == null || array.length() == 0) {
            return new LinkedList<>();
        }
        List<PrintDataObject> list = new LinkedList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            list.add(toPrintDataObject(obj));
        }
        return list;
    }

    /**
     * 将JSONObject的内容填充到been中
     *
     * @param obj JSONObject 对象
     */
    private PrintDataObject toPrintDataObject(JSONObject obj) {
        PrintDataObject been = new PrintDataObject(obj.optString("text"));
        been.setWordWrap(obj.optBoolean("isWordWrap", true));
        been.setUnderline(obj.optBoolean("isUnderline", false));
        been.setLineHeight(obj.optInt("lineHeight", 29));
        been.setLetterSpacing(obj.optInt("letterSpacing", 0));
        been.setMarginLeft(obj.optInt("marginLeft", 0));
        been.setIsLittleSize(obj.optBoolean("isLittleSize", false));
        been.setFontSize(obj.optInt("fontSize", 8));
        been.setBold(obj.optBoolean("isBold", false));
        //几个枚举类型需要通过字符串(不区分大小写)设置
        String align = obj.optString("align", "").toUpperCase();
        switch (align) {
            case "RIGHT": {
                been.setAlign(PrintDataObject.ALIGN.RIGHT);
                break;
            }
            case "CENTER": {
                been.setAlign(PrintDataObject.ALIGN.CENTER);
                break;
            }
            default: {
                been.setAlign(PrintDataObject.ALIGN.LEFT);
            }
        }
        String bmp = obj.optString("bmp", "").toLowerCase();
        switch (bmp) {
            case "AUTO": {
                been.setBmp(PrintDataObject.BMP.AUTO);
                break;
            }
            case "AUTO_CENTER": {
                been.setBmp(PrintDataObject.BMP.AUTO_CENTER);
                break;
            }
            case "DEFAULT_CENTER": {
                been.setBmp(PrintDataObject.BMP.DEFAULT_CENTER);
                break;
            }
            default: {
                been.setBmp(PrintDataObject.BMP.DEFAULT);
            }
        }
        String spacing = obj.optString("spacing", "").toUpperCase();
        switch (spacing) {
            case "DOUBLE_HIGH": {
                been.setSpacing(PrintDataObject.SPACING.DOUBLE_HIGH);
                break;
            }
            case "DOUBLE_HIGH_WIDTH": {
                been.setSpacing(PrintDataObject.SPACING.DOUBLE_HIGH_WIDTH);
                break;
            }
            case "DOUBLE_WIDTH": {
                been.setSpacing(PrintDataObject.SPACING.DOUBLE_WIDTH);
                break;
            }
            default: {
                been.setSpacing(PrintDataObject.SPACING.DEFAULT);
            }
        }
        return been;
    }

    private void printText(JSONArray args, final CustomCallBack callBack) {
        try {
            if (args == null || args.length() == 0) {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                return;
            }
            aidlPrinter.printText(toPrintDataObjectList(args), new PrintCallBack(callBack));
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void printBarCode(JSONArray args, CustomCallBack callBack) {
        try {
            if (args == null || args.length() == 0) {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                return;
            }
            String text = args.optString(0, "");
            if (text.isEmpty()) {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                return;
            }
            String type = args.optString(1, "").toLowerCase();
            //全参数
            if ("extend".equals(type)) {
                int i1 = args.optInt(1, -1);
                int i2 = args.optInt(2, -1);
                int i3 = args.optInt(3, -1);
                int i4 = args.optInt(4, -1);
                if (i1 <= -1 || i2 <= -1 || i3 <= -1 || i4 <= -1) {
                    CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    return;
                }
                aidlPrinter.printBarCodeExtend(text, i1, i2, i3, i4, new PrintCallBack(callBack));
                //同步打印
            }
            if ("fast".equals(type)) {
                int i = args.optInt(1, -1);
                if (i <= -1) {
                    CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                    return;
                }
                aidlPrinter.printBarCodeFast(text, i, new PrintCallBack(callBack));
            } else if ("sync".equals(type)) {
                aidlPrinter.printBarCodeSync(text);
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } else {
                aidlPrinter.printBarCode(text, new PrintCallBack(callBack));
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void printBmp(JSONArray args, CustomCallBack callBack) {
        try {
            String base64 = args.optString(0);
            if (base64.isEmpty()) {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                return;
            }
            int marginLeft = args.optInt(1, -1);
            if (marginLeft <= -1) {
                marginLeft = 0;
            }
            int width = args.optInt(2, 100);
            int height = args.optInt(3, 100);

            byte[] bs = Base64.decode(base64, Base64.NO_WRAP);
            Bitmap bmp = BitmapFactory.decodeByteArray(bs, 0, bs.length);
            aidlPrinter.printBmp(marginLeft, width, height, bmp, new PrintCallBack(callBack));
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void printBmpFast(JSONArray args, CustomCallBack callBack) {
        try {
            String base64 = args.optString(0);
            if (base64.isEmpty()) {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                return;
            }
            int align = args.optInt(1, -1);
            align = align <= -1 ? 0 : align;
            align = align >= 3 ? 0 : align;
            byte[] bs = Base64.decode(base64, Base64.NO_WRAP);
            Bitmap bmp = BitmapFactory.decodeByteArray(bs, 0, bs.length);
            aidlPrinter.printBmpFast(bmp, align, new PrintCallBack(callBack));
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void spitPaper(JSONArray args, CustomCallBack callBack) {
        try {
            if (args == null || args.length() == 0) {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                return;
            }
            int i = args.optInt(0);
            if (i <= 0) {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
            } else {
                aidlPrinter.spitPaper(i);
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void setPrinterGray(JSONArray args, CustomCallBack callBack) {
        try {
            int level = 1;
            try {
                level = args.getInt(0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // 一共4个等级,小于1取1,大于4取4
            level = level < 1 ? 1 : level > 4 ? 4 : level;
            aidlPrinter.setPrinterGray(level);
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void initPrinter(CustomCallBack callBack) {
        try {
            aidlPrinter.initPrinter();
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }


    private void printQRCodeFast(JSONArray args, CustomCallBack callBack) {
        try {
            if (args == null || args.length() == 0) {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                return;
            }
            String content = args.optString(0);
            if (content.isEmpty()) {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                return;
            }
            int width = args.optInt(1, -1);
            int align = args.optInt(2, -1);
            if (width <= -1) {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                return;
            }
            //对其:左中右=0,1,2
            align = align > 2 ? 1 : align;
            align = align < 0 ? 1 : align;
            aidlPrinter.printQRCodeFast(content, width, align, new PrintCallBack(callBack));
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void setPrintQueue(JSONArray args, CustomCallBack callBack) {
        try {
            if (args == null || args.length() == 0) {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                return;
            }
            boolean flag = args.optBoolean(0);
            flag = aidlPrinter.setPrintQueue(flag);
            if (flag) {
                CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
            } else {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }


    private List<PrinterParams> toPrinterParamsList(JSONArray args) {
        List<PrinterParams> ret = new LinkedList<>();
        for (int i = 0; i < args.length(); i++) {
            JSONObject obj = args.optJSONObject(i);
            if (obj != null) {
                ret.add(toPrinterParams(obj));
            }
        }
        return ret;
    }

    private PrinterParams toPrinterParams(JSONObject object) {
        PrinterParams params = new PrinterParams();
        String align = object.optString("align", "LEFT").toUpperCase();
        switch (align) {
            case "LEFT":
                params.setAlign(PrinterParams.ALIGN.LEFT);
                break;
            case "RIGHT":
                params.setAlign(PrinterParams.ALIGN.RIGHT);
                break;
            case "CENTER":
                params.setAlign(PrinterParams.ALIGN.CENTER);
                break;
            case "OTHER":
            default:
                params.setAlign(PrinterParams.ALIGN.OTHER);
        }
        String dataType = object.optString("dataType", "TEXT").toUpperCase();
        switch (dataType) {
            case "IMAGE": {
                String bmpStr = object.optString("bitmap");
                if (!bmpStr.isEmpty()) {
                    params.setDataType(PrinterParams.DATATYPE.IMAGE);
                    try {
                        byte[] bmpBytes = Base64.decode(bmpStr, Base64.NO_WRAP);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bmpBytes, 0, bmpBytes.length);
                        params.setBitmap(bitmap);
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                        LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
                    }
                }
                //出现缺少图片数据,自动转成打印文字类型,不会打印图片也不会打印文字
                params.setDataType(PrinterParams.DATATYPE.TEXT);
                break;
            }
            case "QRCODE":
                params.setDataType(PrinterParams.DATATYPE.QRCODE);
                break;
            case "BARCODE":
                params.setDataType(PrinterParams.DATATYPE.BARCODE);
                break;
            case "FEED_LINE":
                params.setDataType(PrinterParams.DATATYPE.FEED_LINE);
                break;
            case "TEXT":
            default:
                params.setDataType(PrinterParams.DATATYPE.TEXT);
        }
        String typeFace = object.optString("typeFace", "DEFAULT").toUpperCase();
        switch (typeFace) {
            case "SIMSUN":
                params.setTypeface(PrinterParams.TYPEFACE.SIMSUN);
                break;
            case "FANGSONG":
                params.setTypeface(PrinterParams.TYPEFACE.FANGSONG);
                break;
            case "KAITI":
                params.setTypeface(PrinterParams.TYPEFACE.KAITI);
                break;
            case "MSYH":
                params.setTypeface(PrinterParams.TYPEFACE.MSYH);
                break;
            case "ROBOTOLIGHT":
                params.setTypeface(PrinterParams.TYPEFACE.ROBOTOLIGHT);
                break;
            default:
                params.setTypeface(PrinterParams.TYPEFACE.DEFAULT);
        }
        params.setText(object.optString("text", ""));
        params.setTextSize(object.optInt("textSize", 24));
        params.setItalic(object.optBoolean("isItalic", false));
        params.setUpsetDown(object.optBoolean("isUpsetDown", false));
        params.setStrikeThruText(object.optBoolean("isStrikeThruText", false));
        params.setSmallSize(object.optBoolean("smallSize", false));
        params.setBold(object.optBoolean("bold", false));
        params.setUnderLine(object.optBoolean("underLine", false));
        params.setDoubleWidth(object.optBoolean("doubleWidth", false));
        params.setDoubleHeight(object.optBoolean("doubleHeight", false));
        params.setLineHeight(object.optInt("lineHeight", 28));
        params.setBarcodeWidth(object.optInt("barcodeWidth", 200));
        params.setBarcodeHeight(object.optInt("barcodeHeight", 50));
        params.setQrcodeWidth(object.optInt("qrcodeWidth", 250));
        params.setGray(object.optInt("gray", 1));
        params.setLetterSpace(object.optInt("letterSpace", 0));
        params.setMarginLeft(object.optInt("marginLeft", 0));
        params.setLineSpace(object.optInt("lineSpace", 0));
        params.setImgWidth(object.optInt("imgWidth", 0));
        params.setImgHeigth(object.optInt("imgHeigth", 0));
        params.setFeedlineNum(object.optInt("feedlineNum", 0));
        return params;
    }


    private void printDatas(JSONArray args, CustomCallBack callBack) {
        try {
            if (args == null || args.length() == 0) {
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INVALID_PARAM);
                return;
            }
            List<PrinterParams> var1 = toPrinterParamsList(args);
            aidlPrinter.printDatas(var1, new PrintCallBack(callBack));
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private void checkBlackLable(CustomCallBack callBack) {
        try {
            int i = aidlPrinter.checkBlackLable();
            CtCallbackHelper.cbHelper(callBack, true, i);
        } catch (Exception e) {
            e.printStackTrace();
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_EXCEPTION);
            LogUtil.getInstance().printExceptionWithClassName(getClass(), e);
        }
    }

    private class PrintCallBack extends AidlPrinterStateChangeListener.Stub {

        private CustomCallBack callBack;

        private PrintCallBack(CustomCallBack callBack) {
            this.callBack = callBack;
        }

        @Override
        public void onPrintFinish() {
            CtCallbackHelper.cbHelper(callBack, true, CommonFlag.STR_SUCCESS);
        }

        @Override
        public void onPrintError(int i) {
            CtCallbackHelper.cbHelper(callBack, false, i);
        }

        @Override
        public void onPrintOutOfPaper() {
            CtCallbackHelper.cbHelper(callBack, false, "OUT_OF_PAPER");
        }
    }

    @Override
    public boolean isAction(String action) {
        return (action != null) && action.startsWith(ActionCmd.PRINTER);
    }

    @Override
    public void execute(String action, JSONArray args, Context context, CustomCallBack callBack) {
        action = action == null ? "" : action;
        if (!action.equals(ActionCmd.PRINTER_INIT) && aidlPrinter == null) {
            CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_INIT_FIRST);
            return;
        }
        switch (action) {
            //初始化
            case ActionCmd.PRINTER_INIT:
                bindService(context, callBack);
                break;
            //设置灰度
            case ActionCmd.PRINTER_GRAY:
                setPrinterGray(args, callBack);
                break;
            //打印文字
            case ActionCmd.PRINTER_PRINT_TEXT:
                printText(args, callBack);
                break;
            //打印数据
            case ActionCmd.PRINTER_PRINT_DATA:
                printDatas(args, callBack);
                break;
            //吐纸
            case ActionCmd.PRINTER_SPIT_PAPER:
                spitPaper(args, callBack);
                break;
            //初始化打印参数
            case ActionCmd.PRINTER_INIT_PARAM:
                initPrinter(callBack);
                break;
            //获取打印机状态
            case ActionCmd.PRINTER_STATE:
                getPrinterState(callBack);
                break;
            //设置打印机是否开启打印队列
            case ActionCmd.PRINTER_SET_PRINT_QUEUE:
                setPrintQueue(args, callBack);
                break;
            //打印一维码(不提供同步方法)
            case ActionCmd.PRINTER_PRINT_BAR_CODE:
                printBarCode(args, callBack);
                break;
            //打印二维码(不提供同步方法)
            case ActionCmd.PRINTER_PRINT_QRCODE:
                printQRCodeFast(args, callBack);
                break;
            //打印图片
            case ActionCmd.PRINTER_PRINT_BMP:
                printBmp(args, callBack);
                break;
            //打印图片
            case ActionCmd.PRINTER_PRINT_BMP_FASTER:
                printBmpFast(args, callBack);
                break;
            //检查黑标
            case ActionCmd.PRINTER_CHECK_BLACK_LABLE:
                checkBlackLable(callBack);
                break;
            default:
                CtCallbackHelper.cbHelper(callBack, false, CommonFlag.STR_ACTION_ERROR);
                break;
        }

    }

    @Override
    public void onServiceBinded(AidlDeviceManager manager) throws Throwable {
        aidlPrinter = AidlPrinter.Stub.asInterface(manager.getDevice(Constant.DEVICE_TYPE.DEVICE_TYPE_PRINTERDEV));
        checkAIDLNotAllNull(aidlPrinter);
    }
}
