package com.smcplugin.intentions;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.ex.IdeDocumentHistory;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtil;
import com.intellij.util.IncorrectOperationException;
import com.smcplugin.psi.SmcFile;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.Arrays;

/**
 * scmplugin
 * Created by lemen on 27.03.2016.
 */
public class CreateMethodInContextClassFix extends BaseIntentionAction {
    public static final String CREATE_METHOD = "Create method ''{0}''";
    private final int parameterCount;
    private String name;

    public CreateMethodInContextClassFix(String name, int parameterCount) {
        this.name = name;
        this.parameterCount = parameterCount;
    }

    @Nls
    @NotNull
    @Override
    public String getFamilyName() {
        return "Fix Method Not Found";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        SmcFile smcFile = (SmcFile) file;
        return smcFile.getContextClass() != null;
    }

    @NotNull
    @Override
    public String getText() {
        return MessageFormat.format(CREATE_METHOD, name);
    }


    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
        SmcFile smcFile = (SmcFile) file;
        if (!FileModificationService.getInstance().prepareFileForWrite(smcFile.getContextClass().getContainingFile())) {
            return;
        }

        IdeDocumentHistory.getInstance(project).includeCurrentPlaceAsChangePlace();
        ApplicationManager.getApplication().invokeLater(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                new WriteCommandAction(project, file) {
                                                                    @Override
                                                                    protected void run(@NotNull Result result) throws Throwable {
                                                                        createMethod(smcFile.getContextClass(), name, parameterCount);
                                                                    }

                                                                }.execute();
                                                            }
                                                        }
        );
    }


    public static PsiMethod createMethod(PsiClass targetClass, String methodName, int parametersCount) {
        JVMElementFactory factory = JVMElementFactories.getFactory(targetClass.getLanguage(), targetClass.getProject());
        if (factory == null) {
            return null;
        }

        PsiMethod method = factory.createMethod(methodName, PsiType.VOID);
        PsiParameterList psiParameterList = PsiTreeUtil.findChildOfType(method, PsiParameterList.class);
        if (psiParameterList != null) {
            PsiParameterList parameterList = factory.createParameterList(getNamesForParamList(parametersCount), getTypesForParamList(targetClass.getManager(), parametersCount));
            psiParameterList.replace(parameterList);
        }
        if (targetClass.isInterface() || PsiUtil.isAbstractClass(targetClass)) {
            PsiCodeBlock body = method.getBody();
            if (body != null) {
                body.delete();
            }
        }

        return (PsiMethod) targetClass.add(method);
    }

    private static String[] getNamesForParamList(int parametersCount) {
        ;
        String[] params = new String[parametersCount];
        for (int i = 0; i < parametersCount; i++) {
            params[i] = "arg" + i;
        }
        return params;
    }

    private static PsiType[] getTypesForParamList(PsiManager manager, int parametersCount) {
        PsiType[] params = new PsiType[parametersCount];
        PsiClassType object = PsiType.getJavaLangObject(manager, GlobalSearchScope.allScope(manager.getProject()));
        Arrays.fill(params, object);
        return params;
    }
}
