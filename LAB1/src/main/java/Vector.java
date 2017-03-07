import java.util.List;

public interface Vector {

    public List<Double> add(VectorImpl wektor);
    //List<Double> addVectors(List<Double> wektor1, List<Double> wektor2);
    public List<Double> getValues();
    public void setValues(List<Double> values);

}
