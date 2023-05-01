package calculator;

// Author: Mason Wichman

// Define the CalculatorModel class
public class CalculatorModel {
	double result; // Holds the current result of the calculator
	String op; // Holds the current operator for the calculator
	
	// Constructor that initializes the result and op
	public CalculatorModel() {
		result = 0.0;
		op = null;
	}
	
	// Returns the current result of the calculator
	public double getResult() {
		return result;
	}
	
	// Sets the current operator for the calculator based on the input op
	// If op is "M+", sets op to "+"
	// If op is "M-", sets op to "-"
	public void setOp(String op) {
		if (op == null) {
			this.op = null;
			return;
		}
		
		if (op.equals("M+")) {
			op = "+";
		} else if (op.equals("M-")) {
			op = "-";
		}
		
		this.op = op;
	}
	
	// Calculates the result of the equation based on the current operator
	// If op is null, throw ArithmeticException
	// The equation is split into two operands using the splitString method
	// The operands are then parsed to double and the corresponding calculation is performed
	// If the second operand is not empty for the (Squared) or (Square-Root) operations, throws an ArithmeticException
	// If any parsing error occurs or if the operator is not valid, throws an ArithmeticException
	public void calculate(String equation) {
		if (op == null) {
			throw new ArithmeticException("Error");
		}
		
		String[] operands = splitString(equation, op);
		// Replace "neg_" with "-" in operands
		operands[0] = operands[0].replace("neg_", "-");
		if (operands.length > 1) {
			operands[1] = operands[1].replace("neg_", "-");
		}
		double operand1;
		double operand2;
		
		try {
			if (operands[0].equals("M")) {
				operand1 = result;
			} else {
				operand1 = Double.parseDouble(operands[0]);
			}
		
			if (op.equals("+")) {
				operand2 = Double.parseDouble(operands[1]);
				
				result = operand1 + operand2;
			} else if (op.equals("-")) {
				operand2 = Double.parseDouble(operands[1]);
				
				result = operand1 - operand2;
			} else if (op.equals("*")) {
				operand2 = Double.parseDouble(operands[1]);
				
				result = operand1 * operand2;
			} else if (op.equals("/")) {
				operand2 = Double.parseDouble(operands[1]);
				
				if (operand2 == 0) throw new ArithmeticException("Error");
				
				result = operand1 / operand2;
			} else if (op.equals("(Squared)")) {
				if (!operands[1].equals("")) throw new ArithmeticException("Error");
				
				result = Math.pow(operand1, 2);
			} else if (op.equals("(Square-Root)")) {
				if (!operands[1].equals("")) throw new ArithmeticException("Error");
				
				result = Math.sqrt(operand1);
			} else {
				throw new ArithmeticException("Error");
			}
		} catch (NullPointerException | NumberFormatException e) {
			throw new ArithmeticException("Error");
		}
	}
	
	// Resets the current result to 0.0
	public void clear() {
		result = 0.0;
	}
	
	// Splits the input string based on the delimiter and returns an array of two strings
	public String[] splitString(String input, String delimiter) {
		int index = input.indexOf(delimiter);
		if (index < 0) {
			return new String[] {input};
		} else {
			String[] output = new String[2];
			output[0] = input.substring(0, index);
			output[1] = input.substring(index + delimiter.length());
			return output;
		}
	}
}
