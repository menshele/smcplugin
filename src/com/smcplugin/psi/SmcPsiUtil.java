package com.smcplugin.psi;

import com.intellij.codeInsight.completion.JavaLookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.file.impl.JavaFileManager;
import com.intellij.psi.impl.file.impl.JavaFileManagerImpl;
import com.intellij.psi.impl.source.resolve.PsiResolveHelperImpl;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.Predicate;
import com.intellij.util.indexing.FileBasedIndex;
import com.smcplugin.SmcFileType;
import com.smcplugin.psi.impl.SmcPsiImplUtil;
import com.smcplugin.util.SmcStringUtils;
import org.apache.commons.lang.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * scmplugin
 * Created by lemen on 29.02.2016.
 */
public class SmcPsiUtil {

    public static JavaFileManager getFileManager(Project project) {
        return new JavaFileManagerImpl(project);
    }

    public static PsiResolveHelper getPsiResolveHelper(Project project) {
        return new PsiResolveHelperImpl(PsiManager.getInstance(project));
    }

    @SuppressWarnings("ConstantConditions")
    public static PsiClass[] findClasses(String name, Project project) {
        return getFileManager(project).findClasses(name, GlobalSearchScope.projectScope(project));
    }

    @SuppressWarnings("ConstantConditions")
    public static List<PsiClass> findClassesForPackage(String name, Project project) {
        PsiPackage aPackage = getFileManager(project).findPackage(name);
        if (aPackage != null) {
            return Arrays.asList(aPackage.getClasses());
        }
        return Collections.emptyList();
    }

    @SuppressWarnings("ConstantConditions")
    public static List<SmcParameter> findParameterListByTypedArgument(SmcTypedArgumentElement argument) {
        SmcTransition transition = PsiTreeUtil.getParentOfType(argument, SmcTransition.class);
        if (transition != null) {
            SmcTransitionArgs transitionArgs = transition.getTransitionArgs();
            if (transitionArgs != null) {
                SmcParameters parameters = transitionArgs.getParameters();
                if (parameters != null) {
                    return parameters.getParameterList();
                }
            }
        }
        return Collections.emptyList();
    }

    @SuppressWarnings("ConstantConditions")
    public static List<PsiClass> findInnerClasses(String qname, Project project) {
        PsiClass aClass = findClass(qname, project);
        PsiClass[] innerClasses = null;
        if (aClass != null) {
            innerClasses = aClass.getInnerClasses();
        }
        return ArrayUtils.isEmpty(innerClasses) ? Collections.<PsiClass>emptyList() : Arrays.asList(innerClasses);
    }

    public static List<LookupElement> fillVariantsForStaticMembers(String qname, PsiClass accessibleFromClass) {
        if (accessibleFromClass == null) return Collections.emptyList();
        List<LookupElement> variants = new ArrayList<>();
        Project project = accessibleFromClass.getProject();
        PsiClass aClass = findClass(qname, project);
        if (aClass != null) {
            Set<PsiMethod> methods = new HashSet<>();
            methods.addAll(Arrays.asList(aClass.getMethods()));
            List<Pair<PsiMethod, PsiSubstitutor>> allMethodsAndTheirSubstitutors = aClass.getAllMethodsAndTheirSubstitutors();
            for (Pair<PsiMethod, PsiSubstitutor> pair : allMethodsAndTheirSubstitutors) {

                PsiMethod method = pair.getFirst();
                if (!methods.contains(method)) {
                    continue;
                }
                PsiSubstitutor methodSubst = pair.getSecond();

                if (method.hasModifierProperty(PsiModifier.STATIC) && getPsiResolveHelper(project).isAccessible(method, accessibleFromClass, null)) {
                    variants.add(JavaLookupElementBuilder.forMethod(method, methodSubst));
                }
            }
            for (PsiField field : collectStatics(aClass.getFields(), accessibleFromClass, project)) {

                variants.add(JavaLookupElementBuilder.forField(field));
            }
        }
        return variants;
    }

    private static <T extends PsiMember> List<T> collectStatics(T[] members, PsiClass accessibleFromPsiClass, Project project) {
        List<T> result = new ArrayList<>();
        for (T member : members) {
            if (member.hasModifierProperty(PsiModifier.STATIC) && getPsiResolveHelper(project).isAccessible(member, accessibleFromPsiClass, null)) {
                result.add(member);
            }
        }
        return result;
    }

