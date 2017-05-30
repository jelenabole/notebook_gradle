package hr.tvz.notebook.other.mapper;

import hr.tvz.notebook.model.Notebook;
import hr.tvz.notebook.web.form.NotebookForm;

public class NotebookMapper {

	public static NotebookForm mapNotebookToForm(Notebook notebook) {
		NotebookForm notebookForm = new NotebookForm();
		notebookForm.setId(notebook.getId());
		notebookForm.setTitle(notebook.getTitle());
		notebookForm.setDescription(notebook.getDescription());

		return notebookForm;
	}

	public static Notebook mapFormToNotebook(NotebookForm notebookForm) {
		Notebook notebook = new Notebook();
		notebook.setId(notebookForm.getId());
		notebook.setTitle(notebookForm.getTitle());
		notebook.setDescription(notebookForm.getDescription());

		return notebook;
	}
}
