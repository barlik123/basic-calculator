
// Equation class that deals with defined equation's Arithmetics.
public class Equation {
	
	private double answer;
	
	public Equation() {
		this.answer = 0;
	}
	
	public void setValue(double value) {
		answer = value;
	}
	
	public void addition(double value) {
		answer += value;
	}
	
	public void subtraction(double value) {
		answer -= value;
	}
	
	public void multiplication(double value) {
		answer *= value;
	}
	
	//Division function. throws exception when division by zero.
	public void division(double value) throws ArithmeticException {
		if (value != 0) {
			answer /= value;
		}
		else {
			throw new ArithmeticException("Error: Cannot divide by zero");
	    }
	}
	
	//Function that returns a string of the equations's answer.
	// returns Int if an Int and Double if a fraction.
	public String getAnswer() {
		if (answer % 1 == 0) { // Checks if there's no fractional part
	        return Integer.toString((int) answer);
	    } else {
	        return Double.toString(answer);
	    }
	}
	
	public void clearEquation() {
		answer = 0;
	}

}
