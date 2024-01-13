package facebook.app.ui;

import facebook.app.controller.ProfileController;
import facebook.app.entities.Profile;
import facebook.app.exceptions.UserIOException;
import facebook.app.services.ProfileService;
import facebook.app.services.UserService;

import java.util.Scanner;

public class ProfileUI {
    private ProfileController profileController = new ProfileController();
    private Profile profile = new Profile();
    private UserService userService = new UserService();
    private ProfileService profileService = new ProfileService();
    int userId;
    {   try {
            userId = (int) userService.getCurrentUserId();
        } catch (UserIOException e) {
            throw new RuntimeException(e);
        }
    }
  
    public void startProfile() {
        int choice;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("        Welcome to the Profile page");
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
            scanner.close();
        } while (choice != 0);
    }

    public void createProfile() {
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

        int id = profileService.generateUniqueProfileId();
        Profile userProfile = new Profile(id, name, email, age, location);

        System.out.println("\nProfile Created Successfully!");
        System.out.println("Profile Details:");
        System.out.println("ProfileID: " + userProfile.getId() +
                           "\nName: " + name +
                           "\nEmail: " + email +
                           "\nAge: " + age +
                           "\nLocation: " + location);

        scanner.close();
        profileController.editProfile(id, name, email, age, location);

        startProfile();
    }
  
    public void editProfile() {
        System.out.println("Edit your Profile!");
        for (Profile p : profileController.getAllProfile()) {
            if (p.getId() == userId) {
                profile = p;
            }
        }
        Scanner scanner = new Scanner(System.in);
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

        scanner.close();
        profileController.editProfile(profile.getId(),  profile.getName(), profile.getEmail(), profile.getAge() ,  profile.getLocation());

        startProfile();
    }

    public void displayProfile() {
        for (Profile p : profileController.getAllProfile()) {
            if (p.getId() == userId) {
                profile = p;
            }
        }
        System.out.println("\n===== Your Profile =====");
        System.out.println("ID: " + profile.getId());
        System.out.println("Name: " + profile.getName());
        System.out.println("Email: " + profile.getEmail());
        System.out.println("Age: " + profile.getAge());
        System.out.println("Location: " + profile.getLocation());

        startProfile();
    }
}