package com.smcplugin;

import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import com.smcplugin.psi.SmcTransition;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcChooseByNameContributor implements ChooseByNameContributor {
    @NotNull
    @Override
    public String[] getNames(Project project, boolean includeNonProjectItems) {
        List<SmcTransition> properties = SmcUtil.findTransitions(project);
        List<String> names = new ArrayList<String>(properties.size());
        for (SmcTransition property : properties) {
            if (property.getName() != null && property.getName().length() > 0) {
                names.add(property.getName());
            }
        }
        return names.toArray(new String[names.size()]);
    }

    @NotNull
    @Override
    public NavigationItem[] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
        // todo include non project items
        List<SmcTransition> properties = SmcUtil.findTransitions(project, name);
        return properties.toArray(new NavigationItem[properties.size()]);
    }
}