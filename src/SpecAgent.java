import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiko on 01-04-2017.
 */
public class SpecAgent extends Agent {
  private Run run;

  public SpecAgent(Run run) {
    this.run = run;
  }

  protected void setup() {
    addBehaviour(new MessageReceiverBehaviour(this));
    addBehaviour(new FirstRunBehaviour(this));
  }

  class FirstRunBehaviour extends OneShotBehaviour {
    public FirstRunBehaviour(Agent agent) {
      super(agent);
    }

    private List<Process> createStep13APS(){
      List<Process> aps = new ArrayList<>();

      /* Process 1 */
      List<Constraint> p1_constraints = new ArrayList<>();
      Constraint p1_c1 = new Constraint("A", Constraint.IN, "no");
      Constraint p1_c2 = new Constraint("TG", Constraint.IN, "no");
      Constraint p1_c3 = new Constraint("G", Constraint.NOT_IN, "sci-fi");
      Constraint p1_c4 = new Constraint("G", Constraint.NOT_IN, "thriller");
      p1_constraints.add(p1_c1);
      p1_constraints.add(p1_c2);
      p1_constraints.add(p1_c3);
      p1_constraints.add(p1_c4);

      List<AskableAtom> p1_ud = new ArrayList<>();
      List<String> p1_ud_params = new ArrayList<>();
      p1_ud_params.add("Matrix");
      p1_ud_params.add("G");
      AskableAtom p1_aa1 = new AskableAtom("genre", p1_ud_params, "db");
      p1_ud.add(p1_aa1);

      Process p1 = new Process(false, null, null, p1_constraints, p1_ud);


      /* Process 2 */
      List<Constraint> p2_constraints = new ArrayList<>();
      Constraint p2_c1 = new Constraint("A", Constraint.IN, "no");
      Constraint p2_c2 = new Constraint("TG", Constraint.IN, "no");
      Constraint p2_c3 = new Constraint("M", Constraint.IN, "no");
      Constraint p2_c4 = new Constraint("R", Constraint.LT, "8.3");
      Constraint p2_c5 = new Constraint("G", Constraint.NOT_IN, "sci-fi");
      Constraint p2_c6 = new Constraint("G", Constraint.NOT_IN, "thriller");
      p2_constraints.add(p2_c1);
      p2_constraints.add(p2_c2);
      p2_constraints.add(p2_c3);
      p2_constraints.add(p2_c4);
      p2_constraints.add(p2_c5);
      p2_constraints.add(p2_c6);


      List<AskableAtom> p2_ud = new ArrayList<>();
      List<String> p2_ud_params1 = new ArrayList<>();
      p2_ud_params1.add("Matrix");
      p2_ud_params1.add("R");
      AskableAtom p2_aa1 = new AskableAtom("rating", p2_ud_params1, "db");
      p2_ud.add(p2_aa1);

      List<String> p2_ud_params2 = new ArrayList<>();
      p2_ud_params2.add("Matrix");
      p2_ud_params2.add("G");
      AskableAtom p2_aa2 = new AskableAtom("genre", p2_ud_params2, "db");
      p2_ud.add(p2_aa2);

      Process p2 = new Process(false, null, null, p2_constraints, p2_ud);


      /* Process 3 */
      List<Constraint> p3_constraints = new ArrayList<>();
      Constraint p3_c1 = new Constraint("A", Constraint.IN, "no");
      Constraint p3_c2 = new Constraint("TG", Constraint.IN, "no");
      Constraint p3_c3 = new Constraint("M", Constraint.IN, "no");
      Constraint p3_c4 = new Constraint("P", Constraint.GEQ, "3");
      Constraint p3_c5 = new Constraint("G", Constraint.NOT_IN, "sci-fi");
      Constraint p3_c6 = new Constraint("G", Constraint.NOT_IN, "thriller");
      p3_constraints.add(p3_c1);
      p3_constraints.add(p3_c2);
      p3_constraints.add(p3_c3);
      p3_constraints.add(p3_c4);
      p3_constraints.add(p3_c5);
      p3_constraints.add(p3_c6);


      List<AskableAtom> p3_ud = new ArrayList<>();
      List<String> p3_ud_params1 = new ArrayList<>();
      p3_ud_params1.add("Matrix");
      p3_ud_params1.add("P");
      AskableAtom p3_aa1 = new AskableAtom("popularity", p3_ud_params1, "db");
      p3_ud.add(p3_aa1);

      List<String> p3_ud_params2 = new ArrayList<>();
      p3_ud_params2.add("Matrix");
      p3_ud_params2.add("G");
      AskableAtom p3_aa2 = new AskableAtom("genre", p3_ud_params2, "db");
      p3_ud.add(p3_aa2);

      Process p3 = new Process(false, null, null, p3_constraints, p3_ud);

      aps.add(p1);
      aps.add(p2);
      aps.add(p3);

      return aps;
    }

