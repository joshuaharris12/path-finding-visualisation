package org.joshuaharris.visualisation.UI;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import org.joshuaharris.visualisation.exceptions.ButtonNotFoundException;
import org.joshuaharris.visualisation.exceptions.InvalidButtonIdException;
import org.joshuaharris.visualisation.representations.AlgorithmType;
import org.joshuaharris.visualisation.representations.Graph;
import org.joshuaharris.visualisation.representations.Square;

import java.util.List;

public class View {
    private static final String FREE_SPACE_STYLE = "-fx-background-color: #ffffff";
    private static final String WALL_STYLE = "-fx-background-color: #002626";
    private static final String PATH_STYLE = "-fx-background-color: #95C623";
    private static final String START_SQUARE_STYLE = "-fx-background-color: blue";
    private static final String TARGET_SQUARE_STYLE = "-fx-background-color: red";
    private int height;
    private int width;
    private Button[][] gridButtons;
    private Graph graph;
    private Pane pane;

    private Square startSquare;
    private Square targetSquare;


    public View(int height, int width) {
        this.height = height;
        this.width = width;
        this.gridButtons = new Button[height][width];
        this.graph = new Graph(height, width);
        createView();
        this.startSquare = null;
        this.targetSquare = null;
    }

    public Pane getView() {
        return this.pane;
    }

    private void createView() {
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);

        for (int row = 0; row < this.height; ++row) {
            for (int col = 0; col < this.width; ++col) {
                Button btn = new Button();
                btn.setId(String.format("%d-%d", row, col));
                btn.setStyle(FREE_SPACE_STYLE);
                btn.setOnAction(this::handleButtonPress);
                btn.setMinSize(30,30);

                this.gridButtons[row][col] = btn;
                gridPane.add(btn, col, row);
                gridPane.setMargin(btn, new Insets(1,1,1,1));
            }
        }

        BorderPane rootPane = new BorderPane();
        rootPane.setCenter(gridPane);
        rootPane.setBottom(createBottomBar());
        this.pane = rootPane;
    }



    private Pane createBottomBar() {
        HBox bottomBar = new HBox();

        Button clearBtn = new Button("Clear");
        clearBtn.setCursor(Cursor.HAND);
        clearBtn.setOnAction(this::clearGrid);

        Button dfsBtn = new Button("Depth-first Search");
        dfsBtn.setCursor(Cursor.HAND);
        dfsBtn.setOnAction(a -> performAlgorithm(AlgorithmType.DFS));

        Button bfsBtn = new Button("Breadth-first Search");
        bfsBtn.setCursor(Cursor.HAND);
        bfsBtn.setOnAction(a -> performAlgorithm(AlgorithmType.BFS));

        bottomBar.getChildren().addAll(dfsBtn, bfsBtn, clearBtn);
        return bottomBar;
    }

    private void handleButtonPress(ActionEvent event) {
        Button btn = ((Button) event.getSource());
        Square square = getSquareAssociatedWithButton(btn);

        if (this.startSquare == null) {
            this.startSquare = getSquareAssociatedWithButton(btn);
            btn.setStyle(START_SQUARE_STYLE);
        }
        else if (this.targetSquare == null) {
            this.targetSquare = getSquareAssociatedWithButton(btn);
            btn.setStyle(TARGET_SQUARE_STYLE);
        } else {
            if (graph.isWall(square)) {
                graph.removeWall(square);
                this.gridButtons[square.getRow()][square.getCol()].setStyle(FREE_SPACE_STYLE);
            } else {
                graph.setWall(square);
                this.gridButtons[square.getRow()][square.getCol()].setStyle(WALL_STYLE);
            }
        }
    }

    private void clearGrid(ActionEvent event) {
        for (int row = 0; row < this.height; ++row) {
            for (int col = 0; col < this.width; ++col) {
                this.gridButtons[row][col].setStyle(FREE_SPACE_STYLE);
            }
        }

        this.graph.refresh();
        this.startSquare = null;
        this.targetSquare = null;
    }

    private void performAlgorithm(AlgorithmType type) {
        if (this.startSquare != null && this.targetSquare != null) {

            List<Square> path = AlgorithmController.performAlgorithm(
                    type,
                    this.graph,
                    startSquare,
                    targetSquare);
            removePreviousPath();
            if (path.size() > 2) path.remove(0);
            if (path.size() > 2) path.remove(path.size()-1);
            for (Square square : path) {
                int row = square.getRow();
                int col = square.getCol();

                this.gridButtons[row][col].setStyle(PATH_STYLE);
            }
        }
    }

    private void removePreviousPath() {
        for (int row = 0; row < this.height; ++row) {
            for (int col = 0; col < this.width; ++col) {
                if (this.gridButtons[row][col].getStyle().equals(PATH_STYLE)) {
                    this.gridButtons[row][col].setStyle(FREE_SPACE_STYLE);
                }
            }
        }
    }

    private Square getSquareAssociatedWithButton(Button button) {
        String buttonId = button.getId();
        if (buttonId == null) throw new ButtonNotFoundException("Invalid Button Id");

        String[] coordinates = buttonId.split("-");
        if (coordinates.length != 2) throw new InvalidButtonIdException("Invalid Button Id");
        int row = Integer.parseInt(coordinates[0]);
        int col = Integer.parseInt(coordinates[1]);
        Square square = new Square(col, row);
        return square;
    }





}
