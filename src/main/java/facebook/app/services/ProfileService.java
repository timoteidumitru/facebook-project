package facebook.app.services;

import facebook.app.controller.ProfileController;
import facebook.app.dao.ProfileDAO;
import facebook.app.entities.Profile;

import java.util.List;


public class ProfileService {
    private final ProfileDAO profileDAO = new ProfileDAO();
    private final ProfileController profileController = new ProfileController();

    public List<Profile> getAllProfile() {
        return profileDAO.readProfile();
    }

    public int generateUniqueProfileId() {
        return getAllProfile()
                .stream()
                .mapToInt(Profile::getId) //.mapToInt(p -> p.getId())
                .max()
                .orElse(0) + 1;
    }

    public Profile getCurrentUserId(int userId) {
        List<Profile> profileList = profileDAO.readProfile();
        return profileList.stream()
                .filter(profile -> profile.getId() == userId)
                .findFirst()
                .orElse(null);
    }
    public Profile getLastUserId() {
        List<Profile> profileList = profileDAO.readProfile();
        Profile newProfile = profileList.get(profileList.size() - 1);
        return  newProfile;
    }
}
