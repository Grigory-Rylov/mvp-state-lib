package com.github.mvpstatelib.processor;

import com.github.mvpstatelib.beans.ArgumentHolder;
import com.github.mvpstatelib.beans.ClassInfoHolder;
import com.github.mvpstatelib.printers.StateResolversMaker;
import com.github.mvpstatelib.state.annotations.SubscribeState;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;

/**
 * Created by grishberg on 22.04.17.
 */
@SupportedAnnotationTypes("com.github.mvpstatelib.state.annotations.SubscribeState")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class SubscribeStateProcessor extends AbstractProcessor {
    private final HashMap<String, ClassInfoHolder> classes = new HashMap<>();
    private final StateResolversMaker classMaker = new StateResolversMaker(processingEnv);

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(SubscribeState.class)) {

            ClassInfoHolder currentClassInfo = getClassInfoHolderForElement(annotatedElement);

            currentClassInfo.addArgumentHolder(getArgHolderForMethod(annotatedElement));
        }
        classMaker.makeClasses(classes);
        return true;
    }

    private ArgumentHolder getArgHolderForMethod(Element annotatedElement) {
        String methodName = annotatedElement.getSimpleName().toString();

        ExecutableType executableType = (ExecutableType) annotatedElement.asType();
        List<? extends TypeMirror> parameters = executableType.getParameterTypes();
        TypeMirror firstArgument = parameters.get(0);
        DeclaredType declaredType = (DeclaredType) firstArgument;

        return new ArgumentHolder(methodName, declaredType.toString());
    }

    private ClassInfoHolder getClassInfoHolderForElement(Element annotatedElement) {
        Element patentClass = annotatedElement.getEnclosingElement().getEnclosingElement();
        String parentClassName = patentClass.getSimpleName().toString();
        String originalPackageName = patentClass.getEnclosingElement().toString();
        String key = originalPackageName + parentClassName;
        ClassInfoHolder holder = classes.get(key);
        if (classes.get(key) == null) {
            holder = new ClassInfoHolder(originalPackageName, parentClassName);
        }
        return holder;
    }
}
