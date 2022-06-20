package base;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ExcelUtility {

    public static String getExcelData(String sheetName, String testcaseID, String columnName) throws FilloException {
        Fillo fillo = new Fillo();
        Connection connection = fillo.getConnection(FilePath.EXCEL_PATH);
        String strQuery="Select * from "+sheetName+" where TestCaseId='"+testcaseID+"'";
        Recordset recordset= connection.executeQuery(strQuery);
        String dataFromExcel=null;
        while (recordset.next()){
           dataFromExcel = recordset.getField(columnName);
        }
        connection.close();
        return dataFromExcel;
    }
    public static void writeExcelData(String sheetName,String columnNames, String values) throws FilloException {
        Fillo fillo = new Fillo();
        Connection connection = fillo.getConnection(FilePath.EXCEL_PATH);
        String query="INSERT INTO "+sheetName+" ("+ columnNames +") VALUES ('"+values+"')";
        connection.executeUpdate(query);
    }
}
