package submit;

import coursesolutions.*;
import coursesolutions.courseparser.CourseParser;
import coursesolutions.courseparser.CourseScanner;
import lab7.*;
import lab8.*;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Stack;

import autogen.*;

import common.OpenFile;

/**
 */

/*
 * CSE 431: Lab 5
 * Authors: Leo Bremer and Manil Bastola
 */

public class CodeGenVisitor extends NodeVisitor {
	
	public static boolean commentsOn = true;
	
	public int regCount = 0;
	public int labelCount = 0;
	public int bodylabelCount = 0;
	public String thisClass = "";
	public HashMap<String, Integer> fieldMap = new HashMap<String, Integer>();
	public Stack<String> scopeStack = new Stack<String>();
	String defaultPath = "TestClasses";
	
	public static java_cup.runtime.lr_parser getParser(String[] args) {
		// FIXME
		// If you want to use your parser, then use the following line instead
		//    of the one below it:
		// return new Parser(new Yylex(args.length == 0 ? new OpenFile("") : new OpenFile(args[0])));
		return new CourseParser(new CourseScanner(args.length == 0 ? new OpenFile("") : new OpenFile(args[0])));
	}

	public static ReflectiveVisitor getSymtabVisitor() { 
		// FIXME
		// If you want to use your own symbol table code, then
		//    replace the line below with something suitable
		return new CourseSymtabVisitor(new CourseBuildSymtab());
	}


	/**
	 * Change this method to return the visitor you want for code generation. As
	 * given to you, it runs the course-sponsored solution
	 * (CourseProjectCodeGenVisitor); Change the return to
	 * "new CodeGenVisitor()" to return an instance of this class instead.
	 * 
	 * @return the visitor for performing code generation
	 */

	public static ReflectiveVisitor getCodeGenVisitor() {
		return new CodeGenVisitor(); // Change this to CodeGenVisitor() to
		//return new CourseCodeGenVisitor(); // Change this to CodeGenVisitor() to
		// test your code
	}

	public static ReflectiveVisitor getTypeSetVisitor() {
		return new CourseTypeSetVisitor();
	}

	private void emit(String s) {
		PrintStream ps = System.out;
		out(ps, s);
	}

	private void emit(NodeDumpable a, String s) {
		emit("; " + a.dump());
		emit(s);
	}

	private void emitComment(String s) {
		if(commentsOn){
			emit("; " + s);
		}
	}

	private void skip(int num) {
		for (int i = 0; i < num; ++i)
			emit("");
	}

	/**
	 * This outputs a standard prelude, with the class extending Object, a dummy
	 * method for main(String[] args) that calls main431 Thus, your test file
	 * must have a static main431 to kick things off
	 */

	
	public void visit(ClassDeclaring c) {
		//emitComment("CSE431 automatically generated code file");
		//emitComment("");
		emit(c, ".class public TestClasses/" + c.getName());
		emit(".super java/lang/Object");
		emit("; standard initializer\n\n" + ".method public <init>()V\n"
				+ "   aload_0\n"
				+ "   invokenonvirtual java/lang/Object/<init>()V\n"
				+ "   return\n" + ".end method\n\n");
		//emitComment("dummy main to call our main because we don't handle arrays");
		skip(2);
		emit(".method public static main([Ljava/lang/String;)V\n"
				+ "   .limit locals 1\n" + "   .limit stack  3\n"
				+ "   invokestatic " + "TestClasses/" + c.getName() + "/main431()V\n"
				+ "   return\n" + ".end method\n\n");
		thisClass = "TestClasses";
		AbstractNode node = (AbstractNode)c;
		String[] tmp = node.getName().split(" ");
		String className = tmp[tmp.length-1];
		scopeStack.push(className);
		visitChildren((AbstractNode) c);
		scopeStack.pop();
	}

	public void defaultVisit(Object o) {
		AbstractNode n = (AbstractNode) o;
		out("Ignoring " + n.dump());
		visitChildren(n);
	}
	
	public String getFullPath(){
		String fullPath = defaultPath;
		for (int i = 0; i < scopeStack.size();++i){
			fullPath += "/";
			fullPath += scopeStack.elementAt(i);
		}
		fullPath += "/";
		return fullPath;
	}
	
