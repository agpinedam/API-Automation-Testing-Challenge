public class RequestList {
	private String name;
	private String description;
	private String language;

	public RequestList(String name, String description, String language) {
		this.name = name;
		this.description = description;
		this.language = language;
	}

	public String getName(){
		return name;
	}

	public String getDescription(){
		return description;
	}

	public String getLanguage(){
		return language;
	}
}
