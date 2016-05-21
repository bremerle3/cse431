/*Manil Bastola
*/
package lab3;

import java.util.Enumeration;

public class Fsa {

 static int GOTO[][] = {
/*     B    D    S    O    C    S    T    N    S    W    O */
/*     l    e    e    r    o    t    e    o    t    i    t */
/*     a    f    m         m    r    r    n    a    t    h */
/*     n    i    i         m         m         r    h    e */
/*     k    n              a         i         t         r */
/*          e                        n                     */
/*                                   a                     */
/*                                   l                     */
/*                                                         */
{      0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0},/* bad state */
{      1,   0,   0,   0,   0,   0,   3,   2,   4,   0,   0},/* 0th state */
{      2,   0,   0,   0,   0,   0,   5,   0,   0,   0,   0},/* state brought by "non" token */
{      3,   0,   0,   0,   0,   8,   0,   0,   0,   0,   0},/* state brought by "terminal" after 0th state*/
{      4,   0,   0,   0,   0,   0,   0,   0,   0,   10,   0},/* state brought by "start" */
{      5,   0,   0,   0,   0,   6,   0,   0,   0,   0,   0},/* state  brought by "terminal" after "non"*/
{      6,   0,   0,   0,   0,   7,   0,   0,   0,   0,   0},/* state brought by "str" after "terminal"*/
{      7,   0,   1,   0,   6,   0,   0,   0,   0,   0,   0},/* state brought by "str" after "str" above */
{      8,   0,   0,   0,   0,   9,   0,   0,   0,   0,   0},/* state brought by "str" after 0th "terminal" i.e. row3*/
{      9,   0,   1,   0,   8,   0,   0,   0,   0,   0,   0},/* state brought by "str" after last row */ 
{      10,   0,   0,   0,   0,   11,   0,   0,   0,   0,   0},/* state brought by "with" token*/ 
{      11,   0,   12,   0,   0,   0,   0,   0,   0,   0,   0},/* state brought by "str" after "with"*/ 
{      12,   0,   0,   0,   0,   13,   0,   0,   0,   0,   0},/* state brought by ";" after "str" token. Productions begin here*/ 
{      13,   14,   0,   0,   0,   0,   0,   0,   0,   0,   0},/* state brought by "str" i.e. lhs */ 
{      14,   0,   0,   0,   0,   15,   0,   0,   0,   0,   0},/* state brought by ":=" token*/ 
{      15,   0,   12,   14,   0,   16,   0,   0,   0,   0,   0},/* state brought by "str"(terminal) after ":=" */ 
{      16,   0,   12,   14,   0,   0,   0,   0,   0,   0,   0},/* state brought by "str"(non terminal) after "str" above*/ 
};

 static int ACTION[][] = {
/*     B    D    S    O    C    S    T    N    S    W    O */
/*     l    e    e    r    o    t    e    o    t    i    t */
/*     a    f    m         m    r    r    n    a    t    h */
/*     n    i    i         m         m         r    h    e */
/*     k    n              a         i         t         r */
/*          e                        n                     */
/*                                   a                     */
/*                                   l                     */
/*                                                         */
{      1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1}, /* Row 0 */
{      1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1},/* Row 1 */
{      1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1},/* Row 2 */
{      1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1},/* Row 3 */
{      1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1},/* Row 4 */
{      1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1},/* Row 5 */
{      2,   2,   2,   2,   2,   2,   2,   2,   2,   2,   2},/* Row 6: At this state we enter non terminal into symbol table.*/
{      1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1},/* Row 7 */
{      3,   3,   3,   3,   3,   3,   3,   3,   3,   3,   3},/* Row 8 At this state we enter a terminal into symbol table*/
{      1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1},/* Row 9 */
{      4,   4,   4,   4,   4,   4,   4,   4,   4,   4,   4},/* Row 10 Print "Start <start non terminal>"*/
{      1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1},/* Row 11 */
{      5,   5,   5,   5,   5,   5,   5,   5,   5,   5,   5},/* Row 12 Store nonterm1(lhs) = <non terminal>*/
{      1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1},/* Row 13 */
{      6,   6,   6,   6,   6,   6,   6,   6,   6,   6,   6},/* Row 14 Store label = <terminal>*/
{      7,   7,   7,   7,   7,   7,   7,   7,   7,   7,   7},/* Row 15 Update nonterm2 = <non terminal> or print "Edge nonterm1 nonterm2 label"*/
{      1,   1,   8,   8,   1,   1,   1,   1,   1,   1,   1},/* Row 16 Print "Edge nonterm1 nonterm2 label*/
};

