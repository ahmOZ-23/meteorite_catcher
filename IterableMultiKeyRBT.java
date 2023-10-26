// --== CS400 Fall 2023 File Header Information ==--
// Name: Ahmed Osman
// Email: aaosman2@wisc.edu
// Group: C15
// TA: Alex Peseckis
// Lecturer: Florian Heimerl
// Notes to Grader: None
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

import static org.junit.Assert.assertEquals;


public class IterableMultiKeyRBT<T extends Comparable<T>> extends RedBlackTree<KeyListInterface<T>>
    implements IterableMultiKeySortedCollectionInterface<T>{

  Comparable<T> startPoint; // the starting point for iterations
  int numKeys; // the number of keys in the tree


  /**
   * Inserts value into tree that can store multiple objects per key by keeping lists of objects in
   * each node of the tree.
   *
   * @param key object to insert
   * @return true if a new node was inserted, false if the key was added into an existing node
   */
  @Override
  public boolean insertSingleKey(T key) {
    KeyListInterface<T> keyList = new KeyItem<>(key); // creating a new key list object to compare
   // checking if the key is already in the tree
    if (contains(keyList)) {
      KeyListInterface<T> node = findNode(keyList).data;
      node.addKey(key);
      numKeys++;
      return false;
    }
    // otherwise creating a new node
    else {
      boolean inserted = super.insert(keyList);
      if (inserted) numKeys++;
      return inserted;
    }

  }

  /**
   * @return the number of values in the tree.
   */
  @Override
  public int numKeys() {
    return numKeys;
  }

  /**
   * Returns an iterator that does an in-order iteration over the tree.
   */
  @Override
  public Iterator<T> iterator() {
    Stack<Node<KeyListInterface<T>>> stack = getStartStack();
    //returning an anonymous class that implements the iterator interface
    return new Iterator<>() {
      Iterator<T> iterator; // the iterator for the node at the top of the stack

      /**
       * checks if the iterator has a next node value
       * @return true if the iterator has a next node value
       */
      @Override
      public boolean hasNext() {
        return !stack.isEmpty();
      }

      /**
       * helper method that returns a key-list iterator for the node at the top of the stack
       * @return
       */
      private Iterator<T> getIterator() {
        Node<KeyListInterface<T>> node = stack.pop();
        if (node.down[1] != null) {
          Node<KeyListInterface<T>> pointer = node.down[1];
          while (pointer != null) {
            // adding all the left children of the node into the stack
            stack.push(pointer);
            pointer = pointer.down[0];
          }}
        return node.data.iterator();
      }

      /**
       * returns the next node value
       * @return the next node value
       * @throws NoSuchElementException if there is no next node value
       *
       * @return key-list value of the next node
       */
      @Override
      public T next() {
        // initializing the iterator if it is null
        if (iterator == null) {
          iterator = getIterator();
        }
        //checking if the iterator has a next value
        if (iterator.hasNext()) {
          return iterator.next();
        }
        // if the iterator does not have a next value then the next node is popped from the stack
        else {
          iterator = getIterator();
          return iterator.next();
        }

      }

    };

  }

  /**
   * Sets the starting point for iterations. Future iterations will start at the starting point or
   * the key closest to it in the tree. This setting is remembered until it is reset. Passing in
   * null disables the starting point.
   *
   * @param startPoint the start point to set for iterations
   */
  @Override
  public void setIterationStartPoint (Comparable<T> startPoint) {
    this.startPoint = startPoint;
  }

  /**
   *
   * Overridden insert to update the number of keys in the tree
   *
   * @param data the data to insert into the tree
   * @return true if the data was inserted, false if the data was already in the tree
   * @throws NullPointerException if data is null
   * @throws IllegalArgumentException if data is empty
   */
  @Override
  public boolean insert(KeyListInterface<T> data)
      throws NullPointerException, IllegalArgumentException {
    if (data == null) throw new NullPointerException("data cannot be null");
    if (!data.iterator().hasNext()) {
      throw new IllegalArgumentException("data must contain at least one key");
    }
    boolean inserted = super.insert(data);
    if (inserted){
      KeyItem<T> keyList = (KeyItem<T>) data;
      numKeys += keyList.keys.size();
    }
    return inserted;
  }



  /**
   *  gets the starter stack for the iterator method
   * @return the stack of nodes that the iterator will start at
   */
  protected Stack<Node<KeyListInterface<T>>> getStartStack() {
    Stack<Node<KeyListInterface<T>>> stack = new Stack<>(); // initializing the stack
    Node<KeyListInterface<T>> pointer = root; // the pointer node
    // if the started point is not given then the iterator will start at the left most node
    if (startPoint == null) {
      while (pointer != null) {
        stack.push(pointer);
        pointer = pointer.down[0];
      }
    }
    // otherwise the iterator will start at the node with the starting point
    else{
      // pushes all the nodes above the starting point into the stack (including the starting point if found)
      while (pointer != null){
        int compare = pointer.data.iterator().next().compareTo((T)startPoint);
        // terminating the loop if the starting point is found
        if (compare == 0) {
          stack.push(pointer);
          break;
        }
        // moving the pointer to  the right child if the starting point is greater than the node
        else if (compare < 0) {
          pointer = pointer.down[1];
        }
        // pushing the node into the stack and moving the pointer to the left child if the starting point is less than the node
        else {
          stack.push(pointer);
          pointer = pointer.down[0];
        }
      }
      }
    return stack;
  }



  /**
   *  Removes all the nodes from the tree.
   */
  @Override
  public void clear(){
    numKeys = 0;
    super.clear();
  }

  /**
   * Tests iterator() method by creating a tree and iterating over it.
   * then checking that the values are in the correct order.
   */
  @Test
  public void iteratorTest(){
      IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
        //populating the tree
        tree.insertSingleKey(3);
        tree.insertSingleKey(1);
        tree.insertSingleKey(2);
        tree.insertSingleKey(5);
        tree.insertSingleKey(4);

        Iterator<Integer> iterator = tree.iterator();

        // will be removed when this class is implemented
        String actual = "";
        //checking that the values are in the correct order
        while (iterator.hasNext()){
            actual += iterator.next();
        }
        String expected = "12345";
        Assertions.assertEquals(expected, actual);
  }

    /**
     * Tests setIterationStartPoint() method by creating a tree by setting a new iteration point
     * and iterating over it then checking that the values are in the correct order.
     */
    @Test
    public void setIterationStartPointTest(){
      // testing when the start point is found
      {
      IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
        //populating the tree
        tree.insertSingleKey(3);
        tree.insertSingleKey(1);
        tree.insertSingleKey(2);
        tree.insertSingleKey(5);
        tree.insertSingleKey(4);
        //setting the iteration point
       tree.setIterationStartPoint(3);

        Iterator<Integer> iterator = tree.iterator();

        //checking that the values are in the correct order
        String actual = "";
        while (iterator.hasNext()){
            actual += iterator.next();
        }
        String expected = "345";

        Assertions.assertEquals(expected, actual);
      }
      //testing when the start point is not found
      {
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
        //populating the tree
        tree.insertSingleKey(7);
        tree.insertSingleKey(2);
        tree.insertSingleKey(9);
        tree.insertSingleKey(4);
        //setting the iteration point
        tree.setIterationStartPoint(4);

        Iterator<Integer> iterator = tree.iterator();

        //checking that the values are in the correct order
        String actual = "";
        while (iterator.hasNext()){
          actual += iterator.next();
        }
        String expected = "479";

        Assertions.assertEquals(expected, actual);
      }
    }

  /**
   * tests numkeys() method by creating a tree and checking that the number of keys is correct.
   */
  @Test
    public void numKeysTest(){
    // case: when tree is empty
    {
      IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
        int actual = tree.numKeys();
        int expected = 0;
        Assertions.assertEquals(expected, actual);
    }
    // case: when tree is not empty
    {
      IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
        tree.insertSingleKey(1);
        tree.insertSingleKey(2);
        tree.insertSingleKey(3);
        tree.insertSingleKey(4);
        Assertions.assertFalse(tree.insertSingleKey(4)); // checking that a new node was not made
        // inserting a list of keys
        KeyListInterface<Integer> keyList = new KeyItem<>(5);
        keyList.addKey(5);
        keyList.addKey(5);

        for (int i: tree) {
            System.out.println(i);
        }

        tree.insert(keyList);
        int actual = tree.numKeys();
        int expected = 8;

        assertEquals(expected, actual); //checking the given number of keys
    }
    }

  /**
   * This test checks if the iterator() method works as intended. It adds nodes to a tree and then
   * calls iterator().next to get nodes in order.
   */
  @Test
  public void testIterator() {
    IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<Integer>(); //creating tree
    tree.insertSingleKey(18); //adding nodes
    tree.insertSingleKey(22);
    tree.insertSingleKey(28);
    tree.insertSingleKey(14);
    Iterator<Integer> iter = tree.iterator();
    if (tree.iterator().next() == null){
      Assertions.assertFalse(true);
    }else {
      // System.out.println(iter.next());
     // System.out.println(iter.next());
      //System.out.println(iter.next());
      //System.out.println(iter.next());
      Assertions.assertTrue(iter.next() == 14 && iter.next() == 18 && iter.next() == 22 && iter.next() == 28);  //checking if nodes

      //are produced in an in ordered way
    }

  }








}
