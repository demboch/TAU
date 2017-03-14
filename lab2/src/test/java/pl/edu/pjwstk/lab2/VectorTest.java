package pl.edu.pjwstk.lab2;

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
    public void TestAddVectors() {


        List<Double> vector1 = Arrays.asList(2.0,4.0,6.0);
        List<Double> vector2 = Arrays.asList(3.0,5.0,8.0);
        List<Double> addVector = Arrays.asList(5.0,9.0,14.0);

        VectorImpl v1 = new VectorImpl(vector1);
        VectorImpl v2 = new VectorImpl(vector2);

        assertEquals("Dodawanie wektorow", v1.add(v2), addVector);

    }

//    @Test (expected=NullPointerException.class)
//    public void TestSubNullException() {
//        List<Double> vector1 = Arrays.asList(3.0,5.0,7.0);
//        List<Double> vector3 = Arrays.asList(7.0,12.0,17.0);
//
//        VectorImpl v1 = new VectorImpl(vector1);
//        VectorImpl v2 = new VectorImpl();
//        VectorImpl v3 = new VectorImpl(vector3);
//        assertEquals("Odejmowanie wektorow", v3.sub(v2), v1);
//    }

    @Test
    public void TestSubVectors() {

        List<Double> subVector = Arrays.asList(3.0,6.0,8.0);
        List<Double> vector1 = Arrays.asList(4.0,10.0,12.0);
        List<Double> vector2 = Arrays.asList(7.0,16.0,20.0);

        VectorImpl v1 = new VectorImpl(subVector);
        VectorImpl v2 = new VectorImpl(vector1);
        VectorImpl v3 = new VectorImpl(vector2);

        assertEquals("Odejmowanie wektorow", v3.sub(v2), subVector);
    }
}

