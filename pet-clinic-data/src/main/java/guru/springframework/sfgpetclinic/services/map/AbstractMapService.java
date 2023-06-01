package guru.springframework.sfgpetclinic.services.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T,ID> {
    protected Map<ID,T> map=new HashMap<ID,T>();

    public Set<T> findAll(){
       return new HashSet<>(map.values());
    }

    public T findById(ID id){
        return map.get(id);
    }

    public T save(ID id,T object){
        return map.put(id,object);
    }

    public void deleteById(ID id){
        map.remove(id);
    }
    public void deleteByObject(T object){
        map.entrySet().removeIf(entry -> entry.equals(object));
    }


}
