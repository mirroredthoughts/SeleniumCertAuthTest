package com.abn.utils;

import org.apache.log4j.Logger;
import org.testng.IExecutionListener;


public class TestNGExecutionListener implements IExecutionListener {

    protected static Logger LOGGER = Logger.getLogger(TestNGExecutionListener.class);

    @Override
    public void onExecutionStart() {
        LOGGER.debug("Starting execution");
    }

    @Override
    public void onExecutionFinish() {
        LOGGER.debug("Generating report");
        try {
            MasterThoughtsReportGenerator.generateReport();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
