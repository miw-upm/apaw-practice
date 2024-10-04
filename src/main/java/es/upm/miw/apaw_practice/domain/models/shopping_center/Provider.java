package es.upm.miw.apaw_practice.domain.models.shopping_center;

public class Provider {
    private String name;
    private String mainService;
    private boolean isNational;

    public Provider() {
        //empty for framework
    }

    public Provider(String name, String mainService, boolean isNational) {
        this.name = name;
        this.mainService = mainService;
        this.isNational = isNational;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainService() {
        return mainService;
    }

    public void setMainService(String mainService) {
        this.mainService = mainService;
    }

    public boolean isNational() {
        return isNational;
    }

    public void setNational(boolean national) {
        isNational = national;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "name='" + name + '\'' +
                ", mainService='" + mainService + '\'' +
                ", isNational=" + isNational +
                '}';
    }
}
