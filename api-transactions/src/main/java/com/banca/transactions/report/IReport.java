package com.banca.transactions.report;

import java.util.HashMap;
import java.util.List;

public interface IReport {
    Object makeReport(HashMap<String,String> headers, List<?> data);
}
