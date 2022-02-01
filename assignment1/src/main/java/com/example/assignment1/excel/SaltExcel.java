package com.example.assignment1.excel;

import com.example.assignment1.model.Salt;
import de.rototor.pdfbox.graphics2d.IPdfBoxGraphics2DFontTextDrawer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class SaltExcel {

    private XSSFWorkbook xssfWorkbook;
    private XSSFSheet xssfSheet;

    private List<Salt> saltList;

    public SaltExcel(List<Salt> saltList){
        this.saltList = saltList;
        xssfWorkbook = new XSSFWorkbook();
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle cellStyle){
        xssfSheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);

        if(value instanceof Long){
            cell.setCellValue((Long) value);
        }
        else if(value instanceof Integer){
            cell.setCellValue((Integer) value);
        }
        else if(value instanceof Boolean){
            cell.setCellValue((Boolean) value);
        }
        else if(value instanceof String){
            cell.setCellValue((String) value);
        }
        else{
            cell.setCellValue((Date) value);
        }
        cell.setCellStyle(cellStyle);
    }

    private void writeHeaderLine(){
        xssfSheet = xssfWorkbook.createSheet("Salt");
        Row row = xssfSheet.createRow(0);
        CellStyle cellStyle = xssfWorkbook.createCellStyle();
        XSSFFont xssfFont = xssfWorkbook.createFont();
        xssfFont.setBold(true);
        xssfFont.setFontHeight(20);
        cellStyle.setFont(xssfFont);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        createCell(row, 0, "Salt Information", cellStyle);
        xssfSheet.addMergedRegion(new CellRangeAddress(0,0,0,8));
        xssfFont.setFontHeightInPoints((short)(10));

        row = xssfSheet.createRow(1);
        xssfFont.setBold(true);
        xssfFont.setFontHeight(14);
        cellStyle.setFont(xssfFont);
        createCell(row, 0, "Salt Id", cellStyle);
        createCell(row, 0, "Salt Name", cellStyle);

        createCell(row, 0, "Salt Control StartDate", cellStyle);
        createCell(row, 0, "Salt ControlEndDate", cellStyle);
        createCell(row, 0, "Salt Control", cellStyle);
        createCell(row, 0, "Salt Ailment", cellStyle);
        createCell(row, 0, "Salt URl", cellStyle);
        createCell(row, 0, "Salt ApiGroup", cellStyle);
        createCell(row, 0, "Salt Deleted", cellStyle);


    }

    private void  writeDataLines(){
        int rowCount = 2;
        CellStyle cellStyle = xssfWorkbook.createCellStyle();
        XSSFFont xssfFont = xssfWorkbook.createFont();
        xssfFont.setFontHeight(12);
        cellStyle.setFont(xssfFont);

        for(Salt salt:saltList){
            Row row = xssfSheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, salt.getId(),cellStyle);
            createCell(row, columnCount++, salt.getSaltName(),cellStyle);
            createCell(row, columnCount++, salt.getControlStartDate(),cellStyle);
            createCell(row, columnCount++, salt.getControlEndDate(),cellStyle);
            createCell(row, columnCount++, salt.getAilment(),cellStyle);
            createCell(row, columnCount++, salt.getUrl(),cellStyle);
            createCell(row, columnCount++, salt.getApiGroup(),cellStyle);

        }
    }

    public  void export(HttpServletResponse servletResponse) throws IOException{
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream servletOutputStream = servletResponse.getOutputStream();
        xssfWorkbook.write(servletOutputStream);
        xssfWorkbook.close();
        servletOutputStream.close();
    }

}
