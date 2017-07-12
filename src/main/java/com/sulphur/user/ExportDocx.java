package com.sulphur.user;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class ExportDocx {

	List<String> result = new ArrayList<String>();
	
	public ExportDocx(String path) throws FileNotFoundException, IOException{
		
		XWPFDocument document = new XWPFDocument(new FileInputStream(path));
		// get tables
		List<XWPFTable> tables = document.getTables();
		for (XWPFTable table : tables) {
		    // get row of table
		    List<XWPFTableRow> rows = table.getRows();
		    for (XWPFTableRow row : rows) {
		    	
		    	int i=0;//flag
		        // get cell of table
		        List<XWPFTableCell> tableCells = row.getTableCells();
		        for (XWPFTableCell cell : tableCells) {
		        	if(i==1){
		             // get content of cell
		             String text = cell.getText();
		             result.add(text);
		        	}
		        	i++;
		        }
		    }
		}
		document.close();
	}
	
	public List<String> getResult(){
		return result;
	}
}
