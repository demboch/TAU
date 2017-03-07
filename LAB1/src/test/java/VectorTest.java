import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class VectorTest {

    @Test
    public void Test1() {


        List<Double> wektory = Arrays.asList(2.0,4.0,6.0);
        List<Double> wektory1 = Arrays.asList(3.0,5.0,8.0);
        List<Double> test = Arrays.asList(5.0,9.0,14.0);

        VectorImpl v1 = new VectorImpl(wektory);
        VectorImpl v2 = new VectorImpl(wektory1);

        assertEquals("Dodawanie wektorow", v1.add(v2), test);

    }
}

