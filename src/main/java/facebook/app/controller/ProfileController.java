package facebook.app.controller;

import facebook.app.dao.ProfileDAO;
import facebook.app.entities.Profile;
import facebook.app.entities.User;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class ProfileController {

    // filrare date
    private ProfileDAO profileDAO = new ProfileDAO();

    public void editProfile(int id, String name, String email, int age, String location) {

        if (name.length() < 2 || location.length() < 2) {
            throw new InvalidParameterException("The data does not have corresponding length");
        }
        // if(email.contains("@0" & ".")
        if (age == 0 || age >= 110) {
            throw new InvalidParameterException("The age can not be 0 or over 110");
        }
        profileDAO.writeProfile(id, name, email, age, location);
    //    List<Profile> profileList = asList(id, name, email, age, location);
    //    profileDAO.writeProfile(Profile.profileToString());

    }
    public void checkProfile(Profile userProfile) {
        List<Profile> profileList = profileDAO.readProfile();
        if (!isUserUnique(profileList, userProfile.getEmail())) {
            System.out.println("User with email " + userProfile.getEmail() + " already exists. Please try a different one!");
            return;
        }
        profileList.add(userProfile);
        System.out.println(userProfile+"Profile Controler");
     //   profileDAO.writeProfile(profileList);
    }
    public List<Profile> getAllProfile() {
        return profileDAO.readProfile();
    }
    private boolean isUserUnique(List<Profile> profileList, String email) {
        return profileList.stream().noneMatch(profile -> profile.getEmail().equals(email));
    }

}
