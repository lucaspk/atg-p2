package com.ufcg.atg.library;

import com.ufcg.atg.graph.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class VertexNumberTest {

    private GraphLibrary<Integer> graphLibrary;

    private IGraph<Integer, Edge<Integer>> graphEmpty;
    private IGraph<Integer, Edge<Integer>> graphOneVertex;
    private IGraph<Integer, Edge<Integer>> graphWithLoop;
    private IGraph<Integer, Edge<Integer>> graphWithDuplicateEdges;
    private IGraph<Integer, Edge<Integer>> bigGraphStarForm;
    private IGraph<Integer, Edge<Integer>> bigGraphListForm;
    private IGraph<Integer, Edge<Integer>> bigGraphDisconnected;


    @BeforeEach
    public void setUp() {
        graphLibrary = new GraphLibrary<>();

        setUpGraphs();
    }

    private void setUpGraphs() {
        Integer v1 = -1, v2 = -2;

        graphEmpty = new Graph<>();

        graphOneVertex = new Graph<>();
        graphLibrary.addVertex(graphOneVertex, v1);


        graphWithLoop = new Graph<>();
        graphLibrary.addEdge(graphWithLoop, v1, v1);

        graphWithDuplicateEdges = new Graph<>();
        graphLibrary.addEdge(graphWithDuplicateEdges, v2, v1);

        setUpBigGraphStarForm(v1);

        setUpBigGraphListForm();

        setUpBigGraphDisconnected();
    }

    private void setUpBigGraphStarForm(Integer centralVertex) {
        bigGraphStarForm = new Graph<>();
        for (int v = 0; v < 999999; v++) {
            graphLibrary.addEdge(bigGraphStarForm, centralVertex, v);
        }
    }

    private void setUpBigGraphListForm() {
        bigGraphListForm = new Graph<>();
        for (int v = 1; v < 1000000; v += 2) {
            graphLibrary.addEdge(bigGraphListForm, v, v - 1);
        }
    }

    private void setUpBigGraphDisconnected() {
        bigGraphDisconnected = new Graph<>();
        for (int v = 0; v < 1000000; v++) {
            graphLibrary.addVertex(bigGraphDisconnected, v);
        }
    }

    @Test
    public void emptyGraphTest() {
        assertEquals(0, graphLibrary.getVertexNumber(graphEmpty));
    }

    @Test
    public void oneVertexTest() {
        assertEquals(1, graphLibrary.getVertexNumber(graphOneVertex));
    }

    @Test
    public void loopVertexTest() {
        assertEquals(1, graphLibrary.getVertexNumber(graphWithLoop));
    }

    @Test
    public void duplicatedVertexTest() {
        Integer v1 = -1;

        try {
            graphLibrary.addVertex(graphOneVertex, v1);
            fail("Should have thrown exception when trying to add a" +
                    " existent vertex.");
        } catch (Exception e) {
            assertEquals("The graph already contains the specified vertex.",
                    e.getMessage(), "A mensagem de erro está errada.");
        }

        try {
            graphLibrary.addVertex(graphWithLoop, v1);
            fail("Should have thrown exception when trying to add a" +
                    " existent vertex.");
        } catch (Exception e) {
            assertEquals("The graph already contains the specified vertex.",
                    e.getMessage(), "A mensagem de erro está errada.");
        }
        assertEquals(1, graphLibrary.getVertexNumber(graphOneVertex));
    }

    @Test
    public void duplicatedEdgesTest() {
        Integer v1 = -1, v2 = -2;

        try {
            graphLibrary.addEdge(graphWithDuplicateEdges, v2, v1);
            fail("Should have thrown exception when trying to add a" +
                    " existent Edge.");
        } catch (Exception e) {
            assertEquals("The graph already contains the specified edge.",
                    e.getMessage(), "A mensagem de erro está errada.");
        }
        assertEquals(2, graphLibrary.getVertexNumber(graphWithDuplicateEdges));
    }

    @Test
    public void bigGraphListFormTest() {
        assertEquals(1000000, graphLibrary.getVertexNumber(bigGraphListForm));
    }

    @Test
    public void bigGraphStarFormTest() {
        assertEquals(1000000, graphLibrary.getVertexNumber(bigGraphStarForm), 1000000);
    }

    @Test
    public void bigGraphDisconnectedTest() {
        assertEquals(1000000, graphLibrary.getVertexNumber(bigGraphDisconnected));
    }

}
