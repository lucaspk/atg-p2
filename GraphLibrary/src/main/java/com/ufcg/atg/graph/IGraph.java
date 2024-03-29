package com.ufcg.atg.graph;

import java.util.Set;

/**
 * Defines the interface of an undirected and unweighted graph.
 *
 * @param <V> Type of the vertex.
 * @param <E> Type of the edge.
 *
 * @author Vélmer Oliveira
 */
public interface IGraph<V extends Comparable<V>, E extends Edge<V>> {

    /**
     * Adds a vertex to the graph.
     *
     * @param v Vertex to be added.
     */
    void addVertex(V v);

    /**
     * Adds a edge to the graph. {@code v1} is going to be the edge origin and
     * {@code v2} the edge target. As this is an undirected graph, each added
     * edge, internally, will be represented by two edges, one from {@code v1}
     * to {@code v2} and another from {@code v2} to {@code v1}.
     *
     * @param v1 Vertex to be the edge origin.
     * @param v2 Vertex to be the edge target.
     * @return The added edge between {@code v1} and {@code v2}.
     */
    E addEdge(V v1, V v2);

    /**
     * Returns all vertexes of the graph.
     *
     * @return All vertexes of the graph.
     */
    Set<V> getAllVertexes();

    /**
     * Returns all edges of the graph.
     *
     * @return All edges of the graph.
     */
    Set<E> getAllEdges();

    /**
     * Returns all edges of the specified vertex.
     *
     * @param v Vertex to have all its edges returned.
     * @return Specified vertex's edges.
     */
    Set<E> getEdgesOfVertex(V v);

    /**
     * Returns all adjacent vertexes of the specified vertex.
     *
     * @param v Vertex to have all its adjacent vertexes returned.
     * @return Specified vertex's adjacent vertexes.
     */
    Set<V> getAdjacentVertexes(V v);

    /**
     * Returns if the graph contains the specified vertex.
     *
     * @param v Vertex that will have its existence in the graph verified.
     * @return {@code true} if contains, {@code false} otherwise.
     */
    boolean containsVertex(V v);

    /**
     * Returns if the graph contains the specified edge.
     *
     * @param e Edge that will have its existence in the graph verified.
     * @return {@code true} if contains, {@code false} otherwise.
     */
    boolean containsEdge(Edge<V> e);

    /**
     * Returns the number of vertexes of the graph.
     *
     * @return Number of vertexes of the graph.
     */
    int getVertexNumber();

    /**
     * Returns the number of edges of the graph.
     *
     * @return Number of edges of the graph.
     */
    int getEdgeNumber();

    /**
     * Returns the mean edge of the graph.
     *
     * @return Mean edge of the graph.
     */
    float getMeanEdge();

    /**
     * Returns the graph representation based on the {@link RepresentationType}
     * specified.
     *
     * @param representationType Type of the representation to be returned.
     *
     * @return The graph representation.
     */
    String graphRepresentation(RepresentationType representationType);

    /**
     * Returns a tree representation based on the Breadth-first Search (BFS)
     * algorithm starting from the vertex specified.
     *
     * @param v The vertex to be the root of the returned tree.
     * @return BFS of the graph.
     */
    String BFS(V v);

    /**
     * Returns a tree representation based on the Depth-first Search (DFS)
     * algorithm starting from the vertex specified.
     *
     * @param v The vertex to be the root of the returned tree.
     * @return DFS of the graph.
     */
    String DFS(V v);

    /**
     * Returns a boolean indicating if the graph is connect or not.
     *
     * @return Connectivity of the graph.
     */
    boolean connected();

    /**
     * Returns a representation of the shortest path between the specified
     * vertexes.
     *
     * @param v1 Origin vertex of the path.
     * @param v2 Target vertex of the path.
     * @return Shortest path between {@code v1} e {@code v2}.
     */
    String shortestPath(V v1, V v2);

    /**
     * Returns a representation of the Minimum Spanning Tree (MST) of the
     * graph.
     *
     * @return MST of the graph.
     */
    String MST();

}
