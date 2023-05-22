package org.yearup.cardealership;

public class LeaseContract extends Contract{
    private final double leaseFinanceRate = 0.04;
    private double expectedEndValue;
    private double leaseFee;
    private double totalPrice;

    public LeaseContract(String contractDate, String cxName, String cxEmail, String vehicleSold) {
        super(contractDate, cxName, cxEmail, vehicleSold);
        this.totalPrice = totalPrice;
        this.expectedEndValue = totalPrice * 0.5;
        this.leaseFee = totalPrice * 0.07;
    }

    public double getExpectedEndValue() {
        return expectedEndValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    @Override
    public double getTotalPrice() {
        return totalPrice + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        //math.pow returns the value of argument a raised to the power of argument b
        double monthlyInterestRate = 0.04 / 12;
        double leaseAmount = getTotalPrice() - expectedEndValue;
        return (leaseAmount * monthlyInterestRate) / (1- Math.pow(1 + monthlyInterestRate, -36));
    }
}
