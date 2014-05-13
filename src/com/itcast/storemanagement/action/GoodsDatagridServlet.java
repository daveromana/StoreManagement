package com.itcast.storemanagement.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.itcast.storemanagement.po.Goods;
import com.itcast.storemanagement.service.GoodsService;
import com.itcast.storemanagement.util.SystemFinalVar;

public class GoodsDatagridServlet extends HttpServlet {

	GoodsService gs = null;

	public GoodsDatagridServlet() {
		gs = new GoodsService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String methodType = request.getParameter("methodType");
		int current_page = 1;
		int maxCount = 1;
		try {
			if ("delete".equals(methodType)) {
				this.delete(request, response);
			}else if ("query".equals(methodType)) {
				this.query(request, response);
			} else if ("downexcle".equals(methodType)) {
				this.downExcle(request, response);
			}  else {
				// 判断当前页数
				String page = request.getParameter("current_page");
				if (page != "" && page != null) {
					current_page = Integer.parseInt(page);
				}
				// 获得权限列表
				List goodsList = gs.getAllGoods(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("goodsList", goodsList);
				// 计算总页数，Session存储总页数
				maxCount = gs.getAllGoodsCount();
				if (maxCount % SystemFinalVar.INDEX_POWER_LENGTH == 0)
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH;
				else
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH + 1;
				// 跳转页面
				request.getSession().setAttribute("maxCount", maxCount);
				request.getSession().setAttribute("queryGoodsName", "");
				response.sendRedirect("jcxxgl/hwgl/goods.jsp?current_page="
						+ current_page);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void downExcle(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.reset();  
	    response.setContentType("application/vnd.ms-excel;charset=utf-8");  
	    response.setHeader("Content-Disposition", "attachment;filename="  
	    + new String("货物表.xls".getBytes(),"iso-8859-1"));  
	    ServletOutputStream out = response.getOutputStream();  
	    BufferedInputStream bis = null;  
	    BufferedOutputStream bos = null;  
	    try {  
	    bis = new BufferedInputStream(this.getInputStream());  
	    bos = new BufferedOutputStream(out);  
	    byte[] buff = new byte[2048];  
	    int bytesRead;  
	    // Simple read/write loop.  
	    while (-1 != (bytesRead = bis.read(buff,0, buff.length))) {  
	    bos.write(buff, 0, bytesRead);  
	    }  
	    } catch (final IOException e) {  
	    System.out.println("IOException.");  
	    throw e;  
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {  
	    if (bis != null)  
	    bis.close();  
	    if (bos != null)  
	    bos.close();  
	    } 
		
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int current_page = 1;
		String queryGoodsName = request.getParameter("querygoodsname");
			//如果不为空
			try {
				if (!"".equals(queryGoodsName)) {
					List goodsList = gs.getAllGoods(queryGoodsName);
					request.getSession().setAttribute("goodsList", goodsList);
					request.getSession().setAttribute("queryGoodsName", queryGoodsName);
					response.sendRedirect("jcxxgl/hwgl/goods.jsp?current_page="
							+ current_page);
				} else {
					List goodsList = gs.getAllGoods(
							SystemFinalVar.INDEX_POWER_LENGTH, current_page);
					request.getSession().setAttribute("goodsList", goodsList);
					request.getSession().setAttribute("queryGoodsName", "");
					response.sendRedirect("jcxxgl/hwgl/goods.jsp?current_page="
							+ current_page);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id = request.getParameter("id");
		if(gs.deleteOneGoods(id)){
			try {
				int current_page = 1;
				int maxCount = 1;
				// 判断当前页数
				String page = request.getParameter("current_page");
				if (page != "" && page != null) {
					current_page = Integer.parseInt(page);
				}
				// 获得权限列表
				List goodsList = gs.getAllGoods(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("goodsList", goodsList);
				// 计算总页数，Session存储总页数
				maxCount = gs.getAllGoodsCount();
				if (maxCount % SystemFinalVar.INDEX_POWER_LENGTH == 0)
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH;
				else
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH + 1;
				// 跳转页面
				request.getSession().setAttribute("maxCount", maxCount);
				response.sendRedirect("jcxxgl/hwgl/goods.jsp?result=success&current_page="
						+ current_page);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			response.sendRedirect("jcxxgl/hwgl/goods.jsp?result=failed");
		}

	}
	private InputStream getInputStream() throws SQLException {  
	    //�õ�ResultSet����
		ResultSet rs = gs.getAllGoodsExcle();
		//����һ��������
	    HSSFWorkbook wb = new HSSFWorkbook();
	    //����һ��������
	    HSSFSheet sheet = wb.createSheet("sheet1");
	    


	    //�����������һ�У������У�
	    HSSFRow row= sheet.createRow((short)0);;
		HSSFCell cell;
		// ��ȡ�� ResultSet ������еı�š����ͺ�����
		ResultSetMetaData md=rs.getMetaData();
		//��ȡ��ȡmd����������
		int nColumn=md.getColumnCount();
		//д������ֶε���ƣ������е���ƣ�
		for(int i=1;i<=nColumn;i++){
			cell = row.createCell((i-1));
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			//��ȡ���ڴ�ӡ�������ʾ��ָ���еĽ������
			cell.setCellValue(md.getColumnLabel(i));
			}
		 //��ResultSet�����е������ӵ�������
	     List<Goods> tbList=new ArrayList<Goods>();
	     try {
			while(rs.next()){
				 Goods tbf=new Goods(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
				 tbList.add(tbf);
			 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     //��������ÿ�������ӵ���������
	     for (int i = 0; i < tbList.size(); ++i)  
	     {  
	     Goods user = tbList.get(i);  
	      
	     row = sheet.createRow(i + 1);  
	      
	     cell = row.createCell( 0);  
	     cell.setCellType(HSSFCell.ENCODING_UTF_16);  
	     cell.setCellValue(user.getId());  
	      
	     cell = row.createCell( 1);  
	     cell.setCellType(HSSFCell.ENCODING_UTF_16);  
	     cell.setCellValue(user.getGoodsName());  
	      
	     cell = row.createCell( 2);  
	     cell.setCellType(HSSFCell.ENCODING_UTF_16);  
	     cell.setCellValue(user.getGoodsType());  
	     
	     cell = row.createCell( 3);  
	     cell.setCellType(HSSFCell.ENCODING_UTF_16);  
	     cell.setCellValue(user.getGoodsAddress());  
	      
	     }  
	     
	     //����ʵ����һ������������е���ݱ�д��һ�� byte ���顣�������������ݵĲ���д����Զ���������ʹ�� toByteArray() �� toString() ��ȡ��ݡ� 

	     //�ر� ByteArrayOutputStream ��Ч�������еķ����ڹرմ������Կɱ����ã��������κ� IOException

	    ByteArrayOutputStream os = new ByteArrayOutputStream();  
	    try {  
	    wb.write(os);  
	    } catch (IOException e) {  
	    e.printStackTrace();  
	    }  
	    byte[] content = os.toByteArray(); 
	    
	    InputStream is = new ByteArrayInputStream(content);  
	    return is;  
	    }  

}
