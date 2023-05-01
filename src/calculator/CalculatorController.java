package calculator;

// Author: Mason Wichman

import java.awt.Color;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.swing.JButton;

// This class serves as the controller in the Model-View-Controller (MVC) architecture
public class CalculatorController {
    private CalculatorModel model; // Instance of CalculatorModel that represents the model in the MVC pattern
    private CalculatorView view; // Instance of CalculatorView that represents the view in the MVC pattern
    
    // Constructor for the controller that takes in a model and a view
    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        
        // Adds listeners to the view's buttons
        this.view.addNumButtonListener(new NumButtonListener(view));
        this.view.addOpButtonListener(new OpButtonListener(view, model));
        this.view.addClearButtonListener(new ClearButtonListener(view));
        this.view.addDeleteButtonListener(new DeleteButtonListener(view));
        this.view.addEqualButtonListener(new EqualButtonListener(view, model));
        this.view.addMRecallButtonListener(new MRecallButtonListener(view, model));
        this.view.addMClearButtonListener(new MClearButtonListener(view, model));
    }
    
    // Inner class that serves as a listener for number buttons in the view
    class NumButtonListener implements ActionListener {
    	private CalculatorView view;
    	
    	// Constructor that takes in a view
    	public NumButtonListener(CalculatorView view) {
    		this.view = view;
    	}
    	
    	// Implements actionPerformed method of ActionListener interface
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton selectedBtn = (JButton) e.getSource();
			appendToEquation(selectedBtn.getText()); // Calls appendToEquation method
		}
		
		// Appends the given string value to the equation shown in the view
		private void appendToEquation(String value) {
			view.getEquation().setText(view.getEquation().getText() + value);
		}
    }
    
    // Inner class that serves as a listener for operator buttons in the view
    class OpButtonListener implements ActionListener {
    	private CalculatorView view;
    	private CalculatorModel model;
    	
    	// Constructor that takes in a view and a model
    	public OpButtonListener(CalculatorView view, CalculatorModel model) {
    		this.view = view;
    		this.model = model;
    	}
    	
    	// Implements actionPerformed method of ActionListener interface
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton selectedBtn = (JButton) e.getSource();
			model.setOp(selectedBtn.getText()); // Sets the operator in the model to the text of the clicked button
			replaceOperator(selectedBtn.getText()); // Replaces the operator in the equation shown in the view with the clicked button
			
			// Resets the background color of all operator buttons
			for (JButton btn : view.getOpBtn()) {
				btn.setBackground(new JButton().getBackground());
			}
			
			// Sets the background color of the clicked operator button to black
			selectedBtn.setBackground(new Color(0, 0, 0));
		}
		
		// Replaces the existing operator in the equation shown in the view with the given replacement operator
		private void replaceOperator(String replaceWith) {
			List<String> operators = Arrays.asList("+", "-", "*", "/", "(Squared)", "(Square-Root)", "M+", "M-");
			String regex = String.join("|", operators.stream().map(Pattern::quote).collect(Collectors.toList()));
			String text = view.getEquation().getText();
			String newText = text.replaceAll(regex, replaceWith);
			
			// If the replacement operator is not already present in the equation, adds it at the end
			if (!newText.contains(replaceWith)) {
				newText += replaceWith;
			}
			
			// Sets the text of the equation text field to the updated text
			view.getEquation().setText(newText);
		}
    }
    
    // Inner class that serves as a listener for a clear button
    class ClearButtonListener implements ActionListener {
    	private CalculatorView view;
    	
    	// Constructor that takes in a view
    	public ClearButtonListener(CalculatorView view) {
    		this.view = view;
    	}
    	
    	// Implements actionPerformed method of ActionListener interface
		@Override
		public void actionPerformed(ActionEvent e) {
			// Sets equation text field to be empty
			view.getEquation().setText("");
			
			// Reset op value
			model.setOp(null);
			
			// Resets the background color of all operator buttons
			for (JButton btn : view.getOpBtn()) {
				btn.setBackground(new JButton().getBackground());
			}
		}
    }
    
    // Inner class that serves as a listener for a delete button
    class DeleteButtonListener implements ActionListener {
    	private CalculatorView view;
    	
    	// Constructor that takes in a view
    	public DeleteButtonListener(CalculatorView view) {
    		this.view = view;
    	}
    	
    	// Implements actionPerformed method of ActionListener interface
		@Override
		public void actionPerformed(ActionEvent e) {
			// Gets text of equation text field and returns early if text length is 0
			String text = view.getEquation().getText();	
			if (text.length() == 0) return;
			
			// Removes entire operator when it is at the end of the equation text
			// Otherwise removes last char of equation text
			char lastChar = text.charAt(text.length() - 1);
			if (lastChar == '+' || lastChar == '-' || lastChar == '*'
					|| lastChar == '/' || lastChar == ')') {
				List<String> operators = Arrays.asList("+", "-", "*", "/", "(Squared)", "(Square-Root)", "M+", "M-");
				String regex = String.join("|", operators.stream().map(Pattern::quote).collect(Collectors.toList()));
				text = text.replaceAll(regex, "");
				
				// Reset op value
				model.setOp(null);
				
				// Resets the background color of all operator buttons
				for (JButton btn : view.getOpBtn()) {
					btn.setBackground(new JButton().getBackground());
				}
			} else if (lastChar == '_') { // Remove neg_
				text = text.substring(0, text.length() - 4);
			} else {
				text = text.substring(0, text.length() - 1);
			}
			// Update equation text field
			view.getEquation().setText(text);
		}
    }
    
    // Inner class that serves as a listener for a equal button
    class EqualButtonListener implements ActionListener {
    	private CalculatorView view;
    	private CalculatorModel model;
    	
    	// Constructor that takes in a view and a model
    	public EqualButtonListener(CalculatorView view, CalculatorModel model) {
    		this.view = view;
    		this.model = model;
    	}
    	
    	// Implements actionPerformed method of ActionListener interface
		@Override
		public void actionPerformed(ActionEvent e) {
			// Try to perform calculations on equation text field and update output text field accordingly
			try {
				model.calculate(view.getEquation().getText());
				view.getOutput().setText(String.valueOf(model.getResult()));
				view.getEquation().setText("");
			} catch (ArithmeticException err) {
				// Update equation text field to show error 
				view.getEquation().setText(err.getMessage());
			}
			
			// Reset op value
			model.setOp(null);
			
			// Resets the background color of all operator buttons
			for (JButton btn : view.getOpBtn()) {
				btn.setBackground(new JButton().getBackground());
			}
		}
    }
    
    // Inner class that serves as a listener for a M-Recall button
    class MRecallButtonListener implements ActionListener {
    	private CalculatorView view;
    	private CalculatorModel model;
    	
    	// Constructor that takes in a view and a model
    	public MRecallButtonListener(CalculatorView view, CalculatorModel model) {
    		this.view = view;
    		this.model = model;
    	}
    	// Implements actionPerformed method of ActionListener interface
		@Override
		public void actionPerformed(ActionEvent e) {
			String appendingString = String.valueOf(model.getResult());
			// Replace any "-" in appending string with "neg_"
			appendingString = appendingString.replace("-", "neg_");
			// Appends model's result value to equation text field
			view.getEquation().setText(view.getEquation().getText() + appendingString);
		}
    }
    
    // Inner class that serves as a listener for a M-Clear button
    class MClearButtonListener implements ActionListener {
    	private CalculatorView view;
    	private CalculatorModel model;
    	
    	// Constructor that takes in a view and a model
    	public MClearButtonListener(CalculatorView view, CalculatorModel model) {
    		this.view = view;
    		this.model = model;
    	}
    	
    	// Implements actionPerformed method of ActionListener interface
		@Override
		public void actionPerformed(ActionEvent e) {
			// Resets model's result value and the output text field
			model.clear();
			view.getOutput().setText(String.valueOf(model.getResult()));
		}
    }
}


