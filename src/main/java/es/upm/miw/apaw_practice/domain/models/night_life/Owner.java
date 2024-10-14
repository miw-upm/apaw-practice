package es.upm.miw.apaw_practice.domain.models.night_life;

public class Owner {
    private String name;
    private String phone;
    private String email;

    public Owner() {
        //empty for framework
    }

    public Owner(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "OwnerClinic{" + "name=" + name + ", phone=" + phone + ", email=" + email + '}';
    }


}