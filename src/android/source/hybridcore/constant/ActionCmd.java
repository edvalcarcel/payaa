package com.centerm.hybridcore.constant;

/**
 * @author wangwenxun
 * @date 2018/9/3
 */
public class ActionCmd {

    public static final String TOAST = "TOAST";

    /**
     * 钱箱设备
     */
    public static final String CASH_BOX_INIT = "CASH_BOX_INIT";
    public static final String CASH_BOX_OPEN = "CASH_BOX_OPEN";

    /**
     * WIFI探针
     */
    public static final String WIFIPROBE = "WIFIPROBE_";
    public static final String WIFIPROBE_INIT = "WIFIPROBE_INIT";
    public static final String WIFIPROBE_OPEN = "WIFIPROBE_OPEN";
    public static final String WIFIPROBE_CLOSE = "WIFIPROBE_CLOSE";
    public static final String WIFIPROBE_START_GET_INFO = "WIFIPROBE_START_GET_INFO";
    public static final String WIFIPROBE_STOP_GET_INFO = "WIFIPROBE_STOP_GET_INFO";
    public static final String WIFIPROBE_START_GET_STATE = "WIFIPROBE_START_GET_STATE";
    public static final String WIFIPROBE_STOP_GET_STATE = "WIFIPROBE_STOP_GET_STATE";

    /**
     * 客显设备
     */
    public static final String CUSDISPLAYER = "CUSDISPLAYER_";
    public static final String CUSDISPLAYER_INIT = "CUSDISPLAYER_INIT";
    public static final String CUSDISPLAYER_OPEN = "CUSDISPLAYER_OPEN";
    public static final String CUSDISPLAYER_CLOSE = "CUSDISPLAYER_CLOSE";
    public static final String CUSDISPLAYER_CLEAR = "CUSDISPLAYER_CLEAR";
    public static final String CUSDISPLAYER_DISPLAY = "CUSDISPLAYER_DISPLAY";
    public static final String CUSDISPLAYER_LED_CTR = "CUSDISPLAYER_LED_CTR";

    /**
     * 版本工具
     */
    public static final String VERSION = "VERSION_";
    public static final String VERSION_INIT = "VERSION_INIT";
    public static final String VERSION_GET = "VERSION_GET";

    /**
     * LCD(屏幕)控制
     */
    public static final String LCD = "LCD_";
    public static final String LCD_CONTROL = "LCD_CONTROL";
    public static final String LCD_INIT = "LCD_INIT";


    /**
     * 辅屏控制
     */
    public static final String OLDLED = "OLDLED_";
    public static final String OLDLED_INIT = "OLDLED_INIT";
    public static final String OLDLED_SHOW = "OLDLED_SHOW";
    public static final String OLDLED_CLEAR = "OLDLED_CLEAR";

    /**
     * 身份证识别设备
     */
    public static final String IDCARD = "IDCARD_";
    public static final String IDCARD_INIT = "IDCARD_INIT";
    public static final String IDCARD_START_SEARCH = "IDCARD_START_SEARCH";
    public static final String IDCARD_STOP_SEARCH = "IDCARD_STOP_SEARCH";
    public static final String IDCARD_ISREGIST = "IDCARD_ISREGIST";

    /**
     * IC卡设备
     */
    public static final String IC = "IC_";
    public static final String IC_INIT = "IC_INIT";
    public static final String IC_OPEN = "IC_OPEN";
    public static final String IC_HALT = "IC_HALT";
    public static final String IC_CLOSE = "IC_CLOSE";
    public static final String IC_STATUS = "IC_STATUS";
    public static final String IC_RESET = "IC_RESET";
    public static final String IC_SEND_APDU = "IC_SEND_APDU";
    public static final String IC_SEND_APDUSYNC = "IC_SEND_APDU_SYNC";

