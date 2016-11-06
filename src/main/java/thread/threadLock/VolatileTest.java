package thread.threadLock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class VolatileTest {
//volatile 保证了可见�?，但不保证原子�?，因此即使申明为volatile�?count的操作可有可能不同步�?
//可见性：某一线程对一操作的结果，其它线程会马上得知�?即缓存执行结果马上刷新到主存
//原子性：某线程执行某�?��作过程中不会发�?线程切换，即要么不执行要么执行到底�?
//synchronized 和Lock 既保证原子�?也保证可见�?�?
public volatile static int count = 0;
public static void inc() {
// 这里延迟1毫秒，使得结果明�?
try {
Thread.sleep(10);
} catch (InterruptedException e) {
}
count++;
}
public static void main(String[] args) throws InterruptedException {
// 同时启动1000个线程，去进行i++计算，看看实际结�?
ExecutorService threadPool = Executors.newCachedThreadPool();
for (int i = 0; i < 1000; i++) {
threadPool.execute(new Runnable() {
public void run() {
System.out.println(Thread.currentThread().getName());
VolatileTest.inc();
}
});
}
threadPool.shutdown();
threadPool.awaitTermination(20, TimeUnit.SECONDS);
// TimeUnit.SECONDS.sleep(5);
// 这里每次运行的�?都有可能不同,可能�?000
System.out.println("运行结果:Counter.count=" + VolatileTest.count);
}
}
