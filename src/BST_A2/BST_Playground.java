package BST_A2;

public class BST_Playground {
	/*
	 * you will test your own BST implementation in here
	 *
	 * we will replace this with our own when grading, and will do what you
	 * should do in here... create BST objects, put data into them, take data
	 * out, look for values stored in it, checking size and height, and looking
	 * at the BST_Nodes to see if they are all linked up correctly for a BST
	 * 
	 */

	public static void main(String[] args) {

		// you should test your BST implementation in here
		// it is up to you to test it thoroughly and make sure
		// the methods behave as requested above in the interface

		// do not simple depend on the oracle test we will give
		// use the oracle tests as a way of checking AFTER you have done
		// your own testing

		// one thing you might find useful for debugging is a print tree method
		// feel free to use the printLevelOrder method to verify your trees
		// manually
		// or write one you like better
		// you may wish to print not only the node value, and indicators of what
		// nodes are the left and right subtree roots,
		// but also which node is the parent of the current node

		// Build a basic tree, loud the first time to demonstrate functionality
		BST test = buildTree();
		// Print out the tree and info about it
		printLevelOrder(test);
		System.out.println("Size: " + test.size + ", Height: " + test.height());

		// Test removing the root
		printRemove(test, "f");
		printLevelOrder(test);
		
		// Rebuild the tree
		test = buildTreeSilent();
		
		// Active tests that affect the tree and require rebuild
		// TODO: test most remove cases
		// Setup the tree to be remove-capable
		test.insert("e");
		test.insert("i");
		test.insert("y");
		System.out.println("\n\nTesting remove");
		System.out.println("This is the initial tree");
		printLevelOrder(test);
		// First test
		System.out.println();
		System.out.println(test.remove("l"));
		printLevelOrder(test);
		// Second test
		System.out.println("\nThis should return false in order to indicate this doesn't exist and can't be removed.");
		System.out.println(test.remove("m"));
		printLevelOrder(test);
		// Third test
		System.out.println("\nThis should return true and remove z and y");
		System.out.println(test.remove("z"));
		System.out.println(test.remove("y"));
		printLevelOrder(test);
		// Fourth test
		System.out.println("\nTest removing basic leaves, so a and e, hopefully both true");
		System.out.println(test.remove("a"));
		System.out.println(test.remove("e"));
		printLevelOrder(test);
		

		// These are the static tests that don't affect the tree
		// Rebuild the tree clean prior to the passive tests
		test = buildTreeSilent();
		// TODO: test contains for all contents of tree
		// 		These should be the trues
		System.out.println("\n\nTesting contains");
		System.out.println("These should all return true");
		System.out.println(test.contains("f"));
		System.out.println(test.contains("d"));
		System.out.println(test.contains("h"));
		System.out.println(test.contains("a"));
		System.out.println(test.contains("l"));
		System.out.println(test.contains("z"));
		System.out.println("These should all return false");
		System.out.println(test.contains("t"));
		System.out.println(test.contains("q"));
		// TODO: test after using a remove
		
		// TODO: test findMin and findMax
		System.out.println("\n\nTesting findMin and findMax");
		System.out.println("This should return a");
		System.out.println(test.findMin());
		System.out.println("This should return z");
		System.out.println(test.findMax());
		
		// TODO: test height
		test = buildTreeSilent();
		System.out.println("\n\nTesting height");
		System.out.println("This should return 3");
		System.out.println(test.height());
		test.insert("y");
		System.out.println("It should now be one higher");
		System.out.println(test.height());
		
		
		
		// TODO: test empty
		test = buildTreeSilent();
		System.out.println("\n\nTesting empty");
		System.out.println("This should return false, it's the full BST");
		System.out.println(test.empty());
		test = new BST();
		System.out.println("This should return true, it's brand spanking new");
		System.out.println(test.empty());
		test.insert("a");
		test.remove("a");
		System.out.println("This should return false, it's been cleared out");
		System.out.println(test.empty());
		test.insert("a");
		System.out.println("Tree is no longer empty, and so it must be false.");
		System.out.println(test.empty());
	}

	static void printLevelOrder(BST tree) {
		// will print your current tree in Level-Order...
		// https://en.wikipedia.org/wiki/Tree_traversal
		int h = tree.getRoot().getHeight();
		for (int i = 0; i <= h; i++) {
			printGivenLevel(tree.getRoot(), i);
			System.out.print(" - ");
		}

	}

	static void printGivenLevel(BST_Node root, int level) {
		if (root == null)
			return;
		if (level == 0)
			System.out.print(root.data + " ");
		else if (level > 0) {
			printGivenLevel(root.left, level - 1);
			printGivenLevel(root.right, level - 1);
		}
	}
	
	static BST buildTree() {
		// Builds the tree to be used as static in all of the tests
		BST test = new BST();
		printInsert(test, "f");
		printInsert(test, "d");
		printInsert(test, "h");
		printInsert(test, "a");
		printInsert(test, "l");
		printInsert(test, "z");
		
		return test;
	}
	
	static BST buildTreeSilent() {
		// Build the tree, but without print output
		BST test = new BST();
		test.insert( "f");
		test.insert("d");
		test.insert("h");
		test.insert("a");
		test.insert("l");
		test.insert("z");
		
		return test;
	}

	static void printInsert(BST tree, String data) {
		// Inserts node and gives various feedback datum
		System.out.println("Inserting " + data + "...");
		System.out.println(tree.insert(data));
		System.out.println("The tree's size is now: " + tree.size + "\n");
	}

	static void printRemove(BST tree, String data) {
		// Remove node and gives various feedback
		System.out.println("Removing " + data + "...");
		System.out.println(tree.remove(data));
		System.out.println("The tree's size is now: " + tree.size + "\n");
	}
}