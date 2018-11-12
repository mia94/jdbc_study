package jdbc_study.dto;

import java.util.Arrays;

public class UserDetail {
	private int id;
	private String name;
	private Gender gender; 
	private byte[] pic; 
	private String bio;
	
	public UserDetail() {
	}

	public UserDetail(int id) {
		this.id = id;
	}

	public UserDetail(int id, String name, Gender gender, String bio) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.bio = bio;
	}

	public UserDetail(int id, String name, Gender gender, byte[] pic, String bio) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.pic = pic;
		this.bio = bio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	@Override
	public String toString() {
		return String.format("[%s, %s, %s, %s, %s]", id, name, 
				gender==Gender.FEMALE?"FEMALE?":"MALE",
				pic==null?"":pic.length+"bytes", bio.length()+"bytes");
	} 
	
	
	
	
}
