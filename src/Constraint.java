import java.util.List;

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
    this.parameter = parameter;
    this.constraintType = constraint;
    this.value = value;
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
}
