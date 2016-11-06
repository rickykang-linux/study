package akka.actor.calculator;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class CreationActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {

        String threadName = Thread.currentThread().getName();

        if (message instanceof Op.MathOp) {
            ActorRef calculator = getContext().actorOf(
                    Props.create(CalculatorActor.class));
            calculator.tell(message, getSelf());

        } else if (message instanceof Op.MultiplicationResult) { //乘法结果
            Op.MultiplicationResult result = (Op.MultiplicationResult) message;
            System.out.printf(threadName+", "+"Mul result: %d * %d = %d\n", result.getN1(),
                    result.getN2(), result.getResult());
//            getContext().stop(getSender());

        } else if (message instanceof Op.DivisionResult) {  //除法结果
            Op.DivisionResult result = (Op.DivisionResult) message;
            System.out.printf(threadName+", "+"Div result: %.0f / %d = %.2f\n", result.getN1(),
                    result.getN2(), result.getResult());
//            getContext().stop(getSender());

        } else {
            unhandled(message);
        }
    }
}
