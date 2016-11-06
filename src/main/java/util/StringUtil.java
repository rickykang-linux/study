package util;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
* 处理字符�?String)相关的工具类
*
* @author 10157733
*/
public class StringUtil {
/**
* 用于js与后台之间中文转码，js�?temName = encodeURI(encodeURI(temName));
* @param decodeString
* @return
*/
public String decode(String decodeString){
String string = decodeString;
try{
string = URLDecoder.decode(decodeString, "utf-8");
}catch(Exception e){
e.printStackTrace();
}
return string;
}
/**
* 判断是否是数据类�?
* @param orginal
* @return
*/
public static boolean isRealNumber(String orginal){
return isWholeNumber(orginal) || isDecimal(orginal);
}
/**
* 判断是否为整�?
* @param orginal
* @return
*/
public static boolean isWholeNumber(String orginal) {
return isMatch("[+-]{0,1}0", orginal) || isMatch("^\\+{0,1}[1-9]\\d*", orginal) || isMatch("^-[1-9]\\d*", orginal);
}
/**
* 判断是否为小�?
* @param orginal
* @return
*/
public static boolean isDecimal(String orginal){
return isMatch("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+", orginal);
}
private static boolean isMatch(String regex, String orginal){
if (orginal == null || orginal.trim().equals("")) {
return false;
}
Pattern pattern = Pattern.compile(regex);
Matcher isNum = pattern.matcher(orginal);
return isNum.matches();
}
/**
* 字段分割,分割后去除字段两边空�?
* @param obj
* @param splitValue
* @return
*/
public static String[] split(String obj,String splitValue) {
String[] fieldValues;
if(splitValue.equals("|")){
fieldValues = obj.split("\\|");
}else if(splitValue.equals("*")){
fieldValues = obj.split("\\*");
}else {
fieldValues = obj.split(splitValue);
}
for (int i = 0; i < fieldValues.length; i++) {
fieldValues[i] = fieldValues[i].trim();
}
return fieldValues;
}
/**
* 去除数组中每个成员的换行符和两边空格
* @param array
* @return
*/
public static String[] normalize(String[] array) {
for (int i = 0; i < array.length; i++) {
String obj = array[i];
obj = obj.replaceAll("\n", "");
obj = obj.trim();
array[i] = obj;
}
return array;
}
public static String formatPath(String strPath) {
String result = validEndWith(strPath, getSeparator());
return result;
}
private static String validEndWith(String strValue, String strEnd) {
String result = strValue;
if (!strValue.endsWith(strEnd)) {
result = strValue + strEnd;
}
return result;
}
public static String getSeparator() {
return "/";
}
/**
* 判断指定的字符串是否为空, 为空返回"true", 不为空返�?false"
*/
public static boolean isEmpty(String str) {
return ((str == null) || ("".equals(str.trim())));
}
/**
* 将指定的字符串按照指定的分隔符分�?
*
* @param str
*            指定的字符串
* @param regex
*            指定的分隔符(正则形式)
* @return 分隔后的String[]
*/
public static String[] separateStringWithRegex(String str, String regex) {
if (isEmpty(str)) {
return new String[] { "" };
}
String tmp = str + "suffix";
String[] s = tmp.split(regex);
String lastField = s[s.length - 1];
s[s.length - 1] = lastField.substring(0, lastField.length() - 6);
return s;
}
/**
* 将指定的字符串按","分隔
*
* @param str
* @return
*/
public static String[] separateStringWithComma(String str) {
return separateStringWithRegex(str, ",");
}
/**
* 将字段分隔符转换成正则表达式
*
* @param src
*            用户配置的分隔符
* @return 转换成正则表达式的分隔符
*/
public static String transferStringToRegex(String src) {
String str = src;
List<Character> cs = Arrays.asList('.', '$', '|', '(', ')', '[', ']', '{', '}', '^', '?', '*', '+');
for (int i = 0; i < src.length(); i++) {
char c = src.charAt(i);
if (cs.contains(c)) {
str = str.replace(String.valueOf(c), "\\" + c);
}
}
return str;
}
public static Double toDouble(String obj) {
return Double.parseDouble(obj.toString());
}
public static Integer toInteger(String obj) {
Double objDouble = Double.parseDouble(obj.toString());
return objDouble.intValue();
}
public static String ArrayToString(String[] words, String delimiter) {
if (words.length == 0) {
return "";
}
StringBuffer result = new StringBuffer("");
for(String word: words){
result = result.append(word).append(delimiter);
}
String string = result.toString();
return string.substring(0,string.length()-1);
}
}
