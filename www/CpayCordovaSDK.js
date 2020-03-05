var exec = require('cordova/exec');

var cpaySdkPlugin = {
	toast: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'TOAST', [arg0]);
	},
	//IC卡设备
	icInit: function (success, error) {
		exec(success, error, 'AndroidInterface', 'IC_INIT', []);
	},
	icOpen: function (success, error) {
		exec(success, error, 'AndroidInterface', 'IC_OPEN', []);
	},
	icClose: function (success, error) {
		exec(success, error, 'AndroidInterface', 'IC_CLOSE', []);
	},
	icHalt: function (success, error) {
		exec(success, error, 'AndroidInterface', 'IC_HALT', []);
	},
	icStatus: function (success, error) {
		exec(success, error, 'AndroidInterface', 'IC_STATUS', []);
	},
	icReset: function (success, error) {
		exec(success, error, 'AndroidInterface', 'IC_RESET', []);
	},
	icSendApdu: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'IC_SEND_APDU', [arg0]);
	},
	icSendApduSync: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'IC_SEND_APDU_SYNC', [arg0]);
	},
	//IC卡设备结束

	//打印机设备
	printerInit: function (success, error) {
		exec(success, error, 'AndroidInterface', 'PRINTER_INIT', []);
	},
    printerData: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'PRINTER_PRINT_DATA', arg0);
	},
	printerText: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'PRINTER_PRINT_TEXT', arg0);
	},
	printerOpen: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'PRINTER_OPEN', [arg0]);
	},
	printerSpitPager: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'PRINTER_SPIT_PAPER', [arg0]);
	},
	printerGray: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'PRINTER_GRAY', [arg0]);
	},
	printerInitParam: function (success, error) {
		exec(success, error, 'AndroidInterface', 'PRINTER_INIT_PARAM', []);
	},
	printerState: function (success, error) {
		exec(success, error, 'AndroidInterface', 'PRINTER_STATE', []);
	},
	printerSetPrintQueue: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'PRINTER_SET_PRINT_QUEUE', [arg0]);
	},
	printerPrintBarCode: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'PRINTER_PRINT_BAR_CODE', [arg0]);
	},
	printerPrintQrcode: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'PRINTER_PRINT_QRCODE', arg0);
	},
	printerPrintBmp: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'PRINTER_PRINT_BMP', arg0);
	},
	printerPrintBmpFast: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'PRINTER_PRINT_BMP_FASTER', arg0);
	},
	printerCheckBlackLable: function (success, error) {
		exec(success, error, 'AndroidInterface', 'PRINTER_CHECK_BLACK_LABLE', []);
	},
	//打印机设备结束

	//钱箱设备
	cashBoxInit: function (success, error) {
		exec(success, error, 'AndroidInterface', 'CASH_BOX_INIT', []);
	},
	cashBoxOpen: function (success, error) {
		exec(success, error, 'AndroidInterface', 'CASH_BOX_OPEN', []);
	},
	//钱箱设备结束

	//M1卡设备
	mifareInit: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MIFARE_INIT', []);
	},
	mifareOpen: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MIFARE_OPEN', []);
	},
	mifareClose: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MIFARE_CLOSE', []);
	},
	mifareReset: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MIFARE_RESET', []);
	},
	mifareStatue: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MIFARE_STATUS', []);
	},
	mifareAddValue: function (arg0, success, error) { //[addId, custom, redData]
		exec(success, error, 'AndroidInterface', 'MIFARE_ADD', arg0);
	},
	mifareReduceValue: function (arg0, success, error) { //[addId, custom, redData]
		exec(success, error, 'AndroidInterface', 'MIFARE_REDUCE', arg0);
	},
	mifareAuth: function (arg0, success, error) { //[flag, addId, keyA, resetData]
		exec(success, error, 'AndroidInterface', 'MIFARE_AUTH', arg0);
	},
	mifareRead: function (arg0, success, error) { //addId
		exec(success, error, 'AndroidInterface', 'MIFARE_READ', [arg0]);
	},
	mifareWrite: function (arg0, success, error) { //[addId, wData]
		exec(success, error, 'AndroidInterface', 'MIFARE_WRITE', arg0);
	},
	mifareReadStorage: function (arg0, success, error) { //addId
		exec(success, error, 'AndroidInterface', 'MIFARE_READ_STORAGE', [arg0]);
	},
	mifareWriteStorage: function (arg0, success, error) { //[address, data]
		exec(success, error, 'AndroidInterface', 'MIFARE_WRITE_STORAGE', arg0);
	},
	//M1卡设备结束

	//RF卡设备
	rfInit: function (success, error) {
		exec(success, error, 'AndroidInterface', 'RF_INIT', []);
	},
	rfOpen: function (success, error) {
		exec(success, error, 'AndroidInterface', 'RF_OPEN', []);
	},
	rfClose: function (success, error) {
		exec(success, error, 'AndroidInterface', 'RF_CLOSE', []);
	},
	rfReset: function (success, error) {
		exec(success, error, 'AndroidInterface', 'RF_RESET', []);
	},
	rfStatue: function (success, error) {
		exec(success, error, 'AndroidInterface', 'RF_STATUS', []);
	},
	rfSendAPDU: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'RF_SEND_APDU', [arg0]);
	},
	rfWUPAReset: function (success, error) {
		exec(success, error, 'AndroidInterface', 'RF_WUPA_RESET', []);
	},
	rfHalt: function (success, error) {
		exec(success, error, 'AndroidInterface', 'RF_HALT', []);
	},
	rfPwmBeep: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'RF_PWM_BEEP', [arg0]);
	},
	rfSetLED: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'RF_SET_LED', arg0);
	},
	rfOpenIdCard: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'RF_OPEN_ID_CARD', [arg0]);
	},
	rfReadCardType: function (success, error) {
		exec(success, error, 'AndroidInterface', 'RF_READ_CARD_TYPE', []);
	},
	//RF卡设备结束

	//磁条卡(刷卡设备)
	magInit: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MAG_INIT', []);
	},
	magOpen: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MAG_OPEN', []);
	},
	magClose: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MAG_CLOSE', []);
	},
	magRead: function (time, success, error) {
		exec(success, error, 'AndroidInterface', 'MAG_READ', [time]);
	},
	magStopRead: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MAG_STOP_READ', []);
	},
	magSound: function (args, success, error) {
		if (args == 1) {
			exec(success, error, 'AndroidInterface', 'MAG_OPEN_SOUND', []);
		} else {
			exec(success, error, 'AndroidInterface', 'MAG_CLOSE_SOUND', []);
		}
	},
	//磁条卡结束

	//语音播报
	soundInit: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SOUND_INIT', []);
	},
	soundPlay: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'SOUND_PLAY', [arg0]);
	},
	soundCommon: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'SOUND_COMMON', [arg0]);
	},
	soundClever: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'SOUND_CLEVER', [arg0]);
	},
	soundReadParam: function (arg0, success, error) { //arg0是一个jsonobject
		exec(success, error, 'AndroidInterface', 'SOUND_READ_PARAM', [arg0]);
	},
	//语音播报结束

	//系统接口
	systemInit: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_INIT', []);
	},
	systemOpenHID: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_OPEN_HID', []);
	},
	systemCloseHID: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_CLOSE_HID', []);
	},
	systemOpenWifi: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_OPEN_WIFI', []);
	},
	systemCloseWifi: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_CLOSE_WIFI', []);
	},
	systemSetDefautAPN: function (index, success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_SET_DEFAULT_APN', [index]);
	},
	systemDeleteAPN: function (index, success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_DELETE_APN', [index]);
	},
	systemFlush: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_FLUSH', []);
	},
	systemGetBsVersion: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_GET_CURRBS', []);
	},
	systemGetNeighborBsList: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_GET_NEIGHBOR_BS_LIST', []);
	},
	systemInsertApn: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_INSERT_APN', arg0);
	},
	systemInstallApk: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_INSTALL_APK', [arg0]);
	},
	systemUninstallApk: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_UNINSTALL_APK', [arg0]);
	},
	systemInstallApkBack: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_INSTALL_APK_BACK', [arg0]);
	},
	systemUninstallApkBack: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_UNINSTALL_APK_BACK', [arg0]);
	},
	systemMount: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_MOUNT', []);
	},
	systemUnMount: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_UNMOUNT', []);
	},
	systemMountUSB: function (arg0, success, error) { //[devId, inPoint]
		exec(success, error, 'AndroidInterface', 'SYSTEM_MOUNT_USB', arg0);
	},
	systemSetSystemTime: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_SET_SYSTEM_TIME', [arg0]);
	},
	systemSetVpdn: function (args, success, error) { //[name,s]
		exec(success, error, 'AndroidInterface', 'SYSTEM_SET_VPDN', args);
	},
	systemStartApp: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_START_APP', [arg0]);
	},
	systemGetIpAddress: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_GET_IP_ADDRESS', []);
	},
	systemGetMacAddress: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_GET_MAC_ADDRESS', []);
	},
	systemGetIMEI: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_GET_IMEI', []);
	},
	systemGetIMSI: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_GET_IMSI', []);
	},
	systemGetPsamno: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_GET_PSAMNO', []);
	},
	systemGetSerialNum: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_GET_SERIAL_NUM', []);
	},
	systemPowerRebot: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_POWER_REBOT', []);
	},
	systemOpenMultiTouch: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_OPEN_MULTI_TOUCH', []);
	},
	systemCloseMultiTouch: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_CLOSE_MULTI_TOUCH', []);
	},
	systemRegistTouchListerner: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_REGIST_TOUCH_LISTERNER', []);
	},
	systemUnregistTouchListerner: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_UNREGIST_TOUCH_LISTERNER', []);
	},
	systemGetDefaultAPN: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_GET_DEFAUT_APN', []);
	},
	systemGetApnId: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_GET_APNID', [arg0]);
	},
	systemGetSDKVersion: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_GET_SDK_VERSION', []);
	},
	systemReadStorageLog: function (arg0, success, error) { //[index,date]
		exec(success, error, 'AndroidInterface', 'SYSTEM_READ_STORAGE_LOG', arg0);
	},
	systemWriteStorageLog: function (arg0, success, error) { //[index, dataStr]
		exec(success, error, 'AndroidInterface', 'SYSTEM_WRITE_STORAGE_LOG', arg0);
	},
	systemInsertCustomAPN: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_INSERT_CUSTOM_APN', [arg0]);
	},
	systemInsertNewCustomApn: function (arg0, success, error) {
		exec(success, error, 'AndroidInterface', 'SYSTEM_INSERT_NEW_CUSTOM_APN', [arg0]);
	},
	systemCheckCustomKey: function (arg0, success, error) { //[key, index]
		exec(success, error, 'AndroidInterface', 'SYSTEM_CHECK_CUSTOM_KEY', arg0);
	},
	systemSetLED: function (arg0, success, error) { //[color, time, interval]
		exec(success, error, 'AndroidInterface', 'SYSTEM_SET_LED', arg0);
	},
	//系统接口结束

	//身份证识别接口(身份证设备-非接)
	idCardInit: function (success, error) {
		exec(success, error, 'AndroidInterface', 'IDCARD_INIT', []);
	},
	idCardStartSearch: function (time, success, error) {
		exec(success, error, 'AndroidInterface', 'IDCARD_START_SEARCH', [time]);
	},
	idCardStopSearch: function (success, error) {
		exec(success, error, 'AndroidInterface', 'IDCARD_STOP_SEARCH', []);
	},
	idCardIsRegist: function (success, error) {
		exec(success, error, 'AndroidInterface', 'IDCARD_ISREGIST', []);
	},
	//身份证识别接口结束

	//客显设备
	cusdisplayerInit: function (success, error) {
		exec(success, error, 'AndroidInterface', 'CUSDISPLAYER_INIT', []);
	},
	cusdisplayerOpen: function (success, error) {
		exec(success, error, 'AndroidInterface', 'CUSDISPLAYER_OPEN', []);
	},
	cusdisplayerClose: function (success, error) {
		exec(success, error, 'AndroidInterface', 'CUSDISPLAYER_CLOSE', []);
	},
	cusdisplayerClear: function (success, error) {
		exec(success, error, 'AndroidInterface', 'CUSDISPLAYER_CLEAR', []);
	},
	cusdisplayerLEDCtr: function (arg0, success, error) { //int类型
		exec(success, error, 'AndroidInterface', 'CUSDISPLAYER_LED_CTR', [arg0]);
	},
	cusdisplayerDisplay: function (arg0, success, error) { //字符类型
		exec(success, error, 'AndroidInterface', 'CUSDISPLAYER_DISPLAY', [arg0]);
	},
	//客显设备结束

	//辅屏控制
	oldLedInit: function (success, error) {
		exec(success, error, 'AndroidInterface', 'OLDLED_INIT', []);
	},
	oldLedShow: function (arg0, success, error) { //[s1, s2]
		exec(success, error, 'AndroidInterface', 'OLDLED_SHOW', arg0);
	},
	oldLedClear: function (success, error) {
		exec(success, error, 'AndroidInterface', 'OLDLED_CLEAR', []);
	},
	//辅屏控制结束

	//版本获取
	versionInit: function (success, error) {
		exec(success, error, 'AndroidInterface', 'VERSION_INIT', []);
	},
	versionGet: function (arg0, success, error) { //arg0:需要获取版本的名字
		exec(success, error, 'AndroidInterface', 'VERSION_GET', [arg0]);
	},
	//版本获取结束

	//LCD
	lcdInit: function (success, error) {
		exec(success, error, 'AndroidInterface', 'LCD_INIT', []);
	},
	lcdSet: function (arg0, success, error) {
		if (arg0) {
			exec(success, error, 'AndroidInterface', 'LCD_CONTROL', ['on']);
		} else {
			exec(success, error, 'AndroidInterface', 'LCD_CONTROL', ['off']);
		}
	},
	//LCD结束

	//扫码接口
	scannerInit: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SCANNER_INIT', []);
	},
	scannerScan: function (arg0, success, error) { //[CameraBeanZbar,ExternalMap]
		exec(success, error, 'AndroidInterface', 'SCANNER_SCAN', arg0);
	},
	scannerCancle: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SCANNER_CANCLE', []);
	},
	//扫码接口结束

	//WIFI探针
	wifiProboInit: function (success, error) {
		exec(success, error, 'AndroidInterface', 'WIFIPROBE_INIT', []);
	},
	wifiProboOpen: function (success, error) {
		exec(success, error, 'AndroidInterface', 'WIFIPROBE_OPEN', []);
	},
	wifiProboClose: function (success, error) {
		exec(success, error, 'AndroidInterface', 'WIFIPROBE_CLOSE', []);
	},
	wifiProboStartGetInfo: function (success, error) {
		exec(success, error, 'AndroidInterface', 'WIFIPROBE_START_GET_INFO', []);
	},
	wifiProboStopGetInfo: function (success, error) {
		exec(success, error, 'AndroidInterface', 'WIFIPROBE_STOP_GET_INFO', []);
	},
	wifiProboStartGetState: function (success, error) {
		exec(success, error, 'AndroidInterface', 'WIFIPROBE_START_GET_STATE', []);
	},
	wifiProboStopGetState: function (success, error) {
		exec(success, error, 'AndroidInterface', 'WIFIPROBE_STOP_GET_STATE', []);
	},
	//WIFI探针结束

	//串口设备开始
	serialInit: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SERIAL_INIT', []);
	},
	serialOpen: function (arg0, success, error) { //[port,baudRate]
		exec(success, error, 'AndroidInterface', 'SERIAL_OPEN', arg0);
	},
	serialRead: function (arg0, success, error) { //[port,timeout]
		exec(success, error, 'AndroidInterface', 'SERIAL_RECEIVE', arg0);
	},
	serialWrite: function (arg0, success, error) { //[port,data,boolHexWrite]
		exec(success, error, 'AndroidInterface', 'SERIAL_SEND', arg0);
	},
	serialClose: function (arg0, success, error) { //port
		exec(success, error, 'AndroidInterface', 'SERIAL_CLOSE', [arg0]);
	},
	//串口设备结束

	//签名帮助
	signInit: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SIGN_INIT', []);
	},
	signDo: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SIGN_DO', []);
	},
	signCancel: function (success, error) {
		exec(success, error, 'AndroidInterface', 'SIGN_CANCEL', []);
	},
	//签名帮助结束

	//PSAM
	pasmInit: function (success, error) {
		exec(success, error, 'AndroidInterface', 'PSAM_INIT', []);
	},
	pasmOpen: function (index, success, error) {
		exec(success, error, 'AndroidInterface', 'PSAM_OPEN', [index]);
	},
	pasmClose: function (index, success, error) {
		exec(success, error, 'AndroidInterface', 'PSAM_CLOSE', [index]);
	},
	pasmReset: function (index, success, error) {
		exec(success, error, 'AndroidInterface', 'PSAM_RESET', [index]);
	},
	pasmSetEtu: function (args, success, error) { //[index,time]
		exec(success, error, 'AndroidInterface', 'PSAM_SET_ETU', args);
	},
	pasmSendApdu: function (args, success, error) { //[index,cmd]
		exec(success, error, 'AndroidInterface', 'PSAM_SEND_APDU', args);
	},
	//PSAM end

	//MEM102
	mem102Init: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM102_INIT', []);
	},
	mem102Open: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM102_OPEN', []);
	},
	mem102Close: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM102_CLOSE', []);
	},
	mem102Status: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM102_STATUS', []);
	},
	mem102ChangePwd: function (args, success, error) { //pwd
		exec(success, error, 'AndroidInterface', 'MEM102_CHANGE_PWD', [args]);
	},
	mem102Reset: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM102_RESET', []);
	},
	mem102Read: function (args, success, error) { //[blockNum, len]
		exec(success, error, 'AndroidInterface', 'MEM102_READ', args);
	},
	mem102EraseData: function (args, success, error) { //[blockNum, len]
		exec(success, error, 'AndroidInterface', 'MEM102_ERASE_DATA', args);
	},
	mem102VerifyPwd: function (args, success, error) { //pwd
		exec(success, error, 'AndroidInterface', 'MEM102_VERIFY_PWD', [args]);
	},
	mem102Write: function (args, success, error) { //[blockNum,data]
		exec(success, error, 'AndroidInterface', 'MEM102_WRITE', args);
	},
	mem102VerticalOpen: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM102_VERTICAL_OPEN', []);
	},
    mem102VerticalClose: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM102_VERTICAL_CLOSE', []);
	},
	mem102VerticalStatus: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM102_VERTICAL_STATUS', []);
	},
	mem102VerticalReset: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM102_VERTICAL_RESET', []);
	},
	mem102VerticalRead: function (args, success, error) { //[blockNum, len]
		exec(success, error, 'AndroidInterface', 'MEM102_VERTICAL_READ', args);
	},
	mem102VerticalVerifyPwd: function (args, success, error) { //psw
		exec(success, error, 'AndroidInterface', 'MEM102_VERTICAL_VERIFY_PWD', [args]);
	},
	mem102VerticalWrite: function (args, success, error) { //[blockNum, data]
		exec(success, error, 'AndroidInterface', 'MEM102_VERTICAL_WRITE', args);
	},
	mem102VerticalChangePwd: function (args, success, error) { //pwd
		exec(success, error, 'AndroidInterface', 'MEM102_VERTICAL_CHANGE_PWD', [args]);
	},
	mem102VerticalEraseData: function (args, success, error) { //[blockNum, len]
		exec(success, error, 'AndroidInterface', 'MEM102_VERTICAL_ERASE_DATA', args);
	},
	//MEM102 end

	//MEM1608
	mem1608Init: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM1608_INIT', []);
	},
	mem1608Open: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM1608_OPEN', []);
	},
	mem1608Close: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM1608_CLOSE', []);
	},
	mem1608Read: function (args, success, error) { //[byte area, byte location, int length]
		exec(success, error, 'AndroidInterface', 'MEM1608_READ', args);
	},
	mem1608ReadPwdErrorCount: function (args, success, error) { //[byte sArea, byte type]
		exec(success, error, 'AndroidInterface', 'MEM1608_READ_PWD_ERROR_COUNTER', args);
	},
	mem1608Write: function (args, success, error) { //byte sArea, byte location, byte[] data,
		exec(success, error, 'AndroidInterface', 'MEM1608_WRITE', args);
	},
	mem1608Reset: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM1608_RESET', []);
	},
	mem1608Status: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM1608_STATUS', []);
	},
	mem1608VerifyPwd: function (args, success, error) { //[byte sArea, byte type, byte[] psw]
		exec(success, error, 'AndroidInterface', 'MEM1608_VERIFY_PWD', args);
	},
	mem1608ReadAccessAuth: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM1608_READ_ACCESS_AUTH', []);
	},
	mem1608VerticalOpen: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM1608_VERTICAL_OPEN', []);
	},
	mem1608VerticalClose: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM1608_VERTICAL_CLOSE', []);
	},
	mem1608VerticalReadPwdErrorCount: function (args, success, error) { //[byte sArea, byte type]
		exec(success, error, 'AndroidInterface', 'MEM1608_VERTICAL_READ_PWD_ERROR_COUNTER', args);
	},
	mem1608VerticalRead: function (args, success, error) { //[byte area, byte location, int length]
		exec(success, error, 'AndroidInterface', 'MEM1608_VERTICAL_READ', args);
	},
	mem1608VerticalWrite: function (args, success, error) { //[byte sArea, byte location, byte[] data]
		exec(success, error, 'AndroidInterface', 'MEM1608_VERTICAL_WRITE', args);
	},
	mem1608VerticalReset: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM1608_VERTICAL_RESET', []);
	},
	mem1608VerticalStatus: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM1608_VERTICAL_STATUS', []);
	},
	mem1608VerticalVerifyPwd: function (args, success, error) { //[byte sArea, byte type, byte[] psw]
		exec(success, error, 'AndroidInterface', 'MEM1608_VERTICAL_VERIFY_PWD', args);
	},
	mem1608VerticalReadAccessAuth: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM1608_VERTICAL_READ_ACCESS_AUTH', []);
	},
	//MEM1608 end

	//MEM4428
	mem4428Init: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4428_INIT', []);
	},
	mem4428Open: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4428_OPEN', []);
	},
	mem4428Close: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4428_CLOSE', []);
	},
	mem4428Status: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4428_STATUS', []);
	},
	mem4428Reset: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4428_RESET', []);
	},
	mem4428VerifyPwd: function (args, success, error) { //byte[] psw
		exec(success, error, 'AndroidInterface', 'MEM4428_VERIFY_PWD', [args]);
	},
	mem4428NoProtectRead: function (args, success, error) { //[int blockNum, int length]
		exec(success, error, 'AndroidInterface', 'MEM4428_NO_PROTECT_READ', args);
	},
	mem4428ProtectRead: function (args, success, error) { //[int blockNum, int length]
		exec(success, error, 'AndroidInterface', 'MEM4428_PEOTECT_READ', args);
	},
	mem4428NoProtectWrite: function (args, success, error) { //int blockNum, byte[] data
		exec(success, error, 'AndroidInterface', 'MEM4428_NO_PROTECT_WRITE', args);
	},
	mem4428ProtectWrite: function (args, success, error) { //int blockNum, byte[] data
		exec(success, error, 'AndroidInterface', 'MEM4428_PROTECT_WRITE', args);
	},
	mem4428VerticalOpen: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4428_VERTICAL_OPEN', []);
	},
	mem4428VerticalClose: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4428_VERTICAL_CLOSE', []);
	},
	mem4428VerticalStatus: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4428_VERTICAL_STATUS', []);
	},
	mem4428VerticalReset: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4428_VERTICAL_RESET', []);
	},
	mem4428VerticalVerifyPwd: function (args, success, error) { //byte[] psw
		exec(success, error, 'AndroidInterface', 'MEM4428_VERTICAL_VERIFY_PWD', [args]);
	},
	mem4428VerticalNoProtectRead: function (args, success, error) { //[int blockNum, int length]
		exec(success, error, 'AndroidInterface', 'MEM4428_VERTICAL_NO_PROTECT_READ', args);
	},
	mem4428VerticalProtectRead: function (args, success, error) { //[int blockNum, int length]
		exec(success, error, 'AndroidInterface', 'MEM4428_VERTICAL_PEOTECT_READ', args);
	},
	mem4428VerticalNoProtectWrite: function (args, success, error) { //[int blockNum, byte[] data]
		exec(success, error, 'AndroidInterface', 'MEM4428_VERTICAL_NO_PROTECT_WRITE', args);
	},
	mem4428VerticalProtectWrite: function (args, success, error) { //[int blockNum, byte[] data]
		exec(success, error, 'AndroidInterface', 'MEM4428_VERTICAL_PROTECT_WRITE', args);
	},
	//MEM4428 end

	//MEM4442
	mem4442Init: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4442_INIT', []);
	},
	mem4442Open: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4442_OPEN', []);
	},
	mem4442Close: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4442_CLOSE', []);
	},
	mem4442ChangePwd: function (args, success, error) { //byte[] psw
		exec(success, error, 'AndroidInterface', 'MEM4442_CHANGE_PWD', [args]);
	},
	mem4442Read: function (args, success, error) { //[byte blockNum, byte len]
		exec(success, error, 'AndroidInterface', 'MEM4442_READ', args);
	},
	mem4442ReadAtrData: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4442_READ_ATR_DATA', []);
	},
	mem4442WriteProtectData: function (args, success, error) { //[byte blockNum, byte data]
		exec(success, error, 'AndroidInterface', 'MEM4442_WRITE_PROTECT_DATA', args);
	},
	mem4442ReadSecurityData: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4442_READ_SECURITY_DATA', []);
	},
	mem4442ReadProtectData: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4442_READ_PROTECT_DATA', []);
	},
	mem4442Reset: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4442_RESET', []);
	},
	mem4442Status: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4442_STATUS', []);
	},
	mem4442Verify: function (args, success, error) { //[byte ec, byte[] psw]
		exec(success, error, 'AndroidInterface', 'MEM4442_VIRIFY', args);
	},
	mem4442Write: function (args, success, error) { //[byte blockNum, byte data]
		exec(success, error, 'AndroidInterface', 'MEM4442_WRITE', args);
	},
	mem4442ReadData: function (args, success, error) { //[byte blockNum, int len]
		exec(success, error, 'AndroidInterface', 'MEM4442_READ_DATA', args);
	},
	mem4442WriteData: function (args, success, error) { //[byte blockNum, byte[] data]
		exec(success, error, 'AndroidInterface', 'MEM4442_WRITE_DATA', args);
	},
	mem4442VerticalChangePwd: function (args, success, error) { //byte[] psw
		exec(success, error, 'AndroidInterface', 'MEM4442_VERTICAL_CHANGE_PWD', [args]);
	},
	mem4442VerticalOpen: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4442_VERTICAL_OPEN', []);
	},
	mem4442VerticalClose: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4442_VERTICAL_CLOSE', []);
	},
	mem4442VerticalRead: function (args, success, error) { //[byte blockNum, byte len]
		exec(success, error, 'AndroidInterface', 'MEM4442_VERTICAL_READ', args);
	},
	mem4442VerticalReadAtrData: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4442_VERTICAL_READ_ATR_DATA', []);
	},
	mem4442VerticalWriteProtectData: function (args, success, error) { //[byte blockNum, byte data]
		exec(success, error, 'AndroidInterface', 'MEM4442_VERTICAL_WRITE_PROTECT_DATA', args);
	},
	mem4442VerticalReadSecurityData: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4442_VERTICAL_READ_SECURITY_DATA', []);
	},
	mem4442VerticalReadProtectData: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4442_VERTICAL_READ_PROTECT_DATA', []);
	},
	mem4442VerticalReset: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4442_VERTICAL_RESET', []);
	},
	mem4442VerticalStatus: function (success, error) {
		exec(success, error, 'AndroidInterface', 'MEM4442_VERTICAL_STATUS', []);
	},
	mem4442VerticalVerify: function (args, success, error) { //[byte ec, byte[] pwd]
		exec(success, error, 'AndroidInterface', 'MEM4442_VERTICAL_VERIFY', args);
	},
	mem4442VerticalWrite: function (args, success, error) { //[byte blockNum, byte data]
		exec(success, error, 'AndroidInterface', 'MEM4442_VERTICAL_WRITE', args);
	},
	mem4442VerticalReadData: function (args, success, error) { //[byte blockNum, int len]
		exec(success, error, 'AndroidInterface', 'MEM4442_VERTICAL_READ_DATA', args);
	},
	mem4442VerticalWriteData: function (args, success, error) { //[byte blockNum, byte[] data]
		exec(success, error, 'AndroidInterface', 'MEM4442_VERTICAL_WRITE_DATA', args);
	},
	//MEM4442 end

	//PINPAD
	pinpadInit: function (success, error) {
		exec(success, error, 'AndroidInterface', 'PINPAD_INIT', []);
	},
    pinpadOpen: function (args, success, error) { //baudrate
		exec(success, error, 'AndroidInterface', 'PINPAD_OPEN', [args]);
	},
	pinpadClose: function (success, error) {
		exec(success, error, 'AndroidInterface', 'PINPAD_CLOSE', []);
	},
	pinpadStopGetPin: function (success, error) {
		exec(success, error, 'AndroidInterface', 'PINPAD_STOP_GET_PIN', []);
	},
	pinpadGetRamdom: function (success, error) {
		exec(success, error, 'AndroidInterface', 'PINPAD_GET_RANDOM', []);
	},
	pinpadDisperseWkey: function (args, success, error) { //[byte type, byte[] key, byte mIndex, byte wIndex]
		exec(success, error, 'AndroidInterface', 'PINPAD_DISPERSE_W_KEY', args);
	},
	pinpadDisperseMkey: function (args, success, error) { //[byte[] key, byte index]
		exec(success, error, 'AndroidInterface', 'PINPAD_DISPERSE_M_KEY', args);
	},
	pinpadDisperseSm4mKey: function (args, success, error) { //[int type, byte[] key, byte index]
		exec(success, error, 'AndroidInterface', 'PINPAD_DISPERSE_SM4M_KEY', args);
	},
	pinpadConfirmGetPin: function (success, error) {
		exec(success, error, 'AndroidInterface', 'PINPAD_CONFIRM_GET_PIN', []);
	},
	pinpadGetPin: function (args, success, error) { //PinInfo
		exec(success, error, 'AndroidInterface', 'PINPAD_GET_PIN', [args]);
	},
	pinpadGetMac: function (args, success, error) { //[byte wkeyIndex, byte modeMethodType, byte modeEntype, String data, String random,HashMap map]
		exec(success, error, 'AndroidInterface', 'PINPAD_GET_MAC', args);
	},
	pinpadEncryptData: function (args, success, error) { //[byte encryptMode, byte workKeyIndex, byte[] data, byte[] random]
		exec(success, error, 'AndroidInterface', 'PINPAD_ENCRYPT_DATA', args);
	},
	pinpadDisplay: function (args, success, error) { //[String strOne, String strTwo]
		exec(success, error, 'AndroidInterface', 'PINPAD_DISPLAY', args);
	},
	pinpadGetPinpadSn: function (args, success, error) { //index
		exec(success, error, 'AndroidInterface', 'PINPAD_GET_PINPAD_SN', [args]);
	},
	pinpadOpenPinpadKeyboard: function (success, error) {
		exec(success, error, 'AndroidInterface', 'PINPAD_OPEN_PINPAD_KEYBOARD', []);
	},
	pinpadClosePinpadKeyboard: function (success, error) {
		exec(success, error, 'AndroidInterface', 'PINPAD_CLOSE_PINPAD_KEYBOARD', []);
	},
	pinpadKeyboardState: function (success, error) {
		exec(success, error, 'AndroidInterface', 'PINPAD_PINPAD_KEYBOARD_STATE', []);
	},
	pinpadRfCardStatus: function (success, error) {
		exec(success, error, 'AndroidInterface', 'PINPAD_RF_CARD_STATUS', []);
	},
	pinpadRfCardReset: function (success, error) {
		exec(success, error, 'AndroidInterface', 'PINPAD_RF_CARD_RESET', []);
	},
	pinpadRfCardApdu: function (args, success, error) { //byte[] apdu
		exec(success, error, 'AndroidInterface', 'PINPAD_RF_CARD_APDU', [args]);
	},
	pinpadRfCardHalt: function (success, error) {
		exec(success, error, 'AndroidInterface', 'PINPAD_RF_CARD_HALT', []);
	},
	pinpadSetKeyVoice: function (args, success, error) { //boolean voice
		exec(success, error, 'AndroidInterface', 'PINPAD_SET_KEY_VOICE', [args]);
	},
	pinpadDownKey: function (args, success, error) { //byte[] key
		exec(success, error, 'AndroidInterface', 'PINPAD_DOWN_KEY', [args]);
	},
	pinpadDownTmk: function (args, success, error) { //[byte[] tmk, byte[] checkValue, byte keyId]
		exec(success, error, 'AndroidInterface', 'PINPAD_DOWN_TMK', args);
	},
	pinpadDownTek: function (args, success, error) { //[byte tekId, byte[] key, byte[] checkValue]
		exec(success, error, 'AndroidInterface', 'PINPAD_DOWN_TEK', args);
	},
	pinpadDownkoadKekEncryptKek: function (args, success, error) { //[byte type, byte tekId, byte mKeyId, byte[] key, byte[] checkValue]
		exec(success, error, 'AndroidInterface', 'PINPAD_DOWNLOAD_KEK_ENCRYPT_KEK', args);
	},
	pinpadGetPinpadSnFromProject: function (args, success, error) { //[int n1, int n2]
		exec(success, error, 'AndroidInterface', 'PINPAD_GET_PINPAD_SN_FROM_PROJECT', args);
	},
	pinpadDownloadKEKEncryptedMkey: function (args, success, error) { //[byte tekId, byte mKeyId, byte[] key, byte[] checkValue, int length]
		exec(success, error, 'AndroidInterface', 'PINPAD_DOWNLOAD_KEK_ENCRYPTED_MKEY', args);
	},
	pinpadDownloadKEKEncryptedWkey: function (args, success, error) { //[byte type, byte tekId, byte mKeyId, byte wKeyId, byte[] key, byte[] checkValue,int length]
		exec(success, error, 'AndroidInterface', 'PINPAD_DOWNLOAD_KEK_ENCRYPTED_WKEY', args);
	},
	pinpadStartSign: function (args, success, error) { //String pkg
		exec(success, error, 'AndroidInterface', 'PINPAD_START_SIGN', [args]);
	},
	pinpadStopSign: function (success, error) {
		exec(success, error, 'AndroidInterface', 'PINPAD_STOP_SIGN', []);
	},
	pinpadGetPinWithTime: function (args, success, error) { //[int i, PinInfo pinInfo]
		exec(success, error, 'AndroidInterface', 'PINPAD_GET_PIN_WITH_TIME', args);
	},
	pinpadGetPinpadSnFromExternal: function (args, success, error) { //[byte b1, byte b2]
		exec(success, error, 'AndroidInterface', 'PINPAD_GET_PINPAD_SN_FROM_EXTERNAL', args);
	},
	pinpadSetPinpadCancelMode: function (args, success, error) { //byte type
		exec(success, error, 'AndroidInterface', 'PINPAD_SET_PINPAD_CANCEL_MODE', [args]);
	},
	pinpadGetHardwareSn: function (success, error) {
		exec(success, error, 'AndroidInterface', 'PINPAD_GET_HARDWARE_SN', []);
	},
	pinpadGetMacForSnk: function (args, success, error) { //[String data, String random]
		exec(success, error, 'AndroidInterface', 'PINPAD_GET_MAC_FOR_SNK', args);
	},
	pinpadSetInnerPinpadBeep: function (args, success, error) { //boolean open
		exec(success, error, 'AndroidInterface', 'PINPAD_SET_INNER_PINPAD_BEEP', [args]);
	},
	//PINPAD end

	//EMV 设备
	emvInit: function (success, error) {
		exec(success, error, 'AndroidInterface', 'EMV_INIT', []);
	},
    emvCheckCard: function (args,success, error) {//[boolean checkMag, boolean checkIc, boolean checkRf, int timeout]
		exec(success, error, 'AndroidInterface', 'EMV_CHECK_CARD', args);
	},
    emvCancleCheckCard: function (success, error) { 
		exec(success, error, 'AndroidInterface', 'EMV_CANCEL_CHECK_CARD', []);
	},
    emvProcessPboc: function (args,success, error) {//EmvTransData
		exec(success, error, 'AndroidInterface', 'EMV_PROCESS_PBOC', [args]);
	},
    emvEndPboc: function (success, error) {
		exec(success, error, 'AndroidInterface', 'EMV_END_PBOC', []);
	},
    emvAbortPboc: function (success, error) {
		exec(success, error, 'AndroidInterface', 'EMV_ABORT_PBOC', []);
	},
    emvClearKernelIcTransLog: function (success, error) {
		exec(success, error, 'AndroidInterface', 'EMV_CLEAR_KERNEL_IC_TRANS_LOG', []);
	},
    emvReadKernalData: function (args,success, error) {//[byte[] tagList, byte[] outBuffer]
		exec(success, error, 'AndroidInterface', 'EMV_READ_KERNEL_DATA', args);
	},
    emvSetTlv: function (args,success, error) {//[int index, byte[] data]
		exec(success, error, 'AndroidInterface', 'EMV_SET_TLV', args);
	},
    emvRarseTlv: function (args,success, error) {//[String tag, String data]
		exec(success, error, 'AndroidInterface', 'EMV_PARSE_TLV', args);
	},
    emvImportAmount: function (args,success, error) {//String amount
		exec(success, error, 'AndroidInterface', 'EMV_IMPORT_AMOUNT', [args]);
	},
    emvImportAidSelectRes: function (args,success, error) {//int index
		exec(success, error, 'AndroidInterface', 'EMV_IMPORT_AID_SELECT_RES', [args]);
	},
    emvImportCardInfoRes: function (args,success, error) {//boolean success
		exec(success, error, 'AndroidInterface', 'EMV_IMPORT_CONFIRM_CARD_INFO_RES', [args]);
	},
    emvImportPin: function (args,success, error) {//String pin
		exec(success, error, 'AndroidInterface', 'EMV_IMPORT_PIN', [args]);
	},
    emvImportUserAuthRes: function (args,success, error) {//boolean success
		exec(success, error, 'AndroidInterface', 'EMV_IMPORT_USER_AUTH_RES', [args]);
	},
    emvImportMsgConfirmRes: function (args,success, error) {//boolean success
		exec(success, error, 'AndroidInterface', 'EMV_IMPORT_MSG_CONFIRM_RES', [args]);
	},
    emvImportEcashTipConfirmRes: function (args,success, error) {//boolean eCashTipConfirmResult
		exec(success, error, 'AndroidInterface', 'EMV_IMPORT_ECASH_TIP_CONFIRM_RES', [args]);
	},
    emvImportOnloneResp: function (args,success, error) {//[boolean success, String code, String result]
		exec(success, error, 'AndroidInterface', 'EMV_IMPORT_ONLINE_RESP', args);
	},
    emvUpdateAid: function (args,success, error) {//[int index, String data]
		exec(success, error, 'AndroidInterface', 'EMV_UPDATE_AID', args);
	},
    emvUpdateCapk: function (args,success, error) {//[int index, String data]
		exec(success, error, 'AndroidInterface', 'EMV_UPDATE_CAPK', args);
	},
    emvAidParanRead: function (args,success, error) {//byte[] index
		exec(success, error, 'AndroidInterface', 'EMV_AID_PARAM_READ', [args]);
	},
    emvCaPublicKeyParamRead: function (args,success, error) {//byte[] index
		exec(success, error, 'AndroidInterface', 'EMV_CA_PUBLIC_KEY_PARAM_READ', [args]);
	},
    emvSetParameters: function (args,success, error) {//[byte tag, byte value]
		exec(success, error, 'AndroidInterface', 'EMV_SET_PARAMETERS', args);
	},
    emvUpdateDrlParams: function (args,success, error) {//[int index, DRLParam drlParam]
		exec(success, error, 'AndroidInterface', 'EMV_UPDATE_DRL_PARAMS', args);
	},
    emvDrlParamRead: function (args,success, error) {//byte[] data
		exec(success, error, 'AndroidInterface', 'EMV_DRL_PARAM_READ', [args]);
	},
    emvSetIcSlot: function (args,success, error) {//byte index
		exec(success, error, 'AndroidInterface', 'EMV_SET_IC_SLOT', [args]);
	},
    emvResultOfIssueVoiceRefence: function (args,success, error) {//byte[] result
		exec(success, error, 'AndroidInterface', 'EMV_RESULT_OF_ISSUER_VOICE_REFERENCE', [args]);
	},
	//EMV 设备 end
};
module.exports = cpaySdkPlugin;