package thread.threadLock;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
//线程不同�?
public class ThreadLockDemo2 implements Runnable {
private static int ticketNum = 1000;
private static Map<Integer,Integer> ticketMap = new HashMap<Integer, Integer>();
private static int runNum = 0;
// ThreadLockDemo(int ticket){
// this.ticket = ticket;
// }
public void reduce(String curThreadName) throws InterruptedException {
System.out.println("卖出�?��");
ticketNum--;
ticketMap.put(ticketNum, ticketMap.get(ticketNum) == null ? 1 : ticketMap.get(ticketNum) + 1);
System.out.println(curThreadName + " 卖票剩余：ticketNum" + ticketNum);
runNum++;
}
public void run() {
String curThreadName = Thread.currentThread().getName();
for (int i = 0; i < 100; i++) {
if (ticketNum > 0) {
try {
Thread.sleep(10);
} catch (InterruptedException e) {
e.printStackTrace();
}
synchronized (ThreadLockDemo2.class) {
try {
reduce(curThreadName);
} catch (InterruptedException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
Thread.yield();
}
// ticket.sell(curThreadName);
}
}
}
/**
* @param args
* @throws InterruptedException
*/
public static void main(String[] args) throws InterruptedException {
//统计十次�?看是否会出现线程不安全的次数�?
int noSafeNum = 0;
for (int i = 0; i < 10; i++) {
if (!isSafe()) {
noSafeNum ++;
}
}
System.out.println("\nnoSafeNum:"+noSafeNum);
}
public static boolean isSafe() throws InterruptedException {
ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
for (int i = 0; i < 5; i++) {
cachedThreadPool.execute(new ThreadLockDemo2());
}
cachedThreadPool.shutdown();
System.out.println("cachedThreadPool shut down");
cachedThreadPool.awaitTermination(20, TimeUnit.SECONDS);
System.out.println("ticketMap size:"+ticketMap.size()+"; runNum: "+runNum);
boolean isSafe = true;
for(Integer key : ticketMap.keySet()){
Integer integer = ticketMap.get(key);
if (integer > 1) {
System.out.println("** map key:"+key + "; value:"+integer);
isSafe = false;
}
}
return isSafe;
}
}
