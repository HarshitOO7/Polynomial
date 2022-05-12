import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;
import java.lang.Math;

public class Polynomial implements Comparable<Polynomial> {
    private TreeMap<Integer,Integer> poly;
    private int degree;
    
    public Polynomial() {
        //a comprator from collections claass to reverse the order
        poly = new TreeMap<>(Collections.reverseOrder());
        degree =-1;
    }
    public Polynomial(int power,int coefficient){
        poly = new TreeMap<>(Collections.reverseOrder());
        if(coefficient>0){//if the value exists
        //for power cannot be negtive
            try {
                checkpower(power);
            } catch (Exception e) {
                System.out.println("A problem occured");
            }
            poly.put(power, coefficient);
        }
        
    }
    public Polynomial(TreeMap<Integer,Integer> map){
        poly = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            poly.put(entry.getKey(), entry.getValue());
        }
        
    }
    public Polynomial(Polynomial p) {
        poly = new TreeMap<>(Collections.reverseOrder());
        poly.putAll(p.getP());
        
    }
    //copy constructor
    public Polynomial(Map<Integer, Integer> copy) {
        poly = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry<Integer, Integer> entry: copy.entrySet()) {
            poly.put(entry.getKey(), entry.getValue());
        }
        
    }

    // to get the add polynomial in add
    public Map<Integer, Integer> getP(){
        Map<Integer,Integer> temp = new TreeMap<>(poly);
        return temp;
    }
    public void add(Polynomial add){
        Map<Integer, Integer> temp = new TreeMap<>(Collections.reverseOrder());
        temp.putAll(poly);
        for (Map.Entry<Integer, Integer> entry: add.getP().entrySet()) {
            if(temp.containsKey(entry.getKey())){// if the same power exist, just increment coefficient
                int value =temp.get(entry.getKey())+entry.getValue();
                temp.put(entry.getKey(),value );
                if((temp.get(entry.getKey())+entry.getValue())==0 ){//if coeff is zero, then remove
                    temp.remove(entry.getKey(),value);
                    continue;
                }
                
            }
            else{
                temp.put(entry.getKey(), entry.getValue());
            }
        }
        poly.clear();
        poly.putAll(temp);
        //a new tree map for using firstKey funcetion
        TreeMap<Integer,Integer> deg = (TreeMap<Integer,Integer>)poly;
        this.degree = deg.firstKey();//set the degree
        
    }
    public static Polynomial add(Polynomial p1, Polynomial p2){
        TreeMap<Integer, Integer> p3 = new TreeMap<>(Collections.reverseOrder());
        
        for (Map.Entry<Integer, Integer> entry: p1.getP().entrySet()) {
            if(p2.getP().containsKey(entry.getKey())){// if the same power exist, just increment coefficient
                int value =p2.getP().get(entry.getKey())+entry.getValue();
                p3.put(entry.getKey(),value);
                if((p2.getP().get(entry.getKey())+entry.getValue())==0 ){//if coeff is zero, then remove
                    p3.remove(entry.getKey(),value);
                    continue;
                }
                
            }
            else{
                p3.put(entry.getKey(), entry.getValue());
            }
        }
        Polynomial temp = new Polynomial(p3);
        return temp;
    }
    public Map<Integer,Integer> getMap() {
                return this.poly;
        }
    public void subtract(Polynomial sub){
        Map<Integer, Integer> temp = new TreeMap<>(Collections.reverseOrder());
        temp.putAll(poly);
        for (Map.Entry<Integer, Integer> entry: sub.getP().entrySet()) {
            if(temp.containsKey(entry.getKey())){// if the same power exist, just increment coefficient
                int value =temp.get(entry.getKey())-entry.getValue();
                temp.put(entry.getKey(),value );
                if((temp.get(entry.getKey())+entry.getValue())==0 ){//if coeff is zero, then remove
                    temp.remove(entry.getKey(),value);
                    continue;
                }
   
            }
            else{
                temp.put(entry.getKey(), entry.getValue()*-1);
            }
        }
        poly.clear();
        poly.putAll(temp);
        //a new tree map for using firstKey funcetion
        TreeMap<Integer,Integer> deg = (TreeMap<Integer,Integer>)poly;
        this.degree = deg.firstKey();//set the degree
    }
    
    public static Polynomial subtract(Polynomial p1, Polynomial p2){
        TreeMap<Integer, Integer> p3 = new TreeMap<>(Collections.reverseOrder());
        
        for (Map.Entry<Integer, Integer> entry: p1.getP().entrySet()) {
            if(p2.getP().containsKey(entry.getKey())){// if the same power exist, just increment coefficient
                int value =p2.getP().get(entry.getKey())+entry.getValue();
                p3.put(entry.getKey(),value);
                if((p2.getP().get(entry.getKey())-entry.getValue())==0 ){//if coeff is zero, then remove
                    p3.remove(entry.getKey(),value);
                    continue;
                }
                
            }
            else{
                p3.put(entry.getKey(), entry.getValue()*-1);
            }
        }
        Polynomial temp = new Polynomial(p3);
        return temp;
    }
    public Polynomial multiply(Polynomial mul){
        Polynomial temp = new Polynomial();
        Map<Integer,Integer> map = new TreeMap<>();
        map.putAll(poly);
        temp= (mul);
        for (Map.Entry<Integer,Integer> i : map.entrySet()){
            for (Map.Entry<Integer,Integer> j : temp.getP().entrySet()){//power will get added and coeff will multiply
                mul.add(new Polynomial(i.getKey() + j.getKey(), i.getValue() * j.getValue()));
            }
        }
        return mul;
    }
    //returns degree
    public int getDegree() {
        return degree;
    }
    //sets degree
    public void setDegree(int degree) {
        this.degree = degree;
    }
    public int coefficient(int power){
        return poly.get(power);
    }
    public void changeCoefficient(int power , int newcoeff){
        Map<Integer, Integer> temp = new TreeMap<>(Collections.reverseOrder());
        temp.putAll(poly);
        
        for (Map.Entry<Integer, Integer> entry: temp.entrySet()) {
            if(entry.getKey() == power){
                poly.put(power,newcoeff);
            }
        }
    }
    public void removeTerm(int power){
        Map<Integer, Integer> temp = new TreeMap<>(Collections.reverseOrder());
        temp.putAll(poly);
        for (Map.Entry<Integer, Integer> entry: temp.entrySet()) {
            if(entry.getKey() == power){
                poly.remove(power);
            }
        }
    }
    public double evaluate(double val){
        double res = 0;
        for (Map.Entry<Integer, Integer> entry: poly.entrySet()) {
            int power = entry.getKey();
            int coeff = entry.getValue();
            res += Math.pow(val,power)*coeff;
            
        }
        return res;
    }
    //for exception handling
    private boolean checkpower(int power){
        if(power<0){
            throw new IllegalArgumentException(" Power of a term can't be negative. The term ignored");
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Polynomial other = (Polynomial) obj;
        if (poly == null) {
            if (other.poly != null)
                return false;
        } else if (!poly.equals(other.poly))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Polynomial [degree=" + degree + ", poly=" + poly + "]";
    }
    
    @Override
    public int compareTo(Polynomial obj) {
        if (this == obj)
            return 1;
        if (obj == null)
            return -1;
        if (getClass() != obj.getClass())
            return -1;
        Polynomial other = (Polynomial) obj;
        if (poly == null) {
            if (other.poly != null)
                return -1;
        } else if (!poly.equals(other.poly))
            return -1;
        return 0;
    }
    
}
