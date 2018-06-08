package com.ufcg.atg.library;

import com.ufcg.atg.graph.*;
import com.ufcg.atg.util.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.ufcg.atg.util.Utils.LINE_SEPARATOR;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Anderson on 5/29/2018.
 */
public class AdjacencyRepresentationTest {

    private final int LARGE_GRAPH_SIZE = 2000;
    private GraphLibrary<Integer> graphLibraryInt;
    private GraphLibrary<String> graphLibraryStr;
    private IGraph<Integer, Edge<Integer>> largeIntegerGraph;
    private IGraph<String, Edge<String>> largeStringGraph;
    private IWeightedGraph<Integer, WeightedEdge<Integer>> largeWeightedIntegerGraph;
    private IWeightedGraph<String, WeightedEdge<String>> largeWeightedStringGraph;
    private IGraph<Integer, Edge<Integer>> disconnectedGraph;
    private IGraph<Integer, Edge<Integer>> integerGraph;
    private IGraph<Integer, Edge<Integer>> integerGraph2;
    private IGraph<String, Edge<String>> stringGraph;
    private IWeightedGraph<Integer, WeightedEdge<Integer>> weightedIntegerGraph;
    private IWeightedGraph<String, WeightedEdge<String>> weightedStringGraph;

    /**
     * Tests' set up.
     */
    @BeforeEach
    public void setUp() {
        graphLibraryInt = new GraphLibrary<>();
        graphLibraryStr = new GraphLibrary<>();
        setUpLargeGraphOfIntegers();
        setUpLargeGraphOfStrings();
        setUpLargeWeightedGraphOfIntegers();
        setUpLargeWeightedGraphOfStrings();
        setUpDisconnectedGraph();
        setUpGraphOfIntegers();
        setUpGraphOfIntegers2();
        setUpGraphOfStrings();
        setUpWeightedGraphOfIntegers();
        setUpWeightedGraphOfStrings();
    }

