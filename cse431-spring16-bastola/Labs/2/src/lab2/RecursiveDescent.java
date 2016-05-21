package lab2;
import autogen.*;
import java_cup.runtime.*;
import common.Listing;

/**
 * Manil Bastola (id 438223)
 * Answer to question 1:

Two left recursions detected. Need to specify four Non terminals and a lambda terminal.

non terminal integer W ,X, Y, Z;
terminal integer lambda;

Lists
	::= W X
	;

W
	::= List:L 
		{: Listing.get().EmitMessage("Value: " + L.intValue()); :}
	;

X	
	::= List:L X
	  	{: Listing.get().EmitMessage("Value: " + L.intValue()); :}
	  	| lambda
	;
----------------------------------------------------------------

Operands
	::= Y Z
	;

Y
	::= Operand:Op
		{: RESULT = new Integer(0); :}
	;
	
Z
	::= Operand:Op Z
		{: RESULT = new Integer(0); :}
	| lambda
	;
----------------------------------------------------------------------
----------------------------------------------------------------------	
  * Answer to question 2:

Non-terms		|	First sets					| Follow Sets
------------------------------------------------------------------------------
Program			| lparen						| $
File			| lparen						| $
Lists			| lparen						| $
List			| lparen						| lparen, number, rparen,$
W				| lparen						| lparen,$
X				| lambda, lparen				| $
Expression		| plus, minus, times, negate,	| rparen
 				| sum, product, mean			| 
Operand1		| lparen, number				| lparen, number
Operand2		| lparen, number				| rparen
Atom			| lparen, number				| lparen, number, rparen
Operand			| lparen, number				| lparen, number, rparen
Operands		| lparen, number				| rparen
Y				| lparen, number				| lparen, number, rparen
Z				| lparen, number, lambda		| rparen

-------------------------------------------------------------------------
-------------------------------------------------------------------------

  * Answer to question 4:
  *My Y and Z return value is of type IntPair where value.int1 is the sum or product of all numbers in the list
  *depending on the sym.number associated with the list operands. The value.int2 is the number of elements in 
  *the list of operands and increases everytime I expect one operand. For mean, I simply divide the value.int1 
  *(as returned when operation is sym.sum) by value.int2.  
  
  *Final Note:
  *I have never used Java before this class. And unfortunately I had to work alone for this lab. I tried my best to 
  *come up with perfect solution but did not get enough time to fix the final print out. The print out is of the form 
  *1:negate (negate (negate 0)
   2:           ^^^Result 0.
 */

public class RecursiveDescent {

	public RecursiveDescent() {
      Scanner.init();
      Program();
   }
   
   public void Program() {
	   	  if (peek() == sym.lparen ) { // predicts Program -> File
	         File();
	      }
	   	  else oops("expected lparen");
   }
   
   public void File() {
   	   if (peek() == sym.lparen ) { // predicts File -> Lists
   	         Lists();
   	      }
   	   else oops("expected lparen");
      }
   
   public void Lists() {
   	   if (peek() == sym.lparen ) { // predicts Lists -> W X
   	         W();
   	         X();
   	      }
   	   else oops("expected lparen");
      }
   
   public void W() {
   	   if (peek() == sym.lparen ) { // predicts W -> List
   		   int result = List();
   		   Listing.get().EmitMessage("Result " + result);
   	      }
   	   else oops("expected lparen");
      }
   
   public void X() {
   	   if (peek() == sym.lparen ) { // predicts X -> List X
   		   	 int result = List();
   		   	 Listing.get().EmitMessage("Result " + result);
   	         X();     
   	      }
   	   else if (peek() == sym.EOF){ // predicts X -> lambda
   		   //Do nothing;
   	   }
   	   else oops("expected lparen or EOF");
      }
   
   public int List() {
	   int result=0;
   	   if (peek() == sym.lparen ) { // predicts List -> lparen Expression rparen
   		   expect(sym.lparen, "expected lparen");
   		   result = Expression();
   		   expect(sym.rparen, "expected rparen");
   		   return result;
   	      }
   	   else oops("expected lparen");
   	   return result;
      }
   
