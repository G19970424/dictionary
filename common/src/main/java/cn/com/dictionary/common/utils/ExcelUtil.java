package cn.com.dictionary.common.utils;

import cn.com.dictionary.common.annotation.ExcelAnnotation;
import cn.com.dictionary.common.exception.ExcelException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gejj
 * @data 2023/5/8 15:43
 * Excel Util
 */
public class ExcelUtil {

    private final static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private final static String excel2003L = "xls";
    private final static String excel2007U = "xlsx";

    /**
     * 获取对应实体类成员
     * @param T 实体类
     * @return
     */
    private static Map<String ,Object> getFieldAndValue(Class T){
        Map<String, Object> result = new HashMap<>();
        Field[] fields = T.getDeclaredFields();
        if(fields != null){
            for (Field field : fields) {
                ExcelAnnotation annotation = field.getAnnotation(ExcelAnnotation.class);
                if(annotation.value() != null && !"".equals(annotation.value())){
                    result.put(annotation.value(), field.getName());
                }
            }
        }else{
            logger.warn("实体类："+T+"不存在成员变量");
            throw new ExcelException("实体类："+T+"不存在成员变量");
        }
        return result;
    }

    /**
     *  根据sheet名称 处理excel内容 转换为List<T>输出
     * @param workbook  工作簿
     * @param StartRow  开始行
     * @param EndRow    结束行
     * @param t         对应实体类
     * @param sheetName sheet页名称
     * @return
     */
    private static <t> List<Object> handlerDataPojo(Workbook workbook,int StartRow,int EndRow,Class<?> t,String sheetName) throws InstantiationException, IllegalAccessException, IntrospectionException, NoSuchFieldException {
        List<Object> list = new ArrayList<>();
        Sheet sheet = workbook.getSheet(sheetName);
        if(sheet == null){
            logger.error("Excel中并无《"+sheetName+"》");
            throw new ExcelException("Excel中并无《"+sheetName+"》");
        }
        
        //获取头部数据
        //声明头部数据数列对象
        ArrayList<String> top = new ArrayList<>();
        //获取Excel第一行数据
        int firstRowNum = sheet.getFirstRowNum();
        Row firstRow = sheet.getRow(firstRowNum);
        if (null == firstRow) {
            logger.warn("解析Excel失败，在第一行没有读取到任何数据！");
            throw new ExcelException("解析Excel失败，在第一行没有读取到任何数据！");
        }
        for (int i = 0; i < firstRow.getLastCellNum(); i++) {
            top.add(convertCellValueToString(firstRow.getCell(i),"String"));
        }
        //获取实体类的成原变量
        Map<String, Object> fields = getFieldAndValue(t);
        //判断所需要的数据列
        Map<String, Object> excel = new HashMap<>();
        for (int i = 0; i < top.size(); i++) {
            if (fields.get(top.get(i)) != null && !"".equals(fields.get(top.get(i)))) {
                excel.put(String.valueOf(i), fields.get(top.get(i)));
            }
        }
        /*处理Excel数据内容*/
        int endRowNum;
        //获取结束行数
        if (EndRow == -1) {
            endRowNum = sheet.getPhysicalNumberOfRows();
        } else {
            endRowNum = EndRow <= sheet.getPhysicalNumberOfRows() ? EndRow : sheet.getPhysicalNumberOfRows();
        }
        //遍历行数
        for (int i = StartRow - 1; i < endRowNum; i++) {
            Row row = sheet.getRow(i);
            if (null == row) {
                continue;
            }
            //获取需要的列数据
            t texcel = (t) t.newInstance();
            for (Map.Entry<String, Object> map : excel.entrySet()) {
                //使用反射
                //获取实体类T中指定成员变量的对象
                PropertyDescriptor pd = new PropertyDescriptor((String) map.getValue(), texcel.getClass());
                //获取成员变量的set方法
                Method method = pd.getWriteMethod();
                //判断成员变量的类型
                Field field = texcel.getClass().getDeclaredField((String) map.getValue());
                String type = field.getGenericType().getTypeName();

                //获取Excel对应列的数据
                String cellData = convertCellValueToString(row.getCell(Integer.parseInt(map.getKey())),type);
                methodInvoke(method, texcel, cellData, type);
            }
            list.add(texcel);
        }
        return list;
    }

