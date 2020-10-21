// A $( document ).ready() block.
$( document ).ready(function() {
    

	var selectedHike = $("select#hike").val();
	

	function disableDuration(n){
		$("select#duration option").each(function() {
		    var $thisOption = $(this);

		    $thisOption.prop('disabled', false);

		});


		switch(parseInt(n)) {
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
		  	break
		  default:
		    console.log("derp");
		}
	}

	disableDuration(selectedHike);

	$('select#hike').on('change', function() {
	  disableDuration( this.value );
	});
	
	var totalCost = parseInt($("#totalCost").text());
	var numHikers = parseInt($("select#people").val());
	
	if(totalCost){
		$("#totalCost").text(totalCost*numHikers);
	}
	
	if(numHikers){
		$("#numHikers").text(numHikers);
	}
});