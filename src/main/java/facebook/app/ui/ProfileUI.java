package facebook.app.ui;

import facebook.app.controller.ProfileController;
import facebook.app.entities.Profile;
import facebook.app.exceptions.InvalidEmailFormatException;
import facebook.app.exceptions.UserIOException;
import facebook.app.services.ProfileService;
import facebook.app.services.UserService;

import java.util.Scanner;

public class ProfileUI {
    private final ProfileController profileController = new ProfileController();
    private final ProfileService profileService = new ProfileService();
    private Profile profile = new Profile();
    private final UserService userService = new UserService();
    private final Scanner scanner = new Scanner(System.in);
    private final int id = userService.getCurrentUserId();
    public ProfileUI() throws UserIOException {
    }

    public void startProfile() throws InvalidEmailFormatException, UserIOException {
        int choice;
        do {
            System.out.println("\n         --- Welcome to Profile --- ");
            System.out.println("Please choose one of the following options: ");
            System.out.println("     1. Create Profile          2. Edit Profile");
            System.out.println("     3. Display Profile         0. Back");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createProfile();
                    break;
                case 2:
                    editProfile();
                    break;
                case 3:
                    displayProfile();
                    break;
                case 0:
                    System.out.println("Returning to Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public void createProfile() throws InvalidEmailFormatException {
        System.out.println("\n         --- Create New Profile --- ");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter your location: ");
        String location = scanner.nextLine();

        Profile userProfile = new Profile(id, name, email, age, location);
        System.out.println("\nProfile Created Successfully!");
        System.out.println(" --- Profile Details ---");
        System.out.println("ProfileID: " + userProfile.getId() +
                "\nName: " + name +
                "\nEmail: " + email +
                "\nAge: " + age +
                "\nLocation: " + location);
        profileController.createProfile(id, name, email, age, location);
    }

    public void editProfile() throws UserIOException {
        System.out.println("\n         --- Edit Your Profile --- ");
        this.profile = profileService.displayCurrentProfile(userService.getCurrentUserId(), this.profile);

        System.out.println("Your profile ID is: " + this.profile.getId());
        System.out.println("Your profile Email is: " + this.profile.getEmail());
        System.out.print("Enter your name: ");
        this.profile.setName(scanner.nextLine());
        System.out.print("Enter your age: ");
        this.profile.setAge(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter your location: ");
        this.profile.setLocation(scanner.nextLine());

        System.out.println("\nProfile Edited Successfully!");
        System.out.println("Profile Details:");
        System.out.println("ProfileID: " + this.profile.getId() +
                "\nName: " + this.profile.getName() +
                "\nEmail: " + this.profile.getEmail() +
                "\nAge: " + this.profile.getAge() +
                "\nLocation: " + this.profile.getLocation());
        profileController.editProfile(this.profile.getId(), this.profile.getName(), this.profile.getEmail(), this.profile.getAge(), this.profile.getLocation());
    }

    public void displayProfile() throws UserIOException, InvalidEmailFormatException {
        System.out.println("\n--- Display Profile ---");
        System.out.println("1. Current Profile\n2. New Profile\n0. Back");
        int choice;
        do {
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline

            switch (choice) {
                case 1:
                    currentProfile();
                    break;
                case 2:
                    newProfile();
                    break;
                case 0:
                    System.out.println("Returning to Main Menu.");
                    return; // Use return to exit the method
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (true);
    }

    private void newProfile() throws UserIOException, InvalidEmailFormatException {
        Profile lastProfile = profileService.getLastProfileId();
        if (lastProfile != null) {
            displayProfileDetails(lastProfile);
        } else {
            System.out.println("No new profiles available.");
        }
    }

    private void currentProfile() throws UserIOException, InvalidEmailFormatException {
        Profile currentProfile = profileService.displayCurrentProfile(userService.getCurrentUserId(), this.profile);
        if (currentProfile != null) {
            displayProfileDetails(currentProfile);
        } else {
            System.out.println("Current profile not found.");
        }
    }

    private void displayProfileDetails(Profile profile) throws UserIOException, InvalidEmailFormatException {
        System.out.println("\n--- Profile Details ---");
        System.out.println("ID: " + profile.getId());
        System.out.println("Name: " + profile.getName());
        System.out.println("Email: " + profile.getEmail());
        System.out.println("Age: " + profile.getAge());
        System.out.println("Location: " + profile.getLocation());
        startProfile();
    }
}
