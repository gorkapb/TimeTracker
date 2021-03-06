package core;

import visitors.Visitor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* core.Interval class, treated as observer
    Tries to divide a task into the time frames used to complete it. */
public class Interval implements Observer {
  private static final int SECONDS = 2; //core.Clock period
  private LocalDateTime initialTime;
  private Duration totalTime;
  private LocalDateTime finalTime;
  private final Task parent;
  private static final Logger logger = LoggerFactory.getLogger(Interval.class);

  public Interval(Task parent, LocalDateTime actualTime) {
    this.totalTime = Duration.ofSeconds(0);
    this.initialTime = actualTime;
    this.parent = parent;
    this.parent.addInterval(this); //Adds itself to parent's array
  }

  /*
 Override Observer's update class to save
    the time it receives from the notifyObserver of the core.Clock class.
 */
  @Override
  public void update(Observable o, Object time) {
    this.totalTime = this.totalTime.plusSeconds(SECONDS);
    this.finalTime = (LocalDateTime) time;
    this.show();
    this.parent.update((LocalDateTime) time,2); //Update parent

  }

  public LocalDateTime getInitialTime() {
    return this.initialTime;
  }

  public LocalDateTime getFinalTime() {
    return this.finalTime;
  }

  public long getTotalTime() { return this.totalTime.getSeconds(); }

  public Task getParent() { return this.parent; }

  public void setTime(LocalDateTime initialTime, LocalDateTime finalTime, Long totalTime){
    this.initialTime = initialTime;
    this.finalTime = finalTime;
    this.totalTime = Duration.ofSeconds(totalTime);
  }

  public void acceptVisitor(Visitor vis) {
    vis.visitInterval(this);
  }

  // Displays the data of the interval
  public void show() {
    String init = this.initialTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    String fin = this.finalTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    logger.info("interval:\t {} \t {} \t {}",
        init, fin, this.totalTime.getSeconds());
  }
}