    /**
     * RF卡设备
     */
    public static final String RF = "RF_";
    public static final String RF_INIT = "RF_INIT";
    public static final String RF_OPEN = "RF_OPEN";
    public static final String RF_CLOSE = "RF_CLOSE";
    public static final String RF_STATUS = "RF_STATUS";
    public static final String RF_RESET = "RF_RESET";
    public static final String RF_WUPA_RESET = "RF_WUPA_RESET";
    public static final String RF_HALT = "RF_HALT";
    public static final String RF_PWM_BEEP = "RF_PWM_BEEP";
    public static final String RF_SET_LED = "RF_SET_LED";
    public static final String RF_OPEN_ID_CARD = "RF_OPEN_ID_CARD";
    public static final String RF_READ_CARD_TYPE = "RF_READ_CARD_TYPE";
    public static final String RF_SEND_APDU = "RF_SEND_APDU";
    public static final String RF_SEND_APDUSYNC = "RF_SEND_APDU_SYNC";

    /**
     * 磁条卡设备
     */
    public static final String MAG = "MAG_";
    public static final String MAG_INIT = "MAG_INIT";
    public static final String MAG_OPEN = "MAG_OPEN";
    public static final String MAG_CLOSE = "MAG_CLOSE";
    public static final String MAG_STOP_READ = "MAG_STOP_READ";
    public static final String MAG_OPEN_SOUND = "MAG_OPEN_SOUND";
    public static final String MAG_CLOSE_SOUND = "MAG_CLOSE_SOUND";
    public static final String MAG_READ = "MAG_READ";

    /**
     * Mifare卡
     */
    public static final String MIFARE = "MIFARE_";
    public static final String MIFARE_INIT = "MIFARE_INIT";
    public static final String MIFARE_OPEN = "MIFARE_OPEN";
    public static final String MIFARE_RESET = "MIFARE_RESET";
    public static final String MIFARE_STATUS = "MIFARE_STATUS";
    public static final String MIFARE_AUTH = "MIFARE_AUTH";
    public static final String MIFARE_READ = "MIFARE_READ";
    public static final String MIFARE_WRITE = "MIFARE_WRITE";
    public static final String MIFARE_ADD = "MIFARE_ADD";
    public static final String MIFARE_REDUCE = "MIFARE_REDUCE";
    public static final String MIFARE_READ_STORAGE = "MIFARE_READ_STORAGE";
    public static final String MIFARE_WRITE_STORAGE = "MIFARE_WRITE_STORAGE";
    public static final String MIFARE_CLOSE = "MIFARE_CLOSE";
    //    public static final String  MIFARE_WUPA_RESET = "MIFARE_WUPA_RESET";//RF设备的接口

    /**
     * 打印机设备
     */
    public static final String PRINTER = "PRINTER_";
    public static final String PRINTER_INIT = "PRINTER_INIT";
    public static final String PRINTER_GRAY = "PRINTER_GRAY";
    public static final String PRINTER_PRINT_TEXT = "PRINTER_PRINT_TEXT";
    public static final String PRINTER_PRINT_DATA = "PRINTER_PRINT_DATA";
    public static final String PRINTER_SPIT_PAPER = "PRINTER_SPIT_PAPER";
    public static final String PRINTER_INIT_PARAM = "PRINTER_INIT_PARAM";
    public static final String PRINTER_STATE = "PRINTER_STATE";
    public static final String PRINTER_SET_PRINT_QUEUE = "PRINTER_SET_PRINT_QUEUE";
    public static final String PRINTER_PRINT_BAR_CODE = "PRINTER_PRINT_BAR_CODE";
    public static final String PRINTER_PRINT_QRCODE = "PRINTER_PRINT_QRCODE";
    public static final String PRINTER_PRINT_BMP = "PRINTER_PRINT_BMP";
    public static final String PRINTER_PRINT_BMP_FASTER = "PRINTER_PRINT_BMP_FASTER";
    public static final String PRINTER_CHECK_BLACK_LABLE = "PRINTER_CHECK_BLACK_LABLE";

