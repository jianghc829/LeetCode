import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution146 {
    public static void main(String[] args) {
        int capacity = 2;
        LRUCache lru = new LRUCache(capacity);
        lru.put(1, 1);
        lru.put(2, 2);
        System.out.println(lru.get(1));
        lru.put(3, 3);
        System.out.println(lru.get(2));
        lru.put(4, 4);
        System.out.println(lru.get(1));
        System.out.println(lru.get(3));
        System.out.println(lru.get(4));
    }
}

class LRUCache {
    private Map<Integer, Cell> map = new HashMap<>();
    private PriorityQueue<Cell> pq = new PriorityQueue<>();
    private int count = 0;
    private final int CAP;

    public LRUCache(int capacity) {
        this.CAP = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Cell cell = map.get(key);
        cell.cnt = ++count;
        return cell.val;
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            Cell cell = new Cell(key, value, ++count);
            map.put(key, cell);
            pq.offer(cell);
            if (pq.size() > CAP) {
                Cell old = pq.poll();
                map.remove(old.key, old);
            }
        } else {
            Cell cell = map.get(key);
            cell.val = value;
            cell.cnt = ++count;
        }
    }

    private static class Cell implements Comparable<Cell> {
        public int key;
        public int val;
        public int cnt;

        public Cell(int key, int val, int cnt) {
            this.key = key;
            this.val = val;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Cell another) {
            if (cnt == another.cnt)
                return 0;
            return cnt < another.cnt ? -1 : 1;
        }

        // @Override
        // public boolean equals(Object obj) {
        // if (this == obj) return true;
        // if (!(obj instanceof Cell)) return false;
        // Cell another = (Cell) obj;
        // return this.val == another.val;
        // }

        // @Override
        // public int hashCode() {
        // return val;
        // }
    }
}