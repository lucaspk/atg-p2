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

    private final int LARGE_GRAPH_SIZE_REP = 2000;
    private GraphLibrary<Integer> graphLibrary;
    private GraphLibrary<String> graphLibraryStr;
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

    /**
     * Tests' set up.
     */
    @BeforeEach
    public void setUp() {
        graphLibrary = new GraphLibrary<>();
        graphLibraryStr = new GraphLibrary<>();
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

    /**
     * Constructs a large {@link Graph} with vertices of integer value.
     */
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

    /**
     * Constructs a {@link Graph} with vertices of string value.
     */
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

    /**
     * Constructs a weighted graph with vertices of integer value.
     */
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

    /**
     * Constructs a weighted graph with vertices of string value.
     */
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

    /**
     * Constructs a {@link Graph} with vertices of integer duplicate value.
     */
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

    /**
     * Constructs a {@link Graph} with vertices of integer value.
     */
    private void setUpGraphOfIntegers2Rep() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
        integerGraph2Rep = new Graph<>();
        integerGraph2Rep.addEdge(i1, i2);
        integerGraph2Rep.addEdge(i2, i5);
        integerGraph2Rep.addEdge(i5, i3);
        integerGraph2Rep.addEdge(i4, i5);
        integerGraph2Rep.addEdge(i1, i5);
    }

    /**
     * Constructs a {@link Graph} with vertices of string value.
     */
    private void setUpGraphOfStringsRep() {
        String s1 = "A", s2 = "B", s3 = "C", s4 = "D", s5 = "E";
        stringGraphRep = new Graph<>();
        stringGraphRep.addEdge(s1, s2);
        stringGraphRep.addEdge(s2, s5);
        stringGraphRep.addEdge(s5, s3);
        stringGraphRep.addEdge(s4, s5);
        stringGraphRep.addEdge(s1, s5);
    }

    /**
     * Constructs a disconnected {@link Graph} with vertices of integer value.
     */
    private void setUpDisconnectedGraphRep() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
        disconnectedGraphRep = new Graph<>();
        disconnectedGraphRep.addVertex(i1);
        disconnectedGraphRep.addVertex(i2);
        disconnectedGraphRep.addVertex(i3);
        disconnectedGraphRep.addVertex(i4);
        disconnectedGraphRep.addVertex(i5);
    }

    /**
     * Constructs a weighted graph with vertices of integer value.
     */
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

    /**
     * Constructs a weighted graph with vertices of string value.
     */
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

        String list = graphLibrary.graphRepresentation(disconnectedGraphRep, RepresentationType.ADJACENCY_LIST);
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

        String matrix = graphLibrary.graphRepresentation(integerGraphRep, RepresentationType.ADJACENCY_MATRIX);
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

        String matrix = graphLibrary.graphRepresentation(integerGraph2Rep, RepresentationType.ADJACENCY_MATRIX);
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

        String matrix = graphLibraryStr.graphRepresentation(stringGraphRep, RepresentationType.ADJACENCY_MATRIX);
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

        String matrix = graphLibrary.graphRepresentation(disconnectedGraphRep, RepresentationType.ADJACENCY_MATRIX);
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

        String list = graphLibrary.graphRepresentation(integerGraphRep, RepresentationType.ADJACENCY_LIST);
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

        String list = graphLibrary.graphRepresentation(integerGraph2Rep, RepresentationType.ADJACENCY_LIST);
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

        String list = graphLibraryStr.graphRepresentation(stringGraphRep, RepresentationType.ADJACENCY_LIST);
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

        String matrix = graphLibrary.graphRepresentation(weightedIntegerGraphRep, RepresentationType.ADJACENCY_MATRIX);
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

        String matrix = graphLibraryStr.graphRepresentation(weightedStringGraphRep, RepresentationType.ADJACENCY_MATRIX);
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

        String list = graphLibrary.graphRepresentation(weightedIntegerGraphRep, RepresentationType.ADJACENCY_LIST);
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

        String list = graphLibraryStr.graphRepresentation(weightedStringGraphRep, RepresentationType.ADJACENCY_LIST);
        assertEquals(expectedList, list);
    }

    /**
     * Tests the adjacency list representation of a integer's  {@link Graph}.
     */
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

    /**
     * Tests the adjacency matrix representation of a integer's  {@link Graph}.
     */
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

    /**
     * Tests the adjacency matrix representation of a weighted integer's  {@link Graph}.
     */
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

    /**
     * Tests the adjacency matrix representation of a weighted integer's  {@link Graph}.
     */
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

    /**
     * Tests the adjacency list representation of a string's  {@link Graph}.
     */
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

    /**
     * Tests the adjacency matrix representation of a string's  {@link Graph}.
     */
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

    /**
     * Tests the adjacency list representation of a weighted string's  {@link Graph}.
     */
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

    /**
     * Tests the adjacency matrix representation of a weighted string's  {@link Graph}.
     */
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
}
