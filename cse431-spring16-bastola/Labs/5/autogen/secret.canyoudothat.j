  ; 67: ClassNode[ASTNodeIsh, NodeDumpable, SymDeclaring, ClassDeclaring]  "Class public instance fact"
  .class public TestClasses/fact
  .super java/lang/Object
  ; standard initializer

.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method


  
  
  .method public static main([Ljava/lang/String;)V
   .limit locals 1
   .limit stack  3
   invokestatic TestClasses/fact/main431()V
   return
.end method


          .method public static factorial(I)I
          .limit locals 10
          .limit stack  30
                iload 0
                ldc 0
              isub
              ifne Label_1
              goto Label_0
              
Label_0:
                ldc 1
              ireturn
              
Label_1:
                  iload 0
                        iload 0
                        ldc 1
                      isub
                  invokestatic fact/factorial(I)I
                imul
              ireturn
          ireturn
          .end method

          .method public static main431()V
          .limit locals 10
          .limit stack  30
              ldc 0
            istore 0
                ldc "CS431 fact test"
            invokestatic fact/outStrln(Ljava/lang/String;)V
            
BeginWhile_0:
                iload 0
                ldc 10
              isub
              ifgt EndWhile_1
                      iload 0
                  invokestatic fact/computeFactorial(I)I
                istore 2
                    iload 0
                    ldc 1
                  iadd
                istore 0
            goto BeginWhile_0
            
EndWhile_1:
          return
          .end method

