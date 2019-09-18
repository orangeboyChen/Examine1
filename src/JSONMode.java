import java.util.*;

import com.google.gson.Gson;

public class JSONMode {
    private HashMap<String,String> map=new HashMap<String,String>();
    private HashMap<String,Object> map_Convert=new HashMap<String, Object>();
    public void addItem(String key,String value){
        map.put(key,value);
    }

    public String mapToJson(HashMap map){
        return new Gson().toJson(map);
    }

    public void convertMap(){
        Iterator it=map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,String> next=(Map.Entry<String, String>)it.next();
            if(map_Convert.containsKey(next.getValue())){
                if(map_Convert.get(next.getValue())instanceof List){
                    ((List)map_Convert.get(next.getValue())).add(next.getKey());
                }
                else{
                    List<String> list=new ArrayList<String>();
                    list.add((String)map_Convert.get(next.getValue()));
                    list.add(next.getKey());
                    map_Convert.put(next.getValue(),list);
                }
            }
            else{
                map_Convert.put(next.getValue(),next.getKey());
            }
        }
    }

    public HashMap getMap(){
        return map;
    }

    public HashMap getConvertMap(){
        return map_Convert;
    }
}
