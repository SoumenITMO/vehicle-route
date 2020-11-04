var key;
var objectid;
var startdate;
var enddate;

$(document).ready(function() {
  $(".car-list").hide();
  $(".cars").on("click", "tr", function() {
	 objectid = $(this).attr("object-id");
	 $(".cars tr").css("color","white").css("font-weight",200);
	 $(this).css("color","red").css("font-weight",500);
	 $(".route-search-box").show();
	 $(".car-route-section").show();
	 $(".car-route").html("");
	 $(".car-route-details").hide();
  });
  
  $('#daterange').daterangepicker({ opens: 'left'}, function(start, end, label) {
	startdate = start.format('YYYY-MM-DD');
	enddate = end.format('YYYY-MM-DD');
    $("#datepicker").val(start.format('YYYY/MM/DD') + " - " + end.format('YYYY/MM/DD'));
  });
  $("#datepicker").val("");
});

function getVehicleRoute() {
	showRoute(objectid, startdate, enddate);
}

function showRoute(objectid, startdate, enddate) {

  $(".loading").show();
  $.ajax({
  type: "GET",
  headers: {  'Access-Control-Allow-Origin': '*' },
  url: "getvehicleroute/"+key+"/"+objectid+"/"+startdate+"/"+enddate,
	  success : function( vechicalroutedata ) 
	  {
	  	if(vechicalroutedata.statusCode != undefined) {
	  		$(".loading").hide();
			$(".error-section-vehicle-history").fadeIn(200).fadeOut(5000);
		}
	  	else {
			if(vechicalroutedata.vechicalroutepoints.length !== 0) {
				mapdata(vechicalroutedata);
			}
			else {
				$(".loading").hide();
				$(".error-section-vehicle-history").fadeIn(200).fadeOut(5000);
			}
		}
	  }
  });
}

function mapdata(carroute) {
	var carroutedetails = "";
	
	var markerIcon = {
	  url: 'images/placeholder_red.svg',
	  scaledSize: new google.maps.Size(25, 25),
	  origin: new google.maps.Point(0, 0),
	  anchor: new google.maps.Point(32,65),
	  labelOrigin: new google.maps.Point(20,30)
    };

	const map = new google.maps.Map(document.getElementById("map"), {
	  zoom: 13,
	  center: { lat: carroute.vechicalroutepoints[0].lat, lng: carroute.vechicalroutepoints[0].lng },
	  mapTypeId: "terrain",
	});
	
	const flightPath = new google.maps.Polyline({
	  path: carroute.vechicalroutepoints,
	  geodesic: true,
	  strokeColor: "yellow",
	  strokeOpacity: 1.0,
	  strokeWeight: 2,
	});

	$("#map").hide();
	$(".loading").show();
	
	new google.maps.Marker({
		map: map,
		animation: google.maps.Animation.DROP,
		position: { lat: carroute.vechicalroutepoints[0].lat, lng: carroute.vechicalroutepoints[0].lng },
		icon: markerIcon,
		label: { text: "start", color: 'yellow' }
	});
	
	new google.maps.Marker({
		map: map,
		animation: google.maps.Animation.DROP,
		position: { lat: carroute.vechicalroutepoints[carroute.vechicalroutepoints.length - 1].lat, lng: carroute.vechicalroutepoints[carroute.vechicalroutepoints.length - 1].lng },
		icon: markerIcon,
		label: { text: "end", color: 'yellow' }
	});
	
	carroutedetails += "<td>"+carroute.totalDistance + " km</td>";
	carroutedetails += "<td>" + carroute.stops + " </td>";
	carroutedetails += "<td>" + carroute.shortestDistance + " km</td>";
	flightPath.setMap(map);
	
	$(".car-route").html(carroutedetails);
	$(".loading").hide();
	$("#map").show();
	$(".car-route-details").show();
}

function getvehicles() {

  var htmlData = "";
  var map;
  
  var markerIcon = {
	  url: 'images/placeholder.svg',
	  scaledSize: new google.maps.Size(20, 20),
	  origin: new google.maps.Point(0, 0),
	  anchor: new google.maps.Point(32,65),
	  labelOrigin: new google.maps.Point(20,33)
  };

  $(".car-route-section").hide();
  $(".loading").show();
  $("#map").hide();
  key = $(".key").val();
  
  $.ajax({
  type: "GET",
  headers: {  'Access-Control-Allow-Origin': '*' },
  url: "getvehicles/"+key,
	  success : function( vechicles )
	  {
  	      if(vechicles.length > 0)
  	      {
  	      	  $(".loading").show();
			  map = new google.maps.Map(document.getElementById("map"), {
				  zoom: 8,
				  center: { lat: vechicles[0].lat, lng: vechicles[0].lng },
				  mapTypeId: "terrain",
			  });
			  $.each(vechicles, function(index, cardata) {
				  htmlData += "<tr object-id="+cardata.objectId+">";
				  htmlData +=  " <td class='g'>"+cardata.plate+"</td> ";
				  htmlData +=  " <td>"+cardata.speed+"</td>";
				  htmlData +=  " <td>"+cardata.lastUpdate+"</td>";
				  htmlData +=  " <td>"+cardata.address+"</td>";
				  htmlData += "</tr>";

				  new google.maps.Marker({
					  map: map,
					  animation: google.maps.Animation.DROP,
					  position: { lat: cardata.lat, lng: cardata.lng },
					  icon: markerIcon,
					  label: { text: cardata.plate, color: 'yellow' }
				  });
			  });
			  $(".cars").html(htmlData);
			  $(".loading").hide();
			  $("#map").show();
			  $(".car-list").show();
		  }
  	      else
  	      	{
				$(".loading").hide();
  	      		$(".error-section-vehicles").fadeIn(200).fadeOut(5000);
		  }
	  }
  });
}
