package com.asep.loginjwt.controller;

import com.asep.loginjwt.response.ResponseApi1;
import com.asep.loginjwt.util.CsvFileGenerator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

@RestController
public class GetApiClient {
    @Value("${api.client1}")
    private String api1;
    @Value("${api.client2}")
    private String api2;


    @Autowired
    private CsvFileGenerator csvGenerator;

    @RequestMapping("/api1")
    private Object getAPI1() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(api1,String.class);
    }
    @RequestMapping("/api2/{id}")
    private Object getAPI2(@PathVariable("id") String id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(api2+id,String.class);
    }

    @GetMapping(value = "/getCSV", produces = "text/csv")
    public void exportIntoCSV(HttpServletResponse response) throws IOException {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ResponseApi1[]> getRest = restTemplate.getForEntity(api1, ResponseApi1[].class);

        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=\"data.csv\"");
        csvGenerator.toCsv(List.of(getRest.getBody()), response.getWriter());
    }
}
