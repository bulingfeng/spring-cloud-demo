package com.bulingfeng.upload.file.utils;
import com.bulingfeng.upload.file.model.User;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * @Author:bulingfeng
 * @Date: 2020-12-03
 */
public class ExcelUtil {


    public static List<Object> excelToShopIdList(InputStream inputStream) {
        List<Object> list = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            //工作表对象
            Sheet sheet = workbook.getSheetAt(0);
            //总行数
            int rowLength = sheet.getLastRowNum()+1;
            System.out.println("总行数有多少行" + rowLength);
            //工作表的列
            Row row = sheet.getRow(0);

            //总列数
            int colLength = row.getLastCellNum();
            System.out.println("总列数有多少列" + colLength);
            //得到指定的单元格
            Cell cell = row.getCell(0);
            for (int i = 0; i < rowLength; i++) {
                row = sheet.getRow(i);
                User user=new User();
                for (int j = 0; j < colLength; j++) {
                    cell = row.getCell(j);
                    // System.out.println(cell);
                    if (cell != null) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String data = cell.getStringCellValue();
                        data = data.trim();
                        if (j==0){
                            user.setName(data);
                        }
                        if (j==1){
                            user.setAge(data);
                        }

                    }
                }

                list.add(user);
            }
        } catch (Exception e) {

        }
        return list;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String path="/Users/bulingfeng/Desktop/test-flie/test.xlsx";
        InputStream inputStream=new FileInputStream(new File(path));
        List<Object> list = excelToShopIdList(inputStream);
        for (Object o : list) {
            System.out.println(o);
        }
    }
}
