package facebook.app.services;

import facebook.app.controller.ProfileController;
import facebook.app.dao.ProfileDAO;
import facebook.app.entities.Profile;
import facebook.app.exceptions.InvalidEmailFormatException;
import facebook.app.exceptions.UserIOException;

import java.util.List;

public class ProfileService {
    private final ProfileDAO profileDAO = new ProfileDAO();
    private final UserService userService = new UserService();
    public List<Profile> getAllProfile() {
        return profileDAO.readProfile();
    }

    public Profile displayCurrentProfileDetails() throws UserIOException {
        int userId = userService.getCurrentUserId();
        Profile prodDetails = new Profile();
        for (Profile p : getAllProfile()) {
            if (p.getId() == userId) {
                prodDetails = p;
            }
        }
        return prodDetails;
    }

    public void createProfile(int id, String name, String email, int age, String location) {
        // Here you can add additional validation if needed
        Profile profile = new Profile(id, name, email, age, location);
        // Now, write the profile using ProfileDAO
        profileDAO.writeProfile(profile);
    }

    public void editProfile(int id, String name, String email, int age, String location) {
        Profile profile = new Profile(id, name, email, age, location);
        profileDAO.editProfile(profile);
    }
}
