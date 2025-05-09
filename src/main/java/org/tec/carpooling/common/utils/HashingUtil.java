package org.tec.carpooling.common.utils;

import java.util.Objects;

/**
 * Utility class for hashing operations.
 */
public final class HashingUtil {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private HashingUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Computes a hash code from a sequence of input values.
     * This method delegates to {@link java.util.Objects#hash(Object...)}.
     *
     * <p>It's suitable for implementing {@link Object#hashCode()} methods.
     * For example:
     * <pre>{@code
     * @Override public int hashCode() {
     *     return HashingUtil.hash(field1, field2, field3);
     * }
     * }</pre>
     *
     * @param values the values to be hashed.
     * @return a hash code value for the sequence of input values.
     *         Returns 0 if no values are provided or if all values are null.
     */
    public static int hash(Object... values) {
        return Objects.hash(values);
    }

    /**
     * A common starting prime for manual hash code calculation if not using Objects.hash().
     * (Not strictly needed if always using Objects.hash())
     */
    public static final int SEED_PRIME = 17;

    /**
     * A common multiplier prime for manual hash code calculation.
     * (Not strictly needed if always using Objects.hash())
     */
    public static final int MULTIPLIER_PRIME = 31;
}