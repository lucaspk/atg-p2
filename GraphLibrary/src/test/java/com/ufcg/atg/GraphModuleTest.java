package com.ufcg.atg;

import com.ufcg.atg.graph.*;
import com.ufcg.atg.library.GraphLibrary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.ufcg.atg.util.Utils.LINE_SEPARATOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Jobson Silva, Jose Breno
 *
 */
class GraphModuleTest {
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

}