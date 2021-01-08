package sample.ch.zli.school.feuchter.project;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int tileSize = 40;
    private static final int width = 800;
    private static final int height = 600;

    //anzahl tiles pro grid auf der ROW & COLUMN
    private static final int xTiles = width / tileSize;
    private static final int yTiles = height / tileSize;

    //matrix für die GUI
    private Tile[][] matrix = new Tile[xTiles][yTiles];
    private Scene scene;

    //TODO: MARK BOMB IN MATRIX

    //TODO: FXML DATEI

    @Override
    public void start(Stage stage) throws Exception {
        // try catch exception incase something goes wrong
        try {
            scene = new Scene(initGui());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            // i was thinking maybe a throw but, this can be caught and understood
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Parent initGui() {
        Pane root = new Pane();
        root.setPrefSize(width, height);

        // initialisiert matrix mit 20% Bomben
        for (int y = 0; y < yTiles; y++) {
            for (int x = 0; x < xTiles; x++) {
                Tile tile = new Tile(x, y, Math.random() < 0.2);

                matrix[x][y] = tile;
                root.getChildren().add(tile);
            }
        }

        // bomben aufzählen pro tile und GUI aktualisieren
        for (int y = 0; y < yTiles; y++) {
            for (int x = 0; x < xTiles; x++) {
                Tile tile = matrix[x][y];

                if (tile.Bomb)
                    continue;

                long bombs = getNeighbours(tile).stream().filter(t -> t.Bomb).count();

                if (bombs > 0)
                    tile.text.setText(String.valueOf(bombs));
            }
        }

        return root;
    }

    /*
     * @param tile  tile den man gerade selektiert hat
     */
    private List<Tile> getNeighbours(Tile tile) {
        List<Tile> neighbors = new ArrayList<>();

        int[] points = new int[]{-1, -1, -1, 0, -1, 1, 0, -1, 0, 1, 1, -1, 1, 0, 1, 1};

        for (int i = 0; i < points.length; i++) {
            int dx = points[i];
            int dy = points[++i];

            int newX = tile.x + dx;
            int newY = tile.y + dy;

            if (newX >= 0 && newX < xTiles
                    && newY >= 0 && newY < yTiles) {
                neighbors.add(matrix[newX][newY]);
            }
        }

        return neighbors;
    }

    private List<Tile> getNeighbours2(Tile tile){
        List<Tile> neighbours = new ArrayList<>();

        int x = tile.x;
        int y = tile.y;
        int x1, y1;

        for(x1 = x-1; x1 < x+2; x1++){
            for(y1 = y-1; y1 < y+2; y1++){
                if (x1 < 0) continue;       // if coordinates
                if (y1 < 0) continue;       // out of bounds
                if (x1 >= xTiles) continue; // skip
                if (y1 >= yTiles) continue;

                neighbours.add(matrix[x1][y1]);
            }
        }
        return neighbours;
    }


    private class Tile extends StackPane {
        private int x;
        private int y;
        private boolean Bomb;
        private boolean isOpen = false;

        private Rectangle border = new Rectangle(tileSize - 2, tileSize - 2);
        private Text text = new Text();

        // constructor
        // setzt die verschiedenen variablen & grössen etc.
        /*
         * @param x     x coordinate
         * @param y     y coordinate
         *
         * @param Bomb  boolean for whether or not a Tile is a bomb.
         */
        public Tile(int x, int y, boolean Bomb) {
            this.x = x;
            this.y = y;
            this.Bomb = Bomb;

            border.setStroke(Color.LIGHTGRAY);

            text.setFont(Font.font(18));
            text.setText(Bomb ? "X" : "");
            text.setVisible(false);

            getChildren().addAll(border, text);

            setTranslateX(x * tileSize);
            setTranslateY(y * tileSize);

            // setOnMouseClicked da Tile kein Button ist.
            setOnMouseClicked(e -> open());

        }

        public void open() {
            if (isOpen)
                return;

            // falls Bombe auf Tile:
            //      Game Over & Reset.
            if (Bomb) {
                System.out.println("Game Over");
                scene.setRoot(initGui());
                return;
            }

            // falls isOpen true returned hat:
            //      text = visible
            //      border = null (tile wird aufgeblendet)
            if (isOpen = true) {
                text.setVisible(true);
                border.setFill(null);
            }

            // falls text leer ist:
            //      nachbar holen & open() methode wiederholen für die nächste nachbar bis auf bombe
            if (text.getText().isEmpty()) {
                getNeighbours(this).forEach(Tile::open);
            }
        }
    }
}
