package util;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class FileUtil {
private static String separator = null;
public static String formatPath(String strPath){
String result = validEndWith(strPath, getSeparator()); ;
return result ;
}
private static String validEndWith(String strValue , String strEnd){
String result = strValue ;
if ( !strValue.endsWith(strEnd) ) {
result = strValue + strEnd ;
}
return result ;
}
public static String getSeparator() {
if (separator == null) {
separator = System.getProperty("file.separator");
}
return separator;
}
/**
* 获取指定目录下所有文�?
* @param path
*            目录路径
* @return 目录下所有文�?
*/
public static List<String> getFiles(String path) {
File file = new File(path);
if (!file.exists()) {
return null;
}
List<String> totalFiles = new ArrayList<String>();
File[] files = file.listFiles();
if (files != null && files.length > 0) {
for (File tmpfile : files) {
if (tmpfile.exists() && tmpfile.isDirectory()) {
totalFiles.addAll(getFiles(tmpfile.getAbsolutePath()));
} else if (tmpfile.exists() && tmpfile.isFile()) {
totalFiles.add(tmpfile.getAbsolutePath());
}
}
}
return totalFiles;
}
public static void deleteIfExists(String path) {
File file2 = new File(path);
if (file2.exists()) {
FileUtil.deleteLocalFile(file2);
}
}
public static void deleteLocalFile(File Dir) {
if (Dir.exists()) {
if (Dir.isDirectory()) {
File[] listFiles = Dir.listFiles();
for(File file: listFiles){
deleteLocalFile(file);
}
Dir.delete();
}else {
Dir.delete();
}
}
}
}
