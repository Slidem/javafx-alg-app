package com.algorithms.strings.problems.kthpermutation;

/**
 * @author Mihai Alexandru
 * @date 11.08.2018
 */
public class KthPermutation {

    private final KthPermutationObserver kthPermutationObserver;

    private final int[] array;

    private EndsTester goLeftTester = (sv, hv, ev) -> (sv > hv && ev > hv);

    private EndsTester goRightTester = (sv, hv, ev) -> (sv < hv && ev < hv);

    private KthPermutation(KthPermutationObserver kthPermutationObserver, int[] array) {
        this.kthPermutationObserver = kthPermutationObserver;
        this.array = array;
    }

    public static KthPermutation createWithNoObserver(int[] array) {
        return new KthPermutation(new EmptyKthPermutationObserver(), array);
    }


    public static KthPermutation create(int[] array, KthPermutationObserver observer) {
        return new KthPermutation(observer, array);
    }

    public int getKthPermutation() {
        IdxHolder ih = new IdxHolder();
        while (ih.isValid()) {
            if (isSplit(ih.half)) {
                return ih.half;
            }

            Directions direction = computeDirection(ih);
            if (direction == Directions.DEFAULT) {
                return ih.start;
            }
            ih.computeIndexes(direction);
        }
        return ih.half;
    }

    private boolean isSplit(int idx) {
        if (idx == 0 || idx == array.length) {
            return true;
        }
        int prevValue = array[idx - 1];
        int nextValue = array[idx + 1];
        int idxValue = array[idx];
        // 5, 6, 7, {1}, 2, 3, 4. idx of value {1} is a split. it's neighbours are both bigger than him.
        return (prevValue > idxValue) && (idxValue < nextValue);
    }

    private Directions computeDirection(IdxHolder idxHolder) {
        if (testEnds(idxHolder, goLeftTester)) {
            return Directions.LEFT;
        } else if (testEnds(idxHolder, goRightTester)) {
            return Directions.RIGHT;
        }
        return Directions.DEFAULT;
    }

    private boolean testEnds(IdxHolder idxHolder, EndsTester testEndsPredicate) {
        int startValue = array[idxHolder.start];
        int endValue = array[idxHolder.end];
        int halfValue = array[idxHolder.half];
        return testEndsPredicate.test(startValue, halfValue, endValue);
    }

    private class IdxHolder {

        private int start = 0;

        private int end = array.length - 1;

        private int half = end / 2;

        private void computeIndexes(Directions dir) {
            int aux = 0;
            switch (dir) {
                case LEFT:
                    aux = end;
                    end = half;
                    half = (aux - start) / 2;
                    break;
                case RIGHT:
                    start = half;
                    if (end - half == 1) {
                        half = end;
                    } else {
                        half = (half + end) / 2;
                    }
                    break;
                case DEFAULT:
            }
        }

        private boolean isValid() {
            return start != half && end != half;
        }

        private int getStartIdxValue() {
            return array[start];
        }

        private int getEndIdxValue() {
            return array[end];
        }

        private int getHalfIdxValue() {
            return array[half];
        }
    }

    private enum Directions {
        LEFT, RIGHT, DEFAULT;
    }

    private static interface EndsTester {
        boolean test(int sV, int hV, int eV);
    }


}
