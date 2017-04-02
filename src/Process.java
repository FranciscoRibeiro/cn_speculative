import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;

/**
 * Created by kiko on 31-03-2017.
 */
public class Process {
  private boolean suspended;
  private List<AskableAtom> sas;
  private List<Atom> goalSet;
  private List<Constraint> constraints;
  private List<AskableAtom> ud;

  public Process(boolean suspended, List<AskableAtom> sas, List<Atom> goalSet, List<Constraint> constraints, List<AskableAtom> ud) {
    this.suspended = suspended;
    this.sas = sas;
    this.goalSet = goalSet;
    this.constraints = constraints;
    this.ud = ud;
  }

  public boolean isSuspended() {
    return this.suspended;
  }

  public List<AskableAtom> getSas() {
    return this.sas;
  }

  public List<Atom> getGoalSet() {
    return this.goalSet;
  }

  public List<Constraint> getConstraints() {
    return this.constraints;
  }

  public List<AskableAtom> getUd() {
    return this.ud;
  }

  public void setSuspended(boolean suspended) {
    this.suspended = suspended;
  }

  public void setSas(List<AskableAtom> sas) {
    this.sas = sas;
  }

  public void setGoalSet(List<Atom> goalSet) {
    this.goalSet = goalSet;
  }

  public void setConstraints(List<Constraint> constraints) {
    this.constraints = constraints;
  }

  public void setUd(List<AskableAtom> ud) {
    this.ud = ud;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("< ");

    // SAS.
    if (this.suspended) {
      sb.append("{");
      for (AskableAtom atom : this.sas) {
        sb.append(atom.toString());
        if (this.sas.indexOf(atom) != (this.sas.size() -1)) {
          sb.append(",");
        }
      }
      sb.append("}");
    }

    sb.append("\u2190 ");

    // C.
    if ((this.constraints != null) && (this.constraints.size() > 0)) {
      for (Constraint c : this.constraints) {
        sb.append(c.toString());
        if (this.constraints.indexOf(c) != (this.constraints.size() -1)) {
          sb.append(",");
        }
        sb.append(" ");
      }
    }

    sb.append("|| ");

    // GS.
    if ((this.goalSet == null) || (this.goalSet.size() == 0)) {
      sb.append("\u2205, ");
    } else {
      sb.append("{");
      for (Atom a : this.goalSet) {
        if (a instanceof AskableAtom) {
          AskableAtom tmp = (AskableAtom) a;
          sb.append(tmp.toString());
        } else {
          sb.append(a.toString());
        }
        if (this.goalSet.indexOf(a) != (this.goalSet.size() -1)) {
          sb.append(", ");
        }
      }
      sb.append("}, ");
    }

    // UD.
    if ((this.ud == null) || (this.ud.size() == 0)) {
      sb.append("\u2205");
    } else {
      sb.append("{");
      for (AskableAtom a : this.ud) {
        sb.append(a.toString());
        if (this.ud.indexOf(a) != (this.ud.size() -1)) {
          sb.append(", ");
        }
      }
      sb.append("}");
    }

    sb.append(" >");
    return sb.toString();
  }
  public void addUD(AskableAtom askAtom){
    if(this.ud == null){
      this.ud = new ArrayList<AskableAtom>();
    }
    this.ud.add(askAtom);
  }
}
