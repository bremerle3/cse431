  ; 58: ClassNode[ASTNodeIsh, NodeDumpable, SymDeclaring, ClassDeclaring]  "Class public instance good1p"
  .class public TestClasses/good1p
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
   invokestatic TestClasses/good1p/main431()V
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
          .method public static main431()V
          .limit locals 10
          .limit stack  30
            ; AssignIsh
              ; LocalReferencing
              ; ComputeIsh
                ; ConstantProducing
                ldc 3
                ; ConstantProducing
                ldc 4
              iadd
            istore 1
            ; AssignIsh Done
            ; InvokeIsh
              ; InvokeReference
                ; LocalReferencing
                iload 1
            invokestatic TestClasses/good1p/outInt(I)V
            ; AssignIsh
              ; LocalReferencing
              ; ComputeIsh
                ; ConstantProducing
                ldc 5
                ; ConstantProducing
                ldc 7
              imul
            istore 1
            ; AssignIsh Done
            ; InvokeIsh
              ; InvokeReference
                ; LocalReferencing
                iload 1
            invokestatic TestClasses/good1p/outInt(I)V
          return
          .end method

