<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

  <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
  <title>Output Screen</title>
	
  <script type="text/javascript" src="<c:url value="/webjars/jquery/3.6.0/jquery.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/webjars/bootstrap/5.1.3/js/bootstrap.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/javascript/index.js"/>"></script>
  
  <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.1.3/css/bootstrap.min.css"/>"/>  
  <link href="<c:url value="/webjars/font-awesome/6.0.0/css/all.css"/>" rel="stylesheet">
  <script type="text/javascript">
  setInterval(() => {
	  processBadmintonProcedures('READ-MATCH-AND-POPULATE');
	}, 1000);
  </script>

</head>
<body>
<form:form name="output_form" autocomplete="off" action="POST">
<div class="content py-5" style="background-color: #EAE8FF; color: #2E008B">
  <div class="container">
	<div class="row">
	 <div class="col-md-8 offset-md-2">
       <span class="anchor"></span>
         <div class="card card-outline-secondary">
           <div class="card-header">
             <h3 class="mb-0">Output</h3>
           </div>
          <div class="card-body">
			  <div id="select_graphic_options_div" style="display:none;">
			  </div>
		<div id="main_captions_div" class="form-group row row-bottom-margin ml-2" style="margin-bottom:5px;">
			  <div id="captions_div" class="form-group row row-bottom-margin ml-2" style="margin-bottom:5px;">
			    <label class="col-sm-4 col-form-label text-left">Match: ${session_match.match.matchId} </label>
			    <label class="col-sm-4 col-form-label text-left">IP Address: ${session_selected_ip} </label>
			    <label class="col-sm-4 col-form-label text-left">Port Number: ${session_selected_port} </label>
			    <label class="col-sm-4 col-form-label text-left">Broadcaster: ${session_selected_broadcaster} </label>
			    <label id="category" class="col-sm-4 col-form-label text-left" >Category: ${session_match.match.categoryId}</label>
				<label class="col-sm-4 col-form-label text-left"> ${session_match.match.groupname} </label>
			  </div>
			    <label id="home_team" class="col-sm-4 col-form-label text-left" >Home : ${session_match.match.homeTeam.fullname}  ${session_match.homeTeamSetsWon} </label>
			    <label id="away_team" class="col-sm-4 col-form-label text-right" >${session_match.awayTeamSetsWon} ${session_match.match.awayTeam.fullname} : Away   </label>
			    <div id="caption_div" class="left" style="margin-bottom:5px;">
					<c:forEach var="homeplayers" begin = "0" end = "2" items="${session_match.match.homePlayers}">
						<label id="home_player" class="col-sm-4 col-form-label text-left">Home player: ${homeplayers.ticker_name}</label>
					</c:forEach>
					<c:forEach var="awayplayers" items="${session_match.match.awayPlayers}">
						<label id="away_player" class="col-sm-4 col-form-label text-right" >Away player: ${awayplayers.ticker_name}</label>
					</c:forEach>
				</div>

				<div id="caption_divs" class="form-group row row-bottom-margin ml-2" style="margin-bottom:5px;">
					<c:forEach var="set" items="${session_match.sets}">
						<c:if test="${(set.setNumber == 1)}">
							<label id="set1" class="col-sm-4 col-form-label text-left" >Set1: ${set.homeTeamTotalScore}-${set.awayTeamTotalScore}(${sets.status}) </label>
						</c:if>
						<c:if test="${(set.setNumber == 2)}">
							<label id="set2" class="col-sm-4 col-form-label text-left" >Set2: ${set.homeTeamTotalScore}-${set.awayTeamTotalScore}(${sets.status})  </label>
						</c:if>
						<c:if test="${(set.setNumber == 3)}">
							<label id="set3" class="col-sm-4 col-form-label text-left" >Set3: ${set.homeTeamTotalScore}-${set.awayTeamTotalScore}(${sets.status})  </label>
						</c:if>
						
					</c:forEach>
				</div>
				<div class="form-group row row-bottom-margin ml-2" style="margin-bottom:5px;">
			    <label for="previous_xml_data" class="col-sm-4 col-form-label text-left">Previous Manual Data </label>
			    <div class="col-sm-6 col-md-6">
			      <select id="previous_xml_data" name="previous_xml_data" 
			      		class="browser-default custom-select custom-select-sm">
						<c:forEach items = "${manual_files}" var = "files">
				          	<option value="${files.name}">${files.name}</option>
						</c:forEach>
			      </select>
			    </div>
			  </div>
			<div class="left">
			<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="scorebug_graphic_btn" id="scorebug_graphic_btn" onclick="processUserSelection(this)"> Scorebug </button>
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="scorebugstat_graphic_btn" id="scorebugstat_graphic_btn" onclick="processUserSelection(this)"> Scorebug Stats </button>
		  	
		  	</div>
		  	<div class="left">
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="singlel3matchid_graphic_btn" id="singlel3matchid_graphic_btn" onclick="processUserSelection(this)"> SingleL3MatchId  </button>
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="singleffmatchid_graphic_btn" id="singleffmatchid_graphic_btn" onclick="processUserSelection(this)"> SingleFFMatchId  </button>
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="ff_single_match_graphic_btn" id="ff_single_match_graphic_btn" onclick="processUserSelection(this)"> FF-Single-Match Promo  </button>
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="l3_single_match_graphic_btn" id="l3_single_match_graphic_btn" onclick="processUserSelection(this)"> LT-Single-Match Promo  </button>	
		  	
		  	</div>
		  	<div class="left">
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="doublel3matchid_graphic_btn" id="doublel3matchid_graphic_btn" onclick="processUserSelection(this)"> Double L3 MatchId  </button>
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="doubleffmatchid_graphic_btn" id="doubleffmatchid_graphic_btn" onclick="processUserSelection(this)"> Double FF MatchId  </button>
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="ff_double_match_graphic_btn" id="ff_double_match_graphic_btn" onclick="processUserSelection(this)"> FF-Double-Match Promo  </button>
		  	
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="l3_double_match_graphic_btn" id="l3_double_match_graphic_btn" onclick="processUserSelection(this)"> LT-Double-Match Promo  </button>
		  	
		  	</div>
		  	<div class="left">
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="l3tieid_graphic_btn" id="l3tieid_graphic_btn" onclick="processUserSelection(this)"> L3-TieId  </button>
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="fftieid_graphic_btn" id="fftieid_graphic_btn" onclick="processUserSelection(this)"> FF-TieId  </button>
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="ff_tie_graphic_btn" id="ff_tie_graphic_btn" onclick="processUserSelection(this)"> FFTiePromo  </button>
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="l3_tie_graphic_btn" id="l3_tie_graphic_btn" onclick="processUserSelection(this)"> LTTiePromo  </button>
		  	
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="sides_graphic_btn" id="sides_graphic_btn" onclick="processUserSelection(this)"> Sides  </button>
		  	
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="super_graphic_btn" id="super_graphic_btn" onclick="processUserSelection(this)"> Name Super  </button>
		  		
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="bug_super_graphic_btn" id="bug_super_graphic_btn" onclick="processUserSelection(this)"> Bug Super  </button>
		  		
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="namesuper_player_graphic_btn" id="namesuper_player_graphic_btn" onclick="processUserSelection(this)"> NameSuper-Player  </button>
		  		
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="playerprofile_graphic_btn" id="playerprofile_graphic_btn" onclick="processUserSelection(this)"> Player Profile  </button>
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="orderofplay_graphic_btn" id="orderofplay_graphic_btn" onclick="processUserSelection(this)"> Order Of Play  </button>
		  	
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="squads_graphic_btn" id="squads_graphic_btn" onclick="processUserSelection(this)"> Squad  </button>
		  	
			<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="teamslogo_graphic_btn" id="teamslogo_graphic_btn" onclick="processUserSelection(this)"> Teams Logos </button>
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="supermatch_graphic_btn" id="supermatch_graphic_btn" onclick="processUserSelection(this)"> Super MatchFF  </button>
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="supermatch1_graphic_btn" id="supermatch1_graphic_btn" onclick="processUserSelection(this)"> Super Match1  </button>
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="supermatch2_graphic_btn" id="supermatch2_graphic_btn" onclick="processUserSelection(this)"> Super Match2  </button>
		  		
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="points_table_graphic_btn" id="points_table_graphic_btn" onclick="processUserSelection(this)"> Points Table  </button>

		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="rules_graphic_btn" id="rules_graphic_btn" onclick="processUserSelection(this)"> Rules  </button>
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="schedule_graphic_btn" id="schedule_graphic_btn" onclick="processUserSelection(this)"> Schedule  </button>	
		  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
		  		name="lt_tieid_double_graphic_btn" id="lt_tieid_double_graphic_btn" onclick="processUserSelection(this)"> LT TieID Double  </button>

		  	</div>
		  	<div class="left">
		  	<button style="background-color:#f44336;color:#FEFEFE;;" class="btn btn-sm" type="button"
		  		name="animateout_graphic_btn" id="animateout_graphic_btn" onclick="processUserSelection(this)"> AnimateOut </button>
		  	<button style="background-color:#f44336;color:#FEFEFE;;" class="btn btn-sm" type="button"
		  		name="clearall_graphic_btn" id="clearall_graphic_btn" onclick="processUserSelection(this)"> Clear All </button>
		  	<button style="background-color:#f44336;color:#FEFEFE;;" class="btn btn-sm" type="button"
		  		name="animateout_category_graphic_btn" id="animateout_category_graphic_btn" onclick="processUserSelection(this)"> AnimateOut Category </button>
		  	
		  <button style="background-color:#f44336;color:#FEFEFE;;" class="btn btn-sm" type="button"
		  		name="manualgraphics_graphic_btn" id="manualgraphics_graphic_btn" onclick="processUserSelection(this)"> Animate In Manual </button>
		  </div>
			  </div>
	      </div>
	    </div>
       </div>
    </div>
  </div>
</div>
<input type="hidden" name="select_broadcaster" id="select_broadcaster" value="${session_selected_broadcaster}"/>
<input type="hidden" id="match_file_timestamp" name="match_file_timestamp" value="${session_match.match_file_timestamp}"></input>
</form:form>
</body>
</html>