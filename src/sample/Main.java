package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Border;
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
        grid.setPadding(new Insets(5, 5,10,10));
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
        grid.add(fieldFeature, 1,2);

        Button button = new Button("Generate");
        grid.add(button, 2, 2);
        button.setOnAction(event -> {
            if(grid.getChildren().size() == 7)
                grid.getChildren().remove(6);
            grid.add(content(), 0, 3, 5, 1);
        });
        scrollPane.setContent(grid);
        Scene scene = new Scene(scrollPane, 500, 900);
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
//        createFeature.setStyle("-fx-font: 10 arial;");
        createFeature.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(createFeature.getText());
            clipboard.setContent(content);
            createFeature.getStyleClass().add("buttonPushed");
        });
        gridContent.add(createFeature, 0, 1);

        gridContent.add(new Label("Запушить фичу"), 0, 2);
        Button pushFeature = new Button("git push origin -u feature/CPP-" + fieldFeature.getText());
        pushFeature.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(pushFeature.getText());
            clipboard.setContent(content);
            pushFeature.getStyleClass().add("buttonPushed");
        });
        gridContent.add(pushFeature, 0, 3);

        gridContent.add(new Label("Создать таску"), 0, 4);
        Button createTask = new Button("git checkout -b CPP-" + fieldTask.getText());
        createTask.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(createTask.getText());
            clipboard.setContent(content);
            createTask.getStyleClass().add("buttonPushed");
        });
        gridContent.add(createTask, 0, 5);

        gridContent.add(new Label("Запушить таску"), 0, 6);
        Button pushTask = new Button("git push origin -u CPP-" + fieldTask.getText());
        pushTask.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(pushTask.getText());
            clipboard.setContent(content);
            pushTask.getStyleClass().add("buttonPushed");
        });
        gridContent.add(pushTask, 0, 7);

        gridContent.add(new Label("Закоммитить все изменения дотянуть девелоп свежий до фичи"), 0, 8);
        Button repoSync = new Button("repo-build sync -j 20");
        repoSync.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(repoSync.getText());
            clipboard.setContent(content);
            repoSync.getStyleClass().add("buttonPushed");
        });
        gridContent.add(repoSync, 0, 9);
        Button fsFeature = new Button("repo-build fs -f feature/CPP-" + fieldFeature.getText() + " -j 20");
        fsFeature.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(fsFeature.getText());
            clipboard.setContent(content);
            fsFeature.getStyleClass().add("buttonPushed");
        });
        gridContent.add(fsFeature, 0, 10);
        Button featureMergeRelease = new Button("repo-build feature-merge-release -f feature/CPP-" +
                fieldFeature.getText() + " -j 20");
        featureMergeRelease.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(featureMergeRelease.getText());
            clipboard.setContent(content);
            featureMergeRelease.getStyleClass().add("buttonPushed");
        });
        gridContent.add(featureMergeRelease, 0, 11);

        gridContent.add(new Label("Дотянуть с фичи в свою ветку"), 0, 12);
        Button taskMergeFeature = new Button("repo-build sync switch -f feature/CPP-" + fieldFeature.getText()
                + " -I CPP-" + fieldTask.getText() + " -j 20 task-merge-feature -j 20");
        taskMergeFeature.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(taskMergeFeature.getText());
            clipboard.setContent(content);
            taskMergeFeature.getStyleClass().add("buttonPushed");
        });
        gridContent.add(taskMergeFeature, 0, 13);

        gridContent.add(new Label("Собрать проект"), 0, 14);
        Button repoBuildSkipTests = new Button("repo-build mvn-build-parallel -DskipTests");
        repoBuildSkipTests.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(repoBuildSkipTests.getText());
            clipboard.setContent(content);
            repoBuildSkipTests.getStyleClass().add("buttonPushed");
        });
        Button repoBuild = new Button("repo-build mvn-build-parallel");
        repoBuild.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(repoBuild.getText());
            clipboard.setContent(content);
            repoBuild.getStyleClass().add("buttonPushed");
        });
        GridPane groupRepoBuild = new GridPane();
        groupRepoBuild.add(repoBuildSkipTests, 0,0);
        groupRepoBuild.add(repoBuild, 1, 0);
        gridContent.add(groupRepoBuild, 0, 15);

        gridContent.add(new Label("Запушить фичу и таску"), 0, 16);
        Button repoPushFeature = new Button("repo-build push-feature -f feature/CPP-" + fieldFeature.getText());
        repoPushFeature.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(repoPushFeature.getText());
            clipboard.setContent(content);
            repoPushFeature.getStyleClass().add("buttonPushed");
        });
        gridContent.add(repoPushFeature, 0, 17);
        Button repoPushTask = new Button("repo-build push-feature -f CPP-" + fieldTask.getText());
        repoPushTask.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(repoPushTask.getText());
            clipboard.setContent(content);
            repoPushTask.getStyleClass().add("buttonPushed");
        });
        gridContent.add(repoPushTask, 0, 18);

        gridContent.add(new Label("После успешного пулл-реквеста"), 0, 20);
        Button repoSyncAfterPR = new Button("repo-build sync -j 20");
        repoSyncAfterPR.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(repoSyncAfterPR.getText());
            clipboard.setContent(content);
            repoSyncAfterPR.getStyleClass().add("buttonPushed");
        });
        gridContent.add(repoSyncAfterPR, 0, 21);
        Button fsFeatureAfterPR = new Button("repo-build fs -f feature/CPP-" + fieldFeature.getText() + " -j 20");
        fsFeatureAfterPR.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(fsFeatureAfterPR.getText());
            clipboard.setContent(content);
            fsFeatureAfterPR.getStyleClass().add("buttonPushed");
        });
        gridContent.add(fsFeatureAfterPR, 0, 22);
        Button featureMergeReleaseAfterPR = new Button("repo-build feature-merge-release -f feature/CPP-" +
                fieldFeature.getText() + " -j 20");
        featureMergeReleaseAfterPR.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(featureMergeReleaseAfterPR.getText());
            clipboard.setContent(content);
            featureMergeReleaseAfterPR.getStyleClass().add("buttonPushed");
        });
        gridContent.add(featureMergeReleaseAfterPR, 0, 23);

        gridContent.add(new Label("Собрать проект"), 0, 24);
        Button repoBuildSkipTestsAfterPR = new Button("repo-build mvn-build-parallel -DskipTests");
        repoBuildSkipTestsAfterPR.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(repoBuildSkipTestsAfterPR.getText());
            clipboard.setContent(content);
            repoBuildSkipTestsAfterPR.getStyleClass().add("buttonPushed");
        });
        Button repoBuildAfterPR = new Button("repo-build mvn-build-parallel");
        repoBuildAfterPR.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(repoBuildAfterPR.getText());
            clipboard.setContent(content);
            repoBuildAfterPR.getStyleClass().add("buttonPushed");
        });
        GridPane groupRepoBuildAfterPR = new GridPane();
        groupRepoBuildAfterPR.add(repoBuildSkipTestsAfterPR, 0,0);
        groupRepoBuildAfterPR.add(repoBuildAfterPR, 1, 0);
        gridContent.add(groupRepoBuildAfterPR, 0, 26);

        Button repoSyncAfterPR2 = new Button("repo-build sync -j 20");
        repoSyncAfterPR2.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(repoSyncAfterPR2.getText());
            clipboard.setContent(content);
            repoSyncAfterPR2.getStyleClass().add("buttonPushed");
        });
        gridContent.add(repoSyncAfterPR2, 0, 27);

        Button releaseMergeFeature = new Button("repo-build release-merge-feature -f feature/CPP-" +
                fieldFeature.getText() + " -j 20");
        releaseMergeFeature.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(releaseMergeFeature.getText());
            clipboard.setContent(content);
            releaseMergeFeature.getStyleClass().add("buttonPushed");
        });
        gridContent.add(releaseMergeFeature, 0, 28);

        gridContent.add(new Label("Собрать проект"), 0, 29);
        Button repoBuildSkipTestsAfterPR1 = new Button("repo-build mvn-build-parallel -DskipTests");
        repoBuildSkipTestsAfterPR1.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(repoBuildSkipTestsAfterPR1.getText());
            clipboard.setContent(content);
            repoBuildSkipTestsAfterPR1.getStyleClass().add("buttonPushed");
        });
        Button repoBuildAfterPR1 = new Button("repo-build mvn-build-parallel");
        repoBuildAfterPR1.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(repoBuildAfterPR1.getText());
            clipboard.setContent(content);
            repoBuildAfterPR1.getStyleClass().add("buttonPushed");
        });
        GridPane groupRepoBuildAfterPR1 = new GridPane();
        groupRepoBuildAfterPR1.add(repoBuildSkipTestsAfterPR1, 0,0);
        groupRepoBuildAfterPR1.add(repoBuildAfterPR1, 1, 0);
        gridContent.add(groupRepoBuildAfterPR1, 0, 30);

        Button pushManifest = new Button("repo-build push-manifest");
        pushManifest.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(pushManifest.getText());
            clipboard.setContent(content);
            pushManifest.getStyleClass().add("buttonPushed");
        });
        gridContent.add(pushManifest, 0, 31);
        return gridContent;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
