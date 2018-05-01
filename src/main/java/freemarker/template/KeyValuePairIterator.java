package freemarker.template;

import java.util.NoSuchElementException;

/**
 * Iterates over the key-value pairs in a hash. This is very similar to an {link Iterator}, but has a fixed item
 * type, can throw {link TemplateModelException}-s, and has no {@code remove()} method.
 *
 * @since 2.3.25
 */
public interface KeyValuePairIterator {

    /**
     * Similar to {link Iterator#hasNext()}.
     */
    boolean hasNext() throws TemplateModelException;

    /**
     * Similar to {link Iterator#next()}.
     *
     * @return Not {@code null}
     *
     * @throws NoSuchElementException
     */
    KeyValuePair next() throws TemplateModelException;
}
