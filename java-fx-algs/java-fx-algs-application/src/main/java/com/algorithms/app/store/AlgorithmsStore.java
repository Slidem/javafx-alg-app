package com.algorithms.app.store;


import com.algorithms.commmons.Algorithm;

import java.util.Optional;
import java.util.Set;

/**
 * @author slidem
 */
public interface AlgorithmsStore {

    Set<Algorithm> getAll();

    Optional<Algorithm> getByName(String algorithmName);

}
