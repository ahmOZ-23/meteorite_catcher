// --== CS400 Fall 2023 File Header Information ==--
// Name: Ahmed Osman
// Email: aaosman2@wisc.edu
// Group: C15
// TA: Alex Peseckis
// Lecturer: Florian Heimer
// Notes to Grader: None

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Red-Black Tree implementation with a RBTNode inner class for representing the nodes of the tree,
 * they extend BinarySearchTree and Node respectively,
 */
public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {
  protected static class RBTNode<T> extends Node<T> {
    public int blackHeight = 0;
    public RBTNode(T data) { super(data); }
    public RBTNode<T> getUp() { return (RBTNode<T>)this.up; }
    public RBTNode<T> getDownLeft() { return (RBTNode<T>)this.down[0]; }
    public RBTNode<T> getDownRight() { return (RBTNode<T>)this.down[1]; }
  }

  /**
   * Checks if the tree satisfies Red-Black tree properties after insertion.
   * By checking if a newly inserted node's parent is red and rectifying it if so
   *  and resolving recursively if needed
   *
   * @param node the node that was inserted
   */
  protected void enforceRBTreePropertiesAfterInsert(RBTNode<T> node){
    // base cases (exits the method):
    // 1) the node passed is the root node;
    // 2) the node's parent is the root node;
    // 3) the node's parent is black.
    if (node == root || node.getUp() == root || node.getUp().blackHeight == 1) return;

    // storing the parent, grandParent and aunt node's in variables for easy access
    RBTNode<T> parent = node.getUp();
    RBTNode<T> grandParent = parent.getUp();
    // uses ternary operator to find if aunt node is the right or left child to grandparent
    RBTNode<T> aunt = parent.isRightChild()? grandParent.getDownLeft():grandParent.getDownRight();

    // recursive case: aunt node is null (i.e. black) or aunt node is black
    if(aunt == null|| aunt.blackHeight == 1){

      // checks if the node is on the same side as the parent; this will require one rotation
      if (parent.isRightChild() == node.isRightChild()) {
        this.rotate(parent, grandParent);
        grandParent.blackHeight = 0;
        parent.blackHeight = 1;
      }else{
        // otherwise two rotations are required
        this.rotate(node, parent);
        this.rotate(node, grandParent);
        //recoloring
        grandParent.blackHeight = 0;
        node.blackHeight = 1;
      }
    }
    //recursive case: aunt node is red
    else if (aunt.blackHeight == 0){
      //recoloring
      parent.blackHeight = 1;
      aunt.blackHeight = 1;
      grandParent.blackHeight = 0;
      //resolving recursively after recoloring
      enforceRBTreePropertiesAfterInsert(grandParent);
    }

  }

  /**
   * Inserts a new data value into the tree. This tree will not hold null references, nor duplicate
   * data values.
   *
   * @param data to be added into this Red-Black tree
   * @return true if the value was inserted, false if it's in the tree already
   * @throws NullPointerException when the provided data argument is null
   */
  @Override
  public boolean insert(T data) throws NullPointerException {
    //checks if data passed is null
    if (data == null)
      throw new NullPointerException("Cannot insert data value null into the tree.");

    //creates a new node and stores it in a variable to be passed into insertHelper()
    RBTNode<T> newNode = new RBTNode<>(data);
    boolean isInserted = this.insertHelper(newNode);
    //the tree is checked RBT properties are checked after a successful insertion
    if (isInserted) enforceRBTreePropertiesAfterInsert(newNode);
    //down-casting the root node into an RBTNode to get access to the blackHeight variable
    RBTNode<T> root = (RBTNode<T>) this.root;
    root.blackHeight = 1;

    return isInserted;

  }

  /**
   * Tests the enforceRBTreePropertiesAfterInsert method when inserted node's aunt node is red
   * and checks if the RBT is valid after a correction
   */
  @Test
  public void testA() {
    Integer[] data = new Integer[]{5,2,7};
    RedBlackTree<Integer> tree = new RedBlackTree<>();
    for (Integer val : data) tree.insert(val);
    // inserting the key that causes the RBtree to be invalid
    tree.insert(6);
    //checks the color of all the keys to see if the correction was successful
    RBTNode<Integer> node  = (RBTNode<Integer>) tree.findNode(6);
    Assertions.assertEquals(node.blackHeight, 0);
    node = (RBTNode<Integer>) tree.findNode(7);
    Assertions.assertEquals(node.blackHeight, 1);
    node = (RBTNode<Integer>) tree.findNode(2);
    Assertions.assertEquals(node.blackHeight, 1);
    node = (RBTNode<Integer>) tree.findNode(5);
    Assertions.assertEquals(node.blackHeight, 1);
  }
  /**
   * Tests the enforceRBTreePropertiesAfterInsert method when inserted node's aunt node is black
   * and checks if the RBT is valid after a correction
   */
  @Test
  public void testB (){
    Integer[] data = new Integer[]{5,2,7,9};
    RedBlackTree<Integer> tree = new RedBlackTree<>();
    for (Integer val : data) tree.insert(val);
    // inserting the key that causes the RBtree to be invalid
    tree.insert(8);
    //checks the color of all the keys and the level order to see if the correction was successful
    RBTNode<Integer> node  = (RBTNode<Integer>) tree.findNode(8);
    Assertions.assertEquals(node.blackHeight, 1);
    node = (RBTNode<Integer>) tree.findNode(7);
    Assertions.assertEquals(node.blackHeight, 0);
    node = (RBTNode<Integer>) tree.findNode(9);
    Assertions.assertEquals(node.blackHeight, 0);
    node = (RBTNode<Integer>) tree.findNode(2);
    Assertions.assertEquals(node.blackHeight, 1);
    node = (RBTNode<Integer>) tree.findNode(5);
    Assertions.assertEquals(node.blackHeight, 1);
    Assertions.assertEquals(tree.toLevelOrderString(), "[ 5, 2, 8, 7, 9 ]");
  }

  /**
   * Tests the enforceRBTreePropertiesAfterInsert method when inserted node's aunt node is red, and it
   * is resolved recursively and checks if the RBT is valid after correction of the tree using both color swap and
   * rotate corrections algorithms
   */
  @Test
  public void testC(){
    Integer[] data = new Integer[]{14,7,20,1,11,18,25,23,29};
    RedBlackTree<Integer> tree = new RedBlackTree<>();
    for (Integer val : data) tree.insert(val);
    // inserting the key that causes the RBtree to be invalid
    tree.insert(27);
    //checks the color of the keys that have been changed
    // and the level order to see if the correction was successful
    RBTNode<Integer> node  = (RBTNode<Integer>) tree.findNode(27);
    Assertions.assertEquals(node.blackHeight, 0);
    node = (RBTNode<Integer>) tree.findNode(20);
    Assertions.assertEquals(node.blackHeight, 1);
    Assertions.assertEquals(tree.root, node);
    node = (RBTNode<Integer>) tree.findNode(14);
    Assertions.assertEquals(node.blackHeight, 0);
    node = (RBTNode<Integer>) tree.findNode(23);
    Assertions.assertEquals(node.blackHeight, 1);
    node = (RBTNode<Integer>) tree.findNode(29);
    Assertions.assertEquals(node.blackHeight, 1);
    Assertions.assertEquals(tree.toLevelOrderString(), "[ 20, 14, 25, 7, 18, 23, 29, 1, 11, 27 ]");
  }










}
