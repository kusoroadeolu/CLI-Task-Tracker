// import java.time.LocalDateTime;

import java.time.LocalDateTime;
import java.util.ArrayList;

//Class to handle parsing to and parsing from json(also a few other methods here)
public class Parser {

  // Parse a string from JSON and assign the values to a new Task
  public Task parseFromJson(String jsonString) {
    // Remove all the braces, quotes, and brackets from the string
    String newString = jsonString.replace("{", "")
        .replace("}", "")
        .replace("\"", "")
        .replace("[", "")
        .replace("]", "");

    // Create an array of the json objects from the string
    String[] jsonObjects = newString.split(",", 5);
    if (jsonObjects.length < 5) {
      return null;
    }

    int id = 0;
    String description = "";
    String status = null;
    LocalDateTime createdAt = null;
    LocalDateTime updatedAt = null;

    for (String jsonObject : jsonObjects) {
      String keyValue[] = jsonObject.split(":", 2);
      // check for invalid pairs
      if (keyValue.length < 2) {
        continue;
      }

      // assign the keys and values to their respective strings
      String key = keyValue[0].trim();
      String value = keyValue[1].trim();

      switch (key) {
        case "Id" -> id = Integer.parseInt(value);
        case "Description" -> description = value;
        case "Status" -> status = value;
        case "createdAt" -> createdAt = LocalDateTime.parse(value);
        case "updatedAt" -> updatedAt = LocalDateTime.parse(value);
      }
    }
    // assign all the values from the string to a new task
    Task task = new Task(id, description, createdAt, updatedAt);

    // getting the task status
    if (status != null) {
      task.updateStatus(Status.statusFromString(status));
    }
    return task;
  }

  // Convert the task to a json object
  public String parseToString(Task task) {
    return String.format(
        "\n{\"Id\": %d, \"Description\": \"%s\", \"Status\": \"%s\", \"createdAt\": \"%s\", \"updatedAt\": \"%s\"},",
        task.getId(),
        task.getDescription(), task.getStatus(), task.getCreationDate(), task.getUpdateDate()).toString();

  }

  public String parseToJsonArray(String str) {
    if (str.length() != 0) {
      StringBuilder fmtToJsonArray = new StringBuilder(str);
      return fmtToJsonArray.insert(0, "[").insert(str.length(), "]").deleteCharAt(str.length() + 1)
          .toString();

    }
    return null;
  }

  public String concatTasks(ArrayList<Task> tasks) {
    StringBuilder concatString = new StringBuilder("");
    for (Task task : tasks) {
      if (task != null) {
        concatString.append(parseToString(task));
      }
    }
    return concatString.toString();
  }
}
