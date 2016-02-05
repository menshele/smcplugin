package com.smcplugin;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.smcplugin.psi.SmcTransition;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

import static com.smcplugin.SmcConstants.*;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */

//TODO: implement this for navigation to smc methods from java and for smc transitions within smc file.
public class SmcLineMarkerProvider extends RelatedItemLineMarkerProvider {
    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
        if (element instanceof PsiLiteralExpression) {
            PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
            String value = literalExpression.getValue() instanceof String ? (String)literalExpression.getValue() : null;
            if (value != null && value.startsWith(SMC_PREFIX)) {
                Project project = element.getProject();
                final List<SmcTransition> properties = SmcUtil.findTransitions(project, value.substring(SMC_PREFIX.length()));
                if (properties.size() > 0) {
                    NavigationGutterIconBuilder<PsiElement> builder =
                            NavigationGutterIconBuilder.create(SmcIcons.FILE).
                                    setTargets(properties).
                                    setTooltipText("Navigate to a smc property");
                    result.add(builder.createLineMarkerInfo(element));
                }
            }
        }
    }
}
