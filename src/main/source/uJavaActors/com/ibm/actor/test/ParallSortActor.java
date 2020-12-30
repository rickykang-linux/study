package com.ibm.actor.test;

import java.util.Arrays;
import com.ibm.actor.*;

public class ParallSortActor extends AbstractActor{
  public static ActorManager manager = DefaultActorManager.getDefaultInstance();
  int count = 10;
  int finishedCount = 0;
  
  @Override
  protected void loopBody(Message m){
    if(m.getSubject().equals("init")){
      System.out.println("begin parall sort ...");
      
      int totalItems = 10000;
      for(int i=0; i<count; i++){
        Actor sortActor = manager.createAndStartActor(SortActor.class, SortActor.class.getSimpleName() + i);
        int[] data = new int[totalItems / count ];
        for(int j = 0; j< data.length; j++){
          data[j] = (int)(Math.abs(Math.random()) * totalItems);
        }
        
        DefaultMessage rm = new DefaultMessage("sort", data);
        manager.send(rm, this, sortActor);
      }
     }else if(m.getSubject().equals("result")){
      finishedCount++;
      System.out.println("received result from " + m.getSource().getName() + " result " + Arrays.toString((int[])m.getData()));
      m.getSource().shutdown();
      if(finishedCount == 10){
        System.out.println("all finished");
        this.shutdown();
      }
     }
   }
   
   public static void main(String[] args)throws InterruptedException {
    Actor myactor = manager.createAndStartActor(ParallSortActor.class, ParallSortActor.class.getSimpleName());
    while( !myactor.isShutdown()){
      Thread.sleep(1000);
      System.out.println("...");
    }
   }
 }
 
 public class SortActor extends AbstractActor{
  @Override
  protected void loopBody(Message m){
    if(m.getSubject().equals("sort")){
      System.out.println("enter SortActor " + this.getName());
      int[] data = (int[])m.getData();
      Arrays.sort(data);
      int[] topData = Arrays.copyOf(data, 10);
      System.out.println("SortActor " + this.getName() + " finished " + Arrays.toString(topData));
      DefaultMessage rm = new DefaultMessage("result", topData);
      this.getManager().send(rm, this, m.getSource());
    }
   }
 }
      
  
      
