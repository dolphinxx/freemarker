package freemarker.template;

/**
 * A key-value pair in a hash; used for {link KeyValuePairIterator}.
 *
 * @since 2.3.25
 */
public interface KeyValuePair {

    /**
     * @return Any type of {link TemplateModel}, maybe {@code null} (if the hash entry key is {@code null}).
     */
    TemplateModel getKey() throws TemplateModelException;

    /**
     * @return Any type of {link TemplateModel}, maybe {@code null} (if the hash entry value is {@code null}).
     */
    TemplateModel getValue() throws TemplateModelException;
}
