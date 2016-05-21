
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Mon Feb 22 17:06:50 CST 2016
//----------------------------------------------------

package autogen;

import common.Listing;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Mon Feb 22 17:06:50 CST 2016
  */
public class Parsercup3 extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public Parsercup3() {super();}

  /** Constructor which sets the default scanner. */
  public Parsercup3(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public Parsercup3(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\023\000\002\002\004\000\002\002\004\000\002\002" +
    "\003\000\002\003\005\000\002\004\004\000\002\004\003" +
    "\000\002\005\003\000\002\005\003\000\002\005\003\000" +
    "\002\007\003\000\002\007\003\000\002\007\003\000\002" +
    "\007\003\000\002\006\003\000\002\006\003\000\002\006" +
    "\003\000\002\006\003\000\002\010\003\000\002\010\003" +
    "" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\027\000\026\030\020\031\010\032\016\033\007\034" +
    "\015\035\005\036\024\037\013\040\006\041\023\001\002" +
    "\000\030\030\ufffa\031\ufffa\032\ufffa\033\ufffa\034\ufffa\035" +
    "\ufffa\036\ufffa\037\ufffa\040\ufffa\041\ufffa\042\ufffa\001\002" +
    "\000\030\030\ufff3\031\ufff3\032\ufff3\033\ufff3\034\ufff3\035" +
    "\ufff3\036\ufff3\037\ufff3\040\ufff3\041\ufff3\042\ufff3\001\002" +
    "\000\030\030\ufff0\031\ufff0\032\ufff0\033\ufff0\034\ufff0\035" +
    "\ufff0\036\ufff0\037\ufff0\040\ufff0\041\ufff0\042\ufff0\001\002" +
    "\000\030\030\ufff5\031\ufff5\032\ufff5\033\ufff5\034\ufff5\035" +
    "\ufff5\036\ufff5\037\ufff5\040\ufff5\041\ufff5\042\ufff5\001\002" +
    "\000\030\030\ufff8\031\ufff8\032\ufff8\033\ufff8\034\ufff8\035" +
    "\ufff8\036\ufff8\037\ufff8\040\ufff8\041\ufff8\042\ufff8\001\002" +
    "\000\030\002\030\030\020\031\010\032\016\033\007\034" +
    "\015\035\005\036\024\037\013\040\006\041\023\001\002" +
    "\000\030\030\020\031\010\032\016\033\007\034\015\035" +
    "\005\036\024\037\013\040\006\041\023\042\025\001\002" +
    "\000\030\030\ufff1\031\ufff1\032\ufff1\033\ufff1\034\ufff1\035" +
    "\ufff1\036\ufff1\037\ufff1\040\ufff1\041\ufff1\042\ufff1\001\002" +
    "\000\030\030\ufffb\031\ufffb\032\ufffb\033\ufffb\034\ufffb\035" +
    "\ufffb\036\ufffb\037\ufffb\040\ufffb\041\ufffb\042\ufffb\001\002" +
    "\000\030\030\ufff4\031\ufff4\032\ufff4\033\ufff4\034\ufff4\035" +
    "\ufff4\036\ufff4\037\ufff4\040\ufff4\041\ufff4\042\ufff4\001\002" +
    "\000\030\030\ufff6\031\ufff6\032\ufff6\033\ufff6\034\ufff6\035" +
    "\ufff6\036\ufff6\037\ufff6\040\ufff6\041\ufff6\042\ufff6\001\002" +
    "\000\030\002\uffff\030\uffff\031\uffff\032\uffff\033\uffff\034" +
    "\uffff\035\uffff\036\uffff\037\uffff\040\uffff\041\uffff\001\002" +
    "\000\030\030\ufff7\031\ufff7\032\ufff7\033\ufff7\034\ufff7\035" +
    "\ufff7\036\ufff7\037\ufff7\040\ufff7\041\ufff7\042\ufff7\001\002" +
    "\000\030\030\ufffc\031\ufffc\032\ufffc\033\ufffc\034\ufffc\035" +
    "\ufffc\036\ufffc\037\ufffc\040\ufffc\041\ufffc\042\ufffc\001\002" +
    "\000\030\030\ufff9\031\ufff9\032\ufff9\033\ufff9\034\ufff9\035" +
    "\ufff9\036\ufff9\037\ufff9\040\ufff9\041\ufff9\042\ufff9\001\002" +
    "\000\030\030\uffef\031\uffef\032\uffef\033\uffef\034\uffef\035" +
    "\uffef\036\uffef\037\uffef\040\uffef\041\uffef\042\uffef\001\002" +
    "\000\030\030\ufff2\031\ufff2\032\ufff2\033\ufff2\034\ufff2\035" +
    "\ufff2\036\ufff2\037\ufff2\040\ufff2\041\ufff2\042\ufff2\001\002" +
    "\000\004\043\027\001\002\000\030\030\ufffd\031\ufffd\032" +
    "\ufffd\033\ufffd\034\ufffd\035\ufffd\036\ufffd\037\ufffd\040\ufffd" +
    "\041\ufffd\042\ufffd\001\002\000\030\002\ufffe\030\ufffe\031" +
    "\ufffe\032\ufffe\033\ufffe\034\ufffe\035\ufffe\036\ufffe\037\ufffe" +
    "\040\ufffe\041\ufffe\001\002\000\004\002\000\001\002\000" +
    "\030\002\001\030\001\031\001\032\001\033\001\034\001" +
    "\035\001\036\001\037\001\040\001\041\001\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\027\000\020\002\010\003\016\004\011\005\020\006" +
    "\003\007\013\010\021\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\016\003\030\004\011\005\020\006\003\007\013" +
    "\010\021\001\001\000\012\005\025\006\003\007\013\010" +
    "\021\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$Parsercup3$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$Parsercup3$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$Parsercup3$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$Parsercup3$actions {
  private final Parsercup3 parser;

  /** Constructor */
  CUP$Parsercup3$actions(Parsercup3 parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$Parsercup3$do_action(
    int                        CUP$Parsercup3$act_num,
    java_cup.runtime.lr_parser CUP$Parsercup3$parser,
    java.util.Stack            CUP$Parsercup3$stack,
    int                        CUP$Parsercup3$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$Parsercup3$result;

      /* select the action based on the action number */
      switch (CUP$Parsercup3$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // Abstractness ::= NONABSTRACT 
            {
              Object RESULT =null;

              CUP$Parsercup3$result = parser.getSymbolFactory().newSymbol("Abstractness",6, ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), RESULT);
            }
          return CUP$Parsercup3$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // Abstractness ::= ABSTRACT 
            {
              Object RESULT =null;

              CUP$Parsercup3$result = parser.getSymbolFactory().newSymbol("Abstractness",6, ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), RESULT);
            }
          return CUP$Parsercup3$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // Type ::= OBJECT 
            {
              Object RESULT =null;

              CUP$Parsercup3$result = parser.getSymbolFactory().newSymbol("Type",4, ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), RESULT);
            }
          return CUP$Parsercup3$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // Type ::= FLOAT 
            {
              Object RESULT =null;

              CUP$Parsercup3$result = parser.getSymbolFactory().newSymbol("Type",4, ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), RESULT);
            }
          return CUP$Parsercup3$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // Type ::= INT 
            {
              Object RESULT =null;

              CUP$Parsercup3$result = parser.getSymbolFactory().newSymbol("Type",4, ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), RESULT);
            }
          return CUP$Parsercup3$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // Type ::= VOID 
            {
              Object RESULT =null;

              CUP$Parsercup3$result = parser.getSymbolFactory().newSymbol("Type",4, ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), RESULT);
            }
          return CUP$Parsercup3$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // Access ::= PACKAGE 
            {
              Object RESULT =null;

              CUP$Parsercup3$result = parser.getSymbolFactory().newSymbol("Access",5, ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), RESULT);
            }
          return CUP$Parsercup3$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // Access ::= PROTECTED 
            {
              Object RESULT =null;

              CUP$Parsercup3$result = parser.getSymbolFactory().newSymbol("Access",5, ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), RESULT);
            }
          return CUP$Parsercup3$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // Access ::= PRIVATE 
            {
              Object RESULT =null;

              CUP$Parsercup3$result = parser.getSymbolFactory().newSymbol("Access",5, ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), RESULT);
            }
          return CUP$Parsercup3$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // Access ::= PUBLIC 
            {
              Object RESULT =null;

              CUP$Parsercup3$result = parser.getSymbolFactory().newSymbol("Access",5, ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), RESULT);
            }
          return CUP$Parsercup3$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // Mod ::= Abstractness 
            {
              Object RESULT =null;

              CUP$Parsercup3$result = parser.getSymbolFactory().newSymbol("Mod",3, ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), RESULT);
            }
          return CUP$Parsercup3$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // Mod ::= Type 
            {
              Object RESULT =null;

              CUP$Parsercup3$result = parser.getSymbolFactory().newSymbol("Mod",3, ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), RESULT);
            }
          return CUP$Parsercup3$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // Mod ::= Access 
            {
              Object RESULT =null;

              CUP$Parsercup3$result = parser.getSymbolFactory().newSymbol("Mod",3, ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), RESULT);
            }
          return CUP$Parsercup3$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // Mods ::= Mod 
            {
              Object RESULT =null;

              CUP$Parsercup3$result = parser.getSymbolFactory().newSymbol("Mods",2, ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), RESULT);
            }
          return CUP$Parsercup3$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // Mods ::= Mods Mod 
            {
              Object RESULT =null;

              CUP$Parsercup3$result = parser.getSymbolFactory().newSymbol("Mods",2, ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.elementAt(CUP$Parsercup3$top-1)), ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), RESULT);
            }
          return CUP$Parsercup3$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // Dcl ::= Mods name semi 
            {
              Object RESULT =null;

              CUP$Parsercup3$result = parser.getSymbolFactory().newSymbol("Dcl",1, ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.elementAt(CUP$Parsercup3$top-2)), ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), RESULT);
            }
          return CUP$Parsercup3$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // Dcls ::= Dcl 
            {
              Object RESULT =null;

              CUP$Parsercup3$result = parser.getSymbolFactory().newSymbol("Dcls",0, ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), RESULT);
            }
          return CUP$Parsercup3$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= Dcls EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.elementAt(CUP$Parsercup3$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.elementAt(CUP$Parsercup3$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$Parsercup3$stack.elementAt(CUP$Parsercup3$top-1)).value;
		RESULT = start_val;
              CUP$Parsercup3$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.elementAt(CUP$Parsercup3$top-1)), ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$Parsercup3$parser.done_parsing();
          return CUP$Parsercup3$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // Dcls ::= Dcls Dcl 
            {
              Object RESULT =null;

              CUP$Parsercup3$result = parser.getSymbolFactory().newSymbol("Dcls",0, ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.elementAt(CUP$Parsercup3$top-1)), ((java_cup.runtime.Symbol)CUP$Parsercup3$stack.peek()), RESULT);
            }
          return CUP$Parsercup3$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

