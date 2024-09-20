package com.eban.social_media.DTO;

public class SearchUserDTO {
    private Long user_id;
    private String user_name, firstname, lastname, avatar;
    private boolean folow;
    private Long countFollow;

    public SearchUserDTO(Long user_id, String user_name, String firstname, String lastname, String avatar, boolean folow, Long countFollow) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.firstname = firstname;
        this.lastname = lastname;
        this.avatar = avatar;
        this.folow = folow;
        this.countFollow = countFollow;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isFolow() {
        return folow;
    }

    public void setFolow(boolean folow) {
        this.folow = folow;
    }

    public Long getCountFollow() {
        return countFollow;
    }

    public void setCountFollow(Long countFollow) {
        this.countFollow = countFollow;
    }
}
