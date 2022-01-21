package save.entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="pawer")
public class Pawer {
    String username;
    String password;
    String nickname;
    @Id
    Integer id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pawer(String username, String password, String nickname, Integer id) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.id = id;
    }

    public Pawer() {
    }
}
