package assignment2_Andre_Godinez;



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
    public static void main(String args[]) throws EarningsException, InvalidDateException,IllegalArgumentException {
    	DecimalFormat precision2 = new DecimalFormat("0.00");
    	ArrayList<Employee> employees = new ArrayList<Employee>();
  
    	
//    	try {
//            Boss boss = new Boss("John", "Smith",1985,6,30,13,12, 800.0);
//            employees.add(boss);
//        	}catch(InvalidDateException e) {
//        		System.out.println(e.getMessage());
//        	}catch(IllegalArgumentException e) {
//        		System.out.println(e.getMessage() + "\n");
//        	}
//          
//        	try {
//        	PieceWorker pieceWorker =
//        	new PieceWorker("Bob", "Lewis",2020,8,4,10,23, -10, 200);
//    		employees.add(pieceWorker);
//        	}catch(InvalidDateException e) {
//        		System.out.println(e.getMessage());
//        	}catch(IllegalArgumentException e) {
//        		System.out.println(e.getMessage() + "\n");
//        	}
//        	
//        	try {
//    	    CommissionWorker commissionWorker =
//            new CommissionWorker(
//            "Sue", "Jones",1996,2,29,9,35, 400.0, 3.0, 150);
//            employees.add(commissionWorker);
//          	}catch(InvalidDateException e) {
//          		System.out.println(e.getMessage());
//          	}catch(IllegalArgumentException e) {
//          		System.out.println(e.getMessage() + "\n");
//          	}
//        	
//        	try {
//        	HourlyWorker hourlyWorker =
//        	new HourlyWorker("Karen", "Price",2010,13,9,14,45, 13.75, 40);
//     	    employees.add(hourlyWorker);
//         	}catch(InvalidDateException e) {
//         		System.out.println(e.getMessage());
//         	}catch(IllegalArgumentException e) {
//         		System.out.println(e.getMessage() + "\n");
//         	}
    	
    	try {
            Boss boss = new Boss("John", "Smith",2008,10,27,8,33, 800.0);
            employees.add(boss);
        	}catch(InvalidDateException e) {
        		System.out.println(e.getMessage());
        	}catch(IllegalArgumentException e) {
        		System.out.println(e.getMessage() + "\n");
        	}
          
        	try {
        	PieceWorker pieceWorker =
        	new PieceWorker("Bob", "Lewis",2003,1,18,11,14, -10, 200);
    		employees.add(pieceWorker);
        	}catch(InvalidDateException e) {
        		System.out.println(e.getMessage());
        	}catch(IllegalArgumentException e) {
        		System.out.println(e.getMessage() + "\n");
        	}
        	
        	try {
    	    CommissionWorker commissionWorker =
            new CommissionWorker(
            "Sue", "Jones",1999,2,30,16,11, 400.0, 3.0, 150);
            employees.add(commissionWorker);
          	}catch(InvalidDateException e) {
          		System.out.println(e.getMessage());
          	}catch(IllegalArgumentException e) {
          		System.out.println(e.getMessage() + "\n");
          	}
        	
        	try {
        	HourlyWorker hourlyWorker =
        	new HourlyWorker("Karen", "Price",2015,7,8,12,37, 13.75, 40);
     	    employees.add(hourlyWorker);
         	}catch(InvalidDateException e) {
         		System.out.println(e.getMessage());
         	}catch(IllegalArgumentException e) {
         		System.out.println(e.getMessage() + "\n");
         	}
        
        
        String output = "";
        
        for(Employee emp : employees) {
//          dividing by 12 to get the amount of years from months
            int year = emp.calculateMonthsSinceJoined()/12;
//          Getting the remainder of months/12
            int month = emp.calculateMonthsSinceJoined()%12;
            boolean bonusStatus = emp.bonusStatus();
            
            try {
               double weeklyWage = emp.earnings();
               double monthlyPayroll = emp.monthlyPayroll();
               output+= emp.toString()  + "\n"
                        + "Weekly wage : " + precision2.format(weeklyWage) + "\n"
                        + "ID Number : " + emp.getIDnumber() + "\n"
                        + "Join date & time :  " + emp.getDateJoined().toString("MM/dd/yyyy hh:mm") + "\n"
                        + "Join day : " + emp.getDateJoined().dayOfWeek().getAsText()+ "\n"
                        + "Member for : " + (year) + " year(s) and " + month + " months" + "\n"
                        + "Bonus Status : " + bonusStatus+ "\n"
                        + "Monthly Payroll : " + precision2.format(monthlyPayroll)+ "\n\n\n";

            
            }
            catch(EarningsException e){
                System.out.println(e.getMessage());
            }
     
        }// end loop through employees
        
        
        JOptionPane.showMessageDialog(null, output,
                "Assignment 1",
                 JOptionPane.INFORMATION_MESSAGE);
        
        

       System.exit(0);

    }
} // end class Test
