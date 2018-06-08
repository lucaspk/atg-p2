package com.ufcg.atg;

import com.ufcg.atg.graph.*;
import com.ufcg.atg.library.GraphLibrary;
import com.ufcg.atg.util.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import static com.ufcg.atg.util.Utils.LINE_SEPARATOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Jobson Silva, Jose Breno
 *
 */
class GraphModuleTest {
    private final int LARGE_GRAPH_SIZE_REP = 2000;
    private final float EDGE_DEFAULT_WEIGHT = 1f;
    private GraphLibrary<Integer> graphLibrary;
    private GraphLibrary<String> graphLibraryStr;

    private IGraph<Integer, Edge<Integer>> graph;
    private IGraph<Integer, Edge<Integer>> graphBlank;
    private IGraph<Integer, Edge<Integer>> graphIncompleteEdge;
    private IGraph<Integer, Edge<Integer>> graphNoEdges;
    private IGraph<Integer, Edge<Integer>> graphNoVertexNumb;
    private IGraph<Integer, Edge<Integer>> graphOneVertexEdge;
    private IGraph<Integer, Edge<Integer>> graphVertexLoop;
    private IGraph<Integer, Edge<Integer>> graphDuplicatedEdge;
    private IGraph<Integer, Edge<Integer>> graphEmptyFile;
    private IWeightedGraph<Integer, WeightedEdge<Integer>> graphWeighted;
    private IWeightedGraph<Integer, WeightedEdge<Integer>> graphWeightedDisconnected;
    private IGraph<Integer, Edge<Integer>> graphDisconnected;
    private IWeightedGraph<Integer, WeightedEdge<Integer>> integerWeightedGraph;
    private IGraph<Integer, Edge<Integer>> graphFloatNumbVertex;
    private IGraph<Integer, Edge<Integer>> graphNameNumbVertex;
    private IGraph<String, Edge<String>> graphStr;
    private IGraph<Integer, Edge<Integer>> bigGraphStarForm;
    private IGraph<Integer, Edge<Integer>> bigGraphListForm;
    private IGraph<Integer, Edge<Integer>> bigGraphDisconnected;
    private IGraph<Integer, Edge<Integer>> graphSparse;
    // SHORTEST PATH
    private IGraph<Integer, Edge<Integer>> disconnectedGraphSP;
    private IGraph<Integer, Edge<Integer>> integerGraphSP;
    private IGraph<Integer, Edge<Integer>> integerGraph2SP;
    private IGraph<Integer, Edge<Integer>> integerGraph3SP;
    private IGraph<String, Edge<String>> stringGraphSP;
    private IGraph<String, Edge<String>> stringGraph2SP;
    private IWeightedGraph<Integer, WeightedEdge<Integer>> weightedIntegerGraphSP;
    private IWeightedGraph<Integer, WeightedEdge<Integer>> weightedIntegerGraph2SP;
    private IWeightedGraph<Integer, WeightedEdge<Integer>> weightedIntegerGraph3SP;
    private IWeightedGraph<String, WeightedEdge<String>> weightedStringGraphSP;
    private IWeightedGraph<String, WeightedEdge<String>> weightedStringGraph2SP;
    // GRAPH REPRESENTATION
    private IGraph<Integer, Edge<Integer>> largeIntegerGraphRep;
    private IGraph<String, Edge<String>> largeStringGraphRep;
    private IWeightedGraph<Integer, WeightedEdge<Integer>> largeWeightedIntegerGraphRep;
    private IWeightedGraph<String, WeightedEdge<String>> largeWeightedStringGraphRep;
    private IGraph<Integer, Edge<Integer>> disconnectedGraphRep;
    private IGraph<Integer, Edge<Integer>> integerGraphRep;
    private IGraph<Integer, Edge<Integer>> integerGraph2Rep;
    private IGraph<String, Edge<String>> stringGraphRep;
    private IWeightedGraph<Integer, WeightedEdge<Integer>> weightedIntegerGraphRep;
    private IWeightedGraph<String, WeightedEdge<String>> weightedStringGraphRep;


    private String sampleGraph = "GraphLibrary/src/graphFiles/sample_graph.txt";
    private String sampleWeightedGraph = "GraphLibrary/src/graphFiles/sample_graph_weighted.txt";
    private String sampleGraphWithBlankLine = "GraphLibrary/src/graphFiles/sample_graph_with_blank_line.txt";
    private String noVertexNumbGraph = "GraphLibrary/src/graphFiles/sample_graph_no_vertex_numb.txt";
    private String duplicatedEdgeGraph = "GraphLibrary/src/graphFiles/sample_graph_duplicated_edge.txt";
    private String incompleteEdgeGraph = "GraphLibrary/src/graphFiles/sample_graph_incomplete_edge.txt";
    private String noEdgesGraph = "GraphLibrary/src/graphFiles/sample_graph_no_edges.txt";
    private String oneVertexEdgeGraph = "GraphLibrary/src/graphFiles/sample_graph_one_vertex_edge.txt";
    private String vertexLoopGraph = "GraphLibrary/src/graphFiles/sample_graph_vertex_loop.txt";
    private String emptyGraph = "GraphLibrary/src/graphFiles/sample_graph_empty.txt";
    private String disconnectedGraph = "GraphLibrary/src/graphFiles/sample_graph_disconnected.txt";
    private String disconnectedWeightedGraph = "GraphLibrary/src/graphFiles/sample_graph_weighted_disconnected.txt";
    private String floatNumbVertexGraph = "GraphLibrary/src/graphFiles/sample_graph_float_vertex_numb.txt";
    private String nameNumbVertexGraph = "GraphLibrary/src/graphFiles/sample_graph_name_vertex_numb.txt";
    private String sampleWeightedGraphNegCycle = "GraphLibrary/src/graphFiles/sample_graph_weighted_neg_cycle.txt";
    private String stringGraph = "GraphLibrary/src/graphFiles/sample_graph_str.txt";

    @BeforeEach
    public void setUp() {
        graphLibrary = new GraphLibrary<>();
        graphLibraryStr = new GraphLibrary<>();

        graph = graphLibrary.readGraph(sampleGraph);
        graphWeighted = graphLibrary.readWeightedGraph(sampleWeightedGraph);
        graphBlank = graphLibrary.readGraph(sampleGraphWithBlankLine);
        graphDuplicatedEdge = graphLibrary.readGraph(duplicatedEdgeGraph);

        setUpGraphs();
    }

    private void setUpGraphs() {
        Integer v1 = -1;

        setUpBigGraphStarForm(v1);

        setUpBigGraphListForm();

        setUpBigGraphDisconnected();

        setUpGraphStrings();

        setUpIntegerWeightedGraph();

        setUpGraphSparse();

        // SHORTEST PATH
        setUpLargeGraphOfStringsSP();

        setUpLargeWeightedGraphOfIntegersSP();

        setUpLargeWeightedGraphOfStringsSP();

        setUpDisconnectedGraphSP();

        setUpGraphOfIntegersSP();

        setUpGraphOfIntegers2SP();

        setUpGraphOfIntegers3SP();

        setUpGraphOfStringsSP();

        setUpWeightedGraphOfIntegersSP();

        setUpWeightedGraphOfIntegers2SP();

        setUpWeightedGraphOfStringsSP();

        // GRAPH REPRESENTATION
        setUpLargeGraphOfIntegersRep();

        setUpLargeGraphOfStringsRep();

        setUpLargeWeightedGraphOfIntegersRep();

        setUpLargeWeightedGraphOfStringsRep();

        setUpDisconnectedGraphRep();

        setUpGraphOfIntegersRep();

        setUpGraphOfIntegers2Rep();

        setUpGraphOfStringsRep();

        setUpWeightedGraphOfIntegersRep();

        setUpWeightedGraphOfStringsRep();
    }

    private void setUpIntegerWeightedGraph() {
        Integer v1 = 1, v2 = 2, v3 = 3, v4 = 4;
        Float w1 = 0.1f, w2 = 0.2f, w3 = 0.3f, w4 = 0.4f;

        integerWeightedGraph = new WeightedGraph<>();
        integerWeightedGraph.addEdge(v1, v2, w1);
        integerWeightedGraph.addEdge(v2, v4, w2);
        integerWeightedGraph.addEdge(v3, v1, w3);
        integerWeightedGraph.addEdge(v4, v4, w4);
    }

    private void setUpGraphStrings() {
        String v1 = "A", v2 = "B", v3 = "C", v4 = "D", v5 = "E";
        graphStr = new Graph<>();
        graphStr.addEdge(v1, v1);
        graphStr.addEdge(v1, v2);
        graphStr.addEdge(v1, v3);
        graphStr.addEdge(v1, v4);
        graphStr.addEdge(v1, v5);
        graphStr.addEdge(v2, v5);
        graphStr.addEdge(v5, v3);
        graphStr.addEdge(v4, v5);
    }

    private void setUpBigGraphStarForm(Integer centralVertex) {
        bigGraphStarForm = new Graph<>();
        for (int v = 0; v < 79999; v++) {
            graphLibrary.addEdge(bigGraphStarForm, centralVertex, v);
        }
    }

    private void setUpBigGraphListForm() {
        bigGraphListForm = new Graph<>();
        for (int v = 1; v < 80000; v ++) {
            graphLibrary.addEdge(bigGraphListForm, v, v - 1);
        }
    }

    private void setUpBigGraphDisconnected() {
        bigGraphDisconnected = new Graph<>();
        for (int v = 0; v < 80000; v++) {
            graphLibrary.addVertex(bigGraphDisconnected, v);
        }
    }

    private void setUpGraphSparse() {
        graphSparse = new Graph<>();

        for (int v = 0; v < 100; v++) {
            graphLibrary.addVertex(graphSparse, v);
        }
    }

