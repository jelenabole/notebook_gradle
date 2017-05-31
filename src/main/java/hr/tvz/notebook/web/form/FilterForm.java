package hr.tvz.notebook.web.form;

import java.util.Arrays;
import java.util.List;

public class FilterForm {

	List<String> orderByList;
	String object;

	String orderBy;
	String orderDirection;
	String searchBy;

	//XXX - pagination:
	Integer active;
	Integer perPage;
	List<Integer> showPerPage;
	List<Integer> pages;

	public FilterForm() {
	}

	public FilterForm(List<String> orderByList, String objectName) {
		this.orderByList = orderByList;
		this.object = objectName;
		this.orderDirection = "asc";
		this.showPerPage = Arrays.asList(5, 10, 15, 20);
		this.active = 1;
		this.perPage = 5;
	}

	public List<String> getOrderByList() {
		return orderByList;
	}

	public void setOrderByList(List<String> orderByList) {
		this.orderByList = orderByList;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getPerPage() {
		return perPage;
	}

	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}

	public List<Integer> getShowPerPage() {
		return showPerPage;
	}

	public void setShowPerPage(List<Integer> showPerPage) {
		this.showPerPage = showPerPage;
	}

	public List<Integer> getPages() {
		return pages;
	}

	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}

}