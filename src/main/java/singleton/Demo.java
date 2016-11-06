package singleton;
public class Demo {
private static Demo demo = null;
private Demo() {
// TODO Auto-generated constructor stub
}
public static Demo getInstance(){
if (demo == null) {
demo = new Demo();
}
return demo;
}
public static void main(String[] args){
Demo demo1 = Demo.getInstance();
Demo demo2 = Demo.getInstance();
// Demo.c
if (demo2 == demo1) {
System.out.println("æ˜¯åŒä¸?¸ªå®ä¾‹");
}
}
}
