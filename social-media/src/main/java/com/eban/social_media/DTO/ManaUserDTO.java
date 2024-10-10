package com.eban.social_media.DTO;

public class ManaUserDTO {
    UserDTO userDTO;
    ProfileDetailDTO profileDetailDTO;

    public ManaUserDTO(UserDTO userDTO, ProfileDetailDTO profileDetailDTO) {
        this.userDTO = userDTO;
        this.profileDetailDTO = profileDetailDTO;
    }

    public ManaUserDTO() {

    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public ProfileDetailDTO getProfileDetailDTO() {
        return profileDetailDTO;
    }

    public void setProfileDetailDTO(ProfileDetailDTO profileDetailDTO) {
        this.profileDetailDTO = profileDetailDTO;
    }
}
