package com.whaleread.jsweet;

import com.sun.tools.javac.tree.JCTree;
import org.jsweet.transpiler.JSweetContext;
import org.jsweet.transpiler.JSweetFactory;
import org.jsweet.transpiler.Java2TypeScriptTranslator;
import org.jsweet.transpiler.TranspilationHandler;
import org.jsweet.transpiler.extension.PrinterAdapter;

/**
 * Created by dolphin on 2018/5/1
 */
public class CustomJSweetFactory extends JSweetFactory {
    public PrinterAdapter createAdapter(JSweetContext context) {
        return new CustomJava2TypeScriptAdapter(context);
    }

    public Java2TypeScriptTranslator createTranslator(PrinterAdapter adapter, TranspilationHandler transpilationHandler,
                                                      JSweetContext context, JCTree.JCCompilationUnit compilationUnit, boolean fillSourceMap) {
        return new CustomJava2TypeScriptTranslator(adapter, transpilationHandler, context, compilationUnit, fillSourceMap);
    }
}