    /**
     * 语音播报
     */
    public static final String SOUND = "SOUND_";
    public static final String SOUND_INIT = "SOUND_INIT";
    public static final String SOUND_PLAY = "SOUND_PLAY";
    public static final String SOUND_COMMON = "SOUND_COMMON";
    public static final String SOUND_CLEVER = "SOUND_CLEVER";
    public static final String SOUND_READ_PARAM = "SOUND_READ_PARAM";

    /**
     * 扫码设备
     */
    public static final String SCANNER = "SCANNER_";
    public static final String SCANNER_INIT = "SCANNER_INIT";
    public static final String SCANNER_SCAN = "SCANNER_SCAN";
    public static final String SCANNER_CANCLE = "SCANNER_CANCLE";

    /**
     * 系统设置
     */
    public static final String SYSTEM = "SYSTEM_";
    public static final String SYSTEM_INIT = "SYSTEM_INIT";
    public static final String SYSTEM_INSERT_CUSTOM_APN = "SYSTEM_INSERT_CUSTOM_APN";
    public static final String SYSTEM_INSERT_NEW_CUSTOM_APN = "SYSTEM_INSERT_NEW_CUSTOM_APN";
    public static final String SYSTEM_CHECK_CUSTOM_KEY = "SYSTEM_CHECK_CUSTOM_KEY";
    public static final String SYSTEM_READ_STORAGE_LOG = "SYSTEM_READ_STORAGE_LOG";
    public static final String SYSTEM_WRITE_STORAGE_LOG = "SYSTEM_WRITE_STORAGE_LOG";
    public static final String SYSTEM_SET_LED = "SYSTEM_SET_LED";
    public static final String SYSTEM_GET_DEFAUT_APN = "SYSTEM_GET_DEFAUT_APN";
    public static final String SYSTEM_GET_APNID = "SYSTEM_GET_APNID";
    public static final String SYSTEM_GET_SDK_VERSION = "SYSTEM_GET_SDK_VERSION";
    public static final String SYSTEM_REGIST_TOUCH_LISTERNER = "SYSTEM_REGIST_TOUCH_LISTERNER";
    public static final String SYSTEM_UNREGIST_TOUCH_LISTERNER = "SYSTEM_UNREGIST_TOUCH_LISTERNER";
    public static final String SYSTEM_OPEN_MULTI_TOUCH = "SYSTEM_OPEN_MULTI_TOUCH";
    public static final String SYSTEM_CLOSE_MULTI_TOUCH = "SYSTEM_CLOSE_MULTI_TOUCH";
    public static final String SYSTEM_POWER_REBOT = "SYSTEM_POWER_REBOT";
    public static final String SYSTEM_GET_PSAMNO = "SYSTEM_GET_PSAMNO";
    public static final String SYSTEM_GET_SERIAL_NUM = "SYSTEM_GET_SERIAL_NUM";
    public static final String SYSTEM_GET_IP_ADDRESS = "SYSTEM_GET_IP_ADDRESS";
    public static final String SYSTEM_GET_MAC_ADDRESS = "SYSTEM_GET_MAC_ADDRESS";
    public static final String SYSTEM_GET_IMEI = "SYSTEM_GET_IMEI";
    public static final String SYSTEM_GET_IMSI = "SYSTEM_GET_IMSI";
    public static final String SYSTEM_START_APP = "SYSTEM_START_APP";
    public static final String SYSTEM_SET_VPDN = "SYSTEM_SET_VPDN";
    public static final String SYSTEM_SET_SYSTEM_TIME = "SYSTEM_SET_SYSTEM_TIME";
    public static final String SYSTEM_MOUNT = "SYSTEM_MOUNT";
    public static final String SYSTEM_UNMOUNT = "SYSTEM_UNMOUNT";
    public static final String SYSTEM_MOUNT_USB = "SYSTEM_MOUNT_USB";
    public static final String SYSTEM_INSTALL_APK = "SYSTEM_INSTALL_APK";
    public static final String SYSTEM_UNINSTALL_APK = "SYSTEM_UNINSTALL_APK";
    public static final String SYSTEM_INSTALL_APK_BACK = "SYSTEM_INSTALL_APK_BACK";
    public static final String SYSTEM_UNINSTALL_APK_BACK = "SYSTEM_UNINSTALL_APK_BACK";
    public static final String SYSTEM_INSERT_APN = "SYSTEM_INSERT_APN";
    public static final String SYSTEM_GET_NEIGHBOR_BS_LIST = "SYSTEM_GET_NEIGHBOR_BS_LIST";
    public static final String SYSTEM_GET_CURRBS = "SYSTEM_GET_CURRBS";
    public static final String SYSTEM_FLUSH = "SYSTEM_FLUSH";
    public static final String SYSTEM_OPEN_HID = "SYSTEM_OPEN_HID";
    public static final String SYSTEM_CLOSE_HID = "SYSTEM_CLOSE_HID";
    public static final String SYSTEM_SET_DEFAULT_APN = "SYSTEM_SET_DEFAULT_APN";
    public static final String SYSTEM_DELETE_APN = "SYSTEM_DELETE_APN";
    public static final String SYSTEM_OPEN_WIFI = "SYSTEM_OPEN_WIFI";
    public static final String SYSTEM_CLOSE_WIFI = "SYSTEM_CLOSE_WIFI";

