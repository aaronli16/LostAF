package graphs.shortestpaths;

import graphs.BaseEdge;
import graphs.Graph;
import priorityqueues.DoubleMapMinPQ;
import priorityqueues.ExtrinsicMinPQ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Objects;

/**
 * Computes shortest paths using Dijkstra's algorithm.
 * @see SPTShortestPathFinder for more documentation.
 */
public class DijkstraShortestPathFinder<G extends Graph<V, E>, V, E extends BaseEdge<V, E>>
    extends SPTShortestPathFinder<G, V, E> {

    protected <T> ExtrinsicMinPQ<T> createMinPQ() {
        return new DoubleMapMinPQ<>();
        /*
        If you have confidence in your heap implementation, you can disable the line above
        and enable the one below.
         */
        // return new ArrayHeapMinPQ<>();

        /*
        Otherwise, do not change this method.
        We override this during grading to test your code using our correct implementation so that
        you don't lose extra points if your implementation is buggy.
         */
    }

    @Override
    protected Map<V, E> constructShortestPathsTree(G graph, V start, V end) {
        Map<V, E> edgeTo = new HashMap<>();
        if (Objects.equals(start, end)) {
            return edgeTo;
        }
        Map<V, Double> distTo = new HashMap<>();
        Set<V> known = new HashSet<>();
        ExtrinsicMinPQ<V> pq = new DoubleMapMinPQ<>();

        pq.add(start, 0.0);
        distTo.put(start, 0.0);
        boolean foundEnd = false;
        while (!pq.isEmpty() && !foundEnd) {
            V u = pq.removeMin();
            if (u.equals(end)) {
                foundEnd = true;
            } else {
                known.add(u);

                for (E edge : graph.outgoingEdgesFrom(u)) {
                    V v = edge.to();
                    double weight = edge.weight();
                    if (!distTo.containsKey(v)) {
                        distTo.put(v, Double.POSITIVE_INFINITY);
                    }

                    double oldDist = distTo.get(v);
                    double newDist = distTo.get(u) + weight;
                    if (newDist < oldDist) {
                        distTo.put(v, newDist);
                        edgeTo.put(v, edge);

                        if (pq.contains(v)) {
                            pq.changePriority(v, newDist);
                        } else {
                            pq.add(v, newDist);
                        }
                    }

                }

            }
        }
        return edgeTo;
    }

    @Override
    protected ShortestPath<V, E> extractShortestPath(Map<V, E> spt, V start, V end) {
        if (Objects.equals(start, end)) {
            return new ShortestPath.SingleVertex<>(start);
        }
        E edge = spt.get(end);

        if (edge == null) {
            return new ShortestPath.Failure<>();
        }


        List<E> path = new ArrayList<>();
        V curr = end;

        while (!Objects.equals(start, curr)) {
            edge = spt.get(curr);
            path.add(edge);
            curr = edge.from();
        }
        Collections.reverse(path);
        return new ShortestPath.Success<>(path);




    }

}
