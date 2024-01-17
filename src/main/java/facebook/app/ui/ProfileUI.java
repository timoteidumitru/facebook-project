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
    private final UserService userService = new UserService();
    private final Scanner scanner = new Scanner(System.in);
    public ProfileUI() {
    }

    public void startProfile() throws InvalidEmailFormatException, UserIOException {
        int choice;
        while (true) {
                System.out.println("\n         --- Welcome to Profile --- ");
                System.out.println("Please choose one of the following options: ");
                System.out.println("     1. Create Profile          2. Edit Profile");
                System.out.println("     3. Display Profile         0. Back to Main Menu");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline

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
                        return; // Exit the while loop and the method
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume the invalid input
            }
        }
    }


    public void createProfile() throws InvalidEmailFormatException, UserIOException {
        int id = userService.getCurrentUserId();
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
        int choice;
        Profile profile = profileService.displayCurrentProfileDetails();
        if (profile.getName() == null) {
            System.out.println("You don't have a profile yet, please register one.");
        } else {
           do {
                System.out.println("\n       --- Edit Your Profile --- ");
                System.out.println("Please choose the detail you want to edit:");
                System.out.println("   1. Edit Name      2. Edit Email");
                System.out.println("   3. Edit Age       4. Edit Location");
                System.out.println("         0. Back to Main Menu");

                // Make sure the scanner is ready for integer input
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // consume the invalid input
                }
                choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline

                if (choice == 0) {
                    return; // Exit the method if the user chooses to cancel
                }

                Profile currentDetails = profileService.displayCurrentProfileDetails();
                int id = currentDetails.getId();
                String name = currentDetails.getName();
                String email = currentDetails.getEmail();
                int age = currentDetails.getAge();
                String location = currentDetails.getLocation();

                switch (choice) {
                    case 1:
                        System.out.print("Edit your name: ");
                        name = scanner.nextLine();
                        break;
                    case 2:
                        System.out.print("Edit your email address: ");
                        email = scanner.nextLine();
                        break;
                    case 3:
                        System.out.print("Edit your age: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Please enter a valid number for age.");
                            scanner.next(); // consume invalid input
                        }
                        age = scanner.nextInt();
                        scanner.nextLine(); // consume the newline
                        break;
                    case 4:
                        System.out.print("Edit your location: ");
                        location = scanner.nextLine();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        continue;
                }

                try {
                    profileController.editProfile(id, name, email, age, location);
                    System.out.println("Profile updated successfully.");
                } catch (Exception e) {
                    System.out.println("An error occurred: " + e.getMessage());
                    e.printStackTrace();
                }

                System.out.println("\nDo you want to edit another detail? (Yes/Y or No/N)");
                String continueEditing = scanner.nextLine().trim().toLowerCase();
                if (!continueEditing.equals("yes") && !continueEditing.equals("y")) {
                    break; // Break the loop if the user does not want to continue editing
                }
            } while (true);
        }
    }

    public void displayProfile() throws UserIOException{
        System.out.println("\n--- Your Profile Details ---");
        currentProfile();
    }

    private void currentProfile() throws UserIOException {
        Profile currentProfile = profileService.displayCurrentProfileDetails();
        if (currentProfile != null) {
            displayProfileDetails(currentProfile);
        } else {
            System.out.println("Current profile not found.");
        }
    }

    private void displayProfileDetails(Profile profile) {
        if (profile.getName() == null) {
            System.out.println("You don't have a profile yet, please register one.");
        } else {
            System.out.println("ID: " + profile.getId());
            System.out.println("Name: " + profile.getName());
            System.out.println("Email: " + profile.getEmail());
            System.out.println("Age: " + profile.getAge());
            System.out.println("Location: " + profile.getLocation());
        }
    }
}
