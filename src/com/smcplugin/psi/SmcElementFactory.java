package com.smcplugin.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import com.smcplugin.SmcFileType;

/**
 * scmplugin
 * Created by lemen on 29.02.2016.
 */

public class SmcElementFactory {

    public static final String DUMMY_SM = "%package com.company.smc\n" +
            "\n" +
            "%class com.company.smc.ContextClass\n" +
            "\n" +
            "%import com.company.smc.ContextClass\n" +
            "%fsmfile turnstile\n" +
            "%fsmclass StateMachine\n" +
            "%access private\n" +
            "%start STARTMAPNAMEELEMENT_NAME::STARTSTATENAMEELEMENT_NAME\n" +
            "\n" +
            "%map MAP_NAME %%\n" +
            "\n" +
            "STATE_NAME\n" +
            "{\n" +
            "    PUSH_TRANSITION_NAME PUSHPROXYSTATENAMEELEMENT_NAME/push(PUSHMAPNAMEELEMENT_NAME::PUSHSTATENAMEELEMENT_NAME) {}\n" +
            "\n" +
            "    TRANSITION_NAME\n" +
            "    NEXTSTATE_NAME\n" +
            "    {\n" +
            "        ACTION_NAME();\n" +
            "    }\n" +
            "}\n" +
            "%%\n";

    public static <T extends SmcNamedElement> T createElement(Project project, Class<T> aClass, String name) {
        final SmcFile file = createFile(project, aClass, name);
        return PsiTreeUtil.findChildOfType(file, aClass);
    }

    public static <T extends SmcNamedElement> SmcFile createFile(Project project, Class<T> aClass, String name) {
        String simpleName = aClass.getSimpleName();
        String tokenName = simpleName.substring(3,simpleName.length() - 4).toUpperCase()+"_NAME";
        String fileText = DUMMY_SM.replaceAll(tokenName, name);
        return (SmcFile) PsiFileFactory.getInstance(project).
                createFileFromText("dummy.sm", SmcFileType.INSTANCE, fileText);
    }


}