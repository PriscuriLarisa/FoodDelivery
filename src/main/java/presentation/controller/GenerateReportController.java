package presentation.controller;

import business.DeliveryService;
import business.ReportDate;
import presentation.view.AdminView;
import presentation.view.GenerateReport;
import presentation.view.LogInView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GenerateReportController {
    private AdminView adminView;
    private GenerateReport reportView;
    public DeliveryService deliveryService;

    public void start() {

        this.reportView = new GenerateReport();
        reportView.setVisible(true);
        deliveryService = new DeliveryService();

        initializeUserInterfaceButtons();
    }

    public void initializeUserInterfaceButtons() {
        reportView.generateReportButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportDate reportDate = new ReportDate( Integer.valueOf(reportView.getStart()),
                                                        Integer.valueOf(reportView.getEnd()),
                                                        Integer.valueOf(reportView.getTimes()),
                                                        Integer.valueOf(reportView.getMinimTimes()),
                                                        Integer.valueOf(reportView.getMinPrice()),
                                                        Integer.valueOf(reportView.getDay()),
                                                        Integer.valueOf(reportView.getMonth()),
                                                        Integer.valueOf(reportView.getYear()));
                deliveryService.generateReports(reportDate);
            }


        });

    }
}

