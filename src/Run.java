import java.util.ArrayList;
import java.util.List;

public class Run{
  private List<Process> aps; //Active Process Set
  private List<Process> sps; //Suspended Process Set
  private List<Rule> delta; //Default values
  private List<AskableAtom> aaq; //Already Asked Questions
  private List<Rule> rf; //Returned Facts
  private List<Rule> cbs; //Current Belief Set
  private List<Rule> rules;

  public Run(List<Process> aps, List<Process> sps, List<Rule> delta, List<AskableAtom> aaq, List<Rule> rf, List<Rule> cbs, List<Rule> rules) {
    this.aps = aps;
    this.sps = sps;
    this.delta = delta;
    this.aaq = aaq;
    this.rf = rf;
    this.cbs = cbs;
    this.rules = rules;
  }

  private static Atom createAtom(String name, String secondParameter){
    List<String> parameters = new ArrayList<>();
    parameters.add("F");
    parameters.add(secondParameter);
    Atom defaultAtom = new Atom(name, parameters);
    return defaultAtom;
  }

  private static Constraint createConstraint(String parameter, Integer constraintType, String value){
    return new Constraint(parameter, constraintType, value);
  }

  private static Rule createDefaultRule(Atom atom, Constraint constraint){
    List<Constraint> constraintsList = new ArrayList<>();
    constraintsList.add(constraint);
    return new Rule(atom, constraintsList, null);
  }

  private static List<Rule> createDefaults(){
    /* duration(F, D)@db ← D = 90|| */
    Atom defaultDurationAtom = createAtom("duration", "D");
    Constraint defaultDurationConstraint = createConstraint("D", Constraint.EQ, "90");
    Rule defaultDurationRule = createDefaultRule(defaultDurationAtom, defaultDurationConstraint);

    /* rating(F, R)@db ← R = 6|| */
    Atom defaultRatingAtom = createAtom("rating", "R");
    Constraint defaultRatingConstraint = createConstraint("R", Constraint.EQ, "6");
    Rule defaultRatingRule = createDefaultRule(defaultRatingAtom, defaultRatingConstraint);

    /* genre(F, G)@db ← G ∈ {action}|| */
    Atom defaultGenreAtom = createAtom("genre", "G");
    Constraint defaultGenreConstraint = createConstraint("G", Constraint.IN, "action");
    Rule defaultGenreRule = createDefaultRule(defaultGenreAtom, defaultGenreConstraint);

    /* popularity(F, P)@db ← 10 ≤ P ≤ 20
       In this case, Rule is instantiated directly and two constraints are created */
    Atom defaultPopularityAtom = createAtom("popularity", "P");
    Constraint defaultPopularity_constraint1 = createConstraint("P", Constraint.GEQ, "10");
    Constraint defaultPopularity_constraint2 = createConstraint("P", Constraint.LEQ, "20");

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

  /* Creates first process asking if Matrix is recommended or not */
  private static List<Process> createFirstAPS(){
    List<String> firstParams = new ArrayList<>();
    firstParams.add("Matrix");
    firstParams.add("A");

    List<Atom> firstGoalSet = new ArrayList<>();
    Atom firstAtom = new Atom("recommended", firstParams);
    firstGoalSet.add(firstAtom);

    List<Process> firstAPS = new ArrayList<>();
    Process firstProcess = new Process(false, null, firstGoalSet, null, null);
    firstAPS.add(firstProcess);

    return firstAPS;
  }


  private static AskableAtom createAskableAtom(String name, String secondParameter, String source){
    List<String> parameters = new ArrayList<>();
    parameters.add("F");
    parameters.add(secondParameter);
    AskableAtom askableAtom = new AskableAtom(name, parameters, source);
    return askableAtom;
  }

  /* Used when the Atom has only one parameter */
  private static Atom createSingleAtom(String name, String parameter){
    List<String> params = new ArrayList<>();
    params.add(parameter);
    return new Atom(name, params);
  }

  private static List<Rule> createRules(){
    /* recommended(F, A) ← A ∈ {yes}, R ≥ 9||rating(F, R)@db, exists(F). */
    Atom head1 = createAtom("recommended", "A");
    Constraint c1 = createConstraint("A", Constraint.IN, "yes");
    Constraint c2 = createConstraint("R", Constraint.GEQ, "9");
    List<Constraint> constraints1 = new ArrayList<>();
    constraints1.add(c1);
    constraints1.add(c2);
    AskableAtom aa1 = createAskableAtom("rating", "R", "db");
    Atom a1 = createSingleAtom("exists", "F");
    List<Atom> body1 = new ArrayList<>();
    body1.add(aa1);
    body1.add(a1);
    Rule rule1 = new Rule(head1, constraints1, body1);

    /* recommended(F, A) ← A ∈ {yes}, G ∈ {sci − f i}, D > 60, R > 7.5||
    genre(F, G)@db, duration(F, D)@db, rating(F, R)@db, exists(F). */
    Atom head2 = createAtom("recommended", "A");
    Constraint c3 = createConstraint("A", Constraint.IN, "yes");
    Constraint c4 = createConstraint("G", Constraint.IN, "sci-fi");
    Constraint c5 = createConstraint("D", Constraint.GT, "60");
    Constraint c6 = createConstraint("R", Constraint.GT, "7.5");
    List<Constraint> constraints2 = new ArrayList<>();
    constraints1.add(c3);
    constraints1.add(c4);
    constraints1.add(c5);
    constraints1.add(c6);
    AskableAtom aa2 = createAskableAtom("genre", "G", "db");
    AskableAtom aa3 = createAskableAtom("duration", "D", "db");
    AskableAtom aa4 = createAskableAtom("rating", "R", "db");
    Atom a2 = createSingleAtom("exists", "F");
    List<Atom> body2 = new ArrayList<>();
    body1.add(aa2);
    body1.add(aa3);
    body1.add(aa4);
    body1.add(a2);
    Rule rule2 = new Rule(head2, constraints2, body2);

  }

  public static void main(String[] args){
    List<Process> aps = createFirstAPS();
    List<Process> sps = new ArrayList<>();
    List<Rule> delta = createDefaults();
    List<AskableAtom> aaq = new ArrayList<>();
    List<Rule> rf = new ArrayList<>();
    List<Rule> cbs = createDefaults(); //In the beginning, the CBS is the same as Delta
    List<Rule> rules = createRules();

    Run run = new Run(aps, sps, delta, aaq, rf, cbs, rules);
  }

}
