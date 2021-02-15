package space.example.register;

public class Credentials {
    private String userName;
    private String uPassword;

    Credentials(String userName, String uPassword) {
        this.userName = userName;
        this.uPassword = uPassword;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }
}