    private List<Process> createStep13SPS() {
      List<Process> sps = new ArrayList<>();

      /* Process 4 */
      List<AskableAtom> p4_sas = new ArrayList<>();
      List<String> p4_sas_params = new ArrayList<>();
      p4_sas_params.add("Matrix");
      p4_sas_params.add("R");
      AskableAtom p4_aa1 = new AskableAtom("rating", p4_sas_params, "db");
      p4_sas.add(p4_aa1);

      List<Constraint> p4_constraints = new ArrayList<>();
      Constraint p4_c1 = new Constraint("A", Constraint.IN, "yes");
      Constraint p4_c2 = new Constraint("R", Constraint.GEQ, "9");
      p4_constraints.add(p4_c1);
      p4_constraints.add(p4_c2);

      List<Atom> p4_gs = new ArrayList<>();
      List<String> p4_gs_params = new ArrayList<>();
      p4_gs_params.add("Matrix");
      Atom p4_a1 = new Atom("exists", p4_gs_params);
      p4_gs.add(p4_a1);

      Process p4 = new Process(true, p4_sas, p4_gs, p4_constraints, null);


      /* Process 5 */
      List<AskableAtom> p5_sas = new ArrayList<>();
      List<String> p5_sas_params = new ArrayList<>();
      p5_sas_params.add("Matrix");
      p5_sas_params.add("R");
      AskableAtom p5_aa1 = new AskableAtom("rating", p5_sas_params, "db");
      p5_sas.add(p5_aa1);

      List<Constraint> p5_constraints = new ArrayList<>();
      Constraint p5_c1 = new Constraint("A", Constraint.IN, "yes");
      Constraint p5_c2 = new Constraint("G", Constraint.IN, "sci-fi");
      Constraint p5_c3 = new Constraint("D", Constraint.GT, "60");
      Constraint p5_c4 = new Constraint("R", Constraint.GT, "7.5");
      p5_constraints.add(p5_c1);
      p5_constraints.add(p5_c2);
      p5_constraints.add(p5_c3);
      p5_constraints.add(p5_c4);

      List<Atom> p5_gs = new ArrayList<>();
      List<String> p5_gs_params1 = new ArrayList<>();
      p5_gs_params1.add("Matrix");
      p5_gs_params1.add("G");
      AskableAtom p5_aa2 = new AskableAtom("genre", p5_gs_params1, "db");
      p5_gs.add(p5_aa2);

      List<String> p5_gs_params2 = new ArrayList<>();
      p5_gs_params2.add("Matrix");
      p5_gs_params2.add("D");
      AskableAtom p5_aa3 = new AskableAtom("duration", p5_gs_params2, "db");
      p5_gs.add(p5_aa3);

      List<String> p5_gs_params3 = new ArrayList<>();
      p5_gs_params3.add("Matrix");
      Atom p5_a1 = new Atom("exists", p5_gs_params3);
      p5_gs.add(p5_a1);

      Process p5 = new Process(true, p5_sas, p5_gs, p5_constraints, null);


      /* Process 6 */
      List<AskableAtom> p6_sas = new ArrayList<>();
      List<String> p6_sas_params = new ArrayList<>();
      p6_sas_params.add("Matrix");
      p6_sas_params.add("R");
      AskableAtom p6_aa1 = new AskableAtom("rating", p6_sas_params, "db");
      p6_sas.add(p6_aa1);

      List<Constraint> p6_constraints = new ArrayList<>();
      Constraint p6_c1 = new Constraint("A", Constraint.IN, "yes");
      Constraint p6_c2 = new Constraint("M", Constraint.IN, "yes");
      Constraint p6_c3 = new Constraint("L", Constraint.IN, "60");
      Constraint p6_c4 = new Constraint("D", Constraint.LEQ, "240");
      Constraint p6_c5 = new Constraint("R", Constraint.GEQ, "8.3");
      Constraint p6_c6 = new Constraint("P", Constraint.LT, "3");
      p6_constraints.add(p6_c1);
      p6_constraints.add(p6_c2);
      p6_constraints.add(p6_c3);
      p6_constraints.add(p6_c4);
      p6_constraints.add(p6_c5);
      p6_constraints.add(p6_c6);

      List<Atom> p6_gs = new ArrayList<>();
      List<String> p6_gs_params1 = new ArrayList<>();
      p6_gs_params1.add("Matrix");
      p6_gs_params1.add("P");
      AskableAtom p6_aa2 = new AskableAtom("popularity", p6_gs_params1, "db");
      p6_gs.add(p6_aa2);

      List<String> p6_gs_params2 = new ArrayList<>();
      p6_gs_params2.add("Matrix");
      p6_gs_params2.add("D");
      AskableAtom p6_aa3 = new AskableAtom("duration", p6_gs_params2, "db");
      p6_gs.add(p6_aa3);

      Process p6 = new Process(true, p6_sas, p6_gs, p6_constraints, null);


      /* Process 7 */
      List<AskableAtom> p7_sas = new ArrayList<>();
      List<String> p7_sas_params = new ArrayList<>();
      p7_sas_params.add("Matrix");
      p7_sas_params.add("P");
      AskableAtom p7_aa1 = new AskableAtom("popularity", p7_sas_params, "db");
      p7_sas.add(p7_aa1);

      List<Constraint> p7_constraints = new ArrayList<>();
      Constraint p7_c1 = new Constraint("A", Constraint.IN, "yes");
      Constraint p7_c2 = new Constraint("T", Constraint.IN, "yes");
      Constraint p7_c3 = new Constraint("P", Constraint.LEQ, "2");
      p7_constraints.add(p7_c1);
      p7_constraints.add(p7_c2);
      p7_constraints.add(p7_c3);

      List<Atom> p7_gs = new ArrayList<>();
      List<String> p7_gs_params = new ArrayList<>();
      p7_gs_params.add("Matrix");
      Atom p7_a1 = new Atom("exists", p7_gs_params);
      p7_gs.add(p7_a1);

      Process p7 = new Process(true, p7_sas, p7_gs, p7_constraints, null);


      /* Process 8 */
      List<AskableAtom> p8_sas = new ArrayList<>();
      List<String> p8_sas_params = new ArrayList<>();
      p8_sas_params.add("Matrix");
      p8_sas_params.add("D");
      AskableAtom p8_aa1 = new AskableAtom("duration", p8_sas_params, "db");
      p8_sas.add(p8_aa1);

      List<Constraint> p8_constraints = new ArrayList<>();
      Constraint p8_c1 = new Constraint("A", Constraint.IN, "no");
      Constraint p8_c2 = new Constraint("L", Constraint.IN, "yes");
      Constraint p8_c3 = new Constraint("D", Constraint.GT, "240");
      p8_constraints.add(p8_c1);
      p8_constraints.add(p8_c2);
      p8_constraints.add(p8_c3);

      Process p8 = new Process(true, p8_sas, null, p8_constraints, null);

      sps.add(p4);
      sps.add(p5);
      sps.add(p6);
      sps.add(p7);
      sps.add(p8);

      return sps;
    }

