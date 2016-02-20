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
            new AttributesDescriptor("LIne Comment", SmcSyntaxHighlighter.SMC_LINE_COMMENT),
            new AttributesDescriptor("Block Comment", SmcSyntaxHighlighter.SMC_BLOCK_COMMENT),
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
            new AttributesDescriptor("State Map Name", SmcSyntaxHighlighter.SMC_STATE_MAP_NAME),
            new AttributesDescriptor("State Name", SmcSyntaxHighlighter.SMC_STATE_NAME),
            new AttributesDescriptor("Transition Name", SmcSyntaxHighlighter.SMC_TRANSITION_NAME),
            new AttributesDescriptor("Transition Parameter", SmcSyntaxHighlighter.SMC_PARAMETER),
            new AttributesDescriptor("Other", SmcSyntaxHighlighter.SMC_DEFAULT),

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