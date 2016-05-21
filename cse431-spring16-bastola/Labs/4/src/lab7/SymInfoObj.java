package lab7;

import lab8.ModsAttrs;
import lab8.SymInfo;
import lab8.TypeAttrs;

public class SymInfoObj implements SymInfo {
	protected TypeAttrs symType;
	protected ModsAttrs symMods;
	protected int symReg;
	protected AbstractNode defNode;
	protected int nestLevel;

	public SymInfoObj(AbstractNode n, TypeAttrs t, ModsAttrs m, int level) {
		symType = t;
		symMods = m;
		symReg = -1;
		defNode = n;
		nestLevel = level;
	}

	public int getNest() {
		return nestLevel;
	}
	
	public TypeAttrs getType() {
		return symType;
	}

	public ModsAttrs getMods() {
		return symMods;
	}

	public AbstractNode getDefiningNode() {
		return defNode;
	}

	public int getRegister() {
		return symReg;
	}

	public void setRegister(int r) {
		symReg = r;
	}
	
	public String toString() {
		String strType = "null";
		String strMod = "null";
		if (symType != null){
			strType = symType.toString();
		}
		if (symMods != null){
			strMod =symMods.toString();
		}
		return ( " Type: "+ strType+" Mods: "+ strMod +" Regs: "+symReg);
	}

}