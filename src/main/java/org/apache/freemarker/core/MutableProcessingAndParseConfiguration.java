/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.freemarker.core;

import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.freemarker.core.outputformat.OutputFormat;
import org.apache.freemarker.core.templateresolver.TemplateLoader;
import org.apache.freemarker.core.util._NullArgumentException;

// TODO This will be the superclass of TemplateConfiguration.Builder and Configuration.Builder
public abstract class MutableProcessingAndParseConfiguration<
        SelfT extends MutableProcessingAndParseConfiguration<SelfT>>
        extends MutableProcessingConfiguration<SelfT>
        implements ParserAndProcessingConfiguration {

    private TemplateLanguage templateLanguage;
    private Integer tagSyntax;
    private Integer namingConvention;
    private Boolean whitespaceStripping;
    private Integer autoEscapingPolicy;
    private Boolean recognizeStandardFileExtensions;
    private OutputFormat outputFormat;
    private Charset sourceEncoding;
    private Integer tabSize;

    protected MutableProcessingAndParseConfiguration(Version incompatibleImprovements) {
        super(incompatibleImprovements);
    }

    protected MutableProcessingAndParseConfiguration(MutableProcessingConfiguration parent) {
        super(parent);
    }

    /**
     * See {@link Configuration#setTagSyntax(int)}.
     */
    public void setTagSyntax(int tagSyntax) {
        Configuration.valideTagSyntaxValue(tagSyntax);
        this.tagSyntax = tagSyntax;
    }

    /**
     * The getter pair of {@link #setTagSyntax(int)}.
     */
    @Override
    public int getTagSyntax() {
        return tagSyntax != null ? tagSyntax : getInheritedTagSyntax();
    }

    protected abstract int getInheritedTagSyntax();

    @Override
    public boolean isTagSyntaxSet() {
        return tagSyntax != null;
    }

    /**
     * See {@link Configuration#getTemplateLanguage()}
     */
    @Override
    public TemplateLanguage getTemplateLanguage() {
         return isTemplateLanguageSet() ? templateLanguage : getInheritedTemplateLanguage();
    }

    protected abstract TemplateLanguage getInheritedTemplateLanguage();

    /**
     * See {@link Configuration#setTemplateLanguage(TemplateLanguage)}
     */
    public void setTemplateLanguage(TemplateLanguage templateLanguage) {
        _NullArgumentException.check("templateLanguage", templateLanguage);
        this.templateLanguage = templateLanguage;
    }

    public boolean isTemplateLanguageSet() {
        return templateLanguage != null;
    }

    /**
     * See {@link Configuration#setNamingConvention(int)}.
     */
    public void setNamingConvention(int namingConvention) {
        Configuration.validateNamingConventionValue(namingConvention);
        this.namingConvention = namingConvention;
    }

    /**
     * The getter pair of {@link #setNamingConvention(int)}.
     */
    @Override
    public int getNamingConvention() {
         return isNamingConventionSet() ? namingConvention
                : getInheritedNamingConvention();
    }

    protected abstract int getInheritedNamingConvention();

    /**
     * Tells if this setting is set directly in this object or its value is coming from the {@link #getParent() parent}.
     */
    @Override
    public boolean isNamingConventionSet() {
        return namingConvention != null;
    }

    /**
     * See {@link Configuration#setWhitespaceStripping(boolean)}.
     */
    public void setWhitespaceStripping(boolean whitespaceStripping) {
        this.whitespaceStripping = Boolean.valueOf(whitespaceStripping);
    }

    /**
     * The getter pair of {@link #getWhitespaceStripping()}.
     */
    @Override
    public boolean getWhitespaceStripping() {
         return isWhitespaceStrippingSet() ? whitespaceStripping.booleanValue()
                : getInheritedWhitespaceStripping();
    }

    protected abstract boolean getInheritedWhitespaceStripping();

    /**
     * Tells if this setting is set directly in this object or its value is coming from the {@link #getParent() parent}.
     */
    @Override
    public boolean isWhitespaceStrippingSet() {
        return whitespaceStripping != null;
    }

    /**
     * Sets the output format of the template; see {@link Configuration#setAutoEscapingPolicy(int)} for more.
     */
    public void setAutoEscapingPolicy(int autoEscapingPolicy) {
        Configuration.validateAutoEscapingPolicyValue(autoEscapingPolicy);

        this.autoEscapingPolicy = Integer.valueOf(autoEscapingPolicy);
    }

    /**
     * The getter pair of {@link #setAutoEscapingPolicy(int)}.
     */
    @Override
    public int getAutoEscapingPolicy() {
         return isAutoEscapingPolicySet() ? autoEscapingPolicy.intValue()
                : getInheritedAutoEscapingPolicy();
    }

    protected abstract int getInheritedAutoEscapingPolicy();

    /**
     * Tells if this setting is set directly in this object or its value is coming from the {@link #getParent() parent}.
     */
    @Override
    public boolean isAutoEscapingPolicySet() {
        return autoEscapingPolicy != null;
    }

    /**
     * Sets the output format of the template; see {@link Configuration#setOutputFormat(OutputFormat)} for more.
     */
    public void setOutputFormat(OutputFormat outputFormat) {
        _NullArgumentException.check("outputFormat", outputFormat);
        this.outputFormat = outputFormat;
    }

    /**
     * The getter pair of {@link #setOutputFormat(OutputFormat)}.
     */
    @Override
    public OutputFormat getOutputFormat() {
         return isOutputFormatSet() ? outputFormat : getInheritedOutputFormat();
    }

    protected abstract OutputFormat getInheritedOutputFormat();

    /**
     * Tells if this setting is set directly in this object or its value is coming from the {@link #getParent() parent}.
     */
    @Override
    public boolean isOutputFormatSet() {
        return outputFormat != null;
    }

    /**
     * See {@link Configuration#setRecognizeStandardFileExtensions(boolean)}.
     */
    public void setRecognizeStandardFileExtensions(boolean recognizeStandardFileExtensions) {
        this.recognizeStandardFileExtensions = Boolean.valueOf(recognizeStandardFileExtensions);
    }

    /**
     * Getter pair of {@link #setRecognizeStandardFileExtensions(boolean)}.
     */
    @Override
    public boolean getRecognizeStandardFileExtensions() {
         return isRecognizeStandardFileExtensionsSet() ? recognizeStandardFileExtensions.booleanValue()
                : getInheritedRecognizeStandardFileExtensions();
    }

    protected abstract boolean getInheritedRecognizeStandardFileExtensions();

    /**
     * Tells if this setting is set directly in this object or its value is coming from the {@link #getParent() parent}.
     */
    @Override
    public boolean isRecognizeStandardFileExtensionsSet() {
        return recognizeStandardFileExtensions != null;
    }

    @Override
    public Charset getSourceEncoding() {
         return isSourceEncodingSet() ? sourceEncoding : getInheritedSourceEncoding();
    }

    protected abstract Charset getInheritedSourceEncoding();

    /**
     * The charset to be used when reading the template "file" that the {@link TemplateLoader} returns as binary
     * ({@link InputStream}). If the {@code #ftl} header specifies an charset, that will override this.
     */
    public void setSourceEncoding(Charset sourceEncoding) {
        _NullArgumentException.check("sourceEncoding", sourceEncoding);
        this.sourceEncoding = sourceEncoding;
    }

    public boolean isSourceEncodingSet() {
        return sourceEncoding != null;
    }

    /**
     * See {@link Configuration#setTabSize(int)}.
     *
     * @since 2.3.25
     */
    public void setTabSize(int tabSize) {
        this.tabSize = Integer.valueOf(tabSize);
    }

    /**
     * Getter pair of {@link #setTabSize(int)}.
     *
     * @since 2.3.25
     */
    @Override
    public int getTabSize() {
         return isTabSizeSet() ? tabSize.intValue() : getInheritedTabSize();
    }

    protected abstract int getInheritedTabSize();

    /**
     * Tells if this setting is set directly in this object or its value is coming from the {@link #getParent() parent}.
     *
     * @since 2.3.25
     */
    @Override
    public boolean isTabSizeSet() {
        return tabSize != null;
    }

}