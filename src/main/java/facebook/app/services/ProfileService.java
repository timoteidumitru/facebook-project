package facebook.app.services;

import facebook.app.controller.ProfileController;
import facebook.app.dao.ProfileDAO;
import facebook.app.entities.Profile;
import facebook.app.exceptions.InvalidEmailFormatException;
import java.util.List;

public class ProfileService {
    private final ProfileDAO profileDAO = new ProfileDAO();
    public List<Profile> getAllProfile() {
        return profileDAO.readProfile();
    }

    public Profile getLastProfileId() {
        List<Profile> profileList = getAllProfile();
        return  profileList.get(profileList.size() - 1);
    }

    public Profile displayCurrentProfile(int userId, Profile profile) {
        for (Profile p : profileDAO.readProfile()) {
            if (p.getId() == userId) {
                profile = p;
            }
        }
        return profile;
    }

    public void createProfile(int id, String name, String email, int age, String location) {
        // Here you can add additional validation if needed
        Profile profile = new Profile(id, name, email, age, location);
        // Now, write the profile using ProfileDAO
        profileDAO.writeProfile(profile);
    }

    public void editProfile(int id, String name, String email, int age, String location) {
//        profileController.editProfile(id, name, email, age, location);
    }

    public void writeProfile(int id, String name, String email, int age, String location) {
        // Here you can add additional validation if needed
        Profile profile = new Profile(id, name, email, age, location);
        // Now, write the profile using ProfileDAO
        profileDAO.writeProfile(profile);
    }
}
