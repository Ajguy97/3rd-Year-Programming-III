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
        double annualSalary = earnings()*52;
//        divide by 12 to get monthly of annual salary
        double monthly = annualSalary/12;
        if(monthly < 0){
            throw new EarningsException("Monthly Payroll can't be a negative number");
        }
        else{

            if(bonus){
            monthly+=200;
        }
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
