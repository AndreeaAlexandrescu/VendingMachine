import java.util.HashMap;
import java.util.Map;

public class Inventory<T> {
    private Map<T, Integer> inventory;

    public Inventory() {
        this.inventory = inventory;
        inventory = new HashMap<>();

    }

    public int getQuantity(T item) {
        Integer value = inventory.get(item);
        return value;

    }

    public void add(T item) {
        int count = inventory.get(item);
        inventory.put(item, count + 1);
    }

    public void decrease(T item) {
        if ((hasItem(item))){
            int count = inventory.get(item);
            inventory.put(item, count - 1);
        }

    }

    public boolean hasItem(T item){
        return getQuantity(item) > 0;
    }
    public void clear() {
        inventory.clear();
    }
    public void put(T item, int quantity) {
        inventory.put(item, quantity);
    }

    //public void add(T item){};
    // public boolean has item(T item) {};
    // public void decrease (T item) {};
    //public void clear () [];
    //public void put (T item, int quantity){};
}

