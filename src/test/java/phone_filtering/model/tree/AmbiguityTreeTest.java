package phone_filtering.model.tree;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import phone_filtering.model.tree.tools.TreeTools;

public class AmbiguityTreeTest {

	//Testing if 11 would not be split in two numbers 10 1
	@Test
	public void testEleven() {
		AmbiguityTree tree=new AmbiguityTree(list("11"));
		assertEquals(list("11"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Testing if 12 would not be split in two numbers 10 2
	@Test
	public void testTwelve() {
		AmbiguityTree tree=new AmbiguityTree(list("12"));
		assertEquals(list("12"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Testing if a 2-digit number with non zero units produces two results
	@Test
	public void testTwoDigitSeparation() {
		AmbiguityTree tree=new AmbiguityTree(list("13"));
		assertEquals(list("103","13"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Testing 2-digit number with zero units and next number 3 digit
	@Test
	public void testTwoDigitZeroUnitsNextThreeDigit() {
		AmbiguityTree tree=new AmbiguityTree(list("10","300"));
		assertEquals(list("10300"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	
	//Testing 2-digit number with zero units and next number 2 digit
	@Test
	public void testTwoDigitZeroUnitsNextTwoDigit() {
		AmbiguityTree tree=new AmbiguityTree(list("10","20"));
		assertEquals(list("1020"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Testing 10 with next number 1
	@Test
	public void testTenWithOneAfter() {
		AmbiguityTree tree=new AmbiguityTree(list("10","1"));
		assertEquals(list("101"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Testing 10 with next number 2
	@Test
	public void testTenWithTwoAfter() {
		AmbiguityTree tree=new AmbiguityTree(list("10","2"));
		assertEquals(list("102"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Testing two digit number with 1-digit next
	@Test
	public void testTwoDigitWithOneDigitAfter() {
		AmbiguityTree tree=new AmbiguityTree(list("10","4"));
		assertEquals(list("104","14"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Testing two digit number with zero units with zero next
	
	@Test
	public void testTwoDigitWithZeroAfter() {
		AmbiguityTree tree=new AmbiguityTree(list("10","0"));
		assertEquals(list("100"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	
	//Testing 3 digit number with zero tens and non zero units
	@Test
	public void testThreeDigitZeroTens() {
		AmbiguityTree tree=new AmbiguityTree(list("203"));
		assertEquals(list("203","2003"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Testing 3 digit number with 11 at the end
	@Test
	public void testThreeDigitEleven() {
		AmbiguityTree tree=new AmbiguityTree(list("211"));
		assertEquals(list("20011","211"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Testing 3 digit number with 11 at the end
	@Test
	public void testThreeDigitTwelve() {
		AmbiguityTree tree=new AmbiguityTree(list("212"));
		assertEquals(list("20012","212"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Testing 3 digit number with no zero values
	@Test
	public void testThreeDigitNoZero() {
		AmbiguityTree tree=new AmbiguityTree(list("234"));
		assertEquals(list("200304","20034","2304","234"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Testing 3 digit number with zero units and 3 digit after
	@Test
	public void testThreeDigitZeroUnitsThreeDigitsAfter() {
		AmbiguityTree tree=new AmbiguityTree(list("210","400"));
		assertEquals(list("20010400","210400"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Testing 3 digit number with zero units and 2 digit after
	@Test
	public void testThreeDigitZeroUnitsTwoDigitAfter() {
		AmbiguityTree tree=new AmbiguityTree(list("210","20"));
		assertEquals(list("2001020","21020"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Testing 3 digit number with zero units one at tens and 1 after
	@Test
	public void testThreeDigitZeroUnitsOneAtTensAndOneAfter() {
		AmbiguityTree tree=new AmbiguityTree(list("210","1"));
		assertEquals(list("200101","2101"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Testing 3 digit number with zero units one at tens and 2 after
	@Test
	public void testThreeDigitZeroUnitsOneAtTensAndTwoAfter() {
		AmbiguityTree tree=new AmbiguityTree(list("210","2"));
		assertEquals(list("200102","2102"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Testing 3 digits number with zero units and 1-digit number after
	@Test
	public void testThreeDigitZeroUnitsOneDigitAfter() {
		AmbiguityTree tree=new AmbiguityTree(list("210","3"));
		assertEquals(list("200103","20013","2103","213"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Test 3 digit number with zero tens zero units and 3 digit after
	@Test
	public void testThreeDigitZeroTensZeroUnits() {
		AmbiguityTree tree=new AmbiguityTree(list("200","400"));
		assertEquals(list("200400"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Test 3 digit number with zero tens zero units and 0 after
	@Test
	public void testThreeDigitZeroTensZeroUnitsAndZero() {
		AmbiguityTree tree=new AmbiguityTree(list("200","0"));
		assertEquals(list("2000"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Test 3 digit number with zero tens zero units and 1 digit after
	@Test
	public void testThreeDigitZeroTensZeroUnitsAndOneDigitAfter() {
		AmbiguityTree tree=new AmbiguityTree(list("200","2"));
		assertEquals(list("2002","202"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Test 3 digit number with zero tens zero units and 11 after
	@Test
	public void testThreeDigitZeroTensZeroUnitsAndElevenAfter() {
		AmbiguityTree tree=new AmbiguityTree(list("200","11"));
		assertEquals(list("20011","211"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Test 3 digit number with zero tens zero units and 12 after
	@Test
	public void testThreeDigitZeroTensZeroUnitsAndTwoAfter() {
		AmbiguityTree tree=new AmbiguityTree(list("200","12"));
		assertEquals(list("20012","212"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Test 3 digit number with zero tens zero units and 2 digit after
	@Test
	public void testThreeDigitZeroTensZeroUnitsTwoDigitsAfter() {
		AmbiguityTree tree=new AmbiguityTree(list("200","13"));
		assertEquals(list("200103","20013","2103","213"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Test 3 digit number with zero tens zero units next number 2 digit with zero units and 3 digit after 
	@Test
	public void testThreeDigitZeroTensZeroUnitsTwoDigitAfterZeroUnitsNextThreeDigit() {
		AmbiguityTree tree=new AmbiguityTree(list("200","10","300"));
		assertEquals(list("210300","20010300"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Test 3 digit number with zero tens zero units next number 2 digit with zero units and 2 digit after 
	@Test
	public void testThreeDigitZeroTensZeroUnitsTwoDigitAfterZeroUnitsNextTwoDigit() {
		AmbiguityTree tree=new AmbiguityTree(list("200","10","20"));
		assertEquals(list("21020","2001020"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Test 3 digit number with zero tens zero units next number 2 digit with zero units and 1 at tens and 1 after 
	@Test
	public void testThreeDigitZeroTensZeroUnitsTwoDigitAfterZeroUnitsNextTenAndNextOne() {
		AmbiguityTree tree=new AmbiguityTree(list("200","10","1"));
		assertEquals(list("200101","2101"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Test 3 digit number with zero tens zero units next number 2 digit with zero units and 1 at tens and 2 after 
	@Test
	public void testThreeDigitZeroTensZeroUnitsTwoDigitAfterZeroUnitsNextTenAndNextTwo() {
		AmbiguityTree tree=new AmbiguityTree(list("200","10","2"));
		assertEquals(list("200102","2102"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Test 3 digit number with zero zero tens zero units next number two digits zero units and after next 1 digit
	@Test
	public void testThreeDigitZeroTensZeroUnitsTwoDigitAfterZeroUnitsNextTwoDigitAndAfterNextTwo() {
		AmbiguityTree tree=new AmbiguityTree(list("200","10","3"));
		assertEquals(list("200103","20013","2103","213"),TreeTools.pathsToList(tree.getPaths()));
	}
	
	//Test 3 digit number with zero tens zero units next number two digit with zero units and after next 1 digit zero
	@Test
	public void testThreeDigitZeroTensZeroUnitsNextTwoDigitsZeroUnitsNextOneDigitZero() {
		AmbiguityTree tree=new AmbiguityTree(list("200","10","0"));
		assertEquals(list("2100","200100"),TreeTools.pathsToList(tree.getPaths()));
	}
	private List<String> list(String... strings ){
		List<String> list=new ArrayList<>();
		for(String str:strings) {
			list.add(str);
		}
		return list;
	}

}
