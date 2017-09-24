package assignment2_Andre_Godinez;

// CommissionWorker class derived from Employee

//Student Id : 15460718
//Name : Andre Godinez

public final class CommissionWorker extends Employee {

    private double salary; // base salary per week
    private double commission; // amount per item sold
    private int quantity; // total items sold for week

    // constructor for class CommissionWorker
    public CommissionWorker(String first, String last,int year,int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour,
            double salary, double commission, int quantity) throws InvalidDateException {
    	super(first, last,year,monthOfYear,dayOfMonth,hourOfDay,minuteOfHour); // call superclass constructor
        setSalary(salary);
        setCommission(commission);
        setQuantity(quantity);
    }

    // set CommissionWorker's weekly base salary
    public void setSalary(double weeklySalary) {
        salary = weeklySalary;
    }

    // set CommissionWorker's commission
    public void setCommission(double itemCommission) {
        commission = itemCommission;
    }

    // set CommissionWorker's quantity sold
    public void setQuantity(int totalSold) {
        quantity = totalSold;
    }

    // determine CommissionWorker's earnings
    public double earnings() throws EarningsException{
        if( (salary + commission * quantity ) < 0 ){
            throw new EarningsException(
                    toString() + "\n"
                            + "Miscalculated wage:  " + (salary+commission*quantity) + "\n"
                            +  "Earnings can't be a negative number.(less than zero)\n");
        }
        else {
            return salary + commission * quantity;
        }
    }

    // get String representation of CommissionWorker's name
    public String toString() {
        return "Commission worker: " + super.toString();
    }
} // end class CommissionWorker
