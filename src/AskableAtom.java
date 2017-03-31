import java.util.List;

class AskableAtom extends Atom {
  private String source;

  public AskableAtom(String name, List<String> parameters, String source) {
    super(name, parameters);
    this.source = source;
  }

  public String informationSource() {
    return this.source;
  }
}
