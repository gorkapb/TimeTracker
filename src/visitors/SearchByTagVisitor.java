package visitors;

import core.Assignment;
import core.Project;
import core.Task;

import java.util.ArrayList;

public class SearchByTagVisitor extends Visitor{
  private ArrayList<Assignment> searchResult;
  private String tag;

  public Object visitProject(Project project){
    for (String tag : project.getTags()) {
      if (tag == this.tag) {
        this.searchResult.add(project);
        break;
      }
    }
    for (Assignment child : project.getChildren()) {
      if (child.getType()){
        visitProject((Project) child);
      }
      else {
        visitTask((Task) child);
      }
    }
    return null;
  }

  public Object visitTask(Task task){
    for (String tag : task.getTags()) {
      if (tag == this.tag) {
        this.searchResult.add(task);
        break;
      }
    }
    return null;
  }

  public void setNewSearch (String tag) {
    this.tag = tag;
    this.searchResult = new ArrayList<Assignment>();
    logger.info("Searching by tag: " + this.tag);

  }

  public ArrayList<Assignment> getSearchResult() {
    return this.searchResult;
  }
}
