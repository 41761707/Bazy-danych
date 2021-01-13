package com.zalewskiwojtczak;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public abstract class AbstractPersonTableModel extends AbstractTableModel {
    protected String[] columnNames;
    protected List<? extends Person> people;
    protected Person tempPerson;

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return people.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public Object getValueAt(int row, int col) {
        tempPerson = people.get(row);

        switch (col) {
            case 0:
                return tempPerson.getId();
            case 1:
                return tempPerson.getFirstName();
            case 2:
                return tempPerson.getLastName();
            case 3:
                return tempPerson.getAddress();
            case 4:
                return tempPerson.getClassOrClassrooms();
            case 5:
                return tempPerson.getPesel();
            case 6:
                return tempPerson.getPhone();
            case 7:
                return tempPerson.getEmail();
            default:
                return null;
        }
    }
}