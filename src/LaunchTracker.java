import java.time.LocalDate;
import java.time.LocalDateTime;

//method to launch the class
public class LaunchTracker {

  private TaskManager taskManager;

  public LaunchTracker() {
    this.taskManager = new TaskManager();

  }

  // method to launch the task tracker(takes arguments as a parameter)
  public void launchTracker(String[] args) {
    if (args.length < 1) {
      System.out.println("cli_usage: <command> [arguments]");
      return;
    }

    String command = args[0];

    switch (command) {
      case "add" -> handleAddTasks(args);

      case "delete" -> handleDeleteTasks(args);

      case "update" -> handleUpdateTasks(args);

      case "mark" -> handleMarkTasks(args);

      case "list" -> handleListTasks(args);
    }

    taskManager.writeToFile();
  }

  // handle add tasks
  public void handleAddTasks(String[] args) {
    try {
      if (args.length < 3) {
        System.out.println("Usage: add <id> <description>");
        return;
      } else {
        int id = Integer.parseInt(args[1]);
        String description = args[2];
        Task t = new Task(id, description, LocalDateTime.now(), null);
        taskManager.addTask(t);
      }
    } catch (NumberFormatException e) {
      System.out.println("Enter a numeric value!");
    }
  }

  // handle delete tasks
  public void handleDeleteTasks(String[] args) {
    try {
      if (args.length < 2) {
        System.out.println("Usage: delete <id>");
      } else {
        String id = args[1];
        if (id.equalsIgnoreCase("all")) {
          taskManager.deleteAllTasks();
        } else if (id.equalsIgnoreCase(id)) {
          taskManager.deleteTask(Integer.parseInt(id));
        }
      }
    } catch (NumberFormatException e) {
      System.out.println("Enter a numeric value");
    }
  }

  // handle update tasks
  public void handleUpdateTasks(String[] args) {
    try {
      if (args.length < 3) {
        System.out.println("Usage: update <id> <description>");
      } else {
        int id = Integer.parseInt(args[1]);
        String description = args[2];
        taskManager.updateDescription(id, description);
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  // handle mark tasks
  public void handleMarkTasks(String[] args) {
    if (args.length < 3) {
      System.out.println("Usage: mark <id> <status>");
    } else {
      int id = Integer.parseInt(args[1]);
      String status = args[2].toLowerCase();

      switch (status) {
        case "done" -> taskManager.markDone(id);
        case "in_progress" -> taskManager.markInProgress(id);
        case "to_do" -> taskManager.markToDo(id);
      }
    }
  }

  // handle list tasks
  public void handleListTasks(String[] args) {
    if (args.length < 2) {
      System.out.println("Usage: list <command>");
    } else {
      String statusMod = args[1].toLowerCase();
      switch (statusMod) {
        case "all" -> taskManager.listAllTasks();
        case "done" -> taskManager.listDone();
        case "in_progress" -> taskManager.listInProgress();
        case "to_do" -> taskManager.listToDo();
      }
    }
  }

}
