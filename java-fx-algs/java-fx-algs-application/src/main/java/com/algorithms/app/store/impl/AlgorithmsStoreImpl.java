package com.algorithms.app.store.impl;

import com.algorithms.app.display.AlgorithmDisplayImpl;
import com.algorithms.app.store.AlgorithmsStore;
import com.algorithms.commmons.Algorithm;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * @author Mihai Alexandru
 */
public enum AlgorithmsStoreImpl implements AlgorithmsStore {

    INSTANCE;

    private Map<String, Algorithm> algorithmsMap;

    private AlgorithmsStoreImpl() {
        initStore();
    }

    @Override
    public Set<Algorithm> getAll() {
        return algorithmsMap.entrySet().stream().map(Entry::getValue).collect(Collectors.toSet());
    }

    @Override
    public Optional<Algorithm> getByName(String algorithmName) {
        return Optional.ofNullable(algorithmsMap.get(algorithmName));
    }

    private void initStore() {
        ServiceLoader<Algorithm> serviceLoader = ServiceLoader.load(Algorithm.class);
        algorithmsMap = new HashMap<>();
        for (Algorithm service : serviceLoader) {
            service.setAlgorithmDisplay(AlgorithmDisplayImpl.INSTANCE);
            algorithmsMap.put(service.getType().getName(), service);
        }
    }

}
