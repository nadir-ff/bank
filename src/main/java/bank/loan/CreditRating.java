package bank.loan;

public enum CreditRating {
  A(5),
  B(4),
  C(3),
  D(0);

  private final int multiplier;

  CreditRating(int multiplier) {
    this.multiplier = multiplier;
  }

  public int multiplier() {
    return multiplier;
  }
}
