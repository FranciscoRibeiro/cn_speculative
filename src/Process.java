import java.util.List;

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
}
