package submit;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Stack;

import autogen.Parser;
import autogen.Yylex;
import common.OpenFile;

import coursesolutions.*;
import coursesolutions.courseparser.CourseParser;
import coursesolutions.courseparser.CourseScanner;
import lab7.*;
import lab8.*;

/**
 * You must implement the visitor and the symbol table
 */

/*
 * Lab 4: Symbol Table Lab
 * Authors: Leo Bremer and Manil Bastola
 * 
 * Description: Our SymtabVisitor is compatible with the CSE431 course parser and
 * course symbol table. We also adapted the parser that we wrote from Lab 3 to work 
 * with this visitor. Additionally, we implemented our own stack-based symbol table
 * that the Visitor is compatible. Since we used common interfaces among these three
 * components (parser, symbol table, and AST), you should be able to mix and match
 * implementations. We did not implement every interface from the lab8 package at this
 * time (although we may do so on the final project if necessary). The interfaces with
 * unique visit methods are:
 * 
 * 		MethodDeclaring
 * 		FieldDeclaring
 * 		LocalDeclaring
 * 		ClassDeclaring
 * 		LocalReferencing
 * 		BlockIsh
 * 		ClassIsh
 * 
 * We have a few nodes and objects in jmm.cup that implement additional interfaces such as 
 * StaticReferencing, but we were able to get by without a visit(StaticReferencing a) 
 * method.
 * 		
 * Usage: Build using the build.xml Ant build targets. Set the SymtabVisitor member
 * variable "debugDisp" from 0 to 1 if you would like to enable to debugging printouts.
 * 
 * TODO: 
 * 		1) Implement other interfaces as needed during code generation (Lab 5). 
 * 		2) The current method of handling errors is to print our an error message
 * 		   using System.out.println(msg) and then return -1. In the future, it would
 * 		   be nice to have specific error codes for each type of compile error and
 * 		   wrap it up in exceptions for more robust error handling.
 * 
 */

public class SymtabVisitor extends NodeVisitor {

	/**
	 * This method lets us keep Main as-is before and after your implementation.
	 * Suggestion: develop as follows. Step 1: replace the return below with
	 * return new SymtabVisitor(new ClassBuildSymtab()); This will use your
	 * visitor but the class symbol table implementation Step 2: [ not necessary
	 * but if you want] replace the return below with return new
	 * SymtabVisitor(new BuildSymtab()); This will use your visitor and your
	 * symbol table implementation
	 */
	
	// Set to 1 to display debugging messages
	private static int debugDisp = 0;

	public static ReflectiveVisitor getVisitor() {
		// return new CourseSymtabVisitor(new CourseBuildSymtab());
		//return new SymtabVisitor(new CourseBuildSymtab());
		return new SymtabVisitor(new BuildSymtab());
	}

	public static java_cup.runtime.lr_parser getParser(String[] args) {
		// FIXME
		// If you want to use your parser,
		// then use the following line instead of the one below it:
		return new Parser(new Yylex(args.length == 0 ? new OpenFile("") : new OpenFile(args[0])));
		//return new CourseParser(new CourseScanner(args.length == 0 ? new OpenFile("") : new OpenFile(args[0])));
	}

	private SymtabInterface sti;

	public SymtabVisitor(SymtabInterface sti) {
		this.sti = sti;
	}
	
	// Method to add new (name, SymInfoObj) pairs to the symbol table
	public void updateSymTab(String name, AbstractNode SymInfoNode, TypeAttrs type, ModsAttrs mods, int level){
		SymInfo asym = new SymInfoObj(SymInfoNode, type, mods, level);
		sti.enter(name, asym);
		if(debugDisp == 1){
			System.out.println("Symtab entry -- Name: " + name + " | Type: " + type + " | Mods: " + mods);
		}
	}
	
