jQuery.validator.addMethod("noSpace", function(value, element) {
	if (value.trim().length == 0) {
		return this.optional(element) || false;
	} else {
		return this.optional(element) || true;
	}
}, "empty spaces are not allowed");

$(document).ready(function() {
	$('.dual_select').bootstrapDualListbox({
		selectorMinimalHeight : 160
	});

	/** ***bundle-create form**** */
	$('#_cBundle_Form').validate({
		rules : {
			bundleName : {
				required : true,
				noSpace : true,
				remote : {
					url : '/bundle/validate',
					type : "post",
					data : {
						bundleName : function() {
							return $('#_cBundleName').val().trim();
						},
						_csrf : function() {
							return $("input[name='_csrf']").val();
						}
					}

				}
			}
		},
		messages : {
			bundleName : {
				remote : "bundle name already exists"
			}
		},
		errorPlacement : function(error, element) {
			error.insertAfter(element);
		},
		submitHandler : function(form) {
			form.submit();
		}

	});
});
/* search assets by its properties for bundle creation */
function fetchAssets() {
	var _cAssetLanguage = $('#_cAssetLanguage').val();
	var _cAssetStudio = $('#_cAssetStudio').val();
	var _cAssetType = $('#_cAssetType').val();
	var _cAssetId = $('#_ctenantId').val();
	$('#_cAssets').empty();
	if (_cAssetType.trim().length == 0) {
		$('#_cAssetTypeError').text("Type is mandatory");
		$('#_cAssetType').addClass("error");
		return false;
	} else {
		$('#_cAssetTypeError').text("");
		$('#_cAssetType').removeClass("error");
	}

	$.ajax({
		type : 'GET',
		url : '/fetchAssetByAttributes?language=' + _cAssetLanguage
				+ "&studioName=" + _cAssetStudio + "&type=" + _cAssetType +"&tenantId=" + _cAssetId,
		success : function(response) {
			if (response.code === 200) {
				var i = 0;
				var data = '';
				$.each(response.results, function(key, value) {
					data = data + '<option value="' + value.id + '">'
							+ value.name + '</option>';					
				});
				$('#_cAssets').append(data);
				$('#_cAssets').bootstrapDualListbox('refresh');
			} else {
				swal('Oops...', 'No matching records found', 'error');
				return false;
			}
		}
	});
}

function populateFetchedAssetValues() {
	//$('#_cSelectedAssets').empty();
	var data = '';
	$("#_cAssets option:selected").each(
			function() {
				var $this = $(this);
				if ($this.length) {
					var assetText = $this.text();
					var assetValue = $this.val();
					data = data + '<option value="' + assetValue
							+ '" selected="selected">' + assetText
							+ '</option>';
				}
			});
	$('#_cSelectedAssets').append(data);
	$('#_cSelectedAssets').bootstrapDualListbox('refresh');
}
