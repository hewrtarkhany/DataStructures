public class CDoublyLinkedList {

	private class Node {
		private Object data;   //Assume data implemented Comparable
		private Node next, prev;
		private Node(Object data, Node pref, Node next)
		{
			this.data = data;
			this.prev = pref;
			this.next = next;
		}
	}

	private Node head;
	private int size;

	public CDoublyLinkedList() {
		this.head = new Node(null, null, null );
		this.head.next = this.head;
		this.head.prev=this.head;
		this.size = 0;
	}

	public boolean isEmpty() {
		return this.head == this.head.next;
	} 
	
	// Add Object data to start of this LinkedList
	// Please DO NOT change this addFirst() method.
	// You must keep and include this provided addFirst() method
	//      in your source code.
	public void addFirst(Object data) {
		Node nn = new Node(data, this.head, this.head.next);
		this.head.next.prev = nn;
		this.head.next = nn;
		this.size ++;
	}

	// write a method void addLast(Object data) that will insert 
	// the piece of data at the end of the current list.
	// Note: this list is allowed to store null data element in its list node.

	public void addLast(Object data) {
		Node nn = new Node(data,this.head,this.head.next);

		nn.next=this.head;
		nn.prev=this.head.prev;
		this.head.prev.next=nn;
		this.head.prev=nn;

		this.size ++;

		}
	
	// Write the subListOfSmallerValues method.  
	// It should return a CDoublyLinkedList object 
	//     containing data that is smaller than the value passed to the method.
        // If a null data element in this list is encountered, you can skip it.
        // You need to use the CompareTo() method to determine which object is smaller.
        // If list A contains {9, 11, 14, 6, 4, 7} and you call  A.subListOfSmallerValues(10),
        // the method call returns a list that contains data in A that is smaller than 10, the passed-in argument.
        // That is, the returned list contains { 9, 6, 4, 7}.
	public CDoublyLinkedList subListOfSmallerValues(Comparable data) {

		if(this.size <=0){
			return this;
		}
		
		CDoublyLinkedList newList  = new CDoublyLinkedList ();
		
		//Node nn = new Node (data,this.head.prev,this.head.next);
		Node cur = this.head.next;
		while(cur!=this.head)
		{
			if(((Comparable) cur.data).compareTo(data)<0){
				newList.addLast(cur.data);
			}
				cur=cur.next;

			}
	
		return newList; //change this as needed.
	}
	
	// This method should remove the first occurrence of the data from the list, 
        //      starting at the *BACK* of the list. 
        // It scans the list from back to the front by following the prev links. 
	// The method should return true if successful, false otherwise. 
	// Note that list node may contain null data element. Please handle this edge case.

	public boolean removeStartingAtBack(Object dataToRemove) {
		Node cur =this.head.prev,pref=this.head;

		while(cur!=this.head)
		{
				if(cur.data==null && dataToRemove==null)
				{
					pref.prev=cur.prev;
					cur.prev.next=cur.next;

					// cur.next.prev=cur.prev;
					// cur.next=cur.prev.next;
					this.size--;
					return true;
				}
				else if(cur.data!=null &&cur.data.equals(dataToRemove))
				{
					pref.prev=cur.prev;
					cur.prev.next=cur.next;
					//cur.prev=cur.next.prev;
					//cur.next=cur.prev.next;
					this.size--;
					return true;
				}
				pref= cur;
				cur=cur.prev;
			}
			return false;
	}
		
	
	// Returns the index of the last occurrence of the specified element in this list, 
	//     or -1 if this list does not contain the element. 
	// More formally, returns the highest index i 
	//     such that (o==null ? get(i)==null : o.equals(get(i))), 
	//     or -1 if there is no such index.
	// Note: a list node may store a null data element. Please handle this edge case.
	public int lastIndexOf(Object o) {

		Node cur = this.head.next; 
		int x=-1;
    for(int i=0;i<this.size;i++){
	
		if ( cur.data==null && o==null)
		{	
			cur=cur.next;
		  	x=i;
		}
		if (cur.data!=null && cur.data.equals(o)){
			cur=cur.next;
    			x= i ;
		}
		cur=cur.next;
	    }
		return x;

	}

	// Removes from this list all of its elements that 
	//    are NOT contained in the specified linkedlist other.
	// If any element has been removed from this list,
	//    returns true. Otherwise returns false.
	// If other list is null, throws NullPointerException.
		// Helper methods are allowed.
		
	public boolean retainAll(CDoublyLinkedList other) throws NullPointerException {
		if (other.head == null )
		{
			throw new NullPointerException("null list");
		}
		boolean res = false;
		Node cur= this.head.next,pref=this.head,cur2=other.head.next,pref2=other.head;
		while (cur!=this.head)
		{
			boolean keep = false;

			while (cur2!=other.head){
				if(cur.data.equals(cur2.data))

					keep=true;

				pref2=cur2;
				cur2=cur2.next;
			}
			if (keep)
			{
				// cut
				cur.next.prev=cur.prev;
				cur.prev.next=cur.next;
				res=true;	
			}

			pref=cur;
			cur=cur.next;
		
	}
	    return res; //change this as needed.
	}
        // Write this method to sort this list using insertion sort algorithm, 
        //      as we have learned in the classroom.
	public void insertionSort() {
		Node sorted,sortedwalker;
		Object unsorted;
		for (sorted=this.head.next;sorted!=this.head.prev;sorted=sorted.next){
			unsorted=sorted.data;
			for (sortedwalker=sorted; sortedwalker!=this.head && ((Comparable)sortedwalker.data).compareTo(unsorted) > 0; sortedwalker=sortedwalker.prev){
				
					sortedwalker.next.data=sortedwalker.data;
			}


		}
	}
	@Override
	public String toString() {
		String result = "{";
	    for (Node node = this.head.next; node != this.head; node = node.next) {
	    		if(node.next != this.head)
	    			result += node.data + "->"; 
	    		else
	    			result += node.data;
	    }
	    return result + "}";
	  }
}
			