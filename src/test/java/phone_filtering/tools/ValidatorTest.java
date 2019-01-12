package phone_filtering.tools;

import static org.junit.Assert.*;
import org.junit.Test;

public class ValidatorTest {
	
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

}
