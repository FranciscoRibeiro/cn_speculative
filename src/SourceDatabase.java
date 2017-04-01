import java.util.Hashtable;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.core.Agent;

class SourceDatabase extends Agent {
  private Hashtable<String, Film> films;

  public SourceDatabase() {
    this.films = new Hashtable<String, Film>();
    this.films.put("Matrix", new Film(9.0, 180, "sci-fi", 10));
    this.films.put("Frozen", new Film(2.0, 5, "music", 1));
    this.films.put("Harry Potah", new Film(8.2, 150, "magic", 180));
  }

  public SourceDatabase(Hashtable<String, Film> films) {
    this.films = films;
  }

  public String getAnswer(String filmName, String parameter) {
    Film film = films.get(filmName);
    parameter.toUpperCase();
    String res;

    if (parameter == "RATING") {
      res = film.getRating().toString();
    } else
    if (parameter == "DURATION") {
      res = film.getDuration().toString();
    } else
    if (parameter == "GENRE") {
      res = film.getGenre();
    } else {
      res = film.getPopularity().toString();
    }

    return res;
  }

  protected void setup() {
    // Cyclic Behaviour to interpret messages.
    addBehaviour(new MessageReceiverBehaviour(this));
  }

  protected void reply(ACLMessage message) {
    String film = message.getUserDefinedParameter("FILM");
    String filmParameter = message.getUserDefinedParameter("FILM_PARAMETER");

    ACLMessage reply = message.createReply();
    reply.setPerformative(ACLMessage.INFORM);
    reply.addUserDefinedParameter("PARAMETER", filmParameter);
    reply.addUserDefinedParameter("VALUE", getAnswer(film, filmParameter));

    send(reply);
  }

  class MessageReceiverBehaviour extends CyclicBehaviour {
    public MessageReceiverBehaviour(Agent agent) { super(agent); }

    @Override
    public void action() {
      ACLMessage message = myAgent.receive();
      int performative = message.getPerformative();

      if (message != null && performative == ACLMessage.REQUEST) {
        reply(message);
      } else {
        block();
      }
    }
  }
}
