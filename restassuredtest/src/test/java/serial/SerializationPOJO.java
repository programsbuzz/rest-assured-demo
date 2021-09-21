package serial;

public class SerializationPOJO {
	String name;
	String email;
	String gender;
	String status;
	
	
	public SerializationPOJO( String name, String email, String gender, String status) {
		this.name=name;
		this.email=email;
		this.gender=gender;
		this.status=status;
		
	}

	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender(){
		return gender;
	}
	public void setGender(String gender){
		this.gender = gender;
		
	}
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
		
	}
	

        public String toString(){
                return "The Person name is " + this.name + " And his email is " + this.email + " he the first "+this.gender+ " candidate with dev status as " +this.status; 
        }
	
}