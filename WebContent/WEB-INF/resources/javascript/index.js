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
	case 'animateout_scorebugstat_btn':
		processBadmintonProcedures('ANIMATE-OUT-STAT');
		break;
	case 'scorebug_graphic_btn': case 'scorebugstat_graphic_btn': case 'singlel3matchid_graphic_btn': case 'singleffmatchid_graphic_btn': case 'doublel3matchid_graphic_btn':
	case 'doubleffmatchid_graphic_btn': case 'l3tieid_graphic_btn': case 'fftieid_graphic_btn': case 'sides_graphic_btn': case 'super_graphic_btn':
		$("#captions_div").hide();
		switch ($(whichInput).attr('name')) {
		case 'scorebug_graphic_btn':
			processBadmintonProcedures('SCOREBUG_GRAPHICS-OPTIONS');
			break;
		case 'scorebugstat_graphic_btn':
			processBadmintonProcedures('SCOREBUGSTAT_GRAPHICS-OPTIONS');
			break;
		case 'singlel3matchid_graphic_btn':
			addItemsToList('SINGLE-L3-MATCHID-OPTIONS',null);
			break;
		case 'singleffmatchid_graphic_btn':
			addItemsToList('SINGLE-FF-MATCHID-OPTIONS',null);
			break;
		case 'doublel3matchid_graphic_btn':
			addItemsToList('DOUBLE-L3-MATCHID-OPTIONS',null);
			break;
		case 'doubleffmatchid_graphic_btn':
			addItemsToList('DOUBLE-FF-MATCHID-OPTIONS',null);
			break;
		case 'l3tieid_graphic_btn':
			addItemsToList('L3-TIEID-OPTIONS',null);
			break;
		case 'fftieid_graphic_btn':
			addItemsToList('FF-TIEID-OPTIONS',null);
			break;
		case 'sides_graphic_btn':
			addItemsToList('SIDES-OPTIONS',null);
			break;
		case 'super_graphic_btn':
			processBadmintonProcedures('SUPER_GRAPHICS-OPTIONS');
			
			break;
		}
		break;
	case 'populate_scorebug_btn': case 'populate_scorebugstat_btn': case 'populate_singlel3matchid_btn': case 'populate_singleffmatchid_btn': case 'populate_doublel3matchid_btn':
	case 'populate_doubleffmatchid_btn': case 'populate_l3tieid_btn': case 'populate_fftieid_btn': case 'populate_sides_btn': case 'populate_super_btn':
		processWaitingButtonSpinner('START_WAIT_TIMER');
		switch ($(whichInput).attr('name')) {
		case 'populate_scorebug_btn':
			processBadmintonProcedures('POPULATE-SCOREBUG');
			break;
		case 'populate_scorebugstat_btn':
			processBadmintonProcedures('POPULATE-SCOREBUGSTATS');
			break;
		case 'populate_singlel3matchid_btn':
			processBadmintonProcedures('POPULATE-SINGLE-L3-MATCHID');
			break;
		case 'populate_singleffmatchid_btn':
			processBadmintonProcedures('POPULATE-SINGLE-FF-MATCHID');
			break;
		case 'populate_doublel3matchid_btn':
			processBadmintonProcedures('POPULATE-DOUBLE-L3_MATCHID');
			break;
		case 'populate_doubleffmatchid_btn':
			processBadmintonProcedures('POPULATE-DOUBLE-FF-MATCHID');
			break;
		case 'populate_l3tieid_btn':
			processBadmintonProcedures('POPULATE-L3-TIEID');
			break;
		case 'populate_fftieid_btn':
			processBadmintonProcedures('POPULATE-FF-TIEID');
			break;
		case 'populate_sides_btn':
			if($('#selectSide1 option:selected').val() == "Select_Top_Side" && $('#selectSide2 option:selected').val() == "Select_Bottom_Side"){
				alert("Select Side First!!");
				addItemsToList('SIDES-OPTIONS',null);
			}
			else if($('#selectSide1 option:selected').val() == "Select_Top_Side" || $('#selectSide2 option:selected').val() == "Select_Bottom_Side"){
				alert("Select Side First!!");
				addItemsToList('SIDES-OPTIONS',null);
			}
			else if($('#selectSide1 option:selected').val() == $('#selectSide2 option:selected').val()){
				alert("Both Side Same Please Select Different Side!!");
				addItemsToList('SIDES-OPTIONS',null);
			}
			else{
				processBadmintonProcedures('POPULATE-SIDES');
			}
			break;
		case 'populate_super_btn':
			processBadmintonProcedures('POPULATE-SUPER');
			break;
		}
		
		break;
		
	case 'cancel_graphics_btn':
		$('#select_graphic_options_div').empty();
		document.getElementById('select_graphic_options_div').style.display = 'none';
		$("#captions_div").show();
		break;
	case 'select_broadcaster':
		switch ($('#select_broadcaster :selected').val()) {
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
		valueToProcess = $('#match_file_timestamp').val();
		break;
	case 'POPULATE-SCOREBUG': 
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = $('#scorebugScene').val();
			break;	
		}
		break;
	case 'POPULATE-SCOREBUGSTATS':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			
			valueToProcess = $('#selectscorebugStat option:selected').val() ;
			break;
		}
		break;
	case 'POPULATE-SINGLE-L3-MATCHID':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = $('#singlel3matchidScene').val();
			break;
		}
		break;
	case 'POPULATE-SINGLE-FF-MATCHID':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = $('#singleffmatchidScene').val();
			break;
		}
		break;
	case 'POPULATE-DOUBLE-L3_MATCHID':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = $('#doublel3matchidScene').val();
			break;
		}
		break;
	case 'POPULATE-DOUBLE-FF-MATCHID':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = $('#doubleffmatchidScene').val();
			break;
		}
		break;
	case 'POPULATE-L3-TIEID':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = $('#l3tieidScene').val();
			break;
		}
		break;
	case 'POPULATE-FF-TIEID':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = $('#fftieidScene').val();
			break;
		}
		break;
	case 'POPULATE-SIDES':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = $('#sidesScene').val() + ',' + $('#selectSide1 option:selected').val() + ',' + $('#selectSide2 option:selected').val();
			break;
		}
		break;
	case 'POPULATE-SUPER':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = $('#superScene').val() + ',' + $('#selectTeam option:selected').val();
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
			case 'READ-MATCH-AND-POPULATE':
				if(data){
					if($('#match_file_timestamp').val() != data.match_file_timestamp) {
						document.getElementById('match_file_timestamp').value = data.match_file_timestamp;
					}
				}
				break;
			case 'SCOREBUG_GRAPHICS-OPTIONS':
				addItemsToList('SCOREBUG-OPTIONS',data);
				//addItemsToList('SCOREBUG-OPTIONS',data);
				match_data = data;
				break;
			case 'SCOREBUGSTAT_GRAPHICS-OPTIONS':
				addItemsToList('SCOREBUGSTAT-OPTIONS',data);
				//addItemsToList('SCOREBUG-OPTIONS',data);
				match_data = data;
				break;
			case 'SUPER_GRAPHICS-OPTIONS':
				addItemsToList('SUPER-OPTIONS',data);
				//addItemsToList('SCOREBUG-OPTIONS',data);
				match_data = data;
				break;
			
			case 'POPULATE-SCOREBUG': case 'POPULATE-SINGLE-L3-MATCHID': case 'POPULATE-SINGLE-FF-MATCHID': case 'POPULATE-DOUBLE-L3_MATCHID': case 'POPULATE-DOUBLE-FF-MATCHID': case 'POPULATE-L3-TIEID':
			case 'POPULATE-FF-TIEID': case 'POPULATE-SIDES': case 'POPULATE-SUPER':
				if (data.status.toUpperCase() == 'SUCCESSFUL') {
					if(confirm('Animate In?') == true){
						     
						$('#select_graphic_options_div').empty();
						document.getElementById('select_graphic_options_div').style.display = 'none';
						$("#captions_div").show();
						
			        	switch(whatToProcess) {
						case 'POPULATE-SCOREBUG': 
							processBadmintonProcedures('ANIMATE-IN-SCOREBUG');
							break;
						case 'POPULATE-SINGLE-L3-MATCHID': 
							processBadmintonProcedures('ANIMATE-IN-SINGLE-L3_MATCHID');
							break;
						case 'POPULATE-SINGLE-FF-MATCHID': 
							processBadmintonProcedures('ANIMATE-IN-SINGLE-FF_MATCHID');
							break;
						case 'POPULATE-DOUBLE-L3_MATCHID': 
							processBadmintonProcedures('ANIMATE-IN-DOUBLE-L3_MATCHID');
							break;
						case 'POPULATE-DOUBLE-FF-MATCHID': 
							processBadmintonProcedures('ANIMATE-IN-DOUBLE-FF_MATCHID');
							break;
						case 'POPULATE-L3-TIEID': 
							processBadmintonProcedures('ANIMATE-IN-L3_TIEID');
							break;
						case 'POPULATE-FF-TIEID': 
							processBadmintonProcedures('ANIMATE-IN-FF_TIEID');
							break;
						case 'POPULATE-SIDES': 
							processBadmintonProcedures('ANIMATE-IN-SIDES');
							break;
						case 'POPULATE-SUPER': 
							processBadmintonProcedures('ANIMATE-IN-SUPER');
							break;
						}
					}
				} else {
					alert(data.status);
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
	case 'SCOREBUG-OPTIONS': case 'SCOREBUGSTAT-OPTIONS': case 'SIDES-OPTIONS': case 'SUPER-OPTIONS':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':

			$('#select_graphic_options_div').empty();
	
			header_text = document.createElement('h6');
			header_text.innerHTML = 'Select Graphic Options';
			document.getElementById('select_graphic_options_div').appendChild(header_text);
			
			table = document.createElement('table');
			table.setAttribute('class', 'table table-bordered');
					
			tbody = document.createElement('tbody');
	
			table.appendChild(tbody);
			document.getElementById('select_graphic_options_div').appendChild(table);
			
			row = tbody.insertRow(tbody.rows.length);
		    
			switch (whatToProcess) {
			case 'SCOREBUG-OPTIONS':
				select = document.createElement('input');
				select.type = "text";
				select.id = 'scorebugScene';
				select.value = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/Scorebug.sum';
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				 
				break;
				
			case 'SCOREBUGSTAT-OPTIONS':
			
				select = document.createElement('select');
				select.id = 'selectscorebugStat';
				select.name = select.id;
				
				option = document.createElement('option');
				option.value = 'Forehand_Winner';
				option.text = 'Forehand_Winner';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'Forehand_Error';
				option.text = 'Forehand_Error';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'Backhand_Winner';
				option.text = 'Backhand_Winner';
				select.appendChild(option);

				option = document.createElement('option');
				option.value = 'Backhand_Error';
				option.text = 'Backhand_Error';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'Team_Name';
				option.text = 'Team_Name';
				select.appendChild(option);
				
				//select.setAttribute('onchange',"processUserSelection(this)");
				row.insertCell(cellCount).appendChild(select);
				
				cellCount = cellCount + 1;
				break;
			
			case 'SIDES-OPTIONS':
				select = document.createElement('input');
				select.type = "text";
				select.id = 'sidesScene';
				select.value = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/LT_Sides.sum';
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				
				select = document.createElement('select');
				select.id = 'selectSide1';
				select.name = select.id;
				
				option = document.createElement('option');
				option.value = 'Select_Top_Side';
				option.text = 'Select_Top_Side';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'home_player';
				option.text = 'Home Player';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'away_player';
				option.text = 'Away Player';
				select.appendChild(option);
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				
				select = document.createElement('select');
				select.id = 'selectSide2';
				select.name = select.id;
				
				option = document.createElement('option');
				option.value = 'Select_Bottom_Side';
				option.text = 'Select_Bottom_Side';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'away_player';
				option.text = 'Away Player';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'home_player';
				option.text = 'Home Player';
				select.appendChild(option);
				//select.setAttribute('onchange',"processUserSelection(this)");
				row.insertCell(cellCount).appendChild(select);
				
				cellCount = cellCount + 1;
				break;
				
			case 'SUPER-OPTIONS':
				select = document.createElement('input');
				select.type = "text";
				select.id = 'superScene';
				select.value = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/LT_Sides.sum';
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				
				select = document.createElement('select');
				select.id = 'selectTeam';
				select.name = select.id;
				
				option = document.createElement('option');
				option.value = dataToProcess.match.homePlayers.team.teamId;
				option.text = dataToProcess.match.homePlayers.team.shortname;
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = dataToProcess.match.awayPlayers.team.teamId;
				option.text = dataToProcess.match.awayPlayers.team.shortname;
				select.appendChild(option);
			
				//select.setAttribute('onchange',"processUserSelection(this)");
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				
				
				break;
			}
			option = document.createElement('input');
		    option.type = 'button';
		    
			switch (whatToProcess) {
			
			case'SCOREBUG-OPTIONS':
			    option.name = 'populate_scorebug_btn';
			    option.value = 'Populate Scorebug';
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
			
			case'SCOREBUGSTAT-OPTIONS':
			    option.name = 'populate_scorebugstat_btn';
			    option.value = 'Populate ScorebugStat';
			    
			    option.id = option.name;
			    
			    option.setAttribute('onclick',"processUserSelection(this)");
			    
			    div = document.createElement('div');
			    div.append(option);
				
				option = document.createElement('input');
				option.type = 'button';
				option.name = 'animateout_scorebugstat_btn';
				option.id = option.name;
				option.value = 'Animate out ';
				option.setAttribute('onclick','processUserSelection(this)');
		
			    div.append(option);
			    
			    row.insertCell(cellCount).appendChild(div);
			    cellCount = cellCount + 1;
				
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
				
			case'SIDES-OPTIONS':
			    option.name = 'populate_sides_btn';
			    option.value = 'Populate Sides';
			    
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
				
			case'SUPER-OPTIONS':
			    option.name = 'populate_super_btn';
			    option.value = 'Populate Super';
			    
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
		break;
	}
	switch (whatToProcess) {
	case 'SINGLE-L3-MATCHID-OPTIONS': case 'SINGLE-FF-MATCHID-OPTIONS': case 'DOUBLE-L3-MATCHID-OPTIONS': case 'DOUBLE-FF-MATCHID-OPTIONS': case 'L3-TIEID-OPTIONS': case 'FF-TIEID-OPTIONS':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':

			$('#select_graphic_options_div').empty();
	
			header_text = document.createElement('h6');
			header_text.innerHTML = 'Select Graphic Options';
			document.getElementById('select_graphic_options_div').appendChild(header_text);
			
			table = document.createElement('table');
			table.setAttribute('class', 'table table-bordered');
					
			tbody = document.createElement('tbody');
	
			table.appendChild(tbody);
			document.getElementById('select_graphic_options_div').appendChild(table);
			
			row = tbody.insertRow(tbody.rows.length);
		    
			switch (whatToProcess) {
			case'SINGLE-L3-MATCHID-OPTIONS':
				select = document.createElement('input');
				select.type = "text";
				select.id = 'singlel3matchidScene';
				select.value = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/LT_MatchID_Score_Single.sum';
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				
				break;
			case'SINGLE-FF-MATCHID-OPTIONS':
				select = document.createElement('input');
				select.type = "text";
				select.id = 'singleffmatchidScene';
				select.value = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/MatchId_Singles.sum';
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				
				break;
			case'DOUBLE-L3-MATCHID-OPTIONS':
				select = document.createElement('input');
				select.type = "text";
				select.id = 'doublel3matchidScene';
				select.value = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/LT_MatchID_Score_Double.sum';
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				
				break;
			case'DOUBLE-FF-MATCHID-OPTIONS':
				select = document.createElement('input');
				select.type = "text";
				select.id = 'doubleffmatchidScene';
				select.value = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/MatchId_Double.sum';
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				
				break;
			case'L3-TIEID-OPTIONS':
				select = document.createElement('input');
				select.type = "text";
				select.id = 'l3tieidScene';
				select.value = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/LT_TieID.sum';
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				
				break;
			case'FF-TIEID-OPTIONS':
				select = document.createElement('input');
				select.type = "text";
				select.id = 'fftieidScene';
				select.value = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/TieId.sum';
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				
				break;
			}
			option = document.createElement('input');
		    option.type = 'button';
		    
			switch (whatToProcess) {
			
			case'SINGLE-L3-MATCHID-OPTIONS':
				
			    option.name = 'populate_singlel3matchid_btn';
			    option.value = 'Populate MatchId L3';
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
			case'SINGLE-FF-MATCHID-OPTIONS':
				
			    option.name = 'populate_singleffmatchid_btn';
			    option.value = 'Populate FF MatchId';
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
				
			case'DOUBLE-L3-MATCHID-OPTIONS':
				
			    option.name = 'populate_doublel3matchid_btn';
			    option.value = 'Populate Double L3 MatchId';
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
				
			case'DOUBLE-FF-MATCHID-OPTIONS':
				
			    option.name = 'populate_doubleffmatchid_btn';
			    option.value = 'Populate Double FF MatchId';
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
				
			case'L3-TIEID-OPTIONS':
				
			    option.name = 'populate_l3tieid_btn';
			    option.value = 'Populate L3 TieId';
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
				
			case'FF-TIEID-OPTIONS':
				
			    option.name = 'populate_fftieid_btn';
			    option.value = 'Populate FF TieId';
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