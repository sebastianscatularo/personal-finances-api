package ar.com.jss.controller;

/**
 * @author sebastianscatularo@gmail.com.
 */
final class Path {
    public static final String ACCOUNT     = "/users/{user}/accounts";
    public static final String TRANSACTION = "/users/{user}/accounts/{account}/transactions";

    private Path() {
    }
}
