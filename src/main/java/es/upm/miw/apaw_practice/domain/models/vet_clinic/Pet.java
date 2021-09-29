package es.upm.miw.apaw_practice.domain.models.vet_clinic;

public class Pet {
    private Integer chip;
    private Integer age;
    private String nick;
    private String owner;

    public Pet(){
      //empty for framework
    }

    public Pet(Integer chip, Integer age, String nick, String owner) {
        this.chip = chip;
        this.age = age;
        this.nick = nick;
        this.owner = owner;
    }

    public Integer getChip() {
        return chip;
    }

    public void setChip(Integer chip) {
        this.chip = chip;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "chip=" + chip +
                ", age=" + age +
                ", nick='" + nick + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
