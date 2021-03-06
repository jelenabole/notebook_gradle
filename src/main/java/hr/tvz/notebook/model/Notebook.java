package hr.tvz.notebook.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "notebooks")
public class Notebook implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String title;
	String description;

	// XXX - skip while importing from database:
	@Transient
	Integer numberOfNotes;

	@OneToMany(mappedBy = "notebook", cascade = CascadeType.ALL)
	private List<Note> notes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNumberOfNotes() {
		return numberOfNotes;
	}

	public void setNumberOfNotes(Integer numberOfNotes) {
		this.numberOfNotes = numberOfNotes;
	}

	@Override
	public String toString() {
		return "NOTEBOOK - id: " + id + " - title: " + title + " - description: " + description;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (!(other instanceof Notebook))
			return false;

		Notebook notebook = (Notebook) other;
		return (id.equals(notebook.id) && title.equals(notebook.title));
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 31 + id.hashCode();
		hash = hash * 31 + title.hashCode();

		return hash;
	}

	public String getAjax() {
		String temp = "{";
		temp += "\"id\":\"" + id + "\",";
		temp += "\"title\":\"" + title + "\",";
		temp += "\"description\":\"" + description;
		temp += "\"}";

		return temp;
	}
}
