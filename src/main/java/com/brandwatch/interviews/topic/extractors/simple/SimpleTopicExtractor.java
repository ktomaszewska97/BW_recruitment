package com.brandwatch.interviews.topic.extractors.simple;

import com.brandwatch.interviews.topic.extractors.Topic;
import com.brandwatch.interviews.topic.extractors.TopicExtractor;
import com.brandwatch.interviews.topic.extractors.TopicResults;
import org.springframework.stereotype.Component;

@Component
public class SimpleTopicExtractor implements TopicExtractor {
    public TopicResults extract(String inputText) {
        TopicResults results = new TopicResults();
        String[] words = inputText.split(" ");
        for (String word : words) {
            Topic topic = new Topic();
            topic.setLabel(word);
            results.addTopic(topic);
        }
        return results;
    }
}
