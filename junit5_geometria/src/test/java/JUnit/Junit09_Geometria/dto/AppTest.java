package JUnit.Junit09_Geometria.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class AppTest {
	
	private static Geometria geo;
	
	@BeforeAll
	public static void createObject() {
		geo = new Geometria(); 
		geo = new Geometria(5);
	}
	
	@Test
	public void testSqrArea() {
		assertEquals(25, geo.areacuadrado(5));
	}
	
	@Test
	public void testAreaCirculo() {
		assertEquals(78.5, geo.areaCirculo(5), 0.1);
	}

	@Test
	public void testAreaTriangulo() {
		assertEquals(25, geo.areatriangulo(5, 10));
	}
	
	@Test
	public void testAreaRectangulo() {
		assertEquals(6, geo.arearectangulo(2, 3));
	}
	
	@Test
	public void testAreaPentagono() {
		assertEquals(3, geo.areapentagono(2, 3));
	}
	
	@Test
	public void testAreaRombo() {
		assertEquals(15, geo.arearombo(6, 5));
	}

	@Test
	public void testAreaRomboide() {
		assertEquals(30, geo.arearomboide(6, 5));
	}

	@Test
	public void testAreaTrapecio() {
		assertEquals(18, geo.areatrapecio(7, 5, 3));
	}
	
	private static Stream<Arguments> getFiguras(){
		return Stream.of(
				Arguments.of(1, "Cuadrado"),
				Arguments.of(2, "Circulo"),
				Arguments.of(3, "Triangulo"),
				Arguments.of(4, "Rectangulo"),
				Arguments.of(5, "Pentagono"),
				Arguments.of(6, "Rombo"),
				Arguments.of(7, "Romboide"),
				Arguments.of(8, "Trapecio"),
				Arguments.of(13, "Default"));
	}
	
	@ParameterizedTest
	@MethodSource("getFiguras")
	public void testFigura(int figuraNum, String expected) {
		assertEquals(expected, geo.figura(figuraNum));
	}
	
	@Test
	public void testGetId() {
		assertEquals(5, geo.getId());
	}
	
	@Test
	public void testSetId() {
		geo.setId(20);
		assertEquals(20, geo.getId());
	}
	
	@Test
	public void testGetNom() {
		assertEquals("Pentagono", geo.getNom());
	}
	
	@Test
	public void testSetNom() {
		geo.setNom("Cuadrado");
		assertEquals("Cuadrado", geo.getNom());
	}
	
	@Test
	public void testGetArea() {
		assertEquals(0, geo.getArea());
	}
	
	@Test
	public void testSetArea() {
		geo.setArea(23.3);
		assertEquals(23.3, geo.getArea());
	}
	
	@AfterAll
	public static void testToString() {
		String expected = "Geometria [id=20, nom=Cuadrado, area=23.3]";
		assertEquals(expected, geo.toString());
	}
}

