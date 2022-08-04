package com.brandwatch.interviews.topic.extractors.rake;

import com.brandwatch.interviews.topic.extractors.TopicExtractor;
import com.brandwatch.interviews.topic.extractors.TopicResults;
import com.brandwatch.interviews.topic.extractors.model.WordCooccurrence;
import com.brandwatch.interviews.topic.model.Keyphrase;
import com.brandwatch.interviews.topic.model.StemmedToken;
import com.brandwatch.interviews.topic.model.Token;
import com.brandwatch.interviews.topic.preprocessors.KeyphraseExtractor;
import com.brandwatch.interviews.topic.preprocessors.PosTagger;
import com.brandwatch.interviews.topic.preprocessors.Stemmer;
import com.brandwatch.interviews.topic.preprocessors.Tokenizer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RakeTopicExtractor implements TopicExtractor {

    private final Tokenizer tokenizer;

    private final PosTagger posTagger;

    private final Stemmer stemmer;

    private final KeyphraseExtractor keyphraseExtractor;

    public RakeTopicExtractor(Tokenizer tokenizer, PosTagger posTagger, Stemmer stemmer, KeyphraseExtractor keyphraseExtractor) {
        this.tokenizer = tokenizer;
        this.posTagger = posTagger;
        this.stemmer = stemmer;
        this.keyphraseExtractor = keyphraseExtractor;
    }

    @Override
    public TopicResults extract(String inputText) {
        List<Keyphrase> keyphrases = preProcess(inputText);
        WordCooccurrence wordCooccurrence = new WordCooccurrence(keyphrases);
        return wordCooccurrence.getTopics();
    }

    private List<Keyphrase> preProcess(String text) {
        List<String> stringTokens = tokenizer.tokenize(text);
        List<Token> tokens = posTagger.tag(stringTokens);
        List<StemmedToken> stemmedTokens = stemmer.stem(tokens);
        List<Keyphrase> keyphrases = keyphraseExtractor.extract(stemmedTokens);
        return keyphrases;
    }

}
