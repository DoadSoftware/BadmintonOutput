var match_data,is_scorebug_on_screen;
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

function processUserSelectionData(whatToProcess,dataToProcess){
	switch (whatToProcess){
		case 'LOGGER_FORM_KEYPRESS':
			switch (dataToProcess){
				case ' ':
					processBadmintonProcedures('CLEAR-ALL');
					break;
				case '-':
					if(confirm('It will Also Delete Your Preview from Directory...\r\n\r\n Are You Sure To Animate Out?') == true){
						processBadmintonProcedures('ANIMATE-OUT');
					}
					break;
				
				//ScoreBug	
				case 'F1':
					processBadmintonProcedures('POPULATE-SCOREBUG');
					break;
				case 'i':
					$("#main_captions_div").hide();
					processBadmintonProcedures('SCOREBUGSTAT_GRAPHICS-OPTIONS');
					break;
				case 'o':
					processBadmintonProcedures('ANIMATE-OUT-STAT');
					break;
				
				
				case 'F2': case 'F3': case 'F4': case 'F5': case 'F6': case 'F7': case 'F8': case 'F9': case 'F10': case 'F11': case 'F12': case 'p': case 'r': case 's': case 't': case 'd': 
				case 'f': case 'g': case 'z': case 'x': case 67: case 'q': case 'w': case 'e': case 'a': case 'h': case 'j':case 'c':
								
					if(is_scorebug_on_screen == true){
						alert('Score Bug is On Air');
						$('#select_graphic_options_div').empty();
						document.getElementById('select_graphic_options_div').style.display = 'none';
						$("#main_captions_div").show();
					}else{
						switch (dataToProcess){
							case 'F2':
								$("#main_captions_div").hide();
								processBadmintonProcedures('NAMESUPER_GRAPHICS-OPTIONS');
								break;
							case 'F3':
								$("#main_captions_div").hide();
								processBadmintonProcedures('NAMESUPER_PLAYER_GRAPHICS-OPTIONS');
								break;
							case 'F4':
								$("#main_captions_div").hide();
								processBadmintonProcedures('BUGSUPER_GRAPHICS-OPTIONS');
								break;
							case 'F5':
								processBadmintonProcedures('POPULATE-SINGLE-L3-MATCHID');
								break;
							case 'F6':
								processBadmintonProcedures('POPULATE-SINGLE-FF-MATCHID');
								break;
							case 'F7':
								processBadmintonProcedures('POPULATE-DOUBLE-L3_MATCHID');
								break;
							case 'F8':
								processBadmintonProcedures('POPULATE-DOUBLE-FF-MATCHID');
								break;
							case 'F9':
								processBadmintonProcedures('POPULATE-L3-TIEID');
								break;
							case 'F10':
								processBadmintonProcedures('POPULATE-FF-TIEID');
								break;
							case 'F11':
								$("#main_captions_div").hide();
								addItemsToList('SIDES-OPTIONS',null);
								break;
							case 'F12':
								$("#main_captions_div").hide();
								processBadmintonProcedures('LT-TIEID-DOUBLE_GRAPHICS-OPTIONS');
								break;
							case 'p':
								$("#main_captions_div").hide();
								processBadmintonProcedures('POINTS_TABLE_GRAPHICS-OPTIONS');
								break;
							case 'r':
								processBadmintonProcedures('POPULATE-RULES');
								break;
							case 's':
								$("#main_captions_div").hide();
								processBadmintonProcedures('SCHEDULE_GRAPHICS-OPTIONS');
								break;
							case 't':
								processBadmintonProcedures('POPULATE-TEAMS_LOGO');
								break;
							case 'd':
								$("#main_captions_div").hide();
								processBadmintonProcedures('SQUADS_GRAPHICS-OPTIONS');
								break;
							case 'f':
								$("#main_captions_div").hide();
								processBadmintonProcedures('PLAYER_PROFILE_GRAPHICS-OPTIONS');
								break;
							case 'g':
								$("#main_captions_div").hide();
								processBadmintonProcedures('ORDER_OF_PLAY_GRAPHICS-OPTIONS');
								break;
							case 'z':
								processBadmintonProcedures('POPULATE-SUPER_MATCH');
								break;
							case 'x':
								processBadmintonProcedures('SUPER_MATCH1_GRAPHICS-OPTIONS');
								break;
							case 'c':
								processBadmintonProcedures('POPULATE-SUPER_MATCH2');
								break;
							case 'q':
								$("#main_captions_div").hide();
								processBadmintonProcedures('FF-SINGLE_GRAPHICS-OPTIONS');
								break;
							case 'w':
								$("#main_captions_div").hide();
								processBadmintonProcedures('LT-SINGLE_GRAPHICS-OPTIONS');
								break;
							case 'e':
								$("#main_captions_div").hide();
								processBadmintonProcedures('FF-DOUBLE_GRAPHICS-OPTIONS');
								break;
							case 'a':
								$("#main_captions_div").hide();
								processBadmintonProcedures('LT-DOUBLE_GRAPHICS-OPTIONS');
								break;
							case 'h':
								$("#main_captions_div").hide();
								processBadmintonProcedures('FF-TIE_GRAPHICS-OPTIONS');
								break;
							case 'j':
								$("#main_captions_div").hide();
								processBadmintonProcedures('L3-TIE_GRAPHICS-OPTIONS');
								break;
						}
						
					}
					break;
			}
			break;
	}
}

