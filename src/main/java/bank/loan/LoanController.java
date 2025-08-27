package bank.loan;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

  private final LoanService loanService;

  public LoanController(LoanService loanService) {
    this.loanService = loanService;
  }

  public record LoanResponse(int amount) {}

  @PostMapping("/loans")
  public LoanResponse estimate(/* TODO handle request body with data submitted in form */ ) {
    int amount = loanService.estimate();
    return new LoanResponse(amount);
  }
}