    // SHORTEST PATH
    private void setUpGraphOfIntegers3SP() {
        integerGraph3SP = new Graph<>();
        integerGraph3SP.addEdge(1, 2);
        integerGraph3SP.addEdge(2, 3);
        integerGraph3SP.addEdge(3, 4);
        integerGraph3SP.addEdge(4, 5);
    }

    private void setUpLargeGraphOfStringsSP() {
        stringGraph2SP = new Graph<>();

        stringGraph2SP.addEdge("A", "B");
        stringGraph2SP.addEdge("B", "C");
        stringGraph2SP.addEdge("C", "D");
        stringGraph2SP.addEdge("D", "E");
    }

    private void setUpLargeWeightedGraphOfIntegersSP() {
        weightedIntegerGraph3SP = new WeightedGraph<>();

        weightedIntegerGraph3SP.addEdge(1, 2, 1);
        weightedIntegerGraph3SP.addEdge(2, 3, 1);
        weightedIntegerGraph3SP.addEdge(3, 4, 1);
        weightedIntegerGraph3SP.addEdge(4, 5, 1);
    }

    private void setUpLargeWeightedGraphOfStringsSP() {
        weightedStringGraph2SP = new WeightedGraph<>();

        weightedStringGraph2SP.addEdge("A", "B", 1);
        weightedStringGraph2SP.addEdge("B", "C", 1);
        weightedStringGraph2SP.addEdge("C", "D", 1);
        weightedStringGraph2SP.addEdge("D", "E", 1);
    }