   public Fsa(Enumeration e) {
      // Uncomment the line below and each token processed will be echoed.
      // ((TokenEnum)e).setDebug(true);

      SymbolTable symboltable = new SymbolTable();

      int state = 1;

      String 
         lhs     = "", 
         term    = "", 
         nonterm = "$FINAL$";

      while (e.hasMoreElements()) {
         Token t = (Token)e.nextElement();
         /*System.out.println("   Read token type " + t.type() + ": " + t);
         */
         int action = ACTION[state][t.type()];
         int newstate = GOTO[state][t.type()];
         /*
         System.out.println("State " + state +
                " Performing action " + action + " and going to " + newstate);
         	*/
         switch (action) {
            case  1: /* do nothing */
                     break;
            case  2: 
            	if (isValid(t.strValue())){ 
            		symboltable.enterNonTerminal(t.strValue());
            		newstate = GOTO[state][5]; /*Goto STR column i.e. 5 in this state. 
            		This is because at this state our concern is only the string value of the token and not its type*/
            	}
                break;
            case  3:  	
            	if (isValid(t.strValue())){ /*Checks validity of variable name. See the function below for details*/
            		symboltable.enterTerminal(t.strValue());
            		newstate = GOTO[state][5]; 
            	}
                break;
            case  4:
            	if (isValid(t.strValue())){
            		if (symboltable.isTerminal(t.strValue())){ /*Here we expect a non-terminal so raise error if we see a terminal*/
            			oops("Expected Non-Terminal on LHS: " + t.strValue() );
            		}
            		else{
            			System.out.println("Start " + t.strValue());
            			newstate = GOTO[state][5];
            		}
            	}
            	break;
            case  5:
            	if (isValid(t.strValue())){
            		if (symboltable.isTerminal(t.strValue())){ /*Here we expect a non-terminal so raise error if we see a terminal*/
            			oops("Expected Non-Terminal on LHS: " + t.strValue() );
            		}
            		else{
            			lhs = t.strValue();
            			newstate = GOTO[state][5]; 
            		}
            	}
            	break;
            case  6:
            	if (isValid(t.strValue())){
            		if (symboltable.isTerminal(t.strValue())){ /*Here we expect a terminal so raise error if we see a nonterminal*/
            			term = t.strValue();
            		}
            		else{
            			oops("Expected Terminal: " + t.strValue() );
            		}
            		newstate = GOTO[state][5]; 
            	}
            	break;
            case  7:
            	if (isValid(t.strValue())){
            		if (symboltable.isTerminal(t.strValue())){ /*Here we expect a non-terminal so raise error if we see a terminal*/
                		oops("Expected Non-Terminal: " + t.strValue() );
                	}
                	else{
                		nonterm = t.strValue();
                		newstate = GOTO[state][5]; 
                	}
            	}
            	else if ((t.type()== 2) || (t.type() == 3)){ /*if t represents "|" or ";" tokens, we do not expect a non-terminal after this token, so print and reset variables*/
            		System.out.println("Edge " + lhs + " " + nonterm + " " + term);
                    term    = ""; 
                    nonterm = "$FINAL$";
                	break;
            	}
            	break;
            case  8: /*Here t represents "|" or ";" tokens, so we print and reset variables*/
            	System.out.println("Edge " + lhs + " " + nonterm + " " + term);
                term    = ""; 
                nonterm = "$FINAL$";
            	break;
         }

         state = newstate;
      }
      if (state == 0) oops("End in bad state: " + state); /*I define 0 as a bad state*/
   }

   void oops(String s) {
      System.err.println("Error: " + s);
      System.out.println("ABORT");
      System.exit(-1);
   }
   
   /*isValid checks if str is not a member of the set {";",":=", "|", ",","" which are invalid variable names for terminal and nonterminal types}*/
   boolean isValid(String s){
	   return (s != "")&&(s != ";")&&(s != "|")&&(s != ",")&&(s!= ":=");
   }
}
