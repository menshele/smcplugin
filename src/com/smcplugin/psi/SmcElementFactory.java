package com.smcplugin.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import com.smcplugin.SmcFileType;
import org.apache.commons.lang.StringUtils;

/**
 * scmplugin
 * Created by lemen on 29.02.2016.
 */

public class SmcElementFactory {

/*    public static final String DUMMY_SM = "%package com.company.smc\n" +
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
            "%%\n";  */

    public static final String DUMMY_SM = "%package com.company.smc\n" +
            "\n" +
            "%class com.company.smc.SomeContextClass\n" +
            "\n" +
            "%import com.company.smc.SomeContextClass\n" +
            "%fsmfile turnstile\n" +
            "%fsmclass StateMachine\n" +
            "%access private\n" +
            "%start STARTMAPNAMEELEMENT_NAME::STARTSTATENAMEELEMENT_NAME\n" +
            "\n" +
            "%map MAP_NAME %%\n" +
            "\n" +
            "    STATE_NAME\n" +
            "    {\n" +
            "        PUSH_TRANSITION_NAME PUSHPROXYSTATENAMEELEMENT_NAME/push(PUSHMAPNAMEELEMENT_NAME::PUSHSTATENAMEELEMENT_NAME) {\n" +
            "\n" +
            "\n" +
            "        }\n" +
            "\n" +
            "        TRANSITION_NAME(PARAMETERNAMEELEMENT_NAME: PARAMETERTYPEELEMENT_NAME)\n" +
            "        NEXTSTATE_NAME\n" +
            "        {\n" +
            "            ACTION_NAME(TYPEDARGUMENTELEMENT_NAME);\n" +
            "        }\n" +
            "    }\n" +
            "%%\n" +
            "\n" +
            "\n";

    public static final String DEFAULT_PACKAGE_NAME = "com.company.smc";
    public static final String DEFAULT_STATE_MACHINE_CLASS = "StateMachine";
    public static final String DEFAULT_CONTEXT_CLASS = "ContextClass";
    public static final String DEFAULT_MAP_NAME = "MAP_NAME";
    public static final String NEW_SMC_FILE = "%package com.company.smc\n" +
            "\n" +
            "%class ContextClass\n" +
            "%fsmclass StateMachine\n" +
            "\n" +
            "%access public\n" +
            "%start MAP_NAME::StartState\n" +
            "\n" +
            "%map MAP_NAME %%\n" +
            "    StartState {\n" +
            "\n" +
            "        transition\n" +
            "        NextState {\n" +
            "\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    NextState {}\n" +
            "%%";

    public static <T extends SmcNamedElement> T createElement(Project project, Class<T> aClass, String name) {
        final SmcFile file = createFile(project, aClass, name);
        return PsiTreeUtil.findChildOfType(file, aClass);
    }

    public static <T extends SmcNamedElement> SmcFile createFile(Project project, Class<T> aClass, String name) {
        String simpleName = aClass.getSimpleName();
        String tokenName = simpleName.substring(3, simpleName.length() - 4).toUpperCase() + "_NAME";
        String fileText = DUMMY_SM.replaceAll(tokenName, name);
        return (SmcFile) PsiFileFactory.getInstance(project).
                createFileFromText("dummy.sm", SmcFileType.INSTANCE, fileText);
    }

    public static <T extends SmcNamedElement> SmcFile createNewFile(Project project,
                                                                    String smFileName,
                                                                    String packageName,
                                                                    String smClass,
                                                                    String ctxClass) {

        String fileText = StringUtils.isNotBlank(packageName) ? NEW_SMC_FILE.replaceAll(DEFAULT_PACKAGE_NAME, packageName)
                : NEW_SMC_FILE;
        fileText = StringUtils.isNotBlank(smClass) ? fileText.replaceAll(DEFAULT_STATE_MACHINE_CLASS, smClass) : fileText;
        fileText = StringUtils.isNotBlank(ctxClass) ? fileText.replaceAll(DEFAULT_CONTEXT_CLASS, ctxClass): fileText;
        return (SmcFile) PsiFileFactory.getInstance(project).
                createFileFromText(smFileName, SmcFileType.INSTANCE, fileText);
    }

    public static void main(String[] args) {
        System.out.println(DUMMY_SM);
    }
}