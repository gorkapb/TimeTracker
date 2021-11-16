package core;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

//This class will be in charge of accessing the time of the device and updating its observers.
public class Clock extends Observable{
  private static final int SECONDS = 2; //core.Clock period
  private static Clock uniqueInstance;
  private LocalDateTime time;

  /*Constructor where we initialize the period through which it will go
updating the time to the observers.*/
  private Clock() {
    this.time = LocalDateTime.now();
    startClock();
  }

  /* Method that returns the clock instance to ensure that
   there is only one clock ticking for all observers.
   synchronized does not allow two or more threads to enter at the same time
   in the function, they enter one by one. */
  public static Clock getInstance() {
    if (uniqueInstance == null) {
      uniqueInstance = new Clock();
    }
    return  uniqueInstance;
  }

  public LocalDateTime getTime(){
    return time;
  }

  private void startClock(){

    TimerTask updateTime = new TimerTask() {
      public void run() {
        setChanged();
        time = time.plusSeconds(SECONDS);
        notifyObservers(time);
      }
    };

    Timer timer = new Timer("Timer");
    timer.scheduleAtFixedRate(updateTime, 2, SECONDS * 1000); //Period = 2s
  }

}
