import java.util.ArrayList;
import java.util.List;

public class PowerOfTwoMaxHeap {
    private final int childrenPower;
    private final List<Integer> heap;

    public PowerOfTwoMaxHeap(int childrenPower) {
        this.childrenPower = childrenPower;
        this.heap = new ArrayList<>();
    }

    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    public int popMax() {
    if (heap.isEmpty()) {
        throw new IllegalStateException("Heap is empty");
    }

    int max = heap.get(0);
    int last = heap.remove(heap.size() - 1);
    if (!heap.isEmpty()) {
        heap.set(0, last);
        heapifyDown(0);
    }
    return max;
}

private void heapifyUp(int index) {
    int parentIndex = (index-1) / (int) Math.pow(2, childrenPower) + 1;
    while (index > 0 && heap.get(index) > heap.get(parentIndex)) {
        swap(index, parentIndex);
        index = parentIndex;
        parentIndex = (index - 1) / (int) Math.pow(2, childrenPower) + 1;
    }
}

private void heapifyDown(int index) {
    int maxChildIndex = getMaxChildIndex(index);
    while (maxChildIndex != -1 && heap.get(index) < heap.get(maxChildIndex)) {
        swap(index, maxChildIndex);
        index = maxChildIndex;
        maxChildIndex = getMaxChildIndex(index);
    }
}

private int getMaxChildIndex(int index) {
    int maxChildIndex = -1;
    int start = (int) Math.pow(2, childrenPower) * index + 1;
    int end = Math.min(heap.size(), (int) Math.pow(2, childrenPower) * (index + 1) + 1);
    for (int i = start; i < end; i++) {
        if (maxChildIndex == -1 || heap.get(i) > heap.get(maxChildIndex)) {
            maxChildIndex = i;
        }
    }
    return maxChildIndex < heap.size() ? maxChildIndex : -1;
}

private void swap(int i, int j) {
    int temp = heap.get(i);
    heap.set(i, heap.get(j));
    heap.set(j, temp);
}
}