<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
table.search .select   div { width: 40px; }
table.search .SeqNo 	div { width: 60px; }
table.search .name    	div { width: 110px; }
table.search .state  	div { width: 290px; }
table.search .star   	div { width: 40px; }
table.search .altern1 {
	background: #ffffff;
	}
  table.search .altern2 {
	background: #EFF2E8;
	}
</style>
<title>Search</title>   
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" type="text/css" href="resources/css/screen.css">
<link rel="stylesheet" type="text/css" href="resources/css/colors.css">
<link rel="stylesheet" type="text/css" href="resources/css/responsive.css">
<link rel="stylesheet" type="text/css" href="resources/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="resources/css/dropzone.css">
<script type="text/javascript" src="resources/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="resources/js/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/js_json2.js"></script>
<script type="text/javascript" src="resources/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="resources/js/jquery.floatThead.js"></script>

</head>

<body id="searchPage">
<div id="wrap">
  <div id="main">
     <!--CONTENT-->
    <div id="content">
     <h4>Member Details Table</h4>
      <h5>Search Member Details</h5>
      
       <div class="rowElem">
		 <input type="text" id="searchTextField" name="searchTextField" class="inputText"/>
			&nbsp;&nbsp;<input type="button" id="searchBtn" onclick="showSearchableResult();" value="Search" class="inputBt" />
	  </div>
	  
      <h5>Search Results List</h5>
      <div class="scrollableContainer">
      <div id="searchDetailsTableDiv" class="scrollingArea">
	      <table id="searchDetailsTable" border="0" cellspacing="0" cellpadding="0" class="search scrollable">
	        <thead>
	        	<tr>
	          		<th style="width: 100px;">Member Id</th>
	          		<th style="width: 400px;white-space:normal;">Status</th>
	          		<th style="width: 100px;">Race</th>
	          		<th style="width: 100px;">Weight</th>
	          		<th style="width: 100px;">Height</th>	          
	          		<th style="width: 100px;">Is Veg</th>
	          </tr>
	        </thead>
	        <tbody></tbody>	
		   </table>
	   </div>
	   </div>
	
      </div>      
    
	</div>
              
</div>
</div>
</body>
<script type="text/javascript">

var newCharacteristicsOrder = '';
var recordNum = 0;
var trNum = 0;
var editTOCID = '';
var handleSave = "false";
$(document).ready(function() {	

	//Show WAIT cursor when UPLOAD Document is clicked 
	$("#uploadBtn").click(function(){
        //$("body").addClass("waiting");
    	
	}); 	
	
});


//Bind the ENTER key with Characteristics Search
//Focusing the press of enter only for the TEXTArea for Searching Adopted characteristics
$("#searchTextField").keypress(function(e) {
    // 13 is ENTER
  //  if (e.which === 13 && $("#searchTextField").data("hasfocus")) {
	  if (e.which == 13){
        if ($.trim($("#searchTextField").val()) != "") {
        	e.preventDefault();	
        	$("#searchBtn").click();
        	//Applying to all elements on this page
        	//$("*").css("cursor","wait");
        }
        else {
        	//When the Search Field is blank the Enter key should work in Other TextAreas
        	//so the below line must be commented but the condition is the searchtextfiled should be empty.
        	//e.preventDefault(); 	
        }
    }
});

var adoptedExpressionNotesArray = '';
var selectedAdoptedTgId = '';
var isAdopted = false;
var isAdoptedAndModified = false;
var adoptedCharacterName = '';
var newAdoptedRowIndex = 0;
var rowIdentity=0;

function showSearchableResult()
{
	var searchText = $('#searchTextField').val();
	searchText = $.trim(searchText);
	if (searchText != "" && searchText.length > 0){
		$.blockUI({message:'Searching Societe-Generale Member Details. Please wait...'});
		$.ajax({
	        url: 'searchMemberDetails.societegenerale',
	        type: 'POST',
	        data:{"searchText": searchText},
	        success: function (data) {
				if(data != null){
							
						//Reset Cursor to all elements on this page.	
						//$("*").css("cursor","auto");
						var dataobj = $.parseJSON(data);
						if(dataobj.EXCEPTIONMSG != null){										
							$('#showFormError').text(dataobj.EXCEPTIONMSG);
							$('#showFormError').show();
						}else if(dataobj.NORESULTFOUND != null){
							$('#searchDetailsTable').find("tr:gt(0)").remove();
							var trObj = '<tr>';
						    trObj += '<td colspan="13">'+dataobj.NORESULTFOUND+'</td>';
						    trObj += '</tr>';
						    $(trObj).appendTo('#searchDetailsTable tbody');
						}else{
							//$('#searchDetailsTable').find("tr:gt(0)").remove();
							rowIndex = 1;
							$.each(dataobj, function(index){
		                        //$("#searchDetailsTable").val(dataobj[index].explanationText);
		                        
		                        var trObj = '<tr>';
		                        trObj += '<td style="width:100px;">'+dataobj[index].memberID+'</td>';
		                        trObj += '<td style="width:400px;">'+dataobj[index].status+'</td>';
		                        trObj += '<td style="width:100px;">'+dataobj[index].race+'</td>';
		                        trObj += '<td style="width:100px;">'+dataobj[index].weightInKG+'</td>';
		                        trObj += '<td style="width:100px;">'+dataobj[index].heightInCM+'</td>';
		                        trObj += '<td style="width:100px;">'+dataobj[index].isVeg+'</td>';
		                        trObj += '</tr>';
		                        $(trObj).appendTo('#searchDetailsTable tbody');
		                        $('#searchDetailsTable tr:even').css('class', '').addClass('altern2');
		                        $('#searchDetailsTable tr:odd').css('class', '').addClass('altern1');
		                        rowIndex++;
		                 });
						//Put the scroll in the start of table. So that first result displayed first.	
						$("#searchDetailsTableDiv").scrollTop(0);
						$("#searchDetailsTable").scrollTop(0);
						}// end of else
						$.unblockUI();
					}//end of first IF
			},
			error:function(data){
				//$("*").css("cursor","auto");
				$.unblockUI();
		    }	
		});

	}

}

</script>
</html>