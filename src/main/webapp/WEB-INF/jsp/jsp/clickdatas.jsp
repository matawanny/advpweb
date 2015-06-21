<%@include file="includes/header.jsp"%>	

<div class="panel panel-primary">

    <div class="panel-heading">
        <h3 class="panel-title">Ad Clicking Log</h3>
    </div>
    
    <div class="panel-body data">
		<table id="clickdatas-table" class="table table-hover table-condensed">
		<thead>
		<tr>
		<th>IP Address</th><th>Device</th>
		<th>Country</th><th>City</th>
		<th>Publisher</th><th>&nbsp;Campaign</th>
		<th>Click Time</th><th>Received Time</th><th>Publish Channel Type</th><th>Referrer</th>
		</tr>
		</thead>
		
		<c:forEach var="clickdata" items="${clickdatas}">
		<tr>
		
		<td><c:out value="${clickdata.ipAddress}"></c:out></td>
		
		<td><c:out value="${clickdata.device}"></c:out></td>
		
		<td><c:out value="${clickdata.country}"></c:out></td>
		
		<td><c:out value="${clickdata.city}"></c:out></td>

		<td><c:out value="${clickdata.publisherId}"></c:out></td>
		
		<td><c:out value="${clickdata.campaignId}"></c:out></td>
		
		
		<td><c:out value="${clickdata.timestamp_sent}"></c:out></td>
		
		<td><c:out value="${clickdata.timestamp_received}"></c:out></td>
		
		<td><c:out value="${clickdata.publisherChannelType}"></c:out></td>
		
		<td><c:out value="${clickdata.referrerId}"></c:out></td>
		   
		</tr>
		</c:forEach>
		</table>
    </div>
    <div>
</div>
    
</div>

<script>
lastid = ${lastid};
</script>

<%@include file="includes/footer.jsp"%>