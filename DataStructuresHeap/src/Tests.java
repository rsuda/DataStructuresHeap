import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class Tests 
{
	int[] array = {35, 98, 17, 27, 46, 82, 75, 68, 5, 3, 19, 22, 43, 9, 2};
	BinaryHeap test = new BinaryHeap(array);

	@Test
	public void test() 
	{
		System.out.print("Print Array     : ");
		test.printHeap();
		System.out.print("\n");
	}
	
	@Test
	public void testDeleteMin()
	{
		System.out.print("DeleteMinimum   : ");
		test.deleteMin();
		test.printHeap();
		System.out.print("\n");
	}
	
	@Test
	public void testRemove()
	{
		test.deleteMin();
		System.out.print("Removing 17     : ");
		test.remove(17);
		test.printHeap();
		System.out.print("\n");
	}
	
	@Test
	public void testChange()
	{
		test.deleteMin();
		test.remove(17);
		System.out.print("Changing 5 to 12: ");
		test.changeValue(5, 12);
		test.printHeap();
		System.out.print("\n");
	}
	
	@Test
	public void testChange2()
	{
		test.deleteMin();
		test.remove(17);
		test.changeValue(5, 12);
		System.out.print("Changing 19 to 14: ");
		test.changeValue(19, 14);
		test.printHeap();
		System.out.print("\n");
	}
	
	@Test
	public void testInsert() throws Exception
	{
		test.deleteMin();
		test.remove(17);
		test.changeValue(5, 12);
		test.changeValue(19, 14);
		System.out.print("Inserting 1    : ");
		test.insert(1);
		test.printHeap();
		System.out.print("\n");
	}
	
	@Test
	public void testRemove5() throws Exception
	{
		test.deleteMin();
		test.remove(17);
		test.changeValue(5, 12);
		test.changeValue(19, 14);
		test.insert(1);
		System.out.print("Remove 5    : ");
		test.remove(5);
		test.printHeap();
		System.out.print("\n");
	}

}
