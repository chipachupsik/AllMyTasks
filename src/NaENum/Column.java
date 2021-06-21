package NaENum;

import java.util.*;

public enum Column implements Columnable {
    Customer("Customer"),
    BankName("Bank Name"),
    AccountNumber("Account Number"),
    Amount("Available Amount");

    private String columnName;

    public static int[] realOrder;

    private Column(String columnName) {
        this.columnName = columnName;
    }

    public static void configureColumns(Column... newOrder) {
        realOrder = new int[values().length];
        for(Column column : values()) {
              realOrder[column.ordinal()] = -1;
            boolean isFound = false;

            for(int i = 0; i < newOrder.length; i++) {
                if(column == newOrder[i]) {
                    if(isFound) {
                        throw new IllegalArgumentException("Column '" + column.columnName + "' is already configured.");
                    }
                    realOrder[column.ordinal()] = i;
                    isFound = true;
                }
            }
        }
    }

    public static List<Column> getVisibleColumns() {
        List<Column> result = new LinkedList<>();
        Map<Integer, Column> map = new TreeMap<>();
        for(int i = 0; i < realOrder.length; i++) {
            map.put(realOrder[i], Column.values()[i]);
        }

        for(Map.Entry<Integer, Column> entry : map.entrySet()) {
            if(entry.getKey() != -1) {
                result.add(entry.getValue());
            }
        }

        return result;

    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    @Override
    public boolean isShown() {
        return realOrder[this.ordinal()] != -1;
    }

    @Override
    public void hide() {
        realOrder[this.ordinal()] = -1;
    }
}