    /**
     * Constructs a large {@link Graph} with vertices of integer value.
     */
    private void setUpLargeGraphOfIntegers() {
        largeIntegerGraph = new Graph<>();
        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
            for (int j = 1; j <= LARGE_GRAPH_SIZE; j++) {
                if (i != j) {
                    largeIntegerGraph.addEdge(i, j);
                }
            }
        }
    }

    /**
     * Constructs a {@link Graph} with vertices of string value.
     */
    private void setUpLargeGraphOfStrings() {
        largeStringGraph = new Graph<>();

        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
            for (int j = 1; j <= LARGE_GRAPH_SIZE; j++) {
                if (i != j) {
                    largeStringGraph.addEdge(Integer.toHexString(i), Integer.toHexString(j));
                }
            }
        }
    }

    /**
     * Constructs a weighted graph with vertices of integer value.
     */
    private void setUpLargeWeightedGraphOfIntegers() {
        largeWeightedIntegerGraph = new WeightedGraph<>();

        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
            for (int j = 1; j <= LARGE_GRAPH_SIZE; j++) {
                if (i != j) {
                    largeWeightedIntegerGraph.addEdge(i, j, (i+j)/2f);
                }
            }
        }
    }

    /**
     * Constructs a weighted graph with vertices of string value.
     */
    private void setUpLargeWeightedGraphOfStrings() {
        largeWeightedStringGraph = new WeightedGraph<>();

        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
            for (int j = 1; j <= LARGE_GRAPH_SIZE; j++) {
                if (i != j) {
                    largeWeightedStringGraph.addEdge(Integer.toHexString(i), Integer.toHexString(j), (i+j)/2f);
                }
            }
        }
    }

    /**
     * Constructs a {@link Graph} with vertices of integer duplicate value.
     */
    private void setUpGraphOfIntegers() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5, i6 = 6;
        integerGraph = new Graph<>();
        integerGraph.addEdge(i1, i2);
        integerGraph.addEdge(i1, i4);
        integerGraph.addEdge(i1, i5);
        integerGraph.addEdge(i2, i3);
        integerGraph.addEdge(i2, i6);
        integerGraph.addEdge(i2, i5);
        integerGraph.addEdge(i3, i4);
        integerGraph.addEdge(i3, i6);
        integerGraph.addEdge(i4, i5);
        integerGraph.addEdge(i5, i6);
    }

    /**
     * Constructs a {@link Graph} with vertices of integer value.
     */
    private void setUpGraphOfIntegers2() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
        integerGraph2 = new Graph<>();
        integerGraph2.addEdge(i1, i2);
        integerGraph2.addEdge(i2, i5);
        integerGraph2.addEdge(i5, i3);
        integerGraph2.addEdge(i4, i5);
        integerGraph2.addEdge(i1, i5);
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
     * Constructs a weighted graph with vertices of integer value.
     */
    private void setUpWeightedGraphOfIntegers() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
        weightedIntegerGraph = new WeightedGraph<>();
        weightedIntegerGraph.addEdge(i1, i2, 0.1f);
        weightedIntegerGraph.addEdge(i2, i5, 0.2f);
        weightedIntegerGraph.addEdge(i5, i3, 5f);
        weightedIntegerGraph.addEdge(i3, i4, -9.5f);
        weightedIntegerGraph.addEdge(i4, i5, 2.3f);
        weightedIntegerGraph.addEdge(i1, i5,1f);
    }

    /**
     * Constructs a weighted graph with vertices of string value.
     */
    private void setUpWeightedGraphOfStrings() {
        String s1 = "A", s2 = "B", s3 = "C", s4 = "D", s5 = "E";
        weightedStringGraph = new WeightedGraph<>();
        weightedStringGraph.addEdge(s1, s2, 0.1f);
        weightedStringGraph.addEdge(s2, s5, 0.2f);
        weightedStringGraph.addEdge(s5, s3, 5f);
        weightedStringGraph.addEdge(s3, s4, -9.5f);
        weightedStringGraph.addEdge(s4, s5, 2.3f);
        weightedStringGraph.addEdge(s1, s5,1f);
    }

    /**
     * Tests the adjacency list representation of a integer's disconnected {@link Graph}.
     */
    @Test
    public void graphDisconnetecListRepresentationTest() {
        String expectedList = new StringBuilder()
                .append("1 - ").append(LINE_SEPARATOR)
                .append("2 - ").append(LINE_SEPARATOR)
                .append("3 - ").append(LINE_SEPARATOR)
                .append("4 - ").append(LINE_SEPARATOR)
                .append("5 - ").append(LINE_SEPARATOR)
                .toString();

        String list = graphLibraryInt.graphRepresentation(disconnectedGraph, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    /**
     * Tests the adjacency matrix representation of a {@link Graph} with
     * duplicated integers.
     */
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

        String matrix = graphLibraryInt.graphRepresentation(integerGraph, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedMatrix, matrix);
    }

    /**
     * Tests the adjacency matrix representation of a integer's {@link Graph}.
     */
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

        String matrix = graphLibraryInt.graphRepresentation(integerGraph2, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedMatrix, matrix);
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

        String matrix = graphLibraryStr.graphRepresentation(stringGraph, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedMatrix, matrix);
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

        String matrix = graphLibraryInt.graphRepresentation(disconnectedGraph, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedMatrix, matrix);
    }

    /**
     * Tests the adjacency list representation of a string's {@link Graph}
     * with duplicated strings.
     */
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

        String list = graphLibraryInt.graphRepresentation(integerGraph, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    /**
     * Tests the adjacency list representation of a integer's  {@link Graph}.
     */
    @Test
    public void graphOfIntegersListRepresentationTest2() {
        String expectedList = new StringBuilder()
                .append("1 - 2 5").append(LINE_SEPARATOR)
                .append("2 - 1 5").append(LINE_SEPARATOR)
                .append("3 - 5").append(LINE_SEPARATOR)
                .append("4 - 5").append(LINE_SEPARATOR)
                .append("5 - 1 2 3 4").append(LINE_SEPARATOR)
                .toString();

        String list = graphLibraryInt.graphRepresentation(integerGraph2, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
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

        String list = graphLibraryStr.graphRepresentation(stringGraph, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    /**
     * Tests the adjacency matrix representation of a integer's {@link WeightedGraph}.
     */
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

        String matrix = graphLibraryInt.graphRepresentation(weightedIntegerGraph, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedMatrix, matrix);
    }

    /**
     * Tests the adjacency matrix representation of a string's {@link WeightedGraph}.
     */
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

        String matrix = graphLibraryStr.graphRepresentation(weightedStringGraph, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedMatrix, matrix);
    }

    /**
     * Tests the adjacency list representation of a integer's {@link WeightedGraph}.
     */
    @Test
    public void weightedGraphOfIntegersListRepresentationTest() {
        String expectedList = new StringBuilder()
                .append("1 - 2(0.1) 5(1)").append(LINE_SEPARATOR)
                .append("2 - 1(0.1) 5(0.2)").append(LINE_SEPARATOR)
                .append("3 - 4(-9.5) 5(5)").append(LINE_SEPARATOR)
                .append("4 - 3(-9.5) 5(2.3)").append(LINE_SEPARATOR)
                .append("5 - 1(1) 2(0.2) 3(5) 4(2.3)").append(LINE_SEPARATOR)
                .toString();

        String list = graphLibraryInt.graphRepresentation(weightedIntegerGraph, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    /**
     * Tests the adjacency list representation of a string's {@link WeightedGraph}.
     */
    @Test
    public void weightedGraphOfStringsListRepresentationTest() {
        String expectedList = new StringBuilder()
                .append("A - B(0.1) E(1)").append(LINE_SEPARATOR)
                .append("B - A(0.1) E(0.2)").append(LINE_SEPARATOR)
                .append("C - D(-9.5) E(5)").append(LINE_SEPARATOR)
                .append("D - C(-9.5) E(2.3)").append(LINE_SEPARATOR)
                .append("E - A(1) B(0.2) C(5) D(2.3)").append(LINE_SEPARATOR)
                .toString();

        String list = graphLibraryStr.graphRepresentation(weightedStringGraph, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    /**
     * Tests the adjacency list representation of a integer's  {@link Graph}.
     */
    @Test
    public void largeGraphOfIntegersListRepresentationTest() {
        StringBuilder expectedListBuilder = new StringBuilder();
        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
            expectedListBuilder.append(i + " -");
            for (int j = 1; j <= LARGE_GRAPH_SIZE; j++) {
                if (i != j) {
                    expectedListBuilder.append(" " + j);
                }
            }
            expectedListBuilder.append(LINE_SEPARATOR);
        }

        String expectedList = expectedListBuilder.toString();

        String list = graphLibraryInt.graphRepresentation(largeIntegerGraph, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    /**
     * Tests the adjacency matrix representation of a integer's  {@link Graph}.
     */
    @Test
    public void largeGraphOfIntegersMatrixRepresentationTest() {
        StringBuilder expectedMatrixBuilder = new StringBuilder();
        expectedMatrixBuilder.append(" ");
        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
            expectedMatrixBuilder.append(" " + i);
        }
        expectedMatrixBuilder.append(LINE_SEPARATOR);

        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
            expectedMatrixBuilder.append(i);
            for (int j = 1; j <= LARGE_GRAPH_SIZE; j++) {
                if (i != j) {
                    expectedMatrixBuilder.append(" 1");
                } else {
                    expectedMatrixBuilder.append(" 0");
                }
            }
            expectedMatrixBuilder.append(LINE_SEPARATOR);
        }

        String expectedMatrix = expectedMatrixBuilder.toString();

        String matrix = graphLibraryInt.graphRepresentation(largeIntegerGraph, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedMatrix, matrix);
    }

    /**
     * Tests the adjacency matrix representation of a weighted integer's  {@link Graph}.
     */
    @Test
    public void largeWeightedGraphOfIntegersListRepresentationTest() {
        StringBuilder expectedListBuilder = new StringBuilder();
        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
            expectedListBuilder.append(i + " -");
            for (int j = 1; j <= LARGE_GRAPH_SIZE; j++) {
                if (i != j) {
                    expectedListBuilder.append(" " + j + "(" + Utils.floatToString((i+j)/2f) + ")");
                }
            }
            expectedListBuilder.append(LINE_SEPARATOR);
        }

        String expectedList = expectedListBuilder.toString();

        String list = graphLibraryInt.graphRepresentation(largeWeightedIntegerGraph, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    /**
     * Tests the adjacency matrix representation of a weighted integer's  {@link Graph}.
     */
    @Test
    public void largeWeightedGraphOfIntegersMatrixRepresentationTest() {
        StringBuilder expectedMatrixBuilder = new StringBuilder();

        expectedMatrixBuilder.append(" ");
        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
            expectedMatrixBuilder.append(" " + i);
        }
        expectedMatrixBuilder.append(LINE_SEPARATOR);

        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
            expectedMatrixBuilder.append(i);
            for (int j = 1; j <= LARGE_GRAPH_SIZE; j++) {
                if (i != j) {
                    expectedMatrixBuilder.append(" " + Utils.floatToString((i+j)/2f));
                } else {
                    expectedMatrixBuilder.append(" 0");
                }
            }
            expectedMatrixBuilder.append(LINE_SEPARATOR);
        }

        String expectedMatrix = expectedMatrixBuilder.toString();

        String matrix = graphLibraryInt.graphRepresentation(largeWeightedIntegerGraph, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedMatrix, matrix);
    }

    /**
     * Tests the adjacency list representation of a string's  {@link Graph}.
     */
    @Test
    public void largeGraphOfStringsListRepresentationTest() {
        StringBuilder expectedListBuilder = new StringBuilder();
        SortedSet<String> set = new TreeSet<>();

        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
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
        String list = graphLibraryStr.graphRepresentation(largeStringGraph, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    /**
     * Tests the adjacency matrix representation of a string's  {@link Graph}.
     */
    @Test
    public void largeGraphOfStringsMatrixRepresentationTest() {
        StringBuilder expectedMatrixBuilder = new StringBuilder();
        SortedSet<String> set = new TreeSet<>();

        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
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
        String matrix = graphLibraryStr.graphRepresentation(largeStringGraph, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedMatrix, matrix);
    }

    /**
     * Tests the adjacency list representation of a weighted string's  {@link Graph}.
     */
    @Test
    public void largeWeightedGraphOfStringsListRepresentationTest() {
        StringBuilder expectedListBuilder = new StringBuilder();
        SortedSet<String> set = new TreeSet<>();

        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
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
        String list = graphLibraryStr.graphRepresentation(largeWeightedStringGraph, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    /**
     * Tests the adjacency matrix representation of a weighted string's  {@link Graph}.
     */
    @Test
    public void largeWeightedGraphOfStringsMatrixRepresentationTest() {
        StringBuilder expectedMatrixBuilder = new StringBuilder();
        SortedSet<String> set = new TreeSet<>();

        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
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
        String matrix = graphLibraryStr.graphRepresentation(largeWeightedStringGraph, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedMatrix, matrix);
    }
}
