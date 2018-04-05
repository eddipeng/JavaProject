package cn.itcast.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.service.BookService;
import cn.itcast.utils.UUIDUtil;
import cn.itcast.vo.Book;

public class AddBookServlet extends HttpServlet {

	/**
	 * 上传图书图片
	 */
	private static final long serialVersionUID = 1135044075581163129L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 完成上传图片的功能
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// 核心文件上传
				ServletFileUpload upload = new ServletFileUpload(factory);
				// 上传文件中文乱码
				upload.setHeaderEncoding("UTF-8");
				// 真实文件的名称
				String realFilename = "";
				
				// 存储普通的表单项的
				Map<String, String> map = new HashMap<String, String>();
				
				// 解析request
				try {
					// 表单分成几部分，FileItem代表一个部分
					List<FileItem> list = upload.parseRequest(request);
					// 循环遍历
					for (FileItem fileItem : list) {
						// 进行判断的操作，是普通表单还是文件上传项
						if(fileItem.isFormField()){
							// 是普通项
							// 解决中文乱码问题
							// 获取key=表单的字段名称（bname  price）  和value=（用户输入的值）
							String key = fileItem.getFieldName();
							// 获取用户输入的值
							String value = fileItem.getString("UTF-8");
							// 把所有的普通表单项的数据全部都保存到map集合中
							map.put(key, value);
							
						}else{
							// 文件上传项
							// 可以获取文件的名称
							String filename = fileItem.getName();
							
							// 把filename保存到数据库中
							
							// 想把文件名称的换掉
							// UUID生成唯一字符串（有文件的后缀名吗？），自己拼接后缀名，后缀名filename
							int index = filename.lastIndexOf(".");
							// 文件的后缀名
							String lastname = filename.substring(index);
							
							// 文件名称：jisdjfolsdfjowjeroijwe.jpg
							realFilename = UUIDUtil.getUUID()+lastname;
							
							// 获取输入流
							InputStream in = fileItem.getInputStream();
							// 把文件上传到哪？
							// 先获取book_img的绝对磁盘路径
							String path = getServletContext().getRealPath("/book_img");
							OutputStream os = new FileOutputStream(path+"/"+realFilename);
							int len = 0;
							byte [] b = new byte[1024];
							while((len = in.read(b)) != -1){
								os.write(b, 0, len);
							}
							in.close();
							os.close();
							
							// fileItem.write(new File);
						}
					}
					
					// 把图书的信息封装好
					Book book = new Book();
					// 先封装一些数据
					BeanUtils.populate(book, map);
					// 设置图书的主键
					book.setBid(UUIDUtil.getUUID());
					// 图书有效的
					book.setIsdel(0);
					// 图片保存的路径  book_img/dfjsdikfjsldfjslo.jpg
					book.setImage("book_img/"+realFilename);
					
					// 保存到数据库中
					BookService bs = new BookService();
					// 数据保存成功了
					bs.save(book);
					
					// 转发或者重定向到页面
					request.getRequestDispatcher("/book?method=findByPage").forward(request, response);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
