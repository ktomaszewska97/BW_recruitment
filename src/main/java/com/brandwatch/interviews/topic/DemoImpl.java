package com.brandwatch.interviews.topic;

import com.brandwatch.interviews.topic.extractors.TopicExtractor;
import com.brandwatch.interviews.topic.extractors.TopicResults;
import com.brandwatch.interviews.topic.printers.TopicResultsPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class DemoImpl implements Demo {

    @Autowired
    private TextProvider provider;
    @Autowired
    @Qualifier("simpleTopicExtractor")
    private TopicExtractor extractor;
    @Autowired
    private TopicResultsPrinter printer;

    public void runDemo(File file) {
        try {
            String inputText = provider.readText(file);
            TopicResults results = extractor.extract(inputText);
            printer.print(results);
        } catch (IOException e) {
            System.err.println("Failed");
        }
    }
}
