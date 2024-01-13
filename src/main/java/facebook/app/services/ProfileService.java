package facebook.app.services;

import facebook.app.controller.ProfileController;
import facebook.app.dao.ProfileDAO;
import facebook.app.entities.Profile;

import facebook.app.exceptions.UserIOException;
import facebook.app.exceptions.UserNotFoundException;

import java.util.List;


public class ProfileService {
    private final ProfileDAO profileDAO = new ProfileDAO();
    private final ProfileController profileController = new ProfileController();

//    public Profile getUserByID(int userID) throws UserIOException {
//        Profile profile = profileDAO.getUserByID(userID);
//        if (profile == null) {
//            throw new InputMismatchException("User not found");
//        }
//        return profile;
//    }

    public Profile getUserByID(int id) throws UserNotFoundException, UserIOException {
        return profileDAO.getUserByID(id);
    }
    public List<Profile> getAllProfile() {
        return profileDAO.readProfile();
    }

//    public Profile getUserByID(int userId) throws UserIOException {
//        List<Profile> profileList = profileDAO.readProfile();
//        return profileList.stream()
//                .filter(profile -> profile.getId() == userId)
//                .findFirst()
//                .orElse(null);
//    }
    public int generateUniqueProfileId() {
        return getAllProfile()
                .stream()
                .mapToInt(Profile::getId) //.mapToInt(p -> p.getId())
                .max()
                .orElse(0) + 1;
    }
}
