/*
 * Copyright 2014 Attila Szegedi, Daniel Dekany, Jonathan Revusky
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package freemarker.core;

import static freemarker.core.HTMLOutputFormat.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import freemarker.template.TemplateModelException; 

public class HTMLOutputFormatTest {
    
    @Test
    public void testOutputMO() throws TemplateModelException, IOException {
       StringWriter out = new StringWriter();
       
       INSTANCE.output(INSTANCE.fromMarkup("<p>Test "), out);
       INSTANCE.output(INSTANCE.escapePlainText("foo & bar "), out);
       INSTANCE.output(INSTANCE.escapePlainText("baaz "), out);
       INSTANCE.output(INSTANCE.escapePlainText("<b>A</b> <b>B</b> <b>C</b>"), out);
       INSTANCE.output(INSTANCE.escapePlainText(""), out);
       INSTANCE.output(INSTANCE.escapePlainText("\"' x's \"y\" \""), out);
       INSTANCE.output(INSTANCE.fromMarkup("</p>"), out);
       
       assertEquals(
               "<p>Test "
               + "foo &amp; bar "
               + "baaz "
               + "&lt;b&gt;A&lt;/b&gt; &lt;b&gt;B&lt;/b&gt; &lt;b&gt;C&lt;/b&gt;"
               + "&quot;&#39; x&#39;s &quot;y&quot; &quot;"
               + "</p>",
               out.toString());
    }
    
    @Test
    public void testOutputString() throws TemplateModelException, IOException {
        StringWriter out = new StringWriter();
        
        INSTANCE.output("a", out);
        INSTANCE.output("<", out);
        INSTANCE.output("b'c", out);
        
        assertEquals("a&lt;b&#39;c", out.toString());
    }
    
    @Test
    public void testEscapePlainText() throws TemplateModelException {
        String plainText = "a&b";
        TemplateHTMLModel mo = INSTANCE.escapePlainText(plainText);
        assertSame(plainText, mo.getPlainTextContent());
        assertNull(mo.getMarkupContent()); // Not the MO's duty to calculate it!
    }

    @Test
    public void testFromMarkup() throws TemplateModelException {
        String markup = "a&amp;b";
        TemplateHTMLModel mo = INSTANCE.fromMarkup(markup);
        assertSame(markup, mo.getMarkupContent());
        assertNull(mo.getPlainTextContent()); // Not the MO's duty to calculate it!
    }
    
    @Test
    public void testMarkup() throws TemplateModelException {
        {
            String markup = "a&amp;b";
            TemplateHTMLModel mo = INSTANCE.fromMarkup(markup);
            assertSame(markup, INSTANCE.getMarkup(mo));
        }
        
        {
            String safe = "abc";
            TemplateHTMLModel mo = INSTANCE.escapePlainText(safe);
            assertSame(safe, INSTANCE.getMarkup(mo));
        }
        {
            String safe = "";
            TemplateHTMLModel mo = INSTANCE.escapePlainText(safe);
            assertSame(safe, INSTANCE.getMarkup(mo));
        }
        {
            TemplateHTMLModel mo = INSTANCE.escapePlainText("<abc");
            assertEquals("&lt;abc", INSTANCE.getMarkup(mo));
        }
        {
            TemplateHTMLModel mo = INSTANCE.escapePlainText("abc>");
            assertEquals("abc&gt;", INSTANCE.getMarkup(mo));
        }
        {
            TemplateHTMLModel mo = INSTANCE.escapePlainText("<abc>");
            assertEquals("&lt;abc&gt;", INSTANCE.getMarkup(mo));
        }
        {
            TemplateHTMLModel mo = INSTANCE.escapePlainText("a&bc");
            assertEquals("a&amp;bc", INSTANCE.getMarkup(mo));
        }
        {
            TemplateHTMLModel mo = INSTANCE.escapePlainText("a&b&c");
            assertEquals("a&amp;b&amp;c", INSTANCE.getMarkup(mo));
        }
        {
            TemplateHTMLModel mo = INSTANCE.escapePlainText("a<&>b&c");
            assertEquals("a&lt;&amp;&gt;b&amp;c", INSTANCE.getMarkup(mo));
        }
        {
            TemplateHTMLModel mo = INSTANCE.escapePlainText("\"<a<&>b&c>\"");
            assertEquals("&quot;&lt;a&lt;&amp;&gt;b&amp;c&gt;&quot;", INSTANCE.getMarkup(mo));
        }
        {
            TemplateHTMLModel mo = INSTANCE.escapePlainText("<");
            assertEquals("&lt;", INSTANCE.getMarkup(mo));
        }
        {
            TemplateHTMLModel mo = INSTANCE.escapePlainText("'");
            String mc = INSTANCE.getMarkup(mo);
            assertEquals("&#39;", mc);
            assertSame(mc, INSTANCE.getMarkup(mo)); // cached
        }
    }
    
    @Test
    public void testConcat() {
        assertMO(
                "ab", null,
                INSTANCE.concat(new TemplateHTMLModel("a", null), new TemplateHTMLModel("b", null)));
        assertMO(
                null, "ab",
                INSTANCE.concat(new TemplateHTMLModel(null, "a"), new TemplateHTMLModel(null, "b")));
        assertMO(
                null, "<a>&lt;b&gt;",
                INSTANCE.concat(new TemplateHTMLModel(null, "<a>"), new TemplateHTMLModel("<b>", null)));
        assertMO(
                null, "&lt;a&gt;<b>",
                INSTANCE.concat(new TemplateHTMLModel("<a>", null), new TemplateHTMLModel(null, "<b>")));
    }
    
    private void assertMO(String pc, String mc, TemplateHTMLModel mo) {
        assertEquals(pc, mo.getPlainTextContent());
        assertEquals(mc, mo.getMarkupContent());
    }
    
    @Test
    public void testGetMimeType() {
        assertEquals("text/html", INSTANCE.getMimeType());
    }
    
}