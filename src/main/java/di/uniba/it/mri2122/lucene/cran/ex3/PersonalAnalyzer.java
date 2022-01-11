package di.uniba.it.mri2122.lucene.cran.ex3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;


import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.core.*;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.*;
import org.apache.lucene.analysis.miscellaneous.RemoveDuplicatesTokenFilter;
import org.apache.lucene.analysis.miscellaneous.StemmerOverrideFilter;
import org.apache.lucene.analysis.ngram.NGramTokenizer;
import org.apache.lucene.analysis.pattern.PatternTokenizer;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.ClassicTokenizer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.synonym.SynonymGraphFilter;
import org.checkerframework.checker.units.qual.K;
import org.tartarus.snowball.ext.EnglishStemmer;
import org.tartarus.snowball.ext.KpStemmer;
import org.tartarus.snowball.ext.LovinsStemmer;


public class PersonalAnalyzer extends StopwordAnalyzerBase {


    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer source = new LetterTokenizer();
        TokenStream filter = new LowerCaseFilter(source);

        filter = new EnglishPossessiveFilter(filter);
        //filter = new PorterStemFilter(filter);
        filter = new RemoveDuplicatesTokenFilter(filter);
        filter = new KStemFilter(filter);
        try {
            filter = new StopFilter(filter, loadStopwordSet(new FileReader((".\\.\\.\\.\\.\\.\\.\\.\\resources\\cran\\stopwords.txt"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new TokenStreamComponents(source, filter);

    }

}
