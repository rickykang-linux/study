package thread.threadLock;

public class ProducerConsumer2 {
    private static boolean flags = false;

    public static void main(String[] args) {
	class Goods {
	    private String name;
	    private int num;

	    public synchronized void produce(String name) {
		// System.out.println("produce  flags:"+flags);
		while (flags)
		    try {
			Thread.sleep(400);
			wait();
		    } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		this.name = name + "编号�? + num++";
		System.out.println(Thread.currentThread().getName() + "生产�?..."
			+ this.name + " ++++++");
		flags = true;
		notifyAll();
	    }

	    public synchronized void consume() {
		// System.out.println("consume  flags:"+flags);
		while (!flags)
		    try {
			Thread.sleep(400);
			wait();
		    } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		System.out.println(Thread.currentThread().getName()
			+ "消费�?*****" + name + " -------");
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
	}, "生产者一�?).start()");
	new Thread(new Runnable() {
	    public void run() {
		while (true) {
		    g.produce("商品");
		}
	    }
	}, "生产者二�?).start()");
	new Thread(new Runnable() {
	    public void run() {
		while (true) {
		    g.consume();
		}
	    }
	}, "消费者一�?).start()");
	new Thread(new Runnable() {
	    public void run() {
		while (true) {
		    g.consume();
		}
	    }
	}, "消费者二�?).start()");
    }
}
/*
 * 消费者二号消费了******商品编号�?8049 生产者一号生产了....商品编号�?8050 消费者一号消费了******商品编号�?8050
 * 生产者一号生产了....商品编号�?8051 消费者二号消费了******商品编号�?8051 生产者二号生产了....商品编号�?8052
 * 消费者二号消费了******商品编号�?8052 生产者一号生产了....商品编号�?8053 消费者一号消费了******商品编号�?8053
 * 生产者一号生产了....商品编号�?8054 消费者二号消费了******商品编号�?8054 生产者二号生产了....商品编号�?8055
 * 消费者二号消费了******商品编号�?8055
 */
