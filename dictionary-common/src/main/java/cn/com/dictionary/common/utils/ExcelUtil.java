package cn.com.dictionary.common.utils;

import cn.com.dictionary.common.annotation.ExcelAnnotation;
import cn.com.dictionary.common.exception.ExcelException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
 */
public class ExcelUtil {

    private final static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private final static String excel2003L = "xls";
    private final static String excel2007U = "xlsx";


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
            logger.info("实体类："+T+"不存在成员变量");
            throw new ExcelException();
        }
        return result;
    }

    /**
     *
     * @param workbook
     * @param StartRow
     * @param EndRow
     * @param t
     * @param sheetNum
     * @param <t>
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IntrospectionException
     * @throws NoSuchFieldException
     * @throws InvocationTargetException
     */
    public static <t> List<List<Object>> ReadDataPojo(Workbook workbook,int StartRow,int EndRow,Class<?> t,Integer[] sheetNum) throws InstantiationException, IllegalAccessException, IntrospectionException, NoSuchFieldException, InvocationTargetException {
        //声明返回的结果集
        List<List<Object>> result = new ArrayList<>();
        //解析sheet（sheet是Excel脚页）
        for (int i = 0; i < sheetNum.length; i++) {
            //声明返回的中间结果集
            List<Object> middleResult = new ArrayList<Object>();
            Sheet sheet = workbook.getSheetAt(sheetNum[i]);
            // 校验sheet是否合法
            if (sheet == null) {
                continue;
            }
            //获取头部数据
            //声明头部数据数列对象
            ArrayList<String> top = new ArrayList<>();
            //获取Excel第一行数据
            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            if (null == firstRow) {
                logger.warn("解析Excel失败，在第一行没有读取到任何数据！");
                return null;
            }
            for (int j = 0; j < firstRow.getLastCellNum(); j++) {
                top.add(convertCellValueToString(firstRow.getCell(j)));
            }
            //获取实体类的成原变量
            Map<String, Object> fields = getFieldAndValue(t);
            //判断所需要的数据列
            Map<String, Object> excel = new HashMap<>();
            for (int j = 0; j < top.size(); j++) {
                if (fields.get(top.get(j)) != null && !"".equals(fields.get(top.get(j)))) {
                    excel.put(String.valueOf(j), fields.get(top.get(j)));
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
            for (int j = StartRow - 1; j < endRowNum; j++) {
                Row row = sheet.getRow(j);
                if (null == row) {
                    continue;
                }
                //获取需要的列数据
                t texcel = (t) t.newInstance();
                for (Map.Entry<String, Object> map : excel.entrySet()) {
                    //获取Excel对应列的数据
                    String celldata = convertCellValueToString(row.getCell(Integer.parseInt(map.getKey())));
                    //获取实体类T中指定成员变量的对象
                    PropertyDescriptor pd = new PropertyDescriptor((String) map.getValue(), texcel.getClass());
                    //获取成员变量的set方法
                    Method method = pd.getWriteMethod();
                    //判断成员变量的类型
                    Field field = texcel.getClass().getDeclaredField((String) map.getValue());
                    String object = field.getGenericType().getTypeName();
                    if (object.endsWith("String")) {
                        if (celldata!=null&&!"".equals(celldata)&&!celldata.isEmpty()){
                            //执行set方法
                            method.invoke(texcel, celldata);
                        }
                        else {/*什么都不用做，甚至这个else都可以不要，因为实体类初始化时会给对应的成员变量赋予空值 */}
                    }
                    if (object.endsWith("Double")) {
                        System.out.println(celldata);
                        if (celldata!=null&&!"".equals(celldata)&&!celldata.isEmpty()) {
                            Double middata = Double.valueOf(celldata);
                            System.out.println("middata:"+middata);
                            //执行set方法
                            method.invoke(texcel, middata);
                        }
                    }
                    if (object.endsWith("Float")) {
                        if (celldata!=null&&!"".equals(celldata)&&!celldata.isEmpty()){
                            Float middata = Float.valueOf(celldata);
                            //执行set方法
                            method.invoke(texcel, middata);
                        }
                    }
                    if (object.endsWith("Integer")) {
                        if (celldata!=null&&!"".equals(celldata)&&!celldata.isEmpty()){
                            Integer  middata = Integer.parseInt(celldata);
                            //执行set方法
                            method.invoke(texcel, middata);
                        }
                    }
                }
                middleResult.add(texcel);
            }
            result.add(middleResult);
        }
        return result;
    }

    /**
     *
     * @param workbook
     * @param StartRow
     * @param EndRow
     * @param t
     * @param sheetNum
     * @return
     */
    public static List<List<Object>> HandlerDataPojo(Workbook workbook,int StartRow,int EndRow,Class<?> t,String sheetNum) throws IntrospectionException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<List<Object>> list = new ArrayList<>();
        if(sheetNum.equals("-1")){
            Integer[] sheetArray = new Integer[workbook.getNumberOfSheets()];
            for (int i = 0; i < sheetArray.length; i++) {
                sheetArray[i] = i;
            }

            list = ReadDataPojo(workbook,StartRow,EndRow,t,sheetArray);
        }else{
            String[] split = sheetNum.split(",");
            Integer[] sheetArray = new Integer[split.length];
            for (int i = 0; i < split.length; i++) {
                sheetArray[i] = Integer.valueOf(split[i]);
            }
            list = ReadDataPojo(workbook,StartRow,EndRow,t,sheetArray);
        }
        return list;
    }

    /**
     *
     * @param fileName
     * @param StartRow
     * @param EndRow
     * @param t
     * @param sheetNum
     * @return
     */
    public static List<List<Object>> ReadExcel(String fileName,int StartRow,int EndRow,Class<?> t,String sheetNum){
        if(StartRow > EndRow && EndRow != -1){
            logger.warn("");
            throw new ExcelException();
        }

        List<List<Object>> list = new ArrayList<>();
        Workbook work = null;
        FileInputStream fileInputStream = null;
        try{
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            File file = new File(fileName);
            if(!file.exists()){
                logger.error("指定excel文件不存在！");
                throw new ExcelException();
            }

            fileInputStream = new FileInputStream(file);
            work = getWorkBook(fileInputStream, fileType);
            list = HandlerDataPojo(work,StartRow,EndRow,t,sheetNum);
        }catch (Exception e){

        }
        return list;
    }

    /**
     *
     * @param cell
     * @return
     */
    private static String convertCellValueToString(Cell cell){
        if(cell == null){
            return null;
        }

        String returnValue = null;
        switch (cell.getCellType()){
            case NUMERIC:
                Double doubleValue = cell.getNumericCellValue();
                //科学计数法，小数点后保留4位小数
                DecimalFormat df = new DecimalFormat("0.0000");
                returnValue = df.format(doubleValue);
                break;
            case STRING:
                returnValue = cell.getStringCellValue();
                break;
            case BOOLEAN:
                Boolean booleanCellValue = cell.getBooleanCellValue();
                returnValue = booleanCellValue.toString();
                break;
            case BLANK:
                break;
            case FORMULA:
                returnValue = cell.getCellFormula();
                break;
            case ERROR:
                break;
            default:
                break;
        }
        return returnValue;
    }

    public static Workbook getWorkBook(InputStream inputStream,String fileType) throws IOException {
        Workbook workbook = null;

        if(fileType.equalsIgnoreCase(excel2003L)){
            workbook = new HSSFWorkbook(inputStream);
        }else if(fileType.equalsIgnoreCase(excel2007U)){
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }

}
