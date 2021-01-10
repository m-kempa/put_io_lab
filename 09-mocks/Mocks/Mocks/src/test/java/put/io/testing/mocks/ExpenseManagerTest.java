package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;
import put.io.students.fancylibrary.service.FancyService;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpenseManagerTest {

    @Test
    public void testCalculate(){
        List <Expense> lista = new ArrayList<Expense>();
        for (int i = 0; i < 3; i++) {
            Expense expense = new Expense();
            expense.setAmount(2);
            lista.add(expense);
        }
        ExpenseRepository mockedRepository = mock(ExpenseRepository.class);
        when(mockedRepository.getExpenses()).thenReturn(lista);
        ExpenseManager manager = new ExpenseManager(mockedRepository, new FancyService());
        assertEquals(6, manager.calculateTotal());
    }

    @Test
    public void testCalculateTotal(){
        List <Expense> listaA = new ArrayList<Expense>();
        for (int i = 0; i < 3; i++) {
            Expense expense = new Expense();
            expense.setAmount(2);
            listaA.add(expense);
        }
        List <Expense> listaB = new ArrayList<Expense>();
        for (int i = 0; i < 3; i++) {
            Expense expense = new Expense();
            expense.setAmount(5);
            listaB.add(expense);
        }
        ExpenseRepository mockedRepository = mock(ExpenseRepository.class);
        when(mockedRepository.getExpensesByCategory(anyString())).thenReturn(Collections.<Expense>emptyList());
        when(mockedRepository.getExpensesByCategory(eq("Car"))).thenReturn(listaA);
        when(mockedRepository.getExpensesByCategory(eq("Home"))).thenReturn(listaB);
        ExpenseManager manager = new ExpenseManager(mockedRepository, new FancyService());
        assertEquals(0, manager.calculateTotalForCategory("Sport"));
        assertEquals(6, manager.calculateTotalForCategory("Car"));
        assertEquals(15, manager.calculateTotalForCategory("Home"));
        // 5.1 TAK
    }

    @Test
    public void testInDollars() throws ConnectException {
        List <Expense> lista = new ArrayList<Expense>();
        for (int i = 0; i < 4; i++) {
            Expense expense = new Expense();
            expense.setAmount(2);
            lista.add(expense);
        }
        ExpenseRepository mockedRepository = mock(ExpenseRepository.class);
        FancyService mockedService = mock(FancyService.class);
        ExpenseManager manager = new ExpenseManager(mockedRepository, mockedService);
        when(mockedService.convert(eq(0.0),eq("PLN"),eq("USD"))).thenThrow(new ConnectException());
        assertEquals(-1, manager.calculateTotalInDollars());

        when(mockedRepository.getExpenses()).thenReturn(lista);
        when(mockedService.convert(anyDouble(),eq("PLN"),eq("USD"))).thenAnswer(new Answer(){
            public Object answer(InvocationOnMock onMock){
                return Double.parseDouble(onMock.getArguments()[0].toString())*0.25;

            }
        });
        assertEquals(2,manager.calculateTotalInDollars());
    }
}
