package facebook.app.controller;

import facebook.app.dao.ProfileDAO;
import facebook.app.entities.Profile;
import facebook.app.entities.User;

import java.security.InvalidParameterException;
import java.util.List;

public class ProfileController {
    private final ProfileDAO profileDAO = new ProfileDAO();

    public void editProfile(int id, String name, String email, int age, String location) {
        if (name.length() < 2 || location.length() < 2) {
            throw new InvalidParameterException("The data does not have corresponding length");
        }
        // if(email.contains("@0" & ".")
        if (age == 0 || age >= 110) {
            throw new InvalidParameterException("The age can not be 0 or over 110");
        }
        profileDAO.writeProfile(id, name, email, age, location);
    }

    public List<Profile> getAllProfile() {
        return profileDAO.readProfile();
    }
}
