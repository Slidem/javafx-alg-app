package com.algorithms.app.store.impl;

import ami.lightdi.annotations.Component;
import ami.lightdi.annotations.Inject;
import com.algorithms.app.store.AlgorithmsStore;
import com.algorithms.commmons.Algorithm;
import com.algorithms.commmons.display.AlgorithmDisplay;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * @author Mihai Alexandru
 */
@Component
public class AlgorithmsStoreImpl implements AlgorithmsStore {

    private Map<String, Algorithm> algorithmsMap;

    @Inject
    public AlgorithmsStoreImpl(List<Algorithm> algorithms, AlgorithmDisplay algorithmDisplay) {
        initStore(algorithms, algorithmDisplay);
    }

    @Override
    public Set<Algorithm> getAll() {
        return algorithmsMap.entrySet().stream().map(Entry::getValue).collect(Collectors.toSet());
    }

    @Override
    public Optional<Algorithm> getByName(String algorithmName) {
        return Optional.ofNullable(algorithmsMap.get(algorithmName));
    }

    private void initStore(List<Algorithm> algorithms, AlgorithmDisplay algorithmDisplay) {
        algorithmsMap = new HashMap<>();
        for (Algorithm algorithm : algorithms) {
            algorithm.setAlgorithmDisplay(algorithmDisplay);
            algorithmsMap.put(algorithm.getType().getName(), algorithm);
        }
    }

}
