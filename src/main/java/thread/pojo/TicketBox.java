package thread.pojo;

import java.util.HashMap;
import java.util.Map;

public class TicketBox {
    /**
     * 票统计pojo�? 剩余票数�? 卖票�?���?
     */
    private int ticketNum;
    private int money;

    // private Map<Integer,Integer> ticketMap = new HashMap<Integer, Integer>();
    public TicketBox(int ticketNum) {
	super();
	this.ticketNum = ticketNum;
    }

    public synchronized void sell(String curThreadName) {
	ticketNum--;
	System.out.println(curThreadName + " 卖出�?��," + " 卖票剩余�? + ticketNum");
	// ticketMap.put(ticketNum, ticketMap.get(ticketNum) == null ? 1 :
	// ticketMap.get(ticketNum) + 1);
    }

    public void collectMoney(String curThreadName) {
	System.out.println(curThreadName + " 收一块钱");
	money++;
    }

    public synchronized int getTicket() {
	return ticketNum;
    }

    public synchronized void setTicket(int ticketNum) {
	this.ticketNum = ticketNum;
    }

    // public synchronized Map<Integer, Integer> getTicketMap() {
    // return ticketMap;
    // }
    // public synchronized void setTicketMap(Map<Integer, Integer> ticketMap) {
    // this.ticketMap = ticketMap;
    // }
    public int getMoney() {
	return money;
    }

    public void setMoney(int money) {
	this.money = money;
    }
}
