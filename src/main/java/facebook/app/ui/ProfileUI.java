package facebook.app.ui;

import facebook.app.controller.ProfileController;
import facebook.app.entities.Profile;

import java.io.IOException;
import java.util.Scanner;

public class ProfileUI {
    private static final ProfileController profileController = new ProfileController();

    public void createProfile() {

        int id = generateUniqueProfileId();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Profile Creator!");
        // Get user input for profile details
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left by nextInt()
        System.out.print("Enter your location: ");
        String location = scanner.nextLine();
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();
        // Create a Profile object with the collected information
        Profile userProfile = new Profile(name, age, location, email);
        // Display the created profile
        System.out.println("\nProfile Created Successfully!");
        System.out.println("Profile Details:");
        System.out.println(userProfile);
        // Close the scanner
        scanner.close();
        profileController.editProfile(id, name, email, age, location);
    }

    public void displayProfile() {
        //  Profile userProfile = new Profile();
        // Display the created profile
        System.out.println("\nProfile Displayed UNSuccessfully!");
        System.out.println("Profile Details:");
        //   System.out.println(userProfile);
    }

    private int generateUniqueProfileId() {
        // For simplicity, here's a basic implementation using the current time in milliseconds.
        return profileController.getAllProfile().size() + 1;
    }

    public String getUserInput() throws IOException {
        Scanner keyboardScanner = new Scanner(System.in);
        String userInput = keyboardScanner.next();
        return userInput;
    }

    public void startProfile() {
        System.out.println("        Welcome to the Profile page");
        System.out.println("Please choose one of the following options: ");
        System.out.println("      1. Create Profile           2. Edit Profile ");
        System.out.println("      3. Display Profile");
        System.out.println("                   0. Back");
    }
}
