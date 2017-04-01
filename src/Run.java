import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
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
  private ScriptEngine engine;

  public Run(List<Process> aps, List<Process> sps, List<Rule> delta, List<AskableAtom> aaq, List<Rule> rf, List<Rule> cbs, List<Rule> rules) {
    this.aps = aps;
    this.sps = sps;
    this.delta = delta;
    this.aaq = aaq;
    this.rf = rf;
    this.cbs = cbs;
    this.rules = rules;

    ScriptEngineManager mgr = new ScriptEngineManager();
    this.engine = mgr.getEngineByName("JavaScript");
  }

  public List<Rule> getCbs() {
    return cbs;
  }

  public Rule getCBSRating(){
    for(Rule r: this.cbs){
      if(r.getHead().name().equals("rating")){
        return r;
      }
    }
    return null;
  }

  public Rule getCBSDuration(){
    for(Rule r: this.cbs){
      if(r.getHead().name().equals("duration")){
        return r;
      }
    }
    return null;
  }

  public Rule getCBSGenre(){
    for(Rule r: this.cbs){
      if(r.getHead().name().equals("genre")){
        return r;
      }
    }
    return null;
  }

  public Rule getCBSPopularity(){
    for(Rule r: this.cbs){
      if(r.getHead().name().equals("popularity")){
        return r;
      }
    }
    return null;
  }

  public void updateCBSField(String parameter, String value){
    Rule r = null;
    switch(parameter){
      case "rating":
        r = getCBSRating();
        break;
      case "duration":
        r = getCBSDuration();
        break;
      case "genre":
        r = getCBSGenre();
        break;
      case "popularity":
        r = getCBSPopularity();
        break;
    }
    //Not intended to use with popularity updates
    Constraint c = new Constraint(parameter, Constraint.EQ, value);
    List<Constraint> cList = new ArrayList<>();
    cList.add(c);
    r.setConstraints(cList);
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
    firstParams.add("TheMatrix");
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
    constraints2.add(c3);
    constraints2.add(c4);
    constraints2.add(c5);
    constraints2.add(c6);
    AskableAtom aa2 = createAskableAtom("genre", "G", "db");
    AskableAtom aa3 = createAskableAtom("duration", "D", "db");
    AskableAtom aa4 = createAskableAtom("rating", "R", "db");
    Atom a2 = createSingleAtom("exists", "F");
    List<Atom> body2 = new ArrayList<>();
    body2.add(aa2);
    body2.add(aa3);
    body2.add(aa4);
    body2.add(a2);
    Rule rule2 = new Rule(head2, constraints2, body2);

    /* recommended(F, A) ← A ∈ {yes}, L ∈ {no}, M ∈ {yes}||tooLong(F, L), masterpiece(F, M). */
    Atom head3 = createAtom("recommended", "A");
    Constraint c7 = createConstraint("A", Constraint.IN, "yes");
    Constraint c8 = createConstraint("L", Constraint.IN, "no");
    Constraint c9 = createConstraint("M", Constraint.IN, "yes");
    List<Constraint> constraints3 = new ArrayList<>();
    constraints3.add(c7);
    constraints3.add(c8);
    constraints3.add(c9);
    Atom a3 = createAtom("tooLong", "L");
    Atom a4 = createAtom("masterpiece", "M");
    List<Atom> body3 = new ArrayList<>();
    body3.add(a3);
    body3.add(a4);
    Rule rule3 = new Rule(head3, constraints3, body3);

    /* recommended(F, A) ← A ∈ {yes}, T ∈ {yes}||trendingMovie(F, T). */
    Atom head4 = createAtom("recommended", "A");
    Constraint c10 = createConstraint("A", Constraint.IN, "yes");
    Constraint c11 = createConstraint("T", Constraint.IN, "yes");
    List<Constraint> constraints4 = new ArrayList<>();
    constraints4.add(c10);
    constraints4.add(c11);
    Atom a5 = createAtom("trendingMovie", "T");
    List<Atom> body4 = new ArrayList<>();
    body4.add(a5);
    Rule rule4 = new Rule(head4, constraints4, body4);

    /* recommended(F, A) ← A ∈ {no}, L ∈ {yes}||tooLong(F, L)@db. */
    Atom head5 = createAtom("recommended", "A");
    Constraint c12 = createConstraint("A", Constraint.IN, "no");
    Constraint c13 = createConstraint("L", Constraint.IN, "yes");
    List<Constraint> constraints5 = new ArrayList<>();
    constraints5.add(c12);
    constraints5.add(c13);
    Atom a6 = createAtom("tooLong", "L");
    List<Atom> body5 = new ArrayList<>();
    body5.add(a6);
    Rule rule5 = new Rule(head5, constraints5, body5);

    /* recommended(F, A) ← A ∈ {no}, T G ∈ {no}, M ∈ {no}||topGenre(F, T G), masterpiece(F, M). */
    Atom head6 = createAtom("recommended", "A");
    Constraint c14 = createConstraint("A", Constraint.IN, "no");
    Constraint c15 = createConstraint("TG", Constraint.IN, "no");
    Constraint c16 = createConstraint("M", Constraint.IN, "no");
    List<Constraint> constraints6 = new ArrayList<>();
    constraints6.add(c14);
    constraints6.add(c15);
    constraints6.add(c16);
    Atom a7 = createAtom("topGenre", "TG");
    Atom a8 = createAtom("masterpiece", "M");
    List<Atom> body6 = new ArrayList<>();
    body6.add(a7);
    body6.add(a8);
    Rule rule6 = new Rule(head6, constraints6, body6);

    /* tooLong(F, A) ← A ∈ {yes}, D > 240||duration(F, D)@db, exists(F). */
    Atom head7 = createAtom("tooLong", "A");
    Constraint c17 = createConstraint("A", Constraint.IN, "yes");
    Constraint c18 = createConstraint("D", Constraint.GT, "240");
    List<Constraint> constraints7 = new ArrayList<>();
    constraints7.add(c17);
    constraints7.add(c18);
    AskableAtom aa5 = createAskableAtom("duration", "D","db" );
    Atom a9 = createSingleAtom("exists", "F");
    List<Atom> body7 = new ArrayList<>();
    body7.add(aa5);
    body7.add(a9);
    Rule rule7 = new Rule(head7, constraints7, body7);

    /* tooLong(F, A) ← A ∈ {no}, D ≤ 240||duration(F, D)@db, exists(F). */
    Atom head8 = createAtom("tooLong", "A");
    Constraint c19 = createConstraint("A", Constraint.IN, "no");
    Constraint c20 = createConstraint("D", Constraint.LEQ, "240");
    List<Constraint> constraints8 = new ArrayList<>();
    constraints8.add(c19);
    constraints8.add(c20);
    AskableAtom aa6 = createAskableAtom("duration", "D","db" );
    Atom a10 = createSingleAtom("exists", "F");
    List<Atom> body8 = new ArrayList<>();
    body8.add(aa6);
    body8.add(a10);
    Rule rule8 = new Rule(head8, constraints8, body8);

    /* masterpiece(F, A) ← A ∈ {yes}, R ≥ 8.3, P < 3||rating(F, R)@db, popularity(F, P)@db, exists(F). */
    Atom head9 = createAtom("masterpiece", "A");
    Constraint c21 = createConstraint("A", Constraint.IN, "yes");
    Constraint c22 = createConstraint("R", Constraint.GEQ, "8.3");
    Constraint c23 = createConstraint("P", Constraint.LT, "3");
    List<Constraint> constraints9 = new ArrayList<>();
    constraints9.add(c21);
    constraints9.add(c22);
    constraints9.add(c23);
    AskableAtom aa7 = createAskableAtom("rating", "R","db" );
    AskableAtom aa8 = createAskableAtom("popularity", "P","db" );
    Atom a11 = createSingleAtom("exists", "F");
    List<Atom> body9 = new ArrayList<>();
    body9.add(aa7);
    body9.add(aa8);
    body9.add(a11);
    Rule rule9 = new Rule(head9, constraints9, body9);

    /* masterpiece(F, A) ← A ∈ {no}, R < 8.3||rating(F, R)@db, exists(F). */
    Atom head10 = createAtom("masterpiece", "A");
    Constraint c24 = createConstraint("A", Constraint.IN, "no");
    Constraint c25 = createConstraint("R", Constraint.LT, "8.3");
    List<Constraint> constraints10 = new ArrayList<>();
    constraints10.add(c24);
    constraints10.add(c25);
    AskableAtom aa9 = createAskableAtom("rating", "R","db" );
    Atom a12 = createSingleAtom("exists", "F");
    List<Atom> body10 = new ArrayList<>();
    body10.add(aa9);
    body10.add(a12);
    Rule rule10 = new Rule(head10, constraints10, body10);

    /* masterpiece(F, A) ← A ∈ {no}, P ≥ 3||popularity(F, P)@db, exists(F). */
    Atom head11 = createAtom("masterpiece", "A");
    Constraint c26 = createConstraint("A", Constraint.IN, "no");
    Constraint c27 = createConstraint("P", Constraint.GEQ, "8.3");
    List<Constraint> constraints11 = new ArrayList<>();
    constraints11.add(c26);
    constraints11.add(c27);
    AskableAtom aa10 = createAskableAtom("popularity", "P","db" );
    Atom a13 = createSingleAtom("exists", "F");
    List<Atom> body11 = new ArrayList<>();
    body11.add(aa10);
    body11.add(a13);
    Rule rule11 = new Rule(head11, constraints11, body11);

    /* topGenre(F, A) ← A ∈ {no}, G /∈ {sci−f i, thriller}||genre(F, G)@db, exists(F). */
    Atom head12 = createAtom("topGenre", "A");
    Constraint c28 = createConstraint("A", Constraint.IN, "no");
    Constraint c29 = createConstraint("G", Constraint.IN, "sci-fi");
    Constraint c30 = createConstraint("G", Constraint.IN, "thriller");
    List<Constraint> constraints12 = new ArrayList<>();
    constraints12.add(c28);
    constraints12.add(c29);
    constraints12.add(c30);
    AskableAtom aa11 = createAskableAtom("genre", "G","db" );
    Atom a14 = createSingleAtom("exists", "F");
    List<Atom> body12 = new ArrayList<>();
    body12.add(aa11);
    body12.add(a14);
    Rule rule12 = new Rule(head12, constraints12, body12);

    /* trendingMovie(F, A) ← A ∈ {yes}, P ≤ 2||popularity(F, P)@db, exists(F). */
    Atom head13 = createAtom("trendingMovie", "A");
    Constraint c31 = createConstraint("A", Constraint.IN, "yes");
    Constraint c32 = createConstraint("P", Constraint.LEQ, "2");
    List<Constraint> constraints13 = new ArrayList<>();
    constraints13.add(c31);
    constraints13.add(c32);
    AskableAtom aa12 = createAskableAtom("popularity", "P","db" );
    Atom a15 = createSingleAtom("exists", "F");
    List<Atom> body13 = new ArrayList<>();
    body13.add(aa12);
    body13.add(a15);
    Rule rule13 = new Rule(head13, constraints13, body13);

    /* exists(F) ← F ∈ {TheMatrix}||. */
    Atom head14 = createSingleAtom("exists", "F");
    Constraint c33 = createConstraint("F", Constraint.IN, "TheMatrix");
    List<Constraint> constraints14 = new ArrayList<>();
    constraints14.add(c33);
    Rule rule14 = new Rule(head14, constraints14, null);

    /* exists(F) ← F ∈ {Inception}||. */
    Atom head15 = createSingleAtom("exists", "F");
    Constraint c34 = createConstraint("F", Constraint.IN, "Inception");
    List<Constraint> constraints15 = new ArrayList<>();
    constraints15.add(c34);
    Rule rule15 = new Rule(head15, constraints15, null);

    /* exists(F) ← F ∈ {Memento}||. */
    Atom head16 = createSingleAtom("exists", "F");
    Constraint c35 = createConstraint("F", Constraint.IN, "Memento");
    List<Constraint> constraints16 = new ArrayList<>();
    constraints16.add(c35);
    Rule rule16 = new Rule(head16, constraints16, null);

    /* exists(F) ← F ∈ {ThePrestige}||. */
    Atom head17 = createSingleAtom("exists", "F");
    Constraint c36 = createConstraint("F", Constraint.IN, "ThePrestige");
    List<Constraint> constraints17 = new ArrayList<>();
    constraints17.add(c36);
    Rule rule17 = new Rule(head17, constraints17, null);

    /* exists(F) ← F ∈ {TheLorfOfTheRings}||. */
    Atom head18 = createSingleAtom("exists", "F");
    Constraint c37 = createConstraint("F", Constraint.IN, "TheLorfOfTheRings");
    List<Constraint> constraints18 = new ArrayList<>();
    constraints18.add(c37);
    Rule rule18 = new Rule(head18, constraints18, null);

    /* exists(F) ← F ∈ {Arrival}||. */
    Atom head19 = createSingleAtom("exists", "F");
    Constraint c38 = createConstraint("F", Constraint.IN, "Arrival");
    List<Constraint> constraints19 = new ArrayList<>();
    constraints19.add(c38);
    Rule rule19 = new Rule(head19, constraints19, null);

    List<Rule> rules = new ArrayList<>();
    rules.add(rule1); rules.add(rule2); rules.add(rule3);
    rules.add(rule4); rules.add(rule5); rules.add(rule6);
    rules.add(rule7); rules.add(rule8); rules.add(rule9);
    rules.add(rule10); rules.add(rule11); rules.add(rule12);
    rules.add(rule13); rules.add(rule14); rules.add(rule15);
    rules.add(rule16); rules.add(rule17); rules.add(rule18);
    rules.add(rule19);

    return rules;
  }

  private void sendQuestion(Atom atom){

  }

  /* Really basic check here
  * If atom.name is the same as any atom's name present in AAQ, then we consider that AAQ contains that atom
  * i.e. the question has been asked already
  * */
  private boolean inAAQ(Atom atom){
    for(Atom a: this.aaq){
      if(a.name().equals(atom.name())){
        return true;
      }
    }
    return false;
  }

  private boolean inUD(Process process, Atom atom){
    for(Atom a: process.getUd()){
      if(a.name().equals(atom.name())){
        return true;
      }
    }
    return false;
  }

  private List<Constraint> inRF(Atom atom){
    for(Rule r: this.rf){
      if(r.getHead().equals(atom.name())){
        return r.getConstraints();
      }
    }
    return null;
  }


  private boolean isNumber(String string){
    try{
      Double.parseDouble(string);
      return true;
    }
    catch(NumberFormatException e){
      return false;
    }
  }

  /*private boolean checkConsistency(List<Constraint> constraints, Process process){
    List<Constraint> processConstraints = process.getConstraints();
    for(Constraint c1: constraints){
      String pName = c1.parameterName();
      for(Constraint c2: processConstraints){
        if(pName.equals(c2.parameterName())){
          Integer type1 = c1.typeOfConstraint();
          Integer type2 = c2.typeOfConstraint();

          if(isNumber(c1.value()) && isNumber(c2.value())){
            double value1 = Double.parseDouble(c1.value());
            double value2 = Double.parseDouble(c2.value());

            switch (type1){
              case 2: //Constraint.LEQ

                break;
            }
          }
        }
      }
    }
  }*/

  /*public void reduction(){
    List<Constraint> constraints;
    Process p = this.aps.get(0); //Go to first process in APS
    this.aps.remove(0); //Remove the process
    Atom goal = p.getGoalSet().get(0); //Go to first goal in the process
    p.getGoalSet().remove(0); //Remove it from the goal set
    if(goal.isAskable()){
      if(!inAAQ(goal)) {
        sendQuestion(goal);
      }
      if(inUD(p, goal)){
        this.aps.add(p);
      }
      else if((constraints = inRF(goal)) != null){
        checkConsistency(constraints, p)
      }
    }
  }*/

  public static void main(String[] args){
    List<Process> aps = createFirstAPS();
    List<Process> sps = new ArrayList<>();
    List<Rule> delta = createDefaults();
    List<AskableAtom> aaq = new ArrayList<>();
    List<Rule> rf = new ArrayList<>();
    List<Rule> cbs = createDefaults(); //In the beginning, the CBS is the same as Delta
    List<Rule> rules = createRules();

    Run run = new Run(aps, sps, delta, aaq, rf, cbs, rules);

    Runtime rt = Runtime.instance();
    rt.setCloseVM(true);

    // Create a new container.
    Profile profile = new ProfileImpl();
    profile.setParameter(Profile.MAIN_HOST, "localhost");
    profile.setParameter(Profile.MAIN_PORT, "1099");
    profile.setParameter(Profile.GUI, "true");

    AgentContainer container = rt.createMainContainer(profile);
    SpecAgent sAgent = new SpecAgent(run);
    try{
      container.acceptNewAgent("SpecAgent", sAgent).start();
      container.start();
    }
    catch (Exception e){
      e.printStackTrace();
    }


  }

}
