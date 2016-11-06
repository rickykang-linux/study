package regex;
public class Demo {
/**
* // 反斜�?
/t 间隔 ('/u0009')
/n 换行 ('/u000A')
/r 回车 ('/u000D')
/d 数字 等价于[0-9]
/D 非数�?等价于[^0-9]
/s 空白符号 [/t/n/x0B/f/r]
/S 非空白符�?[^/t/n/x0B/f/r]
/w 单独字符 [a-zA-Z_0-9]
/W 非单独字�?[^a-zA-Z_0-9]
/f 换页�?
/e Escape
/b �?��单词的边�?
/B �?��非单词的边界
/G 前一个匹配的结束
^为限制开�?
^java     条件限制为以Java为开头字�?
$为限制结�?
java$     条件限制为以java为结尾字�?
.  条件限制�?n以外任意�?��单独字符
java..     条件限制为java后除换行外任意两个字�?
加入特定限制条件「[]�?
[a-z]     条件限制在小写a to z范围中一个字�?
[A-Z]     条件限制在大写A to Z范围中一个字�?
[a-zA-Z] 条件限制在小写a to z或大写A to Z范围中一个字�?
[0-9]     条件限制在小�? to 9范围中一个字�?
[0-9a-z] 条件限制在小�? to 9或a to z范围中一个字�?
[0-9[a-z]] 条件限制在小�? to 9或a to z范围中一个字�?交集)
[]中加入^后加再次限制条件「[^]�?
[^a-z]     条件限制在非小写a to z范围中一个字�?
[^A-Z]     条件限制在非大写A to Z范围中一个字�?
[^a-zA-Z] 条件限制在非小写a to z或大写A to Z范围中一个字�?
[^0-9]     条件限制在非小写0 to 9范围中一个字�?
[^0-9a-z] 条件限制在非小写0 to 9或a to z范围中一个字�?
[^0-9[a-z]] 条件限制在非小写0 to 9或a to z范围中一个字�?交集)
在限制条件为特定字符出现0次以上时，可以使用�?*�?
J*     0个以上J
.*     0个以上任意字�?
J.*D     J与D之间0个以上任意字�?
在限制条件为特定字符出现1次以上时，可以使用�?+�?
J+     1个以上J
.+     1个以上任意字�?
J.+D     J与D之间1个以上任意字�?
在限制条件为特定字符出现�?�?次以上时，可以使用�??�?
JA?     J或�?JA出现
限制为连续出现指定次数字符�?{a}�?
J{2}     JJ
J{3}     JJJ
文字a个以上，并且「{a,}�?
J{3,}     JJJ,JJJJ,JJJJJ,???(3次以上J并存)
文字个以上，b个以下�?{a,b}�?
J{3,5}     JJJ或JJJJ或JJJJJ
两�?取一「|�?
J|A     J或A
Java|Hello     Java或Hello
*/
/**
* @param args
*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String obj = "IPTV_VideoInfo_adfsds.dat";
		boolean result = obj.matches("^IPTV_VideoInfo_.*dat$");
		String obj2 = "adcontent_adfsds.dat";
		boolean result2 = obj2.matches("^adcontent_.*txt$");
		System.out.println(result);
	}
	
	
	
	private void matchs() {
		StringBuffer aBuffer;
		String test = "13812456985";
		String reg = "1[359]\\d{9}";
		boolean matches = test.matches(reg);

	}
	
	private void splits() {
		String test = "zhangsan   lisi   wangwu";
		String reg = " +";
		String[] splits = test.split(reg);

		
		//"([a-z])\\1": 括号( )内表示一个规则组， 是第一个组，之后这个组规则的匹配结果可以用\1使用
		//"([a-z])\\1+"即叠词
	}
}
