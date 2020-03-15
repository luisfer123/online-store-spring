<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<!-- Button trigger modal -->
<button type="button" class="btn btn-danger" data-toggle="modal"
	data-target="#confirmDeleteModal${user.id }">Delete</button>

<!-- Modal -->
<div class="modal fade" id="confirmDeleteModal${user.id }" tabindex="-1" role="dialog"
	aria-labelledby="confirmDeleteModalLabel${user.id }" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="confirmDeleteModalLabel${user.id }">Delete User</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				Do you really want to delete this user?
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
				<a href="/users/delete?user_id=${user.id }" class="btn btn-danger">Delete</a>
			</div>
		</div>
	</div>
</div>