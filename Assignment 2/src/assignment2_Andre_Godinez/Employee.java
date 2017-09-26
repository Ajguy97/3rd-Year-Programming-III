package assignment2_Andre_Godinez;


import org.joda.time.IllegalFieldValueException;
import org.joda.time.LocalDateTime;
import org.joda.time.Months;

import java.time.DateTimeException;
import java.time.Month;
import java.util.Date;

// Abstract base class Employee.

//Student Id : 15460718
//Name : Andre Godinez

public abstract class Employee {
//	I use this to format the date
	//private static SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	
	

	
	
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
    private static LocalDateTime currentDate = new LocalDateTime(new Date());
    private LocalDateTime joined;
    
    

    // constructor
    public Employee(String first, String last,int year,int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour) throws InvalidDateException,IllegalFieldValueException{
        firstName = first;
        lastName = last;
        IDnumber = IDno;
		IDno++;
		
		try {
		joined = new LocalDateTime(year,monthOfYear,dayOfMonth,hourOfDay,minuteOfHour);
		}catch(IllegalFieldValueException e) {
//			Doing it this way it catches the Joda time exception and i can print my own message
//			however the date object still doesn't get instantiated so i have to use the parameters that were passed in
//			to show the invalid date
			
			if(monthOfYear<1||monthOfYear>12) {
			throw new InvalidDateException(toString()+"'s join date "+monthOfYear+"/"+dayOfMonth+"/" + year+ " "+ hourOfDay
														+ ":"+ minuteOfHour+" is invalid." + "\n"
														+ "Reason : month is out of range ");
			}
			
			if(dayOfMonth<1||dayOfMonth>Month.of(monthOfYear).maxLength()) {
				throw new InvalidDateException(toString()+"'s join date "+monthOfYear+"/"+dayOfMonth+"/" + year+ " "+ hourOfDay
															+ ":"+ minuteOfHour+" is invalid." + "\n"
															+ "Reason : day is out of range ");
				}
				
				
			
			}
			
		
		
//		works
		//if the join date is in the future
		if(joined.getYear()>currentDate.getYear()) {
			throw new InvalidDateException(toString()+"'s join date " + joined.toString("MM/dd/yyyy HH:mm") + " is invalid" + "\n"
											+ "Reason : join year is after " + currentDate.getYear() + "\n");
		}
//		works
//		if year is before 1990 
		if(joined.getYear() < 1990) {
			throw new InvalidDateException(toString()+"'s join date " + joined.toString("MM/dd/yyyy HH:mm") + " is invalid" + "\n"
					+ "Reason : join year is before 1990.\n");
		}
		
//		works
//		if hour is not between 9:00 and 18:00 
		if(joined.getHourOfDay() < 9 ||joined.getHourOfDay()>18 ) {
			throw new InvalidDateException(toString()+"'s join date " + joined.toString("MM/dd/yyyy HH:mm") + " is invalid" + "\n"
					+ "Reason : hour is not between 9:00 and 18:00 \n");
		}
//		works
//		if day falls on the weekend 
		if(joined.dayOfWeek().getAsText() == "Saturday" || joined.dayOfWeek().getAsText() == "Sunday") {
			throw new InvalidDateException(toString()+"'s join date " + joined.toString("MM/dd/yyyy HH:mm") + " is invalid" + "\n"
					+ "Reason : join day falls on the weekend ("+ joined.dayOfWeek().getAsText() +").\n");
//		These wont actually work because the date object never gets instantiated
		
////		if month is out of range 
//		if(joined.getMonthOfYear()<1||joined.getMonthOfYear()>12) {
//			throw new InvalidDateException(toString()+"'s join date " + joined.toString("MM/dd/yyyy HH:mm") + " is invalid" + "\n"
//					+ "Reason : month is out of range.\n");
//		}

////		if day is out of range according to the month 
//		if(joined.getDayOfMonth() < 1 || joined.getDayOfMonth() > joined.dayOfMonth().getMaximumValue()) {
//			throw new InvalidDateException(toString()+"'s join date " + joined.toString("MM/dd/yyyy HH:mm") + " is invalid" + "\n"
//					+ "Reason : day is out of range.\n");
//		}
		
		

		}
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
    public LocalDateTime getDateJoined(){
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
    	int days = currentDate.monthOfYear().getMaximumValue();
    	int month = currentDate.getMonthOfYear();
    	double daily = earnings()/7;
    	
    	
    	double monthly = daily*days;
    	
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
