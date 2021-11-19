package core;

import visitors.JsonVisitor;
import visitors.SearchByTagVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


public class Main {
  private static final Logger logger = LoggerFactory.getLogger(Main.class);


  public static void testApenddB(){
    Clock clock = Clock.getInstance();

    Project root = new Project("root", null);
    Project softwareDesing = new Project("software design", root);
    Project softwareTesting = new Project("software testing", root);
    Project databases = new Project("databases", root);
    Project problems = new Project("problems", softwareDesing);
    Project projectTimeTracker = new Project("project time tracker", softwareDesing);

    Task transportation = new Task("transportation", root);
    Task firstList = new Task("first list", problems);
    Task secondList = new Task("second list", problems);
    Task readHandout = new Task("read handout", projectTimeTracker);
    Task firstMilestone = new Task("first milestone", projectTimeTracker);

    logger.info("Start Test\n");
    logger.info("Transportation starts:\n");
    transportation.start();
    sleep(4);
    transportation.stop();
    logger.info("Transportation stop\n");
    sleep(2);
    logger.info("First list starts\n");
    firstList.start();
    sleep(6);
    logger.info("Second list start\n");
    secondList.start();
    sleep(4);
    firstList.stop();
    logger.info("First list stop\n");
    sleep(2);
    secondList.stop();
    logger.info("Second list stop\n");
    sleep(2);
    logger.info("Transportation starts\n");
    transportation.start();
    sleep(4);
    transportation.stop();
    logger.info("Transportation stop\n");


  }

    public static void testSaveData(){
      Clock clock = Clock.getInstance();

      Project root = new Project("root", null);
      Project softwareDesing = new Project("software design", root);
      Project softwareTesting = new Project("software testing", root);
      Project databases = new Project("databases", root);
      Project problems = new Project("problems", softwareDesing);
      Project projectTimeTracker = new Project("project time tracker", softwareDesing);

      Task transportation = new Task("transportation", root);
      Task firstList = new Task("first list", problems);
      Task secondList = new Task("second list", problems);
      Task readHandout = new Task("read handout", projectTimeTracker);
      Task firstMilestone = new Task("first milestone", projectTimeTracker);

      logger.info("Start Test\n");
      logger.info("Transportation starts:\n");
      transportation.start();
      sleep(4);
      transportation.stop();
      logger.info("Transportation stop\n");
      sleep(2);
      logger.info("First list starts\n");
      firstList.start();
      sleep(6);
      logger.info("Second list start\n");
      secondList.start();
      sleep(4);
      firstList.stop();
      logger.info("First list stop\n");
      sleep(2);
      secondList.stop();
      logger.info("Second list stop\n");
      sleep(2);
      logger.info("Transportation starts\n");
      transportation.start();
      sleep(4);
      transportation.stop();
      logger.info("Transportation stop\n");


    JsonVisitor vis = new JsonVisitor();
    root.acceptVisitor(vis);
    vis.saveData("tree.json");
  }

  public static void testLoadData() {
    JsonVisitor vis = new JsonVisitor();
    vis.loadData("/tree.json");
  }

  public static void testSearchByTag(){
    Project root = new Project("root", null);
    Project softwareDesing = new Project("software design", root);
    Project softwareTesting = new Project("software testing", root);
    Project databases = new Project("databases", root);
    Project problems = new Project("problems", softwareDesing);
    Project projectTimeTracker = new Project("project time tracker", softwareDesing);

    Task transportation = new Task("transportation", root);
    Task firstList = new Task("first list", problems);
    Task secondList = new Task("second list", problems);
    Task readHandout = new Task("read handout", projectTimeTracker);
    Task firstMilestone = new Task("first milestone", projectTimeTracker);

    softwareDesing.addTag("java");
    softwareDesing.addTag("flutter");
    softwareTesting.addTag("c++");
    softwareTesting.addTag("Java");
    softwareTesting.addTag("python");
    databases.addTag("SQL");
    databases.addTag("python");
    databases.addTag("C++");
    firstList.addTag("java");
    secondList.addTag("Dart");
    firstMilestone.addTag("Java");
    firstMilestone.addTag("IntelliJ");

    SearchByTagVisitor vis = new SearchByTagVisitor();
    ArrayList<Assignment> results;

    vis.setNewSearch("java");
    root.acceptVisitor(vis);
    results = vis.getSearchResult();
    for ( Assignment result : results ) {
      logger.info(result.getName());
    }

    vis.setNewSearch("python");
    root.acceptVisitor(vis);
    results = vis.getSearchResult();
    for ( Assignment result : results ) {
      logger.info(result.getName());
    }

    vis.setNewSearch("flutter");
    root.acceptVisitor(vis);
    results = vis.getSearchResult();
    for ( Assignment result : results ) {
      logger.info(result.getName());
    }

    vis.setNewSearch("a");
    root.acceptVisitor(vis);
    results = vis.getSearchResult();
    for ( Assignment result : results ) {
      logger.info(result.getName());
    }

  }

  public static void sleep(int seconds) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  }


  public static void main(String[] args) {
    testApenddB();
//    testSaveData();
//    testLoadData();
//    testSearchByTag();
  }
}