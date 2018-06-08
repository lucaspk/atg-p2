package com.ufcg.atg.library;

import com.ufcg.atg.graph.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Created by Anderson on 5/29/2018.
 */
public class ShortestPathTest {

    private GraphLibrary<Integer> graphLibrary;
    private GraphLibrary<String> graphLibraryStr;
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

    /**
     * Tests' set up.
     */
    @BeforeEach
    public void setUp() {
        graphLibrary = new GraphLibrary<>();
        graphLibraryStr = new GraphLibrary<>();
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
    }

    /**
     * Constructs a large {@link Graph} with vertices of integer value.
     */
    private void setUpGraphOfIntegers3SP() {
        integerGraph3SP = new Graph<>();
        integerGraph3SP.addEdge(1, 2);
        integerGraph3SP.addEdge(2, 3);
        integerGraph3SP.addEdge(3, 4);
        integerGraph3SP.addEdge(4, 5);
    }

    /**
     * Constructs a {@link Graph} with vertices of string value.
     */
    private void setUpLargeGraphOfStringsSP() {
        stringGraph2SP = new Graph<>();

        stringGraph2SP.addEdge("A", "B");
        stringGraph2SP.addEdge("B", "C");
        stringGraph2SP.addEdge("C", "D");
        stringGraph2SP.addEdge("D", "E");
    }

    /**
     * Constructs a weighted graph with vertices of integer value.
     */
    private void setUpLargeWeightedGraphOfIntegersSP() {
        weightedIntegerGraph3SP = new WeightedGraph<>();

        weightedIntegerGraph3SP.addEdge(1, 2, 1);
        weightedIntegerGraph3SP.addEdge(2, 3, 1);
        weightedIntegerGraph3SP.addEdge(3, 4, 1);
        weightedIntegerGraph3SP.addEdge(4, 5, 1);
    }

    /**
     * Constructs a weighted graph with vertices of string value.
     */
    private void setUpLargeWeightedGraphOfStringsSP() {
        weightedStringGraph2SP = new WeightedGraph<>();

        weightedStringGraph2SP.addEdge("A", "B", 1);
        weightedStringGraph2SP.addEdge("B", "C", 1);
        weightedStringGraph2SP.addEdge("C", "D", 1);
        weightedStringGraph2SP.addEdge("D", "E", 1);
    }

    /**
     * Constructs a {@link Graph} with vertices of integer duplicate value.
     */
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

    /**
     * Constructs a {@link Graph} with vertices of integer value.
     */
    private void setUpGraphOfIntegers2SP() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
        integerGraph2SP = new Graph<>();
        integerGraph2SP.addEdge(i1, i2);
        integerGraph2SP.addEdge(i2, i5);
        integerGraph2SP.addEdge(i5, i3);
        integerGraph2SP.addEdge(i4, i5);
        integerGraph2SP.addEdge(i1, i5);
    }

    /**
     * Constructs a {@link Graph} with vertices of string value.
     */
    private void setUpGraphOfStringsSP() {
        String s1 = "A", s2 = "B", s3 = "C", s4 = "D", s5 = "E";
        stringGraphSP = new Graph<>();
        stringGraphSP.addEdge(s1, s2);
        stringGraphSP.addEdge(s2, s5);
        stringGraphSP.addEdge(s5, s3);
        stringGraphSP.addEdge(s4, s5);
        stringGraphSP.addEdge(s1, s5);
    }

    /**
     * Constructs a disconnected {@link Graph} with vertices of integer value.
     */
    private void setUpDisconnectedGraphSP() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
        disconnectedGraphSP = new Graph<>();
        disconnectedGraphSP.addVertex(i1);
        disconnectedGraphSP.addVertex(i2);
        disconnectedGraphSP.addVertex(i3);
        disconnectedGraphSP.addVertex(i4);
        disconnectedGraphSP.addVertex(i5);
    }

    /**
     * Constructs a weighted graph with vertices of integer value.
     */
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

    /**
     * Constructs a weighted graph with vertices of integer value.
     */
    private void setUpWeightedGraphOfIntegers2SP() {
        weightedIntegerGraph2SP = new WeightedGraph<>();
        weightedIntegerGraph2SP.addEdge(1, 2, 0.1f);
        weightedIntegerGraph2SP.addEdge(2, 5, 0.2f);
        weightedIntegerGraph2SP.addEdge(5, 3, 5f);
        weightedIntegerGraph2SP.addEdge(4, 5, 2.3f);
        weightedIntegerGraph2SP.addEdge(1, 5,1f);
    }

    /**
     * Constructs a weighted graph with vertices of string value.
     */
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

    /**
     * Tests the shortest path of a integer's disconnected {@link Graph}.
     */
    @Test
    public void graphDisconnetecListRepresentationTest() {
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

    /**
     * Tests the shortest path between two vertexes at {@link Graph}.
     */
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

    /**
     * Tests the shortest path between two vertexes at {@link WeightedGraph}.
     */
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

    /**
     * Tests the shortest path between two vertexes at {@link WeightedGraph}.
     */
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

    /**
     * Tests the shortest path between two vertexes of a string's {@link Graph}.
     */
    @Test
    public void graphOfStringsMatrixRepresentationTest() {
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

    /**
     * Tests the shortest path between two vertexes at {@link WeightedGraph}.
     */
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

    /**
     * Tests the shortest path between two vertexes at {@link WeightedGraph}.
     */
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

    /**
     * Tests the shortest path between two vertexes at {@link WeightedGraph}.
     */
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

    /**
     * Tests the shortest path between two vertexes at {@link Graph}.
     */
    @Test
    public void graphOfIntegersShortestPathTest2() {
        String expectedPathBetween1And5 = "1 2 3 4 5";

        assertEquals(expectedPathBetween1And5, integerGraph3SP.shortestPath(1, 5));
    }

    /**
     * Tests the shortest path between two vertexes at {@link Graph}.
     */
    @Test
    public void weightedGraphOfIntegersShortestPathTest2() {
        String expectedPathBetween1And5 = "1 2 3 4 5";

        assertEquals(expectedPathBetween1And5, weightedIntegerGraph3SP.shortestPath(1, 5));
    }

    /**
     * Tests the shortest path between two vertexes at {@link Graph}.
     */
    @Test
    public void graphOfStringsShortestPathTest() {
        String expectedPathBetweenAAndE = "A B C D E";

        assertEquals(expectedPathBetweenAAndE, stringGraph2SP.shortestPath("A", "E"));
    }

    /**
     * Tests the shortest path between two vertexes at {@link Graph}.
     */
    @Test
    public void weightedGraphOfStringsShortestPathTest() {
        String expectedPathBetweenAAndE = "A B C D E";

        assertEquals(expectedPathBetweenAAndE, weightedStringGraph2SP.shortestPath("A", "E"));
    }
}
