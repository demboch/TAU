import java.util.ArrayList;
import java.util.List;

public class VectorImpl implements Vector {

    List<Double> values = new ArrayList<Double>();

    public VectorImpl(){}

    public VectorImpl(List<Double> v){
        this.values = v;
    }

    public List<Double> add(VectorImpl wektor) {
        if(this.values.size() == wektor.values.size())
        {
            for(int i=0;i<this.values.size();i++){
                this.values.set(i, this.values.get(i)+wektor.values.get(i));
            }
        }
        return this.values;
    }

    public static List<Double> addVectors(List<Double> wektor1, List<Double> wektor2) {
        List<Double> suma = new ArrayList<Double>();
        for (int i = 0; i < wektor1.size(); i++)
            suma.add(wektor1.get(i) + wektor2.get(i));
        return suma;
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }
}