package org.yearup.cardealership;

public class SalesContract extends Contract{
    private static final double salesTax = 0.05;
    private static final double recordingFee = 100.00;
    private static final double processingFeeUnder = 295.00;
    private static final double processingFeeAbove = 495.00;
    private static final double interestRateUnder = 0.0525;
    private static final double interestRateAbove = 0.0425;
    private static final int loanTermUnder = 24;
    private static final int loanTermAbove = 48;

    private double totalPrice;
    private double monthlyPayment;
    private boolean financeOption;

    public SalesContract(String date, String name, String cxEmail, Vehicle vehicleSold, boolean financeOption, double totalPrice) {
        super(date, name, cxEmail, vehicleSold);
        this.financeOption = financeOption;
        this.totalPrice = totalPrice;
    }

    public double getSalesTaxAmount() {
        return totalPrice * salesTax;
    }
    public double getRecordingFee() {
        return recordingFee;
    }
    public double getProcessingFee() {
        //if the total price is below p.u if above p.a
        if (getTotalPrice() < 10000) {
            return processingFeeUnder;
        } else {
            return processingFeeAbove;
        }
    }
    public boolean isFinanceOption() {
        return financeOption;
    }
    public void setFinanceOption(boolean financeOption) {
        this.financeOption = financeOption;
    }

    @Override
    public double getTotalPrice() {
        double vehiclePrice = 0;
        double totalPrice = 0;
        vehiclePrice = vehicleSold.getPrice();
        //calculating
        if (vehiclePrice < 10000) {
            totalPrice = vehiclePrice + processingFeeUnder;
        } else {
            totalPrice = vehiclePrice + processingFeeAbove;
        }
        totalPrice = totalPrice + recordingFee;
        totalPrice += (vehiclePrice + salesTax);

        return totalPrice;
    }

    @Override
    public double getMonthlyPayment() {
        //math.pow returns the value of argument a raised to the power of argument b
        if (!financeOption) {
            return 0;
        }

        double loanAmount = getTotalPrice();

        if (loanAmount >= 10000){
            double monthlyInterest = interestRateAbove / 12;
            int numberOfPayments = loanTermAbove;
            monthlyPayment = (loanAmount * monthlyInterest) / (1 - Math.pow(1+ monthlyInterest, -numberOfPayments));
            return monthlyPayment;
        } else if (loanAmount < 10000){
            double monthlyInterest = interestRateUnder/ 12;
            int numberOfPayments = loanTermUnder;
            monthlyPayment = (loanAmount * monthlyInterest) / (1 - Math.pow(1+ monthlyInterest, -numberOfPayments));
        } return monthlyPayment;

    }

// payment = (P * (r/n)) / (1 - (1 + (r/n)))^-(t*n)

}
