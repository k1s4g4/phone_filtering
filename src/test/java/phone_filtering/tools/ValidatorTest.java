package phone_filtering.tools;

import static org.junit.Assert.*;
import org.junit.Test;

public class ValidatorTest {
	
	
	//Testing inputIsValid method
	@Test
	public void testLettersAsInput() {
		assertFalse(Validator.inputIsValid("asdf"));
	}
	
	@Test
	public void testFourDigitsAsInput() {
		assertFalse(Validator.inputIsValid("1234 123 12"));
	}
	
	@Test
	public void testThreeOrLessDigitsAsInput() {
		assertTrue(Validator.inputIsValid("210 2 35 67"));
	}
	
	@Test
	public void testThreeOrLessDigitsAsInputWithSpaceBeforeEnd() {
		assertTrue(Validator.inputIsValid("69 55 17 0 "));
	}
	
	
	//Testing numberIsValid method
	@Test
	public void testCellNumberCountryCode() {
		assertTrue(Validator.numberIsValid("00306941792752"));
	}
	
	@Test
	public void testCellNumberCountryCodeOneMoreDigit() {
		assertFalse(Validator.numberIsValid("003069417927520"));
	}

	@Test
	public void testCellNumberCountryCodeOneLessDigit() {
		assertFalse(Validator.numberIsValid("0030694179275"));
	}
	
	@Test 
	public void testNotCellCountryCode() {
		assertTrue(Validator.numberIsValid("00302106534127"));
	}
	
	@Test
	public void testCellNumber() {
		assertTrue(Validator.numberIsValid("6977834642"));
	}
	
	@Test
	public void testCellNumberOneMoreDigit() {
		assertFalse(Validator.numberIsValid("69778346422"));
	}
	
	@Test
	public void testCellNumberOneLessDigit() {
		assertFalse(Validator.numberIsValid("697783464"));
	}
	
}
