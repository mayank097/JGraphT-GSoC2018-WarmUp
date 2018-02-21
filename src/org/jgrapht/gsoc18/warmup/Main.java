package org.jgrapht.gsoc18.warmup;

import org.jgrapht.Graph;
import org.jgrapht.alg.NaiveLcaFinder;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.io.*;

import java.io.File;
import java.util.Set;

/***
 * Develop a Java application which can read a simple family tree in .dot (graphviz) format
 * and compute the closest common ancestor of two people.
 * For the .dot file, use Listing 1 from this PDF file
 * (https://visualization.sites.clemson.edu/visualization/wp-content/uploads/2016/10/user-guide-graphviz-palmetto.pdf).
 * Given this file as the tree to work from, and the two inputs "Jon" and "Daenerys",
 * the result should be "Aerys II The Mad".
 * Or given the two inputs "Sansa" and "Arya", the result should be "Eddard".
 *
 * If there are multiple common ancestors of equal distance, output all of them.
 *
 * The Java application should take three parameters on the command line:
 *
 * name of .dot file
 * name of person 1
 * name of person 2
 *
 * The result should be written to standard output.
 */
public final class Main {

    private Main() {}

    public static void main(String[] args) {
        /* Read input parameters */
        if (args.length != 3) {
            System.out.println(
                    "Java app needs three input parameters:\n" +
                            " - path of .dot file\n" +
                            " - name of person 1\n" +
                            " - name of person 2"
            );
            return;
        }
        File dotGraphInputFile = new File(args[0]);
        String person1 = args[1];
        String person2 = args[2];

        /* Build graph from file */
        Graph<String, DefaultEdge> gotGraph = new SimpleDirectedGraph<>(DefaultEdge.class);
        importDOTGraph(gotGraph, dotGraphInputFile);

        if (!gotGraph.containsVertex(person1)) {
            System.out.println(person1 + " is not contained in the input graph");
            return;
        }
        if (!gotGraph.containsVertex(person2)) {
            System.out.println(person2 + " is not contained in the input graph");
            return;
        }

        /* Find set of Closest Common Ancestors of given input nodes */
        NaiveLcaFinder lcaFinder = new NaiveLcaFinder(gotGraph);
        Set<String> lcas = lcaFinder.findLcas(person1, person2);
        System.out.println(lcas.toString());

        /* Find one single Closest Common Ancestor of given input nodes */
        // String lca = (String) lcaFinder.findLca(person1, person2);
        // System.out.println(lca);
    }

    private static void importDOTGraph(Graph graph, File inputFile) {
        VertexProvider<String> vp = (label, attributes) -> label;
        EdgeProvider<String, DefaultEdge> ep = (from, to, label, attributes) -> new DefaultEdge();
        GraphImporter<String, DefaultEdge> importer = new DOTImporter<>(vp, ep);
        try {
            importer.importGraph(graph, inputFile);
        } catch (ImportException e) {
            e.printStackTrace();
        }
    }
}
