package visitors;

import core.Assignment;
import core.Interval;
import core.Project;
import core.Task;
import org.json.JSONArray;
import org.json.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

/*
 Extends visitors.Visitor Class,
 create a JSON object with the project data
    and a JSON array with the data of the child intervals.
 */
public class JsonVisitor extends Visitor{
  private JSONArray jsonArray;

  public JsonVisitor(){
    this.jsonArray = new JSONArray();
  }

  @Override
  public JSONObject visitProject(Project project){
    JSONObject obj = new JSONObject();
    obj.put("type", "project");
    obj.put("name", project.getName());
    if (project.getInitialTime() != null) {
      obj.put("initialTime", project.getInitialTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
      obj.put("finalTime", project.getFinalTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
    else {
      obj.put("initialTime", JSONObject.NULL);
      obj.put("finalTime",JSONObject.NULL);
    }
    obj.put("totalTime", project.getTotalTime());

    JSONArray children = new JSONArray();

    for (Assignment child : project.getChildren()) {
      if (child.getType()){
        children.put(visitProject((Project) child));
      }
      else {
        children.put(visitTask((Task) child));
      }
    }

    obj.put("children", children);
    if (project.getParent() == null) {
      this.jsonArray.put(obj);
    }
    return obj;
  }

  @Override
  public JSONObject visitTask(Task task){

    JSONObject obj = new JSONObject();
    obj.put("type", "task");
    obj.put("name", task.getName());
    if (task.getInitialTime() != null) {
      obj.put("initialTime", task.getInitialTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
      obj.put("finalTime", task.getFinalTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
    else {
      obj.put("initialTime", JSONObject.NULL);
      obj.put("finalTime",JSONObject.NULL);
    }
    obj.put("totalTime", task.getTotalTime());

    JSONArray intervals = new JSONArray();

    for (Interval interval : task.getIntervals()) {
      intervals.put(visitInterval(interval));
    }

    obj.put("intervals", intervals);
    return obj;
  }

  @Override
  public JSONObject visitInterval(Interval interval){
    JSONObject obj = new JSONObject();
    obj.put("type", "interval");
    if (interval.getInitialTime() != null) {
      obj.put("initialTime", interval.getInitialTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
      obj.put("finalTime", interval.getFinalTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
    else {
      obj.put("initialTime", JSONObject.NULL);
      obj.put("finalTime",JSONObject.NULL);
    }
    obj.put("totalTime", interval.getTotalTime());

    return obj;
  }

  public void createFile(String fileName) {
    fileName = fileName;
    try {
      File myObj = new File(fileName);
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  public void saveData(String fileName){
    createFile(fileName);
    try {
      FileWriter myWriter = new FileWriter(fileName);
      myWriter.write(this.jsonArray.toString(4));
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public void loadData(String fileName){

    JSONParser parser = new JSONParser();

    try (Reader reader = new FileReader("tree.json")) {

      JSONObject jsonObject = (JSONObject) parser.parse(reader);
      System.out.println(jsonObject);

      String name = (String) jsonObject.get("name");
      System.out.println(name);

      long age = (Long) jsonObject.get("age");
      System.out.println(age);

      // loop array
      JSONArray msg = (JSONArray) jsonObject.get("messages");
      Iterator<Object> iterator = msg.iterator();
      while (iterator.hasNext()) {
        System.out.println(iterator.next());
      }

    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

}


