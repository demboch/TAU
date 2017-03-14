package pl.edu.pjwstk.lab2;

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

    public List<Double> sub(VectorImpl wektor) {
        if(this.values.size() == wektor.values.size())
        {
            for(int i=0;i<this.values.size();i++){
                this.values.set(i, this.values.get(i)-wektor.values.get(i));
            }
        }
        return this.values;
    }

    public List<Double> addVectors(List<Double> wektor1, List<Double> wektor2) {
        List<Double> suma = new ArrayList<Double>();
        for (int i = 0; i < wektor1.size(); i++)
            suma.add(wektor1.get(i) + wektor2.get(i));
        return suma;
    }

    public List<Double> subVectors(List<Double> wektor1, List<Double> wektor2) {
        List<Double> roznica = new ArrayList<Double>();
        for (int i = 0; i < wektor1.size(); i++)
            roznica.add(wektor1.get(i) - wektor2.get(i));
        return roznica;
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }
}