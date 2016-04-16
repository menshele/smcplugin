package com.smcplugin.actions;

import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.ide.actions.CreateFromTemplateAction;
import com.intellij.ide.fileTemplates.JavaTemplateUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.InputValidatorEx;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiNameHelper;
import com.intellij.psi.PsiPackage;
import com.intellij.util.PlatformIcons;
import com.smcplugin.SmcFileType;
import com.smcplugin.SmcIcons;
import com.smcplugin.psi.SmcElementFactory;
import com.smcplugin.psi.SmcFile;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * scmplugin
 * Created by lemen on 09.04.2016.
 */
public class CreateFsmFileAction extends CreateFromTemplateAction<SmcFile> {

    public static final String NEW_FSM_FILE = "New FSM File";
    public static final String DOT_EXTENSION = "." + SmcFileType.INSTANCE.getDefaultExtension();
    public static final String CONTEXT_SUFFIX = "SMContext";
    public static final String STATE_MACHINE_SUFFIX = "StateMachine";
    public static final String DO_NOT_CREATE_CONTEXT_KIND = "DoNotCreate";

    public CreateFsmFileAction() {
        super(NEW_FSM_FILE, "Create new FSM File", SmcIcons.SM);
    }

    @Nullable
    @Override
    protected SmcFile createFile(String name, String templateName, PsiDirectory dir) {
        String aPackageName = getPackageName(dir);
        boolean validName = PsiNameHelper.getInstance(dir.getProject()).isQualifiedName(name);
        String shortClassName = StringUtils.capitalize(PsiNameHelper.getShortClassName(name));
        String smClassName = validName ? shortClassName + STATE_MACHINE_SUFFIX : "";
        String smContextClassName = validName ?  shortClassName + CONTEXT_SUFFIX : "";
        if (validName && !DO_NOT_CREATE_CONTEXT_KIND.equals(templateName)) {
            JavaDirectoryService.getInstance().createClass(dir, smContextClassName, templateName, true);
        }
        String nameWithExtension = name + DOT_EXTENSION;
        SmcFile newFile = SmcElementFactory.createNewFile(dir.getProject(), nameWithExtension, aPackageName,
                smClassName, smContextClassName);
        dir.add(newFile);
        return newFile;
    }

    @NotNull
    private static String getPackageName(PsiDirectory dir) {
        PsiPackage aPackage = JavaDirectoryService.getInstance().getPackage(dir);
        return aPackage == null ? "" : aPackage.getQualifiedName();
    }

    @Override
    protected void buildDialog(final Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle(NEW_FSM_FILE)
                .addKind("SM Context is an Interface", PlatformIcons.INTERFACE_ICON, JavaTemplateUtil.INTERNAL_INTERFACE_TEMPLATE_NAME)
                .addKind("SM Context is Class", PlatformIcons.CLASS_ICON, JavaTemplateUtil.INTERNAL_CLASS_TEMPLATE_NAME)
                .addKind("Don't create SM Context now", SmcIcons.CONTEXT, DO_NOT_CREATE_CONTEXT_KIND);
        builder.setValidator(new InputValidatorEx() {
            @Override
            public String getErrorText(String inputString) {
                if (inputString.endsWith(DOT_EXTENSION)) {
                    return "Please put name without " + DOT_EXTENSION + ". It will be added automatically";
                }
                return null;
            }

            @Override
            public boolean checkInput(String inputString) {
                return true;
            }

            @Override
            public boolean canClose(String inputString) {
                return !StringUtil.isEmptyOrSpaces(inputString) && getErrorText(inputString) == null;
            }
        });
    }

    @Override
    protected String getActionName(PsiDirectory directory, String newName, String templateName) {
        return "testActionName";
    }
}
