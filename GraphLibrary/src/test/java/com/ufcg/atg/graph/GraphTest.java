package com.ufcg.atg.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.ufcg.atg.util.Utils.LINE_SEPARATOR;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the class {@link Graph} as an implementation of {@link IGraph}.
 */
public class GraphTest {

    private IGraph<Integer, Edge<Integer>> integerGraph;
    private IGraph<Integer, Edge<Integer>> integerGraphDuplicate;
    private IGraph<String, Edge<String>> stringGraph;
    private IGraph<Integer, Edge<Integer>> disconnectedGraph;

    /**
     * Tests' set up.
     */
    @BeforeEach
    public void setUp() {
        setUpGraphOfIntegers();
        setUpGraphOfStrings();
        setUpDisconnectedGraph();
        setUpGraphOfIntegersDuplicate();
    }

    /**
     * Constructs a {@link Graph} with vertices of integer value.
     */
    private void setUpGraphOfIntegers() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
        integerGraph = new Graph<>();
        integerGraph.addEdge(i1, i2);
        integerGraph.addEdge(i2, i5);
        integerGraph.addEdge(i5, i3);
        integerGraph.addEdge(i4, i5);
        integerGraph.addEdge(i1, i5);
    }

    /**
     * Constructs a {@link Graph} with vertices of integer duplicate value.
     */
    private void setUpGraphOfIntegersDuplicate() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5, i6 = 6;
        integerGraphDuplicate = new Graph<>();
        integerGraphDuplicate.addEdge(i1, i2);
        integerGraphDuplicate.addEdge(i1, i4);
        integerGraphDuplicate.addEdge(i1, i5);
        integerGraphDuplicate.addEdge(i2, i3);
        integerGraphDuplicate.addEdge(i2, i6);
        integerGraphDuplicate.addEdge(i2, i5);
        integerGraphDuplicate.addEdge(i3, i4);
        integerGraphDuplicate.addEdge(i3, i6);
        integerGraphDuplicate.addEdge(i4, i5);
        integerGraphDuplicate.addEdge(i5, i6);
    }

    /**
     * Constructs a {@link Graph} with vertices of string value.
     */
    private void setUpGraphOfStrings() {
        String s1 = "A", s2 = "B", s3 = "C", s4 = "D", s5 = "E";
        stringGraph = new Graph<>();
        stringGraph.addEdge(s1, s2);
        stringGraph.addEdge(s2, s5);
        stringGraph.addEdge(s5, s3);
        stringGraph.addEdge(s4, s5);
        stringGraph.addEdge(s1, s5);
    }

    /**
     * Constructs a disconnected {@link Graph} with vertices of integer value.
     */
    private void setUpDisconnectedGraph() {
    	Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
    	disconnectedGraph = new Graph<>();
    	disconnectedGraph.addVertex(i1);
    	disconnectedGraph.addVertex(i2);
    	disconnectedGraph.addVertex(i3);
    	disconnectedGraph.addVertex(i4);
    	disconnectedGraph.addVertex(i5);
    }

    /**
     * Tests the adjacency matrix representation of a {@link Graph} with
     * duplicated integers.
     */
    @Test
    public void graphOfIntegersDuplicateMatrixRepresentationTest() {
        String expectedMatrix = new StringBuilder()
                .append("  1 2 3 4 5 6").append(LINE_SEPARATOR)
                .append("1 0 1 0 1 1 0").append(LINE_SEPARATOR)
                .append("2 1 0 1 0 1 1").append(LINE_SEPARATOR)
                .append("3 0 1 0 1 0 1").append(LINE_SEPARATOR)
                .append("4 1 0 1 0 1 0").append(LINE_SEPARATOR)
                .append("5 1 1 0 1 0 1").append(LINE_SEPARATOR)
                .append("6 0 1 1 0 1 0").append(LINE_SEPARATOR)
                .toString();

        String matrix = integerGraphDuplicate.graphRepresentation(RepresentationType.ADJACENCY_MATRIX);
        assertEquals(matrix, expectedMatrix);
    }

    /**
     * Tests the adjacency matrix representation of a integer's {@link Graph}.
     */
    @Test
    public void graphOfIntegersMatrixRepresentationTest() {
        String expectedMatrix = new StringBuilder()
                .append("  1 2 3 4 5").append(LINE_SEPARATOR)
                .append("1 0 1 0 0 1").append(LINE_SEPARATOR)
                .append("2 1 0 0 0 1").append(LINE_SEPARATOR)
                .append("3 0 0 0 0 1").append(LINE_SEPARATOR)
                .append("4 0 0 0 0 1").append(LINE_SEPARATOR)
                .append("5 1 1 1 1 0").append(LINE_SEPARATOR)
                .toString();

        String matrix = integerGraph.graphRepresentation(RepresentationType.ADJACENCY_MATRIX);
        assertEquals(matrix, expectedMatrix);
    }

    /**
     * Tests the adjacency matrix representation of a string's {@link Graph}.
     */
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

        String matrix = stringGraph.graphRepresentation(RepresentationType.ADJACENCY_MATRIX);
        assertEquals(matrix, expectedMatrix);
    }

    /**
     * Tests the adjacency matrix representation of a integer's  disconnected {@link Graph}.
     */
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

        String matrix = disconnectedGraph.graphRepresentation(RepresentationType.ADJACENCY_MATRIX);
        assertEquals(matrix, expectedMatrix);
    }

    /**
     * Tests the adjacency list representation of a string's {@link Graph}
     * with duplicated strings.
     */
    @Test
    public void graphOfIntegersDuplicateListRepresentationTest() {
        String expectedList = new StringBuilder()
                .append("1 - 2 4 5").append(LINE_SEPARATOR)
                .append("2 - 1 3 5 6").append(LINE_SEPARATOR)
                .append("3 - 2 4 6").append(LINE_SEPARATOR)
                .append("4 - 1 3 5").append(LINE_SEPARATOR)
                .append("5 - 1 2 4 6").append(LINE_SEPARATOR)
                .append("6 - 2 3 5").append(LINE_SEPARATOR)
                .toString();

        String list = integerGraphDuplicate.graphRepresentation(RepresentationType.ADJACENCY_LIST);
        assertEquals(list, expectedList);
    }

    /**
     * Tests the adjacency list representation of a integer's  {@link Graph}.
     */
    @Test
    public void graphOfIntegersListRepresentationTest() {
        String expectedList = new StringBuilder()
                .append("1 - 2 5").append(LINE_SEPARATOR)
                .append("2 - 1 5").append(LINE_SEPARATOR)
                .append("3 - 5").append(LINE_SEPARATOR)
                .append("4 - 5").append(LINE_SEPARATOR)
                .append("5 - 1 2 3 4").append(LINE_SEPARATOR)
                .toString();

        String list = integerGraph.graphRepresentation(RepresentationType.ADJACENCY_LIST);
        assertEquals(list, expectedList);
    }

    /**
     * Tests the adjacency list representation of a string's  {@link Graph}.
     */
    @Test
    public void graphOfStringsListRepresentationTest() {
        String expectedList = new StringBuilder()
                .append("A - B E").append(LINE_SEPARATOR)
                .append("B - A E").append(LINE_SEPARATOR)
                .append("C - E").append(LINE_SEPARATOR)
                .append("D - E").append(LINE_SEPARATOR)
                .append("E - A B C D").append(LINE_SEPARATOR)
                .toString();

        String list = stringGraph.graphRepresentation(RepresentationType.ADJACENCY_LIST);
        assertEquals(list, expectedList);
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

        assertEquals(expectedPathBetween1And3, integerGraph.shortestPath(1, 3));
        assertEquals(expectedPathBetween1And4, integerGraph.shortestPath(1, 4));
        assertEquals(expectedPathBetween3And2, integerGraph.shortestPath(3, 2));
        assertEquals(expectedPathBetween5And2, integerGraph.shortestPath(5, 2));
        assertEquals(expectedPathBetween5And5, integerGraph.shortestPath(5, 5));
    }


    /**
     * Tests MST in integer's {@link Graph}.
     */
    @Test
    public void mstIntegerGraphTest() {
    	 String expectedMST = new StringBuilder()
                 .append("[1, 2]").append(LINE_SEPARATOR)
                 .append("[1, 5]").append(LINE_SEPARATOR)
                 .append("[3, 5]").append(LINE_SEPARATOR)
                 .append("[4, 5]").append(LINE_SEPARATOR)
                 .toString();
    	assertEquals(integerGraph.MST(), expectedMST);
    }

    /**
     * Tests MST in integer's {@link Graph} with duplicated elements.
     */
    @Test
    public void mstIntegerDuplicateGraphTest() {
    	 String expectedMST = new StringBuilder()
    			 .append("[1, 2]").append(LINE_SEPARATOR)
                 .append("[1, 4]").append(LINE_SEPARATOR)
                 .append("[1, 5]").append(LINE_SEPARATOR)
                 .append("[2, 3]").append(LINE_SEPARATOR)
                 .append("[2, 6]").append(LINE_SEPARATOR)
                 .toString();
    	assertEquals(expectedMST, integerGraphDuplicate.MST());
    }

    /**
     * Tests MST in string's {@link Graph}.
     */
    @Test
    public void mstStringGraphTest() {
    	String expectedMST = new StringBuilder()
                .append("[A, B]").append(LINE_SEPARATOR)
                .append("[A, E]").append(LINE_SEPARATOR)
                .append("[C, E]").append(LINE_SEPARATOR)
                .append("[D, E]").append(LINE_SEPARATOR) 
                .toString();
   	    assertEquals(expectedMST, stringGraph.MST());
    }

}
