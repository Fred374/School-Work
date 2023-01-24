var arr = new Array();

// Add tasks to the table and to localStorage
function addTask() {
	getTask();
	arr.push({
		pendingTaskName:document.getElementById("pendingTaskName").value,
		pendingClassName:document.getElementById("pendingClassName").value,
		pendingTaskPriority:document.getElementById("pendingTaskPriority").value,
		pendingTaskDueDate:document.getElementById("pendingTaskDueDate").value
	});
	
	localStorage.setItem("localData", JSON.stringify(arr));
	showTask();
}

// Get the task from storage
function getTask() {
	var str = localStorage.getItem("localData");
	if (str != null) {
		arr = JSON.parse(str);
	}
}

// Delete the entire table
function deleteTask() {
	localStorage.clear();
	location.reload();
}

// Show all of the items that were previously in the table
function showTask() {
	getTask();
	
	var table = document.getElementById("todoListTable");
	
	var x = table.rows.length;
	while(--x) {
		table.deleteRow(x);
	}
	
	for (i = 0; i < arr.length; i++) {
		var row = table.insertRow(1);
		var task = row.insertCell(0);
		var classname = row.insertCell(1)
		var priority = row.insertCell(2);
		var dueDate = row.insertCell(3);
		var deleteBtn = row.insertCell(4);
		
		var inputTaskName = arr[i].pendingTaskName;
		var inputClassName = arr[i].pendingClassName;
		var inputTaskPriority = arr[i].pendingTaskPriority;
		var inputTaskDueDate = arr[i].pendingTaskDueDate;
		var tempDeleteBtn = document.createElement("input");
		tempDeleteBtn.setAttribute("type", "button");
		tempDeleteBtn.setAttribute("onclick", "removeCurrentTask(this)");
		tempDeleteBtn.setAttribute("value", "Remove");
		tempDeleteBtn.setAttribute("name", "removeRow");
		
		task.innerHTML = inputTaskName;
		classname.innerHTML = inputClassName;
		priority.innerHTML = inputTaskPriority;
		dueDate.innerHTML = inputTaskDueDate;
		deleteBtn.appendChild(tempDeleteBtn);
	}
}

// Code to remove rows from the table
function removeCurrentTask(oButton) {
	var table = document.getElementById("todoListTable");
	table.deleteRow(oButton.parentNode.parentNode.rowIndex);
	
	getTask();
	arr = [];
	for (i = 1; i < table.rows.length; i++) {
		arr.push({
			pendingTaskName:table.rows[i].cells[0].innerHTML,
			pendingClassName:table.rows[i].cells[1].innerHTML,
			pendingTaskPriority:table.rows[i].cells[2].innerHTML,
			pendingTaskDueDate:table.rows[i].cells[3].innerHTML
		});
	}
	localStorage.setItem("localData", JSON.stringify(arr));
	showTask();
}


