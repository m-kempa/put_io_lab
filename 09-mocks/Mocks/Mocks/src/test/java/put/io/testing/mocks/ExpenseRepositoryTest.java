package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;

import java.util.Collections;

public class ExpenseRepositoryTest {

    @Test
    public void testLoad(){
        IFancyDatabase mockedDatabase = mock(IFancyDatabase.class);
        when(mockedDatabase.queryAll()).thenReturn(Collections.emptyList());
        ExpenseRepository expense = new ExpenseRepository(mockedDatabase);
        expense.loadExpenses();
        InOrder inOrder = inOrder(mockedDatabase);
        inOrder.verify(mockedDatabase).connect();
        inOrder.verify(mockedDatabase).queryAll();
        inOrder.verify(mockedDatabase).close();
        assertEquals(0, expense.getExpenses().size());
    }

    @Test
    public void testSave(){
        IFancyDatabase mockedDatabase = mock(IFancyDatabase.class);
        ExpenseRepository expense = new ExpenseRepository(mockedDatabase);
        for (int i = 0; i < 5; i++) {
            expense.addExpense(new Expense());
        }
        expense.saveExpenses();
        InOrder inOrder = inOrder(mockedDatabase);
        inOrder.verify(mockedDatabase).connect();
        inOrder.verify(mockedDatabase, times(5)).persist(any(Expense.class));
        inOrder.verify(mockedDatabase).close();
    }

}
