import java.util.ArrayList;

/*
 Project class extension of Assigment
 It is used to generalize the parts of a job or delivery.
 You have access to a list of your lower projects, if any.
 The same with the tasks that could depend on him.
 */
public class Project extends Assignment {
  private ArrayList<Assignment> children = new ArrayList<Assignment>();

  public Project(String n, Assignment par) {
    super(n, par);
    this.type = true;
  }

  public ArrayList<Assignment> getChildren(){
    return this.children;
  }

  @Override
  public void addChild(Assignment assignment) {
    children.add(assignment);
  }

  @Override
  public void acceptVisitor(Visitor vis) {
    vis.visitRoot(this);
  }
}