	public void visit(FieldDeclaring i) {
		AbstractNode n = (AbstractNode) i;
		visitChildren(n);
		String[] tmp = n.getName().split(" ");
		String methodName = tmp[tmp.length-1];
		fieldMap.put(methodName, 1);
	}
	
	public void visit(MethodDeclaring i) {
		emitComment("MethodDeclaring");		
		regCount = 0;
		AbstractNode node = (AbstractNode)i;
		AbstractNode a = i.getBody();
		ModsAttrs modifiers = i.getMods();
		TypeAttrs returnType = i.getType();		
		AbstractNode param = i.getParams().getChild();
		String paramstype = "";
		boolean first = true;
		while (param != null){
			TypeAttrs o = ((SymDeclaring)param).getSymInfo().getType();
			((SymDeclaring)param).getSymInfo().setRegister(regCount);
			++regCount;
			paramstype += o.getTypeString();
			param = param.getSib();
			first = false;
		}
		////System.err.println(paramstype);
		String[] tmp = node.getName().split(" ");
		String methodName = tmp[tmp.length-1];
		// Add to the hash map of user-defined function
		fieldMap.put(methodName, 1);
		//System.err.println(methodName+"yyyyyyyyy");
		String outstr = ".method ";
		if (modifiers.isPublic())
			outstr += "public ";
		if (modifiers.isStatic())
			outstr += "static ";
		outstr += methodName+"("+ paramstype +")";
		outstr += returnType.getTypeString();
		emit(outstr);
		emit(".limit locals 10");
		emit(".limit stack  30");
		visitChildren(a);
		emitReturn(returnType.getTypeString());
		emit(".end method\n");
	}
	
	public void emitReturn(String typeStr){
		if(typeStr.equals("I")){
			emit("ireturn");
		}
		else if(typeStr.equals("V")){
			emit("return");
		}
		else if(typeStr.equals("F")){
			emit("freturn");
		}
		else if(typeStr.equals("J")){
			emit("lreturn");
		}
		else if(typeStr.equals("Z")){
			emit("ireturn");
		}
	}
	
	public void emitOp(String nodeType, String op){
		String pre = "";
		if(nodeType.equals("I")){ //integer operation
			pre = "i";
		}
		else if(nodeType.equals("D")){ //load Double
			pre = "d";	
		}
		else if(nodeType.equals("F")){
			pre = "f";
		}
		else if(nodeType.equals("J")){ // long int.
			pre = "l";	
		}
		else if(nodeType.equals("S")){ //short int.
			pre = "i";	
		}
		else{
			//////emitComment("Operation not defined");
			return;
		}
		emit(pre+op);
	}
	
	public void store(String o, int reg){
		String toRegister = Integer.toString(reg);
		if(o.equals("I")){ //store int from stack to register reg
			emit("istore "+toRegister);	
		}
		else if(o.equals("D")){ //double
 			emit("dstore "+toRegister);	
		}
		else if(o.equals("F")){ //float
 			emit("fstore "+toRegister);	
		}
		else if(o.equals("J")){ //long
 			emit("lstore "+toRegister);	
		}
		else if(o.equals("S")){ //short
 			emit("istore "+toRegister);	
		}
		else if(o.equals("Z")){ //boolean.
 			emit("istore "+toRegister);	
		}
		else{ //if(o.equals("Ljava/io/PrintStream;")){ //store string
			emit("astore "+toRegister);	
		}
		//this.printReg(o, reg);
	}
	
	public void load(String o, int reg){
		String fromRegister = Integer.toString(reg);
		if(o.equals("I")){ //load int from register reg into stack
			emit("iload "+fromRegister);
		}
		else if(o.equals("D")){ //load Double
 			emit("dload "+fromRegister);	
		}
		else if(o.equals("F")){
			emit("fload "+fromRegister);//Integer.toString(regCount)
		}
		else if(o.equals("J")){ //Load long int.
 			emit("lload "+fromRegister);	
		}
		else if(o.equals("S")){ //load short int.
 			emit("iload "+fromRegister);	
		}
		else if(o.equals("Z")){ //load boolean, interpret as int.
 			emit("iload "+fromRegister);	
		}
		else{ //if(o.equals("Ljava/lang/String;")){ //load string
 			emit("aload "+fromRegister);	
		}
	}
	
