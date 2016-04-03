package com.smcplugin.linemarker;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethodCallExpression;
import com.smcplugin.SmcIcons;
import com.smcplugin.psi.SmcPsiUtil;
import com.smcplugin.psi.SmcTransition;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;

/**
 * scmplugin
 * Created by lemen on 13.03.2016.
 */


public class SmcLineMarkerProvider extends RelatedItemLineMarkerProvider {
    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
/*        if (element instanceof SmcNextState) {
            SmcNextState nextState = (SmcNextState) element;
            String name = nextState.getName();
            if (name != null) {
                final SmcState state = SmcPsiUtil.findStateByNameWithinCurrentMap(nextState, name);
                if (state != null) {
                    NavigationGutterIconBuilder<PsiElement> builder =
                            NavigationGutterIconBuilder.create(SmcIcons.STATE).
                                    setTargets(state).
                                    setTooltipText(MessageFormat.format("Navigate to states \"{0}\"", name));
                    result.add(builder.createLineMarkerInfo(element));
                }
            }
        }*/
        if (element instanceof SmcTransition) {
            SmcTransition transition = (SmcTransition) element;
            String name = transition.getName();
            if (name != null) {
                final List<PsiMethodCallExpression> methodLikeCalls = SmcPsiUtil.findMethodLikeCalls(transition);
                if (methodLikeCalls != null && !methodLikeCalls.isEmpty()) {
                    NavigationGutterIconBuilder<PsiElement> builder =
                            NavigationGutterIconBuilder.create(SmcIcons.TRANSITION_REF).
                                    setTargets(methodLikeCalls).
                                    setTooltipText(MessageFormat.format("Navigate to usages of transition \"{0}\"", name));
                    result.add(builder.createLineMarkerInfo(transition));
                }
            }
        }
    }
}
