package com.asep.loginjwt.util;

import com.asep.loginjwt.response.ResponseApi1;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class CsvFileGenerator {
    public void toCsv(List<ResponseApi1> datas, Writer writer) {
        String[] csvHeader = {
                "id", "type", "url", "created_at", "company", "company_url", "location", "title", "description", "how_to_apply", "company_logo"
        };
        try {
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(csvHeader));
            for (ResponseApi1 data : datas) {
                printer.printRecord(data.getId(), data.getType(), data.getUrl(), data.getCreated_at(), data.getCompany(), data.getCompany_url(), data.getLocation(), data.getTitle(), data.getDescription(), data.getHow_to_apply(), data.getCompany_logo());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
