package es.upm.miw.apaw.adapters.in.system;

import java.nio.charset.StandardCharsets;

public final class VersionBadgeGenerator {

    private static final String BADGE_IMAGE_TEMPLATE = """
            <svg xmlns="http://www.w3.org/2000/svg" width="%d" height="20">
                <linearGradient id="a" x2="0" y2="100%%">
                    <stop offset="0" stop-color="#bbb" stop-opacity=".1"/>
                    <stop offset="1" stop-opacity=".1"/>
                </linearGradient>
                <rect rx="3" width="%d" height="20" fill="#6B5D4F"/>
                <rect rx="3" x="%d" width="%d" height="20" fill="#4A5D3A"/>
                <path fill="#4A5D3A" d="M%d 0h4v20h-4z"/>
                <rect rx="3" width="%d" height="20" fill="url(#a)"/>
                <g fill="#fff" text-anchor="middle" font-family="DejaVu Sans,Verdana,Geneva,sans-serif" font-size="11">
                    <text x="%d" y="15" fill="#010101" fill-opacity=".3">%s</text>
                    <text x="%d" y="14">%s</text>
                    <text x="%d" y="15" fill="#010101" fill-opacity=".3">%s</text>
                    <text x="%d" y="14">%s</text>
                </g>
            </svg>
            """;

    private static final int TEXT_MARGIN = 12;

    private VersionBadgeGenerator() {
    }

    public static byte[] generate(String label, String value) {
        int widthLabel = TEXT_MARGIN + measureText(label);
        int widthValue = TEXT_MARGIN + measureText(value);
        int textWidth = widthLabel + widthValue;
        int middleLabel = widthLabel / 2;
        int middleValue = widthLabel + widthValue / 2;
        return String.format(BADGE_IMAGE_TEMPLATE, textWidth, textWidth, widthLabel, widthValue,
                        widthLabel, textWidth,
                        middleLabel, label, middleLabel, label, middleValue, value, middleValue, value)
                .getBytes(StandardCharsets.UTF_8);
    }

    private static int measureText(String text) {
        int width = 0;
        for (char c : text.toCharArray()) {
            width += switch (c) {
                case 'i', 'l', 'I', 'j', '.', ',', ';', ':', '\'', '|' -> 3;
                case 'f', 'r', 't', '(', ')', '[', ']', '{', '}', ' ' -> 4;
                case 'm', 'M', 'w', 'W' -> 9;
                default -> 6;
            };
        }
        return width + TEXT_MARGIN;
    }
}
