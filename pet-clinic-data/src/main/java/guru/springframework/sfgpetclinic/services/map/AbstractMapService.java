package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;


public abstract class AbstractMapService<T extends BaseEntity,ID extends Long> {
    protected Map<Long,T> map=new HashMap<Long,T>();

    public Set<T> findAll(){
       return new HashSet<>(map.values());
    }

    public T findById(ID id){
        return map.get(id);
    }

    public T save(T object){

        if(object!=null) {
            if(object.getId()==null)   object.setId(this.getNextId());
            map.put(object.getId(),object);
        }else throw new RuntimeException("Object cannot be null");
        return object;
    }


    public void deleteById(ID id){
        map.remove(id);
    }
    public void delete(T object){
        map.entrySet().removeIf(entry -> entry.equals(object));
    }
    private Long getNextId(){
        if(map.keySet().size()==0) return 1L;
        return ((long)Collections.max(map.keySet()))+1;
    }


}
