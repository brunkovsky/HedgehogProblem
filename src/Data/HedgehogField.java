package Data;

import java.util.*;

public class HedgehogField {
    private int[][] area;
    private int numberOfDownMoves;
    private int numberOfRightMoves;
    private int numberOfMoves;

    public HedgehogField(int[][] area) {
        this.area = area;
        this.numberOfDownMoves = area.length - 1;
        this.numberOfRightMoves = area[0].length - 1;
        this.numberOfMoves = numberOfDownMoves + numberOfRightMoves;
    }

    public int getResult() {
        int numberOfVariantsOfMoves = getNumberOfVariantsOfMoves();
        Set<Integer> setOfMoves = fillFullSet(numberOfVariantsOfMoves);
        Set<String> setOfMovesInString = convertToString(setOfMoves);
        Map<String, Integer> map = fillMovesMap(setOfMovesInString);
        return Collections.max(map.values());
    }

    @Override
    public String toString() {
        return "HedgehogField{" +
                "area = " + Arrays.deepToString(area) +
                '}';
    }

    private Map<String, Integer> fillMovesMap(Set<String> setOfMovesInString) {
        Map<String, Integer> result = new HashMap<>();
        int numberOfMoves = numberOfDownMoves + numberOfRightMoves;
        for (String currentMoves :setOfMovesInString) {
            char[] currentMove = currentMoves.toCharArray();
            int resultInt = area[0][0];
            int x = 0, y = 0;
            for (int i = 0; i < numberOfMoves; i++) {
                if (currentMove[i] == '1') {
                    x++;
                } else {
                    y++;
                }
                resultInt += area[x][y];
            }
            result.put(currentMoves, resultInt);
        }
        return result;
    }

    private Set<String> convertToString(Set<Integer> setOfMoves) {
        Set<String> result = new HashSet<>();
        String format = "%" + (numberOfRightMoves + numberOfDownMoves) + "s";
        for (Integer integer :setOfMoves) {
            if (Integer.bitCount(integer) == numberOfDownMoves) {
                result.add(String.format(format, Integer.toBinaryString(integer)).replace(' ', '0'));
            }
        }
        return result;
    }

    private Set<Integer> fillFullSet(int variantsOfMoves) {
        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < variantsOfMoves; i++) {
            result.add(i);
        }
        return result;
    }

    private int getNumberOfVariantsOfMoves() {
        int result = 1;
        for (int i = 0; i < numberOfMoves; i++) {
            result <<= 1;
        }
        return result;
    }
}
