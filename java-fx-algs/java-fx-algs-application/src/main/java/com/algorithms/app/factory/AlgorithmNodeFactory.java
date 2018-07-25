package com.algorithms.app.factory;

import com.algorithms.commmons.Algorithm;
import javafx.scene.control.TitledPane;

public interface AlgorithmNodeFactory {

    TitledPane createAlgorithmTitlePane(Algorithm algorithm);

}
