//Andre Godinez
//15460718
package assignment4_Andre_Godinez;
import java.math.BigInteger;

public class Rational {
	//numerator - top number
	private int top;
	//denominator - bottom number
	private int bottom;
	
	public Rational(int a, int b) {
		if(a != b) {
		top = a;
		}else {
			System.out.println("Not a Rational number");
		}
		if(b!=0) {
			bottom = b;
			}else {
				System.out.println("Denominator can't be 0.");
			}
		}
//	a/b * c/d = a*c / b*d
	public static Rational multiply(Rational r1, Rational r2) {
		System.out.printf("(%d/%d) * (%d/%d) = ",r1.top,r1.bottom,r2.top,r2.bottom);
		return new Rational(r1.top*r2.top,r1.bottom*r2.bottom);
	}
	
//	a/b ÷ c/d = a/b * d/c = a*d / b*c
	public static Rational divide(Rational r1, Rational r2) {
		System.out.printf("(%d/%d) / (%d/%d) = ",r1.top,r1.bottom,r2.top,r2.bottom);
		return new Rational(r1.top*r2.bottom,r1.bottom*r2.top);
	}
	
	public static Rational plus(Rational r1, Rational r2) {
		System.out.printf("(%d/%d) + (%d/%d) = ",r1.top,r1.bottom,r2.top,r2.bottom);
		Rational a = new Rational(r1.top*r2.bottom,r1.bottom*r2.bottom);
		Rational b = new Rational(r2.top*r1.bottom,r2.bottom*r1.bottom);
		return new Rational((a.top + b.top),b.bottom);
	}

	public static Rational minus(Rational r1, Rational r2) {
		System.out.printf("(%d/%d) - (%d/%d) = ",r1.top,r1.bottom,r2.top,r2.bottom);
		Rational a = new Rational(r1.top*r2.bottom,r1.bottom*r2.bottom);
		Rational b = new Rational(r2.top*r1.bottom,r2.bottom*r1.bottom);
		return new Rational((a.top - b.top),b.bottom);
	}
	
	public Rational lowestForm() {
		BigInteger gcd = (BigInteger.valueOf(top)).gcd(BigInteger.valueOf(bottom));
		//already in lowest form
		if(gcd.intValue() == 1) {
//			return original rational number
			return this;
		}
		return new Rational(top/gcd.intValue(),bottom/gcd.intValue());
	}
	
	public String toString() {
		return  lowestForm().top+"/"+lowestForm().bottom+ " = "+String.format("%.2f",(double)top/bottom);
	}
	
	public static void main(String args[]) {
		Rational r = new Rational(15,40);
		Rational r1 = new Rational((int)Ball.BASEBALL.getWeight(),(int)Ball.BASKETBALL.getWeight());
		Rational r2 = new Rational((int)Ball.FOOTBALL.getWeight(),(int)Ball.GOLF.getWeight());
		Rational r3 = new Rational((int)Ball.POOL.getDiameter(),(int)Ball.SOFTBALL.getDiameter());
		Rational r4 = new Rational((int)Ball.TENNIS.getDiameter(),(int)Ball.VOLLEYBALL.getDiameter());
		
		System.out.println(r.top+"/"+r.bottom+" = "+ r.toString());
		System.out.println(plus(r1,r2).toString());
		System.out.println(minus(r2,r3).toString());
		System.out.println(multiply(r3,r4).toString());
		System.out.println(divide(r4,r1).toString());
	}
	
}
