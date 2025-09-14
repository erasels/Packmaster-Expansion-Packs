package thePackmaster.patches.insectglaivepack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import javassist.*;
import javassist.expr.*;

import java.util.*;

public class DrawAnalyzer {
    private static final Set<String> analyzedClasses = new HashSet<>();
    private static final Set<String> analyzedMethods = new HashSet<>();
    // For some small efficiency games, we avoid analyzing namespaces we know to be irrelevant
    private static final List<String> irrelevantNamespaces = Arrays.asList("java.");

    private static final int MAX_DEPTH = 1;

    public static boolean isDrawCard(AbstractCard card) {
        analyzedClasses.clear();
        analyzedMethods.clear();
        return analyzeMethodFromMethod(card.getClass().getName(), "use", 0) || CardModifierManager.modifiers(card).stream().anyMatch(m -> analyzeMethodFromMethod(m.getClass().getName(), "onUse", 0));
    }

    private static boolean analyzeMethodFromMethod(String className, String methodName, int depth) {
        if (isIrrelevantNamespace(className)) {
            return false;
        }
        try {
            ClassPool cp = Loader.getClassPool();
            CtClass ctClass = cp.get(className);
            ArrayList<CtMethod> methods = new ArrayList<>();
            // getMethods gives superclass methods but not declared private methods
            // getDeclaredMethods gives private methods but not superclass methods
            // We check both (and don't care about duplicates since we'll just find the first instance of a method)
            Collections.addAll(methods, ctClass.getMethods());
            Collections.addAll(methods, ctClass.getDeclaredMethods());
            CtMethod method = null;
            for (CtMethod m : methods) {
                if (m.getName().equals(methodName)) {
                    method = m;
                    break;
                }
            }

            if (method != null) {
                return analyzeMethodFromClass(method, depth);
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean analyzeMethodFromClass(CtMethod method, int depth) throws Exception {
        final boolean[] foundDrawCardAction = {false};
        final Set<String> classesToAnalyze = new HashSet<>();
        final Set<MethodCall> methodCallsToAnalyze = new HashSet<>();

        if (isIrrelevantNamespace(method.getLongName())) {
            return false;
        }

        if (depth > MAX_DEPTH) {
            return false;
        }

        // 分析方法中的直接调用
        method.instrument(new ExprEditor() {
            @Override
            public void edit(NewExpr newExpr) {
                try {
                    if (newExpr.getClassName().contains(DrawCardAction.class.getName())) {
                        foundDrawCardAction[0] = true;
                    } else classesToAnalyze.add(newExpr.getClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void edit(ConstructorCall c) {
                try {
                    if (c.getClassName().contains(DrawCardAction.class.getName())) {
                        foundDrawCardAction[0] = true;
                    } else classesToAnalyze.add(c.getClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void edit(MethodCall m) {
                try {
                    if (m.getClassName().contains(DrawCardAction.class.getName())) {
                        foundDrawCardAction[0] = true;
                    } else methodCallsToAnalyze.add(m);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        if (foundDrawCardAction[0]) {
            return true;
        }

        for (MethodCall methodCall : methodCallsToAnalyze) {
            String calledClassName = methodCall.getClassName();
            String calledMethodName = methodCall.getMethodName();

            if (analyzedMethods.contains(calledClassName + "." + calledMethodName)) {
                continue;
            }

            analyzedMethods.add(calledClassName + "." + calledMethodName);
//            InsectGlaivePack.logger.info("Analyzing " + calledClassName + "." + calledMethodName);

            // This ensures we fully analyze anonymous actions and similar scenarios that use inner classes
            int newDepth = depth + (sameTopLevelClass(method.getDeclaringClass(), methodCall.getMethod().getDeclaringClass()) ? 0 : 1);
            if (analyzeMethodFromMethod(calledClassName, calledMethodName, newDepth)) {
                return true;
            }
        }

        for (String className : classesToAnalyze) {
            if (analyzedClasses.contains(className)) {
                continue;
            }

            analyzedClasses.add(className);
//            InsectGlaivePack.logger.info("Analyzing " + className);

            try {
                ClassPool cp = Loader.getClassPool();
                CtClass innerClass = cp.get(className);

                // Creating cards or card modifiers with draw effects should not count as a draw effect
                if (innerClass.subclassOf(cp.get(AbstractCard.class.getName())) || innerClass.subclassOf(cp.get(AbstractCardModifier.class.getName()))) {
                    continue;
                }

                // This ensures we fully analyze anonymous actions and similar scenarios that use inner classes
                int newDepth = depth + (sameTopLevelClass(method.getDeclaringClass(), innerClass) ? 0 : 1);
                for (CtMethod innerMethod : innerClass.getMethods()) {
                    if (analyzeMethodFromClass(innerMethod, newDepth)) {
                        return true;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Error analyzing inner class: " + className, e);
            }
        }

        return false;
    }

    private static boolean sameTopLevelClass(CtClass class1, CtClass class2) {
        try {
            CtClass declaringClass1 = class1;
            while (declaringClass1.getDeclaringClass() != null) {
                declaringClass1 = declaringClass1.getDeclaringClass();
            }
            CtClass declaringClass2 = class2;
            while (declaringClass2.getDeclaringClass() != null) {
                declaringClass2 = declaringClass2.getDeclaringClass();
            }
            return declaringClass1.getName().equals(declaringClass2.getName());
        } catch (NotFoundException e) {
            throw new RuntimeException("Error checking top level classes: " + class1.getName() + ", " + class2.getName(), e);
        }
    }

    private static boolean isIrrelevantNamespace(String name) {
        return irrelevantNamespaces.stream().anyMatch(name::startsWith);
    }
}