package librarymanagement.model;


public class Books {


	private String name;
	private int id;
	private String author;
	private String journer;
	private int availableCount;
	private String publication;

	public void setId(Object object) {
        this.id = (int) object;
    }
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getJourner() {
		return journer;
	}

	public void setJourner(String journer) {
		this.journer = journer;
	}

	public int getAvailableCount() {
		return availableCount;
	}

	public void setAvailableCount(int availableCount) {
		this.availableCount = availableCount;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}
}

