public class ToDo extends Task {

  public ToDo(String inputText) {
    super(inputText);
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }

}
