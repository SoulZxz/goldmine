var webStat = {
	logCustomEvent: function(event, vars) {
		var pageName = document.title;
		var customEventDummySrc = "${host}/webstat/api/customEvent/" + event + "?appId=${appId}&pageName=" + encodeURIComponent(pageName) + "&rnd=" + Math.random();
		if(vars != undefined && vars != '') {
			customEventDummySrc += "&vars=" + encodeURIComponent(vars);
		}
		var img = document.createElement("img");
		img.src = customEventDummySrc;
		img.width = "0";
		img.height = "0";
		img.alt = "Page view tracker";
		var st = img.style;
   		st.display = "none";
		document.body.appendChild(img);
	}
};