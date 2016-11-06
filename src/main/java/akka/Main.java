package akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

//class AkkaClient extends UntypedActor {
//
//    @Override
//    public void preStart() {
//        // create the greeter akka.actor
//        final ActorRef greeter =
//                getContext().actorOf(Props.create(Greeter.class), "greeter");//创建greeter actor实例
//        // tell it to perform the greeting
//        greeter.tell(Greeter.Msg.GREET, getSelf());//通过tell方法给greeter akka.actor 发送一条消息
//    }
//
//    @Override
//    public void onReceive(Object msg) {
//        if (msg == Greeter.Msg.DONE) {
//            // when the greeter is done, stop this akka.actor and with it the application
//            getContext().stop(getSelf());
//        } else unhandled(msg);
//    }
//}

class Greeter extends UntypedActor {

    public static enum Msg {
        GREET, DONE;
    }

    @Override
    public void preStart(){
        System.out.println("Greeter thread name: "+Thread.currentThread().getName());
    }

    @Override
    public void onReceive(Object msg) {
        if (msg == Msg.GREET) {
            System.out.println("Hello World!");
            getSender().tell(Msg.DONE, getSelf());
        } else unhandled(msg);
    }

}

class HelloWorld extends UntypedActor {

    @Override
    public void preStart() {
        System.out.println("HelloWorld thread name: "+Thread.currentThread().getName());

        // create the greeter actor
        final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        // tell it to perform the greeting
        greeter.tell(Greeter.Msg.GREET, getSelf());
    }

    @Override
    public void onReceive(Object msg) {
        if (msg == Greeter.Msg.DONE) {
            // when the greeter is done, stop this actor and with it the application
            getContext().stop(getSelf());
        } else
            unhandled(msg);
    }
}


public class Main {

    public static void main(String[] args) {
        System.out.println("Main thread name: "+Thread.currentThread().getName());
//        akka.Main.main(new String[] { HelloWorld.class.getName() });
        ActorSystem system = ActorSystem.create("demo");
        ActorRef helloWorld = system.actorOf(Props.create(HelloWorld.class));

        helloWorld.tell(Greeter.Msg.GREET, ActorRef.noSender());

    }
}