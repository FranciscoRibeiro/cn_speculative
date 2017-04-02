import java.util.List;
import java.lang.StringBuilder;

class Constraint {
  public static final Integer IN      = 0;
  public static final Integer NOT_IN  = 1;
  public static final Integer LEQ     = 2;
  public static final Integer GEQ     = 3;
  public static final Integer LT      = 4;
  public static final Integer GT      = 5;
  public static final Integer EQ      = 6;

  private String parameter;
  private Integer constraintType;
  private String value;

  public Constraint(String parameter, Integer constraint, String value) {
    this.parameter      = parameter;
    this.constraintType = constraint;
    this.value          = value;
  }

  public String parameterName() {
    return this.parameter;
  }

  public Integer typeOfConstraint() {
    return this.constraintType;
  }

  public String value() {
    return this.value;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(parameter);
    sb.append(" ");
    switch (constraintType) {
      case 0: sb.append("\u2208 {" + value + "}"); break;
      case 1: sb.append("\u2209 {" + value + "}"); break;
      case 2: sb.append("<= " + value); break;
      case 3: sb.append(">= " + value); break;
      case 4: sb.append("< " + value); break;
      case 5: sb.append("> " + value); break;
      case 6: sb.append("= " + value); break;
      default: break;
    }
    return sb.toString();
  }
}
