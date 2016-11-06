package thread.threadLock;

public class ProducerConsumer {
    private volatile static boolean flags = false;

    public static void main(String[] args) {
	class Goods {
	    private String name;
	    private int num;

	    public synchronized void produce(String name) {
		System.out.println("produce");
		if (flags)
		    try {
			Thread.sleep(400);
			wait();
		    } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		this.name = name + "编号�? + num++";
		System.out.println("生产�?..." + this.name);
		flags = true;
		notifyAll();
	    }

	    public synchronized void consume() {
		System.out.println("consume");
		if (!flags)
		    try {
			Thread.sleep(400);
			wait();
		    } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		System.out.println("消费�?*****" + name);
		flags = false;
		notifyAll();
	    }
	}
	final Goods g = new Goods();
	new Thread(new Runnable() {
	    public void run() {
		while (true) {
		    g.produce("商品");
		}
	    }
	}).start();
	new Thread(new Runnable() {
	    public void run() {
		while (true) {
		    g.consume();
		}
	    }
	}).start();
    }
}
