import java.util.List;
import java.lang.StringBuilder;

class AskableAtom extends Atom {
  private String source;

  public AskableAtom(String name, List<String> parameters, String source) {
    super(name, parameters);
    this.source = source;
  }

  public String informationSource() {
    return this.source;
  }

  @Override
  public boolean isAskable() {
    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    sb.append("@" + source);
    return sb.toString();
  }
}
