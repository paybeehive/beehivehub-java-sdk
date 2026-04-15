package br.com.paybeehive.sdk;

public final class BeehiveHubConstants {

    public static final String VERSION = "1.0.0";

    public static final String PRODUCTION_API_URL = "https://api.conta.paybeehive.com.br/v1";
    public static final String SANDBOX_API_URL = "https://api.sandbox.hopysplit.com.br/v1";

    public static final String PRODUCTION_PAYMENT_LINK_URL = "https://link.conta.paybeehive.com.br";
    public static final String SANDBOX_PAYMENT_LINK_URL = "https://link.sandbox.hopysplit.com.br";

    public static final String DOCS_URL = "https://docs.beehivehub.io";

    public static final String USER_AGENT = "beehivehub-java-sdk/" + VERSION;

    private BeehiveHubConstants() {}
}
