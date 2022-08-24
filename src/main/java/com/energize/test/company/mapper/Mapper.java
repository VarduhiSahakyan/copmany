package com.energize.test.company.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper{

    public <E, D> D convertToDto(E entity, Class<D> dto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(entity, dto);
    }

    public <E, D> E convertToEntity(D dto, Class<E> entity) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, entity);
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        ModelMapper mapper = new ModelMapper();
        return source.stream()
                .map(element -> mapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