    private void setUpGraphOfIntegersSP() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5, i6 = 6;
        integerGraphSP = new Graph<>();
        integerGraphSP.addEdge(i1, i2);
        integerGraphSP.addEdge(i1, i4);
        integerGraphSP.addEdge(i1, i5);
        integerGraphSP.addEdge(i2, i3);
        integerGraphSP.addEdge(i2, i6);
        integerGraphSP.addEdge(i2, i5);
        integerGraphSP.addEdge(i3, i4);
        integerGraphSP.addEdge(i3, i6);
        integerGraphSP.addEdge(i4, i5);
        integerGraphSP.addEdge(i5, i6);
    }

    private void setUpGraphOfIntegers2SP() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
        integerGraph2SP = new Graph<>();
        integerGraph2SP.addEdge(i1, i2);
        integerGraph2SP.addEdge(i2, i5);
        integerGraph2SP.addEdge(i5, i3);
        integerGraph2SP.addEdge(i4, i5);
        integerGraph2SP.addEdge(i1, i5);
    }

    private void setUpGraphOfStringsSP() {
        String s1 = "A", s2 = "B", s3 = "C", s4 = "D", s5 = "E";
        stringGraphSP = new Graph<>();
        stringGraphSP.addEdge(s1, s2);
        stringGraphSP.addEdge(s2, s5);
        stringGraphSP.addEdge(s5, s3);
        stringGraphSP.addEdge(s4, s5);
        stringGraphSP.addEdge(s1, s5);
    }

    private void setUpDisconnectedGraphSP() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
        disconnectedGraphSP = new Graph<>();
        disconnectedGraphSP.addVertex(i1);
        disconnectedGraphSP.addVertex(i2);
        disconnectedGraphSP.addVertex(i3);
        disconnectedGraphSP.addVertex(i4);
        disconnectedGraphSP.addVertex(i5);
    }

    private void setUpWeightedGraphOfIntegersSP() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
        weightedIntegerGraphSP = new WeightedGraph<>();
        weightedIntegerGraphSP.addEdge(i1, i2, 0.1f);
        weightedIntegerGraphSP.addEdge(i2, i5, 0.2f);
        weightedIntegerGraphSP.addEdge(i5, i3, 5f);
        weightedIntegerGraphSP.addEdge(i3, i4, -9.5f);
        weightedIntegerGraphSP.addEdge(i4, i5, 2.3f);
        weightedIntegerGraphSP.addEdge(i1, i5,1f);
    }

    private void setUpWeightedGraphOfIntegers2SP() {
        weightedIntegerGraph2SP = new WeightedGraph<>();
        weightedIntegerGraph2SP.addEdge(1, 2, 0.1f);
        weightedIntegerGraph2SP.addEdge(2, 5, 0.2f);
        weightedIntegerGraph2SP.addEdge(5, 3, 5f);
        weightedIntegerGraph2SP.addEdge(4, 5, 2.3f);
        weightedIntegerGraph2SP.addEdge(1, 5,1f);
    }

    private void setUpWeightedGraphOfStringsSP() {
        String s1 = "A", s2 = "B", s3 = "C", s4 = "D", s5 = "E";
        weightedStringGraphSP = new WeightedGraph<>();
        weightedStringGraphSP.addEdge(s1, s2, 0.1f);
        weightedStringGraphSP.addEdge(s2, s5, 0.2f);
        weightedStringGraphSP.addEdge(s5, s3, 5f);
        weightedStringGraphSP.addEdge(s3, s4, -9.5f);
        weightedStringGraphSP.addEdge(s4, s5, 2.3f);
        weightedStringGraphSP.addEdge(s1, s5,1f);
    }

    // GRAPH REPRESENTATION
    private void setUpLargeGraphOfIntegersRep() {
        largeIntegerGraphRep = new Graph<>();
        for (int i = 1; i <= LARGE_GRAPH_SIZE_REP; i++) {
            for (int j = 1; j <= LARGE_GRAPH_SIZE_REP; j++) {
                if (i != j) {
                    largeIntegerGraphRep.addEdge(i, j);
                }
            }
        }
    }

    private void setUpLargeGraphOfStringsRep() {
        largeStringGraphRep = new Graph<>();

        for (int i = 1; i <= LARGE_GRAPH_SIZE_REP; i++) {
            for (int j = 1; j <= LARGE_GRAPH_SIZE_REP; j++) {
                if (i != j) {
                    largeStringGraphRep.addEdge(Integer.toHexString(i), Integer.toHexString(j));
                }
            }
        }
    }

    private void setUpLargeWeightedGraphOfIntegersRep() {
        largeWeightedIntegerGraphRep = new WeightedGraph<>();

        for (int i = 1; i <= LARGE_GRAPH_SIZE_REP; i++) {
            for (int j = 1; j <= LARGE_GRAPH_SIZE_REP; j++) {
                if (i != j) {
                    largeWeightedIntegerGraphRep.addEdge(i, j, (i+j)/2f);
                }
            }
        }
    }

    private void setUpLargeWeightedGraphOfStringsRep() {
        largeWeightedStringGraphRep = new WeightedGraph<>();

        for (int i = 1; i <= LARGE_GRAPH_SIZE_REP; i++) {
            for (int j = 1; j <= LARGE_GRAPH_SIZE_REP; j++) {
                if (i != j) {
                    largeWeightedStringGraphRep.addEdge(Integer.toHexString(i), Integer.toHexString(j), (i+j)/2f);
                }
            }
        }
    }

    private void setUpGraphOfIntegersRep() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5, i6 = 6;
        integerGraphRep = new Graph<>();
        integerGraphRep.addEdge(i1, i2);
        integerGraphRep.addEdge(i1, i4);
        integerGraphRep.addEdge(i1, i5);
        integerGraphRep.addEdge(i2, i3);
        integerGraphRep.addEdge(i2, i6);
        integerGraphRep.addEdge(i2, i5);
        integerGraphRep.addEdge(i3, i4);
        integerGraphRep.addEdge(i3, i6);
        integerGraphRep.addEdge(i4, i5);
        integerGraphRep.addEdge(i5, i6);
    }

    private void setUpGraphOfIntegers2Rep() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
        integerGraph2Rep = new Graph<>();
        integerGraph2Rep.addEdge(i1, i2);
        integerGraph2Rep.addEdge(i2, i5);
        integerGraph2Rep.addEdge(i5, i3);
        integerGraph2Rep.addEdge(i4, i5);
        integerGraph2Rep.addEdge(i1, i5);
    }

    private void setUpGraphOfStringsRep() {
        String s1 = "A", s2 = "B", s3 = "C", s4 = "D", s5 = "E";
        stringGraphRep = new Graph<>();
        stringGraphRep.addEdge(s1, s2);
        stringGraphRep.addEdge(s2, s5);
        stringGraphRep.addEdge(s5, s3);
        stringGraphRep.addEdge(s4, s5);
        stringGraphRep.addEdge(s1, s5);
    }

    private void setUpDisconnectedGraphRep() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
        disconnectedGraphRep = new Graph<>();
        disconnectedGraphRep.addVertex(i1);
        disconnectedGraphRep.addVertex(i2);
        disconnectedGraphRep.addVertex(i3);
        disconnectedGraphRep.addVertex(i4);
        disconnectedGraphRep.addVertex(i5);
    }

    private void setUpWeightedGraphOfIntegersRep() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
        weightedIntegerGraphRep = new WeightedGraph<>();
        weightedIntegerGraphRep.addEdge(i1, i2, 0.1f);
        weightedIntegerGraphRep.addEdge(i2, i5, 0.2f);
        weightedIntegerGraphRep.addEdge(i5, i3, 5f);
        weightedIntegerGraphRep.addEdge(i3, i4, -9.5f);
        weightedIntegerGraphRep.addEdge(i4, i5, 2.3f);
        weightedIntegerGraphRep.addEdge(i1, i5,1f);
    }

    private void setUpWeightedGraphOfStringsRep() {
        String s1 = "A", s2 = "B", s3 = "C", s4 = "D", s5 = "E";
        weightedStringGraphRep = new WeightedGraph<>();
        weightedStringGraphRep.addEdge(s1, s2, 0.1f);
        weightedStringGraphRep.addEdge(s2, s5, 0.2f);
        weightedStringGraphRep.addEdge(s5, s3, 5f);
        weightedStringGraphRep.addEdge(s3, s4, -9.5f);
        weightedStringGraphRep.addEdge(s4, s5, 2.3f);
        weightedStringGraphRep.addEdge(s1, s5,1f);
    }

    @Test
    public void testSuccessfulReadingWeightedGraphFromFile() {
        boolean isSuccessfullReading = graphWeighted != null;
        assertTrue(isSuccessfullReading);
    }

    @Test
    public void testGetEdgeMeanFromWeightedGraph() {

        assertEquals(2.0f, graphLibrary.getMeanEdge(graphWeighted));

    }

    @Test
    public void testVertexNumberWeightedGraphFromFile() {

        assertEquals(5, graphLibrary.getVertexNumber(graphWeighted));
    }

    @Test
    public void verticesNumberBigGraphListFormTest() {
        assertEquals(80000, graphLibrary.getVertexNumber(bigGraphListForm));
    }

    @Test
    public void verticesNumberBigGraphStarFormTest() {
        assertEquals(80000, graphLibrary.getVertexNumber(bigGraphStarForm));
    }

    @Test
    public void verticesNumberBigGraphDisconnectedTest() {
        assertEquals(80000, graphLibrary.getVertexNumber(bigGraphDisconnected));
    }


    @Test
    public void testEdgeNumberWeightedGraphFromFile() {

        assertEquals(5, graphLibrary.getEdgeNumber(graphWeighted));
    }

    @Test
    public void testAddEdgeWeightedGraphSpecificWeight() {

        graphLibrary.addEdge(graphWeighted, 1, 6, 1.5f);

        assertEquals(true, graphLibrary.containsEdge(graphWeighted,
                new WeightedEdge<>(1, 6, 1.5f)));
    }

    @Test
    public void testAddEdgeWeightedGraphDefaultWeight() {

        graphLibrary.addEdge(graphWeighted, 1, 6);


        assertEquals(true, graphLibrary.containsEdge(graphWeighted,
                new WeightedEdge<>(1, 6, EDGE_DEFAULT_WEIGHT)));
    }



    @Test
    public void testReadingGraphFromFileWithOneVertexEdges() {
        try {
            graphOneVertexEdge = graphLibrary.readGraph(oneVertexEdgeGraph);
        } catch (Exception e) {
            assertTrue(e != null);
            assertEquals("No edge found in the file. Please, try with another file.", e.getMessage());
        }

        boolean isUnsuccessfullReading = graphOneVertexEdge == null;
        assertTrue(isUnsuccessfullReading);

    }

    @Test
    public void testUnsuccessfullReadingNullFile() {
        IGraph<Integer, Edge<Integer>> graphNull = null;
        try {
            graphNull = graphLibrary.readGraph(null);
        } catch (Exception e) {
            assertTrue(e != null);
            assertEquals("Can't read file from null.", e.getMessage());
        }

        boolean isUnsuccessfullReading = graphNull == null;
        assertTrue(isUnsuccessfullReading);

    }

    @Test
    public void testUnsuccessfullReadingNotFoundFile() {
        IGraph<Integer, Edge<Integer>> graphFromNotFoundFile = null;

        String expected = "java.io.FileNotFoundException: " +
                "jupiter\\madeira\\dinamarca\\zebra.txt " +
                "(O sistema não pode encontrar o caminho especificado)";
        try {
            graphFromNotFoundFile = graphLibrary.readGraph("jupiter/madeira/dinamarca/zebra.txt");
        } catch (Exception e) {
            assertTrue(e != null);
            assertEquals(expected, e.getMessage());
        }

        boolean isUnsuccessfullReading = graphFromNotFoundFile == null;
        assertTrue(isUnsuccessfullReading);
    }

    @Test
    public void testReadingGraphFromFileWithNoEdges() {
        try {
            graphNoEdges = graphLibrary.readGraph(noEdgesGraph);
        } catch (Exception e) {
            assertTrue(e != null);
            assertEquals("No edge found in the file. Please, try with another file.", e.getMessage());
        }

        boolean isUnsuccessfullReading = graphNoEdges == null;
        assertTrue(isUnsuccessfullReading);

    }

    @Test
    public void testReadingGraphFromEmptyFile() {
        try {
            graphEmptyFile = graphLibrary.readGraph(emptyGraph);
        } catch (Exception e) {
            assertTrue(e != null);
            assertEquals("The file is empty. Please, try with another file.", e.getMessage());
        }

        boolean isUnsuccessfullReading = graphEmptyFile == null;
        assertTrue(isUnsuccessfullReading);

    }


    @Test
    public void testSuccessfulReadingGraphFromFileWithBlankLine() {
        boolean isSuccessfullReading = graphBlank != null;
        assertTrue(isSuccessfullReading);

    }

    @Test
    public void testVertexNumberReadGraphFromFileWithBlankLine() {

        assertEquals(5, graphLibrary.getVertexNumber(graphBlank));
    }

    @Test
    public void testEdgeNumberReadGraphFromFileWithBlankLine() {

        assertEquals(5, graphLibrary.getEdgeNumber(graphBlank));
    }


    @Test
    public void testSuccessfulReadingGraphFromFile() {
        boolean isSuccessfullReading = graph != null;
        assertTrue(isSuccessfullReading);
    }

    @Test
    public void testVertexNumberGraphFromFile() {

        assertEquals(5, graphLibrary.getVertexNumber(graph));
    }

    @Test
    public void testEdgeNumberGraphFromFile() {

        assertEquals(5, graphLibrary.getEdgeNumber(graph));
    }

    @Test
    public void testContainsEdgeUnweightedGraph() {

        assertEquals(true, graphLibrary.containsEdge(graph, new Edge<>(1, 2)));
        assertEquals(true, graphLibrary.containsEdge(graph, new Edge<>(2, 1)));
        assertEquals(true, graphLibrary.containsEdge(graph, new Edge<>(5, 1)));
        assertEquals(true, graphLibrary.containsEdge(graph, new Edge<>(1, 5)));
    }

    @Test
    public void testNotContainsEdgeUnweightedGraph() {

        assertEquals(false, graphLibrary.containsEdge(graph, new Edge<>(22, 16)));
        assertEquals(false, graphLibrary.containsEdge(graph, new Edge<>(53, 14)));
        assertEquals(false, graphLibrary.containsEdge(graph, new Edge<>(17, 55)));
        assertEquals(false, graphLibrary.containsEdge(graph, new Edge<>(11, 12)));
    }

    @Test
    public void testContainsEdgeNullUnweightedGraph() {

        assertEquals(false, graphLibrary.containsEdge(graph, new Edge<>(null, 2)));
        assertEquals(false, graphLibrary.containsEdge(graph, new Edge<>(1, null)));
        assertEquals(false, graphLibrary.containsEdge(graph, new Edge<>(null, null)));
    }

    @Test
    public void testContainsVertexUnweightedGraph() {

        assertEquals(true, graphLibrary.containsVertex(graph, 2));
        assertEquals(true, graphLibrary.containsVertex(graph, 5));
        assertEquals(true, graphLibrary.containsVertex(graph, 3));
        assertEquals(true, graphLibrary.containsVertex(graph, 4));
    }

    @Test
    public void testNotContainsVertexUnweightedGraph() {

        assertEquals(false, graphLibrary.containsVertex(graph, 14));
        assertEquals(false, graphLibrary.containsVertex(graph, 8));
        assertEquals(false, graphLibrary.containsVertex(graph, 31));
    }


    @Test
    public void testGetAdjacentVertexNullUnweightedGraph() {
        Set<Integer> adjcent = null;
        try {
             adjcent = graphLibrary.getAdjacentVertexes(graph, null);
        } catch (Exception e) {
            assertEquals("A vertex can't be null. So, no adjacent were found.", e.getMessage());
        }
        assertTrue(adjcent == null);
    }

    @Test
    public void testGetAdjacentNonexistentVertexUnweightedGraph() {
        Set<Integer> adjcent = null;
        try {
            adjcent = graphLibrary.getAdjacentVertexes(graph, 51);
        } catch (Exception e) {
            assertEquals("The graph doesn't contains the specified vertex.", e.getMessage());
        }
        assertTrue(adjcent == null);
    }

    @Test
    public void testGetAdjacentExistentVertexUnweightedGraph() {
        Set<Integer> adjcent = null;

        assertEquals(true, graphLibrary.containsVertex(graph, 2));

        try {
            adjcent = graphLibrary.getAdjacentVertexes(graph, 2);
        } catch (Exception e) {
            assertTrue(e.getMessage() == null);
        }
        assertTrue(adjcent != null);
    }


    @Test
    public void testGetAdjacentVertexNullWeightedGraph() {
        Set<Integer> adjcent = null;
        try {
            adjcent = graphLibrary.getAdjacentVertexes(graphWeighted, null);
        } catch (Exception e) {
            assertEquals("A vertex can't be null. So, no adjacent were found.", e.getMessage());
        }
        assertTrue(adjcent == null);
    }

    @Test
    public void testGetAdjacentNonexistentVertexWeightedGraph() {
        Set<Integer> adjcent = null;
        try {
            adjcent = graphLibrary.getAdjacentVertexes(graphWeighted, 51);
        } catch (Exception e) {
            assertEquals("The graph doesn't contains the specified vertex.", e.getMessage());
        }
        assertTrue(adjcent == null);
    }

    @Test
    public void testGetAdjacentExistentVertexWeightedGraph() {
        Set<Integer> adjcent = null;

        assertEquals(true, graphLibrary.containsVertex(graphWeighted, 2));

        try {
            adjcent = graphLibrary.getAdjacentVertexes(graphWeighted, 2);
        } catch (Exception e) {
            assertTrue(e.getMessage() == null);
        }
        assertTrue(adjcent != null);
    }


    @Test
    public void testGetVertexEdgesVertexNullUnweightedGraph() {
        Set<?> adjcent = null;
        try {
            adjcent = graphLibrary.getEdgesOfVertex(graph, null);
        } catch (Exception e) {
            assertEquals("A vertex can't be null. So, no adjacent were found.", e.getMessage());
        }
        assertTrue(adjcent == null);
    }

    @Test
    public void testGetVertexEdgesNonexistentVertexUnweightedGraph() {
        Set<?> adjcent = null;
        try {
            adjcent = graphLibrary.getEdgesOfVertex(graph, 51);
        } catch (Exception e) {
            assertEquals("The graph doesn't contains the specified vertex.", e.getMessage());
        }
        assertTrue(adjcent == null);
    }

    @Test
    public void testGetVertexEdgesExistentVertexUnweightedGraph() {
        Set<?> adjcent = null;

        assertEquals(true, graphLibrary.containsVertex(graph, 2));

        try {
            adjcent = graphLibrary.getEdgesOfVertex(graph, 2);
        } catch (Exception e) {
            assertTrue(e.getMessage() == null);
        }
        assertTrue(adjcent != null);
    }



    @Test
    public void testGetVertexEdgesVertexNullWeightedGraph() {
        Set<?> adjcent = null;
        try {
            adjcent = graphLibrary.getEdgesOfVertex(graphWeighted, null);
        } catch (Exception e) {
            assertEquals("A vertex can't be null. So, no adjacent were found.", e.getMessage());
        }
        assertTrue(adjcent == null);
    }

    @Test
    public void testGetVertexEdgesNonexistentVertexWeightedGraph() {
        Set<?> adjcent = null;
        try {
            adjcent = graphLibrary.getEdgesOfVertex(graphWeighted, 51);
        } catch (Exception e) {
            assertEquals("The graph doesn't contains the specified vertex.", e.getMessage());
        }
        assertTrue(adjcent == null);
    }

    @Test
    public void testGetVertexEdgesExistentVertexWeightedGraph() {
        Set<?> adjcent = null;

        assertEquals(true, graphLibrary.containsVertex(graphWeighted, 2));

        try {
            adjcent = graphLibrary.getAdjacentVertexes(graphWeighted, 2);
        } catch (Exception e) {
            assertTrue(e.getMessage() == null);
        }
        assertTrue(adjcent != null);
    }




    @Test
    public void testContainsVertexNullUnweightedGraph() {

        assertEquals(false, graphLibrary.containsVertex(graph, null));
    }


    @Test
    public void testGetEdgeMeanFromUnweightedGraph() {

        assertEquals(2.0f, graphLibrary.getMeanEdge(graph));

    }

    @Test
    public void testGetEdgeMeanFromNullGraph() {
        float mean = -1.5f;
        try {
            mean = graphLibrary.getMeanEdge(null);
        } catch (Exception e) {
            assertEquals("Can't get edge mean from null", e.getMessage());
        }
        assertEquals(-1.5f , mean);

    }

    @Test
    public void testAddVertexUnweightedGraph() {
        graphLibrary.addVertex(graph, 6);
        assertEquals(true,
                graphLibrary.containsVertex(graph, 6));
    }

    @Test
    public void testAddNullVertexUnweightedGraph() {
        graphLibrary.addVertex(graph, null);
        assertEquals(false,
                graphLibrary.containsVertex(graph, null));
    }

    @Test
    public void testAddRepeatedVertexUnweightedGraph() {
        try {
            graphLibrary.addVertex(graph, 6);
            graphLibrary.addVertex(graph, 6);
        } catch (Exception e) {
            assertEquals("The graph already contains the specified vertex.",
                    e.getMessage());
        }
        assertEquals(true,
                graphLibrary.containsVertex(graph, 6));
    }

    @Test
    public void testAddVertexNullUnweightedGraph() {
        try {
            graphLibrary.addVertex(null, 6);
        } catch (Exception e) {
            assertEquals("Can't add vertex to null.",
                    e.getMessage());
        }
    }


    @Test
    public void testAddEdgeUnweightedGraph() {
        graphLibrary.addEdge(graph, 1, 6);

        assertEquals(true, graphLibrary.containsEdge(graph,
                new Edge<>(1, 6)));
    }

    @Test
    public void testAddEdgeStringUnweightedGraph() {

        /*There is no method to read vertex as string, so the bellow commented code line will
        cause a compilation error. A method to read a generic type of vertex should be created.

        graphStr = graphLibraryStr.readGraph(stringGraph);

        */
        try {
            graphLibraryStr.addEdge(graphStr, "APLP", "ATG");
        } catch (Exception e) {
            assertTrue(e == null);
        }
        assertEquals(true, graphLibraryStr.containsEdge(graphStr,
                new Edge<String>("APLP", "ATG")));
    }

    @Test
    public void testAddNullEdgeUnweightedGraph() {

        try {
            graphLibrary.addEdge(graph, null, null);
            graphLibrary.addEdge(graph, 1, null);
            graphLibrary.addEdge(graph, null, 1);
        } catch (Exception e) {
            assertEquals("Can't create edge with vertex null.",
                    e.getMessage());
        }

        assertEquals(false, graphLibrary.containsEdge(graph,
                new Edge<>(null, null)));

        assertEquals(false, graphLibrary.containsEdge(graph,
                new Edge<>(1, null)));

        assertEquals(false, graphLibrary.containsEdge(graph,
                new Edge<>(null, 1)));
    }

    @Test
    public void testAddRepeatedEdgeUnweightedGraph() {
        try {
            graphLibrary.addEdge(graph, 1, 6);
            graphLibrary.addEdge(graph, 1, 6);
        } catch (Exception e) {
            assertEquals("The graph already contains the specified edge.",
                    e.getMessage());
        }
        assertEquals(true, graphLibrary.containsEdge(graph,
                new Edge<>(1, 6)));
    }

    @Test
    public void testAddRepeatedEdgeWeightedGraph() {
        try {
            graphLibrary.addEdge(graphWeighted, 1, 6, 1.5f);
            graphLibrary.addEdge(graphWeighted, 1, 6, 1.5f);
        } catch (Exception e) {
            assertEquals("The graph already contains the specified edge.",
                    e.getMessage());
        }
        assertEquals(true, graphLibrary.containsEdge(graphWeighted,
                new WeightedEdge<>(1, 6, 1.5f)));
    }

    @Test
    public void testAddEdgeWeightedNullGraph() {
        try {
            graphLibrary.addEdge(null, 1, 6, 1.5f);
        } catch (Exception e) {
            assertEquals("Can't add edge on null.",
                    e.getMessage());
        }
    }

    @Test
    public void testAddEdgeUnweightedNullGraph() {
        try {
            graphLibrary.addEdge(null, 1, 6);
        } catch (Exception e) {
            assertEquals("Can't add edge on null.",
                    e.getMessage());
        }
    }

    @Test
    public void testAddNullEdgeWeightedGraph() {
        try {
            graphLibrary.addEdge(graphWeighted, null, null, 1.5f);
            graphLibrary.addEdge(graphWeighted, 1, null, 1.5f);
            graphLibrary.addEdge(graphWeighted, null, 1, 1.5f);
        } catch (Exception e) {
            assertEquals("Can't create edge with vertex null.",
                    e.getMessage());
        }
    }

    @Test
    public void testAddRepeatedEdgeDefaultWeightedGraph() {
        try {
            graphLibrary.addEdge(graphWeighted, 1, 6);
            graphLibrary.addEdge(graphWeighted, 1, 6);
        } catch (Exception e) {
            assertEquals("The graph already contains the specified edge.",
                    e.getMessage());
        }
        assertEquals(true, graphLibrary.containsEdge(graphWeighted,
                new WeightedEdge<>(1, 6, EDGE_DEFAULT_WEIGHT)));
    }

    @Test
    public void testGetAllVertexesNullGraph() {
        Set<?> vertexes = null;
        try {
           vertexes = graphLibrary.getAllVertexes(null);
        } catch (Exception e) {
            assertEquals("Can not get vertexes from null.",
                    e.getMessage());
        }
        assertTrue(vertexes == null);
    }

    @Test
    public void testGetAllVertexesUnweightedGraph() {
        Set<?> vertexes = null;
        try {
            vertexes = graphLibrary.getAllVertexes(graph);
        } catch (Exception e) {
            assertTrue(e == null);
        }
        assertTrue(vertexes != null);
    }

    @Test
    public void testGetAllVertexesWeightedGraph() {
        Set<?> vertexes = null;
        try {
            vertexes = graphLibrary.getAllVertexes(graphWeighted);
        } catch (Exception e) {
            assertTrue(e == null);
        }
        assertTrue(vertexes != null);
    }


    @Test
    public void testGetAllEdgesNullGraph() {
        Set<?> edges = null;
        try {
            edges = graphLibrary.getAllEdges(null);
        } catch (Exception e) {
            assertEquals("Can not get edges from null.",
                    e.getMessage());
        }
        assertTrue(edges == null);
    }

    @Test
    public void testGetAllEdgesUnweightedGraph() {
        Set<?> edges = null;
        try {
            edges = graphLibrary.getAllEdges(graph);
        } catch (Exception e) {
            assertTrue(e == null);
        }
        assertTrue(edges != null);
    }

    @Test
    public void testGetAllEdgesWeightedGraph() {
        Set<?> edges = null;
        try {
            edges = graphLibrary.getAllEdges(graphWeighted);
        } catch (Exception e) {
            assertTrue(e == null);
        }
        assertTrue(edges != null);
    }


    @Test
    public void testSuccessFulReadingGraphFromFileNoVertexNumb() {
        try {
            graphNoVertexNumb = graphLibrary.readGraph(noVertexNumbGraph);
        } catch (Exception e) {
            assertTrue(e == null);
        }

        boolean isSuccessfullReading = graphNoVertexNumb != null;
        assertTrue(isSuccessfullReading);

    }

    @Test
    public void testVertexNumberUnweightedGraphNoVertexNumb() {

        try {
            graphNoVertexNumb = graphLibrary.readGraph(noVertexNumbGraph);
        } catch (Exception e) {
            assertTrue(e == null);
        }

        assertEquals(5,
                graphLibrary.getVertexNumber(graphNoVertexNumb));
    }

    @Test
    public void testEdgeNumberUnweightedGraphNoVertexNumb() {

        try {
            graphNoVertexNumb = graphLibrary.readGraph(noVertexNumbGraph);
        } catch (Exception e) {
            assertTrue(e == null);
        }

        assertEquals(5,
                graphLibrary.getEdgeNumber(graph));
    }


    @Test
    public void testSuccessFulReadingGraphFromFileWithDuplicatedEdge() {
        boolean isSuccessfullReading = graphDuplicatedEdge != null;
        assertTrue(isSuccessfullReading);

    }

    @Test
    public void testVertexNumberUnweightedGraphWithDuplicatedEdge() {

        assertEquals(5, graphLibrary.getVertexNumber(graphDuplicatedEdge));
    }

    @Test
    public void testEdgeNumberUnweightedGraphWithDuplicatedEdge() {

        assertEquals(5, graphLibrary.getEdgeNumber(graphDuplicatedEdge));
    }


    @Test
    public void testSuccessFulReadingGraphFromFileWithIncompleteEdge() {
        try {
            graphIncompleteEdge = graphLibrary.readGraph(incompleteEdgeGraph);
        } catch (Exception e) {
            assertTrue(e == null);
        }

        boolean isSuccessfullReading = graphIncompleteEdge != null;
        assertTrue(isSuccessfullReading);

    }

    @Test
    public void testVertexNumberUnweightedGraphWithIncompleteEdge() {

        try {
            graphIncompleteEdge = graphLibrary.readGraph(incompleteEdgeGraph);
        } catch (Exception e) {
            assertTrue(e == null);
        }

        assertEquals(5, graphLibrary.getVertexNumber(graphIncompleteEdge));
    }

    @Test
    public void testEdgeNumberUnweightedGraphWithIncompleteEdge() {

        try {
            graphIncompleteEdge = graphLibrary.readGraph(incompleteEdgeGraph);
        } catch (Exception e) {
            assertTrue(e == null);
        }

        assertEquals(4, graphLibrary.getEdgeNumber(graphIncompleteEdge));
    }

    /**
     * One possibility other than throw an exception or accept the loop is to
     * ignore the vertex with a loop to itself and successfully read the graph.
     * If you prefer this way, change the test below to check it out the cited scenario.
     *
     * */
    @Test
    public void testReadingGraphFromFileWithVertexLoop() {
        try {
            graphVertexLoop = graphLibrary.readGraph(vertexLoopGraph);
        } catch (Exception e) {
            assertEquals("The graph should not have a vertex with a edge to itself (i.e. loop).",
                    e.getMessage());
        }

        boolean isUnsuccessfullReading = graphVertexLoop == null;
        assertTrue(isUnsuccessfullReading);

    }

    @Test
    public void testIsConnectedGraph() {
        assertTrue(graphLibrary.connected(graph) == true);
    }

    @Test
    public void testIsDisconnectedGraph() {
        try {
            graphDisconnected = graphLibrary.readGraph(disconnectedGraph);
        } catch (Exception e) {
            assertTrue(e == null);
        }
        assertTrue(graphLibrary.connected(graphDisconnected) == false);
    }

    @Test
    public void testShortestPathWithUnexistentVertex() {
        String shortestPath = null;
        try {
           shortestPath = graphLibrary.shortestPath(graphWeighted, 15, 19);
        } catch (Exception e) {
            assertEquals("The graph doesn't contains both specified vertexes.", e.getMessage());
        }
        assertTrue(shortestPath == null);
    }

    @Test
    public void testShortestPathWithGraphNull() {
        String shortestPath = null;
        try {
            shortestPath = graphLibrary.shortestPath(null, 15, 19);
        } catch (Exception e) {
            assertEquals("Can't perform shortest path on null.", e.getMessage());
        }
        assertTrue(shortestPath == null);
    }

    @Test
    public void testShortestPathWithVertexNull() {
        String shortestPath = null;
        try {
            shortestPath = graphLibrary.shortestPath(graphWeighted, null, null);
        } catch (Exception e) {
            assertEquals("The graph doesn't accept null vertex. So, " +
                            "shortest path can't be performed.", e.getMessage());
        }
        assertTrue(shortestPath == null);
    }

    @Test
    public void testShortestPathWithTwoVertexDisconnected() {
        String shortestPath = null;
        try {
            graphDisconnected = graphLibrary.readGraph(disconnectedGraph);
            shortestPath = graphLibrary.shortestPath(graphDisconnected, 2, 3);
        } catch (Exception e) {
            assertEquals("There isn't a path between 2 and 3.",
                    e.getMessage());
        }
        assertTrue(shortestPath == null);
    }

    @Test
    public void testShortestPathWithTwoVertexConnected() {
        String shortestPath = null;
        try {
            shortestPath = graphLibrary.shortestPath(graphWeighted, 2, 3);
        } catch (Exception e) {
            assertTrue(e == null);
        }
        assertTrue(shortestPath != null);
    }

    @Test
    public void testShortestPathWithNegativeCycle() {
        String shortestPath = null;
        IGraph<Integer, Edge<Integer>> graphNegCycle = null;

        try {
            graphNegCycle = graphLibrary.readGraph(sampleWeightedGraphNegCycle);
            shortestPath = graphLibrary.shortestPath(graphNegCycle, 1, 3);
        } catch (Exception e) {
            assertEquals("The shortest path cannot be found in a" +
                    " graph with negative circle.", e.getMessage());
        }
        assertTrue(shortestPath == null);
        assertTrue(graphNegCycle != null);
    }


    @Test
    public void testBFSNullGraph() {
        String bfs = null;
        try {
            bfs = graphLibrary.BFS(null, 2);
        } catch (Exception e) {
            assertEquals("Can't perform BFS on null.",
                    e.getMessage());
        }
        assertTrue(bfs == null);
    }

    @Test
    public void testDFSNullGraph() {
        String dfs = null;
        try {
            dfs = graphLibrary.DFS(null, 2);
        } catch (Exception e) {
            assertEquals("Can't perform DFS on null.",
                    e.getMessage());
        }
        assertTrue(dfs == null);
    }

    @Test
    public void testBFSNullVertexGraph() {
        String bfs = null;
        try {
            bfs = graphLibrary.BFS(graph, null);
        } catch (Exception e) {
            assertEquals("Can't perform BFS using null vertex.",
                    e.getMessage());
        }
        assertTrue(bfs == null);
    }

    @Test
    public void BFSBigGraphListFormTest() {
        String outputBigGraphListForm = 0 + " - 0 -" + System.getProperty("line.separator");

        for (int v = 1; v < 80000; v++) {
            outputBigGraphListForm += v + " - " + v + " " + (v - 1) + System.getProperty("line.separator");
        }
        assertEquals(outputBigGraphListForm, graphLibrary.BFS(bigGraphListForm, 0));
    }

    @Test
    public void BFSBigGraphStarFormTest() {

        String outputBigGraphStarForm = -1 + "" + " - 0 -" + System.getProperty("line.separator");

        for (int v = 0; v < 79999; v++) {
            outputBigGraphStarForm += v + " - " + 1 + " " + (-1) + System.getProperty("line.separator");
        }

        assertEquals(outputBigGraphStarForm, graphLibrary.BFS(bigGraphStarForm, -1));
    }

    @Test
    public void BFSBigGraphDisconnectedTest() {
        String expectedOutput = "1 - 0 -" + System.getProperty("line.separator");

        assertEquals(expectedOutput, graphLibrary.BFS(bigGraphDisconnected, 1));
    }


    @Test
    public void testDFSNullVertexGraph() {
        String dfs = null;
        try {
            dfs = graphLibrary.DFS(graph, null);
        } catch (Exception e) {
            assertEquals("Can't perform DFS using null vertex.",
                    e.getMessage());
        }
        assertTrue(dfs == null);
    }

    @Test
    public void testBFSUnexistentVertexGraph() {
        String bfs = null;
        try {
            bfs = graphLibrary.BFS(graph, 51);
        } catch (Exception e) {
            assertEquals("The graph doesn't contains the specified vertex.",
                    e.getMessage());
        }
        assertTrue(bfs == null);
    }

    @Test
    public void testDFSUnexistentVertexGraph() {
        String dfs = null;
        try {
            dfs = graphLibrary.DFS(graph, 51);
        } catch (Exception e) {
            assertEquals("The graph doesn't contains the specified vertex.",
                    e.getMessage());
        }
        assertTrue(dfs == null);
    }


    @Test
    public void testBFSExistentVertexGraph() {
        String bfs = null;
        try {
            bfs = graphLibrary.BFS(graph, 1);
        } catch (Exception e) {
            assertTrue(e == null);
        }
        assertTrue(bfs != null);
    }

    @Test
    public void testDFSExistentVertexGraph() {
        String dfs = null;
        try {
            dfs = graphLibrary.DFS(graph, 1);
        } catch (Exception e) {
            assertTrue(e == null);
        }
        assertTrue(dfs != null);
    }

    @Test
    public void testMSTNullGraph() {
        String mst = null;
        try {
            mst = graphLibrary.MST(null);
        } catch (Exception e) {
            assertEquals("Can't perform MST on null.",
                    e.getMessage());
        }
        assertTrue(mst == null);
    }

    @Test
    public void testMSTGraph() {
        String mst = null;
        try {
            mst = graphLibrary.MST(graph);
        } catch (Exception e) {
            assertTrue(e == null);
        }
        assertTrue(mst != null);

        String expectedMST = new StringBuilder()
                .append("[1, 2]").append(LINE_SEPARATOR)
                .append("[1, 5]").append(LINE_SEPARATOR)
                .append("[3, 5]").append(LINE_SEPARATOR)
                .append("[4, 5]").append(LINE_SEPARATOR)
                .toString();

        assertEquals(expectedMST, mst);
    }
    
    @Test
    public void testMSTWeightedGraph() {
        String mst = null;
        try {
            mst = graphLibrary.MST(graphWeighted);
        } catch (Exception e) {
            assertTrue(e == null);
        }
        assertTrue(mst != null);
        
        String expectedMST = new StringBuilder()
                .append("[3, 5] : 3.2").append(LINE_SEPARATOR)
                .append("[2, 5] : 4.5").append(LINE_SEPARATOR)
                .append("[1, 2] : 5.3").append(LINE_SEPARATOR)
                .append("[4, 5] : 6.4").append(LINE_SEPARATOR)
                .toString();
        
        assertEquals(expectedMST, mst);
    }
    
    @Test
    public void testMSTDisconnectedWeightedGraph() {
        String mst = null;
        try {
        	graphWeightedDisconnected = graphLibrary.readWeightedGraph(disconnectedWeightedGraph);
            mst = graphLibrary.MST(graphWeighted);
        } catch (Exception e) {
            assertEquals("Can't perform MST on disconnected graph.",
                    e.getMessage());
        }
        assertTrue(mst == null);
    }
    
    @Test
    public void testMSTNoEdgesGraph() {
        String mst = null;
        try {
        	graphNoEdges = graphLibrary.readGraph(noEdgesGraph);
            mst = graphLibrary.MST(graphNoEdges);
        } catch (Exception e) {
            assertEquals("Can't perform MST on disconnected graph.",
                    e.getMessage());
        }
        assertTrue(mst == null);
    }

    @Test
    public void testRepresentationNullGraph() {
        String representation = null;
        try {
            representation = graphLibrary.graphRepresentation(null, RepresentationType.ADJACENCY_MATRIX);
        } catch (Exception e) {
            assertEquals("Can't perform an representation for null.",
                    e.getMessage());
        }
        assertTrue(representation == null);
    }

    @Test
    public void testRepresentationNull() {
        String representation = null;
        try {
            representation = graphLibrary.graphRepresentation(graph, null);
        } catch (Exception e) {
            assertEquals("The representation type is null.",
                    e.getMessage());
        }
        assertTrue(representation == null);
    }

    @Test
    public void testRepresentationAdjMatrix() {
        String representation = null;
        try {
            representation = graphLibrary.graphRepresentation(graph, RepresentationType.ADJACENCY_MATRIX);
        } catch (Exception e) {
            assertTrue(e == null);
        }

        String expectedMatrix = new StringBuilder()
                .append("  1 2 3 4 5").append(LINE_SEPARATOR)
                .append("1 0 1 0 0 1").append(LINE_SEPARATOR)
                .append("2 1 0 0 0 1").append(LINE_SEPARATOR)
                .append("3 0 0 0 0 1").append(LINE_SEPARATOR)
                .append("4 0 0 0 0 1").append(LINE_SEPARATOR)
                .append("5 1 1 1 1 0").append(LINE_SEPARATOR)
                .toString();

        assertTrue(representation != null);
        assertEquals(expectedMatrix, representation);
    }

    @Test
    public void stringGraphAMRepresentation() {
        String expectedOutput = "  A B C D E" + System.getProperty("line.separator") +
                "A 1 1 1 1 1" + System.getProperty("line.separator") +
                "B 1 0 0 0 1" + System.getProperty("line.separator") +
                "C 1 0 0 0 1" + System.getProperty("line.separator") +
                "D 1 0 0 0 1" + System.getProperty("line.separator") +
                "E 1 1 1 1 0" + System.getProperty("line.separator");

        assertEquals(expectedOutput, graphLibraryStr.graphRepresentation(graphStr, RepresentationType.ADJACENCY_MATRIX));
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
    public void sparseGraphAMRepresentationTest() {
        String sparseGraphOutput = "";
        String header = " ";
        String body = "";

        for (int v = 0; v < 100; v++) {
            header += " " + v;
            body += v;
            for (int v2 = 0; v2 < 100; v2++) {
                body += " " + 0;
            }
            body += System.getProperty("line.separator");
        }
        sparseGraphOutput += header + System.getProperty("line.separator") + body;

        assertEquals(sparseGraphOutput, graphLibrary.graphRepresentation(graphSparse, RepresentationType.ADJACENCY_MATRIX));
    }

    @Test
    public void testRepresentationAdjList() {
        String representation = null;
        try {
            representation = graphLibrary.graphRepresentation(graph, RepresentationType.ADJACENCY_LIST);
        } catch (Exception e) {
            assertTrue(e == null);
        }
        String expectedList = new StringBuilder()
                .append("1 - 2 5").append(LINE_SEPARATOR)
                .append("2 - 1 5").append(LINE_SEPARATOR)
                .append("3 - 5").append(LINE_SEPARATOR)
                .append("4 - 5").append(LINE_SEPARATOR)
                .append("5 - 1 2 3 4").append(LINE_SEPARATOR)
                .toString();

        assertTrue(representation != null);
        assertEquals(expectedList, representation);
    }

    /**
     * There are some possibilities: round the number of vertex, floor function, ceil function or throw
     * an exception. Change the below test according to whatever scenario you want to.
     * */
    @Test
    public void testReadGraphFromFileWithFloatNumberVertex() {
        try {
            graphFloatNumbVertex = graphLibrary.readGraph(floatNumbVertexGraph);
        } catch (Exception e) {
            assertTrue(e == null);
        }
        assertTrue(graphFloatNumbVertex != null);
        assertEquals(5, graphFloatNumbVertex.getVertexNumber());
    }

    @Test
    public void testReadGraphFromFileWithNameInsteadNumberVertex() {
        try {
            graphNameNumbVertex = graphLibrary.readGraph(nameNumbVertexGraph);
        } catch (Exception e) {
            assertEquals("The number of vertex in the file should be a number.",
                    e.getMessage());
        }
        assertTrue(graphNameNumbVertex == null);
    }

    // ---------------------------------- SHORTEST PATH ----------------------------------------------
    @Test
    public void graphDisconnectedShortestPathTest() {
        try {
            String path = graphLibrary.shortestPath(disconnectedGraphSP, 1, 2);
            fail("Should have thrown exception when trying to get the shortest path on a disconnected graph");
        } catch (Exception e) {
            assertEquals(("There isn't a path between " + 1
                    + " and " + 2), e.getMessage());
        }

        try {
            String path = graphLibrary.shortestPath(disconnectedGraphSP, 1, 3);
            fail("Should have thrown exception when trying to get the shortest path on a disconnected graph");
        } catch (Exception e) {
            assertEquals(("There isn't a path between " + 1
                    + " and " + 3), e.getMessage());
        }

        try {
            String path = graphLibrary.shortestPath(disconnectedGraphSP, 1, 4);
            fail("Should have thrown exception when trying to get the shortest path on a disconnected graph");
        } catch (Exception e) {
            assertEquals(("There isn't a path between " + 1
                    + " and " + 4), e.getMessage());
        }

        try {
            String path = graphLibrary.shortestPath(disconnectedGraphSP, 1, 5);
            fail("Should have thrown exception when trying to get the shortest path on a disconnected graph");
        } catch (Exception e) {
            assertEquals(("There isn't a path between " + 1
                    + " and " + 5), e.getMessage());
        }

        try {
            String path = graphLibrary.shortestPath(disconnectedGraphSP, 1, 1);
            assertEquals("1", path);
        } catch (Exception e) {
            fail("The shortest path of a vertex to itself, should be itself");
        }
    }

    @Test
    public void graphOfIntegersShortestPathTest() {
        String expectedPathBetween1And3 = "1 5 3";
        String expectedPathBetween1And4 = "1 5 4";
        String expectedPathBetween3And2 = "3 5 2";
        String expectedPathBetween5And2 = "5 2";
        String expectedPathBetween5And5 = "5";

        assertEquals(expectedPathBetween1And3, graphLibrary.shortestPath(integerGraph2SP,1, 3));
        assertEquals(expectedPathBetween1And4, graphLibrary.shortestPath(integerGraph2SP,1, 4));
        assertEquals(expectedPathBetween3And2, graphLibrary.shortestPath(integerGraph2SP,3, 2));
        assertEquals(expectedPathBetween5And2, graphLibrary.shortestPath(integerGraph2SP,5, 2));
        assertEquals(expectedPathBetween5And5, graphLibrary.shortestPath(integerGraph2SP,5, 5));
    }

    @Test
    void negativeWeightedGraphOfIntegersShortestPathTest() {
        try {
            graphLibrary.shortestPath(weightedIntegerGraphSP, 1,3);
            fail("Shouldn't be able to get the shortest path of a weighted graph with negative cicle");
        } catch (Exception e) {
            assertEquals("The shortest path cannot be found in a" +
                    " graph with negative circle.", e.getMessage());
        }
    }

    @Test
    void weightedGraphOfIntegersShortestPathTest() {
        String expectedPathBetween1And3 = "1 2 5 3";
        String expectedPathBetween1And4 = "1 2 5 4";
        String expectedPathBetween3And2 = "3 5 2";
        String expectedPathBetween5And2 = "5 2";
        String expectedPathBetween5And5 = "5";

        assertEquals(expectedPathBetween1And3, graphLibrary.shortestPath(weightedIntegerGraph2SP,1, 3));
        assertEquals(expectedPathBetween1And4, graphLibrary.shortestPath(weightedIntegerGraph2SP,1, 4));
        assertEquals(expectedPathBetween3And2, graphLibrary.shortestPath(weightedIntegerGraph2SP,3, 2));
        assertEquals(expectedPathBetween5And2, graphLibrary.shortestPath(weightedIntegerGraph2SP,5, 2));
        assertEquals(expectedPathBetween5And5, graphLibrary.shortestPath(weightedIntegerGraph2SP,5, 5));
    }

    @Test
    public void graphOfStringsShortestPathTest2() {
        String expectedPathBetweenAAndE = "A E";
        String expectedPathBetweenAAndC = "A E C";
        String expectedPathBetweenDAndB = "D E B";
        String expectedPathBetweenEAndA = "E A";
        String expectedPathBetweenDAndD = "D";

        assertEquals(expectedPathBetweenAAndE, graphLibraryStr.shortestPath(stringGraphSP,"A", "E"));
        assertEquals(expectedPathBetweenAAndC, graphLibraryStr.shortestPath(stringGraphSP,"A", "C"));
        assertEquals(expectedPathBetweenDAndB, graphLibraryStr.shortestPath(stringGraphSP,"D", "B"));
        assertEquals(expectedPathBetweenEAndA, graphLibraryStr.shortestPath(stringGraphSP,"E", "A"));
        assertEquals(expectedPathBetweenDAndD, graphLibraryStr.shortestPath(stringGraphSP,"D", "D"));
    }

    @Test
    void negativeWeightedGraphOfStringsShortestPathTest() {
        try {
            graphLibraryStr.shortestPath(weightedStringGraphSP, "A", "E");
            fail("Shouldn't be able to get the shortest path of a weighted graph with negative cicle");
        } catch (Exception e) {
            assertEquals("The shortest path cannot be found in a" +
                    " graph with negative circle.", e.getMessage());
        }
    }

    @Test
    void graphOfStringsShortestPathTestWithNonExistingVertex() {
        try {
            graphLibraryStr.shortestPath(weightedStringGraphSP, "A", "H");
            fail("Shouldn't be able to get the shortest path for a vertex that doesn't exist");
        } catch (Exception e) {
            assertEquals("The graph doesn't contains both specified vertexes.", e.getMessage());
        }

        try {
            graphLibraryStr.shortestPath(stringGraphSP, "A", "H");
            fail("Shouldn't be able to get the shortest path for a vertex that doesn't exist");
        } catch (Exception e) {
            assertEquals("The graph doesn't contains both specified vertexes.", e.getMessage());
        }
    }

    @Test
    void graphOfIntegersShortestPathTestWithNonExistingVertex() {
        try {
            graphLibrary.shortestPath(weightedIntegerGraphSP, 1, 8);
            fail("Shouldn't be able to get the shortest path for a vertex that doesn't exist");
        } catch (Exception e) {
            assertEquals("The graph doesn't contains both specified vertexes.", e.getMessage());
        }

        try {
            graphLibrary.shortestPath(integerGraphSP, 1, 8);
            fail("Shouldn't be able to get the shortest path for a vertex that doesn't exist");
        } catch (Exception e) {
            assertEquals("The graph doesn't contains both specified vertexes.", e.getMessage());
        }

        try {
            graphLibrary.shortestPath(integerGraph2SP, 1, 8);
            fail("Shouldn't be able to get the shortest path for a vertex that doesn't exist");
        } catch (Exception e) {
            assertEquals("The graph doesn't contains both specified vertexes.", e.getMessage());
        }
    }

    @Test
    public void graphOfIntegersShortestPathTest2() {
        String expectedPathBetween1And5 = "1 2 3 4 5";

        assertEquals(expectedPathBetween1And5, integerGraph3SP.shortestPath(1, 5));
    }

    @Test
    public void weightedGraphOfIntegersShortestPathTest2() {
        String expectedPathBetween1And5 = "1 2 3 4 5";

        assertEquals(expectedPathBetween1And5, weightedIntegerGraph3SP.shortestPath(1, 5));
    }

    @Test
    public void graphOfStringsShortestPathTest() {
        String expectedPathBetweenAAndE = "A B C D E";

        assertEquals(expectedPathBetweenAAndE, stringGraph2SP.shortestPath("A", "E"));
    }

    @Test
    public void weightedGraphOfStringsShortestPathTest() {
        String expectedPathBetweenAAndE = "A B C D E";

        assertEquals(expectedPathBetweenAAndE, weightedStringGraph2SP.shortestPath("A", "E"));
    }

    // ---------------------------------- SHORTEST PATH END ------------------------------------------------

    // ---------------------------------- GRAPH REPRESENTATION ----------------------------------------------
    @Test
    public void graphDisconnetecListRepresentationTest() {
        String expectedList = new StringBuilder()
                .append("1 - ").append(LINE_SEPARATOR)
                .append("2 - ").append(LINE_SEPARATOR)
                .append("3 - ").append(LINE_SEPARATOR)
                .append("4 - ").append(LINE_SEPARATOR)
                .append("5 - ").append(LINE_SEPARATOR)
                .toString();

        String list = graphLibrary.graphRepresentation(disconnectedGraphRep, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    @Test
    public void graphOfIntegersMatrixRepresentationTest() {
        String expectedMatrix = new StringBuilder()
                .append("  1 2 3 4 5 6").append(LINE_SEPARATOR)
                .append("1 0 1 0 1 1 0").append(LINE_SEPARATOR)
                .append("2 1 0 1 0 1 1").append(LINE_SEPARATOR)
                .append("3 0 1 0 1 0 1").append(LINE_SEPARATOR)
                .append("4 1 0 1 0 1 0").append(LINE_SEPARATOR)
                .append("5 1 1 0 1 0 1").append(LINE_SEPARATOR)
                .append("6 0 1 1 0 1 0").append(LINE_SEPARATOR)
                .toString();

        String matrix = graphLibrary.graphRepresentation(integerGraphRep, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedMatrix, matrix);
    }

    @Test
    public void graphOfIntegersMatrixRepresentationTest2() {
        String expectedMatrix = new StringBuilder()
                .append("  1 2 3 4 5").append(LINE_SEPARATOR)
                .append("1 0 1 0 0 1").append(LINE_SEPARATOR)
                .append("2 1 0 0 0 1").append(LINE_SEPARATOR)
                .append("3 0 0 0 0 1").append(LINE_SEPARATOR)
                .append("4 0 0 0 0 1").append(LINE_SEPARATOR)
                .append("5 1 1 1 1 0").append(LINE_SEPARATOR)
                .toString();

        String matrix = graphLibrary.graphRepresentation(integerGraph2Rep, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedMatrix, matrix);
    }

    @Test
    public void graphOfStringsMatrixRepresentationTest() {
        String expectedMatrix = new StringBuilder()
                .append("  A B C D E").append(LINE_SEPARATOR)
                .append("A 0 1 0 0 1").append(LINE_SEPARATOR)
                .append("B 1 0 0 0 1").append(LINE_SEPARATOR)
                .append("C 0 0 0 0 1").append(LINE_SEPARATOR)
                .append("D 0 0 0 0 1").append(LINE_SEPARATOR)
                .append("E 1 1 1 1 0").append(LINE_SEPARATOR)
                .toString();

        String matrix = graphLibraryStr.graphRepresentation(stringGraphRep, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedMatrix, matrix);
    }

    @Test
    public void graphDisconnetedMatrixRepresentationTest() {
        String expectedMatrix = new StringBuilder()
                .append("  1 2 3 4 5").append(LINE_SEPARATOR)
                .append("1 0 0 0 0 0").append(LINE_SEPARATOR)
                .append("2 0 0 0 0 0").append(LINE_SEPARATOR)
                .append("3 0 0 0 0 0").append(LINE_SEPARATOR)
                .append("4 0 0 0 0 0").append(LINE_SEPARATOR)
                .append("5 0 0 0 0 0").append(LINE_SEPARATOR)
                .toString();

        String matrix = graphLibrary.graphRepresentation(disconnectedGraphRep, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedMatrix, matrix);
    }

    @Test
    public void graphOfIntegersListRepresentationTest() {
        String expectedList = new StringBuilder()
                .append("1 - 2 4 5").append(LINE_SEPARATOR)
                .append("2 - 1 3 5 6").append(LINE_SEPARATOR)
                .append("3 - 2 4 6").append(LINE_SEPARATOR)
                .append("4 - 1 3 5").append(LINE_SEPARATOR)
                .append("5 - 1 2 4 6").append(LINE_SEPARATOR)
                .append("6 - 2 3 5").append(LINE_SEPARATOR)
                .toString();

        String list = graphLibrary.graphRepresentation(integerGraphRep, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    @Test
    public void graphOfIntegersListRepresentationTest2() {
        String expectedList = new StringBuilder()
                .append("1 - 2 5").append(LINE_SEPARATOR)
                .append("2 - 1 5").append(LINE_SEPARATOR)
                .append("3 - 5").append(LINE_SEPARATOR)
                .append("4 - 5").append(LINE_SEPARATOR)
                .append("5 - 1 2 3 4").append(LINE_SEPARATOR)
                .toString();

        String list = graphLibrary.graphRepresentation(integerGraph2Rep, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    @Test
    public void graphOfStringsListRepresentationTest() {
        String expectedList = new StringBuilder()
                .append("A - B E").append(LINE_SEPARATOR)
                .append("B - A E").append(LINE_SEPARATOR)
                .append("C - E").append(LINE_SEPARATOR)
                .append("D - E").append(LINE_SEPARATOR)
                .append("E - A B C D").append(LINE_SEPARATOR)
                .toString();

        String list = graphLibraryStr.graphRepresentation(stringGraphRep, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    @Test
    public void weightedGraphOfIntegersMatrixRepresentationTest() {
        String expectedMatrix = new StringBuilder()
                .append("  1 2 3 4 5").append(LINE_SEPARATOR)
                .append("1 0 0.1 0 0 1").append(LINE_SEPARATOR)
                .append("2 0.1 0 0 0 0.2").append(LINE_SEPARATOR)
                .append("3 0 0 0 -9.5 5").append(LINE_SEPARATOR)
                .append("4 0 0 -9.5 0 2.3").append(LINE_SEPARATOR)
                .append("5 1 0.2 5 2.3 0").append(LINE_SEPARATOR)
                .toString();

        String matrix = graphLibrary.graphRepresentation(weightedIntegerGraphRep, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedMatrix, matrix);
    }

    @Test
    public void weightedGraphOfStringsMatrixRepresentationTest() {
        String expectedMatrix = new StringBuilder()
                .append("  A B C D E").append(LINE_SEPARATOR)
                .append("A 0 0.1 0 0 1").append(LINE_SEPARATOR)
                .append("B 0.1 0 0 0 0.2").append(LINE_SEPARATOR)
                .append("C 0 0 0 -9.5 5").append(LINE_SEPARATOR)
                .append("D 0 0 -9.5 0 2.3").append(LINE_SEPARATOR)
                .append("E 1 0.2 5 2.3 0").append(LINE_SEPARATOR)
                .toString();

        String matrix = graphLibraryStr.graphRepresentation(weightedStringGraphRep, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedMatrix, matrix);
    }

    @Test
    public void weightedGraphOfIntegersListRepresentationTest() {
        String expectedList = new StringBuilder()
                .append("1 - 2(0.1) 5(1)").append(LINE_SEPARATOR)
                .append("2 - 1(0.1) 5(0.2)").append(LINE_SEPARATOR)
                .append("3 - 4(-9.5) 5(5)").append(LINE_SEPARATOR)
                .append("4 - 3(-9.5) 5(2.3)").append(LINE_SEPARATOR)
                .append("5 - 1(1) 2(0.2) 3(5) 4(2.3)").append(LINE_SEPARATOR)
                .toString();

        String list = graphLibrary.graphRepresentation(weightedIntegerGraphRep, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    @Test
    public void weightedGraphOfStringsListRepresentationTest() {
        String expectedList = new StringBuilder()
                .append("A - B(0.1) E(1)").append(LINE_SEPARATOR)
                .append("B - A(0.1) E(0.2)").append(LINE_SEPARATOR)
                .append("C - D(-9.5) E(5)").append(LINE_SEPARATOR)
                .append("D - C(-9.5) E(2.3)").append(LINE_SEPARATOR)
                .append("E - A(1) B(0.2) C(5) D(2.3)").append(LINE_SEPARATOR)
                .toString();

        String list = graphLibraryStr.graphRepresentation(weightedStringGraphRep, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    @Test
    public void largeGraphOfIntegersListRepresentationTest() {
        StringBuilder expectedListBuilder = new StringBuilder();
        for (int i = 1; i <= LARGE_GRAPH_SIZE_REP; i++) {
            expectedListBuilder.append(i + " -");
            for (int j = 1; j <= LARGE_GRAPH_SIZE_REP; j++) {
                if (i != j) {
                    expectedListBuilder.append(" " + j);
                }
            }
            expectedListBuilder.append(LINE_SEPARATOR);
        }

        String expectedList = expectedListBuilder.toString();

        String list = graphLibrary.graphRepresentation(largeIntegerGraphRep, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    @Test
    public void largeGraphOfIntegersMatrixRepresentationTest() {
        StringBuilder expectedMatrixBuilder = new StringBuilder();
        expectedMatrixBuilder.append(" ");
        for (int i = 1; i <= LARGE_GRAPH_SIZE_REP; i++) {
            expectedMatrixBuilder.append(" " + i);
        }
        expectedMatrixBuilder.append(LINE_SEPARATOR);

        for (int i = 1; i <= LARGE_GRAPH_SIZE_REP; i++) {
            expectedMatrixBuilder.append(i);
            for (int j = 1; j <= LARGE_GRAPH_SIZE_REP; j++) {
                if (i != j) {
                    expectedMatrixBuilder.append(" 1");
                } else {
                    expectedMatrixBuilder.append(" 0");
                }
            }
            expectedMatrixBuilder.append(LINE_SEPARATOR);
        }

        String expectedMatrix = expectedMatrixBuilder.toString();

        String matrix = graphLibrary.graphRepresentation(largeIntegerGraphRep, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedMatrix, matrix);
    }

    @Test
    public void largeWeightedGraphOfIntegersListRepresentationTest() {
        StringBuilder expectedListBuilder = new StringBuilder();
        for (int i = 1; i <= LARGE_GRAPH_SIZE_REP; i++) {
            expectedListBuilder.append(i + " -");
            for (int j = 1; j <= LARGE_GRAPH_SIZE_REP; j++) {
                if (i != j) {
                    expectedListBuilder.append(" " + j + "(" + Utils.floatToString((i+j)/2f) + ")");
                }
            }
            expectedListBuilder.append(LINE_SEPARATOR);
        }

        String expectedList = expectedListBuilder.toString();

        String list = graphLibrary.graphRepresentation(largeWeightedIntegerGraphRep, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    @Test
    public void largeWeightedGraphOfIntegersMatrixRepresentationTest() {
        StringBuilder expectedMatrixBuilder = new StringBuilder();

        expectedMatrixBuilder.append(" ");
        for (int i = 1; i <= LARGE_GRAPH_SIZE_REP; i++) {
            expectedMatrixBuilder.append(" " + i);
        }
        expectedMatrixBuilder.append(LINE_SEPARATOR);

        for (int i = 1; i <= LARGE_GRAPH_SIZE_REP; i++) {
            expectedMatrixBuilder.append(i);
            for (int j = 1; j <= LARGE_GRAPH_SIZE_REP; j++) {
                if (i != j) {
                    expectedMatrixBuilder.append(" " + Utils.floatToString((i+j)/2f));
                } else {
                    expectedMatrixBuilder.append(" 0");
                }
            }
            expectedMatrixBuilder.append(LINE_SEPARATOR);
        }

        String expectedMatrix = expectedMatrixBuilder.toString();

        String matrix = graphLibrary.graphRepresentation(largeWeightedIntegerGraphRep, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedMatrix, matrix);
    }

    @Test
    public void largeGraphOfStringsListRepresentationTest() {
        StringBuilder expectedListBuilder = new StringBuilder();
        SortedSet<String> set = new TreeSet<>();

        for (int i = 1; i <= LARGE_GRAPH_SIZE_REP; i++) {
            set.add(Integer.toHexString(i));
        }

        for (String s : set) {
            expectedListBuilder.append(s + " -");
            for (String s2 : set) {
                if (!s.equals(s2)) {
                    expectedListBuilder.append(" " + s2);
                }
            }
            expectedListBuilder.append(LINE_SEPARATOR);
        }

        String expectedList = expectedListBuilder.toString();
        String list = graphLibraryStr.graphRepresentation(largeStringGraphRep, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    @Test
    public void largeGraphOfStringsMatrixRepresentationTest() {
        StringBuilder expectedMatrixBuilder = new StringBuilder();
        SortedSet<String> set = new TreeSet<>();

        for (int i = 1; i <= LARGE_GRAPH_SIZE_REP; i++) {
            set.add(Integer.toHexString(i));
        }

        expectedMatrixBuilder.append(" ");
        for (String s : set) {
            expectedMatrixBuilder.append(" " + s);
        }
        expectedMatrixBuilder.append(LINE_SEPARATOR);

        for (String s : set) {
            expectedMatrixBuilder.append(s);
            for (String s2 : set) {
                if (s.equals(s2)) {
                    expectedMatrixBuilder.append(" 0");
                } else {
                    expectedMatrixBuilder.append(" 1");
                }
            }
            expectedMatrixBuilder.append(LINE_SEPARATOR);
        }

        String expectedMatrix = expectedMatrixBuilder.toString();
        String matrix = graphLibraryStr.graphRepresentation(largeStringGraphRep, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedMatrix, matrix);
    }

    @Test
    public void largeWeightedGraphOfStringsListRepresentationTest() {
        StringBuilder expectedListBuilder = new StringBuilder();
        SortedSet<String> set = new TreeSet<>();

        for (int i = 1; i <= LARGE_GRAPH_SIZE_REP; i++) {
            set.add(Integer.toHexString(i));
        }

        for (String s : set) {
            expectedListBuilder.append(s + " -");
            for (String s2 : set) {
                if (!s.equals(s2)) {
                    expectedListBuilder.append(" " + s2 + "(" + Utils.floatToString((Integer.parseInt(s, 16) + Integer.parseInt(s2, 16))/2f) + ")");
                }
            }
            expectedListBuilder.append(LINE_SEPARATOR);
        }

        String expectedList = expectedListBuilder.toString();
        String list = graphLibraryStr.graphRepresentation(largeWeightedStringGraphRep, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    @Test
    public void largeWeightedGraphOfStringsMatrixRepresentationTest() {
        StringBuilder expectedMatrixBuilder = new StringBuilder();
        SortedSet<String> set = new TreeSet<>();

        for (int i = 1; i <= LARGE_GRAPH_SIZE_REP; i++) {
            set.add(Integer.toHexString(i));
        }

        expectedMatrixBuilder.append(" ");
        for (String s : set) {
            expectedMatrixBuilder.append(" " + s);
        }
        expectedMatrixBuilder.append(LINE_SEPARATOR);

        for (String s : set) {
            expectedMatrixBuilder.append(s);
            for (String s2 : set) {
                if (s.equals(s2)) {
                    expectedMatrixBuilder.append(" 0");
                } else {
                    expectedMatrixBuilder.append(" " + Utils.floatToString((Integer.parseInt(s, 16) + Integer.parseInt(s2, 16))/2f));
                }
            }
            expectedMatrixBuilder.append(LINE_SEPARATOR);
        }

        String expectedMatrix = expectedMatrixBuilder.toString();
        String matrix = graphLibraryStr.graphRepresentation(largeWeightedStringGraphRep, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedMatrix, matrix);
    }

    // ---------------------------------- GRAPH REPRESENTATION END ------------------------------------------------
}