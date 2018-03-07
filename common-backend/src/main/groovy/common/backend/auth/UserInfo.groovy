package common.backend.auth

class UserInfo {


    UserInfo() {

    }

    UserInfo(String principal) {
        this(principal,null)
    }

    UserInfo(String principal, String accountUrl) {
        this.principal = principal
        this.accountUrl = accountUrl
        this.authenticated = true
    }


    protected boolean authenticated = false
    protected String  principal = null
    protected String  accountUrl = null

    boolean getAuthenticated() {
        return authenticated
    }

    String getPrincipal() {
        return principal
    }

    String getAccountUrl() {
        return accountUrl
    }
}
