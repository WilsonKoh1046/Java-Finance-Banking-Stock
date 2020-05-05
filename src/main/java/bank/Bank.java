package bank;

import account.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Bank {

    // To store all of the created accounts
    private static List<Account> DB = new ArrayList<>();
    private static int bankFund = 10000; // We have 10000 as our default bank fund

    public Bank() {}

    public static List<Account> getDB() {
        return DB;
    }

    public static void setDB(Account account) {
        DB.add(account);
    }

    public static int getBankFund() {
        return bankFund;
    }

    public static boolean debtwithCustomer(int amount, Mode mode) {
        if (mode == Mode.CREDIT) { // customer pay back debt
            bankFund += amount;
        } else {
            Optional<String> sufficientFund = checkSufficientFund(amount);
            if (sufficientFund.isPresent()) {
                bankFund -= amount;
            } else {
                System.out.println("Bank has no sufficient fund to loan currently!");
                return false;
            }
        }
        return true;
    }

    public static Optional<String> checkSufficientFund(int amount) {
        Optional<String> decision;
        if (bankFund >= amount) {
            decision = Optional.of("Validated");
        } else {
            decision = Optional.ofNullable(null);
        }
        return decision;
    }

    public static void deleteAccount(Account account) {
        Optional<Account> targeted_account = DB.stream()
                .filter(a -> a.getName().equals(account.getName()) && a.getId().equals(account.getId()))
                .findFirst();

        if (targeted_account.isPresent()) {
            DB.remove(targeted_account.get());
            System.out.println("Account with the name " + targeted_account.get().getName()
                    + " and id " + targeted_account.get().getId() + " is successfully deleted!");
        } else {
            System.out.println("The account doesn't exist!");
        }
    }
}
