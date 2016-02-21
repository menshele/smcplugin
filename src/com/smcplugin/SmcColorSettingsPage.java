package com.smcplugin;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Action Name", SmcSyntaxHighlighter.SMC_ACTION_NAME),
            new AttributesDescriptor("Line Comment", SmcSyntaxHighlighter.SMC_LINE_COMMENT),
            new AttributesDescriptor("Block Comment Start", SmcSyntaxHighlighter.SMC_BLOCK_COMMENT_OPEN),
            new AttributesDescriptor("Block Comment Content", SmcSyntaxHighlighter.SMC_BLOCK_COMMENT_CONTENT),
            new AttributesDescriptor("Block Comment End", SmcSyntaxHighlighter.SMC_BLOCK_COMMENT_CLOSE),
            new AttributesDescriptor("Braces", SmcSyntaxHighlighter.SMC_BRACES),
            new AttributesDescriptor("Parentheses", SmcSyntaxHighlighter.SMC_PARENTHESES),
            new AttributesDescriptor("Brackets", SmcSyntaxHighlighter.SMC_BRACKETS),
            new AttributesDescriptor("Verbatim Brackets", SmcSyntaxHighlighter.SMC_VERBATIM_BRACKETS),
            new AttributesDescriptor("Comma", SmcSyntaxHighlighter.SMC_COMMA),
            new AttributesDescriptor("Semicolon", SmcSyntaxHighlighter.SMC_SEMICOLON),
            new AttributesDescriptor("Keyword", SmcSyntaxHighlighter.SMS_KEYWORD),
            new AttributesDescriptor("Entry Block Token", SmcSyntaxHighlighter.SMC_ENTRY_KEYWORD),
            new AttributesDescriptor("Exit Block Token", SmcSyntaxHighlighter.SMC_EXIT_KEYWORD),
            new AttributesDescriptor("Verbatim Code", SmcSyntaxHighlighter.SMC_VERBATIM_CODE),
            new AttributesDescriptor("Guard Code", SmcSyntaxHighlighter.SMC_GUARD_RAW_CODE),
            new AttributesDescriptor("Map Name", SmcSyntaxHighlighter.SMC_MAP_NAME),
            new AttributesDescriptor("Start Map Name", SmcSyntaxHighlighter.SMC_START_MAP_NAME),
            new AttributesDescriptor("State Name", SmcSyntaxHighlighter.SMC_STATE_NAME),
            new AttributesDescriptor("Start State Name", SmcSyntaxHighlighter.SMC_START_STATE_NAME),
            new AttributesDescriptor("Transition Name", SmcSyntaxHighlighter.SMC_TRANSITION_NAME),
            new AttributesDescriptor("Transition Parameter Name", SmcSyntaxHighlighter.SMC_PARAMETER_NAME),
            new AttributesDescriptor("Transition Parameter Type", SmcSyntaxHighlighter.SMC_PARAMETER_TYPE),
            new AttributesDescriptor("Other", SmcSyntaxHighlighter.SMC_DEFAULT)
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return SmcIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new SmcSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "%{\n" +
                "    some.row.code.Here();\n" +
                "%}\n" +
                "%class ClassName\n" +
                "//Line Comment\n" +
                "/*\n" +
                "Multiline Comment\n" +
                "*/\n" +
                "%package com.example.package\n" +
                "%start MainMap::Locked\n" +
                "%map\n" +
                "MainMap\n" +
                "%%\n" +
                "Locked\n" +
                "Entry {\n" +
                "   print(param, \"Entry\");\n" +
                "}\n" +
                "{\n" +
                "\n" +
                " coin ( arggs : asd , arg: test )\n" +
                " [ some guard here ]\n" +
                " Unlocked {\n" +
                "    unlock();\n" +
                " }\n" +
                " pass nil { alarm(); }\n" +
                "}\n" +
                "Unlocked\n" +
                "{\n" +
                " pass Locked { lock(); }\n" +
                " coin nil { thankyou(); }\n" +
                "}\n" +
                "%%";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Smc";
    }
}