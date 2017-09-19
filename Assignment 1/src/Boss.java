// Boss class derived from Employee.

//Student Id : 15460718
//Name : Andre Godinez

public final class Boss extends Employee {

    private double weeklySalary;

    // constructor for class Boss
    public Boss(String first, String last,String date, double salary) {
        super(first, last,date); // call superclass constructor
        setWeeklySalary(salary);
    }

//     set Boss's salary
//    public void setWeeklySalary(double salary) {
//        weeklySalary = (salary > 0 ? salary : 0);
//    }

//    Need to create new set setWeeklySalary that lets salary be a negative number
//    else exception wont catch
//
    public void setWeeklySalary(double salary){
        weeklySalary = salary;
    }

    // get Boss's pay
    public double earnings() throws EarningsException{
        if(weeklySalary < 0 ){
            throw new EarningsException(
                    toString() + "\n"
                            + "Miscalculated wage:  " + weeklySalary + "\n"
                            +  "Earnings can't be a negative number.(less than zero)\n");
        }
        else{
        return weeklySalary;
        }
    }

    // get String representation of Boss's name
    public String toString() {
        return "Boss: " + super.toString();
    }
} // end class Boss
