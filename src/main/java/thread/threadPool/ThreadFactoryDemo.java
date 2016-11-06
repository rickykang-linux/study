package thread.threadPool;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
public class ThreadFactoryDemo implements ThreadFactory {
public static void main(String[] args) throws InterruptedException {
ThreadFactoryDemo factory = new ThreadFactoryDemo();
ExecutorService threadPool = Executors.newCachedThreadPool(factory);
for (int i = 0; i < 2; i++) {
threadPool.execute(new MyThread());
}
TimeUnit.SECONDS.sleep(5);
System.out.println("Thread.activeCount(): "+Thread.activeCount());
System.out.println("main over");
}
public Thread newThread(Runnable r) {
Thread thread = new Thread(r);
thread.setDaemon(true);//设置为后台线�?
return thread;
}
}
class MyThread implements Runnable{
public void run() {
while (true) {
try {
TimeUnit.SECONDS.sleep(1);
} catch (InterruptedException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
System.out.println(Thread.currentThread()+" run ");
}
}
}
