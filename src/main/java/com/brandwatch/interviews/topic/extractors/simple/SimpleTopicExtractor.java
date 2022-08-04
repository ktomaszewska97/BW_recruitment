package com.brandwatch.interviews.topic.extractors.simple;

import com.brandwatch.interviews.topic.extractors.Topic;
import com.brandwatch.interviews.topic.extractors.TopicExtractor;
import com.brandwatch.interviews.topic.extractors.TopicResults;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SimpleTopicExtractor implements TopicExtractor {
    public TopicResults extract(String inputText) {
        TopicResults results = new TopicResults();
        String[] words = inputText.split(" ");
        LinkedHashMap sortedMap = wordCount(words);
        Set<String> keys = sortedMap.keySet();

        for (String word : keys) {
            Topic topic = new Topic();
            topic.setLabel(word);
            results.addTopic(topic);
        }

        return results;
    }

    public LinkedHashMap wordCount(String[] words) {
        Map<String,Integer> wordsWithCount = new HashMap<String, Integer>();
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        for (String word : words)
        {
            word = word.toLowerCase();
            if(wordsWithCount.containsKey(word))
            {
                wordsWithCount.put(word, wordsWithCount.get(word)+1);
            }
            else
            {
                wordsWithCount.put(word, 1);
            }
        }
        wordsWithCount.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        //System.out.println("Reverse Sorted Map   : " + sortedMap);
        return sortedMap;
    }

}
