package es.upm.miw.apaw.adapters.rest.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(SystemResource.SYSTEM)
public class SystemResource {
    public static final String SYSTEM = "/";
    public static final String VERSION_BADGE = "/version-badge";
    public static final String APP_INFO = "/app-info";

    private static final int TEXT_MARGIN = 12;
    private static final int CHARACTER_WIDTH = 6;

    private static final String BADGE_IMAGE_TEMPLATE = """
            <svg xmlns="http://www.w3.org/2000/svg" width="%d" height="20">
                <linearGradient id="a" x2="0" y2="100%%">
                    <stop offset="0" stop-color="#bbb" stop-opacity=".1"/>
                    <stop offset="1" stop-opacity=".1"/>
                </linearGradient>
                <rect rx="3" width="%d" height="20" fill="#555"/>
                <rect rx="3" x="%d" width="%d" height="20" fill="#4c1"/>
                <path fill="#4c1" d="M%d 0h4v20h-4z"/>
                <rect rx="3" width="%d" height="20" fill="url(#a)"/>
                <g fill="#fff" text-anchor="middle" font-family="DejaVu Sans,Verdana,Geneva,sans-serif" font-size="11">
                    <text x="%d" y="15" fill="#010101" fill-opacity=".3">%s</text>
                    <text x="%d" y="14">%s</text>
                    <text x="%d" y="15" fill="#010101" fill-opacity=".3">%s</text>
                    <text x="%d" y="14">%s</text>
                </g>
            </svg>
            """;

    @Value("${info.app.artifact}")
    private String artifact;
    @Value("${info.app.version}")
    private String version;
    @Value("${info.app.build}")
    private String build;
    @Value("${spring.profiles.active}")
    private String profile;

    public String generateBadge(String label, String value) {
        int widthLabel = TEXT_MARGIN + CHARACTER_WIDTH * label.length();
        int widthValue = TEXT_MARGIN + CHARACTER_WIDTH * value.length();
        int textWidth = widthLabel + widthValue;
        int middleLabel = widthLabel / 2;
        int middleValue = widthLabel + widthValue / 2;
        return String.format(BADGE_IMAGE_TEMPLATE, textWidth, textWidth, widthLabel, widthValue, widthLabel, textWidth,
                middleLabel, label, middleLabel, label, middleValue, value, middleValue, value);
    }

    @GetMapping(value = APP_INFO)
    public String applicationInfo() {
        return """
                {"version":"%s::%s::%s"} (%s)
                """.formatted(this.artifact, this.version, this.build, LocalDateTime.now());
    }

    @GetMapping(value = VERSION_BADGE, produces = {"image/svg+xml"})
    public byte[] generateBadge() {
        return this.generateBadge("Render", "v" + version).getBytes();
    }


}
