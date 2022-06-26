package trainseatbooking;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class W1727389 {

    public static final int SEATING_CAPACITY = 8;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        String[] train = new String[SEATING_CAPACITY];

        for (int i = 0; i < train.length; i++) {
            train[i] = "e";
        }
        
        System.out.println("+------------------------------------+");
        System.out.println("|           Welcome to our           |");
        System.out.println("|     Train Booking Application      |");
        System.out.println("+------------------------------------+");
        
        
        boolean exit = false;
        while (!exit) {
            System.out.println("Press A: Add Customer To A Seat");
            System.out.println("Press V: View All The Seats");
            System.out.println("Press Q: Quit The Program");
            System.out.println("Press E: Display Empty seats");
            System.out.println("Press D: Delete customer from seat");
            System.out.println("Press F: Find the seat for a given customer name");
            System.out.println("Press S: Store program data in to file");
            System.out.println("Press L: Load program data from file");
            System.out.println("Press O: View seats ordered alphabetically by name");
            System.out.println("");
            String choice = in.next().toUpperCase();
            System.out.println("");

            switch (choice) {
                case "A":
                    AddCustomer(train);
                    ViewCustomer(train);
                    break;
                case "V":
                    ViewCustomer(train);
                    break;
                case "Q":
                    exit = true;
                    break;
                case "E":
                    EmptySeats(train);
                    break;
                case "D":
                    DeleteCustomer(train);
                    break;
                case "F":
                    FindCustomerSeat(train);
                    break;
                case "S":
                    StoreFile(train);
                    break;
                case "L":
                    loadFile(train);
                    break;
                case "O":
                    orderSeats(train);
                    break;
                default: System.out.println("");
                    System.out.println("Invalid character entered."
                            + " Please press one of the options below !");
                    System.out.println("");
            }

        }
    }

    private static void ViewCustomer(String[] train) {
        for (int i = 0; i < train.length; i++) {                            //It loop from 0 to the lenght of train arrey. 
            System.out.println("seat " + (i) + " = " + train[i]);
        }
        System.out.println("");
    }

    private static void AddCustomer(String[] train) {
        Scanner input = new Scanner(System.in);                             // input scanner it scans it
        System.out.println("Enter customer name to add");
        String name = input.next().toUpperCase();                           //stores the stering that was entered as name

        System.out.println("Enter seat number");
        int seat = input.nextInt();                                         //stores the int that was intered as seat
        if (seat > 8 || seat < 0) {
            System.out.println("Please enter the seat number from options below");
        } else {
            train[seat] = name;                                             //customer added
        }

    }

    private static void EmptySeats(String[] train) {
        for (int i = 0; i < train.length; i++) {                           //loops  
            if (train[i].equals("e")) {
                System.out.println("seat " + (i) + " = " + train[i]);
            }

        }
        System.out.println("");
    }

    private static void DeleteCustomer(String[] train) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter seat number");
        int seat = input.nextInt();                                        //stores seat number into seat
        train[seat] = "e";
        System.out.println(" You have deleted a customer at index " + seat + ". Press V to check if it's deleted");
        System.out.println("");
    }

    private static void FindCustomerSeat(String[] train) {
        Scanner input = new Scanner(System.in);
        System.out.println("Find customer name");
        String name = input.next().toUpperCase();                                // stores the the customer name and turns into upper case.
        for (int iterator = 0; iterator < SEATING_CAPACITY; iterator++) {       // Retrieves information one by one
            if (train[iterator].equals(name)) {
                System.out.println(" The seat for customer " + name + " is " + iterator);
                System.out.println("");
                return;
            }
        }
        System.out.println("There is no customer with that name!");
        System.out.println("");

    }

    private static void StoreFile(String[] train) {

        try {                                                                        //define a block of code to be tested for errors while it is being executed
            FileWriter filewriter = new FileWriter("train.txt");
            try ( BufferedWriter bufferedwriter = new BufferedWriter(filewriter)) {   //writes data into file
                for (int i = 0; i < SEATING_CAPACITY; i++) {
                    bufferedwriter.write(train[i] + "\n");
                }
                System.out.println("Successfully wrote the file");
                System.out.println("");
            }
        } catch (IOException ex) {
            Logger.getLogger(W1727389.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void loadFile(String[] train) throws FileNotFoundException {      //throws is a keyword that used to declare an exception
        File f = new File("train.txt");

        try ( Scanner s1 = new Scanner(f)) {
            for (int i = 0; i < train.length; i++) {
                train[i] = s1.nextLine().toUpperCase();
            }
        }
        System.out.println("Load successful!");
        System.out.println("");

    }

    private static void orderSeats(String[] train) {
        for (int i = 0; i < train.length; i++) {                     
            for (int j = 1; j < train.length - i; j++) {               //To compare j to j-1
                int compare = (train[j - 1].compareTo(train[j]));     // To see if first string is bigger than the 1st.
                if (compare >= 1) {                                    
                    String temp = train[j - 1];                       //stroring the bigger string
                    train[j - 1] = train[j];                          //j is now in j-1
                    train[j] = temp;                                
                }

            }

        }
        System.out.println("Names alphabetically sorted!");
        System.out.println("");
    }
}
