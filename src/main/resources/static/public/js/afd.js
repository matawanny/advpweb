function showNewData() {
	urlForNewData = 'http://localhost:8080/greetings?lastid=' + lastid;
	message = $.ajax({
        url: urlForNewData,
        type: 'get',
        dataType: 'json',
        contentType: 'application/json',
        success: function(clickdata){
        	if(clickdata.length > 0) {
        		for(var num = clickdata.length - 1; num >= 0; num--) {
	        		$("table#clickdatas-table").children("tbody").prepend('<tr>'+
	                		'<td>'+clickdata[num].ipAddress+'</td>'+
	                		'<td>'+clickdata[num].device+'</td>'+
	                		'<td>'+clickdata[num].country+'</td>'+
	                		'<td>'+clickdata[num].city+'</td>'+
	                		'<td>'+clickdata[num].publisherId+'</td>'+
	                		'<td>'+clickdata[num].campaignId+'</td>'+
	                		'<td>'+clickdata[num].timestamp_sent+'</td>'+
	                		'<td>'+clickdata[num].timestamp_received+'</td>'+
	                		'<td>'+clickdata[num].publisherChannelType+'</td>'+
	                		'<td>'+clickdata[num].referrerId+'</td>'+
	                		'</tr>');
	        		
	        	}
	        	lastid = clickdata[0].id;
        	}
        	
        }
	});
}

self.setInterval("showNewData()",1000);