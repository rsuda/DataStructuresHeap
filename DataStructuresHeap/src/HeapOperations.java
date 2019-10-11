import java.util.ArrayList;
import java.util.Scanner;

public class HeapOperations 
{
	static int[] heap;
	private int size;
	private static final int MAXSIZE = 20;
	static Scanner in = new Scanner(System.in);

	public HeapOperations()
	{
		this.size = 0;
		heap = new int[MAXSIZE + 1];
		heap[0] = Integer.MIN_VALUE;
	}
	
	private int parent(int index)
	{
		return index / 2;
	}
	
	private int leftChild(int index)
	{
		return (2 * index);
	}
	
	private int rightChild(int index)
	{
		return (2 * index) + 1;
	}
	
	private boolean isLeaf(int index)
	{
		if (index >= (size / 2) && index <= size)
		{
			return true;
		}
		return false;
	}
	
	private void swap(int firstInd, int lastInd)
	{
		int temp;
		temp = heap[firstInd];
		heap[firstInd] = heap[lastInd];
		heap[lastInd] = temp;
	}
	
	private void heapify(int index)
	{
		if (!isLeaf(index))
		{
			if (heap[index] > heap[leftChild(index)] 
					|| heap[index] > heap[rightChild(index)])
			{
				if (heap[leftChild(index)] < heap[rightChild(index)])
				{
					swap(index, leftChild(index));
					heapify(leftChild(index));
				}
				else
				{
					swap(index, rightChild(index));
					heapify(rightChild(index));
				}
			}
		}
	}
	public static void main(String[] args) 
	{
		System.out.println("This program will build a heap with input then do some operations");
		System.out.println("on the heap. Building the heap is expected to be done first.");
		System.out.println("Insert should only take one input at a time and selecting 7 will ");
		System.out.println("terminate the program. To select an option hit the corresponding");
		System.out.println("number, then push enter.\n");
		Scanner in = new Scanner(System.in);
		int input;
		do
		{
			System.out.println("\tSelect 1: build a heap using that takes up to 20 integers.");
			System.out.println("\tSelect 2: insert an integer to the heap. (only one at a time)");
			System.out.println("\tSelect 3: delete the minimum element in the heap. (percolate down)");
			System.out.println("\tSelect 4: remove the value given from heap, if found.");
			System.out.println("\tSelect 5: change the value of one element to another. (Seperate with comma)");
			System.out.println("\tSelect 6: prints out current elements in the heap.");
			System.out.println("\tSelect 7: terminate the program.\n");
			System.out.print("\nSelect option: ");
			input = in.nextInt();
			methodFinder(input);
		}while (input != 7);
		System.out.print("\n\n\tExited program.");
	}
	
	/**
	 * Finds the method to implement depending on the users input.
	 * @param input the option that the user selected.
	 */
	public static void methodFinder(int input)
	{
		if (input == 1)
		{
			buildHeap();
		}
		else if (input == 2)
		{
			insert();
		}
		else if (input == 3)
		{
			deleteMin();
		}
		else if (input == 4)
		{
			remove();
		}
		else if (input == 5)
		{
			changeValue();
		}
		else if (input == 6)
		{
			printHeap();
		}
	}
	
	/**
	 * Takes input from the user of an array that is 20 or less integers
	 * then build a heap structure with that array.
	 */
	private static void buildHeap()
	{
		int[] array = new int[20];
		int counter = 0;
		System.out.println("\n\n\tInput 20 or less integers to build the heap. (Seperate by comma)");
		System.out.print("\nInput: ");
		String build = in.next();
		Scanner record = new Scanner(build);
        record.useDelimiter(",");
        while (record.hasNextInt()) 
        {
        	array[counter] = record.nextInt();
        	counter++;
        }
        heap = new int[counter];
        for (int i = 0; i < counter; i++)
        {
        	heap[i] = array[i];
        }
        record.close();
        sortHeap();
		System.out.print("\n");
		printHeap();
		
	}
	
