package com.newswebsite.main.mapper;

import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionMapper extends ModelMapper {
    public CollectionMapper() {
        super();
    }

    public <T, U> List<U> map(List<T> src, Class<U> des) {
        if (src == null || src.isEmpty()) return Collections.emptyList();
        return src.stream()
                .map(t -> map(t, des))
                .collect(Collectors.toList());
    }

    public <T, K, V> Map<K, V> map(Collection<T> src, Function<T, K> keyMapper, Function<T, V> valueMapper) {
        if (src == null || src.isEmpty()) return Collections.emptyMap();
        return src.stream()
                .collect(Collectors.toMap(keyMapper, valueMapper));
    }
}
