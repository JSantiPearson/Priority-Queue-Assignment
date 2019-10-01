import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A priority queue class implemented using a min heap. Priorities cannot be
 * negative.
 *
 * @author Jordan Pearson and Thalia Barr-Malec
 * @version Date
 *
 */
public class PriorityQueue {

	protected Map<Integer, Integer> location;
	protected List<Pair<Integer, Integer>> heap;

	private static final int ROOT_INDEX = 0;

	/**
	 * Constructs an empty priority queue
	 */
	public PriorityQueue() {
		heap = new ArrayList<Pair<Integer, Integer>>();
		location = new HashMap<Integer, Integer>();
	}

	// Jordan
	public static void main(String[] args) {
		//System.out.print("hello world");
		PriorityQueue queue = new PriorityQueue();

		queue.push(30,-3);
		queue.push(1,-5);
		queue.push(4,-7);
		queue.push(8,-1);
		queue.push(3,-9);
		queue.push(40,-2);
		//queue.pop();
		queue.changePriority(0, -2);
		for(int i = 0; i < queue.size(); i++) {
			System.out.print(queue.heap.get(i).priority);
			System.out.print("\t");
			System.out.println(queue.heap.get(i).element);
		}
		System.out.println(queue.location.toString());
		System.out.print(queue.getPriority(-3));
		queue.clear();
		queue.changePriority(9,8);

	}


	/**
	 * Insert a new element into the queue with the given priority.
	 *
	 * @param priority priority of element to be inserted
	 * @param element  element to be inserted <br>
	 *                 <br>
	 *                 <b>Preconditions:</b>
	 *                 <ul>
	 *                 <li>The element does not already appear in the priority
	 *                 queue.</li>
	 *                 <li>The priority is non-negative.</li>
	 *                 </ul>
	 *
	 */
	// Thalia
	public void push(int priority, int element) {
		if (priority < 0) { //throw error if negative input
			throw new AssertionError("Priorities cannot be negative");
		}
		if (isPresent(element)) { //throw error if the element does not exist
			throw new AssertionError("Cannot have duplicate elements");
		}
		Pair<Integer, Integer> new_pair = new Pair<>(priority, element);
		heap.add(new_pair);
		location.put(new_pair.element, (heap.size() - 1));
		int index = heap.size() - 1;
		while(index >= 0) { //percolateUp until
			index = percolateUp(index);
		}

	}


	/**
	 * Remove the highest priority element <br>
	 * <br>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>The priority queue is non-empty.</li>
	 * </ul>
	 *
	 */

	// Jordan
	public void pop() {
		if (isEmpty()){
			throw new AssertionError("Empty queue. Cannot pop");
		}
		int tailIndex = heap.size() - 1;
		swap(ROOT_INDEX, tailIndex);
		heap.remove(tailIndex);
		pushDown(ROOT_INDEX);
	}

	/**
	 * Returns the highest priority in the queue
	 * 
	 * @return highest priority value <br>
	 *         <br>
	 *         <b>Preconditions:</b>
	 *         <ul>
	 *         <li>The priority queue is non-empty.</li>
	 *         </ul>
	 */
	// Jordan
	public int topPriority() {
		// TODO: Fill in
		if (isEmpty()){
			throw new AssertionError("Empty queue. No topPriority");
		}
		return heap.get(ROOT_INDEX).priority;
	}

	/**
	 * Returns the element with the highest priority
	 * 
	 * @return element with highest priority <br>
	 *         <br>
	 *         <b>Preconditions:</b>
	 *         <ul>
	 *         <li>The priority queue is non-empty.</li>
	 *         </ul>
	 */
	// Jordan
	public int topElement() {
		if (isEmpty()){
			throw new AssertionError("Empty queue. No topElement");
		}
		return heap.get(ROOT_INDEX).element;
	}

	/**
	 * Change the priority of an element already in the priority queue.
	 *
	 * @param newpriority the new priority
	 * @param element     element whose priority is to be changed <br>
	 *                    <br>
	 *                    <b>Preconditions:</b>
	 *                    <ul>
	 *                    <li>The element exists in the priority queue</li>
	 *                    <li>The new priority is non-negative</li>
	 *                    </ul>
	 */
	// Thalia
	public void changePriority(int newpriority, int element) {
		if (!isPresent(element)) {

			throw new AssertionError("Error: element missing");

			}

			if (isEmpty()){
				throw new AssertionError("Error: Empty queue.");
			}

		int index = location.get(element);
		heap.get(index).priority = newpriority;
		while (index > 0) {
			index = percolateUp(index);
		}
		pushDown(index);

	}

	/**
	 * Gets the priority of the element
	 *
	 * @param element the element whose priority is returned
	 * @return the priority value <br>
	 *         <br>
	 *         <b>Preconditions:</b>
	 *         <ul>
	 *         <li>The element exists in the priority queue</li>
	 *         </ul>
	 */
	// Thalia

	public int getPriority(int element) {
		if (!isPresent(element)) {
			throw new AssertionError("Error: element missing");
		}
		int index = location.get(element);
		return heap.get(index).priority;
	}


