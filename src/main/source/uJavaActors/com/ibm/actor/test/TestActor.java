package com.ibm.actor.test;

import java.util.Date;

import com.ibm.actor.Actor;
import com.ibm.actor.DefaultMessage;
import com.ibm.actor.Message;

/**
 * http://www.ibm.com/developerworks/cn/java/j-javaactors/#download
 * An akka.actor that sends messages while counting down a send count.
 * 
 * @author BFEIGENB
 *  TestActor 实现从 AbstractActor 继承的抽象方法。activate 和 deactivate 方法向 akka.actor 通知它的寿命信息；
 *  此示例中不会执行任何其他操作。runBody 方法是在收到任何消息之前、首次创建 akka.actor 的时候调用的。
 *  它通常用于将第一批消息引导至 akka.actor。testMessage 方法在 akka.actor 即将收到消息时调用；
 *  这里 akka.actor 可拒绝或接受消息。在本例中，akka.actor 使用继承的 testMessage 方法测试消息接受情况；因此接受了所有消息。
 *
 *  loopBody 方法（如清单 2 中所示）在 akka.actor 收到一条消息时调用。在通过较短延迟来模拟某种一般性处理之后，才开始处理该消息。
 *  如果消息为 “repeat”，那么 akka.actor 基于 count 参数开始发送另外 N-1 条消息。
 *  这些消息通过调用 akka.actor 管理器的 send 方法发送给一个随机 akka.actor。
 *
 *
 *  如果消息为 “init”，那么 akka.actor 通过向随机选择的 akka.actor 或一个属于 common 类别的 akka.actor 发送两组消息，
 *  启动 repeat 消息队列。一些消息可立即处理（实际上在 akka.actor 准备接收它们且有一个线程可用时即可处理）；
 *  其他消息则必须等待几秒才能运行。这种延迟的消息处理对本示例不是很重要，
 *  但它可用于实现对长期运行的流程（比如等待用户输入或等待对网络请求的响应到达）的轮询。
 *
 */
public class TestActor extends TestableActor {

	@Override
	public void activate() {
		logger.trace("TestActor activate: %s", this);
		super.activate();
	}

	@Override
	public void deactivate() {
		logger.trace("TestActor deactivate: %s", this);
		super.deactivate();
	}

	/**
	 * 收到任何消息之前、首次创建 akka.actor 的时候调用
	 */
	@Override
	protected void runBody() {
		// logger.trace("TestActor:%s runBody: %s", getName(), this);
		DefaultActorTest.sleeper(1);
		DefaultMessage m = new DefaultMessage("init", 8);
		getManager().send(m, null, this);
	}

	/**
	 * loopBody 方法在 akka.actor 收到一条消息时调用
	 * @param m
	 */
	@Override
	protected void loopBody(Message m) {
		// logger.trace("TestActor:%s loopBody %s: %s", getName(), m, this);
		DefaultActorTest.sleeper(1);
		String subject = m.getSubject();

		if ("repeat".equals(subject)) {
			int count = (Integer) m.getData();
			logger.trace("TestActor:%s repeat(%d) %s: %s", getName(), count, m,
					this);
			if (count > 0) {
				m = new DefaultMessage("repeat", count - 1);
				// logger.trace("TestActor loopBody send %s: %s", m, this);
				String toName = "actor"
						+ DefaultActorTest
								.nextInt(DefaultActorTest.TEST_ACTOR_COUNT);
				Actor to = actorTest.getTestActors().get(toName);
				if (to != null) {
					getManager().send(m, this, to);
				} else {
					logger.warning("repeat:%s to is null: %s", getName(),
							toName);
				}
			}
		} else if ("init".equals(subject)) {
			int count = (Integer) m.getData();
			count = DefaultActorTest.nextInt(count) + 1;
			logger.trace("TestActor:%s init(%d): %s", getName(), count, this);
			for (int i = 0; i < count; i++) {
				DefaultActorTest.sleeper(1);
				m = new DefaultMessage("repeat", count);
				// logger.trace("TestActor runBody send %s: %s", m, this);
				String toName = "actor"
						+ DefaultActorTest
								.nextInt(DefaultActorTest.TEST_ACTOR_COUNT);
				Actor to = actorTest.getTestActors().get(toName);
				if (to != null) {
					getManager().send(m, this, to);
				} else {
					logger.warning("init:%s to is null: %s", getName(), toName);
				}
				DefaultMessage dm = new DefaultMessage("repeat", count);
				dm.setDelayUntil(new Date().getTime()
						+ (DefaultActorTest.nextInt(5) + 1) * 1000);
				getManager().send(dm, this, this.getClass().getSimpleName());
			}
		} else {
			logger.warning("TestActor:%s loopBody unknown subject: %s",
					getName(), subject);
		}
	}

}