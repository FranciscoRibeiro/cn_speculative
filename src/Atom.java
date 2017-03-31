import java.util.List;

class Atom {
  private String name;
  private List<String> parameters;

  public Atom(String name, List<String> parameters) {
    this.name       = name;
    this.parameters = parameters;
  }

  public String name() {
    return this.name;
  }

  public List<String> parameters() {
    return this.parameters;
  }
}
