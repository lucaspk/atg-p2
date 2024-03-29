package com.ufcg.atg.graph;

import com.ufcg.atg.util.Utils;

/**
 * Represents a implementation of a undirected and weighted graph, based on
 * the interface defined by {@link IGraph}.
 *
 * @param <V> Type of the vertex.
 *
 * @author Vélmer Oliveira
 */
public class WeightedGraph<V extends Comparable<V>> extends BaseGraph<V, WeightedEdge<V>>
        implements IWeightedGraph<V, WeightedEdge<V>> {

    public static final float EDGE_DEFAULT_WEIGHT = 1f;

    /**
     * Constructs a {@link WeightedGraph}.
     */
    public WeightedGraph() {
        super();
    }

    @Override
    public WeightedEdge<V> addEdge(V v1, V v2) {
        return addEdge(v1, v2, EDGE_DEFAULT_WEIGHT);
    }

    @Override
    public WeightedEdge<V> addEdge(V v1, V v2, float weight) {
        addIfAbsent(v1);
        addIfAbsent(v2);
        WeightedEdge<V> edgeToReturn = new WeightedEdge<>(v1, v2, weight),
                reverseEdge = new WeightedEdge<>(v2, v1, weight);
        vertexes.get(v1).add(edgeToReturn);
        vertexes.get(v2).add(reverseEdge);
        return edgeToReturn;
    }

    @Override
    protected float getEdgeWeight(WeightedEdge<V> e) {
        return e.getWeight();
    }

    @Override
    protected String mapOperatorListRepresentation(WeightedEdge<V> e) {
        return e.getTargetVertex().toString() + "(" + Utils.floatToString(getEdgeWeight(e)) + ")";
    }

    @Override
    protected boolean containsNegativeWeightedEdge() {
        return getAllEdges().stream()
                .filter(e -> e.getWeight() < 0).count() > 0;
    }

}
