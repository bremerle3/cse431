  ; 169: ClassNode[ASTNodeIsh, NodeDumpable, SymDeclaring, ClassDeclaring]  "Class public instance short"
  .class public TestClasses/short
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
   invokestatic TestClasses/short/main431()V
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
          .method public static youBlewShortOr()Z
          .limit locals 10
          .limit stack  30
            ; InvokeIsh
              ; InvokeReference
                ; ConstantProducing
                ldc "You chose unwisely, evaluating the second child of a short-or "
            invokestatic TestClasses/short/outStr(Ljava/lang/String;)V
            ; InvokeIsh
              ; InvokeReference
                ; ConstantProducing
                ldc "whose first child was true"
            invokestatic TestClasses/short/outStrln(Ljava/lang/String;)V
            ; InvokeIsh
              ; InvokeReference
                ; ComputeIsh
                  ; ConstantProducing
                  ldc 1
                ineg
            invokestatic java/lang/System/exit(I)V
            ; ComputeIsh
              ; ConstantProducing
              ldc 1
            ireturn
          ireturn
          .end method

          ; MethodDeclaring
          .method public static gotit()V
          .limit locals 10
          .limit stack  30
            ; InvokeIsh
              ; InvokeReference
                ; ConstantProducing
                ldc "Check passed"
            invokestatic TestClasses/short/outStrln(Ljava/lang/String;)V
          return
          .end method

          ; MethodDeclaring
          .method public static youBlewAns()V
          .limit locals 10
          .limit stack  30
            ; InvokeIsh
              ; InvokeReference
                ; ConstantProducing
                ldc "Check failed for short or"
            invokestatic TestClasses/short/outStrln(Ljava/lang/String;)V
          return
          .end method

          ; MethodDeclaring
          .method public static main431()V
          .limit locals 10
          .limit stack  30
            ; AssignIsh
              ; LocalReferencing
              ; CompareIsh
                ; ConstantProducing
                ldc 3
                ; ConstantProducing
                ldc 4
              isub
              iflt Label_1
              iconst_0
              goto Label_2
              
Label_1:
              iconst_1
              
Label_2:
            istore 0
            ; AssignIsh Done
            ; AssignIsh
              ; LocalReferencing
              ; ShortAndIsh
              ; ShortOr Local Ref atrue113: type Z mods null reg 0
              ; LocalReferencing
              iload 0
              iconst_0
              isub
              ifeq Label_3
              iconst_0
              ; CompareIsh
                ; ConstantProducing
                ldc 4
                ; ConstantProducing
                ldc 5
              isub
              iflt Label_5
              iconst_0
              goto Label_6
              
Label_5:
              iconst_1
              
Label_6:
              isub
              ifeq Label_3
              iconst_1
              goto Label_4
              
Label_3:
              iconst_0
              
Label_4:
            istore 1
            ; AssignIsh Done
            ; AssignIsh
              ; LocalReferencing
              ; CompareIsh
                ; ConstantProducing
                ldc 431
                ; ConstantProducing
                ldc 422
              isub
              ifgt Label_7
              iconst_0
              goto Label_8
              
Label_7:
              iconst_1
              
Label_8:
            istore 2
            ; AssignIsh Done
            ; AssignIsh
              ; LocalReferencing
              ; CompareIsh
                ; ConstantProducing
                ldc 431
                ; ConstantProducing
                ldc 101
              isub
              ifgt Label_9
              iconst_0
              goto Label_10
              
Label_9:
              iconst_1
              
Label_10:
            istore 3
            ; AssignIsh Done
            ; IfIsh
              ; ShortOr Local Ref atrue113: type Z mods null reg 0
              ; LocalReferencing
              iload 0
              iconst_0
              isub
              ifne Label_11
              iconst_0
              ; InvokeIsh
                ; InvokeReference
              invokestatic TestClasses/short/youBlewShortOr()Z
              isub
              ifne Label_11
              iconst_0
              goto Label_12
              
Label_11:
              iconst_1
              
Label_12:
              ; InvokeIsh
                ; InvokeReference
              invokestatic TestClasses/short/gotit()V
              return
              
Label_13:
              ; InvokeIsh
                ; InvokeReference
              invokestatic TestClasses/short/youBlewAns()V
              return
              
Label_14:
          return
          .end method

