package org.yearup.cardealership;

public class ContractDataManager {

    public void saveContract(Contract contract) {

        if (contract instanceof SalesContract) {

        } else if (contract instanceof LeaseContract) {

        }

    }

    // saveContract : Contract contract
        // create an iff statement for if the contract is of type sale or lease using the instanceof
        //lookup how to use instanceof: checks if the instance compares to a type
        //in this case if contract instanceof SalesContract or LeaseContract
        //maybe create an if for the chance there is not any contract data to actually add to the file

    //generateSalesContract method
    private String createSalesContractData(SalesContract salesContract) {
        // create a string builder
        //append SALE|
        // append date, cx name, cx email, vehicle sold, total price, monthly payment, sales tax, recording, processing, finance option

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SALE|")
                .append(salesContract.getContractDate()).append("|")
                .append(salesContract.getCxName()).append("|")
                .append(salesContract.getCxEmail()).append("|")
                .append(salesContract.getVehicleSold()).append("|")
                .append(salesContract.getTotalPrice()).append("|")
                .append(salesContract.getMonthlyPayment()).append("|")
                .append(salesContract.getSalesTaxAmount()).append("|")
                .append(salesContract.isFinanceOption()).append("|");

    }

    //generateLeaseContract method
    private String createLeaseContractData(LeaseContract leaseContract) {
        //create a string builder
        // append LEASE|
        //append date, cx name, cx email, vehicle sold, total price, monthly payment, expected end value, lease fee

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SALE|")
                .append(leaseContract.getContractDate()).append("|")
                .append(leaseContract.getCxName()).append("|")
                .append(leaseContract.getCxEmail()).append("|")
                .append(leaseContract.getVehicleSold()).append("|")
                .append(leaseContract.getTotalPrice()).append("|")
                .append(leaseContract.getMonthlyPayment()).append("|")
                .append(leaseContract.getExpectedEndValue()).append("|")
                .append(leaseContract.getLeaseFee()).append("|");
    }

}
