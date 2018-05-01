package com.whaleread.jsweet;

import com.sun.tools.javac.tree.JCTree;
import org.jsweet.transpiler.JSweetContext;
import org.jsweet.transpiler.Java2TypeScriptTranslator;
import org.jsweet.transpiler.TranspilationHandler;
import org.jsweet.transpiler.extension.PrinterAdapter;

/**
 * Created by dolphin on 2018/5/1
 */
public class CustomJava2TypeScriptTranslator extends Java2TypeScriptTranslator {
    public CustomJava2TypeScriptTranslator(PrinterAdapter adapter, TranspilationHandler logHandler, JSweetContext context,
                                           JCTree.JCCompilationUnit compilationUnit, boolean fillSourceMap) {
        super(adapter, logHandler, context, compilationUnit, fillSourceMap);
    }


}
