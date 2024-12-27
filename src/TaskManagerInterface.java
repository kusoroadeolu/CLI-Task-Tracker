public interface TaskManagerInterface {
  // Interface for the taskmanager
  public void addTask(Task task);

  public void deleteAllTasks();

  public void deleteTask(int id);

  public void listAllTasks();

  public void listDone();

  public void listInProgress();

  public void listToDo();

  public void markInProgress(int id);

  public void markDone(int id);

  public void markToDo(int id);

  public void updateDescription(int id, String description);

  public void writeToFile();

  public void readFromfile();
}
