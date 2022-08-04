package com.brandwatch.interviews.topic.extractors;

import java.util.ArrayList;
import java.util.List;

public class TopicResults {
    private final List<Topic> topics = new ArrayList<>();

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    public List<Topic> getTopics() {
        return topics;
    }
}
