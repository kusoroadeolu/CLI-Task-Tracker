import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskManager implements TaskManagerInterface {
  // Declared these to be static and final because I am only going to be using one
  // instance of this class(You can change it to be modifiable)
  private final static ArrayList<Task> tasks = new ArrayList<>();
  private final static String jsonPath = "C:\\Users\\eastw\\Desktop\\Java Projects\\Mini Projects\\Task Tracker\\tasks.json";
  Parser parser;

  public TaskManager() {
    // initalize a new taskParser
    parser = new Parser();
    try {
      // Does not read from file is the file is empty
      if (Files.size(Paths.get(jsonPath)) != 0) {
        readFromfile();
      }
    } catch (IOException e) {
      System.out.println("Failed to read from file.");
    }
  }

  // checks if there are no tasks in the arrayList
  public boolean isTasksEmpty() {
    return tasks.size() == 0;
  }

  // Adds a task
  public void addTask(Task newTask) {
    for (Task task : tasks) {
      // checks if a task is equal to another
      if (newTask.equals(task)) {
        System.out.println("Duplicate task id found.");
        return;
      }
    }
    tasks.add(newTask);

  }

  // deletes all tasks
  public void deleteAllTasks() {
    if (isTasksEmpty()) {
      System.out.println("No tasks found!");
      return;
    }
    tasks.clear();
  }

  // delete a task with a specific id
  public void deleteTask(int id) {
    if (isTasksEmpty()) {
      System.out.println("No tasks found!");
      return;
    }

    for (Task task : tasks) {
      if (task.getId() == id) {
        tasks.remove(task);
      } else {
        System.err.println("Task not found.");
      }
    }
  }

  // list all tasks
  public void listAllTasks() {
    if (isTasksEmpty()) {
      System.out.println("No tasks found!");
      return;
    }

    for (Task task : tasks) {
      // parses all the tasks to string
      System.out.println(parser.parseToString(task));
    }
  }

  // list done tasks
  public void listDone() {
    if (isTasksEmpty()) {
      System.out.println("No tasks found!");
      return;
    }

    for (Task task : tasks) {
      if (Status.isEqual(Status.done, task.getStatus()))
        System.out.println(parser.parseToString(task));
    }
  }

  // list tasks in progress
  public void listInProgress() {
    if (isTasksEmpty()) {
      System.out.println("No tasks found!");
      return;
    }

    for (Task task : tasks) {
      if (Status.isEqual(Status.inProgress, task.getStatus()))
        System.out.println(parser.parseToString(task));
    }

  }

  // list to do tasks
  public void listToDo() {
    if (isTasksEmpty()) {
      System.out.println("No tasks found!");
      return;
    }

    for (Task task : tasks) {
      if (Status.isEqual(Status.toDo, task.getStatus()))
        System.out.println(parser.parseToString(task));
    }
  }

  // mark a task to be in progress
  public void markInProgress(int id) {
    if (isTasksEmpty()) {
      System.out.println("No tasks found!");
      return;
    }

    for (Task task : tasks) {
      if (task.getId() == id) {
        task.updateStatus(Status.inProgress);
        task.setUpdateDate(LocalDateTime.now());
      }
    }
  }

  public void markToDo(int id) {
    if (tasks.size() == 0 || tasks == null) {
      System.out.println("No tasks found!");
      return;
    }

    for (Task task : tasks)
      if (task.getId() == id) {
        task.updateStatus(Status.toDo);
        task.setUpdateDate(LocalDateTime.now());
      }
  }

  public void markDone(int id) {
    if (tasks.size() == 0 || tasks == null) {
      System.out.println("No tasks found!");
      return;
    }

    for (Task task : tasks)
      if (task.getId() == id) {
        task.updateStatus(Status.done);
        task.setUpdateDate(LocalDateTime.now());
      }
  }

  public void updateDescription(int id, String description) {
    if (tasks.size() == 0 || tasks == null) {
      System.out.println("No tasks found!");
      return;
    }

    for (Task task : tasks) {
      if (task.getId() == id) {
        task.setDescription(description);
        task.setUpdateDate(LocalDateTime.now());
      }
    }
  }

  // write all the tasks in the arrayList to the file
  public void writeToFile() {
    // Get the filepath you want to write to
    Path path = Paths.get(jsonPath);

    // Convert the concatenated tasks to a string
    String newStr = parser.concatTasks(tasks);

    // This string builder basically adds "[ ]" to the beginning and end of the
    byte b[] = parser.parseToJsonArray(newStr).getBytes();

    try {
      Path writtenFilePath = Files.write(path, b, StandardOpenOption.CREATE/* Create a new file if none is found */,
          StandardOpenOption.TRUNCATE_EXISTING/* Erase all of the existing content of the file before writing */);
      System.out.println("Written to file path: " + writtenFilePath.toString());
    } catch (IOException e) {
      System.out.println("Failed to write to file.");
      e.printStackTrace();
    }
  }

  // Read content from the file
  public void readFromfile() {
    try {
      Path path = Paths.get(jsonPath);
      // check if the file path doesnt exist
      if (Files.notExists(path)) {
        Files.createFile(path);
        System.out.println("Created File: " + path.toString());
      }

      // Before reading checks if it can read the file
      if (Files.isReadable(path)) {
        // Reads the file
        String jsonString = Files.readString(path);

        // Gets each Task from the string
        String[] taskObjects = jsonString.split("},");
        for (String taskObject : taskObjects) {
          Task task = parser.parseFromJson(taskObject);
          if (task != null) {
            tasks.add(task);
          }
        }
      }

    } catch (IOException e) {
      System.out.println("Failed to read this file.");
    }
  }
}
