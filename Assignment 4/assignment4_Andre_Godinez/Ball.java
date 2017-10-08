package assignment4_Andre_Godinez;


public enum Ball {
	BASEBALL(149, 73),
    BASKETBALL(624, 239),
    FOOTBALL(450 , 216),
    GOLF(46, 43),
    POOL(156, 60),
    SOFTBALL(180, 97),
    TENNIS(59, 64),
    VOLLEYBALL(260, 218);

    private final double weight;   // in g
    private final double diameter; // in mm
    public static final double PI = 3.14;
    
    Ball(double weight , double diameter){
    	
        this.weight = weight;
        this.diameter = diameter;
    }
    
    public double getWeight() { return weight; }
    public double getDiameter() { return diameter; }

//    Circumference	O =	pi × d = 2 × pi × r	
//    Surface area	P =	pi × d² = 4 × pi × r²	
//    Enclosed volume	V =	1/6 pi × d³ = 4/3 pi × r³

    double getCircumference() {
      return PI*diameter;
    }
    double getVolume() {
        return  (PI/6)*(Math.pow(diameter,3));
    }


public static void main(String[] args) {
    
    for (Ball b : Ball.values())
       System.out.printf("%s \t %.2f g %.2f mm\n",
                         b, b.weight, b.diameter);
    
    System.out.printf("\nThe circumferennce of a golf = %.2f mm\n" ,Ball.GOLF.getCircumference());
    System.out.printf("\nThe volume of a baseball = %.2f mm^3\n" ,Ball.BASEBALL.getVolume());
    
    
	}//end main
}//end ball enum class