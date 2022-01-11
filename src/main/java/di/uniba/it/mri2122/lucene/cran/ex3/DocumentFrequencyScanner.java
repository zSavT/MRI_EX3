package di.uniba.it.mri2122.lucene.cran.ex3;

import org.apache.lucene.index.*;
import org.apache.lucene.search.DocIdSetIterator;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;

import java.io.*;
import java.nio.file.Paths;

public class DocumentFrequencyScanner {

    public DocumentFrequencyScanner() {
    }

    public static void main(String[] args) throws IOException {
        final String INDEX_DIRECTORY = "C:\\Users\\natax\\MRI_EX3\\resources\\cran\\index";
        Directory index;
        index = FSDirectory.open(Paths.get(INDEX_DIRECTORY));
        IndexReader reader = DirectoryReader.open(index);
        int doc = 1;
        while (doc < 1400) {
            Terms termVector = reader.getTermVector(doc, "text");
            TermsEnum termIter = termVector.iterator();
            while (termIter.next() != null) {
                BytesRef instance = termIter.term();
                Term termInstance = new Term("text", instance.utf8ToString());
                long termFreq = reader.totalTermFreq(termInstance);
                long docCount = reader.docFreq(termInstance);
                if (docCount*3<termFreq)
                    System.out.println("term: " + instance.utf8ToString() + ", termFreq = " + termFreq + ", docCount = " + docCount);
            }
            doc++;
        }
    }
}
