package studio8;

import java.util.Hashtable;

import lab8.*;

/** Solution authored by:
 * 
 *  ( team members' names )
 *
 */

public class TeamSymtab extends Symtab implements SymtabInterface {
	private int depth;
	   Hashtable<String, SymInfo> symbolHashTable = new Hashtable<String, SymInfo>();

	/** Should never return a negative integer 
	 */
	
	public int getCurrentNestLevel() {
		
		return -1;  // you must fix this
	}
	
	/** Opens a new scope, retaining outer ones */
	
	public void incrNestLevel() {
		this.depth = this.depth + 1;
		
	}
	
	/** Closes the innermost scope */
	
	public void decrNestLevel() {
		this.depth = this.depth - 1;
		
	}
	
	/** Enter the given symbol information into the symbol table.  If the given
	 *    symbol is already present at the current nest level, do whatever is most
	 *    efficient, but do NOT throw any exceptions from this method.
	 */
	
	public void enter(String s, SymInfo info) {
		SymInfo oldsym = lookup(s);
		if ((oldsym != null) & (oldsym.getReg().equals(depth))){
			System.out.println("Error");
			SymInfo new_sym = createNewSymbol
		}
	}
	
	/** Returns the information associated with the innermost currently valid
	 *     declaration of the given symbol.  If there is no such valid declaration,
	 *     return null.  Do NOT throw any excpetions from this method.
	 */
	
	public SymInfo lookup(String s) {
		SymInfo sym = symbolHashTable.get(s);
		while (sym != null){
			if (sym.getType().equals(s)){
				return sym;
			}			
		}
		return null;
	}

}
