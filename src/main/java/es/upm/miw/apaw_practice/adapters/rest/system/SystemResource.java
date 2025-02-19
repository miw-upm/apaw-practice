package es.upm.miw.apaw_practice.adapters.rest.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SystemResource.SYSTEM)
public class SystemResource {
    static final String SYSTEM = "/";

    static final String APP_INFO = "/app-info";
    static final String VERSION_BADGE = "/version-badge";

    @Value("${info.app.artifact}")
    private String artifact;
    @Value("${info.app.version}")
    private String version;
    @Value("${info.app.build}")
    private String build;
    @Value("${spring.profiles.active}")
    private String profile;

    @GetMapping(value = VERSION_BADGE, produces = {"image/svg+xml"})
    public byte[] generateBadge() { // http://localhost:8080/system/badge
        return new Badge().generateBadge("Render", "v" + version).getBytes();
    }

    @GetMapping(value = APP_INFO)
    public AppInfoDto applicationInfo() {
        return new AppInfoDto(this.artifact, this.version, this.build, this.profile);
    }
}
