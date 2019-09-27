import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A priority queue class implemented using a min heap.
 * Priorities cannot be negative.
 *
 * @author Jordan Pearson and Thalia
 * @version Date
 *
 */
public class PriorityQueue {

	protected Map<Integer, Integer> location;
	protected List<Pair<Integer, Integer>> heap;
	
	private static final int ROOT_INDEX = 0;

	/**
	 *  Constructs an empty priority queue
	 */
	public PriorityQueue() {
		heap = new ArrayList<Pair<Integer, Integer>>();
		location = new HashMap<Integer, Integer>();
	}
	//Jordan
	public static void main(String[] args) {
		
	}

	/**
	 *  Insert a new element into the queue with the
	 *  given priority.
	 *
	 *	@param priority priority of element to be inserted
	 *	@param element element to be inserted
	 *	<br><br>
	 *	<b>Preconditions:</b>
	 *	<ul>
	 *	<li> The element does not already appear in the priority queue.</li>
	 *	<li> The priority is non-negative.</li>
	 *	</ul>
	 *
	 */
	//Thalia
	public void push(int priority, int element) {
		// TODO: Fill in
	}

	/**
	 *  Remove the highest priority element
	 *  <br><br>
	 *	<b>Preconditions:</b>
	 *	<ul>
	 *	<li> The priority queue is non-empty.</li>
	 *	</ul>
	 *
	 */
	//Jordan
	public void pop(){
		int tailIndex = heap.size()-1;
		swap(ROOT_INDEX, tailIndex);
		heap.remove(tailIndex);
		pushDown(ROOT_INDEX);
	}


	/**
	 *  Returns the highest priority in the queue
	 *  @return highest priority value
	 *  <br><br>
	 *	<b>Preconditions:</b>
	 *	<ul>
	 *	<li> The priority queue is non-empty.</li>
	 *	</ul>
	 */
	//Jordan
	public int topPriority() {
		// TODO: Fill in
		return heap.get(ROOT_INDEX).priority;
	}


	/**
	 *  Returns the element with the highest priority
	 *  @return element with highest priority
	 *  <br><br>
	 *	<b>Preconditions:</b>
	 *	<ul>
	 *	<li> The priority queue is non-empty.</li>
	 *	</ul>
	 */
	//Jordan
	public int topElement() {
		return heap.get(ROOT_INDEX).element;
	}


	/**
	 *  Change the priority of an element already in the
	 *  priority queue.
	 *
	 *  @param newpriority the new priority
	 *  @param element element whose priority is to be changed
	 *  <br><br>
	 *	<b>Preconditions:</b>
	 *	<ul>
	 *	<li> The element exists in the priority queue</li>
	 *	<li> The new priority is non-negative </li>
	 *	</ul>
	 */
	//Thalia
	public void changePriority(int newpriority, int element) {
		// TODO: Fill in
	}


	/**
	 *  Gets the priority of the element
	 *
	 *  @param element the element whose priority is returned
	 *  @return the priority value
	 *  <br><br>
	 *	<b>Preconditions:</b>
	 *	<ul>
	 *	<li> The element exists in the priority queue</li>
	 *	</ul>
	 */
	//Thalia
	public int getPriority(int element) {
		// TODO: Fill in
		return 0;
	}

	/**
	 *  Returns true if the priority queue contains no elements
	 *  @return true if the queue contains no elements, false otherwise
	 */
	//Jordan
	public boolean isEmpty() {
		if (heap.size() == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 *  Returns true if the element exists in the priority queue.
	 *  @return true if the element exists, false otherwise
	 */
	//Jordan
	public boolean isPresent(int element) {
		// TODO: Fill in
		return false;
	}

	/**
	 *  Removes all elements from the priority queue
	 */
	//Jordan
	public void clear() {
		// TODO: Fill in
	}

	/**
	 *  Returns the number of elements in the priority queue
	 *  @return number of elements in the priority queue
	 */
	//Jordan
	public int size() {
		return heap.size();
	}



	/*********************************************************
	 * 				Private helper methods
	 *********************************************************/


	/**
	 * Push down the element at the given position in the heap
	 * @param start_index the index of the element to be pushed down
	 * @return the index in the list where the element is finally stored
	 */
	//Jordan
	private int pushDown(int start_index) {
		int startPriority = heap.get(start_index).priority;
		Pair<Integer, Integer> left = heap.get(left(start_index));
		Pair<Integer, Integer> right = heap.get(right(start_index));
		Pair<Integer, Integer> child;
		int childIndex;
		if (left.priority >= right.priority) {
			child = left;
			childIndex = left(start_index);
		}
		else {
			child = right;
			childIndex = right(start_index);
		}
		if (startPriority >= child.priority) {
			return start_index;
		}
		else {
			swap(start_index, childIndex);
			pushDown(childIndex);
		}
		return 0;
	}

	/**
	 * Percolate up the element at the given position in the heap
	 * @param start_index the index of the element to be percolated up
	 * @return the index in the list where the element is finally stored
	 */
	//Thalia
	private int percolateUp(int start_index) {
		return 0;
	}


	/**
	 * Swaps two elements in the priority queue by updating BOTH
	 * the list representing the heap AND the map
	 * @param i The index of the element to be swapped
	 * @param j The index of the element to be swapped
	 */
	//Thalia
	private void swap(int i, int j) {
		// TODO: Fill in
	}

	/**
	 * Computes the index of the element's left child
	 * @param parent index of element in list
	 * @return index of element's left child in list
	 */
	//Jodan
	private int left(int parent) {
		int leftChildIndex = 2*parent+1;
		if (heap.get(leftChildIndex) != null) {
			return leftChildIndex;
		}
		else {
			throw new AssertionError("This Pair does not have a left child!");
		}
	}

	/**
	 * Computes the index of the element's right child
	 * @param parent index of element in list
	 * @return index of element's right child in list
	 */
	//Jordan
	private int right(int parent) {
		int rightChildIndex = 2*parent+2;
		if (heap.get(rightChildIndex) != null) {
			return rightChildIndex;
		}
		else {
			throw new AssertionError("This Pair does not have a right child!");
		}
	}

	/**
	 * Computes the index of the element's parent
	 * @param child index of element in list
	 * @return index of element's parent in list
	 */
	//Jordan
	private int parent(int child) {
		int parentIndex = (int) Math.floor((child-1)/2);
		if (heap.get(parentIndex) != null) {
			return parentIndex;
		}
		else {
			throw new AssertionError("This Pair does not have a parent!");
		}
	}


	/*********************************************************
	 * 	These are optional private methods that may be useful
	 *********************************************************/


	/**
	 * Push down the root element
	 * @return the index in the list where the element is finally stored
	 */
	private int pushDownRoot() {
		// TODO: A one-line function that calls pushDown()
		return 0;
	}

	/**
	 * Percolate up the last leaf in the heap, i.e. the most recently
	 * added element which is stored in the last slot in the list
	 *
	 * @return the index in the list where the element is finally stored
	 */
	private int percolateUpLeaf(){
		// TODO: A one-line function that calls percolateUp()
		return 0;
	}

	/**
	 * Returns true if element is a leaf in the heap
	 * @param i index of element in heap
	 * @return true if element is a leaf
	 */
	private boolean isLeaf(int i){
		// TODO: Fill in
		return false;
	}

	/**
	 * Returns true if element has two children in the heap
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
		// TODO: Fill in
	}

	/**
	 * Print the entries in the location map
	 */
	private void printMap() {
		// TODO: Fill in
	}


}