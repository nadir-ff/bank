# Loan Eligibility API

## Request Parameters

```
{
  "creditRating": "A",
  "income": 50000,
  "dateOfBirth": "1990-01-01",
  "existingDebt": 10000
}
```

- creditRating: A, B, C, or D
- income: integer
- dateOfBirth: ISO 8601 date string (YYYY-MM-DD)
- existingDebt: integer - optional, will be `null` if not provided

## Business Rules

1. Age must be between 18 and 70 (inclusive), otherwise loan amount is 0 (applicant is not eligible)

2. Maximum loan amount is based on credit rating and income:

| Credit Rating | Multiplier        |
|---------------|-------------------|
| A             | 5x income         |
| B             | 4x income         |
| C             | 3x income         |
| D             | 0x (not eligible) |

3. If income is under 20,000, loan is 0 regardless of rating (not eligible)

4. Loan capped at:

- Max: 500,000
- Min: 1,000 (if eligible)

5. Existing debt, if provided, will reduce the maximum loan amount. If maximum loan is less than
   existing debt, the applicant is not eligible.
