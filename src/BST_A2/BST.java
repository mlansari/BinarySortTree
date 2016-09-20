package BST_A2;

public class BST implements BST_Interface {
	public BST_Node root;
	int size;

	public BST() {
		size = 0;
		root = null;
	}

	@Override
	// used for testing, please leave as is
	public BST_Node getRoot() {
		return root;
	}

	/**
	 * **********************************************************************
	 * WORK GOES AFTER THIS (MOSTLY) ****************************************
	 * **********************************************************************
	 */
	
	@Override
	public boolean insert(String s) {
		// Create variable to capture result
		boolean backVal = false;
		
		// Check if the root exists
		if (root != null)
			backVal = this.root.insertNode(s);
		// If it doesn't exist, create it and return true
		else {
			this.root = new BST_Node(s);
			backVal = true;
		}
		
		// Change size depending on backVal
		this.size = backVal ? this.size + 1 : this.size;
		
		// Return backVal value
		return backVal;
	}

	@Override
	public boolean remove(String s) {
		// Check if root exists, if not return false
		if (this.root == null)
			return false;
		// Check if the root is what needs to be removed
		else if (this.root.getData().equals(s)) {
			// Create temp root to use remove operations, like a head similar to for a LinkedList, 
			// but temporarily
			BST_Node temp = new BST_Node(null);
			temp.left = this.root;
			boolean success = this.root.removeNode(temp, s);
			// Swap changed root back in
			this.root = temp.getLeft();
			// Check if the remove was successful
			this.size = success ? this.size - 1 : this.size;
			// Return success value
			return success;
		}
		// Otherwise
		else {
			boolean success = this.root.removeNode(null, s);
			// Check if the remove was successful
			this.size = success ? this.size - 1 : this.size;
			// Return success value
			return success;
		}
	}

	@Override
	public String findMin() {
		// Check if there exists a root for the tree
		if (this.root == null) 
			return null;
		// Otherwise traverse from root and return result
		else
			return this.root.findMin().getData();
	}

	@Override
	public String findMax() {
		// Check if there exists a root for the tree
		if (this.root == null)
			return null;
		// Otherwise traverse tree recursively from root and return result
		else
			return this.root.findMax().getData();			
	}

	@Override
	public boolean empty() {
		// Check if the height of the tree is zero
		if (this.height() == 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean contains(String s) {
		// Check containing from the root, and return the result
		return this.root.containsNode(s);
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public int height() {
		// Check if the root doesn't exist
		if (this.root == null) 
			return 0;
		// If it does exist, return the recursively found height
		else 
			return this.root.getHeight() - 1;		// Subtract one because height is not the amount of nodes, but
													// but the amount of connections
													// Basically, BECAUSE I'M STUPID
	}

}