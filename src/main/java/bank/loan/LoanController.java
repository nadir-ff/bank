package bank.loan;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class LoanController {

  private final LoanService loanService;

  public LoanController(LoanService loanService) {
    this.loanService = loanService;
  }

  public record LoanResponse(int amount) {}

  @PostMapping("/loans")
  public LoanResponse estimate(@RequestBody LoanRequest loanRequest) {
    int amount = loanService.estimate(loanRequest, LocalDate.now());
    return new LoanResponse(amount);
  }
}
