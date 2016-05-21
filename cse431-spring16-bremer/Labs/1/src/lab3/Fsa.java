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
{      0,   0,   0,   0,   0,   8,   2,   1,   5,   8,   0} /* 0 */,  // Accepting state, absorb "non" keyword, absorb "start" token
{      1,   9,   0,   0,   0,   0,   2,   0,   0,   0,   0} /* 1 */,  // Absorb "terminal" keyword after "non" keyword
{      2,   0,   0,   0,   0,   3,   0,   0,   0,   0,   0} /* 2 */,  // Absorb type
{      3,   0,   0,   0,   0,   4,   4,   4,   4,   4,   4} /* 3 */,  // Terminal and nonterminal definitions
{      4,   0,   0,   0,   3,   0,   0,   0,   0,   0,   0} /* 4 */,  // Define more terminal and nonterminal definitions
{      5,   0,   0,   0,   0,   0,   0,   0,   0,   6,   0} /* 5 */,  // Absorb "with" token
{      6,   0,   0,   0,   0,   7,   7,   7,   7,   7,   0} /* 6 */,  // Define start state
{      7,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0} /* 7 */,  // After start definition, absorb semicolon
{      8,   9,   0,   0,   0,   0,   0,   0,   0,   0,   0} /* 8 */,  // Absorb "define" keyword for production rule
{      9,   0,   0,   0,   0,   10,  10,  10,  10,  10,  10} /* 9 */,  // Absorb a terminal
{      10,  0,   0,   9,   0,   11,  11,  11,  11,  11,  11} /* 10 */, // Absorb a non terminal (optional), add another rule, or finish
{      11,  0,   0,   9,   0,   0,   0,   0,   0,   0,   0} /* 11 */,  // Add another rule, or finish
};

 public static final int FAIL = 0, NOOP = 1, TERM = 2, NTER = 3, SYMB = 4, CLER = 5, STRT = 6, PRNT = 7, NFLG = 8, SLHS = 9, PRCL = 10;

 static int ACTION[][] = {
/*     B       D      S      O      C      S      T      N      S      W      O */
/*     l       e      e      r      o      t      e      o      t      i      t */
/*     a       f      m             m      r      r      n      a      t      h */
/*     n       i      i             m             m             r      h      e */
/*     k       n                    a             i             t             r */
/*             e                                  n                             */
/*                                                a                             */
/*                                                l                             */
/*                                                                              */
{      NOOP,   FAIL,  FAIL,  FAIL,  FAIL,  SLHS,  NOOP,  NFLG,  NOOP,  SLHS,   FAIL} /* 0 */,
{      NOOP,   NOOP,  NOOP,  NOOP,  NOOP,  NOOP,  NOOP,  NOOP,  NOOP,  NOOP,   FAIL} /* 1 */,
{      NOOP,   NOOP,  FAIL,  FAIL,  FAIL,  NOOP,  FAIL,  FAIL,  FAIL,  FAIL,   FAIL} /* 2 */,
{      NOOP,   NOOP,  NOOP,  NOOP,  NOOP,  SYMB,  SYMB,  SYMB,  SYMB,  SYMB,   FAIL} /* 3 */,
{      NOOP,   FAIL,  CLER,  FAIL,  NOOP,  FAIL,  FAIL,  FAIL,  FAIL,  FAIL,   FAIL} /* 4 */,
{      NOOP,   FAIL,  FAIL,  FAIL,  FAIL,  FAIL,  FAIL,  FAIL,  FAIL,  NOOP,   FAIL} /* 5 */,
{      NOOP,   NOOP,  FAIL,  FAIL,  FAIL,  STRT,  STRT,  STRT,  STRT,  STRT,   FAIL} /* 6 */,
{      NOOP,   FAIL,  NOOP,  FAIL,  FAIL,  FAIL,  FAIL,  FAIL,  FAIL,  FAIL,   FAIL} /* 7 */,
{      NOOP,   NOOP,  FAIL,  FAIL,  FAIL,  FAIL,  FAIL,  FAIL,  FAIL,  FAIL,   FAIL} /* 8 */,
{      NOOP,   FAIL,  NOOP,  FAIL,  FAIL,  TERM,  TERM,  TERM,  TERM,  TERM,   FAIL} /* 9 */,
{      NOOP,   FAIL,  PRCL,  PRNT,  FAIL,  NTER,  NTER,  NTER,  NTER,  NTER,   FAIL} /* 10 */,
{      NOOP,   FAIL,  PRCL,  PRNT,  FAIL,  FAIL,  FAIL,  FAIL,  FAIL,  FAIL,   FAIL} /* 11 */,
};

   public Fsa(Enumeration e) {
      // Uncomment the line below and each token processed will be echoed.
      // ((TokenEnum)e).setDebug(true);

      SymbolTable symboltable = new SymbolTable();

      int state = 0;

      String 
         lhs     = "", 
         term    = "", 
         nonterm = "$FINAL$",
      	 start   = "";

      boolean nonterm_f = false;;
      
      while (e.hasMoreElements()) {
         Token t = (Token)e.nextElement();

         //System.out.println("   Read token type " + t.type() + ": " + t);

         int action = ACTION[state][t.type()];
         int newstate = GOTO[state][t.type()];

         System.out.println("State " + state +
                " Performing action " + action + " and going to " + newstate);

         switch (action) {
            case FAIL:
            	oops("Invalid edge transition");
                break;
            case NOOP:
            	break;
            case NFLG:
            	nonterm_f = true;
            	break;
            case TERM:
            	term = t.strValue();
            	break;
            case NTER: 
            	nonterm = t.strValue();
            	break;
            case SYMB:
            	if(nonterm_f == false){
            		symboltable.enterTerminal(t.strValue());
            	}
            	else{
            		symboltable.enterNonTerminal(t.strValue());            	
            	}
            	break;
            case CLER:
            	break;
            case STRT:
            	start = t.strValue();
            	System.out.println("Start "+start);
            	break;
            case PRNT:
            	System.out.println("Edge "+lhs+" "+nonterm+" "+term);
                term    = ""; 
                nonterm = "$FINAL$";
            	break;
            case PRCL:
            	 System.out.println("Edge "+lhs+" "+nonterm+" "+term);
                 lhs     = ""; 
                 term    = ""; 
                 nonterm = "$FINAL$";
              	 start   = "";
              	 break;
            case SLHS:
            	lhs = t.strValue();
            	break;
         }

         state = newstate;
      }
      if (state != 0) oops("End in bad state: " + state);
   }

   void oops(String s) {
      System.err.println("Error: " + s);
      System.out.println("ABORT");
      System.exit(-1);
   }
}
