/****************************************************************
Assignment 5 Huffman Tree Project
Randy Jackson
n01173844
COP3530-10014
3/13/2017
****************************************************************/

import java.io.*;
import java.util.*;

public class n01173844 {

   public static void main(String args[]) throws IOException
   {
   
      File file = new File(args[0]);
        
        //Creating Nodes for letters A-G
      Node A = new Node();
      Node B = new Node();
      Node C = new Node();
      Node D = new Node();
      Node E = new Node();
      Node F = new Node();
      Node G = new Node();
        
        //Setting the letters in the node for A-G
      A.setLetter('A');
      B.setLetter('B');
      C.setLetter('C');
      D.setLetter('D');
      E.setLetter('E');
      F.setLetter('F');
      G.setLetter('G');
        
        //Reading chars in from file and adjusting frequencies
      FileInputStream fis = new FileInputStream(file);
        
      char current;
        
      while (fis.available() > 0) 
      {
        
         current = (char) fis.read();
        
         switch (current) 
         {
            
            case 'A':  A.addFrequency();
               break;
            case 'B':  B.addFrequency();
               break;
            case 'C':  C.addFrequency();
               break;
            case 'D':  D.addFrequency();
               break;
            case 'E':  E.addFrequency();
               break;
            case 'F':  F.addFrequency();
               break;
            case 'G':  G.addFrequency();
               break;
            default: 
               break;
         }  
      }
        
        //Creating a tree for each node
      Tree treeA = new Tree();
      Tree treeB = new Tree();
      Tree treeC = new Tree();
      Tree treeD = new Tree();
      Tree treeE = new Tree();
      Tree treeF = new Tree();
      Tree treeG = new Tree();
        
        //Adding Nodes to each tree
      treeA.insertNode(A);
      treeB.insertNode(B);
      treeC.insertNode(C);
      treeD.insertNode(D);
      treeE.insertNode(E);
      treeF.insertNode(F);
      treeG.insertNode(G);
        
        //Adding Trees to Priority Queue
      PriorityQueue initialList = new PriorityQueue(7);
        
      initialList.insert(treeA);
      initialList.insert(treeB);
      initialList.insert(treeC);
      initialList.insert(treeD);
      initialList.insert(treeE);
      initialList.insert(treeF);
      initialList.insert(treeG);
        
        //Building the Huffman Tree
      Tree a;
      Tree b;
        
      Tree HuffmanTree;
        
      while(initialList.numberOfItems() != 1)
      {
         a = initialList.remove();
         b = initialList.remove();
        
         HuffmanTree = new Tree();
         HuffmanTree.insert(a,b);
        
         initialList.insert(HuffmanTree);
        
      }
       
      //The completed Huffman Tree
      HuffmanTree = initialList.remove();
      
      //Encoding the file at this step to have string for d
      String encodedFile = encode(file, HuffmanTree, A, B, C, D, E, F, G);
      
      //User input and interface
      boolean end = false;
      
      Scanner input = new Scanner(System.in);
      
      String option;
       
      while(!end)
      {
      System.out.println("Option:");
      System.out.println("A : Prints the Huffman Tree.");
      System.out.println("B : Prints the table of values.");
      System.out.println("C : Prints the enconded file.");
      System.out.println("D : Prints the decoded file using the Huffman Tree.");
      System.out.println("X : Exits the program.");
      
      System.out.print("Please select an option: ");
      
      option = input.nextLine();
      
      switch (option.toUpperCase()) 
         {
            
            case "A":
            HuffmanTree.displayTree();
            System.out.println();
               break;
            case "B":  
            printTable(HuffmanTree, A, B, C, D, E, F, G);
            System.out.println();
               break;
            case "C":  
            printEncodedFile(encodedFile);
            System.out.println();
               break;
            case "D":  
            HuffmanTree.decodeFile(encodedFile);
            System.out.println();
               break;
            case "X":  
            end = true;
               break;
            default: 
               break;
         }  
      }
      
   }
   
   public static void printEncodedFile(String encodedFile)
   {
   
   StringBuilder fixer = new StringBuilder();
   fixer.append(encodedFile);
   int index = 8;
   int iteration = 1;
   
   while(index < fixer.length())
   {
      if(iteration % 3 == 0 && iteration != 0)
         {
         fixer.insert(index, '\n');
         index++;
         }
      else
         {   
         fixer.insert(index, ' ');
         index++;
         }

      index+=8;
      iteration++;
   }
   System.out.println(fixer.toString());
   
   }
   
   public static String encode(File file, Tree HuffmanTree, Node A, Node B, Node C, Node D, Node E, Node F, Node G ) throws IOException
   {
   FileInputStream fis = new FileInputStream(file);
   
   StringBuilder encoder = new StringBuilder();
        
      char current;
        
      while (fis.available() > 0) 
      {
        
         current = (char) fis.read();
        
         switch (current) 
         {
            
            case 'A':  encoder.append(HuffmanTree.find(A.getLetter()));
               break;
            case 'B':  encoder.append(HuffmanTree.find(B.getLetter()));
               break;
            case 'C':  encoder.append(HuffmanTree.find(C.getLetter()));
               break;
            case 'D':  encoder.append(HuffmanTree.find(D.getLetter()));
               break;
            case 'E':  encoder.append(HuffmanTree.find(E.getLetter()));
               break;
            case 'F':  encoder.append(HuffmanTree.find(F.getLetter()));
               break;
            case 'G':  encoder.append(HuffmanTree.find(G.getLetter()));
               break;
            default: 
               break;
         }  
      }
      
      return encoder.toString();

   
   }
   
