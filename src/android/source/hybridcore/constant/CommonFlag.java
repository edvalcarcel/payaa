package com.centerm.hybridcore.constant;

/**
 * @author wangwenxun
 * @date 2018/9/5
 */
public class CommonFlag {

    public static String STR_SUCCESS = "SUCCESS";
    public static String STR_FAIL = "FAIL";
    public static String STR_EXCEPTION = "EXCEPTION";
    /*
    * BEGIN
    * STR_ERROR,STR_FAIL意思重复,内容重复,故舍弃一个
    * 20181010 qiuchunhua@centerm.com
    * END
    * */
    //public static String STR_ERROR = "ERROR";
    public static String STR_UNKNOWN_ERROR = "UNKNOWN_ERROR";
    public static String STR_RESULT_NULL = "RESULT_IS_NULL";
    public static String STR_INVALID_PARAM = "INVALID_PARAM";
    public static String STR_INPUT_INVALID = "INPUT_DATA_INVALID";
    public static String STR_INIT_FIRST = "PLEASE_CALL_INIT_FIRST";
    public static String STR_TIME_OUT = "TIME_OUT";
    public static String STR_CANCEL = "CANCEL";
    public static String STR_ACTION_ERROR = "ACTION_ERROR";
    public static String STR_TRUE = "TRUE";
    public static String STR_FALSE = "FALSE";

    /**
     * IC卡在位状态
     */
    public static int ICCARD_IN = 1;
    public static int ICCARD_OUT = -1;
    /**
     * RF卡在位状态
     */
    public static int RFCARD_IN = 1;
    public static int RFCARD_OUT = -1;
    /**
     * M1卡在位状态
     */
    public static int M1CARD_IN = 1;
    public static int M1CARD_OUT = -1;

    /**
     * 打印机状态
     */
    public static String PRINT_FINISH = "PRINT_FINISH";
    public static String PRINT_OUT_PAPER = "PRINT_OUT_OF_PAPER";
    public static String PRINT_ERROR = "PRINT_ERROR";

    /**
     * EMV
     */
    public static String FIND_MAG_CARD = "FIND_MAG_CARD";
    public static String FIND_IC_CARD = "FIND_IC_CARD";
    public static String FIND_RF_CARD = "FIND_RF_CARD";
    public static String SWIPE_CARD_FAIL = "SWIPE_CARD_FAIL";
    public static String TIME_OUT = "TIME_OUT";
    public static String CANCEL = "CANCEL";
    public static String ERROR = "ERROR";

    /**
     * 返回给客户的故障值
     */
    public static String STR_OBJECT_NULL = "OBJECT_IS_NULL";
    public static String STR_ARG_NULL = "ARGS_IS_NULL";


}
