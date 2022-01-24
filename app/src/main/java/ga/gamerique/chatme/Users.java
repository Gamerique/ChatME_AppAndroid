package ga.gamerique.chatme;

public class Users {

    String profilePic, userName, userMail, userPhone, userPassword, userID, lastMessage;

    public Users(String profilePic, String userName, String userMail, String userPassword, String userID, String lastMessage) {
        this.profilePic = profilePic;
        this.userName = userName;
        this.userMail = userMail;
        this.userMail = userPassword;
        this.userPhone = userPhone;
        this.userID = userID;
        this.lastMessage = lastMessage;
    }

    public Users(){}


    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    // SignUp Constructor
    public Users(String userName, String userMail, String userPhone, String userPassword) {
        this.userName = userName;
        this.userMail = userMail;
        this.userPhone = userPhone;
        this.userPhone = userPassword;
    }


    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
