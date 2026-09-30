import java.math.BigDecimal;

public class Interest {
    
    // Attributes - Using BigDecimal for calculations
    private BigDecimal principal;
    private BigDecimal rate;
    private BigDecimal time;

    // Constructor - Input validation is done here
    public Interest(BigDecimal principal, BigDecimal rate, BigDecimal time) {
        
        // 1. Check for negative values
        if (principal.compareTo(BigDecimal.ZERO) < 0 || 
            rate.compareTo(BigDecimal.ZERO) < 0 || 
            time.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Error: Principal, Rate, or Time cannot be negative!");
        }

        // 2. Check for integer values (Principal and Rate should not be integers)
        if (isInteger(principal)) {
            throw new IllegalArgumentException("Error: Principal cannot be an integer. Please provide a decimal value (e.g., 1000.0).");
        }
        if (isInteger(rate)) {
            throw new IllegalArgumentException("Error: Rate cannot be an integer. Please provide a decimal value (e.g., 5.0).");
        }

        this.principal = principal;
        this.rate = rate;
        this.time = time;
    }

    // Helper method: Checks if the BigDecimal value is an integer
    private boolean isInteger(BigDecimal bd) {
        // Eğer ondalık kısmı yoksa (scale <= 0 ise) bu bir tam sayıdır.
        return bd.scale() <= 0;
    }

    // Getters for calculations
    public BigDecimal getPrincipal() { return principal; }
    public BigDecimal getRate() { return rate; }
    public BigDecimal getTime() { return time; }
    
    // --- SIMPLE INTEREST METHODS ---

    // Simple Interest with BigDecimal
    public BigDecimal calculateSimpleInterest() {
        BigDecimal divisor = new BigDecimal("100");
        return principal.multiply(rate).multiply(time).divide(divisor, 2, java.math.RoundingMode.HALF_UP);
    }

    // Overloaded Simple Interest method using double parameters
    public double calculateSimpleInterest(double p, double r, double t) {
        return (p * r * t) / 100.0;
    }

    // --- COMPOUND INTEREST METHODS ---

    // Compound Interest with BigDecimal
    public BigDecimal calculateCompoundInterest() {
        BigDecimal divisor = new BigDecimal("100");
        BigDecimal ratePerPeriod = rate.divide(divisor, 4, java.math.RoundingMode.HALF_UP);
        BigDecimal onePlusRate = BigDecimal.ONE.add(ratePerPeriod);
        
        int years = time.intValue();
        BigDecimal amount = principal.multiply(onePlusRate.pow(years));
        
        return amount.subtract(principal).setScale(2, java.math.RoundingMode.HALF_UP);
    }

    // Overloaded Compound Interest method using double parameters
    public double calculateCompoundInterest(double p, double r, double t) {
        double amount = p * Math.pow(1.0 + (r / 100.0), t);
        return amount - p;
    }
}
