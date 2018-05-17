package asu.ser422.Assign1Task1;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TaskList {
	//Use map to rewrite if the 'task' already exists, Similar to how Phonebook uses one.
	private Map<String, ToDoTask> _tasks = new HashMap<String, ToDoTask>();
	
	public static final String DEFAULT_FILENAME = "tasklist.txt";
	public TaskList () throws IOException{
		this(DEFAULT_FILENAME);
	}
	
	public TaskList(String fname) throws IOException {
		this(new BufferedReader(new FileReader(fname)));
	}
	public TaskList(InputStream is) throws IOException {
		this(new BufferedReader(new InputStreamReader(is)));
	}
	
	private TaskList(BufferedReader br) throws IOException {	
		String task = null;
	    String desc = null;
	    String[] days = null;
	    String estimate = null;
	    String priority = null;

		try {
			String nextLine = null;
			while ( (nextLine=br.readLine()) != null)
			{
				task  = nextLine;
				desc = br.readLine();
				days = br.readLine().split(", ");
				estimate = br.readLine();
				priority = br.readLine();
				addTask(task, desc, days, estimate, priority);
			}
			br.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error processing tasklist");
			throw new IOException("Could not process the tasklist file");
		}
	}
	 //Used for task 1 creation
	 public TaskList(ToDoTask tdTask)
	    {
		 _tasks.put(tdTask.getTask(), tdTask);
	    }
	 
	 //Used for task 2.
	 public void addTask(String task, String desc, String[] days, String estimate, String priority){
		 _tasks.put(task, new ToDoTask(task, desc, days, estimate, priority));
	 }
	 //Used for task 1
	 public void addTask(ToDoTask tdTask){
		 _tasks.put(tdTask.getTask(), tdTask);
	 }
	 
	 public int getSize(){
		 return _tasks.size();
	 }
	 
	 public ToDoTask getTasks(String task){
			 return (ToDoTask)_tasks.get(task);
	 }
	 //Searches tasks for a value. Days not implemented yet.
//	 public String[] searchTasks(String val){
//		 System.out.println("In Task List: " +_tasks.containsValue(val)+ " " + val);
//		 System.out.println(_tasks.get("stuff").toString());
//		 if(_tasks.containsValue(val)){
//			 ArrayList<String> temp = new ArrayList<String>();
//			 ToDoTask nextEntry =null;
//			 for (Iterator<ToDoTask> iter = _tasks.values().iterator(); iter.hasNext();) {
//					nextEntry = iter.next();
//					if(nextEntry.contains(val)){
//						temp.add(nextEntry.toString());
//					}
//			 }
//			 return temp.toArray(new String[temp.size()]);
//		 }
//		 return null;
//	 }
	 //Searches and returns tasks in string form. Does not work with Days currently.
	 public String getTasks(Map<String, String[]> params, boolean html){
		 StringBuilder sb = new StringBuilder();
		 ToDoTask temp;
		 String task = (params.containsKey("task")) ? params.get("task")[0] : "";
		 String description = (params.containsKey("description")) ? params.get("description")[0] : "";
		 String days = (params.containsKey("days")) ? params.get("days")[0] : "";
		 String est = (params.containsKey("estimate")) ? params.get("estimate")[0] : "";
		 String priority = (params.containsKey("priority")) ? params.get("priority")[0] : "";
		 
		 
		 
		 if(!task.isEmpty() && _tasks.containsKey(task)){
			 temp = _tasks.get(task);
			 if((description.isEmpty() || temp.getDesc().contains(description)) && (est.isEmpty() || temp.getEstimate().contains(est)) && (priority.isEmpty() || temp.getPriority().contains(priority)) && temp.testDays(days)){
				 sb.append(temp.toString(html));
			 }else{
				 sb.append("No results matched query");
			 }
		 }else{
			 if(task.isEmpty()){
					ToDoTask nextEntry = null;
					for (Iterator<ToDoTask> iter = _tasks.values().iterator(); iter.hasNext();) {
						nextEntry = iter.next();
						if((description.isEmpty() || nextEntry.getDesc().contains(description)) && (est.isEmpty() || nextEntry.getEstimate().contains(est)) && (priority.isEmpty() || nextEntry.getPriority().contains(priority))&& nextEntry.testDays(days)){
							sb.append(nextEntry.toString(html));
						}else if(description.isEmpty() && est.isEmpty() && priority.isEmpty() && days.isEmpty()){
							String[] emptyCall = listEntries(html);
							for(int i = 0; i< emptyCall.length; i++){
								sb.append(emptyCall[i]);
							}
						}else{
							sb.append("No results matched query");
						}
					}
			 }
		 }
		 return sb.toString();
		 
	 }
	 //Lists all entries in the task list. Task 1
	 public String[] listEntries(boolean html)
		{	
			String[] rval = new String[_tasks.size()];
			int i = 0;
			ToDoTask nextEntry = null;
			for (Iterator<ToDoTask> iter = _tasks.values().iterator(); iter.hasNext();) {
				nextEntry = iter.next();
				rval[i++] = nextEntry.toString(html);
			}
			return rval;
		}
	 
	 //Lists entries for file writing version.
	 public String[] listEntries()
		{	
		 	StringBuilder rval = new StringBuilder();
			ToDoTask nextEntry = null;
			for (Iterator<ToDoTask> iter = _tasks.values().iterator(); iter.hasNext();) {
				nextEntry = iter.next();
				rval.append(nextEntry.toString());				
			}
			return rval.toString().split(";");
		}
	 
	 //Used to write to file and save.
	 public void saveTaskList(String fname){
		 try {
			 	
				System.out.println("Opening " + fname);
				PrintWriter pw = new PrintWriter(new FileOutputStream(fname));
				System.out.println("...done");
				String[] entries = listEntries();
				for (int i = 0; i < entries.length; i++)
					pw.println(entries[i]);

				pw.close();
			}
			catch (Exception exc)
			{ 
				exc.printStackTrace(); 
				System.out.println("Error saving task list");
			}
	 }
}
