package org.tec.carpooling.common.utils;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

public final class HashingUtil {

    private static final int BCRYPT_WORKLOAD = 12;

    public static final int SEED_PRIME = 17;

    public static final int MULTIPLIER_PRIME = 31;

    private HashingUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static String hashPassword(String plainTextPassword) {
        if (plainTextPassword == null || plainTextPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(BCRYPT_WORKLOAD));
    }

    public static boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    public static int hash(Object... values) {
        return Objects.hash(values);
    }
}