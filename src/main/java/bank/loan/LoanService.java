package bank.loan;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;

@Service
public class LoanService {

  private final LoanRepository loanRepository;

  public LoanService(LoanRepository loanRepository) {
    this.loanRepository = loanRepository;
  }

  public int estimate(LoanRequest request, LocalDate currentDate) {
    loanRepository.save(request);

    // Rule 1 - age restriction
    int age = Period.between(request.dateOfBirth(), currentDate).getYears();
    if (age < 18 || age > 70) {
      return 0;
    }

    // Rule 3 - income restriction
    if (request.income() < 20_000) {
      return 0;
    }

    // Rule 2 - credit rating restriction and base amount calculation
    if (request.creditRating() == CreditRating.D) {
      return 0;
    }
    int maxAmount = request.income() * request.creditRating().multiplier();

    // Rule 5 - existing debt adjustment
    int amountAfterDebt = request.existingDebt().map(debt -> maxAmount - debt).orElse(maxAmount);
    if (amountAfterDebt < 0) {
      return 0;
    }

    // Rule 4 - loan capping
    return Math.max(1_000, Math.min(amountAfterDebt, 500_000));
  }

  public LoanRequest findById(Long id) {
    return loanRepository.findById(id);
  }
}
