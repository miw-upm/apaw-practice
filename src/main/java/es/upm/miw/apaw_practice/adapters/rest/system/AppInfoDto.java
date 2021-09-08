package es.upm.miw.apaw_practice.adapters.rest.system;

public class AppInfoDto {

    private String application;
    private String version;
    private String build;
    private String profile;

    public AppInfoDto() {
        // Empty for the framework
    }

    public AppInfoDto(String application, String version, String build, String profile) {
        this.application = application;
        this.version = version;
        this.build = build;
        this.profile = profile;
    }

    public String getApplication() {
        return application;
    }

    public String getVersion() {
        return version;
    }

    public String getBuild() {
        return build;
    }

    public String getProfile() {
        return profile;
    }

    @Override
    public String toString() {
        return "AppInfoDto{" +
                "application='" + application + '\'' +
                ", version='" + version + '\'' +
                ", build='" + build + '\'' +
                ", profile='" + profile + '\'' +
                '}';
    }
}
