import java.util.Scanner;

/**
     * Implements a binary heap.
     * Note that all "matching" is based on the compareTo method.
     * @author Robin Suda
     * @resources Data Structures and Algorithm Analysis by Alan Weiss
     */
    public class BinaryHeap
    {
    	private Scanner in;
    	private final int MAX_CAPACITY = 20;
    	
    	private int currentSize;      // Number of elements in heap
        private int [ ] array; // The heap array
        
        /**
         * Construct the binary heap.
         */
        public BinaryHeap( )
        {
            currentSize = 0;
            array = new int[MAX_CAPACITY];
        }
        
        /**
         * Construct the binary heap with elements.
         * @param items the elements to add to the heap.
         */
        public BinaryHeap(int[] items)
        {
        	currentSize = items.length;
        	array = new int[MAX_CAPACITY];
        	int i = 1;
        	for (int item: items)
        	{
        		array[i++] = item;
        	}
        	buildHeap();
        }

        /**
         * Insert into the priority queue, maintaining heap order.
         * Duplicates are allowed.
         * @param x the item to insert.
         * @exception Overflow if container is full.
         */
        public void insert( int x ) throws Exception
        {
            if( isFull( ) )
                throw new Exception( );

            currentSize++;
                // Percolate up
            int hole = currentSize;
            for( ; hole > 1 && x < ( array[ hole / 2 ] ); hole /= 2 )
                array[ hole ] = array[ hole / 2 ];
            array[ hole ] = x;
        }

        /**
         * Find the smallest item in the priority queue.
         * @return the smallest item, or null, if empty.
         */
        public int findMin( )
        {
            if( isEmpty( ) )
                return 0;
            return array[ 1 ];
        }

        /**
         * Remove the smallest item from the priority queue.
         * @return the smallest item, or null, if empty.
         */
        public int deleteMin( )
        {
            if( isEmpty( ) )
            {
            	return 0;
            }

            int minItem = findMin( );
            array[ 1 ] = array[ currentSize--];
            percolateDown( 1 );

            return minItem;
        }

        /**
         * Establish heap order property from an arbitrary
         * arrangement of items. Runs in linear time.
         */
        private void buildHeap( )
        {
            for( int i = currentSize / 2; i > 0; i-- )
                percolateDown( i );
        }

        /**
         * Test if the priority queue is logically empty.
         * @return true if empty, false otherwise.
         */
        public boolean isEmpty( )
        {
            return currentSize == 0;
        }

        /**
         * Test if the priority queue is logically full.
         * @return true if full, false otherwise.
         */
        public boolean isFull( )
        {
            return currentSize == array.length - 1;
        }

        /**
         * Make the priority queue logically empty.
         */
        public void makeEmpty( )
        {
            currentSize = 0;
        }
        
        /**
         * Internal method to percolate down in the heap.
         * @param hole the index at which the percolate begins.
         */
        private void percolateDown( int hole )
        {	
        	int child;
			int tmp = array[ hole ];

			for( ; hole * 2 <= currentSize; hole = child )
            {
				child = hole * 2;
					if( child != currentSize &&
					array[ child + 1 ] < ( array[ child ] ) )
					child++;
				if( array[ child ] < tmp )
					array[ hole ] = array[ child ];
                else
                	break;
            }
			array[ hole ] = tmp;
        }
        
        /**
         * Takes the value and finds it's index.
         * @param value the value you want to find.
         * @return the index, if value is not found return -1.
         */
        public int find(int value)
        {
        	for(int i = 0; i < currentSize; i++)
        	{
        		if (array[i] == value)
        		{
        			return i;
        		}
        	}
        	return -1;
        }
        
        /**
         * Prints the heap in array form.
         */
        public void printHeap()
        {
        	for (int i = 0; i <= currentSize; i++)
        	{
        		System.out.print(array[i] + " ");
        	}
        	System.out.println();
        }
        
        /**
         * Removes the element from the binary heap.
         * @param value the value to remove.
         * @return the removed value.
         */
        public int remove(int value) 
        {
        	int index = find(value);
        	int key = array[index];
        	array[index] = array[currentSize];
        	percolateDown(index);
        	--currentSize;
        	return key;
        }
        
        /**
    	 * Changes the value of an item, while maintaining heap order.
    	 * @param keyValue the value you want to replace.
    	 * @param changeValue the value you want to replace it with.
    	 */
    	public boolean changeValue(int keyValue, int changeValue)
    	{
    		int index = find(keyValue);
    		if(index < 0 || index >= currentSize)
    		{
    			return false;
    		}
    		int oldValue = array[index]; // remember old
    		array[index] = changeValue;  // change to new
    		if(oldValue < changeValue)	// if raised,
    		{
    			percolateUp(index);                // percolate it up
    		}
    		else // if lowered,
    		{
    			percolateDown(index);              // percolate it down
    		}
    		return true;
    		
    	}
    	
    	/**
    	 * Internal method to percolate down in the heap.
    	 * @param hole the index at which the percolate begins.
    	 */
    	private void percolateUp(int index)
    	{
    		int hole;

    		int tmp = array[index];

    		for( hole = index; 
    			hole > 1 && tmp < array[ hole / 2 ]; 
    			hole /= 2 )
    		{
    			array[ hole ] = array[ hole / 2 ];
    		}

    		array[ hole ] = tmp;
    	}
    
    	/**
    	 * Main method that takes user input and inserts it into heap.
    	 * Operations include: Build heap, insert, remove, change value, print heap,
    	 * and terminate program.
    	 * @param args
    	 * @throws Exception exception if the heap is wrong.
    	 */
    public static void main(String[] args) throws Exception 
	{
    	BinaryHeap heap = null;
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
			if (input == 1)
			{
				int[] buildArray = new int[20];
				int counter = 0;
				System.out.println("\n\n\tInput 20 or less integers to build the heap. (Seperate by comma)");
				System.out.print("\nInput: ");
				String build = in.next();
				Scanner record = new Scanner(build);
		        record.useDelimiter(",");
		        while (record.hasNextInt()) 
		        {
		        	buildArray[counter] = record.nextInt();
		        	counter++;
		        }
		        int[] buildArrayFinal = new int[counter];
		        for (int i = 0; i < counter; i++)
		        {
		        	buildArrayFinal[i] = buildArray[i];
		        }
		        record.close();
		        heap = new BinaryHeap(buildArrayFinal);
			}
			else if (input == 2)
			{
				int insertElement = 0;
				System.out.println("\n\n\tInput only one integer to be inserted into the heap.");
				System.out.print("\nInsert: ");
				insertElement = in.nextInt();
				System.out.print("\n");
				heap.insert(insertElement);
			}
			else if (input == 3)
			{
				System.out.println("\n\n\tDeleting minimum element.");
				System.out.print("\n");
				heap.deleteMin();
			}
			else if (input == 4)
			{
				int removeElement = 0;
				System.out.println("\n\n\tInput one element to remove from the heap. (Value, not index)");
				System.out.print("\nRemove: ");
				removeElement = in.nextInt();
				int index = heap.find(removeElement);
				if (index == -1)
				{
					System.out.printf("\n\n\t%d is not found./n/n", removeElement);
				}
				else
				{
					heap.remove(removeElement);
				}
			}
			else if (input == 5)
			{
				int changeElement = 0;
				int changeToElement = 0;
				System.out.println("\n\n\tInput one element to change from the heap. (Value, not index)");
				System.out.print("\nChange: ");
				changeElement = in.nextInt();
				System.out.print("\nChange to: ");
				changeToElement = in.nextInt();
				System.out.print("\n");
				heap.changeValue(changeElement, changeToElement);
			}
			else if (input == 6)
			{
				heap.printHeap();
			}
		}while (input != 7);
		System.out.print("\n\n\tExited program.");
	}
}