    private static <T extends PsiMember> List<T> collectStatics(T[] members) {
        List<T> result = new ArrayList<>();
        for (T member : members) {
            if (member.hasModifierProperty(PsiModifier.STATIC)) {
                result.add(member);
            }
        }
        return result;
    }

    @SuppressWarnings("ConstantConditions")
    public static List<PsiPackage> findSubPackagesForPackage(String name, Project project) {
        PsiPackage aPackage = getFileManager(project).findPackage(name);
        if (aPackage != null) {
            return Arrays.asList(aPackage.getSubPackages());
        }
        return Collections.emptyList();
    }

    @SuppressWarnings("ConstantConditions")
    public static PsiClass findClass(String qName, Project project) {
        return getFileManager(project).findClass(qName, GlobalSearchScope.projectScope(project));
    }

    @SuppressWarnings("ConstantConditions")
    public static PsiClass findClassEverywhere(String qName, Project project) {
        return getFileManager(project).findClass(qName, GlobalSearchScope.allScope(project));
    }

    @SuppressWarnings("ConstantConditions")
    public static List<PsiMethod> findMethodsWithAtLeastArgCount(String qName, int argNum, Project project) {
        PsiClass aClass = findClass(qName, project);
        if (aClass == null) {
            return Collections.emptyList();
        }
        List<PsiMethod> psiMethods = Arrays.asList(aClass.getMethods());
        List<PsiMethod> result = new ArrayList<>();
        for (PsiMethod method : psiMethods) {
            if (method.getParameterList().getParametersCount() >= argNum) {
                result.add(method);
            }
        }
        return result;
    }


    @SuppressWarnings("ConstantConditions")
    public static List<PsiMethod> findMethodInClass(String qName, String methodName, int methodParameterCount,
                                                    Project project, boolean checkBases) {
        PsiClass aClass = SmcPsiUtil.findClass(qName, project);
        if (aClass == null) return Collections.<PsiMethod>emptyList();
        final PsiMethod[] methodsByName = aClass.findMethodsByName(methodName, checkBases);
        if (methodParameterCount < 0) {
            return Arrays.asList(methodsByName);
        }
        List<PsiMethod> methodsNyNameAndParamCount = new ArrayList<>();
        for (PsiMethod method : methodsByName) {
            if (method.getParameterList().getParametersCount() == methodParameterCount) {
                methodsNyNameAndParamCount.add(method);
            }
        }
        return methodsNyNameAndParamCount;
    }

    public static boolean isMethodInClass(String qName, String methodName, int methodParameterCount, Project project) {
        return !findMethodInClass(qName, methodName, methodParameterCount, project, true).isEmpty();
    }

    public static boolean isMethodInClassNotUnique(String qName, String methodName, int methodParameterCount, Project project) {
        return filterSupers(findMethodInClass(qName, methodName, methodParameterCount, project, true)).size() > 1;
    }

    private static List<PsiMethod> filterSupers(List<PsiMethod> methodInClass) {
        List<PsiMethod> methodsToRemove = new ArrayList<>();
        for (PsiMethod method : methodInClass) {
            PsiMethod[] superMethods = method.findSuperMethods();
            if (!ArrayUtils.isEmpty(superMethods)) {
                methodsToRemove.addAll(Arrays.asList(superMethods));
            }
        }
        methodInClass.retainAll(methodsToRemove);
        return methodInClass;
    }

    @SuppressWarnings("ConstantConditions")
    public static boolean classExists(String qName, Project project) {
        return getFileManager(project).findClass(qName, GlobalSearchScope.projectScope(project)) != null;
    }

