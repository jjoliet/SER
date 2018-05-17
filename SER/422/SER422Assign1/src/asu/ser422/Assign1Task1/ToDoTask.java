package asu.ser422.Assign1Task1;

public class ToDoTask {
	private String task;
    private String desc;
    private String[] days;
    private String estimate;
    private String priority;

    public ToDoTask(String task, String desc, String[] days, String est, String priority)
    {
	this.task  = task;
	this.desc  = desc;
	this.days = days;
	this.estimate = est;
	this.priority = priority;
    }
    
    public String toString(boolean html){
    	StringBuilder sb = new StringBuilder();
    	if(html){
    		sb.append("<b><u>Task: " + this.task + "</b></u><br>" + "Description: " + this.desc + "<br>" + "Days: ");
    		for(int i = 0; i < days.length; i++){
    			sb.append(days[i]);
    			if(i+1 < days.length){
    				sb.append(", ");
    			}
    		}
    		sb.append("<br>" + "Estimate: " +this.estimate + "<br>" + "Priority: " + this.priority + "<br>");    
    	}else{
    		sb.append("\nTask: " + this.task + "\n" + "Description: " + this.desc + "\n" + "Days: ");
    		for(int i = 0; i < days.length; i++){
    			sb.append(days[i]);
    			if(i>0 && i+1 < days.length){
    				sb.append(", ");
    			}
    		}
    		sb.append("\n" + "Estimate: " +this.estimate + "\n" + "Priority: " + this.priority + "\n");
    	}
		return sb.toString();
    	
    }
    
    //Only used for filewriting
    public String toString(){
    	StringBuilder sb = new StringBuilder();
		sb.append(this.task + ";" + this.desc + ";");
		for(int i = 0; i < days.length; i++){
    		sb.append(days[i]);
    		if(i+1 < days.length){
    			sb.append(", ");
    		}
    	}
    	sb.append(";" +this.estimate + ";" +this.priority+";");
    	return sb.toString();
    }
    
    public boolean testDays(String days){
    	String[] daysArr;
		 if(!days.isEmpty()){
			 char[] tempDays = days.toCharArray();
			 daysArr = new String[tempDays.length];
			 for(int i =0; i < tempDays.length; i++){
				 switch(tempDays[i]){
				 	case '1': daysArr[i] = "Monday";
				 			break;
				 	case '2': daysArr[i] = "Tuesday";
				 			break;
				 	case '3': daysArr[i] = "Wednesday";
				 			break;
				 	case '4': daysArr[i] = "Thursday";
				 			break;
				 	case '5': daysArr[i] = "Friday";
		 					break;
				 	case '6': daysArr[i] = "Saturday";
		 					break;
				 	case '7': daysArr[i] = "Sunday";
		 					break;
				 }
				 for(int j = 0; j < this.days.length; j++){
					 if(this.days[j].contains(daysArr[i])){
						 return true;
					 }
				 }
			 }
			 return false;
		 }
		 return true;
    }
	public String getTask() {
		return task;
	}
	public boolean contains(String val){
		return this.contains(val);
	}
	public void setTask(String task) {
		this.task = task;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String[] getDays() {
		return days;
	}

	public void setDays(String[] days) {
		this.days = days;
	}

	public String getEstimate() {
		return estimate;
	}

	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
    
}
