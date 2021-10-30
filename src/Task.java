import java.time.LocalDateTime;
import java.util.ArrayList;

/*
 Class Task, extends Assigment
 Its function is to divide a project class into smaller parts,
 and thus easier to work with.
 You will have a list of the intervals that have been performed during the task * /
 */

public class Task extends Assignment {
  private ArrayList<Interval> intervals = new ArrayList<Interval>();

  public Task(String name, Assignment parent) {
    super(name, parent);
    this.type = false;
  }

  public void start() {
    Clock clock = Clock.getInstance();
    LocalDateTime actualTime = clock.getTime();
    Interval interval = new Interval(this, actualTime);
    clock.addObserver(interval);
  }

  public void stop() {
    Clock clock = Clock.getInstance();
    clock.deleteObserver(this.intervals.get(this.intervals.size()-1));
  }

  public ArrayList<Interval> getIntervals(){
    return this.intervals;
  }

  public void addInterval(Interval interval) {
    this.intervals.add(interval);
  }

  @Override
  public void acceptVisitor(Visitor vis) {
    vis.visitTask(this);
  }
}