function initialiseForm(whatToProcess,dataToProcess)
{
	switch (whatToProcess) {
	case 'initialise':
		processUserSelection($('#select_broadcaster'));
		break;
	case 'UPDATE-MATCH-ON-OUTPUT-FORM': 
			document.getElementById('home_team').innerHTML = 'Home :' + dataToProcess.match.homeTeam.teamName1 +'  '+ parseInt(dataToProcess.homeTeamSetsWon);
			document.getElementById('away_team').innerHTML = parseInt(dataToProcess.awayTeamSetsWon) +" "+ dataToProcess.match.awayTeam.teamName1 + ': Away';
			
			if(dataToProcess.match.categoryId == 1){
				document.getElementById('category').innerHTML = 'Category:' + " Women's Singles";
			}
			else if(dataToProcess.match.categoryId == 2){
				document.getElementById('category').innerHTML = 'Category:' + " MEN'S DOUBLES";
			}
			else if(dataToProcess.match.categoryId == 3){
				document.getElementById('category').innerHTML = 'Category:' + " MEN'S SINGLES";
			}
			else if(dataToProcess.match.categoryId == 4){
				document.getElementById('category').innerHTML = 'Category:' + " MIXED DOUBLES";
			}
			else{
				document.getElementById('category').innerHTML = 'Category:' + " SUPER MATCH";
			}
			
			
			dataToProcess.sets.forEach(function(st,index,arr1){
				if(st.setNumber == 1){
					document.getElementById('set1').innerHTML = 'Set 1: ' + parseInt(st.homeTeamTotalScore) + '-'+ parseInt(st.awayTeamTotalScore) + ' (' + st.status + ')';
				}
				else if(st.setNumber == 2){
					document.getElementById('set2').innerHTML = 'Set 2: ' + parseInt(st.homeTeamTotalScore) + '-'+ parseInt(st.awayTeamTotalScore) + ' (' + st.status + ')';
				}
				else{
					document.getElementById('set3').innerHTML = 'Set 3: ' + parseInt(st.homeTeamTotalScore) + '-'+ parseInt(st.awayTeamTotalScore) + ' (' + st.status + ')';
				}
			});
			
			//document.getElementById('previous_xml_data') = dataToProcess.awayTeamSetsWon
		break;
	}
}
function processUserSelection(whichInput)
{	
	switch ($(whichInput).attr('name')) {
	/*case 'selectscorebugStat':
		switch ($('#selectscorebugStat :selected').val().toUpperCase()) {
		case 'SET_POINT':  
			processBadmintonProcedures('POINT_GRAPHICS-OPTIONS');
			break;
		case 'MATCH_POINT': 
			processBadmintonProcedures('MATCH_POINT_GRAPHICS-OPTIONS');
			break;
		}
		break;*/	
	
	case 'animateout_graphic_btn':
		is_scorebug_on_screen = false;
		if(confirm('It will Also Delete Your Preview from Directory...\r\n \r\nAre You Sure To Animate Out? ') == true){
			processBadmintonProcedures('ANIMATE-OUT');	
		}
		break;
	case 'animateout_category_graphic_btn':
		processBadmintonProcedures('ANIMATE-OUT-CATEGORY');
		break;
	case 'animateout_scorebugstat_btn':
		processBadmintonProcedures('ANIMATE-OUT-STAT');
		break;
	case 'manualgraphics_graphic_btn':
		processBadmintonProcedures('LOAD_MANUAL_XML_SCENE');
		break;
	case 'clearall_graphic_btn':
		if(confirm('Are You Sure To Clear All Scenes? ') == true){
			$('#select_graphic_options_div').empty();
			document.getElementById('select_graphic_options_div').style.display = 'none';
			//$("#captions_div").show();
			$("#main_captions_div").show();
			
			processBadmintonProcedures('CLEAR-ALL');
		}
		break;
	case 'scorebug_graphic_btn': case 'scorebugstat_graphic_btn': 
	case 'sides_graphic_btn': case 'super_graphic_btn': case 'bug_super_graphic_btn': case 'playerprofile_graphic_btn': 
	case 'orderofplay_graphic_btn': case 'ff_tie_graphic_btn': case 'l3_tie_graphic_btn': case 'lt_tieid_double_graphic_btn':
	case 'ff_single_match_graphic_btn': case 'ff_double_match_graphic_btn': case 'l3_single_match_graphic_btn': case 'points_table_graphic_btn':
	case 'l3_double_match_graphic_btn': case 'squads_graphic_btn': case 'namesuper_player_graphic_btn': case 'schedule_graphic_btn': case 'cancel_btn':
	
		$("#main_captions_div").hide();
		//$("#caption_divs").hide();
		//$("#caption_div").hide();
		//$("#captions_div").hide();
		switch ($(whichInput).attr('name')) {
		case 'scorebug_graphic_btn':
			is_scorebug_on_screen = true;
			processBadmintonProcedures('SCOREBUG_GRAPHICS-OPTIONS');
			break;
		case 'scorebugstat_graphic_btn':
			processBadmintonProcedures('SCOREBUGSTAT_GRAPHICS-OPTIONS');
			break;
		
		case 'lt_tieid_double_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
				$('#select_graphic_options_div').empty();
				document.getElementById('select_graphic_options_div').style.display = 'none';
				$("#main_captions_div").show();
			}else{
				//addItemsToList('LT-TIEID_DOUBLE-OPTIONS',null);
				processBadmintonProcedures('LT-TIEID-DOUBLE_GRAPHICS-OPTIONS');
				//'LT-TIEID_DOUBLE-OPTIONS',null
			}
			break;
		
		case 'sides_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
				$('#select_graphic_options_div').empty();
				document.getElementById('select_graphic_options_div').style.display = 'none';
				$("#main_captions_div").show();
			}else{
				addItemsToList('SIDES-OPTIONS',null);
			}
			break;
		case 'super_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
				$('#select_graphic_options_div').empty();
				document.getElementById('select_graphic_options_div').style.display = 'none';
				$("#main_captions_div").show();
			}else{
				processBadmintonProcedures('NAMESUPER_GRAPHICS-OPTIONS');
			}
			break;
		case 'bug_super_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
				$('#select_graphic_options_div').empty();
				document.getElementById('select_graphic_options_div').style.display = 'none';
				$("#main_captions_div").show();
			}else{
				processBadmintonProcedures('BUGSUPER_GRAPHICS-OPTIONS');
			}
			break;
		case 'namesuper_player_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
				$('#select_graphic_options_div').empty();
				document.getElementById('select_graphic_options_div').style.display = 'none';
				$("#main_captions_div").show();
			}else{
				processBadmintonProcedures('NAMESUPER_PLAYER_GRAPHICS-OPTIONS');
			}
			break;
		case 'playerprofile_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
				$('#select_graphic_options_div').empty();
				document.getElementById('select_graphic_options_div').style.display = 'none';
				$("#main_captions_div").show();
			}else{
				processBadmintonProcedures('PLAYER_PROFILE_GRAPHICS-OPTIONS');
			}
			break;
		case 'orderofplay_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
				$('#select_graphic_options_div').empty();
				document.getElementById('select_graphic_options_div').style.display = 'none';
				$("#main_captions_div").show();
			}else{
				processBadmintonProcedures('ORDER_OF_PLAY_GRAPHICS-OPTIONS');
			}
			break;
		case 'ff_tie_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
				$('#select_graphic_options_div').empty();
				document.getElementById('select_graphic_options_div').style.display = 'none';
				$("#main_captions_div").show();
			}else{
				processBadmintonProcedures('FF-TIE_GRAPHICS-OPTIONS');
			}
			break;
		case 'l3_tie_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
				$('#select_graphic_options_div').empty();
				document.getElementById('select_graphic_options_div').style.display = 'none';
				$("#main_captions_div").show();
			}else{
				processBadmintonProcedures('L3-TIE_GRAPHICS-OPTIONS');
			}
			break;
		
		case 'ff_single_match_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
				$('#select_graphic_options_div').empty();
				document.getElementById('select_graphic_options_div').style.display = 'none';
				$("#main_captions_div").show();
			}else{
				processBadmintonProcedures('FF-SINGLE_GRAPHICS-OPTIONS');
			}
			break;
		case 'ff_double_match_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
				$('#select_graphic_options_div').empty();
				document.getElementById('select_graphic_options_div').style.display = 'none';
				$("#main_captions_div").show();
			}else{
				processBadmintonProcedures('FF-DOUBLE_GRAPHICS-OPTIONS');
			}
			break;
		case 'l3_single_match_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
				$('#select_graphic_options_div').empty();
				document.getElementById('select_graphic_options_div').style.display = 'none';
				$("#main_captions_div").show();
			}else{
				processBadmintonProcedures('LT-SINGLE_GRAPHICS-OPTIONS');
			}
			break;
		case 'l3_double_match_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
				$('#select_graphic_options_div').empty();
				document.getElementById('select_graphic_options_div').style.display = 'none';
				$("#main_captions_div").show();
			}else{
				processBadmintonProcedures('LT-DOUBLE_GRAPHICS-OPTIONS');
			}
			break;
		case 'squads_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
				$('#select_graphic_options_div').empty();
				document.getElementById('select_graphic_options_div').style.display = 'none';
				$("#main_captions_div").show();
			}else{
				processBadmintonProcedures('SQUADS_GRAPHICS-OPTIONS');
			}
			break;
		case 'schedule_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
				$('#select_graphic_options_div').empty();
				document.getElementById('select_graphic_options_div').style.display = 'none';
				$("#main_captions_div").show();
			}else{
				processBadmintonProcedures('SCHEDULE_GRAPHICS-OPTIONS');
			}
			break;
		case 'points_table_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
				$('#select_graphic_options_div').empty();
				document.getElementById('select_graphic_options_div').style.display = 'none';
				$("#main_captions_div").show();
			}else{
				processBadmintonProcedures('POINTS_TABLE_GRAPHICS-OPTIONS');
				//processBadmintonProcedures('POPULATE-POINTS_TABLE');
			}
			break;
		case 'cancel_btn':
			processBadmintonProcedures('SCOREBUGSTAT_GRAPHICS-OPTIONS');
			break;
		}
		break;
	case 'populate_scorebug_btn': case 'populate_scorebugstat_btn': case 'singlel3matchid_graphic_btn': case 'singleffmatchid_graphic_btn': case 'doublel3matchid_graphic_btn': case 'cancel_btn':
	case 'populate_sides_btn': case 'populate_super_btn': case 'populate_player_profile_btn': case 'doubleffmatchid_graphic_btn': case 'l3tieid_graphic_btn': case 'fftieid_graphic_btn':
	case 'populate_order_of_play_btn': case 'populate_ff_tie_promo_btn': case 'populate_l3_tie_promo_btn': 
	case 'populate_ff_single_match_promo_btn': case 'populate_ff_double_match_promo_btn': case 'populate_l3_single_match_promo_btn':
	case 'populate_bug_super_btn': case 'populate_lt_tieId_double_btn': case 'populate_point_table_btn':
	case 'populate_l3_double_match_promo_btn': case 'populate_squads_btn': case 'populate_namesuper_player_btn': case 'teamslogo_graphic_btn': 
	case 'supermatch_graphic_btn': case 'supermatch1_graphic_btn': case 'supermatch2_graphic_btn': case 'rules_graphic_btn': case 'populate_schedule_btn':
		processWaitingButtonSpinner('START_WAIT_TIMER');
		switch ($(whichInput).attr('name')) {
		case 'populate_scorebug_btn':
			processBadmintonProcedures('POPULATE-SCOREBUG');
			break;
		case 'populate_scorebugstat_btn':
			processBadmintonProcedures('POPULATE-SCOREBUGSTATS');
			break;
		case 'populate_lt_tieId_double_btn':
			processBadmintonProcedures('POPULATE-TIEID-DOUBLE');
			break;
		case 'singlel3matchid_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
			}else{
				processBadmintonProcedures('POPULATE-SINGLE-L3-MATCHID');
			}
			//addItemsToList('SINGLE-L3-MATCHID-OPTIONS',null);
			break;
		case 'singleffmatchid_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
			}else{
				processBadmintonProcedures('POPULATE-SINGLE-FF-MATCHID');
			}
			//addItemsToList('SINGLE-FF-MATCHID-OPTIONS',null);
			break;
		case 'doublel3matchid_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
			}else{
				processBadmintonProcedures('POPULATE-DOUBLE-L3_MATCHID');
			}
			//addItemsToList('DOUBLE-L3-MATCHID-OPTIONS',null);
			break;
		case 'doubleffmatchid_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
			}else{
				processBadmintonProcedures('POPULATE-DOUBLE-FF-MATCHID');
			}
			//addItemsToList('DOUBLE-FF-MATCHID-OPTIONS',null);
			break;
		case 'l3tieid_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
			}else{
				processBadmintonProcedures('POPULATE-L3-TIEID');
			}
			//addItemsToList('L3-TIEID-OPTIONS',null);
			break;
		case 'fftieid_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
			}else{
				processBadmintonProcedures('POPULATE-FF-TIEID');
			}
			//addItemsToList('FF-TIEID-OPTIONS',null);
			break;
		case 'teamslogo_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
			}else{
				processBadmintonProcedures('POPULATE-TEAMS_LOGO');
			}
			//addItemsToList('TEAMS_LOGO-OPTIONS',null);
			break;
		case 'supermatch_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
			}else{
				processBadmintonProcedures('POPULATE-SUPER_MATCH');
			}
			//addItemsToList('SUPER_MATCH-OPTIONS',null);
			break;
		case 'supermatch1_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
			}else{
				processBadmintonProcedures('SUPER_MATCH1_GRAPHICS-OPTIONS');
			}
			break;
		case 'supermatch2_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
			}else{
				processBadmintonProcedures('POPULATE-SUPER_MATCH2');
			}
			//addItemsToList('SUPER_MATCH2-OPTIONS',null);
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
		case 'populate_bug_super_btn':
			processBadmintonProcedures('POPULATE-BUG_SUPER');
			break;
		case 'populate_namesuper_player_btn':
			processBadmintonProcedures('POPULATE-NAMESUPER_PLAYER');
			break;
		case 'populate_player_profile_btn':
			processBadmintonProcedures('POPULATE-PLAYER_PROFILE');
			break;
		case 'populate_order_of_play_btn':
			processBadmintonProcedures('POPULATE-ORDER_OF_PLAY');
			break;
		case 'populate_ff_tie_promo_btn':
			processBadmintonProcedures('POPULATE-FF_TIE_PROMO');
			break;
		case 'populate_l3_tie_promo_btn':
			processBadmintonProcedures('POPULATE-L3_TIE_PROMO');
			break;
		case 'populate_ff_single_match_promo_btn':
			processBadmintonProcedures('POPULATE-SINGLE_MATCH_PROMO');
			break;
		case 'populate_ff_double_match_promo_btn':
			processBadmintonProcedures('POPULATE-DOUBLE_MATCH_PROMO');
			break;
		case 'populate_l3_single_match_promo_btn':
			processBadmintonProcedures('POPULATE-LT_SINGLE_MATCH_PROMO');
			break;
		case 'populate_l3_double_match_promo_btn':
			processBadmintonProcedures('POPULATE-LT_DOUBLE_MATCH_PROMO');
			break;
		case 'populate_squads_btn':
			processBadmintonProcedures('POPULATE-SQUADS');
			break;

		case 'rules_graphic_btn':
			if(is_scorebug_on_screen == true){
				alert('Score Bug is On Air');
			}else{
				processBadmintonProcedures('POPULATE-RULES');
			}
			break;
		case 'populate_schedule_btn':
			processBadmintonProcedures('POPULATE-SCHEDULE');
			break;
		case 'populate_point_table_btn':
			processBadmintonProcedures('POPULATE-POINTS_TABLE');
			break;

		}
		
		break;
		
	case 'cancel_graphics_btn':
		$('#select_graphic_options_div').empty();
		document.getElementById('select_graphic_options_div').style.display = 'none';
		//$("#captions_div").show();
		$("#main_captions_div").show();
		
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
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/Scorebug.sum';
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
	case 'POPULATE-TIEID-DOUBLE':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/TieId_Doubles.sum' + ',' + $('#selectTieID option:selected').val() ;
			break;
		}
		break;
	case 'POPULATE-SINGLE-L3-MATCHID':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/LT_MatchID_Score_Single.sum';
			break;
		}
		break;
	case 'POPULATE-SINGLE-FF-MATCHID':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/MatchId_Singles.sum';
			break;
		}
		break;
	case 'POPULATE-DOUBLE-L3_MATCHID':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/LT_MatchID_Score_Double.sum';
			break;
		}
		break;
	case 'POPULATE-DOUBLE-FF-MATCHID':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/MatchId_Double.sum';
			break;
		}
		break;
	case 'POPULATE-L3-TIEID':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/LT_TieID.sum';
			break;
		}
		break;
	case 'POPULATE-FF-TIEID':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/TieId.sum';
			break;
		}
		break;
	case 'POPULATE-SIDES':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/LT_Sides.sum' + ',' + $('#selectSide1 option:selected').val() + ','
				+ $('#selectSide2 option:selected').val();
			break;
		}
		break;
	case 'POPULATE-SUPER':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/LT_Super.sum' + ',' + $('#selectNameSuper option:selected').val() + ',' 
				+ $('#selectSponsor option:selected').val();
			break;
		}
		break;
	case 'POPULATE-BUG_SUPER':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/Bug_DoubleLine.sum' + ',' + $('#selectNameSuper option:selected').val();
			break;
		}
		break;
		
	case 'POPULATE-NAMESUPER_PLAYER':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/LT_Super.sum' + ',' + $('#selectNameSuperPlayer option:selected').val() + ',' 
				+ $('#selectNameSuperDetail option:selected').val() + ',' + $('#selectSponsor option:selected').val(); 
			break;
		}
		break;
	case 'POPULATE-PLAYER_PROFILE':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/Player_Profile.sum' + ',' + $('#selectPlayer option:selected').val();
			break;
		}
		break;
	case 'POPULATE-ORDER_OF_PLAY':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/Order_Of_Play_3.sum' + ',' + $('#selectTeam1 option:selected').val()  + ',' 
				+ $('#selectPlayer').val();
			break;
		}
		break;
	case 'POPULATE-FF_TIE_PROMO':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/TieId.sum' + ',' + $('#selectTeam option:selected').val();
			break;
		}
		break;
	case 'POPULATE-L3_TIE_PROMO':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/LT_TieID.sum' + ',' + $('#selectTeam option:selected').val();
			break;
		}
		break;
	case 'POPULATE-SINGLE_MATCH_PROMO':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/MatchId_Singles.sum' + ',' + $('#selectTeam option:selected').val();
			break;
		}
		break;
	case 'POPULATE-DOUBLE_MATCH_PROMO':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/MatchId_Double.sum' + ',' + $('#selectTeam option:selected').val();
			break;
		}
		break;
	case 'POPULATE-LT_SINGLE_MATCH_PROMO':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/LT_MatchID_Score_Single.sum' + ',' + $('#selectTeam option:selected').val();
			break;
		}
		break;
	case 'POPULATE-LT_DOUBLE_MATCH_PROMO':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/LT_MatchID_Score_Double.sum' + ',' + $('#selectTeam option:selected').val();
			break;
		}
		break;
	case 'POPULATE-SQUADS':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/Squad.sum' + ',' + $('#selectTeam option:selected').val();
			break;
		}
		break;
	case 'POPULATE-TEAMS_LOGO':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/Teams.sum';
			break;
		}
		break;
	case 'POPULATE-SUPER_MATCH':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/FF_SuperMatch.sum';
			break;
		}
		break;
	case 'POPULATE-SUPER_MATCH1':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/LT_MatchID_Score_Single.sum';
			break;
		}
		break;
	case 'POPULATE-SUPER_MATCH2':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/LT_MatchID_Score_Double.sum';
			break;
		}
		break;
	case 'POPULATE-POINTS_TABLE':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/Points_Table.sum' + ',' + $('#selectGroup option:selected').val();
			break;
		}
		break;
	case 'POPULATE-RULES':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/FF_Rules.sum';
			break;
		}
		break;
	case 'POPULATE-SCHEDULE':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess = 'D:/DOAD_In_House_Everest/Everest_Sports/Everest_GBPL/Scenes/DD_Schedule02.sum' + ',' + $('#selectGroup option:selected').val();
			break;
		}
		break;
	case 'LOAD_MANUAL_XML_SCENE':
		switch ($('#select_broadcaster').val()) {
		case 'DOAD_In_House_Everest':
			valueToProcess =$('#previous_xml_data option:selected').val();
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
						initialiseForm("UPDATE-MATCH-ON-OUTPUT-FORM",data);
					}
				}
				break;
			case 'SCOREBUGSTAT_GRAPHICS-OPTIONS':
				addItemsToList('SCOREBUGSTAT-OPTIONS',data);
				match_data = data;
				break;
			case 'LT-TIEID-DOUBLE_GRAPHICS-OPTIONS':
				addItemsToList('LT-TIEID-DOUBLE-OPTIONS',data);
				match_data = data;
				break;
			case 'NAMESUPER_GRAPHICS-OPTIONS':
				addItemsToList('SUPER-OPTIONS',data);
				//addItemsToList('SCOREBUG-OPTIONS',data);
				match_data = data;
				break;
			case 'BUGSUPER_GRAPHICS-OPTIONS':
				addItemsToList('BUGSUPER-OPTIONS',data);
				//addItemsToList('SCOREBUG-OPTIONS',data);
				match_data = data;
				break;
			case 'NAMESUPER_PLAYER_GRAPHICS-OPTIONS':
				addItemsToList('NAMESUPER_PLAYER-OPTIONS',data);
				match_data = data;
				break;
			case 'PLAYER_PROFILE_GRAPHICS-OPTIONS':
				addItemsToList('PLAYER_PROFILE-OPTIONS',data);
				match_data = data;
				break;
			case 'POINT_GRAPHICS-OPTIONS':
				addItemsToList('POINT-OPTIONS',data);
				match_data = data;
				break;
			case 'MATCH_POINT_GRAPHICS-OPTIONS':
				addItemsToList('MATCH_POINT-OPTIONS',data);
				match_data = data;
				break;
			case 'ORDER_OF_PLAY_GRAPHICS-OPTIONS':
				addItemsToList('ORDER_OF_PLAY-OPTIONS',data);
				match_data = data;
				break;
			case 'FF-TIE_GRAPHICS-OPTIONS':
				addItemsToList('FF_TIE-OPTIONS',data);
				match_data = data;
				break;
			case 'L3-TIE_GRAPHICS-OPTIONS':
				addItemsToList('L3_TIE-OPTIONS',data);
				match_data = data;
				break;
			case 'FF-SINGLE_GRAPHICS-OPTIONS':
				addItemsToList('FF_SINGLE-OPTIONS',data);
				match_data = data;
				break;
			case 'FF-DOUBLE_GRAPHICS-OPTIONS':
				addItemsToList('FF_DOUBLE-OPTIONS',data);
				match_data = data;
				break;
			case 'LT-SINGLE_GRAPHICS-OPTIONS':
				addItemsToList('LT_SINGLE-OPTIONS',data);
				match_data = data;
				break;
			case 'LT-DOUBLE_GRAPHICS-OPTIONS':
				addItemsToList('LT_DOUBLE-OPTIONS',data);
				match_data = data;
				break;
			case 'SQUADS_GRAPHICS-OPTIONS':
				addItemsToList('SQUADS-OPTIONS',data);
				match_data = data;
				break;
			case 'SCHEDULE_GRAPHICS-OPTIONS':
				addItemsToList('SCHEDULE-OPTIONS',data);
				match_data = data;
				break;
			case 'POINTS_TABLE_GRAPHICS-OPTIONS':
				addItemsToList('POINTS_TABLE-OPTIONS',data);
				match_data = data;
				break;
			case 'SUPER_MATCH1_GRAPHICS-OPTIONS':
				addItemsToList('SUPER_MATCH_DATA',data)
				match_data = data;
				break;
			case 'LOAD_MANUAL_XML_SCENE':
				switch(whatToProcess) {
				case 'LOAD_MANUAL_XML_SCENE':
					if(confirm('Animate In?') == true){
						processBadmintonProcedures('ANIMATE-IN-MANUAL_GRAPHIC');
						//addItemsToList('LOAD_PREVIOUS_SCENE-OPTIONS',data);
					}else{
						$('#select_graphic_options_div').empty();
								document.getElementById('select_graphic_options_div').style.display = 'none';
								//$("#captions_div").show();
								$("#main_captions_div").show();
					}
				break;
			}
			break;
			
			case 'POPULATE-SCOREBUG': case 'POPULATE-SINGLE-L3-MATCHID': case 'POPULATE-SINGLE-FF-MATCHID': case 'POPULATE-DOUBLE-L3_MATCHID': case 'POPULATE-DOUBLE-FF-MATCHID': case 'POPULATE-L3-TIEID':
			case 'POPULATE-FF-TIEID': case 'POPULATE-SIDES': case 'POPULATE-SUPER': case 'POPULATE-PLAYER_PROFILE': case 'POPULATE-ORDER_OF_PLAY': case 'POPULATE-TEAMS_LOGO': case 'POPULATE-SUPER_MATCH':
			case 'POPULATE-SUPER_MATCH1': case 'POPULATE-SUPER_MATCH2': case 'POPULATE-FF_TIE_PROMO': case 'POPULATE-L3_TIE_PROMO': case 'POPULATE-SINGLE_MATCH_PROMO': case 'POPULATE-DOUBLE_MATCH_PROMO':
			case 'POPULATE-LT_SINGLE_MATCH_PROMO': case 'POPULATE-LT_DOUBLE_MATCH_PROMO': case 'POPULATE-SQUADS': case 'POPULATE-NAMESUPER_PLAYER': case 'POPULATE-POINTS_TABLE': case 'POPULATE-RULES':
			case 'POPULATE-SCHEDULE': case 'POPULATE-BUG_SUPER': case 'POPULATE-TIEID-DOUBLE':
				if (data.status.toUpperCase() == 'SUCCESSFUL') {
					if(confirm('Animate In?') == true){
						     
						$('#select_graphic_options_div').empty();
						document.getElementById('select_graphic_options_div').style.display = 'none';
						//$("#captions_div").show();
						$("#main_captions_div").show();
						
			        	switch(whatToProcess) {
						case 'POPULATE-SCOREBUG': 
							processBadmintonProcedures('ANIMATE-IN-SCOREBUG');
							break;
						case 'POPULATE-TIEID-DOUBLE':
							processBadmintonProcedures('ANIMATE-IN-TIEID-DOUBLE');
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
						case 'POPULATE-BUG_SUPER':
							processBadmintonProcedures('ANIMATE-IN-BUG_SUPER');
							break;
						case 'POPULATE-NAMESUPER_PLAYER':
							processBadmintonProcedures('ANIMATE-IN-NAMESUPER_PLAYER');
							break;
						case 'POPULATE-PLAYER_PROFILE': 
							processBadmintonProcedures('ANIMATE-IN-PLAYER_PROFILE');
							break;
						case 'POPULATE-ORDER_OF_PLAY': 
							processBadmintonProcedures('ANIMATE-IN-ORDER_OF_PLAY');
							break;
						case 'POPULATE-FF_TIE_PROMO':
							processBadmintonProcedures('ANIMATE-IN-FF_TIE_PROMO');
							break;
						case 'POPULATE-L3_TIE_PROMO':
							processBadmintonProcedures('ANIMATE-IN-L3_TIE_PROMO');
							break;
						case 'POPULATE-TEAMS_LOGO': 
							processBadmintonProcedures('ANIMATE-IN-TEAMS_LOGO');
							break;
						case 'POPULATE-SUPER_MATCH': 
							processBadmintonProcedures('ANIMATE-IN-SUPER_MATCH');
							break;
						case 'POPULATE-SUPER_MATCH1': 
							processBadmintonProcedures('ANIMATE-IN-SUPER_MATCH1');
							break;
						case 'POPULATE-SUPER_MATCH2': 
							processBadmintonProcedures('ANIMATE-IN-SUPER_MATCH2');
							break;
						case 'POPULATE-SINGLE_MATCH_PROMO':
							processBadmintonProcedures('ANIMATE-IN-FF_SINGLE_MATCH_PROMO');
							break;
						case 'POPULATE-DOUBLE_MATCH_PROMO':
							processBadmintonProcedures('ANIMATE-IN-FF_DOUBLE_MATCH_PROMO');
							break;
						case 'POPULATE-LT_SINGLE_MATCH_PROMO':
							processBadmintonProcedures('ANIMATE-IN-LT_SINGLE_MATCH_PROMO');
							break;
						case 'POPULATE-LT_DOUBLE_MATCH_PROMO':
							processBadmintonProcedures('ANIMATE-IN-LT_DOUBLE_MATCH_PROMO');
							break;
						case 'POPULATE-SQUADS':
							processBadmintonProcedures('ANIMATE-IN-SQUADS');
							break;
						case 'POPULATE-POINTS_TABLE':
							processBadmintonProcedures('ANIMATE-IN-POINTS_TABLE');
							break;
						case 'POPULATE-RULES':
							processBadmintonProcedures('ANIMATE-IN-RULES');
							break;
						case 'POPULATE-SCHEDULE':
							processBadmintonProcedures('ANIMATE-IN-SCHEDULE');
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
	case'SUPER_MATCH_DATA':
		dataToProcess.sets.forEach(function(st,index,arr1){
			if(st.homeTeamTotalScore > 8 &&  st.awayTeamTotalScore == 1 || st.awayTeamTotalScore > 8 && st.homeTeamTotalScore == 1 || 
			st.homeTeamTotalScore > 8 &&  st.awayTeamTotalScore == 0 || st.awayTeamTotalScore > 8 && st.homeTeamTotalScore == 0){
				alert('Cannot Populate');
				$('#select_graphic_options_div').empty();
				document.getElementById('select_graphic_options_div').style.display = 'none';
				//$("#captions_div").show();
				$("#main_captions_div").show();
			
			}
			else{
				//alert('Populate');
				processBadmintonProcedures('POPULATE-SUPER_MATCH1');
				//addItemsToList('SUPER_MATCH1-OPTIONS',null);
			}
						
        });
		//alert(dataToProcess.sets.homeTeamTotalScore);
		
		break;
	case 'SCOREBUGSTAT-OPTIONS': case 'SIDES-OPTIONS': case 'SUPER-OPTIONS': case 'PLAYER_PROFILE-OPTIONS': case'POINT-OPTIONS': case'MATCH_POINT-OPTIONS':
	case 'NAMESUPER_PLAYER-OPTIONS': case 'MANUAL_GRAPHICS-OPTIONS': case 'BUGSUPER-OPTIONS': case 'LT-TIEID-DOUBLE-OPTIONS':
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
			case 'LT-TIEID-DOUBLE-OPTIONS':
				var today = new Date();
				var today_date = String(today.getDate()).padStart(2, '0') + '-' + String(today.getMonth() + 1).padStart(2, '0') + '-' + today.getFullYear();
				var tomorrow_date = String(today.getDate() + 1).padStart(2, '0') + '-' + String(today.getMonth() + 1).padStart(2, '0') + '-' + today.getFullYear();
				select = document.createElement('select');
				select.id = 'selectTieID';
				select.name = select.id;
				
				
				option = document.createElement('option');
				option.value = today_date;
				option.text = 'Today' ;
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = tomorrow_date;
				option.text = 'Tomorrow' ;
				select.appendChild(option);
				
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				break;
			
			case'POINT-OPTIONS':
				select = document.createElement('select');
				select.id = 'selectscorebugStat';
				select.name = select.id;
				
				
				option = document.createElement('option');
				option.value = 'home';
				option.text = 'Home Player' ;
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'away';
				option.text = 'Away Player' ;
				select.appendChild(option);
				
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				break;
			case'MATCH_POINT-OPTIONS':
				select = document.createElement('select');
				select.id = 'selectscorebugStat';
				select.name = select.id;
				
				
				option = document.createElement('option');
				option.value = 'match_home';
				option.text = 'Home Player' ;
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'match_away';
				option.text = 'Away Player' ;
				select.appendChild(option);
				
				
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
				option.value = 'Tie_Score';
				option.text = 'Tie Score';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'Team_Name';
				option.text = 'Team_Name';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'Category';
				option.text = 'Category';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'set_point';
				option.text = 'Set Point';
				select.appendChild(option);
				//select.setAttribute('onclick','processUserSelection(this);');
				
				option = document.createElement('option');
				option.value = 'match_point';
				option.text = 'Match Point';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'game_point';
				option.text = 'Game Point';
				select.appendChild(option);
				
				select.setAttribute('onclick','processUserSelection(this);');
				//select.setAttribute('onchange',"processUserSelection(this)");
				row.insertCell(cellCount).appendChild(select);
				
				cellCount = cellCount + 1;
				break;
			
			case 'SIDES-OPTIONS':
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
				
			case 'PLAYER_PROFILE-OPTIONS':
				select = document.createElement('select');
				select.id = 'selectPlayer';
				select.name = select.id;
				
				dataToProcess.forEach(function(pp,index,arr1){
						
					option = document.createElement('option');
                    option.value = pp.playerId;
                    option.text = pp.full_name ;
                    select.appendChild(option);
						
                });
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				
				//select.setAttribute('onchange',"processUserSelection(this)");
				break;
				
			case 'SUPER-OPTIONS':
				select = document.createElement('select');
				select.style = 'width:130px';
				select.id = 'selectNameSuper';
				select.name = select.id;
				
				dataToProcess.forEach(function(ns,index,arr1){
					option = document.createElement('option');
					option.value = ns.namesuperId;
					option.text = ns.firstname + ' ' + ns.surname ;
					select.appendChild(option);
				});
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				
				select = document.createElement('select');
				select.style = 'width:130px';
				select.id = 'selectSponsor';
				select.name = select.id;
				
				
				option = document.createElement('option');
				option.value = 0;
				option.text = 'Without Sponsor';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 1;
				option.text = 'With Sponsor';
				select.appendChild(option);
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				
				break;
			case 'BUGSUPER-OPTIONS':
				select = document.createElement('select');
				select.style = 'width:130px';
				select.id = 'selectNameSuper';
				select.name = select.id;
				
				dataToProcess.forEach(function(ns,index,arr1){
					option = document.createElement('option');
					option.value = ns.namesuperId;
					option.text = ns.firstname + ' ' + ns.surname ;
					select.appendChild(option);
				});
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				break;
				
			case 'NAMESUPER_PLAYER-OPTIONS':
				select = document.createElement('select');
				select.style = 'width:130px';
				select.id = 'selectNameSuperPlayer';
				select.name = select.id;
				
				dataToProcess.forEach(function(plyr,index,arr1){
					option = document.createElement('option');
					option.value = plyr.playerId;
					option.text = plyr.full_name;
					select.appendChild(option);
				});
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				
				select = document.createElement('select');
				select.style = 'width:130px';
				select.id = 'selectNameSuperDetail';
				select.name = select.id;
				
				
				option = document.createElement('option');
				option.value = 0;
				option.text = 'Team Name';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 1;
				option.text = 'Player of the Tie';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 2;
				option.text = 'Fastrack Smart Play of the Tie';
				select.appendChild(option);
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				
				select = document.createElement('select');
				select.style = 'width:130px';
				select.id = 'selectSponsor';
				select.name = select.id;
				
				
				option = document.createElement('option');
				option.value = 0;
				option.text = 'Without Sponsor';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 1;
				option.text = 'With Sponsor';
				select.appendChild(option);
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				
				break;
			}
			option = document.createElement('input');
		    option.type = 'button';
		    
			switch (whatToProcess) {
			
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
			    
				option = document.createElement('input');
				option.type = 'button';
				option.name = 'cancel_graphics_btn';
				option.id = option.name;
				option.value = 'Cancel';
				option.setAttribute('onclick','processUserSelection(this)');
		
			    div.append(option);
			    
			    row.insertCell(cellCount).appendChild(div);
			    
				document.getElementById('select_graphic_options_div').style.display = '';
			    
				break;
				
			case'POINT-OPTIONS': case'MATCH_POINT-OPTIONS':
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
				option.name = 'cancel_btn';
				option.id = option.name;
				option.value = 'Cancel';
				option.setAttribute('onclick','processUserSelection(this)');
		
			    div.append(option);
			    
			    row.insertCell(cellCount).appendChild(div);
			    cellCount = cellCount + 1;
			    
				document.getElementById('select_graphic_options_div').style.display = '';
			    
				break;
				
			case 'LT-TIEID-DOUBLE-OPTIONS':
				option.name = 'populate_lt_tieId_double_btn';
			    option.value = 'Populate TieID Double';
			    
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
				
			case'PLAYER_PROFILE-OPTIONS':
			    option.name = 'populate_player_profile_btn';
			    option.value = 'Populate Player Profile';
			    
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
			
			case 'BUGSUPER-OPTIONS':
				option.name = 'populate_bug_super_btn';
			    option.value = 'Populate Bug Super';
			    
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
				
			case 'NAMESUPER_PLAYER-OPTIONS':
				option.name = 'populate_namesuper_player_btn';
			    option.value = 'Populate NameSuper Player';
			    
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
	
	case 'ORDER_OF_PLAY-OPTIONS': case 'FF_TIE-OPTIONS': case 'L3_TIE-OPTIONS': case 'FF_SINGLE-OPTIONS': case 'FF_DOUBLE-OPTIONS': case 'LT_SINGLE-OPTIONS':
	case 'LT_DOUBLE-OPTIONS': case 'SQUADS-OPTIONS': case 'SCHEDULE-OPTIONS': case 'POINTS_TABLE-OPTIONS':
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
			case 'ORDER_OF_PLAY-OPTIONS':
				select = document.createElement('select');
				select.id = 'selectTeam1';
				select.name = select.id;
				
				dataToProcess.forEach(function(oop,index,arr1){
						
					option = document.createElement('option');
                    option.value = oop.matchnumber;
                    option.text = 'TIE- ' + oop.matchnumber +" "+ oop.home_Team.teamName1 + ' Vs ' + oop.away_Team.teamName1;
                    select.appendChild(option);
						
                });
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				
				select = document.createElement('input');
				select.type = "text";
				select.id = 'selectPlayer';
				select.value = 'Enter name of Player Of Tie';
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				
				/*select = document.createElement('select');
				select.id = 'selectTeam2';
				select.name = select.id;
				
				dataToProcess.forEach(function(oop,index,arr1){
						
					option = document.createElement('option');
                    option.value = oop.away_Team.teamId;
                    option.text = oop.away_Team.fullname;
                    select.appendChild(option);
						
                });
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;*/
				
				//select.setAttribute('onchange',"processUserSelection(this)");
				break;
				
				case 'FF_TIE-OPTIONS':
					select = document.createElement('select');
					select.id = 'selectTeam';
					select.name = select.id;
					
					dataToProcess.forEach(function(oop,index,arr1){	
						option = document.createElement('option');
	                    option.value = oop.matchnumber;
	                    option.text = oop.matchnumber + ' - ' +oop.home_Team.teamName1 + ' Vs ' + oop.away_Team.teamName1 ;
	                    select.appendChild(option);
							
	                });
					
					row.insertCell(cellCount).appendChild(select);
					cellCount = cellCount + 1;
					
					//select.setAttribute('onchange',"processUserSelection(this)");
					break;
				
				case 'L3_TIE-OPTIONS':
					select = document.createElement('select');
					select.id = 'selectTeam';
					select.name = select.id;
					
					dataToProcess.forEach(function(oop,index,arr1){	
						option = document.createElement('option');
	                    option.value = oop.matchnumber;
	                    option.text = oop.matchnumber + ' - ' +oop.home_Team.teamName1 + ' Vs ' + oop.away_Team.teamName1 ;
	                    select.appendChild(option);
							
	                });
					
					row.insertCell(cellCount).appendChild(select);
					cellCount = cellCount + 1;
					
					//select.setAttribute('onchange',"processUserSelection(this)");
					break;
					
				case 'FF_SINGLE-OPTIONS':
					select = document.createElement('select');
					select.id = 'selectTeam';
					select.name = select.id;
					
					dataToProcess.forEach(function(sm,index,arr1){	
						option = document.createElement('option');
						option.value = sm.matchId;
                    	option.text = sm.groupname + '- Match ' + sm.matchnumber ;
	                    select.appendChild(option);
							
	                });
					
					row.insertCell(cellCount).appendChild(select);
					cellCount = cellCount + 1;
					
					//select.setAttribute('onchange',"processUserSelection(this)");
					break;
				
				case 'FF_DOUBLE-OPTIONS': 
					select = document.createElement('select');
					select.id = 'selectTeam';
					select.name = select.id;
					
					dataToProcess.forEach(function(sm,index,arr1){	
						option = document.createElement('option');
						option.value = sm.matchId;
                    	option.text = sm.groupname + '- Match ' + sm.matchnumber ;
	                    select.appendChild(option);
							
	                });
					
					row.insertCell(cellCount).appendChild(select);
					cellCount = cellCount + 1;
					
					//select.setAttribute('onchange',"processUserSelection(this)");
					break;
					
				case 'LT_SINGLE-OPTIONS':
					select = document.createElement('select');
					select.id = 'selectTeam';
					select.name = select.id;
					//alert(dataToProcess.match.groupname);
					dataToProcess.forEach(function(sm,index,arr1){	
						option = document.createElement('option');
						option.value = sm.matchId;
                    	option.text = sm.groupname + '- Match ' + sm.matchnumber ;
	                    select.appendChild(option);
							
	                });
					
					row.insertCell(cellCount).appendChild(select);
					cellCount = cellCount + 1;
					
					//select.setAttribute('onchange',"processUserSelection(this)");
					break;
					
				case 'LT_DOUBLE-OPTIONS':
					select = document.createElement('select');
					select.id = 'selectTeam';
					select.name = select.id;
					
					dataToProcess.forEach(function(sm,index,arr1){	
						option = document.createElement('option');
						option.value = sm.matchId;
                    	option.text = sm.groupname + '- Match ' + sm.matchnumber ;
	                    select.appendChild(option);
							
	                });
					
					row.insertCell(cellCount).appendChild(select);
					cellCount = cellCount + 1;
					
					//select.setAttribute('onchange',"processUserSelection(this)");
					break;
					
				case 'SQUADS-OPTIONS':
					select = document.createElement('select');
					select.id = 'selectTeam';
					select.name = select.id;
					
					dataToProcess.forEach(function(team,index,arr1){	
						option = document.createElement('option');
						option.value = team.teamId;
                    	option.text = team.teamName1 ;
	                    select.appendChild(option);
							
	                });
					
					row.insertCell(cellCount).appendChild(select);
					cellCount = cellCount + 1;
					
					/*select = document.createElement('select');
					select.style = 'width:130px';
					select.id = 'selectCaptain';
					select.name = select.id;
					
					
					option = document.createElement('option');
					option.value = 0;
					option.text = 'Without Captain';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 1;
					option.text = 'With Captain';
					select.appendChild(option);
					
					row.insertCell(cellCount).appendChild(select);
					cellCount = cellCount + 1;*/
					
					//select.setAttribute('onchange',"processUserSelection(this)");
					break;
					
				case 'SCHEDULE-OPTIONS':
					select = document.createElement('select');
					select.id = 'selectGroup';
					select.name = select.id;
					
					option = document.createElement('option');
					option.value = 'Group A';
					option.text = 'Group A';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'Group B';
					option.text = 'Group B';
					select.appendChild(option);
					
					option = document.createElement('option');
					option.value = 'SUPER LEAGUE';
					option.text = 'SUPER LEAGUE';
					select.appendChild(option);
					
					/*option = document.createElement('option');
					option.value = 'FINAL';
					option.text = 'FINAL';
					select.appendChild(option);*/
					
					row.insertCell(cellCount).appendChild(select);
					cellCount = cellCount + 1;
					
					//select.setAttribute('onchange',"processUserSelection(this)");
					break;
			
			case 'POINTS_TABLE-OPTIONS':
				select = document.createElement('select');
				select.id = 'selectGroup';
				select.name = select.id;
				
				option = document.createElement('option');
				option.value = 'Group A';
				option.text = 'Group A';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'Group B';
				option.text = 'Group B';
				select.appendChild(option);
				
				option = document.createElement('option');
				option.value = 'SUPER LEAGUE';
				option.text = 'SUPER LEAGUE';
				select.appendChild(option);
				
				/*option = document.createElement('option');
				option.value = 'FINAL';
				option.text = 'FINAL';
				select.appendChild(option);*/
				
				row.insertCell(cellCount).appendChild(select);
				cellCount = cellCount + 1;
				
				//select.setAttribute('onchange',"processUserSelection(this)");
				break;
			}
			option = document.createElement('input');
		    option.type = 'button';
		    
			switch (whatToProcess) {
			
			case 'ORDER_OF_PLAY-OPTIONS':
				
			    option.name = 'populate_order_of_play_btn';
			    option.value = 'Populate Order Of Play';
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
				
			case 'FF_TIE-OPTIONS':
				option.name = 'populate_ff_tie_promo_btn';
			    option.value = 'Populate FF Tie Promo';
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
				
			case 'L3_TIE-OPTIONS':
				option.name = 'populate_l3_tie_promo_btn';
			    option.value = 'Populate L3 Tie Promo';
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
			case 'FF_SINGLE-OPTIONS':
				option.name = 'populate_ff_single_match_promo_btn';
			    option.value = 'Populate FF Single Match Promo';
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
				
			case 'FF_DOUBLE-OPTIONS':
				option.name = 'populate_ff_double_match_promo_btn';
			    option.value = 'Populate FF Double Match Promo';
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
				
			case 'LT_SINGLE-OPTIONS':
				option.name = 'populate_l3_single_match_promo_btn';
			    option.value = 'Populate LT Single Match Promo';
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
				
			case 'LT_DOUBLE-OPTIONS':
				option.name = 'populate_l3_double_match_promo_btn';
			    option.value = 'Populate LT Double Match Promo';
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
				
			case 'SQUADS-OPTIONS':
				option.name = 'populate_squads_btn';
			    option.value = 'Populate Squads';
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

				
			case 'SCHEDULE-OPTIONS':
				option.name = 'populate_schedule_btn';
			    option.value = 'Populate Schedule';
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
			case 'POINTS_TABLE-OPTIONS':
				option.name = 'populate_point_table_btn';
			    option.value = 'Populate Point Table';
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
	//break;
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