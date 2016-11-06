package thread.threadLock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class test0 {
private static int count = 5;
/**
* @param args
*/
public static void main(String[] args) {
// TODO Auto-generated method stub
ExecutorService threadPool = Executors.newCachedThreadPool();
threadPool.execute(new Runnable() {
public void run() {
try {
Thread.sleep(2000);
} catch (InterruptedException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
count = 6;
System.out.println("A-----count:"+count);
try {
System.out.println("A-----sleeping ...");
Thread.sleep(10000);
System.out.println("A-----sleep over");
} catch (InterruptedException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}
});
threadPool.execute(new Runnable() {
public void run() {
try {
Thread.sleep(2000);
} catch (InterruptedException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
System.out.println("B-----"+count);
}
});
}
}
