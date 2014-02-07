package org.mdubois.freeveggie.service.matcher;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 *
 * @author Mickael Dubois
 */
public class ReflectionMatcher<T> extends TypeSafeMatcher<T> {

    private T expected;
    private String[] excludesField;

    public ReflectionMatcher(T expected) {
        this.expected = expected;
    }

    public ReflectionMatcher(T expected, String[] excludesField) {
        this.expected = expected;
        this.excludesField = excludesField;
    }

    @Override
    protected boolean matchesSafely(T actual) {
        return EqualsBuilder.reflectionEquals(expected, actual, excludesField);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(ToStringBuilder.reflectionToString(expected));
    }
}
