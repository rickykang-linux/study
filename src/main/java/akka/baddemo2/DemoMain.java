package akka.baddemo2;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class DemoMain {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("demo1");
        ActorRef actor1 = system.actorOf(Props.create(Actor01.class));
        ActorRef actor2 = system.actorOf(Props.create(Actor02.class));




        actor1.tell("hello akka!!", actor2);
        system.shutdown();//

    }

}