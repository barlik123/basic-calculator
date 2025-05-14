import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class CalculatorController {

	@FXML
	private Label AnswerDisplay; //Calculator's answer display

	@FXML
	private GridPane buttonsGrid; //Stores Buttons Grid
	
	final private String ButtonFont = "-fx-font-size: 24;"; //defined Buttons font size

	final private int numsDim = 3; //Dimension of the number Buttons (main) rows and columns
	
	private String numsRange = String.format("[0-%d]", numsDim*numsDim); //Numbers range

	private Equation eq = new Equation(); //Declares and initializes equations object
	
	//An equation's component is in a "leftOperand Action rightOperand" format
	private String leftOperand = "";
	private String action = "";
	private String rightOperand = "";
	
	//Calculator's actions strings
	final private String divStr = "/";
	final private String mulStr = "*";
	final private String subStr = "-";
	final private String addStr = "+";
	final private String dotStr = ".";
	final private String signStr = "+/-";
	
	//Function that initializes the Calculator's display,
	//specifically the "Value" Buttons.
	public void initialize() {
		for (int i = numsDim * numsDim; i >= 0 ; i--) {
			// Places the numbered buttons
			createButton(i + "", ((numsDim*numsDim)-i) / numsDim, ((numsDim*numsDim)-i) % numsDim);
		}
		// Places the Action buttons
		createButton(divStr, 0, 3);
		createButton(mulStr, 1, 3);
		createButton(subStr, 2, 3);
		createButton(addStr, 3, 3);
		createButton(dotStr, 3, 1);
		createButton(signStr, 3, 2);
	}
	
	//Function that creates a Button.
	//The functions receives a value for the Button's display
	// and row and column positions.
	private void createButton(String value, int row, int col) {
		Button btn = new Button(String.valueOf(value));
		btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // Stretch to fill cell
		btn.setStyle(ButtonFont);

		// Allow the button to grow
		GridPane.setHgrow(btn, Priority.ALWAYS);
		GridPane.setVgrow(btn, Priority.ALWAYS);


		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleButton(event);
			}
		});

		buttonsGrid.add(btn, col, row);
	}
	
	//Function that handles the "Value" Buttons logic, 
	//appends Button values according to the calculator's logic.
	private void handleButton(ActionEvent event) {
	    Button btn = (Button) event.getSource();
	    String btnValue = btn.getText();
	    
	    if (leftOperand.isEmpty()) { 
	    	//If left Operand is empty allow only left Operands number appending
	    	if (btnValue.matches(numsRange)) { 
		    	appendValue(btnValue);
		    } 
	    }
	    else if (btnValue.matches(numsRange)) { // Appends a number
	    	appendValue(btnValue);
	    } 
	    else if (btnValue.equals(signStr)) { // changes operand sign
	    	changeOperandSign();
	    }
	    else if (btnValue.equals(dotStr)) { //if a dot
	    	appendDot();
	    }
	    else if (!btnValue.matches(numsRange)) { // if an Action
	    	action = btnValue;
	    	AnswerDisplay.setText(action);
        }
	}
	
	//Helper function that appends a value to the appropriate operand
	//If an action is performed, appends to the right operand,
	//If no action, appends to the left operand.
	private void appendValue(String btnValue) {
		if (action.isEmpty()) {
    		leftOperand += btnValue;
	        AnswerDisplay.setText(leftOperand);
    	}
    	else {
        	rightOperand += btnValue;
        	AnswerDisplay.setText(rightOperand);
        }
	}
	
	//Helper function that adds a dot to a operand if possible
	private void appendDot() {
		if (action.isEmpty() && !leftOperand.contains(dotStr)) {
    		leftOperand += dotStr;
	        AnswerDisplay.setText(leftOperand);
		}
		else if(!action.isEmpty() && !rightOperand.contains(dotStr)) {
	        	rightOperand += dotStr;
	        	AnswerDisplay.setText(rightOperand);
	    }
	}
	
	//Helper function that changes the sign of a operand
	private void changeOperandSign() {
	    if (action.isEmpty() && !leftOperand.isEmpty()) {
	        leftOperand = changeSign(leftOperand);
	        AnswerDisplay.setText(leftOperand);
	    } else if (!action.isEmpty() && !rightOperand.isEmpty()) {
	        rightOperand = changeSign(rightOperand);
	        AnswerDisplay.setText(rightOperand);
	    }
	}

	// function that changes sign value of a given string
	private String changeSign(String numStr) {
	    if (numStr.startsWith("-")) {
	        return numStr.substring(1); // Removes '-' if already negative
	    } else {
	        return "-" + numStr; // Add '-' if positive
	    }
	}
	
	
	
	
	// When clicks on the clear button, clears the calculations
	@FXML
	void onClearAnswer(ActionEvent event) {
		leftOperand = ""; 
		AnswerDisplay.setText(leftOperand);
		clearFields();
		eq.clearEquation();
	}
	
	// When clicks on the "equals button", display the result of the
	// current equation's segment
	@FXML
	void onEquals(ActionEvent event) {
		double DLeftOperand = Double.parseDouble(leftOperand);
		eq.setValue(DLeftOperand);
		try {
			if (!action.isEmpty() && !rightOperand.isEmpty()) {
				double DRightOperand = Double.parseDouble(rightOperand);
				switch (action) {
					case divStr:
						eq.division(DRightOperand);
						break;
					case mulStr:
						eq.multiplication(DRightOperand);
						break;
					case subStr:
						eq.subtraction(DRightOperand);
						break;
					case addStr:
						eq.addition(DRightOperand);
						break;
				}
				leftOperand = eq.getAnswer();
				AnswerDisplay.setText(leftOperand);
				clearFields();
			}
		}
		catch (ArithmeticException e) {
	    	AnswerDisplay.setText(e.getMessage());
	    	leftOperand = "";
	    	clearFields();
	    }
	}
	
	//Helper function that resets the equation's right side
	private void clearFields() {
		rightOperand = ""; // Reset right Operand
		action = ""; // Reset Action
	}
}