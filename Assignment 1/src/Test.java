

import javax.swing.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

// Java extension packages

//Student Id : 15460718
//Name : Andre Godinez


public class Test {
	
	public static SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

    // test Employee hierarchy
    public static void main(String args[]) throws EarningsException {
    	DecimalFormat precision2 = new DecimalFormat("0.00");
    	ArrayList<Employee> employees = new ArrayList<Employee>();

//        NB. for all my exceptions to be called i would have to change all default setter
//            methods to calculate earnings
//            i only changed the boss setWeeklySalary method and pieceWorker method
//            so that it would allow negative values
//            I saw no reason to change the other setter methods for the other extended classes
//            to show that my user defined exceptions worked


        //sample negative salary input
        Boss boss = new Boss("John", "Smith", "12/12/2008", -800.0);
        //negative wage input
        PieceWorker pieceWorker =
                new PieceWorker("Bob", "Lewis","12/12/2008", -10, 200);

        //normal input
        CommissionWorker commissionWorker =
        		new CommissionWorker(
             "Sue", "Jones","12/12/2001", 400.0, 3.0, 150);

        //normal input
        HourlyWorker hourlyWorker =
              new HourlyWorker("Karen", "Price","12/12/2015", 13.75, 40);
        
        //adding objects to my employees arraylist
        employees.add(boss);
        employees.add(pieceWorker);
        employees.add(commissionWorker);
        employees.add(hourlyWorker);
        
        for(Employee emp : employees) {
            int year = emp.calculateMonthsSinceJoined()/12;
            int month = emp.calculateMonthsSinceJoined()%12;
            boolean bonusStatus = emp.bonusStatus();
            String output = "";
            try {
//             dividing by 12 to get the amount of years from months
               double weeklyWage = emp.earnings();
//             Getting the remainder of months/12
               double monthlyPayroll = emp.monthlyPayroll();
               output+= emp.toString()  + "\n"
                        + "Weekly wage : " + precision2.format(weeklyWage) + "\n"
                        + "ID Number : " + emp.getIDnumber() + "\n"
                        + "Join date  " + emp.getDateJoined() + "\n"
                        + "Member for " + (year) + " year(s) and " + month + " months" + "\n"
                        + "Bonus Status : " + bonusStatus+ "\n"
                        + "Monthly Payroll : " + precision2.format(monthlyPayroll)+ "\n";

                JOptionPane.showMessageDialog(null, output,
                                              "Assignment 1",
                                               JOptionPane.INFORMATION_MESSAGE);

            }
            catch(EarningsException e){
                System.out.println(e.getMessage());
            }


        }
        
        
        

       System.exit(0);

    }
} // end class Test
