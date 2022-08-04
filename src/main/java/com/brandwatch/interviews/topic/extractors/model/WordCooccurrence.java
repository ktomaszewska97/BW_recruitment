package com.brandwatch.interviews.topic.extractors.model;

import com.brandwatch.interviews.topic.extractors.Topic;
import com.brandwatch.interviews.topic.extractors.TopicResults;
import com.brandwatch.interviews.topic.model.Keyphrase;
import com.brandwatch.interviews.topic.model.StemmedToken;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.*;
import java.util.stream.Collectors;

public class WordCooccurrence {

    private Table<String, String, Integer> cooccurrences = HashBasedTable.create();

    private Map<String, String> stemmedToken2WordMap = new HashMap<>();

    public WordCooccurrence(List<Keyphrase> keyphrases) {
        addCooccurrences(keyphrases);
    }

    private void addCooccurrences(List<Keyphrase> keyphrases) {
        for (Keyphrase keyphrase : keyphrases) {
            List<StemmedToken> tokens = keyphrase.getKeyphrases();
            for (StemmedToken t1 : tokens) {
                for (StemmedToken t2 : tokens) {
                    String token1 = t1.getStemmedToken();
                    String token2 = t2.getStemmedToken();

                    if (cooccurrences.contains(token1, token2)) {
                        int score = cooccurrences.get(token1, token2);
                        cooccurrences.put(token1, token2, ++score);
                        stemmedToken2WordMap.put(token1, t1.getOriginalToken().getOriginalToken());
                        stemmedToken2WordMap.put(token2, t2.getOriginalToken().getOriginalToken());
                    } else {
                        cooccurrences.put(token1, token2, 1);
                        stemmedToken2WordMap.put(token1, t1.getOriginalToken().getOriginalToken());
                        stemmedToken2WordMap.put(token2, t2.getOriginalToken().getOriginalToken());
                    }
                }
            }
        }
    }

    public TopicResults getTopics() {
        Set<String> keywords = cooccurrences.rowKeySet();
        Map<String, Double> scores = new HashMap<>(keywords.size());
        for (String keyword : keywords) {
            Map<String, Integer> row = cooccurrences.row(keyword);
            int sumOfCooccurrence = row.get(keyword);
            int wordFrequency = row.values().stream().mapToInt(i -> i).sum();
            scores.put(keyword, (double) wordFrequency / (double) sumOfCooccurrence);
        }

        List<String> topics = scores.entrySet().stream()
                .sorted(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .map(key -> stemmedToken2WordMap.getOrDefault(key, key))
                .collect(Collectors.toList());
        Collections.reverse(topics);

        TopicResults topicResults = new TopicResults();
        for (String topic : topics) {
            topicResults.addTopic(new Topic(topic));
        }
        return topicResults;
    }
}
