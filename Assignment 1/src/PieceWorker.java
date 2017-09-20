// PieceWorker class derived from Employee
package assignment1_Andre_Godinez;
//Student Id : 15460718
//Name : Andre Godinez

public final class PieceWorker extends Employee {

    private double wagePerPiece; // wage per piece output
    private int quantity; // output for week

    // constructor for class PieceWorker
    public PieceWorker(String first, String last,String date,
            double wage, int numberOfItems) {
        super(first, last,date); // call superclass constructor
        setWage(wage);
        setQuantity(numberOfItems);
    }

    // set PieceWorker's wage
    public void setWage(double wage) {

     //   wagePerPiece = (wage > 0 ? wage : 0);
        wagePerPiece = wage;
    }

    // set number of items output
    public void setQuantity(int numberOfItems) {
        quantity = (numberOfItems > 0 ? numberOfItems : 0);
    }

    // determine PieceWorker's earnings
    public double earnings() throws EarningsException {
        if((quantity*wagePerPiece) < 0 ){
            throw new EarningsException(
                    toString() + "\n"
                            + "Miscalculated wage:  " + (quantity*wagePerPiece) + "\n"
                            + "Earnings can't be a negative number.(less than zero)\n");
        }
        else {
            return quantity * wagePerPiece;
        }
    }

    public String toString() {
        return "Piece worker: " + super.toString();
    }
}
