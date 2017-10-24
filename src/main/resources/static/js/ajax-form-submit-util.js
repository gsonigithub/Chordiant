/**
 * Method to post a form with id formId to postUrl and on success load the reloadUrl.
 * On error a div with id errorDiv will be populated with the error messages.
 * @param formId - Form to be posted
 * @param modalId - Modal container for form
 * @param postUrl - Url to post form
 * @param successUrl - Url to load on success
 */
function performSave(formId, modalId, postUrl, successUrl) {

	// <![CDATA[

	var serializedFormData = $('#'+formId).serialize();

	$.ajax({
		type : "POST",
		url : postUrl,
		data : serializedFormData,
		beforeSend : function() {
			$("#errorDiv").html('<ul id="errorList"></ul>');
		},
		success : function(data) {

			if (data.status == 'SUCCESS') {
				$('#' + modalId).modal('toggle');

				//TODO: to change to use referrer url from main layout ?
				if(successUrl != 'noUrl') {
					$(location).attr('href', successUrl);
				}

			} else {

				var ul = $("#errorList");

				for ( var i = 0; i < data.errorMessages.length; i++) {
					ul.append($("<li>").text(data.errorMessages[i]));
				}
			}

		}
	});

	// ]]>
}