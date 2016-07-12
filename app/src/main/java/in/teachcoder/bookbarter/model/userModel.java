package in.teachcoder.bookbarter.model;

/**
 * Created by Arnav on 12-Jul-16.
 */
public class UserModel {
    String userName, emailID;

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserModel(String userName, String emailID) {

        this.userName = userName;
        this.emailID = emailID;
    }

    public UserModel() {

    }
}
