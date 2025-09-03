package bank.loan;

import java.time.LocalDate;
import java.util.Optional;

public record LoanRequest(
    CreditRating creditRating,
    int income,
    LocalDate dateOfBirth,
    Optional<Integer> existingDebt) {}
