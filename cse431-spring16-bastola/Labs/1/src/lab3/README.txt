/* Manil Bastola
 * Lab 1 Readme
/

Teammate:

I and Rebecca Siow started working on this lab as a team. However, she had to go to Chicago for the weekend and we implemented our FSA.java indepenedently.
In the end we both had two working versions so we decided to submit independently for this lab.

Design:

I have defined bad state with 0th row of GOTO table. My initial state begins with Row 1.
 
My program does not allow ",","|",":=","" and ";" as variable names for terminals and non-terminals.

My GoTo table which is pretty straight forward. In my Action Table, I decided to take action for all columns for a given GOTO[row][column]. For example: The Row 6
of my action table has 2's in all columns. This was to incorporate variable names such as "with","terminal" etc which are defined in cup specifications and would 
lead to problems in the next GOTO[state][t.type()] state if not intercepted by ACTION table and redirected to GOTO[state][5]. Here, 5 represents STR type which means 
even if t.type()= "non", the program does not GOTO[state][7] (which is 0 and is a bad state).  

Tests:

The program gives expected results for all 5 test files in the Test directory. Additionally I checked the following test cases:

Self Test 1: (Test with "blank","semi" token str names) 
non terminal Obj blank, semi;
    terminal Obj terminal, start;

start with semi;

semi
	::= start blank
	|   terminal
	;

blank
	::= terminal blank
	|   terminal 
	|   start;

result:

Start semi
Edge semi blank start
Edge semi $FINAL$ terminal
Edge blank blank terminal
Edge blank $FINAL$ terminal
Edge blank $FINAL$ start

Self test2:(When str after "with" is a terminal)
non terminal Obj blank, semi;
    terminal Obj terminal, start;

start with start;

start
	::= start terminal
	|   terminal
	;

blank
	::= terminal blank
	|   terminal 
	|   start;

result:

ABORT
Error: Expected Non-Terminal on LHS: start


Self test 3:(2 terminals on a same line)

non terminal Obj blank, semi;
    terminal Obj terminal, start;

start with semi;

semi
	::= start terminal
	|   terminal
	;

blank
	::= terminal blank
	|   terminal 
	|   start;

result:

Start semi
ABORT
Error: Expected Non-Terminal: terminal