   public int Expression() {
   	   if (peek() == sym.plus ) { // predicts Expression -> plus Operand1 Operand2
   		   expect(sym.plus, "expected plus");
   		   int a1 = Operand1();
		   int a2 = Operand2();
		   return (a1+a2);
   	      }
   	   else if (peek() == sym.minus ) { // predicts Expression -> minus Operand1 Operand2
   		   expect(sym.minus, "expected minus");
   		   int a1 = Operand1();
		   int a2 = Operand2();
		   return (a1-a2);
   	      } 
   	   else if (peek() == sym.times ) { // predicts Expression -> times Operand1 Operand2
   		   expect(sym.times, "expected times");
   		   int a1 = Operand1();
		   int a2 = Operand2();
		   return (a1*a2);
	      } 
   	   else if (peek() == sym.negate ) { // predicts Expression -> negate Operand
   		   expect(sym.negate, "expected negate");
		   int a = Operand();
   		   return (-1*a);
	      } 
   	   else if (peek() == sym.sum ) { // predicts Expression -> sum Operands
   		   expect(sym.sum, "expected sum");
		   return Operands(sym.sum);
	      } 
   	   else if (peek() == sym.product ) { // predicts Expression -> product Operands
   		   expect(sym.product, "expected product");
		   return Operands(sym.product);
	      } 
   	   else if (peek() == sym.mean ) { // predicts Expression -> mean Operands
   		   expect(sym.mean, "expected mean");
   		   return Operands(sym.mean);
	      } 	
   	   else oops("expected plus, minus, times, negate, sum, product or mean");
   	   return -1;
   }
   
   public int Operand1() {
   	   if (peek() == sym.lparen || peek() == sym.number ) { // predicts Operand1 -> Atom
   	         return Atom();
   	      }
   	   else oops("expected lparen or number");
   	   return -1;
   }
   
   public int Operand2() {
   	   if (peek() == sym.lparen || peek() == sym.number ) { // predicts Operand2 -> Atom
   	         return Atom();
   	      }
   	   else oops("expected lparen or number");
   	   return -1;
   }
   
   public int Operand() {
   	   if (peek() == sym.lparen || peek() == sym.number ) { // predicts Operand -> Atom
   	         return Atom();
   	      }
   	   else oops("expected lparen or number");
   	   return -1;
   }

   public int Operands(int operation) {
   	   if (peek() == sym.lparen || peek() == sym.number ) { // predicts Operands -> Y Z
   	         IntPair a1 = Y();
   	         IntPair a2 = Z(operation);
   	         if (operation == sym.sum){
   	        	 return (a1.int1+a2.int1);
   	         }
   	         else if (operation == sym.product){ 
	        	 return (a1.int1*a2.int1);
	         }
   	         else if (operation == sym.mean){
	        	 return (a1.int1+a2.int1)/(a1.int2+a2.int2);
	         }
   	   }
   	   else oops("expected lparen or number");
   	   return -1;
   }
   
   public IntPair Y() {
   	   if (peek() == sym.lparen || peek() == sym.number ) { // predicts Y -> Operand
   	         int a1 = Operand();
   	         IntPair a2 = new IntPair(a1,1);
   	         return a2;
   	      }
   	   else oops("expected lparen or number");
   	   IntPair a2 = new IntPair(-1,-1);
   	   return a2;
   }
   
   public IntPair Z(int operation) {
   	   if (peek() == sym.lparen || peek() == sym.number ) { // predicts Z -> Operand Z
   	         int a1 = Operand();
   	         IntPair a2 = Z(operation);
   	         if (operation == sym.sum){
   	        	 IntPair a3 = new IntPair(a1+a2.int1,a2.int2);
	        	 return a3;
	         }
	         else if (operation == sym.product){
	        	 IntPair a3 = new IntPair(a1*a2.int1,a2.int2);
	        	 return a3;
	         }
	         else if (operation == sym.mean){
	        	 IntPair a3 = new IntPair(a1+a2.int1,1+a2.int2);
	        	 return a3;
	         }
   	      }
   	   else if (peek() == sym.rparen){ 						//Z -> lambda
   		   //Do nothing;
   		   if (operation == sym.sum || operation == sym.mean){
   			   IntPair a1 = new IntPair(0,0);
	           return a1;
   		   }
   		   else if (operation == sym.product){
   			   IntPair a1 = new IntPair(1,1);
	           return a1;
   		   }
   		}
   	   else oops("expected lparen or number or rparen");
   	   IntPair a3 = new IntPair(-1,-1);
	   return a3;
   }
   
   public int Atom() {
   	   if (peek() == sym.lparen ) { 						// predicts Atom -> List
   	         return List();
   	   }
   	   else if (peek() == sym.number){       				// predicts Atom -> number
   		   int a = (int) Scanner.peek().value ;
   		   expect(sym.number,"expected number");
   		   return a;
   		}
   	   else oops("expected lparen or number or rparen");
   	   return -1;
   }
   
   protected int peek() {
	      return Scanner.peek().sym;
   }
   
   void oops(String s) {
      System.err.println("Error: " + s);
      System.exit(-1);
   }
   
 
		   
   protected void expect(int sym) {
	      expect(sym, "Expected symbol #" +sym + " (as defined in sym.java)");
	   }
	   protected void expect(int sym, String message) {
	      if (peek() != sym) oops(message);
	      else Scanner.advance(); 
	   }
}
