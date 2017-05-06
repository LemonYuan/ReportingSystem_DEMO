function showText(url){
	$.ajax({
        url: url,
        dataType: "TEXT",
        success: function(data) {
        	$("#main").empty();
            $("#main").prepend("<pre  style='height:600px;'>"+data+"</pre>");
        }
    });
}