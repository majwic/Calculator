package calculator;

// Auhtor: Mason Wichman

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Define the CalculatorView class that extends the JFrame class
public class CalculatorView extends JFrame {
	
	// Declare instance variables to represent buttons and text fields
	private JButton btnAdd, btnSub, btnDiv, btnMul,
		btnClr, btnDel, btnEql, btnPer, btnMAdd, btnMSub,
		btnSqrd, btnSqrR, btnMRec, btnMClr, btnNeg;
	private JButton numBtn[];
	JTextField equation;
	JTextField output;
	
	// Constructor for the CalculatorView Class
	public CalculatorView() {
		super("Calculator View"); // Call the constructor for the JFrame class
		
		// Create six JPanels to group components
		JPanel mainPanel = new JPanel();
		JPanel row1 = new JPanel();
		JPanel row2 = new JPanel();
		JPanel row3 = new JPanel();
		JPanel row4 = new JPanel();
		JPanel row5 = new JPanel();
		JPanel row6 = new JPanel();
		
		// Create the text fields and then the buttons and set their text
		equation = new JTextField(16);
		output = new JTextField(16);
		btnAdd = new JButton("+");
		btnSub = new JButton("-");
		btnDiv = new JButton("/");
		btnMul = new JButton("*");
		btnClr = new JButton("C");
		btnDel = new JButton("D");
		btnEql = new JButton("=");
		btnPer = new JButton(".");
		btnMAdd = new JButton("M+");
		btnMSub = new JButton("M-");
		btnSqrd = new JButton("(Squared)");
		btnSqrR = new JButton("(Square-Root)");
		btnMRec = new JButton("M-Recall");
		btnMClr = new JButton("M-Clear");
		btnNeg = new JButton("neg_");
		
		// Create an array of buttons for the digits 0 to 9 and set their font styles
		numBtn = new JButton[10];
		for (int i = 0; i < numBtn.length; i++) {
			numBtn[i] = new JButton(String.valueOf(i));
			numBtn[i].setFont(new Font("Monospaced", Font.BOLD, 22));
		}
		
		// Set the font style of required buttons
		btnAdd.setFont(new Font("Monospaced", Font.BOLD, 22));
		btnSub.setFont(new Font("Monospaced", Font.BOLD, 22));
		btnDiv.setFont(new Font("Monospaced", Font.BOLD, 22));
		btnMul.setFont(new Font("Monospaced", Font.BOLD, 22));
		btnClr.setFont(new Font("Monospaced", Font.BOLD, 22));
		btnDel.setFont(new Font("Monospaced", Font.BOLD, 22));
		btnEql.setFont(new Font("Monospaced", Font.BOLD, 22));
		btnPer.setFont(new Font("Monospaced", Font.BOLD, 22));
		btnMAdd.setFont(new Font("Monospaced", Font.BOLD, 15));
		btnMSub.setFont(new Font("Monospaced", Font.BOLD, 15));
		btnSqrd.setFont(new Font("Monospaced", Font.BOLD, 13));
		btnSqrR.setFont(new Font("Monospaced", Font.BOLD, 13));
		btnMRec.setFont(new Font("Monospaced", Font.BOLD, 13));
		btnMClr.setFont(new Font("Monospaced", Font.BOLD, 13));
		btnNeg.setFont(new Font("Monospaced", Font.BOLD, 13));
		
		// Define the equation text field 
		equation.setMaximumSize(new Dimension(220, 40));
		equation.setFont(new Font("Monospaced", Font.BOLD, 16));
		equation.setDisabledTextColor(new Color(0, 0, 0));
		equation.setMargin(new Insets(0, 5, 0, 0));
		equation.setText("");
		equation.setEditable(false);
		
		// Define the output text field
		output.setMaximumSize(new Dimension(220, 40));
		output.setFont(new Font("Monospaced", Font.BOLD, 16));
		output.setDisabledTextColor(new Color(0, 0, 0));
		output.setMargin(new Insets(0, 5, 0, 0));
		output.setText("0.0");
		output.setEditable(false);
		
		// Define the layout for each component row
		row1.setLayout(new BoxLayout(row1, BoxLayout.LINE_AXIS));
		row2.setLayout(new BoxLayout(row2, BoxLayout.LINE_AXIS));
		row3.setLayout(new BoxLayout(row3, BoxLayout.LINE_AXIS));
		row4.setLayout(new BoxLayout(row4, BoxLayout.LINE_AXIS));
		row5.setLayout(new BoxLayout(row5, BoxLayout.LINE_AXIS));
		row6.setLayout(new BoxLayout(row6, BoxLayout.LINE_AXIS));
		
		// Add buttons to their corresponding rows
		row1.add(numBtn[7]);
		row1.add(numBtn[8]);
		row1.add(numBtn[9]);
		row1.add(btnClr);
		row1.add(btnDel);
		row2.add(numBtn[4]);
		row2.add(numBtn[5]);
		row2.add(numBtn[6]);
		row2.add(btnAdd);
		row2.add(btnSub);
		row3.add(numBtn[1]);
		row3.add(numBtn[2]);
		row3.add(numBtn[3]);
		row3.add(btnMul);
		row3.add(btnDiv);
		row4.add(btnPer);
		row4.add(numBtn[0]);
		row4.add(btnEql);
		row4.add(btnMAdd);
		row4.add(btnMSub);
		row5.add(btnSqrd);
		row5.add(btnSqrR);
		row6.add(btnMRec);
		row6.add(btnMClr);
		row6.add(btnNeg);
		
		// Add components to main panel
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(equation);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		mainPanel.add(output);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		mainPanel.add(row1);
		mainPanel.add(row2);
		mainPanel.add(row3);
		mainPanel.add(row4);
		mainPanel.add(row5);
		mainPanel.add(row6);
		
		// Add main panel to JFrame and define default values of JFrame
		this.add(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(270, 350);
	}
	
	// Method for adding ActionListener to number buttons
	public void addNumButtonListener(ActionListener listener) {
		for (JButton btn : numBtn) {
			btn.addActionListener(listener);
		}
		btnPer.addActionListener(listener);
		btnNeg.addActionListener(listener);
	}
	
	// Method for adding ActionListener to operator buttons
	public void addOpButtonListener(ActionListener listener) {
		btnAdd.addActionListener(listener);
		btnSub.addActionListener(listener);
		btnDiv.addActionListener(listener);
		btnMul.addActionListener(listener);
		btnMAdd.addActionListener(listener);
		btnMSub.addActionListener(listener);
		btnSqrd.addActionListener(listener);
		btnSqrR.addActionListener(listener);
	}
	
	// Method for adding ActionListener to clear button
	public void addClearButtonListener(ActionListener listener) {
		btnClr.addActionListener(listener);
	}
	
	// Method for adding ActionListener to delete button
	public void addDeleteButtonListener(ActionListener listener) {
		btnDel.addActionListener(listener);
	}
	
	// Method for adding ActionListener to equal button
	public void addEqualButtonListener(ActionListener listener) {
		btnEql.addActionListener(listener);
	}
	
	// Method for adding ActionListener to M-Recall button
	public void addMRecallButtonListener(ActionListener listener) {
		btnMRec.addActionListener(listener);
	}
	
	// Method for adding ActionListener to M-Clear button
	public void addMClearButtonListener(ActionListener listener) {
		btnMClr.addActionListener(listener);
	}
	
	// Returns text field of equation
	public JTextField getEquation() {
		return equation;
	}
	
	// Returns text field of output
	public JTextField getOutput() {
		return output;
	}
	
	// Returns button array of number buttons
	public JButton[] getNumBtn() {
		return numBtn;
	}
	
	// Returns button array of operator buttons
	public JButton[] getOpBtn() {
		return new JButton[] {btnAdd, btnSub, btnDiv, btnMul, btnMAdd, btnMSub, btnSqrd, btnSqrR};
	}
}