    @Override
    public void action() {
      boolean recommend = false;
      double cbsRating = Double.parseDouble(run.getCBSRating().getConstraints().get(0).value());
      double cbsDuration = Double.parseDouble(run.getCBSDuration().getConstraints().get(0).value());
      String cbsGenre = run.getCBSDuration().getConstraints().get(0).value();
      double cbsPopularity = 15; //Assume a value between 10 and 20
      //Main decisions taken during the first process reduction
      if (cbsRating >= 9) {
        recommend = true;
      }
      if (cbsRating > 7.5 && cbsDuration > 60 && cbsGenre.equals("sci-fi")) {
        recommend = true;
      }
      if (cbsPopularity <= 2) {
        recommend = true;
      }
      if (recommend) {
        System.out.println("The Matrix is recommended");
      } else {
        System.out.println("The Matrix is NOT recommended");
      }


      System.out.println("Current APS: ");
      List<Process> step13APS = createStep13APS();
      for(Process p: step13APS){
        System.out.println(p.toString());
      }

      System.out.println("Current SPS: ");
      List<Process> step13SPS = createStep13SPS();
      for(Process p: step13SPS){
        System.out.println(p.toString());
      }

      addBehaviour(new SendRatingQuestionBehaviour(myAgent));
      addBehaviour(new SecondRunBehaviour(myAgent));
    }
  }

  private void updateCBS(ACLMessage message){
    String parameter = message.getUserDefinedParameter("PARAMETER");
    String value = message.getUserDefinedParameter("VALUE");

    parameter = parameter.toLowerCase();
    run.updateCBSField(parameter, value);
  }

  class MessageReceiverBehaviour extends CyclicBehaviour {
    public MessageReceiverBehaviour(Agent agent) {
      super(agent);
    }

    @Override
    public void action() {
      ACLMessage message = myAgent.receive();
      int performative = message.getPerformative();

      if (message != null && performative == ACLMessage.INFORM) {
        updateCBS(message);
      } else {
        block();
      }
    }
  }

  class SendRatingQuestionBehaviour extends OneShotBehaviour {
    public SendRatingQuestionBehaviour(Agent agent) {
      super(agent);
    }

    @Override
    public void action(){
      //Sets the receiver agent of the following messages
      AID receiver        = new AID();
      receiver.setLocalName("SourceDatabase");

      //Message asking for the rating
      ACLMessage ratingQuestion  = new ACLMessage(ACLMessage.REQUEST);
      ratingQuestion.addUserDefinedParameter("FILM", "Matrix");
      ratingQuestion.addUserDefinedParameter("FILM_PARAMETER", "RATING");
      ratingQuestion.addReceiver(receiver);
      send(ratingQuestion);

      //Message asking for the genre
      ACLMessage genreQuestion  = new ACLMessage(ACLMessage.REQUEST);
      genreQuestion.addUserDefinedParameter("FILM", "Matrix");
      genreQuestion.addUserDefinedParameter("FILM_PARAMETER", "GENRE");
      genreQuestion.addReceiver(receiver);
      send(genreQuestion);



    }
  }

  class SecondRunBehaviour extends WakerBehaviour{
    public SecondRunBehaviour(Agent agent){
      super(agent, 10000);
    }

    @Override
    public void onWake(){

    }
  }
}