    /**
     * 串口设备
     */
    public static final String SERIAL = "SERIAL_";
    public static final String SERIAL_INIT = "SERIAL_INIT";
    public static final String SERIAL_OPEN = "SERIAL_OPEN";
    public static final String SERIAL_RECEIVE = "SERIAL_RECEIVE";
    public static final String SERIAL_SEND = "SERIAL_SEND";
    public static final String SERIAL_CLOSE = "SERIAL_CLOSE";

    /**
     * SignHelper
     */
    public static final String SIGN = "SIGN_";
    public static final String SIGN_INIT = "SIGN_INIT";
    public static final String SIGN_DO = "SIGN_DO";
    public static final String SIGN_CANCEL = "SIGN_CANCEL";

    /**
     * PSAM设备
     */
    public static final String PSAM = "PSAM_";
    public static final String PSAM_INIT = "PSAM_INIT";
    public static final String PSAM_OPEN = "PSAM_OPEN";
    public static final String PSAM_CLOSE = "PSAM_CLOSE";
    public static final String PSAM_RESET = "PSAM_RESET";
    public static final String PSAM_SET_ETU = "PSAM_SET_ETU";
    public static final String PSAM_SEND_APDU = "PSAM_SEND_APDU";

    /**
     * MEM102设备
     */
    public static final String MEM102 = "MEM102_";
    public static final String MEM102_INIT = "MEM102_INIT";
    public static final String MEM102_OPEN = "MEM102_OPEN";
    public static final String MEM102_CLOSE = "MEM102_CLOSE";
    public static final String MEM102_STATUS = "MEM102_STATUS";
    public static final String MEM102_CHANGE_PWD = "MEM102_CHANGE_PWD";
    public static final String MEM102_RESET = "MEM102_RESET";
    public static final String MEM102_READ = "MEM102_READ";
    public static final String MEM102_ERASE_DATA = "MEM102_ERASE_DATA";
    public static final String MEM102_VERIFY_PWD = "MEM102_VERIFY_PWD";
    public static final String MEM102_WRITE = "MEM102_WRITE";
    public static final String MEM102_VERTICAL_OPEN = "MEM102_VERTICAL_OPEN";
    public static final String MEM102_VERTICAL_CLOSE = "MEM102_VERTICAL_CLOSE";
    public static final String MEM102_VERTICAL_STATUS = "MEM102_VERTICAL_STATUS";
    public static final String MEM102_VERTICAL_RESET = "MEM102_VERTICAL_RESET";
    public static final String MEM102_VERTICAL_READ = "MEM102_VERTICAL_READ";
    public static final String MEM102_VERTICAL_VERIFY_PWD = "MEM102_VERTICAL_VERIFY_PWD";
    public static final String MEM102_VERTICAL_WRITE = "MEM102_VERTICAL_WRITE";
    public static final String MEM102_VERTICAL_CHANGE_PWD = "MEM102_VERTICAL_CHANGE_PWD";
    public static final String MEM102_VERTICAL_ERASE_DATA = "MEM102_VERTICAL_ERASE_DATA";

