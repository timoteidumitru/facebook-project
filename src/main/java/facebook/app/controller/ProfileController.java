package facebook.app.controller;

import facebook.app.dao.ProfileDAO;
import facebook.app.entities.Profile;

import java.security.InvalidParameterException;
import java.util.List;

public class ProfileController {
    private final ProfileDAO profileDAO = new ProfileDAO();


    public void editProfile(int id, String name, String email, int age, String location) {

        if (name.length() < 2 || location.length() < 2) {
            throw new InvalidParameterException("The data does not have corresponding length");
        }
        if (age == 0 || age >= 110) {
            throw new InvalidParameterException("The age can not be 0 or over 110");
        }
        profileDAO.writeProfile(id, name, email, age, location);
    }

    public List<Profile> getAllProfile() {
        return profileDAO.readProfile();
    }

//    public void checkProfile(int currentID) {
//        List<Profile> profileList = getAllProfile();
//
//        for (Profile profile : profileList) {
//           // if (profileList.get(currentID))
  
//                System.out.println("profilul curent" + profile);

//        }
//        System.out.println("toate profilele" + profileList);
//    }

//    private boolean isUserUnique(List<Profile> profileList, String email) {
//        return profileList.stream().noneMatch(profile -> profile.getEmail().equals(email));
//    }


//    public Profile getUserByID(int id) throws UserNotFoundException, UserIOException {
//        return profileService.getUserByID(id);
//    }

}
