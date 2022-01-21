package save.pojo;

public class RegisterUser {
    String nickname;
    String username;
    String pass;
    String checkPass;

    @Override
    public String toString() {
        return "RegisterUser{" +
                "nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", checkPass='" + checkPass + '\'' +
                '}';
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCheckPass() {
        return checkPass;
    }

    public void setCheckPass(String checkPass) {
        this.checkPass = checkPass;
    }

    public RegisterUser() {
    }

    public RegisterUser(String nickname, String username, String pass, String checkPass) {
        this.nickname = nickname;
        this.username = username;
        this.pass = pass;
        this.checkPass = checkPass;
    }
}
