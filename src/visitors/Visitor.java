package visitors;

import core.Interval;
import core.Project;
import core.Task;

/**Allows for one or more operations to be applied to a set of objects at runtime,
  decoupling the operations from the object structure.
 */
public abstract class Visitor {

  public Object visitProject(Project project) {
    Object obj = new Object();
    return obj;
  }

  public Object visitTask(Task task) {
    Object obj = new Object();
    return obj;
  }

  public Object visitInterval(Interval interval) {
    Object obj = new Object();
    return obj;
  }
}
