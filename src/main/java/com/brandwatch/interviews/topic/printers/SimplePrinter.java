package com.brandwatch.interviews.topic.printers;

import com.brandwatch.interviews.topic.extractors.Topic;
import com.brandwatch.interviews.topic.extractors.TopicResults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimplePrinter implements TopicResultsPrinter {

    public void print(TopicResults results) {
        List<Topic> resultBreakdown = results.getTopics();
        resultBreakdown.stream()
                .limit(10)
                .forEach(topic -> System.out.println(topic.getLabel()));
    }
}
