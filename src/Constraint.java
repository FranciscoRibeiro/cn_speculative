import java.util.List;

class Constraint {
  public static final Integer IN      = 0;
  public static final Integer NOT_IN  = 1;
  public static final Integer LEQ     = 2;
  public static final Integer GEQ     = 3;
  public static final Integer LT      = 4;
  public static final Integer GT      = 5;

  private String parameter;
  private Integer constraintType;
  private List<String> values;

  public Constraint(String parameter, Integer constraint, List<String> values) {
    this.parameter = parameter;
    this.constraintType = constraint;
    this.values = values;
  }

  public String parameterName() {
    return this.parameter;
  }

  public Integer typeOfConstraint() {
    return this.constraintType;
  }

  public List<String> values() {
    return this.values;
  }
}
