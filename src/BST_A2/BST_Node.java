package BST_A2;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;

	BST_Node(String data) {
		this.data = data;
		
		// Initialize children specifically to null
		this.left = null;
		this.right = null;
	}

	// --- used for testing ----------------------------------------------
	//
	// leave these 3 methods in, as is

	public String getData() {
		return data;
	}

	public BST_Node getLeft() {
		return left;
	}

	public BST_Node getRight() {
		return right;
	}

	// --- end used for testing -------------------------------------------

	// --- fill in these methods ------------------------------------------
	//
	// at the moment, they are stubs returning false or some
	// appropriate "fake" value
	// you make them work properly
	// add the meat of correct implementation logic to them
	//
	// you may use recursive or iterative implementations

	public boolean containsNode(String s) {
		// Check if this Node contains s
		if (this.getData() == s)
			return true;
		// Check if the left child node exists and it contains s
		else if (this.getLeft() != null && s.compareTo(this.getData()) < 0)
			return this.getLeft().containsNode(s);
		// Check if the right child node exists and it contains s
		else if (this.getRight() != null && s.compareTo(this.getData()) > 0)
			return this.getRight().containsNode(s);
			
		// If recursion shows nothing contains s, return false
		return false;
	}

	public boolean insertNode(String s) {
		// Check if s fits left
		if (s.compareTo(this.getData()) < 0){
			// If left already exists, insert s into its tree
			if (this.getLeft() != null) 
				return this.getLeft().insertNode(s);
			// If it doesn't, insert s as new left Node
			else {
				this.left = new BST_Node(s);
				return true;
			}
		} 
		// Check if s fits right
		else if (s.compareTo(this.getData()) > 0) {
			// If right already exists, insert s into its tree 
			if (this.getRight() != null)
				return this.getRight().insertNode(s);
			// If it doesn't, insert s as new right Node
			else {
				this.right = new BST_Node(s);
				return true;
			}			
		}
		
		// This condition indicates that the valuation is the same as the current Node, in which case
		// the correct behavior is to simply return false
		return false;
	}

	public boolean removeNode(BST_Node parent, String s) {
		// Check if equivalent and is leaf
		if (s.equals(this.getData()) && this.getLeft() == null && this.getRight() == null) {
			if (parent.getLeft() != null && parent.getLeft().getData().equals(this.getData()))
				parent.left = null;
			else
				parent.right = null;
			
			// Return true to show success
			return true;
		}
		// Check if equivalent with only left child existent
		else if (s.equals(this.getData()) && this.getLeft() != null && this.getRight() == null) {
			// Figure out which parent child this is
			if (parent.getLeft() != null && parent.getLeft().getData().equals(this.getData()))
				parent.left = this.left;
			else
				parent.right = this.left;
			
			// Return true to show success
			return true;
		}
		// Check if equivalent with only right child existent
		else if (s.equals(this.getData()) && this.getRight() != null && this.getLeft() == null) {
			// Figure out which parent child this is
			if (parent.getLeft() != null && parent.getLeft().getData().equals(this.getData()))
				parent.left = this.right;
			else
				parent.right = this.right;
			
			// Return true to show success
			return true;
		}
		// What to do if both children exist
		else if (s.equals(this.getData()) && this.getRight() != null && this.getLeft() != null) {
			// Find minimum in right subtree
			String min = this.right.findMin().getData();
			this.data = min;
			// Remove same value from right subtree
			return this.right.removeNode(this, min);
		}
		// What to do if not equivalent and before
		else if (s.compareTo(this.getData()) < 0)
			// Run remove recursive on left subtree
			return this.left.removeNode(this, s);
		// What to do if not equivalent and after
		else if (s.compareTo(this.getData()) > 0) 
			return this.right.removeNode(this, s);
		
		// Default return false
		return false;
	}

	public BST_Node findMin() {
		// Check if there exists a child Node further left than this one
		if (this.getLeft() != null)
			return this.getLeft().findMin();
		else
			// If there doesn't and we're already here, this is the left-most Node and must be returned
			return this;
	}

	public BST_Node findMax() {
		// Check if there exists a child Node further right than this one
		if (this.getRight() != null) 
			return this.getRight().findMax();
		else
			// If there doesn't and we're already here, this is the right-most Node and must be returned
			return this;	
	}

	public int getHeight() {
		// Check if this node has children
		if (this.getLeft() == null && this.getRight() == null)
			// Its own height is only one
			return 1;
		else if (this.getLeft() == null && this.getRight() != null) 
			// If it only has a right child, just return the height of said child plus one
			return this.getRight().getHeight() + 1;
		else if (this.getRight() == null && this.getLeft() != null)
			// if it only has a left child, just return the height of said child plus one
			return this.getLeft().getHeight() + 1;
		else {
			// If they both exist, compare the two and return the larger plus one
			if (this.getRight().getHeight() >= this.getLeft().getHeight())
				return this.getRight().getHeight() + 1;
			else
				return this.getLeft().getHeight() + 1;
		}
	}

	// --- end fill in these methods --------------------------------------

	// --------------------------------------------------------------------
	// you may add any other methods you want to get the job done
	// --------------------------------------------------------------------

	public String toString() {
		return "Data: " + this.data + ", Left: " + ((this.left != null) ? left.data : "null") + ",Right: "
				+ ((this.right != null) ? right.data : "null");
	}
}