   public static void printTable(Tree HuffmanTree, Node A, Node B, Node C, Node D, Node E, Node F, Node G )
   {
   System.out.println("Frequency    Letter      # of Bits      Encode      New# of Bits      ");
   System.out.println("----------------------------------------------------------------------");
   System.out.println(A.getFrequency() + "\t\t\t\t\t" + A.getLetter() + "\t\t\t\t\t" + (8 * A.getFrequency()) + "\t\t\t\t" + HuffmanTree.find(A.getLetter()) + "\t\t\t\t" + HuffmanTree.find(A.getLetter()).length() * 8);
   System.out.println(B.getFrequency() + "\t\t\t\t\t" + B.getLetter() + "\t\t\t\t\t" + (8 * B.getFrequency()) + "\t\t\t\t" + HuffmanTree.find(B.getLetter()) + "\t\t\t\t" + HuffmanTree.find(B.getLetter()).length() * 8);
   System.out.println(C.getFrequency() + "\t\t\t\t\t" + C.getLetter() + "\t\t\t\t\t" + (8 * C.getFrequency()) + "\t\t\t\t" + HuffmanTree.find(C.getLetter()) + "\t\t\t\t" + HuffmanTree.find(C.getLetter()).length() * 8);
   System.out.println(D.getFrequency() + "\t\t\t\t\t" + D.getLetter() + "\t\t\t\t\t" + (8 * D.getFrequency()) + "\t\t\t\t" + HuffmanTree.find(D.getLetter()) + "\t\t\t\t" + HuffmanTree.find(D.getLetter()).length() * 8);
   System.out.println(E.getFrequency() + "\t\t\t\t\t" + E.getLetter() + "\t\t\t\t\t" + (8 * E.getFrequency()) + "\t\t\t\t" + HuffmanTree.find(E.getLetter()) + "\t\t\t\t" + HuffmanTree.find(E.getLetter()).length() * 8);
   System.out.println(F.getFrequency() + "\t\t\t\t\t" + F.getLetter() + "\t\t\t\t\t" + (8 * F.getFrequency()) + "\t\t\t\t" + HuffmanTree.find(F.getLetter()) + "\t\t\t\t" + HuffmanTree.find(F.getLetter()).length() * 8);
   System.out.println(G.getFrequency() + "\t\t\t\t\t" + G.getLetter() + "\t\t\t\t\t" + (8 * G.getFrequency()) + "\t\t\t\t" + HuffmanTree.find(G.getLetter()) + "\t\t\t\t" + HuffmanTree.find(G.getLetter()).length() * 8);
   }   
    
   private static class Node
   {
      public int iData; // data item (FREQUENCY)
      public char dData; // data item (LETTER)
      
      public Node leftChild; // this node left child
      public Node rightChild; // this node right child
      
      public Node()
      {
         iData = 0;
      }
      public void setLetter(char LETTER)
      {
         dData = LETTER;
      }
      
      public void addFrequency()
      {
         iData++;
      } 
      
      public int getFrequency()
      {
         return iData;}
      
      public char getLetter()
      {
         return dData;}
      
      public void displayNode() // display ourself
      {
         System.out.print('{');
         System.out.print(iData);
         System.out.print(", ");
         System.out.print(dData);
         System.out.print("} ");
      }
      
   } //________________END Node
   
   private static class PriorityQueue
   {
      // array in sorted order, from max at 0 to min at size-1
      private int maxSize;
      private Tree[] queArray;
      private int nItems;
      
      //-------------------------------------------------------------
      
      public PriorityQueue(int s) // constructor
      {
         maxSize = s;
         
         queArray = new Tree[maxSize];
         
         nItems = 0;
      }
      
      //-------------------------------------------------------------
      
      public void insert(Tree item) // insert item
      {
         int j;
         
         if(nItems==0) // if no items,
            queArray[nItems++] = item; // insert at 0
            
         else // if items,
         {
            for(j=nItems-1; j>=0; j--) // start at end,
            {
               if( item.getRootFrequency() > queArray[j].getRootFrequency() ) // if new item larger,
               
                  queArray[j+1] = queArray[j]; // shift upward
                  
               else // if smaller,
               
                  break; // done shifting
                  
            } // end for
            
            queArray[j+1] = item; // insert it
            
            nItems++;
         } // end else (nItems > 0)
      } // end insert()
      //-------------------------------------------------------------
      public int numberOfItems() // returns number of items in the queue
      {
         return nItems;}
      //-------------------------------------------------------------
      public Tree remove() // remove minimum item
      { 
         return queArray[--nItems]; }
      //-------------------------------------------------------------
      public Tree peekMin() // peek at minimum item
      { 
         return queArray[nItems-1]; }
      //-------------------------------------------------------------
      public boolean isEmpty() // true if queue is empty
      { 
         return (nItems==0); }
      //-------------------------------------------------------------
      public boolean isFull() // true if queue is full
      { 
         return (nItems == maxSize); }
      //-------------------------------------------------------------
   } // end class PriorityQ
   
   
   
