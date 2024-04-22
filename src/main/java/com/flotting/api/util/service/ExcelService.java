package com.flotting.api.util.service;

import com.flotting.api.util.ExcelDownloadable;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Service
@Slf4j
public class ExcelService {

    public void downloadExcel(List<? extends Object> data, HttpServletResponse response)  {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        String fileName = "결과 다운로드";
        ExcelDownloadable firstExcelData = (ExcelDownloadable) data.getFirst();
        String[] headers = firstExcelData.getHeaders();
        Row row = sheet.createRow(0);
        for(int i = 0 ;i< headers.length ;i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
        }

        for(int i = 0; i< data.size(); i++) {
            ExcelDownloadable excelData = (ExcelDownloadable) data.get(i);
            row = sheet.createRow(i + 1);

            String[] cellDatas = excelData.getCellDatas();

            Cell cell = null;
            for(int j = 0; j< cellDatas.length; j++) {
                cell = row.createCell(j);
                cell.setCellValue(cellDatas[j]);
            }

        }

        response.setContentType("application/vnd.ms-excel");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
        } catch (UnsupportedEncodingException e) {
            log.error("액셀 파일 이름 인코딩 실패!");
            throw new RuntimeException(e);
        }

        try {
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            log.error("액셀 다운로드 실패!");
            throw new RuntimeException(e);
        }
    }
}
