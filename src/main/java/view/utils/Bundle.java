package view.utils;

import java.util.LinkedHashMap;

public class Bundle {

    private final LinkedHashMap<String,Object> dataBundle = new LinkedHashMap<>();

    public void setBundle(String key, Object data){
        dataBundle.put(key,data);
    }
    public Object getBundle(String key){
        return dataBundle.get(key);
    }
    public void free(){
        LinkedHashMap<String, Object> dataBundle = (LinkedHashMap<String, Object>) this.dataBundle.clone();
        dataBundle.forEach((s, o) -> this.dataBundle.remove(s));
    }
}
