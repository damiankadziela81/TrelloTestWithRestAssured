package org.example.secrets;

public class TrelloSecrets {

    private static final String KEY = "fdab1639d2dd56a92c48fab8f8c3e1fe";
    private static final String TOKEN = "ATTA18ed3c705c06a3d94b4f1bdf634793f68cf6a4a68deeda2e3c46a1ff4476fd013AC98DB8";

    private TrelloSecrets() {

    }

    public static String getKey() {
        return KEY;
    }

    public static String getToken() {
        return TOKEN;
    }
}