   private static class Tree
   {
      private Node root; // first node of tree
   
      public Tree() // constructor
      { root = null; } 
      
      public int getRootFrequency()
      { return root.getFrequency(); }
      
      public char getRootLetter()
      { return root.getLetter(); }
      
      public void print()
      { root.displayNode(); }
      
      //____________________END CONSTRUCTOR
   
      public String find(char key) // find node with given key
      {                         // (assumes non-empty tree)
         
         StringBuilder path = new StringBuilder();
         
         Node leftCheck = root; // start at root
         
         boolean found = false;
         
         while(true)
         {
            if(leftCheck.leftChild.getLetter() == key)
            {
               path.append(0);
               found = true;
               break;
            }
            else if(leftCheck.rightChild.getLetter() == key)
            {
               path.append(1);
               found = true; 
               break;           
            }
            else
            {
               if(leftCheck.leftChild.leftChild != null)
               {
                  leftCheck = leftCheck.leftChild;
                  path.append(0);
               }
               else
               {
                  path = new StringBuilder();
                  break;
               }
            }
           }
         
            Node rightCheck = root;
          if(!found)
          {
            while(true)
            {
               if(rightCheck.leftChild.getLetter() == key)
               {
                  path.append(0);
                  found = true;
                  break;
               }
               else if(rightCheck.rightChild.getLetter() == key)
               {
                  path.append(1);
                  found = true;
                  break;            
               }
               else
               {
                  if(rightCheck.rightChild.rightChild != null)
                  {
                     rightCheck = rightCheck.rightChild;
                     path.append(1);
                  }
                  else
                  {
                     path = new StringBuilder();
                     break;
                  }
               }
            
            }}
          
         
          
         return path.toString();
            
      
         
        
         //return current; // found it **/
       
      }
       //________________________END FIND
       
      public void insertNode(Node letter)
      {
      
         int id = letter.getFrequency();
         char dd = letter.getLetter();
      
         root = letter;
            
      } 
       
       //________________________END INSERTNODE
       
      public void insert(Tree A, Tree B)
      {
         Node newNode = new Node(); // make new node
      
         int frequencySum = A.getRootFrequency() + B.getRootFrequency();
         newNode.iData = frequencySum;
      
         newNode.dData = 'X';
      
         root = newNode;
      
         if(A.getRootFrequency() <= newNode.iData)
         {
            root.leftChild = A.root;
            root.rightChild = B.root;
         }
         else
         {
            root.leftChild = B.root;
            root.rightChild = A.root;
         }
      
      }
      
      public void decodeFile(String input)
      {
      Node master = root;
      Node traverse = root;
      
      int index = 0;
      
      while(index < input.length())
         {
         
         if(input.charAt(index)== '0')
         {
            if(traverse.leftChild.getLetter() != 'X')
            {
            System.out.print(traverse.leftChild.getLetter());
            traverse = master;
            index++;
            }
            else
            {
            traverse = traverse.leftChild;
            index++;
            }
         }
         else
         {
            if(traverse.rightChild.getLetter() != 'X')
            {
            System.out.print(traverse.rightChild.getLetter());
            traverse = master;
            index++;
            }
            else
            {
            traverse = traverse.rightChild;
            index++;
            }         
         
         }     
         
         
         }
      
      }
         
         
         
      
      //______________________________END INSERT
      
      public void displayTree()
      {
      
         Stack globalStack = new Stack();
      
         globalStack.push(root);
      
         int nBlanks = 32;
      
         boolean isRowEmpty = false;
      
         System.out.println(
            "......................................................");
      
         while(isRowEmpty==false)
         {
         
            Stack localStack = new Stack();
         
            isRowEmpty = true;
         
            for(int j=0; j<nBlanks; j++) 
               System.out.print(' ');
         
            while(globalStack.isEmpty()==false)
            {
               Node temp = (Node)globalStack.pop();
            
               if(temp != null)
               {
                  System.out.print(temp.iData + " " + temp.dData);
               
               
               
                  localStack.push(temp.leftChild);
               
                  localStack.push(temp.rightChild);
               
                  if(temp.leftChild != null || temp.rightChild != null)
                     isRowEmpty = false;
               }
               
               else
               {
                  System.out.print("--");
               
                  localStack.push(null);
               
                  localStack.push(null);
               }
            
               for(int j=0; j<nBlanks*2-2; j++)
                  System.out.print(' ');
            
            } // end while globalStack not empty
            
            System.out.println();
         
            nBlanks /= 2;
         
            while(localStack.isEmpty()==false)
               globalStack.push( localStack.pop() );
         
         } // end while isRowEmpty is false
      
         System.out.println(
            "......................................................");
      } // end displayTree()
       
   }}
  


