import java.util.List;

class Rule {
  private Atom head;
  private List<Constraint> constraints;
  private List<Atom> body;

  public Rule(Atom head, List<Constraint> constraints, List<Atom> body) {
    this.head = head;
    this.constraints = constraints;
    this.body = body;
  }

  public Atom getHead() {
    return this.head;
  }

  public List<Constraint> getConstraints() {
    return this.constraints;
  }

  public List<Atom> getBody() {
    return this.body;
  }

  public void setHead(Atom head) {
    this.head = head;
  }

  public void setConstraints(List<Constraint> constraints) {
    this.constraints = constraints;
  }

  public void setBody(List<Atom> body) {
    this.body = body;
  }
}
