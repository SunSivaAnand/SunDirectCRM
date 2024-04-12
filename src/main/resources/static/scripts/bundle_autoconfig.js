/**
 * 
 */
$(document).ready(function(){
	$('[data-toggle="tooltip"]').tooltip();
	//var actions = $("table td:last-child").html();
	var actions = '<div class="btn-group">'
		+'<a href="#" class="btn btn-xs btn-outline btn-primary add">Save'
		+'</a>';
	
	// Append table with add row form on add new button click
    $(".add-new").click(function(){
		$(this).attr("disabled", "disabled");
		var index = $("table tbody tr:last-child").index();
        var row = '<tr>' +
            '<td><input type="text" class="form-control" name="type" id="type"></td>' +
            '<td><input type="text" class="form-control" name="lang" id="lang"></td>' +
            '<td><input type="text" class="form-control" name="studio" id="studio"></td>' +
            '<td>' + actions + '</td>' +
        '</tr>';
    	$("table").append(row);		
		$("table tbody tr").eq(index + 1).find(".add").toggle();
        $('[data-toggle="tooltip"]').tooltip();
    });
	// Add row on add button click
	$(document).on("click", ".add", function(){
		var empty = false;
		var input = $(this).parents("tr").find('input[type="text"]');
        input.each(function(){
			if(!$(this).val()){
				$(this).addClass("error");
				empty = true;
			} else{
                $(this).removeClass("error");
            }
		});
		$(this).parents("tr").find(".error").first().focus();
		if(!empty){
			var type;
			var lang;
			var studio;
			var i = 1;
			input.each(function(){
				$(this).parent("td").html($(this).val());
				if(i == 1)
					type = $(this).val();
				if(i == 2)
					lang = $(this).val();
				if(i == 3)
					studio = $(this).val();
				i++;
			});			
			
			//ajax
			
			$.ajax({
				type : 'GET',
				url : "/bundle/auto/config/add/"+$('#bundleId').val()+"?type="+type+"&lang="+lang+"&studio="+studio,
				beforeSend: function( xhr ) {
					$('#pageloader').show();
				}
			})
			.done(function(result){
				if(result.code != 200){
					swal({
						title : 'Please Check the Fields',
						text  : '',
						type  : 'error',
						timer: 2000,
						showConfirmButton: false
					});
				}
				
				if(result.code == 200){
					swal({
						title : 'Added asset configuration',
						text  : '',
						type  : 'success',
						timer: 2000,
						showConfirmButton: true
					});
					
					$(this).parents("tr").find(".add").toggle();
					$(".add-new").removeAttr("disabled");
				}
				location.reload();
				
			})
			.fail(function(xhr, status, err){
				console.log("error");
			})
			.always(function(){
				$('#pageloader').hide();
				
			});
			
			
		}		
    });
	// Edit row on edit button click
	$(document).on("click", ".edit", function(){		
        $(this).parents("tr").find("td:not(:last-child)").each(function(){
			$(this).html('<input type="text" class="form-control" value="' + $(this).text() + '">');
		});		
		$(this).parents("tr").find(".add, .edit").toggle();
		$(".add-new").attr("disabled", "disabled");
    });
	// Delete row on delete button click
	$(document).on("click", ".delete", function(e){
		var value = $(this).attr('id');
		$.ajax({
			type : 'GET',
			url : "/bundle/auto/config/delete/"+value,
			beforeSend: function( xhr ) {
				$('#pageloader').show();
			}
		})
		.done(function(result){
			if(result.code != 200){
				swal({
					title : 'Please Check the Fields',
					text  : '',
					type  : 'error',
					timer: 2000,
					showConfirmButton: false
				});
			}
			
			if(result.code == 200){
				swal({
					title : 'Deleted asset configuration.',
					text  : '',
					type  : 'success',
					timer: 2000,
					showConfirmButton: true
				});
				location.reload();
			}
			
		})
		.fail(function(xhr, status, err){
			console.log("error");
		})
		.always(function(){
			$('#pageloader').hide();
			$(this).parents("tr").remove();
			$(".add-new").removeAttr("disabled");
		});
	});
});