    /**
     * MEM1608设备
     */
    public static final String MEM1608 = "MEM1608_";
    public static final String MEM1608_INIT = "MEM1608_INIT";
    public static final String MEM1608_OPEN = "MEM1608_OPEN";
    public static final String MEM1608_CLOSE = "MEM1608_CLOSE";
    public static final String MEM1608_READ = "MEM1608_READ";
    public static final String MEM1608_READ_PWD_ERROR_COUNTER = "MEM1608_READ_PWD_ERROR_COUNTER";
    public static final String MEM1608_WRITE = "MEM1608_WRITE";
    public static final String MEM1608_RESET = "MEM1608_RESET";
    public static final String MEM1608_STATUS = "MEM1608_STATUS";
    public static final String MEM1608_VERIFY_PWD = "MEM1608_VERIFY_PWD";
    public static final String MEM1608_READ_ACCESS_AUTH = "MEM1608_READ_ACCESS_AUTH";
    public static final String MEM1608_VERTICAL_OPEN = "MEM1608_VERTICAL_OPEN";
    public static final String MEM1608_VERTICAL_CLOSE = "MEM1608_VERTICAL_CLOSE";
    public static final String MEM1608_VERTICAL_READ_PWD_ERROR_COUNTER = "MEM1608_VERTICAL_READ_PWD_ERROR_COUNTER";
    public static final String MEM1608_VERTICAL_READ = "MEM1608_VERTICAL_READ";
    public static final String MEM1608_VERTICAL_WRITE = "MEM1608_VERTICAL_WRITE";
    public static final String MEM1608_VERTICAL_RESET = "MEM1608_VERTICAL_RESET";
    public static final String MEM1608_VERTICAL_STATUS = "MEM1608_VERTICAL_STATUS";
    public static final String MEM1608_VERTICAL_VERIFY_PWD = "MEM1608_VERTICAL_VERIFY_PWD";
    public static final String MEM1608_VERTICAL_READ_ACCESS_AUTH = "MEM1608_VERTICAL_READ_ACCESS_AUTH";


