package com.ufcg.atg.library;


import com.ufcg.atg.graph.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphAMRepresentationTest {

    private GraphLibrary<Integer> graphLibrary;

    private IGraph<Integer, Edge<Integer>> weightedGraphEmpty;
    private IGraph<Integer, Edge<Integer>> unweightedGraphEmpty;
    private IGraph<Integer, Edge<Integer>> graphOneVertex;
    private IGraph<Integer, Edge<Integer>> graphWithLoop;
    private IGraph<String, Edge<String>> stringGraph;
    private IGraph<Integer, Edge<Integer>> integerUnweightedGraph;
    private IWeightedGraph<Integer, WeightedEdge<Integer>> integerWeightedGraph;
    private IGraph<Integer, Edge<Integer>> graphSparse;

    String sparseGraphOutput = "";

    @BeforeEach
    public void setUp() {
        graphLibrary = new GraphLibrary<>();

        setUpGraphs();
    }

    private void setUpGraphs() {
        Integer v1 = 1;

        stringGraph = new Graph<>();

        weightedGraphEmpty = new Graph<>();
        unweightedGraphEmpty = new Graph<>();

        graphOneVertex = new Graph<>();
        graphLibrary.addVertex(graphOneVertex, v1);


        graphWithLoop = new Graph<>();
        graphLibrary.addEdge(graphWithLoop, v1, v1);

        setUpGraphSparse();
        setUpIntegerGraph();
        setUpGraphStrings();
    }

    private void setUpGraphSparse() {
        graphSparse = new Graph<>();

        String header = " ";
        String body = "";

        for (int v = 0; v < 100; v++) {
            graphLibrary.addVertex(graphSparse, v);
            header += " " + v;
            body += v;
            for (int v2 = 0; v2 < 100; v2++) {
                body += " " + 0;
            }
            body += System.getProperty("line.separator");
        }
        sparseGraphOutput += header + System.getProperty("line.separator") + body;
    }

    private void setUpIntegerGraph() {
        Integer v1 = 1, v2 = 2, v3 = 3, v4 = 4;
        integerUnweightedGraph = new Graph<>();
        integerUnweightedGraph.addEdge(v1, v2);
        integerUnweightedGraph.addEdge(v2, v4);
        integerUnweightedGraph.addEdge(v3, v1);
        integerUnweightedGraph.addEdge(v4, v4);

        Float w1 = 0.1f, w2 = 0.2f, w3 = 0.3f, w4 = 0.4f;

        integerWeightedGraph = new WeightedGraph<>();
        integerWeightedGraph.addEdge(v1, v2, w1);
        integerWeightedGraph.addEdge(v2, v4, w2);
        integerWeightedGraph.addEdge(v3, v1, w3);
        integerWeightedGraph.addEdge(v4, v4, w4);

    }

    private void setUpGraphStrings() {
        String v1 = "A", v2 = "B", v3 = "C", v4 = "D", v5 = "E";
        stringGraph = new Graph<>();
        stringGraph.addEdge(v1, v1);
        stringGraph.addEdge(v1, v2);
        stringGraph.addEdge(v1, v3);
        stringGraph.addEdge(v1, v4);
        stringGraph.addEdge(v1, v5);
        stringGraph.addEdge(v2, v5);
        stringGraph.addEdge(v5, v3);
        stringGraph.addEdge(v4, v5);
    }

    @Test
    public void emptyGraphAMTest() {
        assertEquals("" + System.getProperty("line.separator"), graphLibrary.graphRepresentation(weightedGraphEmpty, RepresentationType.ADJACENCY_MATRIX));
        assertEquals("" + System.getProperty("line.separator"), graphLibrary.graphRepresentation(unweightedGraphEmpty, RepresentationType.ADJACENCY_MATRIX));
    }

    @Test
    public void oneVertexGraphAMTest() {
        String expectedOutput = "  1" + System.getProperty("line.separator") +
                         "1 0" + System.getProperty("line.separator");

        assertEquals(expectedOutput, graphLibrary.graphRepresentation(graphOneVertex, RepresentationType.ADJACENCY_MATRIX));
    }

    @Test
    public void vertexLoopGraphAMTest() {
        String expectedOutput = "  1" + System.getProperty("line.separator") +
                "1 1" + System.getProperty("line.separator");

        assertEquals(expectedOutput, graphLibrary.graphRepresentation(graphWithLoop, RepresentationType.ADJACENCY_MATRIX));
    }

    @Test
    public void integerUnweightedGraphAMRepresentation() {
        String expectedOutput = "  1 2 3 4" + System.getProperty("line.separator") +
                "1 0 1 1 0" + System.getProperty("line.separator") +
                "2 1 0 0 1" + System.getProperty("line.separator") +
                "3 1 0 0 0" + System.getProperty("line.separator") +
                "4 0 1 0 1" + System.getProperty("line.separator");

        assertEquals(expectedOutput, graphLibrary.graphRepresentation(integerUnweightedGraph, RepresentationType.ADJACENCY_MATRIX));
    }

    @Test
    public void integerWeightedGraphAMRepresentation() {
        String expectedOutput = "  1 2 3 4" + System.getProperty("line.separator") +
                "1 0 0.1 0.3 0" + System.getProperty("line.separator") +
                "2 0.1 0 0 0.2" + System.getProperty("line.separator") +
                "3 0.3 0 0 0" + System.getProperty("line.separator") +
                "4 0 0.2 0 0.4" + System.getProperty("line.separator");

        assertEquals(expectedOutput, graphLibrary.graphRepresentation(integerWeightedGraph, RepresentationType.ADJACENCY_MATRIX));
    }
    @Test
    public void stringGraphAMRepresentation() {
        String expectedOutput = "  A B C D E" + System.getProperty("line.separator") +
                "A 1 1 1 1 1" + System.getProperty("line.separator") +
                "B 1 0 0 0 1" + System.getProperty("line.separator") +
                "C 1 0 0 0 1" + System.getProperty("line.separator") +
                "D 1 0 0 0 1" + System.getProperty("line.separator") +
                "E 1 0 1 0 1" + System.getProperty("line.separator");

        //Error: Wrong 1st argument type, when calling:
        // graphLibrary.graphRepresentation(RepresentationType.ADJACENCY_MATRIX
        // cannot be applied to String type.
        assertEquals(expectedOutput, "");

    }

    @Test
    public void sparseGraphAMRepresentationTest() {
        assertEquals(sparseGraphOutput, graphLibrary.graphRepresentation(graphSparse, RepresentationType.ADJACENCY_MATRIX));
    }

}
