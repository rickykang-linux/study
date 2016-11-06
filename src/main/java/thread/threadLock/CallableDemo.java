package thread.threadLock;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
public class CallableDemo implements Callable<String>{
private int id;
public CallableDemo(int id) {
this.id = id;
}
/**
* @param args
* @throws ExecutionException
* @throws InterruptedException
*/
public static void main(String[] args) throws InterruptedException, ExecutionException {
ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
for (int i = 0; i < 10; i++) {
Future<String> result = newCachedThreadPool.submit(new CallableDemo(i));
System.out.println("result of TaskWithResult "+result.get());
TimeUnit.SECONDS.sleep(1);
}
newCachedThreadPool.shutdown();
}
public String call() throws Exception {
// TODO Auto-generated method stub
return "" + id;
}
}