    /**
     * MEM4428 设备
     */
    public static final String MEM4428 = "MEM4428_";
    public static final String MEM4428_INIT = "MEM4428_INIT";
    public static final String MEM4428_OPEN = "MEM4428_OPEN";
    public static final String MEM4428_CLOSE = "MEM4428_CLOSE";
    public static final String MEM4428_STATUS = "MEM4428_STATUS";
    public static final String MEM4428_RESET = "MEM4428_RESET";
    public static final String MEM4428_VERIFY_PWD = "MEM4428_VERIFY_PWD";
    public static final String MEM4428_NO_PROTECT_READ = "MEM4428_NO_PROTECT_READ";
    public static final String MEM4428_PEOTECT_READ = "MEM4428_PEOTECT_READ";
    public static final String MEM4428_NO_PROTECT_WRITE = "MEM4428_NO_PROTECT_WRITE";
    public static final String MEM4428_PROTECT_WRITE = "MEM4428_PROTECT_WRITE";
    public static final String MEM4428_VERTICAL_OPEN = "MEM4428_VERTICAL_OPEN";
    public static final String MEM4428_VERTICAL_CLOSE = "MEM4428_VERTICAL_CLOSE";
    public static final String MEM4428_VERTICAL_STATUS = "MEM4428_VERTICAL_STATUS";
    public static final String MEM4428_VERTICAL_RESET = "MEM4428_VERTICAL_RESET";
    public static final String MEM4428_VERTICAL_VERIFY_PWD = "MEM4428_VERTICAL_VERIFY_PWD";
    public static final String MEM4428_VERTICAL_NO_PROTECT_READ = "MEM4428_VERTICAL_NO_PROTECT_READ";
    public static final String MEM4428_VERTICAL_PEOTECT_READ = "MEM4428_VERTICAL_PEOTECT_READ";
    public static final String MEM4428_VERTICAL_NO_PROTECT_WRITE = "MEM4428_VERTICAL_NO_PROTECT_WRITE";
    public static final String MEM4428_VERTICAL_PROTECT_WRITE = "MEM4428_VERTICAL_PROTECT_WRITE";


    /**
     * MEM4442 设备
     */
    public static final String MEM4442 = "MEM4442_";
    public static final String MEM4442_INIT = "MEM4442_INIT";
    public static final String MEM4442_OPEN = "MEM4442_OPEN";
    public static final String MEM4442_CLOSE = "MEM4442_CLOSE";
    public static final String MEM4442_CHANGE_PWD = "MEM4442_CHANGE_PWD";
    public static final String MEM4442_READ = "MEM4442_READ";
    public static final String MEM4442_READ_ATR_DATA = "MEM4442_READ_ATR_DATA";
    public static final String MEM4442_WRITE_PROTECT_DATA = "MEM4442_WRITE_PROTECT_DATA";
    public static final String MEM4442_READ_SECURITY_DATA = "MEM4442_READ_SECURITY_DATA";
    public static final String MEM4442_READ_PROTECT_DATA = "MEM4442_READ_PROTECT_DATA";
    public static final String MEM4442_RESET = "MEM4442_RESET";
    public static final String MEM4442_STATUS = "MEM4442_STATUS";
    public static final String MEM4442_VIRIFY = "MEM4442_VIRIFY";
    public static final String MEM4442_WRITE = "MEM4442_WRITE";
    public static final String MEM4442_READ_DATA = "MEM4442_READ_DATA";
    public static final String MEM4442_WRITE_DATA = "MEM4442_WRITE_DATA";
    public static final String MEM4442_VERTICAL_CHANGE_PWD = "MEM4442_VERTICAL_CHANGE_PWD";
    public static final String MEM4442_VERTICAL_OPEN = "MEM4442_VERTICAL_OPEN";
    public static final String MEM4442_VERTICAL_CLOSE = "MEM4442_VERTICAL_CLOSE";
    public static final String MEM4442_VERTICAL_READ = "MEM4442_VERTICAL_READ";
    public static final String MEM4442_VERTICAL_READ_ATR_DATA = "MEM4442_VERTICAL_READ_ATR_DATA";
    public static final String MEM4442_VERTICAL_WRITE_PROTECT_DATA = "MEM4442_VERTICAL_WRITE_PROTECT_DATA";
    public static final String MEM4442_VERTICAL_READ_SECURITY_DATA = "MEM4442_VERTICAL_READ_SECURITY_DATA";
    public static final String MEM4442_VERTICAL_READ_PROTECT_DATA = "MEM4442_VERTICAL_READ_PROTECT_DATA";
    public static final String MEM4442_VERTICAL_RESET = "MEM4442_VERTICAL_RESET";
    public static final String MEM4442_VERTICAL_STATUS = "MEM4442_VERTICAL_STATUS";
    public static final String MEM4442_VERTICAL_VERIFY = "MEM4442_VERTICAL_VERIFY";
    public static final String MEM4442_VERTICAL_WRITE = "MEM4442_VERTICAL_WRITE";
    public static final String MEM4442_VERTICAL_READ_DATA = "MEM4442_VERTICAL_READ_DATA";
    public static final String MEM4442_VERTICAL_WRITE_DATA = "MEM4442_VERTICAL_WRITE_DATA";

