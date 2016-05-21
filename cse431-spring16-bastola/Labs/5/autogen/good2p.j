  ; 138: ClassNode[ASTNodeIsh, NodeDumpable, SymDeclaring, ClassDeclaring]  "Class public instance good2p"
  .class public TestClasses/good2p
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
   invokestatic TestClasses/good2p/main431()V
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
                ldc 500
                ; ComputeIsh
                  ; ConstantProducing
                  ldc 4
                  ; ConstantProducing
                  ldc 73
                isub
              iadd
            istore 1
            ; AssignIsh Done
            ; InvokeIsh
              ; InvokeReference
                ; LocalReferencing
                iload 1
            invokestatic TestClasses/good2p/outInt(I)V
            ; AssignIsh
              ; LocalReferencing
              ; ComputeIsh
                ; ComputeIsh
                  ; ConstantProducing
                  ldc 30
                  ; ComputeIsh
                    ; ConstantProducing
                    ldc 4
                    ; ConstantProducing
                    ldc 5
                  isub
                iadd
                ; ComputeIsh
                  ; ConstantProducing
                  ldc 200
                  ; ConstantProducing
                  ldc 202
                iadd
              iadd
            istore 1
            ; AssignIsh Done
            ; InvokeIsh
              ; InvokeReference
                ; LocalReferencing
                iload 1
            invokestatic TestClasses/good2p/outInt(I)V
            ; AssignIsh
              ; LocalReferencing
              ; ComputeIsh
                ; ComputeIsh
                  ; ComputeIsh
                    ; ComputeIsh
                      ; ComputeIsh
                        ; ComputeIsh
                          ; ComputeIsh
                            ; ComputeIsh
                              ; ConstantProducing
                              ldc 10
                              ; ConstantProducing
                              ldc 20
                            iadd
                            ; ConstantProducing
                            ldc 30
                          iadd
                          ; ConstantProducing
                          ldc 40
                        iadd
                        ; ConstantProducing
                        ldc 50
                      iadd
                      ; ConstantProducing
                      ldc 60
                    iadd
                    ; ConstantProducing
                    ldc 70
                  iadd
                  ; ConstantProducing
                  ldc 80
                iadd
                ; ConstantProducing
                ldc 71
              iadd
            istore 1
            ; AssignIsh Done
            ; InvokeIsh
              ; InvokeReference
                ; LocalReferencing
                iload 1
            invokestatic TestClasses/good2p/outInt(I)V
            ; AssignIsh
              ; LocalReferencing
              ; ComputeIsh
                ; ComputeIsh
                  ; ComputeIsh
                    ; ConstantProducing
                    ldc 7
                    ; ConstantProducing
                    ldc 11
                  imul
                  ; ConstantProducing
                  ldc 13
                imul
                ; ConstantProducing
                ldc 431
              imul
            istore 1
            ; AssignIsh Done
            ; InvokeIsh
              ; InvokeReference
                ; LocalReferencing
                iload 1
            invokestatic TestClasses/good2p/outInt(I)V
            ; AssignIsh
              ; LocalReferencing
              ; ComputeIsh
                ; ComputeIsh
                  ; ComputeIsh
                    ; ComputeIsh
                      ; ConstantProducing
                      ldc 70
                    ineg
                    ; ComputeIsh
                      ; ConstantProducing
                      ldc 1
                    ineg
                  iadd
                  ; ComputeIsh
                    ; ComputeIsh
                      ; ComputeIsh
                        ; ConstantProducing
                        ldc 1
                        ; ConstantProducing
                        ldc 1
                      iadd
                      ; ConstantProducing
                      ldc 1
                    iadd
                    ; ComputeIsh
                      ; ConstantProducing
                      ldc 2
                      ; ComputeIsh
                        ; ComputeIsh
                          ; ConstantProducing
                          ldc 3
                          ; ComputeIsh
                            ; ConstantProducing
                            ldc 4
                            ; ConstantProducing
                            ldc 5
                          imul
                        imul
                      ineg
                    imul
                  imul
                iadd
              ineg
            istore 1
            ; AssignIsh Done
            ; InvokeIsh
              ; InvokeReference
                ; LocalReferencing
                iload 1
            invokestatic TestClasses/good2p/outInt(I)V
          return
          .end method

