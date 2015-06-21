<%@include file="includes/header.jsp"%>	

<div class="panel panel-primary">

    <div class="panel-heading">
        <h3 class="panel-title">Ad Clicking Log</h3>
    </div>
    
    <div class="panel-body">
		<table class="clickdatas">
		<tr>
		<td>IP Address</td><td>Device</td>
		<td>Country</td><td>City</td>
		<td>Publisher</td><td>&nbsp;Campaign</td>
		<td>Click Time</td><td>Received Time</td><td>Publish Channel Type</td><td>Referrer</td>
		</tr>
		
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
    
</div>

<%@include file="includes/footer.jsp"%>