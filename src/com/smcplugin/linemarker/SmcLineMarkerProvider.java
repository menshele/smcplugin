package com.smcplugin.linemarker;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.PsiElement;
import com.smcplugin.SmcIcons;
import com.smcplugin.psi.SmcNextState;
import com.smcplugin.psi.SmcPsiUtil;
import com.smcplugin.psi.SmcState;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.Collection;

/**
 * scmplugin
 * Created by lemen on 13.03.2016.
 */


public class SmcLineMarkerProvider extends RelatedItemLineMarkerProvider {
    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
        if (element instanceof SmcNextState) {
            SmcNextState nextState = (SmcNextState) element;
            String name = nextState.getName();
            if (name != null) {
                final SmcState state = SmcPsiUtil.findStateByNameWithinCurrentMap(nextState, name);
                if (state != null) {
                    NavigationGutterIconBuilder<PsiElement> builder =
                            NavigationGutterIconBuilder.create(SmcIcons.STATE).
                                    setTargets(state).
                                    setTooltipText(MessageFormat.format("Navigate to a states \"{0}\"",name));
                    result.add(builder.createLineMarkerInfo(element));
                }
            }
        }
    }
}
