// Definition of class HourlyWorker
package assignment2_Andre_Godinez;
//Student Id : 15460718
//Name : Andre Godinez

public final class HourlyWorker extends Employee {

    private double wage; // wage per hour
    private double hours; // hours worked for week

    // constructor for class HourlyWorker
    public HourlyWorker(String first, String last,int year,int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour,
            double wagePerHour, double hoursWorked) throws InvalidDateException {
    	super(first, last,year,monthOfYear,dayOfMonth,hourOfDay,minuteOfHour); // call superclass constructor
        setWage(wagePerHour);
        setHours(hoursWorked);
    }

    // Set the wage
    public void setWage(double wagePerHour) {
        wage = wagePerHour;
    }

    // Set the hours worked
    public void setHours(double hoursWorked) {
        hours = (hoursWorked >= 0 && hoursWorked < 168
                ? hoursWorked : 0);
    }

    // Get the HourlyWorker's pay
    public double earnings() throws EarningsException {
        if (wage * hours < 0) {
            throw new EarningsException(
                    toString() + "\n"
                            + "Miscalculated wage:  " + (wage * hours) + "\n"
                            + "Earnings can't be a negative number.(less than zero)\n");

        }
        else {
            return wage * hours;
        }
        }

    public String toString() {
        return "Hourly worker: " + super.toString();
    }
}