    public static List<SmcMap> findMapGlobally(Project project, String name) {
        List<SmcMap> result = null;
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, SmcFileType.INSTANCE,
                GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            SmcFile simpleFile = (SmcFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile != null) {
                Collection<SmcMap> properties = PsiTreeUtil.findChildrenOfType(simpleFile, SmcMap.class);
                for (SmcMap property : properties) {
                    if (name.equals(property.getName())) {
                        if (result == null) {
                            result = new ArrayList<SmcMap>();
                        }
                        result.add(property);
                    }
                }
            }
        }
        return result != null ? result : Collections.<SmcMap>emptyList();
    }

    public static List<SmcMap> findMap(@NotNull PsiFile smcFile, String name) {
        List<SmcMap> result = null;
        Collection<SmcMap> mapsInFile = PsiTreeUtil.findChildrenOfType(smcFile, SmcMap.class);
        for (SmcMap property : mapsInFile) {
            if (name.equals(property.getName())) {
                if (result == null) {
                    result = new ArrayList<SmcMap>();
                }
                result.add(property);
            }
        }
        return result != null ? result : Collections.<SmcMap>emptyList();
    }

    public static List<SmcFile> findSmcFile(Project project, Predicate<SmcFile> predicate) {
        List<SmcFile> result = null;
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, SmcFileType.INSTANCE,
                GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            SmcFile smcFile = (SmcFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (smcFile != null &&
                    (predicate == null || predicate.apply(smcFile))) {
                if (result == null) {
                    result = new ArrayList<>();
                }
                result.add(smcFile);
            }
        }
        return result != null ? result : Collections.<SmcFile>emptyList();
    }

    public static List<? extends SmcMethodLikeElement> findMethodLikeForMethod(PsiMethod psiMethod, Class<? extends SmcMethodLikeElement> aClass, Predicate<SmcMethodLikeElement> predicate) {
        List<SmcMethodLikeElement> result = null;
        Project project = psiMethod.getProject();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, SmcFileType.INSTANCE,
                GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            SmcFile simpleFile = (SmcFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile != null) {
                Collection<? extends SmcMethodLikeElement> methodLikeElements = PsiTreeUtil.findChildrenOfType(simpleFile, aClass);
                for (SmcMethodLikeElement methodLike : methodLikeElements) {
                    if (psiMethod.getName().equals(methodLike.getName()) &&
                            psiMethod.getParameterList().getParametersCount() == methodLike.getArgumentCount() &&
                            (predicate == null || predicate.apply(methodLike))) {
                        if (result == null) {
                            result = new ArrayList<>();
                        }
                        result.add(methodLike);
                    }
                }
            }
        }
        return result != null ? result : Collections.<SmcMethodLikeElement>emptyList();
    }

    public static Collection<PsiClass> findDirectlyImportedClassBySimpleName(SmcFile file, String simpleName) {
        SmcImportClass singleImportStatement = file.findSingleImportStatement(simpleName);
        String name = (singleImportStatement != null) ?
                singleImportStatement.getQualifiedIdentifier().getLastIdentifier().getQualifiedName() : null;
        return name == null ? Collections.<PsiClass>emptyList() : Arrays.asList(SmcPsiUtil.findClasses(name, file.getProject()));
    }

    public static Collection<PsiClass> findImportedInPackageClassBySimpleName(SmcFile file, String simpleName) {
        Set<String> packages = file.findImportedPackageNames();
        List<PsiClass> candidates = new ArrayList<>();
        for (String packageName : packages) {
            PsiPackage aPackage = getFileManager(file.getProject()).findPackage(packageName);
            if (aPackage != null) {
                PsiClass[] classByShortName = aPackage.findClassByShortName(simpleName, GlobalSearchScope.allScope(file.getProject()));
                candidates.addAll(Arrays.asList(classByShortName));
            }
        }
        return candidates;
    }

    public static Collection<PsiClass> getImportedInPackageClasses(SmcFile file) {
        Set<String> packages = file.findImportedPackageNames();
        List<PsiClass> candidates = new ArrayList<>();
        for (String packageName : packages) {
            PsiPackage aPackage = getFileManager(file.getProject()).findPackage(packageName);
            if (aPackage != null) {
                candidates.addAll(Arrays.asList(aPackage.getClasses()));
            }
        }
        return candidates;
    }

    public static Collection<PsiClass> findImportedClass(SmcFile smFile, String simpleClassName) {
        List<PsiClass> availableClasses = new ArrayList<>();
        availableClasses.addAll(findDirectlyImportedClassBySimpleName(smFile, simpleClassName));
        availableClasses.addAll(findImportedInPackageClassBySimpleName(smFile, simpleClassName));
        return availableClasses;
    }

    public static Collection<PsiClass> findAllImportedClass(SmcFile smFile) {
        List<PsiClass> availableClasses = new ArrayList<>();
        availableClasses.addAll(smFile.getDirectlyImportedClasses());
        availableClasses.addAll(getImportedInPackageClasses(smFile));

        return availableClasses;
    }

    public static List<SmcTransition> findTransitionByMethod(PsiMethod psiMethod, Predicate<SmcTransition> predicate) {
        List<SmcTransition> result = null;
        Project project = psiMethod.getProject();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, SmcFileType.INSTANCE,
                GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            SmcFile simpleFile = (SmcFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile != null) {
                Collection<SmcTransition> smcTransitions = PsiTreeUtil.findChildrenOfType(simpleFile, SmcTransition.class);
                for (SmcTransition transition : smcTransitions) {
                    if (psiMethod.getName().equals(transition.getName()) &&
                            (psiMethod.getParameterList().getParametersCount() == transition.getArgumentCount() + 1) &&
                            (predicate == null || predicate.apply(transition))) {
                        if (result == null) {
                            result = new ArrayList<>();
                        }
                        result.add(transition);
                    }
                }
            }
        }
        return result != null ? result : Collections.<SmcTransition>emptyList();
    }


    public static List<PsiMethodCallExpression> findMethodLikeCalls(SmcMethodLikeElement psiMethodLike) {
        SmcFile containingFile = (SmcFile) psiMethodLike.getContainingFile().getContainingFile();
        PsiClass fsmClass = containingFile.getFsmClass();
        Project project = psiMethodLike.getProject();
        if (fsmClass == null) return Collections.emptyList();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, JavaFileType.INSTANCE,
                GlobalSearchScope.projectScope(project));
        List<PsiMethodCallExpression> result = new ArrayList<>();
        for (VirtualFile virtualFile : virtualFiles) {
            PsiJavaFile psiJavaFile = (PsiJavaFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (psiJavaFile != null) {
                Collection<PsiMethodCallExpression> methodCallExpressions = PsiTreeUtil.findChildrenOfType(psiJavaFile, PsiMethodCallExpression.class);
                for (PsiMethodCallExpression methodCall : methodCallExpressions) {
                    PsiMethod psiMethod = methodCall.resolveMethod();
                    if (psiMethod != null && psiMethodLike.matches(psiMethod)) {
                        result.add(methodCall);
                    }
                }
            }
        }
        return result;
    }

    public static List<PsiMethod> findJavaMethod(Project project, String name) {
        List<PsiMethod> result = null;
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, JavaFileType.INSTANCE,
                GlobalSearchScope.projectScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            PsiJavaFile javaFile = (PsiJavaFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (javaFile != null) {
                Collection<PsiMethod> properties = PsiTreeUtil.findChildrenOfType(javaFile, PsiMethod.class);
                for (PsiMethod javaMethod : properties) {
                    if (name.equals(javaMethod.getName())) {
                        if (result == null) {
                            result = new ArrayList<PsiMethod>();
                        }
                        result.add(javaMethod);
                    }
                }
            }
        }
        return result != null ? result : Collections.<PsiMethod>emptyList();
    }

    public static <T extends SmcNamedElement> boolean isNotSingleNamedElement(@NotNull PsiElement root, @NotNull Class<T> namedElement, @NotNull String name) {
        Collection<T> childrenOfType = PsiTreeUtil.findChildrenOfType(root, namedElement);
        boolean found = false;
        for (T child : childrenOfType) {
            if (name.equals(child.getName())) {
                if (found) {
                    return true;
                } else {
                    found = true;
                }
            }
        }
        return false;
    }

    public static boolean isNotSingleTransition(@NotNull SmcTransitions root, @NotNull String name) {
        List<SmcTransition> transitions = root.getTransitionList();
        boolean found = false;
        for (SmcTransition transition : transitions) {
            if (name.equals(transition.getName()) && transition.getGuard() == null) {
                if (found) {
                    return true;
                } else {
                    found = true;
                }
            }
        }
        return false;
    }

    /**
     * Check that element type of the given AST node belongs to the token set.
     * <p/>
     * It slightly less verbose than {@code set.contains(node.getElementType())} and overloaded methods with the same name
     * allow check ASTNode/PsiElement against both concrete element types and token sets in uniform way.
     */
    public static boolean hasElementType(@NotNull ASTNode node, @NotNull TokenSet set) {
        return set.contains(node.getElementType());
    }

    /**
     * @see #hasElementType(com.intellij.lang.ASTNode, com.intellij.psi.tree.TokenSet)
     */
    public static boolean hasElementType(@NotNull ASTNode node, IElementType... types) {
        return hasElementType(node, TokenSet.create(types));
    }

    /**
     * @see #hasElementType(com.intellij.lang.ASTNode, com.intellij.psi.tree.TokenSet)
     */
    public static boolean hasElementType(@NotNull PsiElement element, @NotNull TokenSet set) {
        return element.getNode() != null && hasElementType(element.getNode(), set);
    }

    /**
     * @see #hasElementType(com.intellij.lang.ASTNode, com.intellij.psi.tree.IElementType...)
     */
    public static boolean hasElementType(@NotNull PsiElement element, IElementType... types) {
        return element.getNode() != null && hasElementType(element.getNode(), types);
    }

    public static SmcState findStateByNameWithinCurrentMap(@NotNull PsiElement psiElement, String name) {
        SmcMap map = PsiTreeUtil.getParentOfType(psiElement, SmcMap.class);
        return map != null && map.getStates() != null ? findNamedElementByTypeAndName(map.getStates(), SmcState.class, name) : null;
    }

    @NotNull
    public static <T extends PsiElement, K extends PsiNamedElement> List<K> getNamedElementsByTypeWithinParentType(PsiElement myElement, Class<T> enclosingType, Class<K> enclosedType, String name) {
        T parentOfType = PsiTreeUtil.getParentOfType(myElement, enclosingType);
        return SmcPsiUtil.findNamedElementsByType(parentOfType, enclosedType, name);
    }

    @NotNull
    public static <T extends PsiElement, K extends PsiElement> Collection<K> getElementsByTypeWithinParentType(PsiElement myElement, Class<T> enclosingType, Class<K> enclosedType) {
        T parentOfType = PsiTreeUtil.getParentOfType(myElement, enclosingType);
        return PsiTreeUtil.findChildrenOfType(parentOfType, enclosedType);
    }

    @NotNull
    public static <T extends PsiNamedElement, K extends PsiNamedElement> Collection<K> getElementsByTypeAndNameWithinNamedType(@NotNull PsiElement myElement,
                                                                                                                               @NotNull Class<T> enclosingType,
                                                                                                                               @NotNull String enclosingName,
                                                                                                                               @NotNull Class<K> enclosedType,
                                                                                                                               @NotNull String enclosedName) {
        Collection<K> results = new ArrayList<>();
        List<T> namedElementsByType = SmcPsiUtil.findNamedElementsByType(myElement.getContainingFile(), enclosingType, enclosingName);
        for (T enclosing : namedElementsByType) {
            results.addAll(SmcPsiUtil.findNamedElementsByType(enclosing, enclosedType, enclosedName));
        }
        return results;
    }

    @NotNull
    public static <T extends PsiNamedElement, K extends PsiNamedElement> Collection<K> getAllElementsByTypeWithinNamedType(@NotNull PsiElement myElement,
                                                                                                                           @NotNull Class<T> enclosingType,
                                                                                                                           String enclosingName,
                                                                                                                           @NotNull Class<K> enclosedType) {
        if (StringUtil.isEmpty(enclosingName)) {
            return PsiTreeUtil.findChildrenOfType(myElement.getContainingFile(), enclosedType);
        }
        Collection<K> results = new ArrayList<>();
        List<T> namedElementsByType = SmcPsiUtil.findNamedElementsByType(myElement.getContainingFile(), enclosingType, enclosingName);
        for (T enclosing : namedElementsByType) {
            results.addAll(PsiTreeUtil.findChildrenOfType(enclosing, enclosedType));
        }
        return results;
    }

    public static <T extends PsiElement, K extends PsiNamedElement> boolean hasNamedElementsByTypeWithinParentType(PsiElement myElement, Class<T> enclosingType, Class<K> enclosedType, String name) {
        T parentOfType = PsiTreeUtil.getParentOfType(myElement, enclosingType);
        return !SmcPsiUtil.findNamedElementsByType(parentOfType, enclosedType, name).isEmpty();
    }

    private static <T extends SmcNamedElement> T findNamedElementByTypeAndName(PsiElement parentElement, Class<T> smcNamedElement, String name) {
        Collection<T> childrenOfType = PsiTreeUtil.findChildrenOfType(parentElement, smcNamedElement);
        for (T child : childrenOfType) {
            if (name.equals(child.getName())) {
                return child;
            }
        }
        return null;
    }


    public static <T extends PsiNamedElement> List<T> findNamedElementsByType(PsiElement parentElement, Class<T> type, String name) {
        Collection<T> childrenOfType = PsiTreeUtil.findChildrenOfType(parentElement, type);
        List<T> elements = new ArrayList<T>();
        for (T child : childrenOfType) {
            if (name.equals(child.getName())) {
                elements.add(child);
            }
        }
        return elements;
    }


    public static List<PsiMethod> findJavaMethod(Project project) {
        List<PsiMethod> result = new ArrayList<PsiMethod>();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, JavaFileType.INSTANCE,
                GlobalSearchScope.projectScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            PsiJavaFile javaFile = (PsiJavaFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (javaFile != null) {
                Collection<PsiMethod> properties = PsiTreeUtil.findChildrenOfType(javaFile, PsiMethod.class);
                result.addAll(properties);
            }
        }
        return result;
    }

    public static List<SmcMap> findMapGlobally(Project project) {
        List<SmcMap> result = new ArrayList<SmcMap>();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, SmcFileType.INSTANCE,
                GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            SmcFile simpleFile = (SmcFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile != null) {
                Collection<SmcMap> properties = PsiTreeUtil.findChildrenOfType(simpleFile, SmcMap.class);
                result.addAll(properties);
            }
        }
        return result;
    }

    public static List<SmcMap> findMap(PsiFile file) {
        List<SmcMap> result = new ArrayList<SmcMap>();
        if (file != null) {
            Collection<SmcMap> properties = PsiTreeUtil.findChildrenOfType(file, SmcMap.class);
            result.addAll(properties);
        }
        return result;
    }

    @NotNull
    public static String getFullNameMethod(String name, int argumentCount, boolean appendBracesIfNoArgs) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        boolean needToShowBracesIfNoArguments = appendBracesIfNoArgs || argumentCount > 0;
        if (needToShowBracesIfNoArguments) {
            stringBuilder.append("(");
        }
        for (int i = 0; i < argumentCount; i++) {
            stringBuilder.append(SmcPsiImplUtil.ARG_PREFIX).append(i);
            if (i < argumentCount - 1) {
                stringBuilder.append(SmcPsiImplUtil.MY_COMMA).append(SmcPsiImplUtil.SPACE);
            }
        }
        if (needToShowBracesIfNoArguments) {
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }

    public static boolean staticMemberExists(String qualifiedName, PsiClass fsmClassQName, Project project) {
        String parentName = getParentName(qualifiedName);
        String shortName = SmcStringUtils.getSimpleName(qualifiedName, parentName);
        PsiClass aClass = findClass(parentName, project);
        Set<String> staticNames = null;
        if (aClass != null && fsmClassQName != null) {
            staticNames = collectStaticNames(aClass.getMethods(), fsmClassQName, project);
            staticNames.addAll(
                    collectStaticNames(aClass.getFields(), fsmClassQName, project));
        }
        return staticNames != null && staticNames.contains(shortName);
    }

    private static <T extends PsiMember> Set<String> collectStaticNames(T[] members, PsiClass psiClass, Project project) {
        Set<String> result = new HashSet<>();
        for (T member : members) {
            if (member.hasModifierProperty(PsiModifier.STATIC) && getPsiResolveHelper(project).isAccessible(member, psiClass, null)) {
                result.add(member.getName());
            }
        }
        return result;
    }

    public static String getParentName(String qname) {
        int lastDotIndex = qname.lastIndexOf(SmcPsiImplUtil.STRING_DOT);

        return lastDotIndex < 0 ? "" : qname.substring(0, lastDotIndex);
    }

    public static boolean staticClassExists(String qualifiedName, Project project) {
        PsiClass aClass = findClass(qualifiedName, project);
        return aClass != null && aClass.hasModifierProperty(PsiModifier.STATIC);
    }

    public static boolean isTypeAvailableFromSmc(SmcParameterType name) {
        SmcFile containingFile = (SmcFile)name.getContainingFile();
        Collection<PsiClass> importedClasses = findImportedClass(containingFile, name.getName());
        return !importedClasses.isEmpty();
    }
}
