package akka.actor.calculator;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.duration.Duration;

import java.util.Random;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * val system=ActorSystem("UniversityMessagingSystem")

 等同于

 val system=ActorSystem("UniversityMessagingSystem", ConfigFactory.load())
 
 它会在classpath的根路径下依次去查找application.conf, application.json以及application.peroperties文件并自动进行加载。

 */
public class CreationApplication {

  public static void main(String[] args) {
//    if (args.length == 0 || args[0].equals("CalculatorWorker"))
//      startRemoteWorkerSystem();
    if (args.length == 0 || args[0].equals("Creation"))
      startRemoteCreationSystem();
  }

  public static void startRemoteWorkerSystem() {
    ActorSystem.create("CalculatorWorkerSystem",
        ConfigFactory.load(("calculator")));
    System.out.println("Started CalculatorWorkerSystem");
  }

  public static void startRemoteCreationSystem() {
//    final ActorSystem system = ActorSystem.create("CreationSystem",
//        ConfigFactory.load("remotecreation"));
    final ActorSystem system = ActorSystem.create("CreationSystem");
    final ActorRef actor = system.actorOf(Props.create(CreationActor.class),
        "creationActor");

    System.out.println("Started CreationSystem");
    final Random r = new Random();
    system.scheduler().schedule(Duration.create(2, SECONDS),
        Duration.create(2, SECONDS), new Runnable() {
          @Override
          public void run() {

            if (r.nextInt(100) % 2 == 0) {
              actor.tell(new Op.Multiply(r.nextInt(100), r.nextInt(100)), null);
            } else {
              actor.tell(new Op.Divide(r.nextInt(10000), r.nextInt(99) + 1),
                  null);
            }
          }
        }, system.dispatcher());
  }
}
