package com.brandwatch.interviews.topic.preprocessors;

import com.brandwatch.interviews.topic.model.Keyphrase;
import com.brandwatch.interviews.topic.model.StemmedToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class KeyphraseExtractor {

    private static final Set<String> stopPOS = Set.of("DT", "EX", "IN", "MD", "PRP", "RB", "VBP", "VBZ", "WDT", "WRB", ".", ",", "-LRB-", "-RRB-");

    public List<Keyphrase> extract(List<StemmedToken> stemmedTokens) {

        List<Keyphrase> allKeyphrases = new ArrayList<>();
        Keyphrase nextKeyphrase = new Keyphrase();
        boolean insideKeyphrase = false;
        for (StemmedToken token : stemmedTokens) {
            if (!stopPOS.contains(token.getOriginalToken().getPosTag())) {
                if (insideKeyphrase) {
                    nextKeyphrase.addKeyphrase(token);
                } else {
                    insideKeyphrase = true;
                    nextKeyphrase.addKeyphrase(token);

                }
            } else {
                if (insideKeyphrase) {
                    insideKeyphrase = false;
                    if (!nextKeyphrase.isEmpty()) {
                        allKeyphrases.add(nextKeyphrase);
                        nextKeyphrase = new Keyphrase();
                    }
                }
            }
        }

        if (!nextKeyphrase.isEmpty()) {
            allKeyphrases.add(nextKeyphrase);
        }

        return allKeyphrases;
    }
}
