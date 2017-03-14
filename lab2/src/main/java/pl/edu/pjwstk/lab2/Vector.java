package pl.edu.pjwstk.lab2;

import java.util.List;

public interface Vector {

    public List<Double> add(VectorImpl wektor);
    public List<Double> addVectors(List<Double> wektor1, List<Double> wektor2);
    public List<Double> subVectors(List<Double> wektor1, List<Double> wektor2);
    public List<Double> getValues();
    public void setValues(List<Double> values);

}
