package phone_filtering.model.tree;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AmbiguityTreeTest {

	@Test
	public void test() {
		List<String> list=new ArrayList<>();
		list.add("300");
		list.add("31");
		list.add("5");
		list.add("24");
		list.add("52");
		list.add("213");
		//list.add("5");
		AmbiguityTree tree=new AmbiguityTree(list);
		System.out.println(tree.getPaths());
	}

}
