package io.nyaperos.libs.cli;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Optional;

import static org.hamcrest.core.IsEqual.equalTo;

public class OptionalMatchers {

    public static Matcher<Optional<?>> isEmpty() {
        return new EmptyMatcher();
    }

    private static class EmptyMatcher extends TypeSafeMatcher<Optional<?>> {

        public void describeTo(Description description) {
            description.appendText("is <Empty>");
        }

        @Override
        protected boolean matchesSafely(Optional<?> item) {
            return !item.isPresent();
        }

        @Override
        protected void describeMismatchSafely(Optional<?> item, Description mismatchDescription) {
            mismatchDescription.appendText("had value ");
            mismatchDescription.appendValue(item.get());
        }
    }


    public static <T> Matcher<Optional<T>> hasValue(T operand) {
        return new HasValue<>(equalTo(operand));
    }

    private static class HasValue<T> extends TypeSafeMatcher<Optional<T>> {
        private Matcher<? super T> matcher;

        HasValue(Matcher<? super T> matcher) {
            this.matcher = matcher;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("has value that is ");
            matcher.describeTo(description);
        }

        @Override
        protected boolean matchesSafely(Optional<T> item) {
            return item.isPresent() && matcher.matches(item.get());
        }

        @Override
        protected void describeMismatchSafely(Optional<T> item, Description mismatchDescription) {
            if (item.isPresent()) {
                mismatchDescription.appendText("value ");
                matcher.describeMismatch(item.get(), mismatchDescription);
            } else {
                mismatchDescription.appendText("was <Empty>");
            }
        }
    }
}