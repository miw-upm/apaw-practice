package es.upm.miw.apaw_practice.domain.models.vet_clinic;

public class Vet {
    private Integer vetNumber;
    private String name;
    private String surname;

    public Vet(){
        //empty for framework
    }

    /*public Vet(Integer vetNumber, String name, String surname){
        this.vetNumber = vetNumber;
        this.name = name;
        this.surname = surname;
    }*/

    public static VetBuilder.VetNumber builder() {
        return new Builder();
    }

    public Integer getVetNumber() {
        return vetNumber;
    }

    public void setVetNumber(Integer vetNumber) {
        this.vetNumber = vetNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Vet{" +
                "vet number=" + vetNumber +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public static class Builder implements VetBuilder.VetNumber, VetBuilder.Name,
            VetBuilder.Surname, VetBuilder.Optionals {

        private final Vet vet;

        public Builder() {
            this.vet = new Vet();
        }

        @Override
        public VetBuilder.Name vetNumber(Integer vetnumber) {
            this.vet.vetNumber = vetnumber;
            return this;
        }

        @Override
        public VetBuilder.Surname name(String name) {
            this.vet.name = name;
            return this;
        }

        @Override
        public VetBuilder.Optionals surname(String surname) {
            this.vet.surname = surname;
            return this;
        }

        @Override
        public Vet build() {
            return this.vet;
        }
    }
}
