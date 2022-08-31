package org.joshuaharris.visualisation.UI;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    private int height;
    private int width;
    private Button[][] gridButtons;
    private Graph graph;
    private Pane pane;


    public View(int height, int width) {
        this.height = height;
        this.width = width;
        this.gridButtons = new Button[height][width];
        this.graph = new Graph(height, width);
        createView();
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
                btn.setMinSize(50,50);

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

        bottomBar.getChildren().addAll(dfsBtn, clearBtn);
        return bottomBar;
    }

    private void handleButtonPress(ActionEvent event) {
        Button btn = ((Button) event.getSource());
        String buttonId = btn.getId();
        if (buttonId == null) throw new ButtonNotFoundException("Invalid Button Id");

        String[] coordinates = buttonId.split("-");
        if (coordinates.length != 2) throw new InvalidButtonIdException("Invalid Button Id");
        int row = Integer.parseInt(coordinates[0]);
        int col = Integer.parseInt(coordinates[1]);
        Square square = new Square(col, row);

        if (graph.isWall(square)) {
            graph.removeWall(square);
            this.gridButtons[row][col].setStyle(FREE_SPACE_STYLE);
        } else {
            graph.setWall(square);
            this.gridButtons[row][col].setStyle(WALL_STYLE);
        }
    }

    private void clearGrid(ActionEvent event) {
        for (int row = 0; row < this.height; ++row) {
            for (int col = 0; col < this.width; ++col) {
                this.gridButtons[row][col].setStyle(FREE_SPACE_STYLE);
            }
        }

        this.graph.refresh();
    }

    private void performAlgorithm(AlgorithmType type) {
        List<Square> path = AlgorithmController.performAlgorithm(
                type,
                this.graph,
                new Square(0,0),
                new Square(9,9));
        removePreviousPath();
        for (Square square : path) {
            int row = square.getRow();
            int col = square.getCol();

            this.gridButtons[row][col].setStyle(PATH_STYLE);
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





}
