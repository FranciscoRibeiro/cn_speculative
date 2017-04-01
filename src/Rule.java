import java.util.List;
import java.lang.StringBuilder;

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

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(head.toString());
    sb.append("  ⃪ ");
    for (Constraint constraint : this.constraints) {
      sb.append(constraint.toString());
      if (this.constraints.indexOf(constraint) != (this.constraints.size() -1)) {
        sb.append(",");
      }
      sb.append(" ");
    }
    sb.append("|| ");
    if (body != null) {
      for (Atom atom : this.body) {
        if (atom instanceof AskableAtom) {
          AskableAtom tmp = (AskableAtom) atom;
          sb.append(tmp.toString());
        } else {
          sb.append(atom.toString());
        }
        if (this.body.indexOf(atom) != (this.body.size() -1)) {
          sb.append(", ");
        }
      }
    }
    sb.append(".");

    return sb.toString();
  }
}
