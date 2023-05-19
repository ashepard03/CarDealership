package org.yearup.cardealership;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    public static Scanner scanner = new Scanner(System.in);
    DealershipFileManager dfm;

    Dealership dealership;
    public void display(){
        init();
        displayMenu();
    }

   private void init(){
       dfm = new DealershipFileManager("inventory.csv");
       dealership = dfm.getDealership();
   }

    private void displayVehicles(ArrayList<Vehicle> inventory) {
        System.out.printf("%-12s %-7s %-15s %-20s %-15s %-15s %-18s %-10s\n", "VIN", "YEAR", "MAKE", "MODEL", "TYPE", "COLOR", "ODOMETER", "PRICE");
        //System.out.println("VIN          YEAR    MAKE            MODEL                TYPE            COLOR           ODOMETER            PRICE");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------\n");
        for (Vehicle v: inventory) {

            System.out.printf("%-12s %-7d %-15s %-20s %-15s %-15s %-18d %-10.2f\n",
                    v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                    v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
        }
    }

    public void displayMenu() {
        boolean screenDone = false;
        while (!screenDone) {
            String menu = """
                    
                    -------Tuner City--------
                    
                       -Search Options-
                    [1] Vehicles By Price
                    [2] Vehicles By Make/Model
                    [3] Vehicles By Year
                    [4] Vehicles By Color
                    [5] Vehicles By Mileage
                    [6} Vehicles By Type
                    [7} All Vehicles
                       -Other Options-
                    [A] Add Vehicle
                    [R} Remove Vehicle
                    [X] Exit
                    
                    """;

            System.out.print(menu);
            System.out.print("Enter a menu option: ");
            String input = scanner.nextLine();

            switch (input.toUpperCase()) {
                case "1" -> processGetByPriceRequest();
                case "2" -> processGetByMakeModelRequest();
                case "3" -> processByYearRequest();
                case "4" -> processByColorRequest();
                case "5" -> processGetByMileageRequest();
                case "6" -> processGetByVehicleTypeRequest();
                case "7" -> processGetAllVehiclesRequest();
                case "A" -> processAddVehicleRequest();
                case "R" -> processRemoveVehicleRequest();
                case "X" -> {
                    screenDone = true;
                    System.out.println("Exiting Car Dealership");
                    System.exit(0);
                }
                default -> System.out.println("Invalid input. Please try again!");
            }
        }
    }
    public void processGetByPriceRequest(){
        System.out.println("What is the minimum amount you are willing to pay?");
        String input = scanner.nextLine();
        double min = Double.parseDouble(input);
        System.out.println("What is the maximum amount you are willing to pay?");
        input = scanner.nextLine();
        double max = Double.parseDouble(input);
        System.out.println("----------------------------------- Search By Price Between " + min + " - " + max + "----------------------------------------------");
        ArrayList aList = (ArrayList) dealership.getVehiclesByPrice(min, max);
        displayVehicles(aList);
    }
    public void processGetByMakeModelRequest(){
        System.out.println("What make are you looking for?");
        String make = scanner.nextLine();
        System.out.println("What model are you looking for?");
        String model = scanner.nextLine();
        System.out.println("----------------------------------- Search By " + make + model + "----------------------------------------------");
        ArrayList aList = (ArrayList) dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(aList);
    }
    public void processByYearRequest(){
        System.out.println("What is the oldest year of vehicle you are looking for?");
        String input = scanner.nextLine();
        int min = Integer.parseInt(input);
        System.out.println("What is the newest year of vehicle you are looking for?");
        input = scanner.nextLine();
        int max = Integer.parseInt(input);
        System.out.println("----------------------------------- Search By Year Between " + min + " - " + max + "----------------------------------------------");
        ArrayList aList = (ArrayList) dealership.getVehiclesByYear(min, max);
        displayVehicles(aList);
    }
    public void processByColorRequest(){
        System.out.println("What color vehicle are you looking for?");
        String color = scanner.nextLine();
        System.out.println("------------------------------------- Search By Color: " + color + "-----------------------------------------------------------");
        ArrayList aList = (ArrayList) dealership.getVehiclesByColor(color);
        displayVehicles(aList);
    }
    public void processGetByMileageRequest(){
        System.out.println("What is the minimum amount of miles you want the car to have?");
        String input = scanner.nextLine();
        double min = Double.parseDouble(input);
        System.out.println("What is the maximum amount of miles you want the car to have?");
        input = scanner.nextLine();
        double max = Double.parseDouble(input);
        System.out.println("---------------------------------- Search By Mileage Between " + min + " - " + max + "----------------------------------------------");
        ArrayList aList = (ArrayList) dealership.getVehiclesByMileage(min, max);
        displayVehicles(aList);
    }
    public void processGetByVehicleTypeRequest(){
        System.out.println("What type of vehicle are you looking for?");
        String type = scanner.nextLine();
        System.out.println("------------------------------------- Search By Vehicle Type: " + type + "-----------------------------------------------------------");
        ArrayList aList = (ArrayList) dealership.getVehiclesByType(type);
        displayVehicles(aList);
    }
    public void processGetAllVehiclesRequest(){
        ArrayList aList = (ArrayList) dealership.getAllVehicles();
        displayVehicles(aList);
    }
    public void processAddVehicleRequest(){
        System.out.print("Enter the vehicle VIN:  ");
        String vin = scanner.nextLine();
        System.out.print("Enter the vehicle year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the vehicle make: ");
        String make = scanner.nextLine();
        System.out.print("Enter the vehicle model: ");
        String model = scanner.nextLine();
        System.out.print("Enter the vehicle type: ");
        String vehicleType = scanner.nextLine();
        System.out.print("Enter the vehicle color: ");
        String color = scanner.nextLine();
        System.out.print("Enter the vehicle mileage: ");
        int odometer = scanner.nextInt();
        System.out.print("Enter the vehicle price: ");
        double price = scanner.nextDouble();
        System.out.println("\n" + make +" "+ model + " was successfully added to Tuner City's inventory " );
        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicles(vehicle);

        dfm.saveDealership(dealership);
    }
    public void processRemoveVehicleRequest(){
        System.out.print("Please enter the VIN of the vehicle you would like to remove: ");
        String vin = scanner.next();
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                System.out.println("The " + v.getMake() + v.getModel() + "has been removed");
                dealership.removeVehicles(v);
            }
        }
        dfm.saveDealership(dealership);
    }

}
