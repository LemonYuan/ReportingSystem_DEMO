function showText(url){
	$.ajax({
        url: url,
        dataType: "TEXT",
        success: function(data) {
        	$("#main").empty();
            $("#main").prepend("<pre>"+data+"</pre>");
        }
    });
}