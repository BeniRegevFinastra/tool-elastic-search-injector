package com.nice.mcr.injector;

import com.nice.mcr.injector.config.InjectorConfig;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;


@SpringBootApplication(scanBasePackages = {"com.nice.mcr"})
@Import({InjectorConfig.class})
public class Main {
    public static void main(String[] args) throws IOException, XmlPullParserException {

        SpringApplication application = new SpringApplication(Main.class);
        WebApplicationType webAppType = WebApplicationType.NONE;
        if (ObjectUtils.isEmpty(args)) {
            webAppType = WebApplicationType.SERVLET;
        }
        application.setWebApplicationType(webAppType);
        if (!Collections.disjoint(Arrays.asList(args), Arrays.asList("-h", "--help", "-v", "--version", "-d", "--dependencies" ))) {
            if (!Collections.disjoint(Arrays.asList(args), Arrays.asList( "-h", "--help" ))) {
                System.out.println("search-injector show CLI tool help");
            }

            if (!Collections.disjoint(Arrays.asList(args), Arrays.asList( "-v", "--version", "-d", "--dependencies" ))) {
                MavenXpp3Reader reader = new MavenXpp3Reader();
                File filePomXml = new File("pom.xml");

                if (filePomXml.exists()) {
                    System.out.println("search-injector show CLI tool's version");
                    Model model = reader.read(new FileReader("pom.xml"));
                    if (!Collections.disjoint(Arrays.asList(args), Arrays.asList( "-v", "--version", "-d", "--dependencies" ))) {
                        System.out.println("CLI Tool Id: " + model.getId());
                        System.out.println("CLI Tool GroupId: " + model.getGroupId());
                        System.out.println("CLI Tool ArtifactId: " + model.getArtifactId());
                        System.out.println("CLI Tool Version: " + model.getVersion());
                    }
                    if (!Collections.disjoint(Arrays.asList(args), Arrays.asList( "-v", "--version", "-d", "--dependencies" ))) {
                        System.out.println("search-injector show CLI tool's dependencies");
                        for (int i = 0; i < model.getDependencies().size(); i++) {
                            System.out.println(model.getDependencies().get(i));
                        }
                    }
                } else {
                    throw new FileNotFoundException("Maven POM.XML file not found in project root directory.");
                }
            }
            System.exit(0);
        }
        application.run(args);

    }
}

