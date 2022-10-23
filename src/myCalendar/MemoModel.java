package myCalendar;

import com.alibaba.fastjson.JSON;
import myCalendar.memo.MemoContent;
import org.apache.commons.io.FileUtils;

import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class MemoModel extends AbstractTableModel {


    public    Vector   columnNames,rowData;

    public static void remoteData(Integer id) {
        //删除数据
        File data=new File("memo.txt");
        if(data.exists()){
            String s = null;
            try {
                s = FileUtils.readFileToString(data, "UTF-8");
                List<MemoContent> memoContents = JSON.parseArray(s, MemoContent.class);
                List<MemoContent> memoContentStreams = memoContents.stream().filter(each -> each.getId() != id).collect(Collectors.toList());
                FileUtils.writeStringToFile(data,JSON.toJSONString(memoContentStreams));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void updateRowData(MemoContent memoContent) {
        //删除数据
        File data=new File("memo.txt");
        if(data.exists()){
            String s = null;
            try {
                s = FileUtils.readFileToString(data, "UTF-8");
                List<MemoContent> memoContents = JSON.parseArray(s, MemoContent.class);
                List<MemoContent> memoContentStreams = memoContents.stream().filter(each ->
                      each.getId() != memoContent.getId()).collect(Collectors.toList());
                memoContentStreams.add(memoContent);
                FileUtils.writeStringToFile(data,JSON.toJSONString(memoContentStreams));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void searchTitle(String text) {
        //删除数据
        File data=new File("memo.txt");
        if(data.exists()){
            String s = null;
            try {
                s = FileUtils.readFileToString(data, "UTF-8");
                List<MemoContent> memoContents = JSON.parseArray(s, MemoContent.class);
                List<MemoContent> memoContentStreams = memoContents.stream().filter(each -> text.contains(each.getTitle())).collect(Collectors.toList());
                Vector  result=new Vector();
                for (MemoContent memoContent : memoContentStreams) {
                    Vector vector=new Vector();
                    vector.add(memoContent.getId());
                    vector.add(memoContent.getTitle());
                    vector.add(memoContent.getContent());
                    vector.add(memoContent.getCreateTime());
                    result.add(vector);
                }
                Memo.memoModel.rowData=result;
                Memo.memoModel.fireTableDataChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public   void  reloadTable(){
          initData();
          Memo.memoModel.fireTableDataChanged();
    }
    public   void initData() {
        if(columnNames==null){
            columnNames=new Vector();
        }
        if(rowData==null){
            rowData=new Vector();
        }
        columnNames.add("id");
        columnNames.add("标题");
        columnNames.add("内容");
        columnNames.add("创建日期");
         try {
             rowData = getRowData();

           }catch(Exception e){
             e.printStackTrace();
           }

    }
    public static   Vector    getRowData() throws  Exception{
        File rowDataFile=new File("memo.txt");
        Vector result=new Vector();
        if(rowDataFile!=null && rowDataFile.exists()){
            String s=FileUtils.readFileToString(rowDataFile,"UTF-8");
            if(s!=null && !s.equals("")){
                List<MemoContent> memoContents = JSON.parseArray(s, MemoContent.class);
                for (MemoContent memoContent : memoContents) {
                    Vector eachRow=new Vector();
                    eachRow.add(memoContent.getId());
                    eachRow.add(memoContent.getTitle());
                    eachRow.add(memoContent.getContent());
                    eachRow.add(memoContent.getCreateTime());
                    result.add(eachRow);
                }
            }
        }else{
            FileUtils.writeStringToFile(rowDataFile,"[]","UTF-8");
        }
        return  result;

    }
    public static void writeDataToFile(MemoContent content) throws IOException {
        File rowDataFile=new File("memo.txt");
        List<MemoContent> memoContents=new ArrayList<>();
        if(rowDataFile!=null && rowDataFile.exists()){
            String s = null;
            s = FileUtils.readFileToString(rowDataFile,"UTF-8");
            if(s!=null && !s.equals("")){
                memoContents = JSON.parseArray(s, MemoContent.class);

            }
            memoContents.add(content);
            FileUtils.writeStringToFile(rowDataFile,JSON.toJSONString(memoContents));
        }
    }

    @Override
    public int getColumnCount() {
        return  columnNames.size();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnNames.get(columnIndex).getClass();
    }

    @Override
    public int getRowCount() {
        return rowData.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return  ((Vector)rowData.get(rowIndex)).get(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return (String) columnNames.get(column);
    }
}
