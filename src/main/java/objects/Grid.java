package objects;

import java.lang.reflect.Method;
import java.util.Random;

public class Grid {
    private Integer _dim;
    private Integer[][] _grid;

    private final int ITERATIONS_AMOUNT;

    public Grid(int dim){
        ITERATIONS_AMOUNT = 10;
        _dim = dim;
        _grid = new Integer[_dim * _dim][_dim * _dim];
        generateBaseGrid(_dim);
        mix();
    }
    public void checkGrid(){
        for (int i = 0; i < _dim * _dim; i++) {
            System.out.println("\n");
            for (int j = 0; j < _dim * _dim; j++) {
                System.out.print(_grid[i][j]);
            }
        }
    }
    private void generateBaseGrid(int dim){
        for (int i = 0; i < dim * dim; i++) {
            for (int j = 0; j < dim * dim; j++) {
                _grid[i][j] = (i * dim + i / dim + j) % (dim * dim) + 1;
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
        Integer[][] newGrid = new Integer[_dim * _dim][_dim * _dim];
        for (int j = 0; j < _dim * _dim; j++){
            for (int i = 0; i < _dim * _dim; i++) {
                newGrid[i][j] = _grid[j][i];
            }
        }
        _grid = newGrid;
    }
    public void swapRowsSmall() {
        int area = (int) (Math.random() * _dim);
        int line1 = (int) (Math.random() *  _dim);
        int N1 = area *  _dim + line1;

        int line2 = (int) (Math.random() * _dim);
        while (line1 == line2) {
            line2 = (int) (Math.random() * _dim);
        }

        int N2 = area * _dim + line2;

        Integer[] temp = _grid[N1];
        _grid[N1] = _grid[N2];
        _grid[N2] = temp;
    }

    public void swapColumnsSmall() {
        transposing();
        swapRowsSmall();
        transposing();
    }

    public void swapRowsArea() {
        int area1 = (int) (Math.random() * _dim);
        int area2 = (int) (Math.random() * _dim);
        while (area1 == area2) {
            area2 = (int) (Math.random() * _dim);
        }

        for (int i = 0; i < _dim; i++) {
            int N1 = area1 * _dim + i;
            int N2 = area2 * _dim + i;
            Integer[] temp = _grid[N1];
            _grid[N1] = _grid[N2];
            _grid[N2] = temp;
        }
    }

    public void swapColumnsArea() {
        transposing();
        swapRowsArea();
        transposing();
    }

}
