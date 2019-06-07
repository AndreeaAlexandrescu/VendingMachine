

public class Main {

    public static void main(String[] args) {
        VendingMachineImpl masina = new VendingMachineImpl();
        masina.printStats();
        masina.selectItemAndGetPrice(Item.MARS);
        masina.insertCoins(Coin.TEN);
        masina.insertCoins(Coin.TWENTY);
        masina.collectItemsAndGetChange();
    }
}