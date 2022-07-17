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
			  
			  <div id="captions_div" class="form-group row row-bottom-margin ml-2" style="margin-bottom:5px;">
			    <label class="col-sm-4 col-form-label text-left">Match: ${session_match.match.matchId} </label>
			    <label class="col-sm-4 col-form-label text-left">IP Address: ${session_viz_ip_address} </label>
			    <label class="col-sm-4 col-form-label text-left">Port Number: ${session_selected_port} </label>
			    <label class="col-sm-4 col-form-label text-left">Broadcaster: ${session_selected_broadcaster} </label>
			    <label class="col-sm-4 col-form-label text-left" >Home Team: ${session_match.match.homeTeam.fullname}</label>
				<label class="col-sm-4 col-form-label text-left" >Away Team: ${session_match.match.awayTeam.fullname}</label>
				
				<c:forEach var="homeplayers" items="${session_match.match.homePlayers}">
					<label class="col-sm-4 col-form-label text-left" >Home player: ${homeplayers.full_name}</label>
				</c:forEach>
				<c:forEach var="awayplayers" items="${session_match.match.awayPlayers}">
					<label class="col-sm-4 col-form-label text-left" >Away player: ${awayplayers.full_name}</label>
				</c:forEach>
				
				<label id="home_team_set_won" class="col-sm-4 col-form-label text-left">Home Team Set Won: ${session_match.homeTeamSetsWon} </label>
				<label id="away_team_set_won" class="col-sm-4 col-form-label text-left">Away Team Set Won: ${session_match.awayTeamSetsWon} </label>
				
				<!-- <c:forEach var="set" items="${session_match.sets}">
					<c:if test="${(set.setNumber == 1)}">
						<label class="col-sm-4 col-form-label text-left" >Set1: ${set.homeTeamTotalScore}-${set.awayTeamTotalScore}  </label>
					</c:if>
					<c:if test="${(set.setNumber == 2)}">
						<label class="col-sm-4 col-form-label text-left" >Set2: ${set.homeTeamTotalScore}-${set.awayTeamTotalScore}  </label>
					</c:if>
					<c:if test="${(set.setNumber == 3)}">
						<label class="col-sm-4 col-form-label text-left" >Set3: ${set.homeTeamTotalScore}-${set.awayTeamTotalScore}  </label>
					</c:if>
					
				</c:forEach> -->
				
				
				<div class="left">
				<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
			  		name="scorebug_graphic_btn" id="scorebug_graphic_btn" onclick="processUserSelection(this)"> Scorebug </button>
			  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
			  		name="scorebugstat_graphic_btn" id="scorebugstat_graphic_btn" onclick="processUserSelection(this)"> Scorebug Stats </button>
			  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
			  		name="singlel3matchid_graphic_btn" id="singlel3matchid_graphic_btn" onclick="processUserSelection(this)"> SingleL3MatchId  </button>
			  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
			  		name="singleffmatchid_graphic_btn" id="singleffmatchid_graphic_btn" onclick="processUserSelection(this)"> SingleFFMatchId  </button>
			  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
			  		name="doublel3matchid_graphic_btn" id="doublel3matchid_graphic_btn" onclick="processUserSelection(this)"> Double L3 MatchId  </button>
			  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
			  		name="doubleffmatchid_graphic_btn" id="doubleffmatchid_graphic_btn" onclick="processUserSelection(this)"> Double FF MatchId  </button>
			  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
			  		name="l3tieid_graphic_btn" id="l3tieid_graphic_btn" onclick="processUserSelection(this)"> L3 TieId  </button>
			  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
			  		name="fftieid_graphic_btn" id="fftieid_graphic_btn" onclick="processUserSelection(this)"> FF TieId  </button>
			  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
			  		name="sides_graphic_btn" id="sides_graphic_btn" onclick="processUserSelection(this)"> Sides  </button>
			  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
			  		name="super_graphic_btn" id="super_graphic_btn" onclick="processUserSelection(this)"> Name Super  </button>
			  	<button style="background-color:#2E008B;color:#FEFEFE;" class="btn btn-sm" type="button"
			  		name="playerprofile_graphic_btn" id="playerprofile_graphic_btn" onclick="processUserSelection(this)"> Player Profile  </button>
			  	
			  	<button style="background-color:#f44336;color:#FEFEFE;;" class="btn btn-sm" type="button"
			  		name="animateout_graphic_btn" id="animateout_graphic_btn" onclick="processUserSelection(this)"> AnimateOut </button>
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