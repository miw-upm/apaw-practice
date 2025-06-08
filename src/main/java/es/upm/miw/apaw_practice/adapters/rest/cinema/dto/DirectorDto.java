package es.upm.miw.apaw_practice.adapters.rest.cinema.dto;

public class DirectorDto {
    private String dni;
    private String birthdate; // ISO format, ej. "1980-12-15"
    private String style;

    public DirectorDto() {}

    public DirectorDto(String dni, String birthdate, String style) {
        this.dni = dni;
        this.birthdate = birthdate;
        this.style = style;
    }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getBirthdate() { return birthdate; }
    public void setBirthdate(String birthdate) { this.birthdate = birthdate; }

    public String getStyle() { return style; }
    public void setStyle(String style) { this.style = style; }
}