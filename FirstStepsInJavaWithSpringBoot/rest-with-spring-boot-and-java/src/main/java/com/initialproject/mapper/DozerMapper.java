package com.initialproject.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerMapper {
	
	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public static <O , D> D parseObject(O origin , Class<D> destination){
		return mapper.map(origin, destination);
		
	}
	
    public static <O , D> List<D> parseListObject(List<O> origin , Class<D> destination) {
    	
    	List<D>list = new ArrayList<>();
    	for(O o : origin) {
    		list.add(mapper.map(o,destination));
    	}
    	return list;
    }


}
