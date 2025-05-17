package org.tec.carpooling.common.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    private static final int BCRYPT_WORKLOAD = 12; // Standard workload factor

    public static String hashPassword(String plainTextPassword) {
        if (plainTextPassword == null || plainTextPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(BCRYPT_WORKLOAD));
    }

    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        if (plainTextPassword == null || hashedPassword == null) {
            return false;
        }
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}