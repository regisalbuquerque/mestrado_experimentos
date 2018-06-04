package testes;

import java.util.Map;
import java.util.TreeMap;

public class TesteTreeMap {
    public static void main(String[] args) {
        System.out.println("experimentos.preliminares.TesteTreeMap.main()");
        
        
        
        TreeMap<Double, String> map = new TreeMap<>();
        


        map.put(5.0, "Cinco");
        map.put(1.0, "Um");
        map.put(10.0, "Dez");
        map.put(7.0, "Sete");
        map.put(8.0, "Oito");
        map.put(5.0, "Zero");
        
        for (Map.Entry<Double, String> entry : map.entrySet()) {
            Double key = entry.getKey();
            String value = entry.getValue();
            
            System.out.println(key + " => " + value);
        }
        
        System.out.println("Size:" + map.size());
        
        int median_index;
        if (map.size() % 2 == 0)
            median_index = map.size()/2-1;
        else
            median_index = map.size()/2;
        
        System.out.println("Median Index:" + median_index);
        
        Double key = (Double) map.keySet().toArray()[median_index];
        String value = map.get(key);
        
        System.out.println("Median :" + value);
        
    }
}
