package com.iwinner.wats.rs.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WapConstants {
	private static ResourceBundle CONFIG = ResourceBundle.getBundle("configSptotify");

	// NDMNS File Processing Info
	
	public static String YYYYMMDDHHMMSS="yyyy-MM-dd HH:mm:ss";
	public static String SUCCESS_WRITE_NDMNS_LOG = "";
	public static String FAIL_WRITE_NDMNS_LOG = "";
	public static String INPUT_FOLDER_PATH = "";
	public static String SUCCESS_EXCEL_FILE = "";
	public static String ERROR_EXCEL_FILE = "";
	public static String YEAR_START = "2015";
	public static Integer TOTAL_DATE_UPTO = 20;
	public static String SEMICOLON_SEPARATION = ";";
	public static String EMPTY_SEPARATION = "";
	public static String NULL = null;
	public static Integer ZERO = 0;
	public static Integer ONE = 0;
	public static Integer TWO = 0;
	public static Integer THREEE = 0;
	public static String OPERATION_START_FROM = "OperationName :";
	public static String REQUEST_COMMONAPI = "OperationName : RetrieveCommonApiRequest[";
	public static String SUCCESS_COMMONAPI = "SUCCESS";
	public static String ERROR_COMMONAPI = "ERROR";
	public static String EMPTY_SPACE = " ";
	public static String OPID_SPOTIFY = "opId=2";
	public static String END_BRACKET = "]";
	
	//##### For Record process
	
	public static String MSISDN="MSISDN=";
	public static String METHODID="MethodId=";
	public static String RETURN_LEVEL="Return Level :";
	public static String OPID="opId=";
   public static String  SOURCE_SPOTIFYCODES_FILE="E:\\SpotifyCodes.txt";
	static {
		INPUT_FOLDER_PATH = CONFIG.getString("INPUT_FOLDER_PATH");
		SUCCESS_EXCEL_FILE = CONFIG.getString("SUCCESS_EXCEL_FILE");
		ERROR_EXCEL_FILE = CONFIG.getString("ERROR_EXCEL_FILE");

	}
	public static String INSERT_SPOTIFY_RECORD="INSERT INTO spotifyhits (MSISDN,PROCESS_DATE,PROCESS_TIME,OPID,METHODID,RETURNMESSAGE,RETURNCLIENTID,ENTIRELINE,RETURN_CODE,UPDATEDTIME,UPDATED_DATE) VALUES (?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP(),CURRENT_DATE())";
	public static List<String> headerInfo = new ArrayList<String>();
	static {
		headerInfo.add("ProcessingTime");
		headerInfo.add("OpId");
		headerInfo.add("MethodId");
		headerInfo.add("MSISDN");
		headerInfo.add("ReturnMessage");
	}

	public static void main(String[] args) {
		System.out.println(WapConstants.INPUT_FOLDER_PATH);
	}
}
