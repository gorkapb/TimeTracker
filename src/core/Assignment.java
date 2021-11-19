package core;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import visitors.Visitor;

/**
 *Assignments class,
 *Its function is to generalize two subclasses (core.Project and core.Task)
 */
public abstract class Assignment {
  protected LocalDateTime initialTime;
  protected Duration totalTime;
  protected LocalDateTime finalTime;
  protected Assignment parent;
  protected String name;
  protected boolean type;
  protected ArrayList<String> tags = new ArrayList<String>();
  protected static final Logger logger = LoggerFactory.getLogger(Assignment.class);


  public Assignment(String name, Assignment parent) {
    this.totalTime = Duration.ofSeconds(0);
    this.name = name;
    this.parent = parent;
    if (this.parent != null) {
      this.parent.addChild(this); //Make parent know it's existence
    }
    assert invariant(): "Class isn't invariant.";
  }

  protected boolean invariant() {
    return this.totalTime.getSeconds() >= 0;
  }


  public void update(LocalDateTime actualTime, int seconds) {
    assert invariant(): "The class isn't invariant";
    if (this.totalTime.getSeconds() == 0) { //not started yet
      this.initialTime = actualTime.minusSeconds(seconds);
    }
    this.totalTime = this.totalTime.plusSeconds(seconds);
    this.finalTime = actualTime;

    show();

    //Send update information to parents
    if (this.parent != null) {
      this.parent.update(actualTime, seconds);
    }
  }

  public void addChild(Assignment assignment){}

  public void addTag(String tag) {
    assert invariant(): "The class isn't invariant";
    this.tags.add(tag);
  }

  public String getName() {
    assert invariant(): "The class isn't invariant";
    return this.name;
  }

  public LocalDateTime getInitialTime() {
    assert invariant(): "The class isn't invariant";
    return this.initialTime;
  }

  public LocalDateTime getFinalTime() {
    assert invariant(): "The class isn't invariant";
    return this.finalTime;
  }

  public long getTotalTime() {
    assert invariant(): "The class isn't invariant";
    return this.totalTime.getSeconds();
  }

  public boolean getType() {
    assert invariant(): "The class isn't invariant";
    return this.type;
  }

  public ArrayList<String> getTags() {
    assert invariant(): "The class isn't invariant";
    return this.tags;
  }

  public Assignment getParent() {
    assert invariant(): "The class isn't invariant";
    return this.parent;
  }

  /** Displays the data of the
   *assignment and the higher assignments.
   */
  public void show() {
    assert invariant(): "The class isn't invariant";

    String init = this.initialTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    String fin = this.finalTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    logger.info("activity: \t {} \t {} \t {} \t {}",
        this.name, init, fin, this.totalTime.getSeconds());
  }

  public void acceptVisitor(Visitor vis) {}
}