	/**
	 * Returns true if the priority queue contains no elements
	 * 
	 * @return true if the queue contains no elements, false otherwise
	 */
	// Jordan
	public boolean isEmpty() {
		if (heap.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns true if the element exists in the priority queue.
	 * 
	 * @return true if the element exists, false otherwise
	 */
	// Thalia
	public boolean isPresent(int element) {
		return location.containsKey(element);

	}

	/**
	 * Removes all elements from the priority queue
	 */
	// Thalia
	public void clear() {
		location.clear();
		heap.clear();
	}

	/**
	 * Returns the number of elements in the priority queue
	 * 
	 * @return number of elements in the priority queue
	 */
	// Jordan
	public int size() {
		return heap.size();
	}

	/*********************************************************
	 * Private helper methods
	 *********************************************************/

	/**
	 * Push down the element at the given position in the heap
	 * 
	 * @param start_index the index of the element to be pushed down
	 * @return the index in the list where the element is finally stored
	 */
	// Jordan
	private int pushDown(int start_index) {
		int startPriority = heap.get(start_index).priority;
		int left = left(start_index);
		int right = right(start_index);
		int child;

		if (left == -1 && right ==-1) {
			return start_index;
		}
		else if (left == -1) {
			child = right;
		}
		else if (right ==-1) {
			child = left;
		}
		else if (heap.get(left).priority >= heap.get(right).priority) {
			child = right;
		} 
		else {
			child = left;
		}
		if (startPriority > heap.get(child).priority) {
			swap(start_index, child);
			pushDown(child);
		} else {
			return start_index;
		}
		return 0;
	}

	/**
	 * Percolate up the element at the given position in the heap
	 * 
	 * @param start_index the index of the element to be percolated up
	 * @return the index in the list where the element is finally stored
	 */
	// Thalia
	private int percolateUp(int start_index) {
		if (start_index == 0) {
			return -1;
		}

		//put(key, value)
		// map element: key --> index in heap
		//System.out.println(start_index);
		int parentIndex = (start_index - 1)/2;
		if (heap.get(start_index).priority < heap.get(parentIndex).priority ) {
			swap(start_index, parentIndex);
			return parentIndex;
		}
		return -1;
	}

	/**
	 * Swaps two elements in the priority queue by updating BOTH the list
	 * representing the heap AND the map
	 * 
	 * @param i The index of the element to be swapped
	 * @param j The index of the element to be swapped
	 */
	// Thalia
	private void swap(int i, int j) {
		//set(int index, E element)
		Pair temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
		location.put(heap.get(i).element, i);
		location.put(heap.get(j).element, j);
				//add(int index, E element)
	}

	/**
	 * Computes the index of the element's left child
	 * 
	 * @param parent index of element in list
	 * @return index of element's left child in list
	 */
	// Jordan
	private int left(int parent) {
		int leftChildIndex = 2 * parent + 1;
		try {
			heap.get(leftChildIndex);
			return leftChildIndex;
		} catch (IndexOutOfBoundsException e) {
			return -1;
		}
	}

	/**
	 * Computes the index of the element's right child
	 * 
	 * @param parent index of element in list
	 * @return index of element's right child in list
	 */
	// Jordan
	private int right(int parent) {
		int rightChildIndex = 2 * parent + 2;
		try {
			heap.get(rightChildIndex);
			return rightChildIndex;
		} catch (IndexOutOfBoundsException e) {
			return -1;
		}
	}

	/**
	 * Computes the index of the element's parent
	 * 
	 * @param child index of element in list
	 * @return index of element's parent in list
	 */
	// Jordan
	private int parent(int child) {
		int parentIndex = (int) Math.floor((child - 1) / 2);
		try {
			return parentIndex;
		} catch (AssertionError e) {
			throw new AssertionError("This Pair does not have a parent!");
		}
	}

	/*********************************************************
	 * These are optional private methods that may be useful
	 *********************************************************/

	/**
	 * Push down the root element
	 * 
	 * @return the index in the list where the element is finally stored
	 */
	private int pushDownRoot() {
		// TODO: A one-line function that calls pushDown()
		return 0;
	}

	/**
	 * Percolate up the last leaf in the heap, i.e. the most recently added element
	 * which is stored in the last slot in the list
	 *
	 * @return the index in the list where the element is finally stored
	 */
	private int percolateUpLeaf() {
		// TODO: A one-line function that calls percolateUp()
		return 0;
	}

	/**
	 * Returns true if element is a leaf in the heap
	 * 
	 * @param i index of element in heap
	 * @return true if element is a leaf
	 */
	private boolean isLeaf(int i) {
		// TODO: Fill in
		return false;
	}

	/**
	 * Returns true if element has two children in the heap
	 * 
	 * @param i index of element in the heap
	 * @return true if element in heap has two children
	 */
	private boolean hasTwoChildren(int i) {
		// TODO: Fill in
		return false;
	}

	/**
	 * Print the underlying list representation
	 */
	private void printHeap() {
		for(int i = 0; i < heap.size(); i++) {
			System.out.print(heap.get(i).priority);
			System.out.print("\t");
			System.out.println(heap.get(i).element);
		}
		//return
		System.out.println(location.toString());
	}

	/**
	 * Print the entries in the location map
	 */
	private void printMap() {
		// TODO: Fill in
	}

}