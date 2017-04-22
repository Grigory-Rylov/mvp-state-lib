package com.github.mvpstatelib.printers;

import com.github.mvpstatelib.beans.ArgumentHolder;
import com.github.mvpstatelib.beans.ClassInfoHolder;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.JavaFileObject;

/**
 * Created by grishberg on 22.04.17.
 */

public class StateResolversMaker {
    private static final String FRAMEWORK_PACKAGE = "com.github.mvpstatelib.framework";
    private static final String DOT = ".";

    public void makeClasses(ProcessingEnvironment processingEnv, HashMap<String, ClassInfoHolder> classes) {
        for (Map.Entry<String, ClassInfoHolder> entry : classes.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }
            String parentClassName = entry.getValue().getParentClassName();
            String generatedClassName = "Generated" + parentClassName + "Subscriber";

            StringBuilder builder = new StringBuilder();

            builder = makePackage(builder, entry.getValue().getPackageName());

            builder = makeImports(builder);

            builder = makeClassHeader(builder, generatedClassName, entry.getValue());

            builder = makeCheckingInstanceOf(builder, entry.getValue());

            builder.append("\n\t}\n") // close method
                    .append("}\n"); // close class

            generateJavaClass(processingEnv, entry.getValue().getPackageName(), generatedClassName, builder);
        }
    }

    private StringBuilder makePackage(StringBuilder builder, String packageName) {
        builder.append("package ")
                .append(packageName)
                .append(";\n\n");
        return builder;
    }

    private StringBuilder makeImports(StringBuilder builder) {
        builder.append("import ")
                .append(FRAMEWORK_PACKAGE)
                .append(DOT)
                .append("state")
                .append(DOT)
                .append("MvpState")
                .append(";\n\n");
        return builder;
    }

    private StringBuilder makeClassHeader(StringBuilder builder,
                                          String generatedClassName,
                                          ClassInfoHolder classInfoHolder) {
        builder.append("public class ")
                .append(generatedClassName)
                .append(" {\n\n")
                .append("\tpublic static void processState(final ")
                .append(classInfoHolder.getParentClassName())
                .append(" view, ")
                .append(" MvpState state) {\n");// open method; // open class
        return builder;
    }

    private StringBuilder makeCheckingInstanceOf(StringBuilder builder, ClassInfoHolder classInfoHolder) {
        boolean isFirstArg = true;
        for (ArgumentHolder argument : classInfoHolder.getArgumentsList()) {
            if (!isFirstArg) {
                builder.append(" else ");
            } else {
                builder.append("\t\t");
            }
            builder.append("if(state instanceof ")
                    .append(argument.getArgType())
                    .append(") {\n")
                    .append("\t\t\tview.")
                    .append(argument.getMethodName())
                    .append("((")
                    .append(argument.getArgType())
                    .append(") state);\n\t\t}");
            isFirstArg = false;
        }
        return builder;
    }

    private void generateJavaClass(ProcessingEnvironment processingEnv,
                                   String originalPackageName,
                                   String className,
                                   StringBuilder builder) {
        try { // write the file
            JavaFileObject source = processingEnv.getFiler()
                    .createSourceFile(originalPackageName + "." + className);

            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // Note: calling e.printStackTrace() will print IO errors
            // that occur from the file already existing after its first run, this is normal
        }
    }
}
