package com.banca.transactions.report;

import java.util.HashMap;
import java.util.List;

public class ReportPdf implements IReport{

    @Override
    public Object makeReport(HashMap<String,String> headers, List<?> data) {
        return data;
    }
}
