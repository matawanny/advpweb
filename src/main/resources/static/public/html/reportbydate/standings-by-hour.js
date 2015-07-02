
var width = 800,
    height = 500,
    pad = 20,
    leftPad = 150
    bottomPad = 70;

var svg = d3.select("#standings-chart")
  .append("svg")
  .attr("width", width)
  .attr("height", height);
  
var gameDiv = d3.select("body")
  .append("div")
  .attr("class", "tooltip")
  .style("opacity", 0);

var x = d3.time.scale().range([leftPad, width - pad]);
var y = d3.scale.linear().range([height - bottomPad, pad]);

var parseDate = d3.time.format("%H").parse;
var dateFormat = d3.time.format("%H:00");

var xAxis = d3.svg.axis()
  .scale(x)
  .orient("bottom")
  .ticks(d3.time.hours, 1)
  .tickFormat(dateFormat);
var yAxis = d3.svg.axis()
  .scale(y)
  .orient("left");

var pointLine = d3.svg.line()
  .x(function(d) { return x(d.date); })
  .y(function(d) { return y(d.points); });

var reload = function() {
  d3.json('data-by-hour.json', function(json) {
    teams = [];
    json.forEach(function(d) {
      d.Date = parseDate(d.Date);
      d.Games.forEach(function(g) {
	      var awayPoints = g.AwayScore;
	      var awayData = {date: d.Date, points: awayPoints, team: 
	        g.Away, opp: g.Home, goals: g.AwayScore, allowed: g.HomeScore,
	        align: "away", venue: g.Venue,
	        decision: (awayPoints==3)? "win":(awayPoints==1)? "draw":"loss"
	      };
	      if (teams.indexOf(g.Away) < 0) {
	        teams.push(g.Away);
	        teams[g.Away] = [awayData];
	      } else {
	        lastIndex = teams[g.Away].length -1;
	        //awayData.points = teams[g.Away][lastIndex].points;
	        teams[g.Away].push(awayData);
	      }
	      
	      var homePoints = g.HomeScore;
	      var homeData = {date: d.Date, points: homePoints, team: 
	        g.Home, opp: g.Away, goals: g.HomeScore, allowed: g.AwayScore,
	        align: "home", venue: g.Venue,
	        decision: (homePoints==3)? "win":(homePoints==1)? "draw":"loss"
	      };
	      if (teams.indexOf(g.Home) < 0) {
	        teams.push(g.Home);
	        teams[g.Home] = [homeData];
	      } else {
	        lastIndex = teams[g.Home].length -1;
	        //homeData.points = teams[g.Home][lastIndex].points;
	        teams[g.Home].push(homeData);
	      }
	    });
    });

    x.domain([json[0].Date, json[json.length -1].Date]);
    y.domain([0, 8000]);
    
    teams.sort(function(a, b) {
      teamA = teams[a];
      pointsA = teamA[teamA.length -1].points;
      teamB = teams[b];
      pointsB = teamB[teamB.length -1].points;
      return d3.descending(pointsA, pointsB);
    });
    
    //console.log(teams);
    redraw(teams);
  });
};

var redraw = function(data) {
  var lines = svg.selectAll(".line-graph")
    .data(data)
  
  lines.enter()
    .append("g")
    .attr("class", "line-graph")
    .attr("transform", "translate(" + xAxis.tickPadding() + ", 0)");
  
  lines.each(function(d, i) {
    d3.select(this)
      .attr("id", makeId(d))
      .attr("data-legend-" + ((i < 16)? 1:2), d)
      .attr("data-legend-" + ((i < 16)? 1:2) + "-pos", i)
      .style("stroke", colors24[i]);
  });
  
  var circles = lines.selectAll("circle")
    .data(function(d) { return data[d]; });
  
  circles.enter()
    .append("circle")
    .attr("r", 3);
  
  circles.each(function(d) {
    var color = d3.select(this.parentElement).style("stroke");
    d3.select(this)
      .attr("cx", x(d.date))
      .attr("cy", y(d.points))
      .style("fill", color)
      .on("mouseover", function(e) {
        gameDiv.transition()
          .duration(200)
          .style("opacity", 0.9)
          .style("background-color", color);
        gameDiv.html(d.team + "(" + dateFormat(d.date) + " - " + d.align + ")<br/>"
          + "Clicks: " + d.points
        )
        .style("left", (d3.event.pageX + 10) + "px")
        .style("top", (d3.event.pageY -40) + "px");
      })
      .on("mouseout", function(e) {
        gameDiv.transition()
          .duration(500)
          .style("opacity", 0);
      });
    });
  
  var path = lines.append("path")
    .datum(function(d) { return data[d]; })
    .attr("d", function(d) { return pointLine(d); });

  var axes = [
    {axis: xAxis, translateX: 0, translateY: (height - bottomPad), clazz: "x"},
    {axis: yAxis, translateX: leftPad, translateY: 0, clazz: "y"},
  ];
  
  var axis = svg.selectAll("g.axis")
    .data(axes)
  
  axis.enter()
    .append("g")
    .classed("axis", true);
    
  axis.each(function(d) {
    d3.select(this)
      .attr("transform", "translate(" + d.translateX + "," + d.translateY + ")")
      .classed(d.clazz, true)
      .call(d.axis);
  });
  
  axis.selectAll(".x text")
    .style("text-anchor", "end")
    .attr({dx: "-0.8em", dy: "0.15em", transform: "rotate(-65)"});
  
  [{legend:1, x:leftPad -130, y:y(8000)}
  ].forEach(function(d) {
	  svg.append("g")
	    .attr("class", "legend")
	    .attr("transform", "translate(" + d.x + "," + d.y + ")")
	    .call(d3.legend, "data-legend-" + d.legend);
	});
};

function makeId(string) {
  return string.replace(/[^A-Za-z0-9]/g,'');
};

reload();
