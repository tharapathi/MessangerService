<html>
<style>
table {
  border-collapse: collapse;
}
td, th {
  border: 1px solid orange;
}
</style>
<body>
    <h2>Jersey RESTful Web Application!</h2>
    <p>User Details</p>
    
    <form action="#">
    	<table>
    		<tr>
    			<td>Name</td>
    			<td><input type = "text" name = "name" id = "name"></td>
    		</tr>
    		<tr>
    			<td>Email</td>
    			<td><input type = "text" name = "email" id = "email"></td>
    		</tr>
    		<tr>
    			<td>Phone Number</td>
    			<td><input type = "text" name = "phoneNumber" id = "phoneNumber"></td>
    		</tr>
    		<tr>
    			<td id = "buttons" colspan = "2"><input type = "button" value = "Add Details" id = "add">
    			<input type = "hidden" name = "id" id = "id">
    			<input type = "button" value = "Update Details" id = "update">
    			<input type = "reset" value = "Clear Form" id = "Clear">
    		</td>
    		</tr>
    	</table>
    </form>
    
    <table id = "dynamic">
    	<thead>
    	<tr>
    		<th>SNO</th>
    		<th>Name</th>
    		<th>Email</th>
    		<th>Phone Number</th>
    	</tr>
    	</thead>
    	
    	<tbody>
    		<tr>
    			<td id = "Id"></td>
    			<td id = "name"></td>
    			<td id = "Email"></td>
    			<td id = "phoneNumber"></td>
    			<td id = "buttons" colspan = "2">
    			<input type = "button" id = "remove" value = "Remove" onclick = "remove(this)"/>
    			<input type = "button" id = "populate" value = "Edit" onclick = "populate(this)"/>
    			</td>
    		</tr>
    	</tbody>
    
    </table>
    
</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>

function remove(event) {
	var id = $(event.parentElement.parentElement).find('#id').text();
	$.ajax({
		url : "http://localhost:8080/MessangerBook/restapi/messages/"+id,
		type: 'DELETE',
		success : function(data) {
			location.reload();
		}
	});
}

function populate(event) {
	var id = $(event.parentElement.parentElement).find('#id').text();
	$.ajax({
		url : "http://localhost:8080/MessangerBook/restapi/messages/"+id,
		success : function(data) {
			$('#id').val(data.id);
			$('#name').val(data.name);
			$('#email').val(data.email);
			$('#phoneNumber').val(data.phoneNumber);
		}
	});
}

$(document).ready(function(){
	
	$("#add").click(function() {
		var query = "/"+$('#name').val()+"/"+$("#email").val()+"/"+$("#phoneNumber").val();
		$.ajax({
			url : "http://localhost:8080/MessangerBook/restapi/messages/addUser/3"+query,
			type : "POST",
			success : function(data) {
				location.reload();
			}
		});
	});
	
	$("#update").click(function() {
		var query = $("#id").val()+"/"+$('#name').val()+"/"+$("#email").val()+"/"+$("#phoneNumber").val();
		$.ajax({
			url : "http://localhost:8080/MessangerBook/restapi/messages/edit/"+query,
			type : "PUT",
			success : function(data) {
				location.reload();
			}
		});
	});
	
	$('#dynamic tbody tr:first-child').hide();
	$.ajax({
		url : "http://localhost:8080/MessangerBook/restapi/messages",
		success: function(data) {
			$('#dynamic tbody tr:not(:first-child)').remove();
			$.each(data, function(index, row) {
				var tr = $('#dynamic tbody tr:first-child').clone();
				$(tr).removeAttr("style");
				$(tr).find('#id').text(row.id);
				$(tr).find('#name').text(row.name);
				$(tr).find('#email').text(row.email);
				$(tr).find('#phoneNumber').text(row.phoneNumber);
				$('#dynamic tbody').append(tr);
			})
		}
	});
});
</script>