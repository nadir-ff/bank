package bank.loan;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LoanRepository {
  // Mock table in database
  private final List<LoanRequest> requestTable = new ArrayList<>();

  public void save(LoanRequest request) {
    // TODO - save the request
  }

  public LoanRequest findById(Long id) {
    // TODO - find the request by id
    return null;
  }
}