	public void visit(ComputeIsh i) {
		emitComment("ComputeIsh");
		AbstractNode n = (AbstractNode) i;
		AbstractNode parent = n.getParent();
		if(parent instanceof IfIsh){
			if(n.getSib() != null){
				emit("ifeq Label_" + (labelCount+1));
			}
			visitChildren(n);
			String op = i.getOperation();
			String nodeType = n.getNodeType().toString();

			if(op.equals("return")){
				emitReturn(nodeType);
			}
			else{
				emitOp(nodeType, op);
				if(n.getSib() != null){
					emit("goto Label_"+(labelCount+2));
				}
			}
		
			labelCount++;
			String label = "\nLabel_" + labelCount + ":";
			//labelCount++;
			emit(label);
			return;
		}
		visitChildren(n);
		String op = i.getOperation();
		String nodeType = n.getNodeType().toString();
		if(nodeType.equals("Z")){
			nodeType = "I";
		}
		if(op.equals("return")){
			emitReturn(nodeType);
		}
		else{
			emitOp(nodeType, op);
		}
	}

	public void visit(CompareIsh i) {
		emitComment("CompareIsh");
		AbstractNode n = (AbstractNode) i;
		visitChildren(n);
		emit("isub");
		// Make label for true case
		labelCount++;
		int trueCount = labelCount;
		String trueLabel = "\nLabel_" + trueCount + ":";
		// Make label for end of compare
		labelCount++;
		int endCount = labelCount;
		String endLabel = "\nLabel_" + endCount + ":";
		emitCompare(i.getCompare(), trueCount);
		emit("iconst_0");
		emit("goto Label_" + endCount);;
		// True case
		emit(trueLabel);
		emit("iconst_1");		
		emit(endLabel);
	}

	public void visit(IfIsh i) {
		emitComment("IfIsh");		
		AbstractNode n = (AbstractNode) i;
		visitChildren(n);
	}

	public void visit(ConstantProducing i) {
		emitComment("ConstantProducing");		
		String value = i.getConstant();	
		emit("ldc "+value);
	}
	
	public void visit(ShortOrIsh i) {
		AbstractNode n = (AbstractNode) i;
		AbstractNode leftChild = ((AbstractNode)i).getChild().getFirst();
		AbstractNode parent = ((AbstractNode)i).getParent();
		emitComment("ShortOr "+leftChild.getName());
		leftChild.accept(this);
		emit("iconst_0");
		emit("isub");
		// Make label for true case
		labelCount++;
		int trueLabelNum = labelCount;
		String trueBranch = "\nLabel_" + trueLabelNum + ":";
		// Make label for end
		labelCount++;
		int endLabelNum = labelCount;
		String endBranch = "\nLabel_" + endLabelNum + ":";
		emit("ifne Label_" + trueLabelNum);
		emit("iconst_0");
		((AbstractNode)i).getChild().getFirst().getSib().accept(this);
		emit("isub");
		emit("ifne Label_" + trueLabelNum);
		emit("iconst_0");
		emit("goto Label_" + endLabelNum);
		emit(trueBranch);
		emit("iconst_1");
		emit(endBranch);
		return;
	}
	
	public void visit(ShortAndIsh i) {
		emitComment("ShortAndIsh");
		AbstractNode n = (AbstractNode) i;
		AbstractNode leftChild = ((AbstractNode)i).getChild().getFirst();
		AbstractNode parent = ((AbstractNode)i).getParent();
		emitComment("ShortOr "+leftChild.getName());
		leftChild.accept(this);
		emit("iconst_0");
		emit("isub");
		// Make label for true case
		labelCount++;
		int trueLabelNum = labelCount;
		String trueBranch = "\nLabel_" + trueLabelNum + ":";
		// Make label for end
		labelCount++;
		int endLabelNum = labelCount;
		String endBranch = "\nLabel_" + endLabelNum + ":";
		emit("ifeq Label_" + trueLabelNum);
		emit("iconst_0");
		((AbstractNode)i).getChild().getFirst().getSib().accept(this);
		emit("isub");
		emit("ifeq Label_" + trueLabelNum);
		emit("iconst_1");
		emit("goto Label_" + endLabelNum);
		emit(trueBranch);
		emit("iconst_0");
		emit(endBranch);
		return;
	}
	
