import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VendingMachineImpl implements VendingMachine {
    private Inventory<Coin> cashInventory = new Inventory<Coin>();
    private Inventory<Item> itemInventory = new Inventory<Item>();
    private long totalSales;
    private Item currentItem;
    private long currentBalance;

    public VendingMachineImpl(Object initialize) {
        initialize();

    }

    private void initialize() {
        for (Coin c : Coin.values()) {
            cashInventory.put(c, 5);
        }
        //for (Item i: Item.values(){
        //itemInventory.put(i,  5);
        //}
    }

    @Override
    public long selectItemAndGetPrice(Item item) {
        if (itemInventory.hasItem(item)) {
            currentItem = item;
            return currentItem.getPrice();
        }
        throw new soldOutException("Sold out, please buy another item");
    }

    @Override
    public void insertCoins(Coin coin) {
        currentBalance = currentBalance + coin.getValue();
        cashInventory.add(coin);
    }

    public void updateCashInventory(List change) {
        for (Coin coin : change) {
            cashInventory.decrease(coin);
        }
    }

    private boolean isFullyPaid() {
        if (currentBalance >= currentItem.getPrice()) {
            return true;
        }
        return false;
    }

    private List<Coin> getChange() {
        long changeAmount = currentBalance - currentItem.getPrice();
        List<Coin> change = getChange(changeAmount);
        updateCashInventory(change);
        currentBalance = 0;
        currentItem = null;
        return change;
    }

    private List<Coin> getChange(long amount) throws notSufficientChangeException {
        List<Coin> changes = Collections.emptyList();
        if (amount > 0) {
            changes = new ArrayList<Coin>();
            long balance = amount;
            while (balance > 0) {
                if (balance >= Coin.FIFTY.getValue() && cashInventory.hasItem(Coin.FIFTY)) {
                    changes.add(Coin.FIFTY);
                    balance = balance - Coin.FIFTY.getValue();
                    continue;
                } else if (balance >= Coin.TWENTY.getValue() && cashInventory.hasItem(Coin.TWENTY)) {
                    changes.add(Coin.TWENTY);
                    balance = balance - Coin.TWENTY.getValue();
                    continue;

                } else if ((balance >= Coin.TEN.getValue()) && cashInventory.hasItem(Coin.TEN) {
                    changes.add(Coin.TEN);
                    balance = balance - Coin.TEN.getValue();
                    continue;
                }
                if (balance >= Coin.FIVE.getValue() && cashInventory.hasItem(Coin.FIVE)) {
                    changes.add(Coin.FIVE);
                    balance = balance - Coin.FIVE.getValue();
                    continue;
                } else {
                    throw new notSufficientChangeException("Not suficient change, please try another product");
                }
            }


        }
        return changes;

    }

    private boolean hasSufficintChangeForAmount(long amount) {
        boolean hasChange = true;
        try {
            getChange(amount);
        } catch (notSufficientChangeException nsce) {
            return hasChange = false;
        }
        return hasChange;
    }

    private boolean hasSufficientChange() {
        return hasSufficintChangeForAmount(currentBalance - currentItem.getPrice());
    }

    private Item collectItem() throws notSufficientChangeException, notFullyPaidException {
        if (isFullyPaid()) {
            if (hasSufficientChange()) {
                itemInventory.decrease(currentItem);
                return currentItem;
            }
            throw new notSufficientChangeException("Not sufficient change in Inventory");
        }
        long remainingBalance = currentItem.getPrice() - currentBalance;
        throw new notFullyPaidException("Price not full paid, remaining: ", remainingBalance);

    }

    private List<Coin> collectChange() throws notFullyPaidException {
        long changeAmount = currentBalance - currentItem.getPrice();
        List<Coin> change = getChange(changeAmount);
        updateCashInventory(change);
        currentBalance = 0;
        currentItem = null;
        return change;
    }

    @Override
    public List<Coin> refund() {
        List<Coin> refund = getChange(currentBalance);
        updateCashInventory(refund);
        currentBalance = 0;
        currentItem = null;
        return refund;

    }

    @Override
    public PurchaseAndCoins <Item, List<Coin>> collectItemsAndGetChange() {
        Item item = collectItem();
        totalSales = totalSales + currentItem.getPrice();
        List<Coin> change = collectChange();

        throw new notSufficientChangeException("Not sufficient change, please select another product");
        long remainingBalance = currentItem.getPrice() - currentBalance;
        throw new notFullyPaidException("Price not fully paid, remaining: ", remainingBalance);
        return new PurchaseAndCoins<Item, List<Coin>>(item, change);
    }

    @Override
    public void reset() {
        cashInventory.clear();
        itemInventory.clear();
        totalSales = 0;
        currentItem = null;
        currentBalance = 0;
    }
    public void printStats() {
        System.out.println("Total sales " + totalSales);
        System.out.println("Current item inventory " + itemInventory);
        System.out.println("Current cash inventory " + cashInventory);

    }
    public long getTotalSales(){
        return totalSales;
    }
}




