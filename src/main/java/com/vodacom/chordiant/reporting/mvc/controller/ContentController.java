package com.vodacom.chordiant.reporting.mvc.controller;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ContentController provides the necessary actions for serving user agent based website content.
 *
 * @author Gaurav Soni
 * @version 1.0
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    private final String[] BOLD_FONT_NAMES = {"Open Sans Bold", "OpenSans-Bold"};
    private final String[] LIGHT_FONT_NAMES = {"Open Sans Light", "OpenSans-Light"};
    private final String[] REGULAR_FONT_NAMES = {"Open Sans", "OpenSans"};
    private final String[] SEMI_BOLD_FONT_NAMES = {"Open Sans Semibold", "OpenSans-Semibold"};

    @Value("${fonts.base.directory}")
    private String fontBase;

    /**
     * Provide font CSS based on user agent
     *
     * @return Font CSS
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/fonts.css", method = RequestMethod.GET)
    public ResponseEntity<Void> css(@RequestHeader("User-Agent") String userAgent, HttpServletResponse response)
            throws IOException {

        String fontFamily = "Open Sans";
        String normal = "normal";
        String woff = "woff";

        response.setContentType("text/css");
        response.setHeader("Cache-Control", "max-age=3600");

        UserAgentStringParser uaParser = UADetectorServiceFactory.getResourceModuleParser();
        ReadableUserAgent agent = uaParser.parse(userAgent);

        ServletOutputStream outputStream = response.getOutputStream();

        if (agent.getOperatingSystem().getFamilyName().toLowerCase().contains("windows")) {

            if (agent.getName().equals("IE")) {

                String eot = "embedded-opentype";

                if (agent.getVersionNumber().getMajor().startsWith("10") ||
                        agent.getVersionNumber().getMajor().startsWith("9")) {

                    List<AbstractMap.SimpleEntry<String, String>> lightFonts = new ArrayList<>();
                    lightFonts.add(new AbstractMap.SimpleEntry<>(eot, "OpenSans-Light.eot"));
                    lightFonts.add(new AbstractMap.SimpleEntry<>(woff, "OpenSans-Light-Win.woff"));

                    List<AbstractMap.SimpleEntry<String, String>> regularFonts = new ArrayList<>();
                    regularFonts.add(new AbstractMap.SimpleEntry<>(eot, "OpenSans-Regular.eot"));
                    regularFonts.add(new AbstractMap.SimpleEntry<>(woff, "OpenSans-Regular-Win.woff"));

                    List<AbstractMap.SimpleEntry<String, String>> semiBoldFonts = new ArrayList<>();
                    semiBoldFonts.add(new AbstractMap.SimpleEntry<>(eot, "OpenSans-Semibold.eot"));
                    semiBoldFonts.add(new AbstractMap.SimpleEntry<>(woff, "OpenSans-Semibold-Win.woff"));

                    List<AbstractMap.SimpleEntry<String, String>> boldFonts = new ArrayList<>();
                    boldFonts.add(new AbstractMap.SimpleEntry<>(eot, "OpenSans-Bold.eot"));
                    boldFonts.add(new AbstractMap.SimpleEntry<>(woff, "OpenSans-Bold-Win.woff"));

                    outputStream.println(this.generateFontFace(fontFamily, normal, 300, "OpenSans-Light.eot",
                            LIGHT_FONT_NAMES, lightFonts));
                    outputStream.println(this.generateFontFace(fontFamily, normal, 400, "OpenSans-Regular.eot",
                            REGULAR_FONT_NAMES, regularFonts));
                    outputStream.println(this.generateFontFace(fontFamily, normal, 600, "OpenSans-Semibold.eot",
                            SEMI_BOLD_FONT_NAMES, semiBoldFonts));
                    outputStream.println(this.generateFontFace(fontFamily, normal, 700, "OpenSans-Bold.eot",
                            BOLD_FONT_NAMES, boldFonts));

                } else {

                    List<AbstractMap.SimpleEntry<String, String>> fonts = new ArrayList<>();
                    fonts.add(new AbstractMap.SimpleEntry<>(eot, "OpenSans-Regular.eot"));
                    fonts.add(new AbstractMap.SimpleEntry<>(woff, "OpenSans-Regular-Win.woff"));

                    outputStream.println(this.generateFontFace(fontFamily, normal, 400, "OpenSans-Regular.eot",
                            REGULAR_FONT_NAMES, fonts));
                }

            } else {

                List<AbstractMap.SimpleEntry<String, String>> lightFonts = new ArrayList<>();
                lightFonts.add(new AbstractMap.SimpleEntry<>(woff, "OpenSans-Light-Win.woff"));

                List<AbstractMap.SimpleEntry<String, String>> regularFonts = new ArrayList<>();
                regularFonts.add(new AbstractMap.SimpleEntry<>(woff, "OpenSans-Regular-Win.woff"));

                List<AbstractMap.SimpleEntry<String, String>> semiBoldFonts = new ArrayList<>();
                semiBoldFonts.add(new AbstractMap.SimpleEntry<>(woff, "OpenSans-Semibold-Win.woff"));

                List<AbstractMap.SimpleEntry<String, String>> boldFonts = new ArrayList<>();
                boldFonts.add(new AbstractMap.SimpleEntry<>(woff, "OpenSans-Bold-Win.woff"));

                outputStream.println(this.generateFontFace(fontFamily, normal, 300, "OpenSans-Light-Win.woff",
                        LIGHT_FONT_NAMES, lightFonts));
                outputStream.println(this.generateFontFace(fontFamily, normal, 400, "OpenSans-Regular-Win.woff",
                        REGULAR_FONT_NAMES, regularFonts));
                outputStream.println(this.generateFontFace(fontFamily, normal, 600, "OpenSans-Semibold-Win.woff",
                        SEMI_BOLD_FONT_NAMES, semiBoldFonts));
                outputStream.println(this.generateFontFace(fontFamily, normal, 700, "OpenSans-Bold-Win.woff",
                        BOLD_FONT_NAMES, boldFonts));
            }

        } else {

            List<AbstractMap.SimpleEntry<String, String>> lightFonts = new ArrayList<>();
            lightFonts.add(new AbstractMap.SimpleEntry<>(woff, "OpenSans-Light.woff"));

            List<AbstractMap.SimpleEntry<String, String>> regularFonts = new ArrayList<>();
            regularFonts.add(new AbstractMap.SimpleEntry<>(woff, "OpenSans-Regular.woff"));

            List<AbstractMap.SimpleEntry<String, String>> semiBoldFonts = new ArrayList<>();
            semiBoldFonts.add(new AbstractMap.SimpleEntry<>(woff, "OpenSans-Semibold.woff"));

            List<AbstractMap.SimpleEntry<String, String>> boldFonts = new ArrayList<>();
            boldFonts.add(new AbstractMap.SimpleEntry<>(woff, "OpenSans-Bold.woff"));

            outputStream.println(this.generateFontFace(fontFamily, normal, 300, "OpenSans-Light.woff",
                    LIGHT_FONT_NAMES, lightFonts));
            outputStream.println(this.generateFontFace(fontFamily, normal, 400, "OpenSans-Regular.woff",
                    REGULAR_FONT_NAMES, regularFonts));
            outputStream.println(this.generateFontFace(fontFamily, normal, 600, "OpenSans-Semibold.woff",
                    SEMI_BOLD_FONT_NAMES, semiBoldFonts));
            outputStream.println(this.generateFontFace(fontFamily, normal, 700, "OpenSans-Bold.woff",
                    BOLD_FONT_NAMES, boldFonts));
        }

        response.flushBuffer();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Generate a font face CSS string
     *
     * @param family Name of the font family
     * @param style Style of the font
     * @param weight Weight of the font
     * @param mainFont The main font file of the font face CSS
     * @param fontNames List of names that can be used to refer to the font in CSS
     * @param fonts List of font formats and associated font files
     * @return Generated font face CSS string
     */
    private String generateFontFace(String family, String style, int weight, String mainFont, String[] fontNames,
                                    List<AbstractMap.SimpleEntry<String, String>> fonts) {

        StringBuilder sb = new StringBuilder();

        sb.append("@font-face {");
        sb.append(String.format("font-family: '%s';", family));
        sb.append(String.format("font-style: %s;", style));
        sb.append(String.format("font-weight: %d;", weight));
        sb.append(String.format("src: url(%s/%s);", this.fontBase, mainFont));
        sb.append("src: ");

        for (String fontName : fontNames) {
            sb.append(String.format("local('%s'), ", fontName));
        }

        for (int i = 0; i < fonts.size(); i++) {

            AbstractMap.SimpleEntry<String, String> font = fonts.get(i);
            sb.append(String.format("url(%s/%s) format('%s')", this.fontBase, font.getValue(), font.getKey()));

            if (i == fonts.size() - 1) {

                sb.append(";");

            } else {

                sb.append(", ");
            }
        }

        sb.append("}");

        return sb.toString();
    }
}
