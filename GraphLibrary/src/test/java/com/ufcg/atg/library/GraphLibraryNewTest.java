package com.ufcg.atg.library;

import com.ufcg.atg.graph.Edge;
import com.ufcg.atg.graph.IGraph;
import com.ufcg.atg.graph.IWeightedGraph;
import com.ufcg.atg.graph.WeightedEdge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Jobson Silva.
 */
class GraphLibraryNewTest {
    private final float EDGE_DEFAULT_WEIGHT = 1f;
    private GraphLibrary<Integer> graphLibrary;

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


    @BeforeEach
    public void setUp() {
        graphLibrary = new GraphLibrary<>();

        graph = graphLibrary.readGraph(sampleGraph);
        graphWeighted = graphLibrary.readWeightedGraph(sampleWeightedGraph);
        graphBlank = graphLibrary.readGraph(sampleGraphWithBlankLine);
        graphDuplicatedEdge = graphLibrary.readGraph(duplicatedEdgeGraph);

//        graphVertexLoop = graphLibrary.readGraph(vertexLoopGraph);

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
    public void testContainsVertexNullUnweightedGraph() {

        assertEquals(false, graphLibrary.containsVertex(graph, null));
    }


    @Test
    public void testGetEdgeMeanFromUnweightedGraph() {

        assertEquals(2.0f, graphLibrary.getMeanEdge(graph));

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
    public void testAddEdgeUnweightedGraph() {
        graphLibrary.addEdge(graph, 1, 6);

        assertEquals(true, graphLibrary.containsEdge(graph,
                new Edge<>(1, 6)));
    }

    @Test
    public void testAddNullEdgeUnweightedGraph() {
        graphLibrary.addEdge(graph, null, null);
        graphLibrary.addEdge(graph, 1, null);
        graphLibrary.addEdge(graph, null, 1);

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


    @Test
    public void testSuccessFulReadingGraphFromFileWithVertexLoop() {
        try {
            graphIncompleteEdge = graphLibrary.readGraph(incompleteEdgeGraph);
        } catch (Exception e) {
            assertTrue(e == null);
        }

        boolean isSuccessfullReading = graphIncompleteEdge != null;
        assertTrue(isSuccessfullReading);

    }

    @Test
    public void testVertexNumberUnweightedGraphWithVertexLoop() {

        try {
            graphIncompleteEdge = graphLibrary.readGraph(incompleteEdgeGraph);
        } catch (Exception e) {
            assertTrue(e == null);
        }

        assertEquals(5, graphLibrary.getVertexNumber(graphIncompleteEdge));
    }

    @Test
    public void testEdgeNumberUnweightedGraphWithVertexLoop() {

        try {
            graphIncompleteEdge = graphLibrary.readGraph(incompleteEdgeGraph);
        } catch (Exception e) {
            assertTrue(e == null);
        }

        assertEquals(4, graphLibrary.getEdgeNumber(graphIncompleteEdge));
    }
}