    /**
     * PINPAD 设备
     */
    public static final String PINPAD = "PINPAD_";
    public static final String PINPAD_INIT = "PINPAD_INIT";
    public static final String PINPAD_OPEN = "PINPAD_OPEN";
    public static final String PINPAD_CLOSE = "PINPAD_CLOSE";
    public static final String PINPAD_STOP_GET_PIN = "PINPAD_STOP_GET_PIN";
    public static final String PINPAD_GET_RANDOM = "PINPAD_GET_RANDOM";
    public static final String PINPAD_DISPERSE_W_KEY = "PINPAD_DISPERSE_W_KEY";
    public static final String PINPAD_DISPERSE_M_KEY = "PINPAD_DISPERSE_M_KEY";
    public static final String PINPAD_DISPERSE_SM4M_KEY = "PINPAD_DISPERSE_SM4M_KEY";
    public static final String PINPAD_CONFIRM_GET_PIN = "PINPAD_CONFIRM_GET_PIN";
    public static final String PINPAD_GET_PIN = "PINPAD_GET_PIN";
    public static final String PINPAD_GET_MAC = "PINPAD_GET_MAC";
    public static final String PINPAD_ENCRYPT_DATA = "PINPAD_ENCRYPT_DATA";
    public static final String PINPAD_DISPLAY = "PINPAD_DISPLAY";
    public static final String PINPAD_GET_PINPAD_SN = "PINPAD_GET_PINPAD_SN";
    public static final String PINPAD_OPEN_PINPAD_KEYBOARD = "PINPAD_OPEN_PINPAD_KEYBOARD";
    public static final String PINPAD_CLOSE_PINPAD_KEYBOARD = "PINPAD_CLOSE_PINPAD_KEYBOARD";
    public static final String PINPAD_PINPAD_KEYBOARD_STATE = "PINPAD_PINPAD_KEYBOARD_STATE";
    public static final String PINPAD_RF_CARD_STATUS = "PINPAD_RF_CARD_STATUS";
    public static final String PINPAD_RF_CARD_RESET = "PINPAD_RF_CARD_RESET";
    public static final String PINPAD_RF_CARD_APDU = "PINPAD_RF_CARD_APDU";
    public static final String PINPAD_RF_CARD_HALT = "PINPAD_RF_CARD_HALT";
    public static final String PINPAD_SET_KEY_VOICE = "PINPAD_SET_KEY_VOICE";
    public static final String PINPAD_DOWN_KEY = "PINPAD_DOWN_KEY";
    public static final String PINPAD_DOWN_TMK = "PINPAD_DOWN_TMK";
    public static final String PINPAD_DOWN_TEK = "PINPAD_DOWN_TEK";
    public static final String PINPAD_DOWNLOAD_KEK_ENCRYPT_KEK = "PINPAD_DOWNLOAD_KEK_ENCRYPT_KEK";
    public static final String PINPAD_GET_PINPAD_SN_FROM_PROJECT = "PINPAD_GET_PINPAD_SN_FROM_PROJECT";
    public static final String PINPAD_DOWNLOAD_KEK_ENCRYPTED_MKEY = "PINPAD_DOWNLOAD_KEK_ENCRYPTED_MKEY";
    public static final String PINPAD_DOWNLOAD_KEK_ENCRYPTED_WKEY = "PINPAD_DOWNLOAD_KEK_ENCRYPTED_WKEY";
    public static final String PINPAD_START_SIGN = "PINPAD_START_SIGN";
    public static final String PINPAD_STOP_SIGN = "PINPAD_STOP_SIGN";
    public static final String PINPAD_GET_PIN_WITH_TIME = "PINPAD_GET_PIN_WITH_TIME";
    public static final String PINPAD_GET_PINPAD_SN_FROM_EXTERNAL = "PINPAD_GET_PINPAD_SN_FROM_EXTERNAL";
    public static final String PINPAD_SET_PINPAD_CANCEL_MODE = "PINPAD_SET_PINPAD_CANCEL_MODE";
    public static final String PINPAD_GET_HARDWARE_SN = "PINPAD_GET_HARDWARE_SN";
    public static final String PINPAD_GET_MAC_FOR_SNK = "PINPAD_GET_MAC_FOR_SNK";
    public static final String PINPAD_SET_INNER_PINPAD_BEEP = "PINPAD_SET_INNER_PINPAD_BEEP";

