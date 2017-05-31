// get notes (on-load):
function getAll() {
	getNotes(form = {
		orderBy : null,
		orderDirection : null
	});
}

// get notes (with filter):
function filter() {
	var form = {};
	form["orderBy"] = $("#orderBy").val();
	form["orderDirection"] = $(".filterForm input[type='radio']:checked").val()
	form["searchBy"] = $("#searchBy").val();
	form["perPage"] = $("#perPage option:selected").val();

	getNotes(form);
}

// get notes - ajax:
function getNotes(form) {
	// console.log(form);
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "note/search",
		data : JSON.stringify(form),
		timeout : 100000,
		success : function(data) {
			console.log("GET ALL");
			// console.log("SUCCESS: ", data);
			$("#table").html(data);
			getPagination();
			deleteForm();
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
}

// edit (get) one:
function getOne(id) {
	$.get("note/edit/" + id, function(data) {
		console.log("GET FOR EDIT");
		// console.log("SUCCESS: ", data);
		$("#form").html(data);
		showForm();
	});
}

// add new - new form
function addNew() {
	$.get("note/new", function(data) {
		console.log("CREATE NEW");
		// console.log("SUCCESS: ", data);
		$("#form").html(data);
		showForm();
	});
}

// save (or update):
function saveForm() {
	// get data from form:
	var noteForm = getNoteInfo();

	// console.log("OBJECT: ", noteForm);
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "note/save",
		data : JSON.stringify(noteForm),
		timeout : 100000,
		success : function(data) {
			console.log("VALIDATED");
			// console.log("SUCCESS: ", data);
			$("#form").html(data);

			if (data == "<div></div>") {
				console.log("SAVED");
				deleteForm();
				filter();
			} else {
				console.log("ERRORS");
			}
		}
	});
}

// delete note:
function deleteNote(id) {
	$.ajax({
		type : "DELETE",
		url : "api/note/" + id,
		success : function(data) {
			console.log("DELETED ID: ", id);
			deleteForm();
			filter();
		}
	});
}

// change DB status:
function changeStatus(id) {
	$.get("api/note/changeStatus/" + id, function(data) {
		console.log("CHANGE STATUS FOR: ", id);
		deleteForm();
		filter();
	});
}

// get info from form:
var getNoteInfo = function() {
	var noteForm = {};
	noteForm["id"] = $("#id").val();
	noteForm["header"] = $("#header").val();
	noteForm["text"] = $("#text").val();

	var user = $("#user").find('option:selected').data("object");
	noteForm["user"] = user;
	var notebook = $("#notebook").find('option:selected').data("object");
	noteForm["notebook"] = notebook;

	noteForm["important"] = $(".newForm input[type='checkbox']:checked").val() == undefined ? null
			: "IMPORTANT";
	noteForm["mark"] = $(".newForm input[type='radio']:checked").val() == undefined ? null
			: $(".newForm input[type='radio']:checked").val();

	return noteForm;
}

// delete form (cancel bttn):
var deleteForm = function() {
	$.get("note/removeForm", function(data) {
		console.log("REMOVE FORM");
		$("#form").html(data);
		hideForm();
	});
}

/** ************* SKRIVANJE FORME ************** */

var hideForm = function() {
	// $(".newForm").hide();
	$("#addButton").show();

}
var showForm = function() {
	// $(".newForm").show();
	$("#addButton").hide();
}

/** ************* SHOW PER PAGE ************** */

// get page numbers:
function getPagination() {
	$.get("note/page/" + $(".notes").length + "/"
			+ $("#perPage option:selected").val(), function(data) {
		console.log("GET PAGES");
		// console.log("SUCCESS: ", data);
		$("#pagination").html(data);
		$("#currentPage").val(1);
		refreshPaging();
	});
}

var refreshPaging = function() {
	var current = $("#currentPage").val();
	// change active link:
	$(".pages").removeClass("active");
	$("#" + current).addClass("active");

	// get start and end index:
	var perPage = $("#perPage").val();
	var until = perPage * current;
	var start = until - perPage;

	// hide everything except the ones on the page:
	$(".notes").hide();
	$(".notes").each(function(i, item) {
		if (i >= start && i < until)
			$(item).show();
	});
}

var changePage = function(num) {
	$("#currentPage").val(num);
	refreshPaging();
}

var back = function() {
	if ($("#currentPage").val() != 1) {
		$("#currentPage").val($("#currentPage").val() - 1);
		refreshPaging();
	}
}

var forth = function() {
	if ($("#currentPage").val() != $(".pages").length) {
		$("#currentPage").val(parseInt($("#currentPage").val()) + 1);
		refreshPaging();
	}
}