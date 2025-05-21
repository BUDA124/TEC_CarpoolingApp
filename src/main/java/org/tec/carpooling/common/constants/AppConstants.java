package org.tec.carpooling.common.constants;

public class AppConstants {
    // System User for Audit Logs
    public static final String SYSTEM_USER = "CARPOOLING_APP_USER";

    // Gender
    public static final String GENDER_MALE = "Male";
    public static final String GENDER_FEMALE = "Female";
    public static final String GENDER_NON_BINARY = "Non-Binary";
    public static final String GENDER_PREFER_NOT_TO_SAY = "Prefer not to say";


    // User Status
    public static final String USER_STATUS_ACTIVE = "Active";
    public static final String USER_STATUS_BANNED = "Banned";
    public static final String USER_STATUS_PENDING_VERIFICATION = "Pending Verification";
    public static final String USER_STATUS_INACTIVE = "Inactive";

    // Credential Types
    public static final String CREDENTIAL_TYPE_NATIONAL_ID = "National ID"; // Cédula Nacional
    public static final String CREDENTIAL_TYPE_DIMEX = "DIMEX"; // Cédula de Residencia
    public static final String CREDENTIAL_TYPE_NITE = "NITE"; // Documento de Identificación Tributaria Especial
    public static final String CREDENTIAL_TYPE_PASSPORT = "Passport"; // Pasaporte

    // Payment Methods
    public static final String PAYMENT_METHOD_CASH = "Cash";
    public static final String PAYMENT_METHOD_SINPE = "SINPE"; // Transferencia SINPE Móvil (Costa Rica)
    public static final String PAYMENT_METHOD_APP_WALLET = "App Wallet"; // Saldo en la aplicación
    public static final String PAYMENT_METHOD_CREDIT_CARD = "Credit Card";
    public static final String PAYMENT_METHOD_DEBIT_CARD = "Debit Card";


    // Price Status
    public static final String PRICE_STATUS_FREE = "Free";
    public static final String PRICE_STATUS_NEGOTIABLE = "Negotiable";
    public static final String PRICE_STATUS_FIXED_TOTAL = "Fixed Total";
    public static final String PRICE_STATUS_PER_PASSENGER = "Per Passenger";

    // Trip Status
    public static final String TRIP_STATUS_SCHEDULED = "Scheduled";
    public static final String TRIP_STATUS_IN_PROGRESS = "In Progress";
    public static final String TRIP_STATUS_COMPLETED = "Completed";
    public static final String TRIP_STATUS_CANCELLED = "Cancelled";


    // Countries, Provinces, Cantons, Districts (Sample)
    public static final String COUNTRY_COSTA_RICA = "Costa Rica";
    public static final String PROVINCE_SAN_JOSE = "San José";
    public static final String CANTON_SAN_JOSE_CENTRAL = "San José"; // Cantón Central de San José
    public static final String DISTRICT_CARMEN_SAN_JOSE = "Carmen"; // Distrito Carmen del Cantón Central de San José

    // Parameters (Example)
    public static final String PARAM_MAX_LOGIN_ATTEMPTS = "MAX_LOGIN_ATTEMPTS";
    public static final String PARAM_SESSION_TIMEOUT_MINUTES = "SESSION_TIMEOUT_MINUTES";

}