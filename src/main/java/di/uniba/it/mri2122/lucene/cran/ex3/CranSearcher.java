/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.it.mri2122.lucene.cran.ex3;

import com.google.gson.Gson;
import di.uniba.it.mri2122.lucene.cran.CranQuery;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.ClassicAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.similarities.*;
import org.apache.lucene.store.FSDirectory;

/**
 *
 * @author pierpaolo
 */
public class CranSearcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ParseException {
        if (args.length > 2) {
            FSDirectory fsdir = FSDirectory.open(new File(args[1]).toPath());
            IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(fsdir));
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            BufferedWriter writer = new BufferedWriter(new FileWriter(args[2]));
            Gson gson = new Gson();
            QueryParser qp = new QueryParser("text", new PersonalAnalyzer());
            int idq=1;
            while (reader.ready()) {
                CranQuery query = gson.fromJson(reader.readLine(), CranQuery.class);
                String t=customQueryBoost(query);
                Query lq = qp.parse(t);
                System.out.println(idq + " " + lq);
                TopDocs topdocs = searcher.search(lq, 100);
                int r = 1;
                for (ScoreDoc sd : topdocs.scoreDocs) {
                    writer.append(String.valueOf(idq)).append(" 0 ");
                    writer.append(searcher.doc(sd.doc).get("id"));
                    writer.append(" ").append(String.valueOf(r));
                    writer.append(" ").append(String.valueOf(sd.score));
                    writer.append(" exp0");
                    writer.newLine();
                    r++;
                }
                idq++;
            }
            reader.close();
          writer.close();
        }
    }

    private static String customQueryBoost(CranQuery query) {
        return query.getQuery().replace("?", "").replace("*", "").replace("-dash", "").replace("/", "").replace("(", "")
                .replace(")","").replace("theory", "theory^0.9").replace("hypersonic", "hypersonic^1.6").replace("airplane", "airplane^0.4")
                .replace("aircraft", "aircraft^0.6").replace("distribution","distribution^0.3").replace("problem", "problem^0.6").replace("heated", "heated^0.4").replace("speed", "speed^0.3")
                .replace("criterion","criterion^0.3").replace("develop", "develop^0.2").replace("aerodynamic", "aerodynamic^0.3").replace("behaviour","behaviour^0.2").replace("predict", "predict^0.2")
                .replace("papers", "papers^0.2").replace("wide","wide^0.4").replace("enthalpy", "enthalpy^0.2").replace("design","design^0.2").replace("thermodynamic","thermodynamic^0.2").replace("basic", "basic^0.2").replace("approximation","approximation^0.2")
                .replace("investigate","investigate^0.2").replace("require", "require^0.2").replace("field", "field^0.3").replace("unstiffened", "unstiffened^0.3").replace("stiffen", "stiffen^0.2").replace("applicable", "applicable^0.2").replace("strength","strength^0.2")
                .replace("shallow","shallow^0.3").replace("existing","existing^0.2").replace("deflection","deflection^0.2").replace("work", "work^0.3").replace("representative","representative^0.2").replace("absence", "absence^0.2").replace("theoretical","theoretical^0.2").replace("kinetic", "kinetic^0.2")
                .replace("data","data^0.2").replace("solve","solve^0.3").replace("elliptic", "elliptic^0.2").replace("equation","equation^0.2").replace("deformation", "deformation^0.2").replace("rarefaction", "rarefaction^0.3").replace("characteristic", "characteristic^0.2").replace("tail", "tail^0.2").replace("phenomena","phenomena^0.3").replace("vortex", "vortex^0.2")
                .replace("small", "small^0.2").replace("oppose", "oppose^0.2").replace("nature", "nature^0.1").replace("discontinuity", "discontinuity^0.4").replace("single", "single^0.1").replace("fundamental", "fundamental^0.1").replace("times", "times^0.1").replace("classical", "classical^0.2").replace("collapse","collapse^0.3").replace("subject", "subject^0.2").replace("principle","principle^0.2")
                .replace("guide", "guide^0.1").replace("obey", "obey^0.1").replace("input", "input^0.1").replace("exist", "exist^0.2").replace("integrate", "integrate^0.2").replace("influence", "influence^0.3").replace("simulation", "simulation^0.3")
                .replace("physical", "physical^0.4").replace("internal", "internal^0.6")
                .replace("magnitude", "magnitude^0.3").replace("dealing", "dealing^0.2")
                .replace("factor", "factor^0.2").replace("gradient", "gradient^0.3")
                .replace("procedure", "procedure^0.5").replace("reasonable", "reasonable^0.5")
                .replace("computation", "computation^0.3").replace("generate", "generate^0.2")
                .replace("flexible", "flexible^0.2").replace("stabilize", "stabilize^0.1")
                .replace("modify", "modify^0.1").replace("environment", "environment^0.2")
                .replace("result", "result^0.2").replace("establish", "establish^0.2").replace("accurate", "accurate^0.3")
                .replace("progress", "progress^0.2").replace("freedom", "freedom^0.2").replace("analyse", "analyse^0.2")
                .replace("core", "core^0.2").replace("rational", "rational^0.2").replace("swept", "swept^0.7")
                .replace("beam", "beam^0.2").replace("inelastic", "inelastic^0.2").replace("reduce", "reduce^0.2").replace("associate","associate^0.1").replace("couette", "couette^0.2")
                .replace("relate","relate^0.2").replace("strong", "strong^0.2").replace("performance", "performance^0.2").replace("current", "current^0.2").replace("term", "term^0.2").replace("concept","concept^0.2").replace("adjacent", "adjacent^0.1").replace("blade", "blade^0.1").replace("sectional", "sectional^0.1").replace("systematic", "systematic^0.2")
                .replace("acoustic", "acoustic^0.2").replace("propagation", "propagation^0.1").replace("wall", "wall^0.4").replace("insulate", "insulate^0.1").replace("attachment", "attachment^0.2").replace("numerical", "numerical^0.2").replace("suitable", "suitable^0.2").replace("partially", "partially^0.2");// remove special chars

    }

}
