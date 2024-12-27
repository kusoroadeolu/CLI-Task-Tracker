import java.time.LocalDateTime;

public class Task {
  // Declare the needed attributes for each Task
  private int id;
  private String description;
  private String status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Task(int id, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.description = description;
    this.status = Status.toDo.getValue();
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  // Basic setters and getters
  public void setUpdateDate(LocalDateTime date) {
    this.updatedAt = date;
  }

  // update the status of the Task
  public void updateStatus(Status status) {
    this.status = status.getValue();
  }

  // set the description of the Task
  public void setDescription(String description) {
    this.description = description;
  }

  public int getId() {
    return this.id;
  }

  public String getDescription() {
    return this.description;
  }

  public String getStatus() {
    return this.status;
  }

  public LocalDateTime getCreationDate() {
    return this.createdAt;
  }

  public LocalDateTime getUpdateDate() {
    if (this.updatedAt == null) {
      return this.createdAt;
    }
    return this.updatedAt;
  }

  // check if a task is the same as another
  public boolean equals(Object object) {
    if (object == null) {
      return false;
    }

    if (object.getClass() != this.getClass()) {
      return false;
    }

    if (this == object) {
      return true;
    }

    Task task = (Task) object;
    return this != null && this.id == task.getId();
  }

}
