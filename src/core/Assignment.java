package core;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
  private ArrayList<String> tags = new ArrayList<String>();


  public Assignment(String name, Assignment parent) {
    this.totalTime = Duration.ofSeconds(0);
    this.name = name;
    this.parent = parent;
    if (this.parent != null) {
      this.parent.addChild(this); //Make parent know it's existence
    }
  }

  public void update(LocalDateTime actualTime, int seconds) {
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
    this.tags.add(tag);
  }

  public String getName() {
    return this.name;
  }

  public LocalDateTime getInitialTime() {
    return this.initialTime;
  }

  public LocalDateTime getFinalTime() {
    return this.finalTime;
  }

  public long getTotalTime() {
    return this.totalTime.getSeconds();
  }

  public boolean getType() {
    return this.type;
  }

  public ArrayList<String> getTags() {
    return this.tags;
  }

  public Assignment getParent() {
    return this.parent;
  }

  /** Displays the data of the
   *assignment and the higher assignments.
   */
  public void show() {
    String init;
    String fin;

    if (this.initialTime == null) {
      init = "Not initial time yet";
      fin = "Not final time yet";
    } else {
      init = this.initialTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
      fin = this.finalTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    System.out.println("activity: \t" + this.name + "\t\t"
        + init + "\t\t" + fin + "\t\t" + this.totalTime.getSeconds());
  }

  public void acceptVisitor(Visitor vis) {}
}