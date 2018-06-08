package com.ufcg.atg.library;

import com.ufcg.atg.graph.*;
import com.ufcg.atg.library.GraphLibrary;
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
    private IGraph<Integer, Edge<Integer>> largeIntegerGraph;
    private IGraph<String, Edge<String>> largeStringGraph;
    private IWeightedGraph<Integer, WeightedEdge<Integer>> weightedIntegerGraph;
    private IWeightedGraph<String, WeightedEdge<String>> weightedStringGraph;
    private GraphLibrary<Integer> graphLibraryInt;
    private GraphLibrary<String> graphLibraryStr;

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
        weightedIntegerGraph = new WeightedGraph<>();

        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
            for (int j = 1; j <= LARGE_GRAPH_SIZE; j++) {
                if (i != j) {
                    weightedIntegerGraph.addEdge(i, j, (i+j)/2f);
                }
            }
        }
    }

    /**
     * Constructs a weighted graph with vertices of string value.
     */
    private void setUpLargeWeightedGraphOfStrings() {
        weightedStringGraph = new WeightedGraph<>();

        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
            for (int j = 1; j <= LARGE_GRAPH_SIZE; j++) {
                if (i != j) {
                    weightedStringGraph.addEdge(Integer.toHexString(i), Integer.toHexString(j), (i+j)/2f);
                }
            }
        }
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
        StringBuilder expectedListBuilder = new StringBuilder();
        expectedListBuilder.append(" ");
        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
            expectedListBuilder.append(" " + i);
        }
        expectedListBuilder.append(LINE_SEPARATOR);

        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
            expectedListBuilder.append(i);
            for (int j = 1; j <= LARGE_GRAPH_SIZE; j++) {
                if (i != j) {
                    expectedListBuilder.append(" 1");
                } else {
                    expectedListBuilder.append(" 0");
                }
            }
            expectedListBuilder.append(LINE_SEPARATOR);
        }

        String expectedList = expectedListBuilder.toString();

        String list = graphLibraryInt.graphRepresentation(largeIntegerGraph, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedList, list);
    }

    /**
     * Tests the adjacency list representation of a weighted integer's  {@link Graph}.
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

        String list = graphLibraryInt.graphRepresentation(weightedIntegerGraph, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    /**
     * Tests the adjacency matrix representation of a weighted integer's  {@link Graph}.
     */
    @Test
    public void largeWeightedGraphOfIntegersMatrixRepresentationTest() {
        StringBuilder expectedListBuilder = new StringBuilder();

        expectedListBuilder.append(" ");
        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
            expectedListBuilder.append(" " + i);
        }
        expectedListBuilder.append(LINE_SEPARATOR);

        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
            expectedListBuilder.append(i);
            for (int j = 1; j <= LARGE_GRAPH_SIZE; j++) {
                if (i != j) {
                    expectedListBuilder.append(" " + Utils.floatToString((i+j)/2f));
                } else {
                    expectedListBuilder.append(" 0");
                }
            }
            expectedListBuilder.append(LINE_SEPARATOR);
        }

        String expectedList = expectedListBuilder.toString();

        String list = graphLibraryInt.graphRepresentation(weightedIntegerGraph, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedList, list);
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
        StringBuilder expectedListBuilder = new StringBuilder();
        SortedSet<String> set = new TreeSet<>();

        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
            set.add(Integer.toHexString(i));
        }

        expectedListBuilder.append(" ");
        for (String s : set) {
            expectedListBuilder.append(" " + s);
        }
        expectedListBuilder.append(LINE_SEPARATOR);

        for (String s : set) {
            expectedListBuilder.append(s);
            for (String s2 : set) {
                if (s.equals(s2)) {
                    expectedListBuilder.append(" 0");
                } else {
                    expectedListBuilder.append(" 1");
                }
            }
            expectedListBuilder.append(LINE_SEPARATOR);
        }

        String expectedList = expectedListBuilder.toString();
        String list = graphLibraryStr.graphRepresentation(largeStringGraph, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedList, list);
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
        String list = graphLibraryStr.graphRepresentation(weightedStringGraph, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    /**
     * Tests the adjacency matrix representation of a weighted string's  {@link Graph}.
     */
    @Test
    public void largeWeightedGraphOfStringsMatrixRepresentationTest() {
        StringBuilder expectedListBuilder = new StringBuilder();
        SortedSet<String> set = new TreeSet<>();

        for (int i = 1; i <= LARGE_GRAPH_SIZE; i++) {
            set.add(Integer.toHexString(i));
        }

        expectedListBuilder.append(" ");
        for (String s : set) {
            expectedListBuilder.append(" " + s);
        }
        expectedListBuilder.append(LINE_SEPARATOR);

        for (String s : set) {
            expectedListBuilder.append(s);
            for (String s2 : set) {
                if (s.equals(s2)) {
                    expectedListBuilder.append(" 0");
                } else {
                    expectedListBuilder.append(" " + Utils.floatToString((Integer.parseInt(s, 16) + Integer.parseInt(s2, 16))/2f));
                }
            }
            expectedListBuilder.append(LINE_SEPARATOR);
        }

        String expectedList = expectedListBuilder.toString();
        String list = graphLibraryStr.graphRepresentation(weightedStringGraph, RepresentationType.ADJACENCY_MATRIX);
        assertEquals(expectedList, list);
    }
}
