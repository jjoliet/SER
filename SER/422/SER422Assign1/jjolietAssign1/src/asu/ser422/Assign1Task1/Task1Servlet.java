package asu.ser422.Assign1Task1;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Task1Servlet extends HttpServlet {
	private static String _filename = null;
	
	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		_filename = sc.getInitParameter("tasklist");
		if(_filename == null || _filename.length() == 0){
			throw new ServletException();
		}
		System.out.println("\n\n\nLoaded init param tasklist with value " + _filename + "\n\n\n");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
			boolean uses_HTML = req.getHeader("accept").contains("text/html");
			String contentType = (uses_HTML) ? "text/html" : "text/plain";
			boolean has_AWK = req.getHeader("user-agent").contains("AppleWebKit");			
			StringBuilder sb = new StringBuilder();
			
			//process headers
			String url = req.getHeader("referer");
			Map<String, String[]> getParams = req.getParameterMap();
			
			if(uses_HTML && has_AWK){
				sb.append("<html><title>SER422 Lab 1</title></head\n<body style=\"background-color:gray;\">\n");
			}
			if(uses_HTML && !has_AWK){
				sb.append("<html><title>SER422 Lab 1</title></head\n<body>\n");
			}
			//process req params
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(_filename);
		    TaskList tl = null;
		    //Create a taskList by reading in file (so that our map can overwrite if necessary)
		    try{
		    	tl = new TaskList(is);
		    	sb.append(tl.getTasks(getParams, uses_HTML));
		    	//resReturn = "Operation Successful! There are currently: " + tl.getSize()+" tasks.";
		    	//save task to file  
		    }catch(Exception e){
		    	//resReturn = "Operation Unsuccessful! Something happened!";
		    	sb.append("Unsuccessful operation!");
		    	e.printStackTrace();
		    	
		    }
			
			if(uses_HTML){
				sb.append("<center><a href=\"" + url +"\">Home</a></center>");
				sb.append("</body></html>");
			}			
			//assemble response payload (above)
			
			
			//assign response header
			res.setContentType(contentType);
			res.setStatus(HttpServletResponse.SC_OK);
				
			//write out the response
			PrintWriter out = res.getWriter();
			out.print(sb.toString());
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {	
		//process headers
		String url = req.getHeader("referer");
		boolean has_AWK = req.getHeader("user-agent").contains("AppleWebKit");
		boolean uses_HTML = req.getHeader("accept").contains("text/html");
		
		//process req params
		String task = req.getParameter("task");
	    String desc = req.getParameter("description");
	    String[] days = req.getParameterValues("days");
	    String estimate = req.getParameter("estimate");
	    String priority = req.getParameter("priority");
		
		//perform processing
	    //Step 1: Create ToDoTask with info from request.
	    ToDoTask reqTask = new ToDoTask(task, desc, days, estimate, priority);
	    StringBuilder sb = new StringBuilder();
	    String resReturn;
	    
	    //Step2: Add that task to the tasklist
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream(_filename);
	    TaskList tl = null;
	    //Create a taskList by reading in file (so that our map can overwrite if necessary)
	    try{
	    	tl = new TaskList(is);
	    	//addTask (overwrites due to map usage)
	    	tl.addTask(reqTask);
	    	resReturn = "Operation Successful! There are currently: " + tl.getSize()+" tasks.";
	    	//save task to file 

	    }catch(Exception e){
	    	resReturn = "Operation Unsuccessful! Something happened!";
	    	e.printStackTrace();
	    	
	    }
	    tl.saveTaskList(this.getServletContext().getRealPath("/WEB-INF/classes" + _filename));
	    //Step3: Declare content Type, Payload changes based on uses_HTML and has_AWK
	    String contentType = (uses_HTML) ? "text/html" : "text/plain";
	    
	    //Write out new page with hyperlink back and the number of tasks
		
		
		if(uses_HTML && has_AWK){
			//Title+Body Tags
			sb.append("<html><head><title>SER422 Lab 1</title></head>\n<body style=\"background-color:gray;\">\n<b>");
			sb.append(resReturn);
			sb.append("</b><br><br><center><a href=\"" + url +"\">Home</a></center></body></html>");
		}else if(uses_HTML){
			sb.append("<html><head><title>SER422 Lab 1</title></head>\n<body>\n<b>");
			sb.append(resReturn);
			sb.append("</b><br><br><center><a href=\"" + url +"\">Home</a></center></body></html>");
			}else{
			sb.append("\n" + resReturn);
		}
		
		//assign response header
		res.setContentType(contentType);
		res.setStatus(HttpServletResponse.SC_OK);
		
		//write out the response
		PrintWriter out = res.getWriter();
		out.print(sb.toString());
	}
}
