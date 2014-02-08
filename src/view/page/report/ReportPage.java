/*
 * This file is part of ProDisFuzz, modified on 08.02.14 23:36.
 * Copyright (c) 2013-2014 Volker Nebelung <vnebelung@prodisfuzz.net>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */

package view.page.report;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import model.Model;
import model.process.report.ReportProcess;
import view.page.Page;
import view.window.ConnectionHelper;
import view.window.Navigation;

import java.io.File;
import java.nio.file.Path;
import java.util.Observable;
import java.util.Observer;

public class ReportPage extends GridPane implements Observer, Page {

    private Navigation navigation;
    @FXML
    private TextField fileTextField;
    private Path savePath;

    /**
     * Instantiates a new report page responsible for visualizing the process of generating the report of the
     * fuzz-testing.
     */
    public ReportPage(Navigation n) {
        super();
        ConnectionHelper.connect(getClass().getResource("reportPage.fxml"), this);
        Model.INSTANCE.getReportProcess().addObserver(this);
        navigation = n;
    }

    @Override
    public void update(Observable o, Object arg) {
        final ReportProcess process = (ReportProcess) o;

        fileTextField.getStyleClass().removeAll("text-field-success", "text-field-fail");
        if (process.isWritten()) {
            fileTextField.getStyleClass().add("text-field-success");
            fileTextField.setText("Successfully exported to '" + savePath.toString() + "'");
        } else {
            fileTextField.getStyleClass().add("text-field-fail");
            fileTextField.setText(savePath == null ? "Please choose the file the protocol structure will be " +
                    "exported to" : "Could not export to '" + savePath.toString() + "'");
        }

        navigation.setCancelable(!process.isWritten(), this);
        navigation.setFinishable(process.isWritten(), this);
    }

    @FXML
    private void browse() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(getScene().getWindow());
        if (file == null) {
            return;
        }
        savePath = file.toPath().toAbsolutePath();
        Model.INSTANCE.getReportProcess().write(savePath);
    }

    @Override
    public void initProcess() {
        Model.INSTANCE.getReportProcess().init();
    }
}