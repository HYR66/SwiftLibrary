package info;

import java.util.ArrayList;

/**
 * dataBase:Êý¾Ý¿â
 * 
 * @author ·äÄñSwift
 *
 */
public class dataBase {
	/*
	 * cloud/loginMaster
	 */
	public static String adminName = "";
	public static String adminPw = "";

	/*
	 * cloud/loginInfo
	 */
	public static ArrayList<String> name = new ArrayList<String>();
	public static ArrayList<String> pw = new ArrayList<String>();
	public static ArrayList<String> trueName = new ArrayList<String>();
	public static ArrayList<Boolean> valid = new ArrayList<Boolean>();
	public static ArrayList<String> phone = new ArrayList<String>();
	public static ArrayList<Integer> credit = new ArrayList<Integer>();
	public static ArrayList<Integer> regYear = new ArrayList<Integer>();
	public static ArrayList<Integer> regMonth = new ArrayList<Integer>();
	public static ArrayList<Integer> regDay = new ArrayList<Integer>();

	/*
	 * cloud/book/bookInfo
	 */
	public static ArrayList<String> bookName = new ArrayList<String>();
	public static ArrayList<String> bookAuthor = new ArrayList<String>();
	public static ArrayList<String> bookPress = new ArrayList<String>();
	public static ArrayList<String> bookISBN = new ArrayList<String>();
	public static ArrayList<String> bookClass = new ArrayList<String>();
	public static ArrayList<String> bookCPos = new ArrayList<String>();
	public static ArrayList<Integer> bookPopular = new ArrayList<Integer>();
	public static ArrayList<Integer> bookLeft = new ArrayList<Integer>();
	public static ArrayList<Integer> bookTotal = new ArrayList<Integer>();
	public static ArrayList<Double> bookPrice = new ArrayList<Double>();
	public static ArrayList<Boolean> fileValid = new ArrayList<Boolean>();

	/*
	 * cloud/book/classList
	 */
	public static ArrayList<String> className = new ArrayList<String>();
}
