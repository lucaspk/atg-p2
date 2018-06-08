package com.ufcg.atg.library;

import com.ufcg.atg.graph.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class BFSTests {

    private GraphLibrary<Integer> graphLibrary;
    private IGraph<Integer, Edge<Integer>> weightedGraphEmpty;
    private IGraph<Integer, Edge<Integer>> unweightedGraphEmpty;
    private IGraph<Integer, Edge<Integer>> graphOneVertex;
    private IGraph<Integer, Edge<Integer>> graphWithLoop;
    private IGraph<Integer, Edge<Integer>> bigGraphStarForm;
    private IGraph<Integer, Edge<Integer>> bigGraphListForm;
    private IGraph<Integer, Edge<Integer>> bigGraphDisconnected;

    @BeforeEach
    public void setUp() {
        graphLibrary = new GraphLibrary<>();

        setUpGraphs();
    }

    private void setUpGraphs() {
        Integer v1 = -1;
        weightedGraphEmpty = new Graph<>();
        unweightedGraphEmpty = new Graph<>();

        graphOneVertex = new Graph<>();
        graphLibrary.addVertex(graphOneVertex, v1);

        graphWithLoop = new Graph<>();
        graphLibrary.addEdge(graphWithLoop, v1, v1);

        setUpBigGraphStarForm(v1);

        setUpBigGraphListForm();

        setUpBigGraphDisconnected();
    }

    private void setUpBigGraphStarForm(Integer centralVertex) {
        bigGraphStarForm = new Graph<>();

        for (int v = 0; v < 79999; v++) {
            graphLibrary.addEdge(bigGraphStarForm, centralVertex, v);
        }

    }

    private void setUpBigGraphListForm() {
        bigGraphListForm = new Graph<>();
        for (int v = 2; v < 80001; v ++) {
            graphLibrary.addEdge(bigGraphListForm, v, v - 1);
        }
    }

    private void setUpBigGraphDisconnected() {
        bigGraphDisconnected = new Graph<>();
        for (int v = 0; v < 8000; v++) {
            graphLibrary.addVertex(bigGraphDisconnected, v);
        }
    }

    @Test
    public void emptyGraphBFSTest() {
        try {
            graphLibrary.BFS(weightedGraphEmpty, 0);
            fail("The graph is empty.");
        } catch (Exception e) {
            assertEquals("The graph doesn't contains the specified vertex.",
                    e.getMessage(), "A mensagem de erro está errada.");
        }

        try {
            graphLibrary.BFS(graphOneVertex, 0);
            fail("The graph doesn't contains the root of BSF.");
        } catch (Exception e) {
            assertEquals("The graph doesn't contains the specified vertex.",
                    e.getMessage(), "A mensagem de erro está errada.");
        }

        try {
            graphLibrary.BFS(unweightedGraphEmpty, 0);
            fail("The graph is empty.");
        } catch (Exception e) {
            assertEquals("The graph doesn't contains the specified vertex.",
                    e.getMessage(), "A mensagem de erro está errada.");
        }
    }

    @Test
    public void oneVertexGraphBFSTest() {
        String expectedOutput = "-1 - 0 -"+ System.getProperty("line.separator");

        assertEquals(expectedOutput, graphLibrary.BFS(graphOneVertex, -1));
    }

    @Test
    public void loopGraphBFSTest() {
        String expectedOutput = "-1 - 0 -" + System.getProperty("line.separator");
        assertEquals(expectedOutput, graphLibrary.BFS(graphWithLoop, -1));
    }

    @Test
    public void bigGraphListFormTest() {
        String outputBigGraphListForm = 1 + " - 0 -" + System.getProperty("line.separator");

        for (int v = 2; v < 80001; v++) {
            outputBigGraphListForm += v + " - " + (v - 1) + " " + (v - 1) + System.getProperty("line.separator");
        }
        assertEquals(outputBigGraphListForm, graphLibrary.BFS(bigGraphListForm, 1));
    }

    @Test
    public void bigGraphStarFormTest() {

        String outputBigGraphStarForm = -1 + "" + " - 0 -" + System.getProperty("line.separator");

        for (int v = 0; v < 79999; v++) {
            outputBigGraphStarForm += v + " - " + 1 + " " + (-1) + System.getProperty("line.separator");
        }

        assertEquals(outputBigGraphStarForm, graphLibrary.BFS(bigGraphStarForm, -1));
    }

    @Test
    public void bigGraphDisconnectedTest() {
        String expectedOutput = "1 - 0 -" + System.getProperty("line.separator");

        assertEquals(expectedOutput, graphLibrary.BFS(bigGraphDisconnected, 1));
    }

}
