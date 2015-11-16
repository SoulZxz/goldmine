var webStat = {
	logCustomEvent: function(event, vars) {
		var pageName = document.title;
		var customEventDummySrc = "${host}/webstat/api/customEvent/" + event + "?appId=${appId}&pageName=" + encodeURIComponent(pageName) + "&rnd=" + Math.random();
		if(vars != undefined && vars != '') {
			customEventDummySrc += "&vars=" + encodeURIComponent(vars);
		}
		loadScript(customEventDummySrc)
	}
};

function loadScript (url){
    var script = document.createElement ("script");
    script.type = "text/javascript";
    script.src = url;
    document.head.appendChild(script);
}