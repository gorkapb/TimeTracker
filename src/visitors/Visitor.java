package visitors;

import core.Assignment;
import core.Interval;
import core.Project;
import core.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**Allows for one or more operations to be applied to a set of objects at runtime,
  decoupling the operations from the object structure.
 */
public abstract class Visitor {
  protected static final Logger logger = LoggerFactory.getLogger(Visitor.class);

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
