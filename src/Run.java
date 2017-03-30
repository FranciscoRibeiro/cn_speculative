import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;

public class Run{
  public static void main(String[] args){
    Query q1 = new Query("consult", new Term[] {new Atom("model.pl")});
    q1.hasSolution();
    Query q3 = new Query("listing",new Term[] {new Atom("recommended")});
		q3.open();
		System.out.println("Recommended listing:");
		System.out.println(q3.getSolution().toString());
		q3.close();
  }

}
