// A $( document ).ready() block.
$( document ).ready(function() {
    

	// Get the selected hike value
	var selectedHike = $("select#hike").val();
	

	// function to disable duration options based on hike
	function disableDuration(hike){

		// reset the list each time function is called
		$("select#duration option").each(function() {
		    var $thisOption = $(this);

		    $thisOption.prop('disabled', false);

		});

		// based on hike, disable duration options 
		switch(parseInt(hike)) {
		  case 1:
		    $("select#duration option").each(function() {
			    var $thisOption = $(this);

			    if($thisOption.val() != "3" && $thisOption.val() != "5") {
			        $thisOption.attr("disabled", "disabled");
			    }
			});
		    break;
		  case 2:
		    $("select#duration option").each(function() {
			    var $thisOption = $(this);

			    if($thisOption.val() != "2" && $thisOption.val() != "3" && $thisOption.val() != "5") {
			        $thisOption.attr("disabled", "disabled");
			    }
			});
		    break;
		  case 3:
		  	$("select#duration option").each(function() {
			    var $thisOption = $(this);

			    if($thisOption.val() != "5" && $thisOption.val() != "7") {
			        $thisOption.attr("disabled", "disabled");
			    }
			});
		  	break;
		  default:
		    console.log("derp");
		}
	}

	// call this function once on page load
	disableDuration(selectedHike);

	// listen for a change on the hike select
	$('select#hike').on('change', function() {
	  disableDuration( this.value );
	});
	
	// get these DOM elements if they exist
	var numHikers = parseInt($("select#people").val());
	
	// set the numHikers
	if(numHikers){
		$("#numHikers").text(numHikers);
	}
	
});