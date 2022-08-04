package com.brandwatch.interviews.topic;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import com.brandwatch.interviews.topic.ascii.AsciiTitleGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication(
        scanBasePackages = "com.brandwatch.interviews.topic"
)
public class Main implements CommandLineRunner {

    @Autowired
    private AsciiTitleGenerator titleGenerator;

    @Autowired
    private Demo demo;

    @Parameter(names = "-input", converter = FileConverter.class)
    private File file;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        file = new File("C:\\Users\\ktoma\\Desktop\\test.txt");
        System.out.println("Topic Extraction Demo");
        JCommander jcommander = JCommander.newBuilder().addObject(this).build();
        jcommander.parse(args);
        System.out.println(titleGenerator.buildTitle());
        demo.runDemo(file);
    }
}
