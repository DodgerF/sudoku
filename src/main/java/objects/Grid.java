package objects;

import java.lang.reflect.Method;
import java.util.Random;

public class Grid {
    private Integer _dim;
    public Cell[][] massive;

    private final int ITERATIONS_AMOUNT;

    public Grid(int dim){
        ITERATIONS_AMOUNT = 10;
        _dim = dim;
        massive = new Cell[_dim * _dim][_dim * _dim];
        generateBaseGrid(_dim);
        mix();
        setCords();
    }
    public Integer getDim() {
        return _dim;
    }
    public void checkGrid(){
        for (int i = 0; i < _dim * _dim; i++) {
            for (int j = 0; j < _dim * _dim; j++) {
                System.out.print(massive[i][j].getValue());
            }
            System.out.println("\n");
        }
    }

    private void generateBaseGrid(int dim){
        for (int i = 0; i < dim * dim; i++) {
            for (int j = 0; j < dim * dim; j++) {
                massive[i][j] = new Cell();
                massive[i][j].setValue((i * dim + i / dim + j) % (dim * dim) + 1);
            }
        }
    }
    private void setCords() {
        for (int i = 0; i < _dim * _dim; i++) {
            for (int j = 0; j < _dim * _dim; j++) {
                massive[i][j].setCords(j, i);
            }
        }
    }
    private void mix(){
        String[] mixFunc = {"transposing",
                            "swapRowsSmall",
                            "swapColumnsSmall",
                            "swapRowsArea",
                            "swapColumnsArea"};

        for(int i = 0; i < ITERATIONS_AMOUNT; i++) {
            int idFunc = new Random().nextInt(mixFunc.length);

            try {
                Method method = getClass().getDeclaredMethod(mixFunc[idFunc]);
                method.invoke(this);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private void transposing(){
        Cell[][] newGrid = new Cell[_dim * _dim][_dim * _dim];
        for (int j = 0; j < _dim * _dim; j++){
            for (int i = 0; i < _dim * _dim; i++) {
                newGrid[i][j] = massive[j][i];
                newGrid[i][j].setCords(i, j);
            }
        }
        massive = newGrid;
    }
    private void swapRowsSmall() {
        int area = (int) (Math.random() * _dim);
        int line1 = (int) (Math.random() *  _dim);
        int N1 = area *  _dim + line1;

        int line2 = (int) (Math.random() * _dim);
        while (line1 == line2) {
            line2 = (int) (Math.random() * _dim);
        }

        int N2 = area * _dim + line2;

        Cell[] temp = massive[N1];
        massive[N1] = massive[N2];
        massive[N2] = temp;
    }

    private void swapColumnsSmall() {
        transposing();
        swapRowsSmall();
        transposing();
    }

    private void swapRowsArea() {
        int area1 = (int) (Math.random() * _dim);
        int area2 = (int) (Math.random() * _dim);
        while (area1 == area2) {
            area2 = (int) (Math.random() * _dim);
        }

        for (int i = 0; i < _dim; i++) {
            int N1 = area1 * _dim + i;
            int N2 = area2 * _dim + i;
            Cell[] temp = massive[N1];
            massive[N1] = massive[N2];
            massive[N2] = temp;
        }
    }

    private void swapColumnsArea() {
        transposing();
        swapRowsArea();
        transposing();
    }
}
