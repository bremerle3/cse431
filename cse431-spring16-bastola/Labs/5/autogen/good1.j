  ; 24: ClassNode[ASTNodeIsh, NodeDumpable, SymDeclaring, ClassDeclaring]  "Class public instance good1"
  .class public TestClasses/good1
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
   invokestatic TestClasses/good1/main431()V
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
          return
          .end method

