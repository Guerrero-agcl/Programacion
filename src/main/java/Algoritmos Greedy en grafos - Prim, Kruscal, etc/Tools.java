package org.eda2.practica02;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;

// TODO: Auto-generated Javadoc
/**
 * The Class Tools.
 */
public class Tools {

    /**
     * Save data.
     *
     * @param net the net
     * @param res the res
     * @param archivo the archivo
     * @throws FileNotFoundException the file not found exception
     */
    public static void saveData(Network net, Resultado res, String archivo) throws FileNotFoundException {

        PrintWriter pw = new PrintWriter(archivo);
        pw.println("digraph EDALand_reduced {");
        pw.println("\tlayout=neato");
        pw.println("\tfontname=\"Helvetica,Arial,sans-serif\"");
        pw.println("\tnode [fontname=\"Helvetica,Arial,sans-serif\", fontsize=19]");
        pw.println("\tedge [fontname=\"Helvetica,Arial,sans-serif\"]");
        pw.println("\trankdir=LR;");
        pw.println("\tnode [style=filled, shape=circle, height=0.25]");
        pw.println("\tedge [dir=none]");
        pw.println("\t// nodes");

        for (String v : net.getGrafo().keySet()) {
            if (v.equals(res.getOrigen())) {
                pw.println("\t" + v + " [xlabel=\"" + v + "\", label=\"\", fillcolor=\"blue\"]");
            } else {
                pw.println("\t" + v + " [xlabel=\"" + v + "\", label=\"\"]");
            }
        }

        for (Entry<String, HashMap<String, Double>> it : net.getGrafo().entrySet()) {
            for (Entry<String, Double> it2 : it.getValue().entrySet()) {
                if (res.containsArista(it.getKey(), it2.getKey())) {
                    pw.println("\t" + it.getKey() + " -> " + it2.getKey() + " [label=\"" + it2.getValue() + "\", color=\"red\"]");
                } else {
                    pw.println("\t" + it.getKey() + " -> " + it2.getKey() + " [label=\"" + it2.getValue() + "\"]");
                }
            }
        }

        pw.println("}");
        pw.close();
    }
}
