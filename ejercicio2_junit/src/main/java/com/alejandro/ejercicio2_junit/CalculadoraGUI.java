package com.alejandro.ejercicio2_junit;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CalculadoraGUI extends JFrame {
	
	private final int FRAME_WIDTH = 350;
	private final int FRAME_HEIGHT = 390;
	private final int BUTTON_WIDTH = 80;
	private final int BUTTON_HEIGHT = 50;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel calculatorScreen;
	private JButton comaButton;
	private JButton[] numericButtons = new JButton[10];
	private JButton[] operationButtons = new JButton[4];
	private JButton equalsButton;
	private int horizontal_position = 5;
	private int horizontal_movement = 81;
	private int vertical_position = 100;
	private int vertical_movement = 51;
	private int colCounter = 0;
	private boolean resetScreen = true;

	public CalculadoraGUI() {
		
		setTitle("Calculadora");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setBackground(new Color(59, 58, 58));
        
        contentPane = new JPanel();
        contentPane.setLayout(null);
        
        setContentPane(contentPane);
        
        calculatorScreen = new JLabel("0", SwingConstants.RIGHT);
        calculatorScreen.setBounds(10, 45, 314, 75);
        calculatorScreen.setFont(new Font("Verdana", Font.PLAIN, 26));
        contentPane.add(calculatorScreen);
        
        for(int i = 0; i < numericButtons.length; i++) {

        	numericButtons[i] = new JButton(String.valueOf(numericButtons.length - (i + 1)));
 
        	if(colCounter % 3 == 0) {
        		horizontal_position = 5;
        		vertical_position += vertical_movement;
        		colCounter = 0;
        	} 
        	
    		numericButtons[i].setBounds(horizontal_position + (horizontal_movement * colCounter), vertical_position, BUTTON_WIDTH, BUTTON_HEIGHT);
    		colCounter++;
        	contentPane.add(numericButtons[i]);
        }
        
        
        operationButtons[0] = new JButton("+");
        operationButtons[1] = new JButton("-");
        operationButtons[2] = new JButton("*");
        operationButtons[3] = new JButton("/");

        int button0x = (int) numericButtons[9].getLocation().getX();
        int button0y = (int) numericButtons[9].getLocation().getY();
        
        for(int i = 0; i < operationButtons.length; i++) {
        	operationButtons[i].setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        	operationButtons[i].setLocation(button0x + horizontal_position + (BUTTON_WIDTH * 3), button0y - (BUTTON_HEIGHT * i));
        	contentPane.add(operationButtons[i]);
        }
        
        comaButton = new JButton(".");
        comaButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        comaButton.setLocation(button0x + (BUTTON_WIDTH), button0y);
        contentPane.add(comaButton);
        
        
        equalsButton = new JButton("=");
        equalsButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        equalsButton.setLocation(button0x + (BUTTON_WIDTH * 2), button0y);
        contentPane.add(equalsButton);

        setListeners();
        
        setVisible(true);
	}

	
	private void setListeners() {
		
		//Listener para los botones numericos que muestra el numero pulsado en la pantalla de la calculadora
		ActionListener showScreenListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String buttonText = ((JButton)e.getSource()).getText();
				if(resetScreen) {
					resetScreen = false;
					calculatorScreen.setText("");
				}
				calculatorScreen.setText(calculatorScreen.getText() + buttonText);
				
			}
		};
		
		//Asignar el listener a los botones numericos
		for(int i = 0; i < numericButtons.length; i++) {
			numericButtons[i].addActionListener(showScreenListener);
		}
		comaButton.addActionListener(showScreenListener);

		for(int i = 0; i < operationButtons.length; i++) {
			operationButtons[i].addActionListener(showScreenListener);
		}
		
		
		ActionListener operateListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String screenData = calculatorScreen.getText().replace(',', '.');
				DecimalFormat df = new DecimalFormat("0.00");
				double result = 0;
				
				if(getOperator(screenData).equals("+")) {
					result = sumar(getFirstOperator(screenData), getSeccondOperator(screenData));
					calculatorScreen.setText(df.format(result));
				} else if(getOperator(screenData).equals("-")) {
					result = restar(getFirstOperator(screenData), getSeccondOperator(screenData));
					calculatorScreen.setText(df.format(result));
				} else if(getOperator(screenData).equals("*")) {
					result = multiplicar(getFirstOperator(screenData), getSeccondOperator(screenData));
					calculatorScreen.setText(df.format(result));
				} else if(getOperator(screenData).equals("/")) {
					result = dividir(getFirstOperator(screenData), getSeccondOperator(screenData));
					calculatorScreen.setText(df.format(result));
				}
				
			}
		};

		for(int i = 0; i < operationButtons.length; i++) {
			operationButtons[i].addActionListener(operateListener);
		}
		
		equalsButton.addActionListener(operateListener);
	}
	
	public double sumar(double num1, double num2) {
		return num1 + num2;
	}
	
	public double restar(double num1, double num2) {
		return num1 - num2;
	}
	
	public double multiplicar(double num1, double num2) {
		return num1 * num2;
	}
	
	public double dividir(double num1, double num2) {
		return num1 / num2;
	}
	
	//Extraer el operador aritmetico del string
	public String getOperator(String operation) {
		
		String operator = "";

		if(operation.indexOf('+') > 0) {
			operator = "+";
		} else if(operation.indexOf('-') > 0) {
			operator = "-";
		} else if(operation.indexOf('*') > 0) {
			operator = "*";
		} else if(operation.indexOf('/') > 0) {
			operator = "/";
		}
		
		return operator;
	}

	//Sacar el primer numero de la operación del string
	public double getFirstOperator(String operation) {
		double firstOperator = 0;
		String operator = getOperator(operation);
		
		if(!operator.equals("")) {
			firstOperator = Double.parseDouble(operation.substring(0, operation.indexOf(operator)));
		}
		
		return firstOperator;
	}
	
	//Sacar el segundo numero de la operación del string
	public double getSeccondOperator(String operation) {
		double seccondOperator = 0;
		String operator = getOperator(operation);
		
		if(!operator.equals("")) {
			if(operation.substring(operation.indexOf(operator) + 1, operation.length()).equals("")) {
				seccondOperator = 0;
			} else {
				seccondOperator = Double.parseDouble(operation.substring(operation.indexOf(operator) + 1, operation.length()));
			}
		}
		
		return seccondOperator;
	}
}
