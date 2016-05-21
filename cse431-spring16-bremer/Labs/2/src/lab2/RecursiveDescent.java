package lab2;
import autogen.*;
import java_cup.runtime.*;
import common.Listing;

/**
  * Answer to question 1:

There are two left recursions present in the grammar:
	1) Lists ::= Lists List
	2) Operands ::= Operands Operand
I will solve these issues by introducing two new nonterminals



  * Answer to question 2:


  * Answer to question 4:

 */

public class RecursiveDescent {

	   Symbol plus_sym = new Symbol(sym.plus);
	   Symbol minus_sym = new Symbol(sym.minus);
	   Symbol sum_sym = new Symbol(sym.sum);
	   Symbol product_sym = new Symbol(sym.product);
	   Symbol times_sym = new Symbol(sym.times);
	   Symbol negate_sym = new Symbol(sym.negate);
	   Symbol mean_sym = new Symbol(sym.mean);
	   
   public RecursiveDescent() {
      Scanner.init();
	
      //
      // FIXME:  Eliminate the loop below and instead call
      //   the method associated with your grammar's goal symbol
      //
      //  So, eliminate from here....
      Symbol t = Scanner.peek();
      while (t.sym != sym.EOF) {
    	  /*
         Listing.get().EmitMessage("Next token will be " + t);
         Scanner.advance();
         Listing.get().EmitMessage("Next2 token will be " + t);
         t = Scanner.peek();
         */
    	  System.out.println("\n--> next symbol: " + t);
    	  System.out.println("\n^^^" + List());
    	  //System.out.println("\n^^^" + List());
    	  t = Scanner.peek();    
      }
      //  ... to here

      /*
      private double List() {
    	  Symbol t1 = Scanner.peek(); 
    	  if(t.sym == sym.number) {
    		  Symbol number_sym = new Symbol(sym.number);
    		  //return new Double( (Integer) expect(number_sym).value);
    		  return new Double( (Integer) 1337);
    	  }
    	  else {
    		  Symbol lparen_sym = new Symbol(sym.lparen);
    		  Symbol rparen_sym = new Symbol(sym.rparen);
    		  expect(lparen_sym);
    		  double retVal = Expression();
    		  expect(rparen_sym);
    		  return retVal;    	  
    	  }
      }
      
      private double Expression() {
    	  int retVal = 8;
    	  double retValDouble = (double)retVal;
    	  return ((double) retVal);
    	  
      }
      */
      
   }
   
   private double List(){
	   Symbol t = Scanner.peek(); 
	   if(t.sym == sym.number) {
		   System.out.println("\nEncountered a number!");
		   double retVal = (double) 35;
		   return retVal;
	   }
	   else{
 		   Symbol lparen_sym = new Symbol(sym.lparen);
 		   Symbol rparen_sym = new Symbol(sym.rparen);
		   expect(lparen_sym);
		   double retVal = Expression();
		   System.out.println("\nPassed Expression eval!");
		   Symbol t2 = Scanner.peek();
		   System.out.println("\n--> next symbol: " + t2);
		   expect(rparen_sym);
	   }
	   return 0;
   }
   
   private double Expression(){
	   Symbol t2 = Scanner.peek();
	   switch(t2.sym) {
		   case sym.plus:
			   return plus_exp();
		   case sym.minus:
			   return minus_exp();
		   case sym.sum:
			   return sum_exp();
		   case sym.product:
			   return product_exp();
		   case sym.times: 
			   return times_exp();
		   case sym.negate:
			   System.out.println("\nEncountered a negate!");
			   return negate_exp();
		   case sym.mean:
			   return mean_exp();
		   default:
			   System.out.println("\nt.sym: " + t2.sym);
			   oops("Syntax error at expression evaluation!");
		   	   return 0;
	   }
	   
   }
   
   private double plus_exp(){
	   expect(plus_sym);
	   return List() + List();
   }
 private double minus_exp(){
	   expect(minus_sym);
	   return List() - List();
   }
 private double sum_exp(){
	 expect(sum_sym);
	 return List() + List();
 }
 private double product_exp(){
	 expect(product_sym);
	 return List() + List();
 }
 private double times_exp(){
	 expect(times_sym);
	 return List() * List();
 }
 private double negate_exp(){
	 expect(negate_sym);
	 return -List();
 }
 private double mean_exp(){
	 expect(mean_sym);
	 return List() + List();
 }
   
   

   void oops(String s) {
      System.err.println("Error: " + s);
      System.exit(-1);
   }
   
   protected void expect(Symbol sym) {
	      expect(sym, "Expected symbol #" +sym + " (as defined in sym.java)");
	   }
	   protected void expect(Symbol sym, String message) {
	      if (Scanner.peek().sym != sym.sym) oops(message);
	      else Scanner.advance(); 
	   }
}
