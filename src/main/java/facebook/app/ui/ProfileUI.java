package facebook.app.ui;

import facebook.app.entities.Profile;
import facebook.app.exceptions.InvalidEmailFormatException;
import facebook.app.exceptions.UserIOException;
import facebook.app.services.ProfileService;
import facebook.app.services.UserService;

import java.util.Scanner;

public class ProfileUI {
    private final ProfileService profileService = new ProfileService();
    private Profile profile = new Profile();
    private final UserService userService = new UserService();
    private final Scanner scanner;
    int choice;
    int userId;
    {   try {
            userId = (int) userService.getCurrentUserId();
        } catch (UserIOException e) {
            throw new RuntimeException(e);
        }
    }

    public ProfileUI(Scanner scanner) {
        this.scanner = scanner;
    }

    public void startProfile() throws InvalidEmailFormatException {
        do {
            System.out.println("\n         --- Welcome to Profile --- ");
            System.out.println("Please choose one of the following options: ");
            System.out.println("      1. Create Profile           3. Display Profile ");
            System.out.println("      2. Edit Profile             0. Back");

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

        int id = profileService.generateUniqueProfileId();
        Profile userProfile = new Profile(id, name, email, age, location);

        System.out.println("\nProfile Created Successfully!");
        System.out.println("Profile Details:");
        System.out.println("ProfileID: " + userProfile.getId() +
                "\nName: " + name +
                "\nEmail: " + email +
                "\nAge: " + age +
                "\nLocation: " + location);
        profileService.createProfile(id, name, email, age, location);
        profileService.getCurrentProfileId(id);
        startProfile();
    }

    public void editProfile() throws InvalidEmailFormatException {
        System.out.println("\n         --- Edit Your Profile --- ");
        profile = profileService.displayCurrentProfile(userId, profile);

        System.out.println("Your profile ID is: " + profile.getId());
        System.out.println("Your profile Email is: " + profile.getEmail());
        System.out.print("Enter your name: ");
        profile.setName(scanner.nextLine());
        System.out.print("Enter your age: ");
        profile.setAge(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter your location: ");
        profile.setLocation(scanner.nextLine());

        System.out.println("\nProfile Edited Successfully!");
        System.out.println("Profile Details:");
        System.out.println("ProfileID: " + profile.getId() +
                "\nName: " + profile.getName() +
                "\nEmail: " + profile.getEmail() +
                "\nAge: " + profile.getAge() +
                "\nLocation: " + profile.getLocation());
        profileService.editProfile(profile.getId(), profile.getName(), profile.getEmail(), profile.getAge(), profile.getLocation());

        startProfile();
    }

    public void displayProfile() throws InvalidEmailFormatException {
        System.out.println("Do you want to see your login profile or the created profile");
        System.out.println("      1. Current Profile           2. New Profile ");
        System.out.println("      0. Back ");
        do {
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    currentProfile();
                    break;
                case 2:
                    newProfile();
                    break;
                case 0:
                    System.out.println("Returning to Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
        startProfile();
    }

    private void newProfile() throws InvalidEmailFormatException {
        profile = profileService.getLastProfileId();
        System.out.println("\n         --- New Profile --- ");
        System.out.println("ID: " + profile.getId());
        System.out.println("Name: " + profile.getName());
        System.out.println("Email: " + profile.getEmail());
        System.out.println("Age: " + profile.getAge());
        System.out.println("Location: " + profile.getLocation());
        startProfile();
    }

    private void currentProfile() throws InvalidEmailFormatException {
        //profile = profileService.displayCurrentProfile(userId, profile);
        profile = profileService.getCurrentProfileId(userId);
        System.out.println("\n         --- Current Profile --- ");
        System.out.println("ID: " + profile.getId());
        System.out.println("Name: " + profile.getName());
        System.out.println("Email: " + profile.getEmail());
        System.out.println("Age: " + profile.getAge());
        System.out.println("Location: " + profile.getLocation());
        startProfile();
    }
}
