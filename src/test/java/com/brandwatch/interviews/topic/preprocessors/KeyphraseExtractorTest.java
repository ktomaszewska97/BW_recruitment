package com.brandwatch.interviews.topic.preprocessors;

import com.brandwatch.interviews.topic.model.Keyphrase;
import com.brandwatch.interviews.topic.model.StemmedToken;
import com.brandwatch.interviews.topic.model.Token;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KeyphraseExtractorTest {

    private KeyphraseExtractor keyphraseExtractor = new KeyphraseExtractor();

    @Test
    void shouldExtractKeyphrases() {
        // Given Risks are an inherent part of operating a business.
        StemmedToken token1 = new StemmedToken("risk", new Token("Risks", "NNS"));
        StemmedToken token2 = new StemmedToken("are", new Token("are", "VBP"));
        StemmedToken token3 = new StemmedToken("an", new Token("an", "DT"));
        StemmedToken token4 = new StemmedToken("inherent", new Token("inher", "JJ"));
        StemmedToken token5 = new StemmedToken("part", new Token("part", "NN"));
        StemmedToken token6 = new StemmedToken("of", new Token("of", "IN"));
        StemmedToken token7 = new StemmedToken("operating", new Token("operat", "VBG"));
        StemmedToken token8 = new StemmedToken("a", new Token("a", "DT"));
        StemmedToken token9 = new StemmedToken("business", new Token("busines", "NN"));
        StemmedToken token10 = new StemmedToken(".", new Token(".", "."));

        List<StemmedToken> tokens = List.of(token1, token2, token3, token4, token5, token6, token7, token8, token9, token10);

        // When
        List<Keyphrase> result = keyphraseExtractor.extract(tokens);

        // Then
        assertThat(result).hasSize(4);
    }
}