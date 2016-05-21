  ; 180: ClassNode[ASTNodeIsh, NodeDumpable, SymDeclaring, ClassDeclaring]  "Class public instance fact"
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


          ; MethodDeclaring
          .method public static outStr(Ljava/lang/String;)V
          .limit locals 10
          .limit stack  30
            ; AssignIsh
              ; LocalReferencing
              ; InvokeReference
              getstatic java/lang/System/out Ljava/io/PrintStream;
            astore 1
            ; AssignIsh Done
            ; InvokeIsh
              ; InvokeReference
                ; LocalReferencing
                aload 1
                ; LocalReferencing
                aload 0
            invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
          return
          .end method

          ; MethodDeclaring
          .method public static outStrln(Ljava/lang/String;)V
          .limit locals 10
          .limit stack  30
            ; AssignIsh
              ; LocalReferencing
              ; InvokeReference
              getstatic java/lang/System/out Ljava/io/PrintStream;
            astore 1
            ; AssignIsh Done
            ; InvokeIsh
              ; InvokeReference
                ; LocalReferencing
                aload 1
                ; LocalReferencing
                aload 0
            invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
          return
          .end method

          ; MethodDeclaring
          .method public static outInt(I)V
          .limit locals 10
          .limit stack  30
            ; AssignIsh
              ; LocalReferencing
              ; InvokeReference
              getstatic java/lang/System/out Ljava/io/PrintStream;
            astore 1
            ; AssignIsh Done
            ; InvokeIsh
              ; InvokeReference
                ; LocalReferencing
                aload 1
                ; LocalReferencing
                iload 0
            invokevirtual java/io/PrintStream/print(I)V
          return
          .end method

          ; MethodDeclaring
          .method public static computeFactorial(I)I
          .limit locals 10
          .limit stack  30
            ; InvokeIsh
              ; InvokeReference
                ; ConstantProducing
                ldc "Computing factorial of "
            invokestatic TestClasses/fact/outStr(Ljava/lang/String;)V
            ; InvokeIsh
              ; InvokeReference
                ; LocalReferencing
                iload 0
            invokestatic TestClasses/fact/outInt(I)V
            ; AssignIsh
              ; LocalReferencing
              ; InvokeIsh
                ; InvokeReference
                  ; LocalReferencing
                  iload 0
              invokestatic TestClasses/fact/factorial(I)I
            istore 1
            ; AssignIsh Done
            ; InvokeIsh
              ; InvokeReference
                ; ConstantProducing
                ldc " is "
            invokestatic TestClasses/fact/outStr(Ljava/lang/String;)V
            ; InvokeIsh
              ; InvokeReference
                ; LocalReferencing
                iload 1
            invokestatic TestClasses/fact/outInt(I)V
            ; InvokeIsh
              ; InvokeReference
                ; ConstantProducing
                ldc ""
            invokestatic TestClasses/fact/outStrln(Ljava/lang/String;)V
            ; ComputeIsh
              ; LocalReferencing
              iload 1
            ireturn
          ireturn
          .end method

          ; MethodDeclaring
          .method public static factorial(I)I
          .limit locals 10
          .limit stack  30
            ; IfIsh
              ; CompareIsh
                ; LocalReferencing
                iload 0
                ; ConstantProducing
                ldc 0
              isub
              ifeq Label_1
              iconst_0
              goto Label_2
              
Label_1:
              iconst_1
              
Label_2:
              ; ComputeIsh
              ifeq Label_3
                ; ConstantProducing
                ldc 1
              ireturn
              
Label_3:
              ; ComputeIsh
                ; ComputeIsh
                  ; LocalReferencing
                  iload 0
                  ; InvokeIsh
                    ; InvokeReference
                      ; ComputeIsh
                        ; LocalReferencing
                        iload 0
                        ; ConstantProducing
                        ldc 1
                      isub
                  invokestatic TestClasses/fact/factorial(I)I
                imul
              ireturn
              
Label_4:
          ireturn
          .end method

          ; MethodDeclaring
          .method public static main431()V
          .limit locals 10
          .limit stack  30
            ; AssignIsh
              ; LocalReferencing
              ; ConstantProducing
              ldc 0
            istore 0
            ; AssignIsh Done
            ; InvokeIsh
              ; InvokeReference
                ; ConstantProducing
                ldc "CS431 fact test"
            invokestatic TestClasses/fact/outStrln(Ljava/lang/String;)V
            ; WhileIsh
            
BeginWhile_0:
            ; comp node is: 
            ; CompareIsh
              ; LocalReferencing
              iload 0
              ; ConstantProducing
              ldc 10
            isub
            iflt Label_5
            iconst_0
            goto Label_6
            
Label_5:
            iconst_1
            
Label_6:
            iconst_0
            isub
            ifle EndWhile_0
              ; AssignIsh
                ; LocalReferencing
                ; InvokeIsh
                  ; InvokeReference
                    ; LocalReferencing
                    iload 0
                invokestatic TestClasses/fact/computeFactorial(I)I
              istore 1
              ; AssignIsh Done
              ; AssignIsh
                ; LocalReferencing
                ; ComputeIsh
                  ; LocalReferencing
                  iload 0
                  ; ConstantProducing
                  ldc 1
                iadd
              istore 0
              ; AssignIsh Done
            goto BeginWhile_0
            iflt EndWhile_0
            
EndWhile_0:
          return
          .end method