	private static void sortHeap()
	{
        // Build heap (rearrange array) 
        for (int i = heap.length / 2 - 1; i >= 0; i--) 
            heapify(heap.length, i); 
  
        // One by one extract an element from heap 
        for (int i = heap.length-1; i >= 0; i--) 
        { 
            // Move current root to end 
            int temp = heap[0]; 
            heap[0] = heap[i]; 
            heap[i] = temp; 
  
            // call max heapify on the reduced heap 
            heapify(i, 0); 
        } 
	}
	
	private static void heapify(int length, int index)
	{
		int maximum = index; // Initialize largest as root 
        int left = 2 * index + 1; // left = 2*i + 1 
        int right = 2 * index + 2; // right = 2*i + 2 
  
        // If left child is larger than root 
        if (left < length && heap[left] > heap[maximum]) 
            maximum = left; 
  
        // If right child is larger than largest so far 
        if (right < length && heap[right] > heap[maximum]) 
            maximum = right; 
  
        // If largest is not root 
        if (maximum != index) 
        { 
            int swap = heap[index]; 
            heap[index] = heap[maximum]; 
            heap[maximum] = swap; 
  
            // Recursively heapify the affected sub-tree 
            heapify(length, maximum); 
        } 
	}
	/**
	 * Takes a single integer from the user and inserts it into the heap.
	 */
	private static void insert()
	{
		int insertElement = 0;
		System.out.println("\n\n\tInput only one integer to be inserted into the heap.");
		System.out.print("\nInsert: ");
		insertElement = in.nextInt();
		System.out.print("\n");
		int[] tempArray = new int[heap.length + 1];
		for (int i = 0; i < tempArray.length; i++)
		{
			if (i == tempArray.length - 1)
			{
				tempArray[i] = insertElement;
			}
			else
			{
				tempArray[i] = heap[i];
			}
		}
		heap = new int[tempArray.length];
		for (int i = 0; i < tempArray.length; i++)
		{
			heap[i] = tempArray[i];
		}
		sortHeap();
		printHeap();
	}
	
	/**
	 * Deletes the minimum value in the heap, which is the root.
	 */
	private static void deleteMin()
	{
		System.out.println("\n\n\tDeleting minimum element.");
		System.out.print("\n");
		int[] tempArray = new int[heap.length - 1];
		for (int i = 0; i < tempArray.length; i++)
		{
			tempArray[i] = heap[i + 1];
		}
		heap = new int[tempArray.length];
		for (int i = 0; i < tempArray.length; i++)
		{
			heap[i] = tempArray[i];
		}
		sortHeap();
		printHeap();
	}
	
	/**
	 * Removes the key with value ‘x’ (not the key at index ‘x’) from the heap
	 */
	private static void remove()
	{
		int removeElement = 0;
		System.out.println("\n\n\tInput one element to remove from the heap. (Value, not index)");
		System.out.print("\nRemove: ");
		removeElement = in.nextInt();
		for (int i = 0; i < heap.length; i++)
		{
			if (removeElement == heap[i])
			{
				
			}
			else if (i == heap.length)
			{
				System.out.printf("Element %d was not found!", removeElement);
			}
		}
		System.out.print("\n");
	}

	/**
	 * Changes the value of key ‘x’ to ‘xnew’ (again, x is an actual value, not an index)
	 */
	private static void changeValue()
	{
		int changeElement = 0;
		int changeToElement = 0;
		System.out.println("\n\n\tInput one element to change from the heap. (Value, not index)");
		System.out.print("\nChange: ");
		changeElement = in.nextInt();
		System.out.print("\nChange to: ");
		changeToElement = in.nextInt();
		System.out.print("\n");
	}
	
	/**
	 * Starting with a message “The heap has the following elements:”, it prints the 
	 * current contents in the array. In addition to a standalone option, print method 
	 * should always be called as the last step of any option 1 to 5. 
	 */
	private static void printHeap()
	{
		System.out.print("\n\n\tPrinting the heap...");
		System.out.print("\n");
		System.out.print("Array: ");
		for (int i = 0; i < heap.length; ++i)
		{
			System.out.print( heap[i] + " ");
		} 
        System.out.println(); 
		System.out.print("\n\n");
	}
}
