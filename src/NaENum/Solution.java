package NaENum;

public class Solution {

    public static void main(String[] args) {

        Column.configureColumns(Column.Customer, Column.Amount, Column.AccountNumber, Column.BankName);

        for(Columnable columnable : Column.getVisibleColumns()) {
            System.out.println(columnable.getColumnName());
            System.out.println(columnable.ordinal());
        }

        System.out.println("---------------------");
        Column.AccountNumber.hide();

        for(Columnable columnable : Column.getVisibleColumns()) {
            System.out.println(columnable.getColumnName());
        }
    }
}
