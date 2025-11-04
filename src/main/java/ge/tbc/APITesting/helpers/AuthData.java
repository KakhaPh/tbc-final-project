package helpers;

public class AuthData {
    private final String cookies;
    private final String xsrfToken;

    public AuthData(String cookies, String xsrfToken) {
        this.cookies = cookies;
        this.xsrfToken = xsrfToken;
    }

    public String getCookies() {
        return cookies;
    }

    public String getXsrfToken() {
        return xsrfToken;
    }
}