    /**
     * EMV 设备
     */
    public static final String EMV = "EMV_";
    public static final String EMV_INIT = "EMV_INIT";
    public static final String EMV_CHECK_CARD = "EMV_CHECK_CARD";
    public static final String EMV_CANCEL_CHECK_CARD = "EMV_CANCEL_CHECK_CARD";
    public static final String EMV_PROCESS_PBOC = "EMV_PROCESS_PBOC";
    public static final String EMV_END_PBOC = "EMV_END_PBOC";
    public static final String EMV_ABORT_PBOC = "EMV_ABORT_PBOC";
    public static final String EMV_CLEAR_KERNEL_IC_TRANS_LOG = "EMV_CLEAR_KERNEL_IC_TRANS_LOG";
    public static final String EMV_READ_KERNEL_DATA = "EMV_READ_KERNEL_DATA";
    public static final String EMV_SET_TLV = "EMV_SET_TLV";
    public static final String EMV_PARSE_TLV = "EMV_PARSE_TLV";
    public static final String EMV_IMPORT_AMOUNT = "EMV_IMPORT_AMOUNT";
    public static final String EMV_IMPORT_AID_SELECT_RES = "EMV_IMPORT_AID_SELECT_RES";
    public static final String EMV_IMPORT_CONFIRM_CARD_INFO_RES = "EMV_IMPORT_CONFIRM_CARD_INFO_RES";
    public static final String EMV_IMPORT_PIN = "EMV_IMPORT_PIN";
    public static final String EMV_IMPORT_USER_AUTH_RES = "EMV_IMPORT_USER_AUTH_RES";
    public static final String EMV_IMPORT_MSG_CONFIRM_RES = "EMV_IMPORT_MSG_CONFIRM_RES";
    public static final String EMV_IMPORT_ECASH_TIP_CONFIRM_RES = "EMV_IMPORT_ECASH_TIP_CONFIRM_RES";
    public static final String EMV_IMPORT_ONLINE_RESP = "EMV_IMPORT_ONLINE_RESP";
    public static final String EMV_UPDATE_AID = "EMV_UPDATE_AID";
    public static final String EMV_UPDATE_CAPK = "EMV_UPDATE_CAPK";
    public static final String EMV_AID_PARAM_READ = "EMV_AID_PARAM_READ";
    public static final String EMV_CA_PUBLIC_KEY_PARAM_READ = "EMV_CA_PUBLIC_KEY_PARAM_READ";
    public static final String EMV_SET_PARAMETERS = "EMV_SET_PARAMETERS";
    public static final String EMV_UPDATE_DRL_PARAMS = "EMV_UPDATE_DRL_PARAMS";
    public static final String EMV_DRL_PARAM_READ = "EMV_DRL_PARAM_READ";
    public static final String EMV_SET_IC_SLOT = "EMV_SET_IC_SLOT";
    public static final String EMV_RESULT_OF_ISSUER_VOICE_REFERENCE = "EMV_RESULT_OF_ISSUER_VOICE_REFERENCE";
}
