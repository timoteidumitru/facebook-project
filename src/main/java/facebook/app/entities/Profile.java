package facebook.app.entities;

public class Profile {
    private String name;
    private int age;
    private String location;
    private String email;
    public Profile(String name, int age, String location, String email) {
        this.name = name;
        this.age = age;
        this.location = location;
        this.email = email;
    }
        @Override
    public String toString() {
        return "Name: " + name + "\nAge: " + age + "\nLocation: " + location + "\nEmail: " + email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }
}
