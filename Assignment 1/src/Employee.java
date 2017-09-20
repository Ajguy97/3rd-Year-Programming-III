package assignment1_Andre_Godinez;

import org.joda.time.LocalDate;
import org.joda.time.Months;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Abstract base class Employee.

//Student Id : 15460718
//Name : Andre Godinez

public abstract class Employee {
//	I use this to format the date
	private static SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

//	Static integer for id number
	static int IDno = 1000;

	private int IDnumber; 
    private String firstName;
    private String lastName;
//	double to store employees monthly payroll
    private double monthlyPayroll;
    
//    this determines whether the employee gets a bonus or not
    private boolean bonus = false;


//    This is the new date variable
    private static LocalDate currentDate = new LocalDate(new Date());
    private LocalDate joined;
    
    

    // constructor
    public Employee(String first, String last,String date) {
        firstName = first;
        lastName = last;
        Date datejoined;
		try {
			datejoined = format.parse(date);
			joined = new LocalDate(datejoined);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IDnumber = IDno;
		IDno++;
        
    }

    // get first name
    public String getFirstName() {
        return firstName;
    }
    // get last name
    public String getLastName() {
        return lastName;
    }
    // gets bonus status of employee
    public boolean bonusStatus(){ return bonus;};
    // get date joined
    public LocalDate getDateJoined(){
    	return joined;
    }

    public String toString() {
        return firstName + ' ' + lastName;
    }

    public abstract double earnings() throws EarningsException;

//    this gets the monthly pay roll of the employee
//    if the bonus status of this employee is true
//    then give 200 bonus to monthlypayroll
    public double monthlyPayroll() throws EarningsException{
//        multiplying by 4 because 4 weeks in a month give or take
//        monthly = earnings()*4;

//        or

//        52 weeks in a year -> annual salary = weekly * 52
//        double annualSalary = earnings()*52;
//        divide by 12 to get monthly of annual salary
//        double monthly = annualSalary/12;
        
//    	  or
//        more accurate
//    	  getting daily by dividing weekly by 7
//    	  then getting days of current month
//    	  then multiplying by daily
    	
    	int days = 0;
    	int month = currentDate.getMonthOfYear();
    	double daily = earnings()/7;
    	double monthly = 0;
    	
    	
    	switch (month) {
    	case 1:case 3:case 5:case 7:case 8:case 10:case 12:
    		days = 31;
    		break;
    	case 4:case 6:case 9:case 11: 
    		days = 30;
    		break;
    	case 2:
    		days = 28;
    		break;
    	}
    	
    	monthly = daily*days;
    	
        if(monthly < 0){
            throw new EarningsException("Monthly Payroll can't be a negative number");
        }
        else{

            if(bonus){
            monthly+=200;
        }
       monthlyPayroll = monthly;
       return monthly;
        }
    }

	public int getIDnumber() {
		return IDnumber;
	}
	
	public int calculateMonthsSinceJoined() {

	    //calculates months between start and end dates.
		int months = Months.monthsBetween(joined,currentDate).getMonths();
		if((months/12)>5){
		    bonus = true;

        }
       return months;
		
	}

}
