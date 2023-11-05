package com.alejandro.ejercicio2_junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CalculadoraGUITest {
	
	private static CalculadoraGUI calculadora;
	
	@BeforeAll
	public static void crearCalculadora() {
		calculadora = new CalculadoraGUI();
	}
	
	@Test
	public void testSuma() {
		assertEquals(12, calculadora.sumar(7, 5));
	}
	
	@Test
	public void testResta() {
		assertEquals(2, calculadora.restar(7, 5));
	}
	
	@Test
	public void testDividir() {
		assertEquals(5, calculadora.dividir(15, 3));
	}
	
	@Test
	public void testMultiplicar() {
		assertEquals(45, calculadora.multiplicar(15, 3));
	}
	
	@Test
	public void testGetOperatorSuma() {
		assertEquals("+", calculadora.getOperator("5 + 4"));
	}
	
	@Test
	public void testGetOperatorResta() {
		assertEquals("-", calculadora.getOperator("5 - 4"));
	}
	
	@Test
	public void testGetOperatorMultiplicar() {
		assertEquals("*", calculadora.getOperator("5 * 4"));
	}
	
	@Test
	public void testGetOperatorDividir() {
		assertEquals("/", calculadora.getOperator("5 / 4"));
	}
	
	@Test
	public void testGetFirstOperator() {
		assertEquals(5.0, calculadora.getFirstOperator("5 / 4"));
	}
	
	@Test
	public void testGetSeccondOperator() {
		assertEquals(4.0, calculadora.getSeccondOperator("5 / 4"));
	}
}
