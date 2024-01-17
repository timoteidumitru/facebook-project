package facebook.app.controller;

import facebook.app.exceptions.InvalidEmailFormatException;
import facebook.app.services.ProfileService;
import java.security.InvalidParameterException;

public class ProfileController {
    private final ProfileService profileService = new ProfileService();

    public void createProfile(int id, String name, String email, int age, String location) throws InvalidEmailFormatException {
        if (age <= 0 || age >= 110) {
            throw new InvalidParameterException("The age can not be lesser then 1 or over 110");
        }
        if (!email.contains("@")) {
            throw new InvalidEmailFormatException("Invalid email format for: " + email);
        }
        profileService.createProfile(id, name, email, age, location);
    }

    public void editProfile(int id, String name, String email, int age, String location) {
        if (name.length() < 2 || location.length() < 2) {
            throw new InvalidParameterException("The entered data does not correspond to minimum length of 3 characters");
        }
        if (age <= 0 || age >= 110) {
            throw new InvalidParameterException("The age can not be lesser then 1 or over 110");
        }
        profileService.writeProfile(id, name, email, age, location);
    }
}