	// Utility function to determine if two parameter lists are distinct
	public boolean checkParams(AbstractNode paramsNode1, AbstractNode paramsNode2){
		AbstractNode param, paramSib;
		// Get params of existing method as string
		String inSymTabParams = "";
		if (paramsNode1 != null) {
			param = paramsNode1.getFirst();
		} else {
			param = null;
		}
		while (param != null) {
			paramSib = param.getSib();
			inSymTabParams += param.toString().split(" ")[1] + ", ";
			param = paramSib;
		}
		// Get params of new method as string
		String newParams = "";
		if (paramsNode2 != null) {
			param = paramsNode2.getFirst();
		} else {
			param = null;
		}
		while (param != null) {
			paramSib = param.getSib();
			newParams += param.toString().split(" ")[1] + ", ";
			param = paramSib;
		}
		return (newParams.equals(inSymTabParams));
	}
	

	/**
	 * This method will cause the whole AST to be visited, but nothing will
	 * happen. It's good default behavior, but you'll need methods to handle
	 * various node types.
	 */
	public void defaultVisit(Object o) {
		AbstractNode n = (AbstractNode) o;
		visitChildren(n);
		//sti.out((AbstractNode) o, "hello");
	}

	public void visit(MethodDeclaring o) {
		AbstractNode a = o.getBody();
		String name = o.getName();
		SymInfo inSymTab = sti.lookup(name);
		
		// Check if name is already in the symbol table
		boolean canAdd = false;
		boolean isNull = (inSymTab == null);
		if(isNull){
			canAdd = true;
		}
		else{
			SymInfoObj inSymTabObj = (SymInfoObj)inSymTab;
			boolean diffNest = (sti.getCurrentNestLevel() != inSymTabObj.getNest());
			if(diffNest){
				canAdd = true;
			}
			else if ((inSymTab.getDefiningNode().getClass() == o.getClass())) //sameclass
			{
				AbstractNode inSymTabNode = inSymTab.getDefiningNode();
				AbstractNode paramsNode1, paramsNode2;
				paramsNode1 = inSymTabNode.getChild().getChild(); // Get params of existing method as string
				paramsNode2 = o.getParams().getChild();	// Get params of new method as string
				boolean sameParams = checkParams(paramsNode1, paramsNode2);
				if (!sameParams){
					canAdd = true;
				}
			}
		}
		if (canAdd){
			updateSymTab(name, (AbstractNode)o, o.getType(), o.getMods(), sti.getCurrentNestLevel());
			sti.incrNestLevel(); //hack
			visitChildren(a);
			sti.decrNestLevel();
		}
		else{
			System.out.println("Error: Duplicate References For Method!  " + name);
			System.exit(-1);
		}
	}
			
	public void visit(FieldDeclaring o) {
		String name = o.getName();
		if (debugDisp == 1) {
			System.out.println("Field Name: " + name +  " \tScope Level: "+sti.getCurrentNestLevel());
		}
		SymInfo inSymTab = sti.lookup(name);
		boolean canAdd = false;
		boolean isNull = (inSymTab == null);
		if(isNull){
			canAdd = true;
		}
		else{
			SymInfoObj inSymTabObj = (SymInfoObj)inSymTab;
			boolean diffNest = (sti.getCurrentNestLevel() != inSymTabObj.getNest());
			if(diffNest){
				canAdd = true;
			}
		}
			// Check if name is already in the symbol table
		if (canAdd){
			// If not, add it
			updateSymTab(name, (AbstractNode)o, o.getType(), o.getMods(), sti.getCurrentNestLevel());
		} 
		else {
			System.out.println(
					"Error: Duplicate Field Declaration!  " + name);
			System.exit(-1);
		}
	}
	
