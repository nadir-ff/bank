package bank.loan;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanTests {

  private final LoanService loanService = new LoanService();

  @Test
  void testEstimateWorks() {
    // TODO
    int result = loanService.estimate();
    assertEquals(0, result);
  }
}
