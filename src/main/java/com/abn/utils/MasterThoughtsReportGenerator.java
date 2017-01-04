package com.abn.utils;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import javax.security.auth.login.AppConfigurationEntry;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MasterThoughtsReportGenerator {

    public static File reportDirectory = new File(System.getProperty("user.dir") + "/target");
    public static List<String> list = new ArrayList<String>();

    public static void listFilesForFolder(final File folder) {
        for(final File fileEntry :folder.listFiles()) {
            if(fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else if(fileEntry.getName().endsWith(".json")) {
                System.out.println("*****" + fileEntry.getName());
                list.add(reportDirectory + "/"+ fileEntry.getName());
            }
        }
    }

    public static void generateReport() throws Exception {
        listFilesForFolder(reportDirectory);
        String jenkinsBasePath = "";
        String buildNumber = "1";
        String projectName = "project";
        boolean skippedFails = true;
        boolean pendingFails = false;
        boolean undefinedFails = true;
        boolean missingFails = true;
        boolean flashCharts = true;
        boolean runWithJenkins = false;
        boolean parallelTesting = false;

        Configuration configuration = new Configuration(reportDirectory,projectName);
        configuration.setStatusFlags(skippedFails,pendingFails,undefinedFails,missingFails);
        configuration.setParallelTesting(parallelTesting);
        configuration.setJenkinsBasePath(jenkinsBasePath);
        configuration.setRunWithJenkins(runWithJenkins);
        configuration.setBuildNumber(buildNumber);

        ReportBuilder reportBuilder = new ReportBuilder(list,configuration);
        reportBuilder.generateReports();
    }

}
