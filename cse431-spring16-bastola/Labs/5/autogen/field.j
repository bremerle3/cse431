  ; 225: ClassNode[ASTNodeIsh, NodeDumpable, SymDeclaring, ClassDeclaring]  "Class public instance field"
  .class public TestClasses/field
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
   invokestatic TestClasses/field/main431()V
   return
.end method


          .method public static outStr(Ljava/lang/String;)V
          .limit locals 10
          .limit stack  30
              getstatic java/lang/System/out Ljava/io/PrintStream;
            astore 1
                aload 1
                aload 0
            invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
          return
          .end method

          .method public static outStrln(Ljava/lang/String;)V
          .limit locals 10
          .limit stack  30
              getstatic java/lang/System/out Ljava/io/PrintStream;
            astore 1
                aload 1
                aload 0
            invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
          return
          .end method

          .method public static outInt(I)V
          .limit locals 10
          .limit stack  30
              getstatic java/lang/System/out Ljava/io/PrintStream;
            astore 1
                aload 1
                iload 0
            invokevirtual java/io/PrintStream/print(I)V
          return
          .end method

          .method public static printPub(Ljava/lang/String;LTestClasses/Pub;II)V
          .limit locals 10
          .limit stack  30
                ldc "Value of "
            invokestatic TestClasses/field/outStr(Ljava/lang/String;)V
                aload 0
            invokestatic TestClasses/field/outStr(Ljava/lang/String;)V
                ldc " should be "
            invokestatic TestClasses/field/outStr(Ljava/lang/String;)V
                iload 2
            invokestatic TestClasses/field/outInt(I)V
                ldc ", "
            invokestatic TestClasses/field/outStr(Ljava/lang/String;)V
                iload 3
            invokestatic TestClasses/field/outInt(I)V
                ldc " and is "
            invokestatic TestClasses/field/outStr(Ljava/lang/String;)V
                    aload 1
                invokevirtual TestClasses/Pub/toString()Ljava/lang/String;
            invokestatic TestClasses/field/outStrln()V
          return
          .end method

          .method public static main431()V
          .limit locals 10
          .limit stack  30
              invokestatic TestClasses/Pub/genPub()LTestClasses/Pub;
            astore 0
              invokestatic TestClasses/Pub/genPub()LTestClasses/Pub;
            astore 1
              invokestatic TestClasses/Pub/genPub()LTestClasses/Pub;
            astore 2
                ldc "p1"
                aload 0
                ldc 400
                ldc 31
            invokestatic TestClasses/field/printPub(Ljava/lang/String;)V
                aload 0
            istore 4
                aload 1
            istore 5
                iload 4
                iload 5
              isub
            istore 6
                aload 2
            iload 6
                aload 2
                aload 1
                ldc "p3"
                aload 2
                ldc 369
                ldc 400
            invokestatic TestClasses/field/printPub(Ljava/lang/String;)V
          return
          .end method

