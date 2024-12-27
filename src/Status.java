//status enum to specify constant status for class tasks
public enum Status {
  toDo("To Do"),
  done("Done"),
  inProgress("In Progress");

  private String value;

  Status(String value) {
    this.value = value;
  }

  // get the description of each status
  public String getValue() {
    return this.value;
  }

  // get the status of a task from a string
  public static Status statusFromString(String statusDescription) {
    for (Status s : Status.values()) {
      if (s.getValue().equals(statusDescription)) {
        return s;
      }
    }
    return null;
  }

  // checks if the statusDescription is equal to the status's value
  public static boolean isEqual(Status status, String statusDescription) {
    if (statusDescription != null) {
      return status.getValue().equals(statusDescription);
    }
    return false;
  }
}
