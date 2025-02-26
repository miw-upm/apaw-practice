package es.upm.miw.apawpractice.adapters.rest.system;

public class AppInfoDto {

    private String artifact;
    private String version;
    private String build;
    private String profile;

    public AppInfoDto() {
        // Empty for the framework
    }

    public AppInfoDto(String artifact, String version, String build, String profile) {
        this.artifact = artifact;
        this.version = version;
        this.build = build;
        this.profile = profile;
    }

    public String getArtifact() {
        return artifact;
    }

    public String getVersion() {
        return this.version;
    }

    public String getBuild() {
        return this.build;
    }

    public String getProfile() {
        return this.profile;
    }

    @Override
    public String toString() {
        return "AppInfoDto{" +
                "artifact='" + artifact + '\'' +
                ", version='" + version + '\'' +
                ", build='" + build + '\'' +
                ", profile='" + profile + '\'' +
                '}';
    }
}
