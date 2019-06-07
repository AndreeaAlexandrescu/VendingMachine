public class PurchaseAndCoins <T1, T2> {
    private T1 Item;
    private T2 coins;

    public PurchaseAndCoins(T1 item, T2 coins) {
        Item = item;
        this.coins = coins;
    }

    public T1 getItem() {
        return Item;
    }

    public void setItem(T1 item) {
        Item = item;
    }

    public T2 getCoins() {
        return coins;
    }

    public void setCoins(T2 coins) {
        this.coins = coins;
    }
}
