package bank.loan;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoanTests {

  private final LoanRepository loanRepository = Mockito.mock(LoanRepository.class);
  private final LoanService loanService = new LoanService(loanRepository);
  private final LocalDate date = LocalDate.now();

  @Test
  void zeroReturnsWhenAgeIsUnder18() {
    LoanRequest req =
        new LoanRequest(CreditRating.A, 50000, date.minusYears(17), Optional.of(10000));
    assertEquals(0, loanService.estimate(req, date));
  }

  @Test
  void zeroReturnsWhenAgeIsOver70() {
    LoanRequest req =
        new LoanRequest(CreditRating.A, 50000, date.minusYears(71), Optional.of(10000));
    assertEquals(0, loanService.estimate(req, date));
  }

  @Test
  void loanIsZeroIfIncomeIsUnder20000() {
    LoanRequest req = new LoanRequest(CreditRating.A, 19999, date.minusYears(30), Optional.empty());
    assertEquals(0, loanService.estimate(req, date));
  }

  @Test
  void loanIsZeroIfCreditRatingIsD() {
    LoanRequest req = new LoanRequest(CreditRating.D, 50000, date.minusYears(30), Optional.empty());
    assertEquals(0, loanService.estimate(req, date));
  }

  @Test
  void loanIs5xIncomeForRatingA() {
    LoanRequest req = new LoanRequest(CreditRating.A, 40000, date.minusYears(30), Optional.empty());
    assertEquals(200000, loanService.estimate(req, date));
  }

  @Test
  void loanIs4xIncomeForRatingB() {
    LoanRequest req = new LoanRequest(CreditRating.B, 40000, date.minusYears(30), Optional.empty());
    assertEquals(160000, loanService.estimate(req, date));
  }

  @Test
  void loanIs3xIncomeForRatingC() {
    LoanRequest req = new LoanRequest(CreditRating.C, 40000, date.minusYears(30), Optional.empty());
    assertEquals(120000, loanService.estimate(req, date));
  }

  @Test
  void loanIsCappedAt500000() {
    LoanRequest req =
        new LoanRequest(CreditRating.A, 200000, date.minusYears(30), Optional.empty());
    assertEquals(500000, loanService.estimate(req, date));
  }

  @Test
  void loanIsCappedAt1000IfEligible() {
    LoanRequest req =
        new LoanRequest(CreditRating.C, 20000, date.minusYears(30), Optional.of(59999));
    assertEquals(1000, loanService.estimate(req, date));
  }

  @Test
  void loanIsReducedByExistingDebt() {
    LoanRequest req =
        new LoanRequest(CreditRating.A, 50000, date.minusYears(30), Optional.of(10000));
    assertEquals(240000, loanService.estimate(req, date));
  }

  @Test
  void loanIsZeroIfExistingDebtExceedsCalculatedLoan() {
    LoanRequest req =
        new LoanRequest(CreditRating.B, 30000, date.minusYears(30), Optional.of(200000));
    assertEquals(0, loanService.estimate(req, date));
  }
}
