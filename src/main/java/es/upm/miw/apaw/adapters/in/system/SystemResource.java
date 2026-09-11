package es.upm.miw.apaw.adapters.in.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(SystemResource.SYSTEM)
public class SystemResource {
    public static final String SYSTEM = "/system";
    public static final String VERSION_BADGE = "/version-badge";

    @Value("${info.app.artifact}")
    private String artifact;
    @Value("${info.app.version}")
    private String version;
    @Value("${info.app.build}")
    private String build;
    @Value("${app.hosting}")
    private String hosting;

    @GetMapping
    public ApplicationInfoDto applicationInfo() {
        return new ApplicationInfoDto("%s::%s::%s".formatted(artifact, version, build), LocalDateTime.now());
    }

    @GetMapping(value = VERSION_BADGE, produces = {"image/svg+xml"})
    public byte[] generateBadge() {
        return VersionBadgeGenerator.generate(hosting + ": " + artifact, version);
    }

}
