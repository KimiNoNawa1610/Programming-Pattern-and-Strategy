public class LinkedStack<T> {
	private static class Node<T> {
		public Node() {
			System.out.println("Constructing new Node");
		}
		public Node<T> mNext;
		public T mData;
	}
	
	private Node<T> mHead;
	private int mSize;
	
	// TODO: add a static Node object called mFreeHead.
	private static Node mFreeHead;
	// This factory method returns a Node from the free list if it is non-empty, and otherwise constructs a new
	// Node object on the heap.
	// Don't worry about the weird <T> syntax.
	private static <T> Node<T> createNode() {
                // TODO: at the moment, this method always constructs a new Node. Change it as described: if mFreeHead is not
		// null, unlink the first node in the free list and return that.
                if(mFreeHead!=null){
                   LinkedStack link=new LinkedStack();
                   link.pop();
                }

		return new Node<T>();
	}
	
	public void push(T data) {
		Node<T> newNode = createNode();
		newNode.mData = data;
		newNode.mNext = mHead;
		mHead = newNode;
		mSize++;
	}
	
	// TODO: write a private static void freeNode(Node<T> n) method, that takes n and adds it to the front of the free list.
	private static<T> void freeNode(Node<T> n){
            LinkedStack link=new LinkedStack();
            link.push(n);
            
        }
	public T pop() {
		T temp = mHead.mData;
		Node<T> newHead = mHead.mNext;
		
		// TODO: take the current head and free it with freeNode.
		
		mHead = newHead;

		mSize--;
		return temp;
	}
	
	public T peek() {
		return mHead.mData;
	}
	
	public static void main(String[] args) {
		LinkedStack<Integer> ints = new LinkedStack<>();
		ints.push(2);
		ints.push(7);
		ints.push(7);
		System.out.println(ints.pop());
		System.out.println(ints.pop());
		ints.push(2);
		
		LinkedStack<Integer> more = new LinkedStack<>();
		more.push(100);
		more.push(200);
	}
	
	
}
