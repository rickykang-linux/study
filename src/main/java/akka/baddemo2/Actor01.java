package akka.baddemo2;

import akka.actor.UntypedActor;

public class Actor01 extends UntypedActor {

    @Override
    public void onReceive(Object arg0) throws Exception {
        if(arg0 instanceof String)
            System.err.println("1-------------->"+arg0);
    }

}