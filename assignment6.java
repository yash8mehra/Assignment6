// Java Program to Implement a Generic Binary Tree

// Importing all input output classes
import java.io.*;
import java.util.Scanner;
import java.lang.Math; // we need this for max and min functions.

// BinaryTree Class: ( Generic BinaryTree class)
//                   T extends Comparable<T> to allow the use of compareTo() to compare element generically !!
class BinaryTree<T extends Comparable<T>> { 

  // Encapsulation: Node is a private nested class to hide implementation details.
  private static class Node<T> {
    T element;     // Data element to sore an item
    Node<T> left;  // a reference to the left subtree
    Node<T> right; // a reference to the right subtree

    // Parameterized constructor to assign a value
    Node(T item) {
      this.element = item; // "this" refers to current object itself
      this.right = null;
      this.left = null;
    }
  }

  // Generic Node instance in the Generic BinaryTree Class
  private Node<T> root;

  BinaryTree(){ // Default constructor
    this.root = null; // a reference to the root node
  } 

  // ********************************** isEmpty() **********************************
  public boolean isEmpty(){ // a method to check if the tree is empty
    return (this.root == null);
  }

  // ********************************** size() **********************************
  //
  // STUDENTS MUST IMPLEMENT THIS METHOD!!!!
  public int size(){ // a method to return the number of elements in the tree
    return size(root);
  }
  private int size(Node<T> node){
    if (node == null) {return 0;}
    else {
        return 1 + size(node.left) + size(node.right);
    }
  }

  // ********************************** height() **********************************
  //
  // this is a public method that delegates the height calculation to a private, recursive method
  public int height(){
    return heightRecursively(this.root);
  }
 
  // a private method to recursively calculate the height 
  private int heightRecursively(Node<T> root){
    int h = -1;
    if(root!=null)
      h = Math.max(heightRecursively(root.left),heightRecursively(root.right)) + 1;
    return h;
  }

  // ********************************** insert() **********************************
  //
  // this is a public method that delegates the insertion process to a private, recursive method
  public void insert(T item){ 

    this.root = insertRecursively(this.root, item);
  }

  // a private method to recursively place the item in the tree
  private Node<T> insertRecursively(Node<T> root, T item){

    if(root == null){
      root = new Node<T>(item);
    }else{
      int r = item.compareTo(root.element);
      if(r < 0){
        root.left = insertRecursively(root.left, item);
      }else if(r > 0){
        root.right = insertRecursively(root.right, item);
      }else{
        System.out.print(item+" already exists in the tree, no duplicates allowed!!\n");
      }
    }
    return root;
  }

  // ********************************** displayAll() **********************************

  // this is a public method that delegates the display process to a private, recursive method
  public void displayAll(){ 
    System.out.println("displayAll():");
    if (this.isEmpty()){
      System.out.println("The tree is empty ...! ");
    }else{
      System.out.println("____________Tree of "+this.size()+" items________________");
      showInorder(this.root);
    }
    System.out.println();
  }

  // a private method to recursively display items in the tree inOrder
  private void showInorder(Node<T> root){
    if(root!=null){
      showInorder(root.left);
      System.out.print(root.element+" ");
      showInorder(root.right);
    }  
  }


  // GET MAX METHOD
  public T getMax(){
      if (root == null) {
          System.out.println("The tree is empty");
          return null;
      }else{
          Node<T> curr = root;
          while (curr.right != null){
              curr = curr.right;
          }
          return curr.element;
      }

  }



  //GET MIN METHOD
  public T getMin(){
      if (root == null){
          System.out.println("Empty list");
          return null;
      }else{
          Node<T> curr = root;
          while (curr.left != null){
              curr = curr.left;
          }
          return curr.element;
      }
  }














  // ********************************** Find(T item) **********************************
  //
  // STUDENTS MUST IMPLEMENT THIS METHOD!!!!

  public boolean find(T item){
    if (root == null) {return false;}
    //ok so logic, if it equals root then return true, if greater then go right if less go left ez
    Node<T> curr = root;

    while (curr != null){
        int r = item.compareTo(curr.element);
        if (r > 0){
            curr = curr.right;
        }else if (r < 0){
            curr = curr.left;
        }else if (r == 0){
            return true;
        }
    }

    return false;
  }

  // ********************************** delete(T item) **********************************

  // this is a public method that delegates the deletion process to a private, recursive method
  public void delete(T item){
    this.root = deleteRecursively(this.root, item);
  }

  // a private method to recursively delete item from the tree
  private Node<T> deleteRecursively(Node<T> root, T item){

    if(root!=null){
      int r = item.compareTo(root.element);

      if(r < 0)
        root.left = deleteRecursively(root.left, item);
      else if(r > 0)
        root.right = deleteRecursively(root.right, item);
      else{

        //case 1: the root node is a leaf node
        if(root.left==null && root.right==null){
          root = null; // delete the current node safely

        // case 2: the root node has one child (on left then on right)
        }else if(root.right==null)
          root = root.left;
        else if(root.left==null)
          root = root.right;
   
        // case 3: the root node has two children
        else {
          T minItem = getMinRecursively(root.right);
          root.element = minItem;
          root.right = deleteRecursively(root.right,minItem);
        }
        System.out.println(item+" was deleted form the tree.");
      }
    }
    return root;
  }

  private T getMinRecursively(Node<T> root){
    T item = null;
    if(root.left==null)
      item = root.element;
    else
      item = getMinRecursively(root.left);
    return item;
  }
}

// The class for the Main Program
public class assignment6 {

  public static void main(String[] args) { // The main() method

    BinaryTree<Integer> tree = new BinaryTree<>();
    Integer ch, item, index;
    boolean quit = false;

    Scanner sc = new Scanner(System.in);

    do{
  
      System.out.println( "____________Main Menu_____________________");
      System.out.println( "select option :");
      System.out.println( "1: insert item");
      System.out.println( "2: find item");
      System.out.println( "3: tree height");
      System.out.println( "4: display items");
      System.out.println( "5: delete item");
      System.out.println( "6: get max item");
      System.out.println( "7: get min item");
      System.out.println( "8: print size");
      System.out.println( "9: exit");
      ch = sc.nextInt();

      switch (ch) {
        case 1:
          System.out.println( "enter item to insert:");
          item = sc.nextInt(); // read in an integer
          tree.insert(item);
          break;
        case 2:
          System.out.println( "enter item to insert:");
          item = sc.nextInt(); // read in an integer
          if(tree.find(item))
            System.out.println(item+" is found in the tree!");
          else
            System.out.println("Sorry... "+item+" is NOT found in the tree!");
          break;
        case 3:
          System.out.println("The height of the tree is: "+tree.height());
          break;
        case 4:
          tree.displayAll();
          break;
        case 5:
          System.out.println( "enter item to delete:");
          item = sc.nextInt(); 
          tree.delete(item);
          break;
        case 6:
          System.out.println("The maximum item is: " + tree.getMax());
          break;
        case 7:
          System.out.println("The minimum item is: " + tree.getMin());
          break;
        case 8:
          System.out.println("The size is: " + tree.size());
          break;
        case 9:
          quit = true;
          System.out.println( "Goodbye!!");
          break;
        default:
          System.out.println( "invalid selection");
      }
    } while(!quit);
  }
}
