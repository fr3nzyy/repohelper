package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author zhukov-ad
 * date: 13.03.2018
 */
public class Main extends Application {
    TextField fieldFeature;
    TextField fieldTask;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Repo-build command helper 1.1");
        primaryStage.getIcons().add(new Image("sample/style/icon.png"));
        ScrollPane scrollPane = new ScrollPane();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(5, 5, 10, 10));
        Label poweredby = new Label("Powered by Zhukov A.D.");
        poweredby.setStyle("-fx-font: 8 arial;");
        grid.add(poweredby, 4, 0);
        Label labelTask = new Label("Enter task # CPP-");
        grid.add(labelTask, 0, 1);
        fieldTask = new TextField();
        grid.add(fieldTask, 1, 1);

        Label labelFeature = new Label("Enter feature # feature/CPP-");
        grid.add(labelFeature, 0, 2);
        fieldFeature = new TextField("");
        grid.add(fieldFeature, 1, 2);

        Button button = new Button("Generate");
        grid.add(button, 2, 2);
        button.setOnAction(event -> {
            if (grid.getChildren().size() == 7)
                grid.getChildren().remove(6);
            grid.add(content(), 0, 3, 5, 1);
        });
        scrollPane.setContent(grid);
        Scene scene = new Scene(scrollPane, 500, 910);
        scene.getStylesheets().add(getClass().getResource("style/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane content() {
        GridPane gridContent = new GridPane();
        gridContent.setHgap(4);
        gridContent.setVgap(4);

        gridContent.add(new Label("Создать фичу"), 0, 0);
        Button createFeature = new Button("git checkout -b feature/CPP-" + fieldFeature.getText());
        createFeature.setOnAction(event -> {
            getClipboard(createFeature);
            createFeature.getStyleClass().add("buttonPushed");
        });
        gridContent.add(createFeature, 0, 1);

        gridContent.add(new Label("Запушить фичу"), 0, 2);
        Button pushFeature = new Button("git push origin -u feature/CPP-" + fieldFeature.getText());
        pushFeature.setOnAction(event -> {
            getClipboard(pushFeature);
            pushFeature.getStyleClass().add("buttonPushed");
        });
        gridContent.add(pushFeature, 0, 3);

        gridContent.add(new Label("Создать таску"), 0, 4);
        Button createTask = new Button("git checkout -b CPP-" + fieldTask.getText());
        createTask.setOnAction(event -> {
            getClipboard(createTask);
            createTask.getStyleClass().add("buttonPushed");
        });
        gridContent.add(createTask, 0, 5);

        gridContent.add(new Label("Запушить таску"), 0, 6);
        Button pushTask = new Button("git push origin -u CPP-" + fieldTask.getText());
        pushTask.setOnAction(event -> {
            getClipboard(pushTask);
            pushTask.getStyleClass().add("buttonPushed");
        });
        gridContent.add(pushTask, 0, 7);

        gridContent.add(new Label("Закоммитить все изменения дотянуть девелоп свежий до фичи"), 0, 8);
        Button repoSync = new Button("repo-build sync build-pom -j 10");
        repoSync.setOnAction(event -> {
            getClipboard(repoSync);
            repoSync.getStyleClass().add("buttonPushed");
        });
        gridContent.add(repoSync, 0, 9);
        Button fsFeature = new Button("repo-build fs -f feature/CPP-" + fieldFeature.getText() + " -j 10");
        fsFeature.setOnAction(event -> {
            getClipboard(fsFeature);
            fsFeature.getStyleClass().add("buttonPushed");
        });
        gridContent.add(fsFeature, 0, 10);
        Button featureMergeRelease = new Button("repo-build feature-merge-release -f feature/CPP-" +
                fieldFeature.getText() + " -j 10");
        featureMergeRelease.setOnAction(event -> {
            getClipboard(featureMergeRelease);
            featureMergeRelease.getStyleClass().add("buttonPushed");
        });
        gridContent.add(featureMergeRelease, 0, 11);
        Button featurePushRelease = new Button("repo-build push-feature -f feature/CPP-" +
                fieldFeature.getText() + " -j 10");
        featurePushRelease.setOnAction(event -> {
            getClipboard(featurePushRelease);
            featurePushRelease.getStyleClass().add("buttonPushed");
        });
        gridContent.add(featurePushRelease, 0, 12);

        gridContent.add(new Label("Дотянуть с фичи в свою ветку"), 0, 13);
        Button taskMergeFeature = new Button("repo-build sync switch -f feature/CPP-" + fieldFeature.getText()
                + " -I CPP-" + fieldTask.getText() + " -j 10 task-merge-feature -j 10");
        taskMergeFeature.setOnAction(event -> {
            getClipboard(taskMergeFeature);
            taskMergeFeature.getStyleClass().add("buttonPushed");
        });
        gridContent.add(taskMergeFeature, 0, 14);

        gridContent.add(new Label("Собрать проект"), 0, 15);
        Button repoBuildSkipTests = new Button("repo-build mvn-build-parallel -j2 -DskipTests");
        repoBuildSkipTests.setOnAction(event -> {
            getClipboard(repoBuildSkipTests);
            repoBuildSkipTests.getStyleClass().add("buttonPushed");
        });
        Button repoBuild = new Button("repo-build mvn-build-parallel -j2");
        repoBuild.setOnAction(event -> {
            getClipboard(repoBuild);
            repoBuild.getStyleClass().add("buttonPushed");
        });
        GridPane groupRepoBuild = new GridPane();
        groupRepoBuild.add(repoBuildSkipTests, 0, 0);
        groupRepoBuild.add(repoBuild, 1, 0);
        gridContent.add(groupRepoBuild, 0, 16);

        gridContent.add(new Label("Запушить фичу и таску"), 0, 17);
        Button repoPushFeature = new Button("repo-build push-feature -f feature/CPP-" + fieldFeature.getText());
        repoPushFeature.setOnAction(event -> {
            getClipboard(repoPushFeature);
            repoPushFeature.getStyleClass().add("buttonPushed");
        });
        gridContent.add(repoPushFeature, 0, 18);
        Button repoPushTask = new Button("repo-build push-feature -f CPP-" + fieldTask.getText());
        repoPushTask.setOnAction(event -> {
            getClipboard(repoPushTask);
            repoPushTask.getStyleClass().add("buttonPushed");
        });
        gridContent.add(repoPushTask, 0, 19);

        gridContent.add(new Label("После успешного пулл-реквеста"), 0, 21);
        Button repoSyncAfterPR = new Button("repo-build sync build-pom -j 10");
        repoSyncAfterPR.setOnAction(event -> {
            getClipboard(repoSyncAfterPR);
            repoSyncAfterPR.getStyleClass().add("buttonPushed");
        });
        gridContent.add(repoSyncAfterPR, 0, 22);
        Button fsFeatureAfterPR = new Button("repo-build fs -f feature/CPP-" + fieldFeature.getText() + " -j 10");
        fsFeatureAfterPR.setOnAction(event -> {
            getClipboard(fsFeatureAfterPR);
            fsFeatureAfterPR.getStyleClass().add("buttonPushed");
        });
        gridContent.add(fsFeatureAfterPR, 0, 23);
        Button featureMergeReleaseAfterPR = new Button("repo-build feature-merge-release -f feature/CPP-" +
                fieldFeature.getText() + " -j 10");
        featureMergeReleaseAfterPR.setOnAction(event -> {
            getClipboard(featureMergeReleaseAfterPR);
            featureMergeReleaseAfterPR.getStyleClass().add("buttonPushed");
        });
        gridContent.add(featureMergeReleaseAfterPR, 0, 24);

        gridContent.add(new Label("Собрать проект"), 0, 25);
        Button repoBuildSkipTestsAfterPR = new Button("repo-build mvn-build-parallel -j2 -DskipTests");
        repoBuildSkipTestsAfterPR.setOnAction(event -> {
            getClipboard(repoBuildSkipTestsAfterPR);
            repoBuildSkipTestsAfterPR.getStyleClass().add("buttonPushed");
        });
        Button repoBuildAfterPR = new Button("repo-build mvn-build-parallel -j2");
        repoBuildAfterPR.setOnAction(event -> {
            getClipboard(repoBuildAfterPR);
            repoBuildAfterPR.getStyleClass().add("buttonPushed");
        });
        GridPane groupRepoBuildAfterPR = new GridPane();
        groupRepoBuildAfterPR.add(repoBuildSkipTestsAfterPR, 0, 0);
        groupRepoBuildAfterPR.add(repoBuildAfterPR, 1, 0);
        gridContent.add(groupRepoBuildAfterPR, 0, 27);

        Button repoSyncAfterPR2 = new Button("repo-build sync build-pom -j 10");
        repoSyncAfterPR2.setOnAction(event -> {
            getClipboard(repoSyncAfterPR2);
            repoSyncAfterPR2.getStyleClass().add("buttonPushed");
        });
        gridContent.add(repoSyncAfterPR2, 0, 28);

        Button releaseMergeFeature = new Button("repo-build release-merge-feature -f feature/CPP-" +
                fieldFeature.getText() + " -j 10");
        releaseMergeFeature.setOnAction(event -> {
            getClipboard(releaseMergeFeature);
            releaseMergeFeature.getStyleClass().add("buttonPushed");
        });
        gridContent.add(releaseMergeFeature, 0, 29);

        gridContent.add(new Label("Собрать проект"), 0, 30);
        Button repoBuildSkipTestsAfterPR1 = new Button("repo-build mvn-build-parallel -j2 -DskipTests");
        repoBuildSkipTestsAfterPR1.setOnAction(event -> {
            getClipboard(repoBuildSkipTestsAfterPR1);
            repoBuildSkipTestsAfterPR1.getStyleClass().add("buttonPushed");
        });
        Button repoBuildAfterPR1 = new Button("repo-build mvn-build-parallel -j2");
        repoBuildAfterPR1.setOnAction(event -> {
            getClipboard(repoBuildAfterPR1);
            repoBuildAfterPR1.getStyleClass().add("buttonPushed");
        });
        GridPane groupRepoBuildAfterPR1 = new GridPane();
        groupRepoBuildAfterPR1.add(repoBuildSkipTestsAfterPR1, 0, 0);
        groupRepoBuildAfterPR1.add(repoBuildAfterPR1, 1, 0);
        gridContent.add(groupRepoBuildAfterPR1, 0, 31);

        Button pushManifest = new Button("repo-build push-manifest");
        pushManifest.setOnAction(event -> {
            getClipboard(pushManifest);
            pushManifest.getStyleClass().add("buttonPushed");
        });
        gridContent.add(pushManifest, 0, 32);
        return gridContent;
    }

    private Clipboard getClipboard(Button button) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(button.getText());
        clipboard.setContent(content);
        return clipboard;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
