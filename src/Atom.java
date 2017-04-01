import java.util.List;
import java.lang.StringBuilder;

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

  public boolean isAskable() {
    return false;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(name + "(");
    for (String parameter : this.parameters) {
      sb.append(parameter);
      if (this.parameters.indexOf(parameter) != (this.parameters.size() -1)) {
        sb.append(",");
      }
    }
    sb.append(")");
    return sb.toString();
  }
}
