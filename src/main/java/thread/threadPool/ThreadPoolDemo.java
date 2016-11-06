package thread.threadPool;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ThreadPoolDemo {
private static Integer num = 5;
public static void main(String[] args) {
ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
// ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>();
for (int i = 0; i < 10; i++) {
// cachedThreadPool.execute(new CountThread(num));
cachedThreadPool.execute(new Runnable() {
public void run() {
synchronized (num) {
num--;
System.out.println("run begin");
Thread.yield();
System.out.println(Thread.currentThread().getName()+
", Priority:"+Thread.currentThread().getPriority()+
", state:"+Thread.currentThread().getState().name() +
", no:"+num);
}
System.out.println("run over");
}
});
if (i == 2) {
cachedThreadPool.shutdown();
}
}
}
}
