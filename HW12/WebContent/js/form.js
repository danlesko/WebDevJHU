
// A $( document ).ready() block.
$( document ).ready(function() {
	
	// Let the user decide if they want to see a JSON or a tabular output	
	$('#json').on('change', function(){
	    if ($(this).is(':checked')) {
	        $('form').attr('action', './webresources/bhc/getHikeInfoJSON');
	    } else {
	        $('form').attr('action', './webresources/bhc/getHikeInfoHTML');
	    }
	});
	
	
});