	public void visit(WhileIsh i) {
		emitComment("WhileIsh");		
		AbstractNode n = (AbstractNode)i;
		emit("\nBeginWhile_" + bodylabelCount + ":");
		AbstractNode comp = n.getChild().getFirst();
		emitComment("comp node is: " + comp.getName());
		comp.accept(this);
		emit("iconst_0");
		emit("isub");
		emit("ifle EndWhile_"+bodylabelCount);
		// Execute the while loop		
		AbstractNode body = n.getChild().getFirst().getSib();
		body.accept(this);
		// Jump to top of while loop at end of body
		emit("goto BeginWhile_" + bodylabelCount);
		CompareIsh compish = (CompareIsh)n.getChild().getFirst();
		emitCompareWhile(compish.getCompare(),bodylabelCount);		
		// Emit label for bottom of while loop
		emit("\nEndWhile_" + bodylabelCount + ":");
		bodylabelCount++;
	}
	
	
	public void visit(LocalDeclaring i) {	
		AbstractNode n = (AbstractNode) i;		
		String nodeName = n.getName();
		i.getSymInfo().setRegister(regCount);
		++regCount;
	}
	
	public void visit(LocalReferencing i) {
		emitComment("LocalReferencing");
		AbstractNode n = (AbstractNode) i;
		AbstractNode parent = n.getParent();
		if(!(parent instanceof AssignIsh)){			
			int reg = i.getSymInfo().getRegister();
			String o = (i.getSymInfo().getType()).getTypeString();
			load(o,reg);
		}					
	}
	
	public void visit(InvokeReference i) {
		emitComment("InvokeReference");
		AbstractNode n = (AbstractNode) i;		
		AbstractNode child = n.getChild().getFirst();		
		AbstractNode parent = n.getParent();		
		String packageName;
		visitChildren(n);		
		if ((i instanceof StaticReferencing) && (parent instanceof AssignIsh)){
			packageName = child.toString();
			packageName = packageName.substring(1, packageName.length()-1);
			String fieldName = ((StaticReferencing)i).getFieldName().toString();
			String retType = ((StaticReferencing)i).getResultingType().toString();
			emit("getstatic " + packageName + "/" + fieldName + " " + retType);
		}
	}
	
	public void visit(InvokeIsh i) {		
		emitComment("InvokeIsh");
		//System.err.println("In InvokeIsh node");
		String className, fieldName, parameters, retType;
		AbstractNode n = (AbstractNode) i;
		visitChildren(n);
		AbstractNode body = n.getChild().getFirst();
		FieldReferencing field = (FieldReferencing) body;		// Get object being referenced
		fieldName = field.getFieldName();
		AbstractNode args = body.getSib().getChild(); 	// Get arguments
		if ( args instanceof LocalReferencing ){
			LocalReferencing argNode = (LocalReferencing) args; 		// Get node containing argument refnode
			parameters = argNode.getSymInfo().getType().getTypeString();
		}
		else if (args instanceof ConstantProducing){
			parameters = args.getNodeType().toString();
		}
		else if (args instanceof ComputeIsh) {	//if (args instanceof ComputeIsh){
			AbstractNode TypeCheck = args.getChild();
			parameters = TypeCheck.getNodeType().toString();
		}
		else
		{
			parameters = "";
		}

		AbstractNode FieldNameNode = body.getChild().getFirst();
		if (FieldNameNode instanceof LocalReferencing ){
			LocalReferencing refNode = (LocalReferencing)FieldNameNode;
			className = refNode.getSymInfo().getType().getTypeString().substring(1).replace(";", "/");
		}
		else{
				className = FieldNameNode.toString().substring(1).replace(";", "/");
		}
		String firstPath = className.split("/")[0];
		boolean javaPath = firstPath.equals("java");
		boolean ourPath = firstPath.equals(defaultPath);
		
		if(!javaPath && !ourPath){
			className = getFullPath();
		}
		retType = field.getResultingType().toString();
		if(parameters.equals("Z")){
			parameters = "I";
		}
		if(body instanceof StaticReferencing){
			emit("invokestatic " + className + fieldName + "(" + parameters + ")" + retType);
		}
		else{
			emit("invokevirtual " + className + fieldName + "(" + parameters + ")" + retType);
		}
		AbstractNode parent = n.getParent();
		if(parent instanceof IfIsh){
			String nodeType = n.getNodeType().toString();
			emitReturn(nodeType);
			labelCount++;
			String label = "\nLabel_" + labelCount + ":";
			emit(label);
			return;	
		}
	}
	
