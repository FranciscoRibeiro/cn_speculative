import java.util.ArrayList;
import java.util.List;

public class Run{
  private List<Process> aps; //Active Process Set
  private List<Process> sps; //Suspended Process Set
  private List<Rule> delta; //Default values
  private List<AskableAtom> aaq; //Already Asked Questions
  private List<Rule> rf; //Returned Facts
  private List<Rule> cbs; //Current Belief Set

  public Run(List<Process> aps, List<Process> sps, List<Rule> delta, List<AskableAtom> aaq, List<Rule> rf, List<Rule> cbs) {
    this.aps = aps;
    this.sps = sps;
    this.delta = delta;
    this.aaq = aaq;
    this.rf = rf;
    this.cbs = cbs;
  }

  private static Atom createDefaultAtom(String name, String secondParameter){
    List<String> durationParameters = new ArrayList<>();
    durationParameters.add("F");
    durationParameters.add(secondParameter);
    Atom defaultAtom = new Atom(name, durationParameters);
    return defaultAtom;
  }

  private static Constraint createDefaultConstraint(String parameter, Integer constraintType, String value){
    return new Constraint(parameter, constraintType, value);
  }

  private static Rule createDefaultRule(Atom atom, Constraint constraint){
    List<Constraint> constraintsList = new ArrayList<>();
    constraintsList.add(constraint);
    return new Rule(atom, constraintsList, null);
  }

  private static List<Rule> createDefaults(){
    /* duration(F, D)@db ← D = 90|| */
    Atom defaultDurationAtom = createDefaultAtom("duration", "D");
    Constraint defaultDurationConstraint = createDefaultConstraint("D", Constraint.EQ, "90");
    Rule defaultDurationRule = createDefaultRule(defaultDurationAtom, defaultDurationConstraint);

    /* rating(F, R)@db ← R = 6|| */
    Atom defaultRatingAtom = createDefaultAtom("rating", "R");
    Constraint defaultRatingConstraint = createDefaultConstraint("R", Constraint.EQ, "6");
    Rule defaultRatingRule = createDefaultRule(defaultRatingAtom, defaultRatingConstraint);

    /* genre(F, G)@db ← G ∈ {action}|| */
    Atom defaultGenreAtom = createDefaultAtom("genre", "G");
    Constraint defaultGenreConstraint = createDefaultConstraint("G", Constraint.IN, "action");
    Rule defaultGenreRule = createDefaultRule(defaultGenreAtom, defaultGenreConstraint);

    /* popularity(F, P)@db ← 10 ≤ P ≤ 20
       In this case, Rule is instantiated directly and two constraints are created */
    Atom defaultPopularityAtom = createDefaultAtom("popularity", "P");
    Constraint defaultPopularity_constraint1 = createDefaultConstraint("P", Constraint.GEQ, "10");
    Constraint defaultPopularity_constraint2 = createDefaultConstraint("P", Constraint.LEQ, "20");

    List<Constraint> popularityConstraintsList = new ArrayList<>();
    popularityConstraintsList.add(defaultPopularity_constraint1);
    popularityConstraintsList.add(defaultPopularity_constraint2);
    Rule defaultPopularityRule = new Rule(defaultPopularityAtom, popularityConstraintsList, null);

    /* Create list with the rules for the default values */
    List<Rule> defaultRules = new ArrayList<>();
    defaultRules.add(defaultDurationRule);
    defaultRules.add(defaultRatingRule);
    defaultRules.add(defaultGenreRule);
    defaultRules.add(defaultPopularityRule);

    return defaultRules;
  }

  public static void main(String[] args){
    List<Process> aps = new ArrayList<>();
    List<Process> sps = new ArrayList<>();
    List<Rule> delta = createDefaults();
    List<AskableAtom> aaq = new ArrayList<>();
    List<Rule> rf = new ArrayList<>();
    List<Rule> cbs = createDefaults();

    Run run = new Run(aps, sps, delta, aaq, rf, cbs);
  }

}