    /**
     * 根据数据类型反射创建对象
     * 支持 String Integer Double Float
     * @param method
     * @param field
     * @param o
     * @param str
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private static <t> void methodInvoke(Method method,t field,String o,String str){
        String type = str.substring(str.lastIndexOf('.')+1);
        try{
            switch (type){
                case "String":
                    method.invoke(field,o);
                    break;
                case "Integer":
                    Integer i = Integer.parseInt(o);
                    method.invoke(field,i);
                    break;
                case "Double":
                    Double v = Double.parseDouble(o);
                    method.invoke(field,v);
                    break;
                case "Float":
                    float f = Float.parseFloat(o);
                    method.invoke(field,f);
                    break;
            }
        }catch (Exception e){
            logger.error("Excel数据与实体类数据类型不符");
            throw new ExcelException("Excel数据转换异常！请检查Excel数据！");
        }


    }
    /**
     *  根据给定实体类中的注解读取excel
     *  1.实体类中需要读取的成员需标注@ExcelAnnotation注解 value 为 excel 字段名
     *  2.主要用于单页 excel 文件读取
     *  3.目前该功能仅支持 int/integer、double/Double、float/Float、String 数据类型
     * @param file      读取文件
     * @param StartRow  开始行
     * @param EndRow    结束行
     * @param t         对应实体类
     * @param sheetName sheet名
     * @return
     */
    public static List<Object> ReadExcel(File file,int StartRow,int EndRow,Class<?> t,String sheetName){
        if(StartRow > EndRow && EndRow != -1){
            logger.warn("未查询到数据！");
            throw new ExcelException("未查询到数据！");
        }

        List<Object> list = new ArrayList<>();
        Workbook work = null;
        FileInputStream fileInputStream = null;
        try{
            String fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            if(!file.exists()){
                logger.error("指定excel文件不存在！");
                throw new ExcelException("请重新上传Excel文件！");
            }
            fileInputStream = new FileInputStream(file);
            //创建工作簿
            work = getWorkBook(fileInputStream, fileType);
            //返回数据结果集
            list = handlerDataPojo(work,StartRow,EndRow,t,sheetName);
        }catch (IOException e){
            logger.error("Excel文件读取异常！");
            throw new ExcelException("Excel文件读取异常！");
        }catch (InstantiationException | NoSuchFieldException | IntrospectionException | IllegalAccessException e) {
            logger.error("Excel数据转换异常！");
            throw new ExcelException("Excel数据转换异常！请检查Excel数据！");
        }
        return list;
    }

    /**
     *  Excel 单元格数据转换 String 类型
     * @param cell
     * @return
     */
    private static String convertCellValueToString(Cell cell,String type){
        String name = type.substring(type.lastIndexOf(".") + 1);
        if(cell == null){
            return null;
        }

        String returnValue = null;
        switch (name){
            case "int":
            case "Integer":
                Double cellValue = cell.getNumericCellValue();
                int i = cellValue.intValue();
                returnValue = String.valueOf(i);
                break;
            case "Double":
            case "double":
                Double doubleValue = cell.getNumericCellValue();
                //科学计数法，小数点后保留4位小数
                DecimalFormat df = new DecimalFormat("0.00");
                returnValue = df.format(doubleValue);
                break;
            case "String":
                returnValue = cell.getStringCellValue();
                break;
            case "Boolean":
                Boolean booleanCellValue = cell.getBooleanCellValue();
                returnValue = booleanCellValue.toString();
                break;
            case "Formula":
                returnValue = cell.getCellFormula();
                break;
            default:
                break;
        }
        return returnValue;
    }

    private static Workbook getWorkBook(InputStream inputStream,String fileType) throws IOException {
        Workbook workbook = null;
        if(fileType.equalsIgnoreCase(excel2003L)){
            workbook = new HSSFWorkbook(inputStream);
        }else if(fileType.equalsIgnoreCase(excel2007U)){
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }

}
