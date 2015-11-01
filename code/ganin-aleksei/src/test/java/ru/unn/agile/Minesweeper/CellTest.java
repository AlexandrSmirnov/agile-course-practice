package ru.unn.agile.Minesweeper;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CellTest {

    Cell cell;

    @Before
    public void runBeforeEveryTest() {
        cell = new Cell();
    }

    @Test
    public  void  set_bomb(){
        cell.setBomb();
        assertEquals(true, cell.isBomb());
    }

    @Test
    public  void  set_value(){
        char value = 8;
        cell.setValue(value);
        assertEquals(value, cell.getValue());
    }

    @Test
    public void set_issue(){
        cell.setIssue();
        assertEquals(true, cell.getIssue());
    }

    @Test
    public void unset_issue(){
        cell.unsetIssue();
        assertEquals(false, cell.getIssue());
    }

    @Test
    public void set_flag(){
        cell.setFlag();
        assertEquals(true, cell.isFlag());
    }
}
