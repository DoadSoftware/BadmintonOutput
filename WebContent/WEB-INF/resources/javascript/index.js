var match_data;
function processWaitingButtonSpinner(whatToProcess) 
{
	switch (whatToProcess) {
	case 'START_WAIT_TIMER': 
		$('.spinner-border').show();
		$(':button').prop('disabled', true);
		break;
	case 'END_WAIT_TIMER': 
		$('.spinner-border').hide();
		$(':button').prop('disabled', false);
		break;
	}
	
}
function initialisePage(whatToProcess)
{
	switch (whatToProcess) {
	case 'initialise':
		processUserSelection($('#select_broadcaster'));
		break;
	}
}
function processUserSelection(whichInput)
{	
	switch ($(whichInput).attr('name')) {
	case 'animateout_graphic_btn':
		if(confirm('It will Also Delete Your Preview from Directory...\r\n \r\nAre You Sure To Animate Out? ') == true){
			processBadmintonProcedures('ANIMATE-OUT');	
		}
		break;
	
	case 'scorebug_graphic_btn':
		$("#captions_div").hide();
		switch ($(whichInput).attr('name')){
			case 'scorebug_graphic_btn':
				processBadmintonProcedures('SCOREBUG_GRAPHICS-OPTIONS');
				break;
		}
		break;
		
	case 'populate_scorebug_btn':
		processWaitingButtonSpinner('START_WAIT_TIMER');
		switch($(whichInput).attr('name')){
			case 'populate_scorebug_btn':
				processBadmintonProcedures('POPULATE-SCOREBUG');
				break;
		}
		break;
		
	case 'cancel_graphics_btn':
		$('#select_graphic_options_div').empty();
		document.getElementById('select_graphic_options_div').style.display = 'none';
		$("#captions_div").show();
		break;
		
		case 'select_broadcaster':
			alert('Broadcaster-index-1:' + $('#select_broadcaster option:selected').val());
			switch ($('#select_broadcaster option:selected').val()) {
				case 'DOAD_In_House_Everest':
					$('#vizPortNumber').attr('value','1980');
					$('label[for=vizScene], input#vizScene').hide();
					break;
			}
			break;
		
	case 'load_scene_btn':
		if(checkEmpty($('#vizIPAddress'),'IP Address Blank') == false
			|| checkEmpty($('#vizPortNumber'),'Port Number Blank') == false) {
			return false;
		}
      	document.initialise_form.submit();
		break;
	}
}
function processBadmintonProcedures(whatToProcess)
{
	var valueToProcess;
	switch(whatToProcess) {
	
	case 'READ-MATCH-AND-POPULATE':
		valueToProcess = $('#matchFileTimeStamp').val();
		break;
		
	case 'POPULATE-SCOREBUG':
		switch ($('#select_broadcaster :selected').val()){
		case 'DOAD_In_House_Everest':
			valueToProcess = $('#scorebugScene').val()
			break;
		}
		break;	
	}

	$.ajax({    
        type : 'Get',     
        url : 'processBadmintonProcedures.html',     
        data : 'whatToProcess=' + whatToProcess + '&valueToProcess=' + valueToProcess, 
        dataType : 'json',
        success : function(data) {
        	switch(whatToProcess) {
			/*case 'READ-MATCH-AND-POPULATE':
				if(data){
					if($('#match_file_timestamp').val() != data.match_file_timestamp) {
						document.getElementById('match_file_timestamp').value = data.match_file_timestamp;
					}
				}
				break;*/
			case 'SCOREBUG_GRAPHICS-OPTIONS':
				addItemsToList('SCOREBUG-OPTION',data);
				match_data = data;
				break;
			
			case 'POPULATE-SCOREBUG':
				if (data.status.toUpperCase() == 'SUCCESSFUL'){
					if(confirm('Animate In?') == true){
						$('#select_graphic_options_div').empty();
						document.getElementById('select_graphic_options_div').style.display = 'none';
						$("#captions_div").show();
						
						switch(whatToProcess){
							case 'POPULATE-SCOREBUG':
								processBadmintonProcedures('ANIMATE-IN-SCOREBUG');
								break;
						}
					}
				}
				break;
			
        	}
			processWaitingButtonSpinner('END_WAIT_TIMER');
	    },    
	    error : function(e) {    
	  	 	console.log('Error occured in ' + whatToProcess + ' with error description = ' + e);     
	    }    
	});
}
function addItemsToList(whatToProcess, dataToProcess)
{
	var select,option,header_text,div,table,tbody,row;
	var cellCount = 0;

	switch (whatToProcess) {
	case 'SCOREBUG-OPTION':
		alert('Broadcaster-index-2:' + $('#select_broadcaster :selected').val());
		switch($('#select_broadcaster :selected').val()){
			case 'DOAD_In_House_Everest':
				
				$('#select_graphic_options_div').empty();
				
				header_text = document.createElement('h6');
				header_text.innerHTML = 'Select Graphic Options';
				document.getElementById('select_graphic_options_div').appendChild(header_text);
				
				table = document.createElement('table');
				table.setAttribute('class', 'table table-bordered');
				
				row = tbody.insertRow(tbody.rows.length);
				
				select = document.createElement('input');
				select.type = "text";
				select.id = 'scorebugScene';
				select.value = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/Scorebug.sum';
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				
				option = document.createElement('input');
				option.type = 'button';
				option.name = 'populate_scorebug_btn';
				option.value = 'Populate ScoreBug'
				
				option.id = option.name;
		   		option.setAttribute('onclick',"processUserSelection(this)");
		   		
		   		div = document.createElement('div');
		    	div.append(option);
		    	
		    	option = document.createElement('input');
				option.type = 'button';
				option.name = 'cancel_graphics_btn';
				option.id = option.name;
				option.value = 'Cancel';
				option.setAttribute('onclick','processUserSelection(this)');
				
				div.append(option);
		    
			    row.insertCell(cellCount).appendChild(div);
			    cellCount = cellCount + 1;
			    
				document.getElementById('select_graphic_options_div').style.display = '';
				
				break;
		}
		break;
	}
	
}
function checkEmpty(inputBox,textToShow) {

	var name = $(inputBox).attr('id');
	
	document.getElementById(name + '-validation').innerHTML = '';
	document.getElementById(name + '-validation').style.display = 'none';
	$(inputBox).css('border','');
	if(document.getElementById(name).value.trim() == '') {
		$(inputBox).css('border','#E11E26 2px solid');
		document.getElementById(name + '-validation').innerHTML = textToShow + ' required';
		document.getElementById(name + '-validation').style.display = '';
		document.getElementById(name).focus({preventScroll:false});
		return false;
	}
	return true;	
}