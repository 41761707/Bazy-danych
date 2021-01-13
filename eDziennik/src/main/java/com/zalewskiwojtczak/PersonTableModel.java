package com.zalewskiwojtczak;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public abstract class PersonTableModel extends AbstractTableModel {
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
    public abstract Object getValueAt(int row, int col);

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}