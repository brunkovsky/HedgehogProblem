package Main;

import Data.HedgehogField;
import Data.DataWorker;

public class Main {
    public static void main(String[] args) {
        DataWorker dataWorker = new DataWorker();
        HedgehogField field = dataWorker.readInputField();
        int result = field.getResult();
        dataWorker.writeOutputData(result);
    }
}
