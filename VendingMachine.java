import jdk.nashorn.internal.ir.LiteralNode;

import java.util.List; //INTERFACE

public interface VendingMachine {
    long selectItemAndGetPrice(Item item);

    class soldOutException extends RuntimeException {
        private String message;

        public soldOutException(String string) {
            this.message = string;
        }

        @Override
        public String getMessage() {
            return this.message;
        }


    }

    void insertCoins(Coin c);

    List<Coin> refund();

    class notSufficientChangeException extends RuntimeException {
        private String message;

        public notSufficientChangeException(String string) {
            this.message = string;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }

    public PurchaseAndCoins<Item, List<Coin>> collectItemsAndGetChange();

    public class notFullyPaidException extends RuntimeException {
        private String message;
        private long remaining;

        public notFullyPaidException(String message, long remaining) {
            this.message = message;
            this.remaining = remaining;
        }

        public long getRemaining() {
            return remaining;
        }

        @Override
        public String getMessage() {
            return message + remaining;
        }
    }

    public void reset();

}


//void selectItemAndGetPrice(Item item); soldOutException; exceptii customizate
//void insertCoins(Coin c);
//List<Coin> refund();
//void CollectItemAndChange(); unsuficientChangeException(); notFullyPaidException() exceptii customizate
//PurchaseAndCoins<Item, List<Coin>> collectItemsAndCoins();
//void reset();
//

