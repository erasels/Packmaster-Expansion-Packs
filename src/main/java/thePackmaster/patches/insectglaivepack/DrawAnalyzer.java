package thePackmaster.patches.insectglaivepack;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import javassist.*;
import javassist.expr.*;
import thePackmaster.cardmodifiers.artificerpack.DrawEnchantModifier;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DrawAnalyzer {

    private static Set<String> analyzedClasses = new HashSet<>();
    private static Set<String> analyzedMethods = new HashSet<>();
    private static Set<String> drawCardModifierClasses = new HashSet<>(Arrays.asList(thePackmaster.cardmodifiers.creativitypack.DrawCardModifier.class.getName(), thePackmaster.cardmodifiers.madsciencepack.DrawCardModifier.class.getName(), DrawEnchantModifier.class.getName()));

    private static final int MAX_DEPTH = 1;

    public static boolean isDrawCard(AbstractCard card) {
        if (CardModifierManager.modifiers(card).stream().anyMatch(m -> drawCardModifierClasses.contains(m.getClass().getName()))) {
            return true;
        }
        analyzedClasses.clear();
        analyzedMethods.clear();
        return analyzeMethodFromMethod(card.getClass().getName(), "use", 0);
    }

    private static boolean analyzeMethodFromMethod(String className, String methodName, int depth) {
        try {
            ClassPool cp = Loader.getClassPool();
            CtClass ctClass = cp.get(className);
            CtMethod[] methods = ctClass.getMethods();
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

            if (analyzeMethodFromMethod(calledClassName, calledMethodName, depth + 1)) {
                return true;
            }
        }

        for (String className : classesToAnalyze) {
            if (analyzedClasses.contains(className)) {
                continue;
            }

            // Applying card modifiers that draw cards should not count as a draw effect, so we ignore these
            if (drawCardModifierClasses.contains(className)) {
                continue;
            }

            analyzedClasses.add(className);
//            InsectGlaivePack.logger.info("Analyzing " + className);

            try {
                ClassPool cp = Loader.getClassPool();
                CtClass innerClass = cp.get(className);

                // Creating cards with draw effects should not count as a draw effect, so we don't analyze other cards
                if (innerClass.subclassOf(cp.get(AbstractCard.class.getName()))) {
                    continue;
                }

                for (CtMethod innerMethod : innerClass.getMethods()) {
                    if (analyzeMethodFromClass(innerMethod, depth + 1)) {
                        return true;
                    }
                }
            } catch (Exception e) {
                System.err.println("Error analyzing inner class: " + className);
                e.printStackTrace();
                throw e;
            }
        }

        return false;
    }
}