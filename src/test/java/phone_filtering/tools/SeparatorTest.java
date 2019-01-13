package phone_filtering.tools;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SeparatorTest {

	@Test
	public void testSeparatorDecades() {
		assertEquals("0 1 101 0 7 70",Separator.separateZeros("01 101 07 70"));
	}
	
	@Test
	public void testSeparatorHundreds() {
		assertEquals("0 0 1 0 0 6",Separator.separateZeros("001 006"));
	}
	
	@Test
	public void testInputToParts() {
		List<String> list=new ArrayList<>();
		list.add("23");
		list.add("45");
		list.add("234");
		list.add("56");
		list.add("5");
		assertEquals(list,Separator.inputToParts("23 45 234 56 5"));
	}
	
	@Test
	public void testCombinationZeroSeparationAndInputToParts() {
		List<String> list=new ArrayList<>();
		list.add("6");
		list.add("90");
		list.add("0");
		list.add("8");
		list.add("56");
		list.add("0");
		list.add("0");
		list.add("8");
		list.add("1");
		assertEquals(list,Separator.inputToParts(Separator.separateZeros("6 90 08 56 008 1")));
	}

}
