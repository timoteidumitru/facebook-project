package facebook.app.ui;

import facebook.app.controller.ProfileController;
import facebook.app.entities.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProfileUI {
    private static ProfileController profileController = new ProfileController();
    private Profile profile = new Profile();
    private ProfileController profileController = new ProfileController();


    public void startProfile() {
        System.out.println("        Welcome to the Profile page");
        System.out.println("Please choose one of the following options: ");
        System.out.println("      1. Create Profile           2. Display Profile ");
        System.out.println("                   0. Back");
        int choice;
        choice = Integer.parseInt(getUserInput());
        switch (choice) {
            case 1:
                editProfile();
                break;
            case 2:
                displayProfile();
                break;
            case 0:
                System.out.println("Returning to Main Menu.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    //create profile = edit profile
    public void editProfile() {
        System.out.println("Welcome to the Profile Creator!");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter your location: ");
        String location = scanner.nextLine();

        int id = generateUniqueProfileId();

        Profile userProfile = new Profile(id, name, email, age, location);

        System.out.println("\nProfile Created Successfully!");
        System.out.println("Profile Details:");
        System.out.println("ProfileID: " + userProfile.getId() +
                "\nName: " + name +
                "\nAge: " + age +
                "\nLocation: " + location);

        scanner.close();

        profileController.editProfile(id, name, email, age, location);
        //profileController.editProfile(userProfile);
    }

    public void displayProfile() {
        System.out.println("\nProfile to be Displayed!");
        System.out.println("Profile Details:");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        List<String> list = new ArrayList<>();
        for (Profile p : profileController.getAllProfile()) {
            String pName = p.getName();
            list.add(pName);
        }
      //  profileController.checkProfile(list.contains(name));
        if (list.contains(name)) {
          //  profile.setName(name);
            profileController.checkProfile(name);
            System.out.println("\n===== Your Profile =====");
            System.out.println("Name: " + profile.getName());
            System.out.println("Email: " + profile.getEmail());
            System.out.println("Age: " + profile.getAge());
            System.out.println("Location: " + profile.getLocation());
        } else {
            System.out.println("Profile not created yet. Please create a profile first.");
        }
        scanner.close();

    }

    private int generateUniqueProfileId() {
        // For simplicity, here's a basic implementation using the current time in milliseconds.
        return profileController.getAllProfile()
                .stream()
                // ops interm many
                .mapToInt(p -> p.getId())
                // op finala one)
                .max()
                .orElse(0) + 1;
    }

    public String getUserInput() {
        Scanner keyboardScanner = new Scanner(System.in);
        String userInput = keyboardScanner.next();
        return userInput;
    }

 /*   private void editProfile(Scanner scanner) {
        if (profile.getName() == null) {
            System.out.println("Profile not created yet. Please create a profile first.");
        } else {
            System.out.print("Enter your new name: ");
            profile.getName() = scanner.nextLine();

            System.out.print("Enter your new age: ");
            profile.getEmail() = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            System.out.print("Enter your new email: ");
            profile.getAge() = scanner.nextLine();

            System.out.println("Profile updated successfully!");
        }

  */

}