	public void visit(LocalDeclaring o) {
		String name = o.getName();
		// Use id only as hash key
	    String[] split_name = name.split(" ");
	    String id = split_name[split_name.length-1]; 
		if (debugDisp == 1) {
			System.out.println("Local Name: " + name+ " \tScope Level: "+sti.getCurrentNestLevel());
		    System.out.println("Local ID: " + id+ " \tScope Level: "+sti.getCurrentNestLevel());
		}
		SymInfo inSymTab = sti.lookup(id);
		SymInfoObj inSymTabObj = (SymInfoObj)inSymTab;
		// Check if name is already in the symbol table
		if (inSymTab == null ) {
			// If not, add it
			updateSymTab(id, (AbstractNode)o, o.getType(), null, sti.getCurrentNestLevel());
		} else {
			if (sti.getCurrentNestLevel() == inSymTabObj.getNest()){
			System.out.println(
					"Error: Duplicate Local Declaration!  " +name);
			System.exit(-1);
		}
			else{
				updateSymTab(id, (AbstractNode)o, o.getType(), null, sti.getCurrentNestLevel());
			}
		}
	}
	
	public void visit(ClassDeclaring o) {
		AbstractNode a = (AbstractNode) o;
		String name = o.getName();
		if (debugDisp == 1) {
			System.out.println("Class Name: " + name +  " \tScope Level: "+sti.getCurrentNestLevel());
		}
		SymInfo inSymTab = sti.lookup(name);
		// Check if name is already in the symbol table
		if (inSymTab == null) {
			// If not, add it
			updateSymTab(name, (AbstractNode)o, null, o.getMods(), sti.getCurrentNestLevel());
			visitChildren(a);
		} else {
			System.out.println(
					"Error: Duplicate Class Declaration!  " +name);
			System.exit(-1);
		}
	}
	
	public void visit(LocalReferencing o) {
		String name = o.getId();
		if (debugDisp == 1) {
			System.out.println("Class Name: " + name +  " \tScope Level: "+sti.getCurrentNestLevel());
		}
		SymInfo inSymTab = sti.lookup(name);
		SymInfoObj inSymTabObj = (SymInfoObj)inSymTab;

		// Check if name is already in the symbol table
		boolean isNull = (inSymTab == null);
		if(isNull){
			System.out.println("Error: Attempted to dereference undeclared variable! " + name);
			System.exit(-1);
		}
		else
		{
			o.setSymInfo(inSymTab);
		}
		
	
	}

	
	// Increment scope when entering a block, decrement when leaving a block
	public void visit(BlockIsh o) {
		if(debugDisp == 1)
			System.out.println("increment scope in Blockish Visit");
		AbstractNode a = (AbstractNode) o;
		sti.incrNestLevel();
		visitChildren(a);
		if(debugDisp == 1)
			System.out.println("decrement scope in Blockish Visit");
		sti.decrNestLevel();
	}
	
	// Increment scope when entering a classbody, decrement when leaving a classbody
	public void visit(ClassIsh o) {
		if(debugDisp == 1)
			System.out.println("increment scope in ClassIsh Visit");
		AbstractNode a = (AbstractNode) o;
		sti.incrNestLevel();
		visitChildren(a);
		if(debugDisp == 1)
			System.out.println("decrement scope in ClassIsh Visit");
		sti.decrNestLevel();
	}
	
}

/**
 * OPTIONAL (or from Studio if you want): Your symbol table implementation
 */
class BuildSymtab extends Symtab implements SymtabInterface {

	public Stack<HashMap<String, SymInfo>> table = new Stack<HashMap<String, SymInfo>>();

	public void incrNestLevel() {
		// out("Nest level now " + getCurrentNestLevel());
		table.push(new HashMap<String, SymInfo>());
	}

	public void decrNestLevel() {
		// out("Nest level now " + getCurrentNestLevel());
		if (!table.empty()) {
			table.pop();
		}
	}

	public int getCurrentNestLevel() {
		return table.size();
	}

	public SymInfo lookup(String s) {
		if (!table.empty()) {
			for (int i = table.size() - 1; i >= 0; --i) {
				HashMap<String, SymInfo> curMap = table.elementAt(i);
				SymInfo info = curMap.get(s);
				if (info != null){
					return (info);
				}
			}
		}
		return null;
	}

	public void enter(String s, SymInfo info) {
		if (!table.empty()) {
			HashMap<String, SymInfo> map = table.peek();
			map.put(s, info);
		}
	}
}