	public void visit(AssignIsh i) {
		//System.err.println("In assign node");
		emitComment("AssignIsh");
		AbstractNode n = (AbstractNode) i;
		visitChildren(n);		
		AbstractNode leftChild = i.getAssignTypeNode();
		AbstractNode rightChild = i.getSubjectNode();
		if (rightChild instanceof LocalReferencing){
			int regRight = ((LocalReferencing)rightChild).getSymInfo().getRegister();
			String o = ((LocalReferencing)rightChild).getSymInfo().getType().getTypeString();
			load(o,regRight);
		}
		if (leftChild instanceof LocalReferencing){			
			int regLeft = ((LocalReferencing)leftChild).getSymInfo().getRegister();
			TypeAttrs o = ((LocalReferencing)leftChild).getSymInfo().getType();			
			store(o.getTypeString(),regLeft);
		}
		if (leftChild instanceof ShortOrIsh){			
			int regLeft = ((LocalReferencing)leftChild).getSymInfo().getRegister();
			TypeAttrs o = ((LocalReferencing)leftChild).getSymInfo().getType();			
			store(o.getTypeString(),regLeft);
		}
		if (leftChild instanceof ShortAndIsh){			
			int regLeft = ((LocalReferencing)leftChild).getSymInfo().getRegister();
			TypeAttrs o = ((LocalReferencing)leftChild).getSymInfo().getType();			
			store(o.getTypeString(),regLeft);
		}
		emitComment("AssignIsh Done");
	}
	
	public void emitCompare(String typeStr, int labelCount){
		if(typeStr.equals("lt")){
			emit("iflt Label_"+labelCount);
		}
		if(typeStr.equals("gt")){
			emit("ifgt Label_"+labelCount);
		}
		if(typeStr.equals("le")){
			emit("ifle Label_"+labelCount);
		}
		if(typeStr.equals("ge")){
			emit("ifge Label_"+labelCount);
		}
		if(typeStr.equals("ne")){
			emit("ifne Label_"+labelCount);
		}
		if(typeStr.equals("eq")){
			emit("ifeq Label_"+labelCount);
		}
	}
	
	public void emitCompareWhile(String typeStr, int labelCount){
		if(typeStr.equals("lt")){
			emit("iflt EndWhile_"+labelCount);
		}
		if(typeStr.equals("gt")){
			emit("ifgt EndWhile_"+labelCount);
		}
		if(typeStr.equals("le")){
			emit("ifle EndWhile_"+labelCount);
		}
		if(typeStr.equals("ge")){
			emit("ifge EndWhile_"+labelCount);
		}
		if(typeStr.equals("ne")){
			emit("ifne EndWhile_"+labelCount);
		}
		if(typeStr.equals("eq")){
			emit("ifeq EndWhile_"+labelCount);
		}
	}
	
	public void printReg(TypeAttrs o, int reg){
		emit(";;;;;;;;;;;;;;;;;;printReg();;;;;;;;;;;;;;;;;;;;;;;;;");
		emit("getstatic java/lang/System/out Ljava/io/PrintStream;");
		emit("ldc \"Register "+reg+" is now \"");
		emit("invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V");
		emit("getstatic java/lang/System/out Ljava/io/PrintStream;");
		load(o.getTypeString(),reg);
		emit("invokestatic java/lang/String/valueOf(I)Ljava/lang/String;");
		emit("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
		emit(";;;;;;;;;;;;;;;;;;printReg();;;;;;;;;;;;;;;;;;;;;;;;;");
	}	
}


