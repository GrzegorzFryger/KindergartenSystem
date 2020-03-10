package pl.edu.pja.prz.core.model;

import java.util.Objects;

public class AuthDto {
    private String username;
    private String password;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthDto authDto = (AuthDto) o;
        return username.equals(authDto.username) &&
                password.equals(authDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
