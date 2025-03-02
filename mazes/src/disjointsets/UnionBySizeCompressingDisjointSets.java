package disjointsets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A quick-union-by-size data structure with path compression.
 * @see DisjointSets for more documentation.
 */
public class UnionBySizeCompressingDisjointSets<T> implements DisjointSets<T> {
    // Do NOT rename or delete this field. We will be inspecting it directly in our private tests.
    List<Integer> pointers;
    Map<T, Integer> itemToIndex;


    /*
    However, feel free to add more fields and private helper methods. You will probably need to
    add one or two more fields in order to successfully implement this class.
    */

    public UnionBySizeCompressingDisjointSets() {
        pointers = new ArrayList<>();
        itemToIndex = new HashMap<>();

    }

    @Override
    public void makeSet(T item) {
        pointers.add(-1);
        itemToIndex.put(item, pointers.size() - 1);

    }

    @Override
    public int findSet(T item) {
        if (!itemToIndex.containsKey(item)) {
            throw new IllegalArgumentException("Item not found in any set");
        }
        int index= itemToIndex.get(item);
        List<Integer> vistedItems = new ArrayList<>();

        while (pointers.get(index) >= 0) {
            vistedItems.add(index);
            index = pointers.get(index);
        }

        for (int i : vistedItems) {
            pointers.set(i, index);
        }
        return index;
    }

    @Override
    public boolean union(T item1, T item2) {
        int rootA = findSet(item1);
        int rootB = findSet(item2);
        if (rootA == rootB) {
            return false;
        }
        int weightA = -1 * pointers.get(rootA);
        int weightB = -1 * pointers.get(rootB);

        if (weightA > weightB) {
            pointers.set(rootA, -1 * (weightA + weightB));
            pointers.set(rootB, rootA);
        }
        else if (weightB > weightA) {
            pointers.set(rootB, -1 * (weightA + weightB));
            pointers.set(rootA, rootB);
        }
        else {
            pointers.set(rootA, -1 * (weightA + weightB));
            pointers.set(rootB, rootA);
        }

        return true;





    }
}
