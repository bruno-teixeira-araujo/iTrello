<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>

	<div id="dashboardContent">
		<h2>Dashboard</h2>
		<p>Percentage of cards with due date on time</p> 
		<br>
		
		<c:if test="${empty dashboardElements}">
			<p>No dashboardElements to display</p>			
		</c:if>
		<c:if test="${not empty dashboardElements}">
			<div class="row">
				<div class="col-md-12"><canvas></canvas></div>
			</div>
			<br>
			
		    <table id="dashboard_results" class="table table-hover">
		      <tbody>
	            <tr>
	                <th>List Name</th>
	                <th>Percentage (%)</th>
	            </tr>
				<c:forEach var="dashboardElement" items="${dashboardElements}">
		            <tr>
		                <td>${dashboardElement.name}</td>
		                <td>${dashboardElement.percent}</td>
		            </tr>
				</c:forEach>
			  </tbody>
		    </table>
		</c:if>

	</div>
	
<script type="text/javascript">
var usr_color = 160; //Change value to change color scheme

var canvas = document.getElementsByTagName("canvas")[0];
var ctx = canvas.getContext("2d");

var w = 700, h = 500;
canvas.width = w;
canvas.height = h;

var arcs = [];

var centerX = 450;
var centerY = 250;
var lineWidth = 20;
var spaceBetween = lineWidth * 1.16;
var init_radius = 25;

function init(){
	console.log("init called");
	reset();
	arcs = [];

	var all_names = [];
	var all_perc = [];
	var all_radius = [];
  
	// load arrays from html table
	var oTable = document.getElementById('dashboard_results');
	var rowLength = oTable.rows.length;
	var name_count = 0;
	for (i = 0; i < rowLength; i++){
	   var oCells = oTable.rows.item(i).cells;
	   // filter 'td' tags
	   if(oCells.item(0).tagName.toUpperCase() == 'TD') {
		   all_names[name_count] = oCells.item(0).innerHTML;
		   all_perc[name_count] = oCells.item(1).innerHTML;
		   name_count++;
		}
	}

	var radius_val = init_radius;
	for(var i=0;i<all_names.length;i++){
		all_radius[i] = init_radius;
		init_radius = init_radius + spaceBetween;
	}
  
	for(var i=0;i<all_names.length;i++){
		var m = new arc();
		m.class = all_names[i];
		m.radius = all_radius[i];
		m.perc = all_perc[i];
		if(all_perc[i] == 0) {
			m.rot = (Math.PI*2) - (Math.PI/2);
		} else if (all_perc[i] == 100) {
			m.rot = -(Math.PI/2);
		} else {
			m.rot = ((m.perc)/100)*(Math.PI*2) - (Math.PI/2);	
		}
		// color based on rotation
		// m.color = m.rot*(180/Math.PI)+usr_color;
		// color based on index position
		m.color = i*(180/Math.PI)+usr_color;
		arcs.push(m);
	}
}

function arc(){
  this.centerX = centerX;
  this.centerY = centerY;
  this.lineWidth = lineWidth;
  this.rot = 1;
  this.perc = 0;
  
  
  this.draw = function(){
    ctx.beginPath();
	if(this.perc != 0) {
		// ctx.arc(x-center,y-center,radios,starting_angle,ending_angle,counterclockwise);
		ctx.arc(this.centerX,this.centerY,this.radius,(Math.PI/(2/3)),this.rot,false);
	}
    ctx.lineWidth = this.lineWidth;
    ctx.strokeStyle = "hsla("+(this.color)+",60%,50%,1)";
    ctx.stroke();
    
    ctx.save();
  }
}

function reset(){
  ctx.fillStyle = "#333";
  ctx.fillRect(0,0,w,h);
}

function draw(){
  reset();
  ctx.fillStyle = "rgba(255,255,255,0.5)";
  ctx.font = "bold 16px Arial";

  for(var i=0;i<arcs.length;i++){
    var a = arcs[i];
	a.draw();
	ctx.fillStyle = "hsla("+(a.color)+",60%,50%,1)";
	ctx.fillText(a.class + " - " + a.perc + "%", 20, a.centerY-a.radius);
  }
}

init();
draw();
</script>