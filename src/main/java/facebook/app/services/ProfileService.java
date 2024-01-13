package facebook.app.services;

import facebook.app.controller.ProfileController;
import facebook.app.dao.ProfileDAO;
import facebook.app.entities.Profile;
import facebook.app.exceptions.UserIOException;

import java.util.InputMismatchException;

public class ProfileService {
    private  ProfileDAO profileDAO = new ProfileDAO();
    private ProfileController profileController = new ProfileController();

    public Profile getUserByID(int userID) throws UserIOException {
        Profile profile = profileDAO.getUserByID(userID);
        if (profile == null) {
            throw new InputMismatchException("User not found");
        }
        return profile;
    }
    public int generateUniqueProfileId() {
        return profileController.getAllProfile()
                .stream()
                .mapToInt(Profile::getId) //.mapToInt(p -> p.getId())
                .max()
                .orElse(0) + 1;
    }
}
