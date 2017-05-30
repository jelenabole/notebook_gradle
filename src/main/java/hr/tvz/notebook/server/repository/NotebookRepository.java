package hr.tvz.notebook.server.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.tvz.notebook.model.Notebook;

public interface NotebookRepository extends JpaRepository<Notebook, Long> {

	public List<Notebook> findAll();

	public Notebook findById(Integer id);

	@SuppressWarnings("unchecked")
	public Notebook save(Notebook notebook);

	@Transactional
	public void deleteById(Integer id);

}
