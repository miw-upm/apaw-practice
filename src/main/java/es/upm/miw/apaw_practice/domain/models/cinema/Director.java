package es.upm.miw.apaw_practice.domain.models.cinema;

public class Director {
    private String id;
    private String name;
    private String dni;
    private String birthdate; // formato ISO
    private String style;

    public Director() {}

    public Director(String id, String name, String dni, String birthdate, String style) {
        this.id = id;
        this.name = name;
        this.dni = dni;
        this.birthdate = birthdate;
        this.style = style;
    }

    // Getters y setters...
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getBirthdate() { return birthdate; }
    public void setBirthdate(String birthdate) { this.birthdate = birthdate; }
    public String getStyle() { return style; }
    public void setStyle(String style) { this.style = style; }

    public static class Builder {
        private String id;
        private String name;
        private String dni;
        private String birthdate;
        private String style;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder dni(String dni) {
            this.dni = dni;
            return this;
        }

        public Builder birthdate(String birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public Builder style(String style) {
            this.style = style;
            return this;
        }

        public Director build() {
            return new Director(id, name, dni, birthdate, style);
        }
    }
}