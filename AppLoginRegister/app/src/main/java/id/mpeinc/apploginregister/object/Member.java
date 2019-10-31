package id.mpeinc.apploginregister.object;

import java.util.Date;

public class Member {
    String userName;
    String fullName;
    Date sessionExpiredDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getSessionExpiredDate() {
        return sessionExpiredDate;
    }

    public void setSessionExpiredDate(Date sessionExpiredDate) {
        this.sessionExpiredDate = sessionExpiredDate;
    }
}
