var _referrer = encodeURIComponent(document.referrer);
var _campaign = _getParameterByName('_campaign');
var _adTag = _getParameterByName('_adTag');
var _pageName = document.title;
var logPvSrc = "${host}/webstat/api/logPv?appId=${appId}"
	+ "&_pageName=" + _pageName
    + ((_referrer == "" || _referrer == null) ? "" : "&_referer=" + _referrer)
    + ((_campaign == "" || _campaign == null) ? "" : "&_campaign=" + _campaign)   
    + ((_adTag == "" || _adTag == null) ? "" : "&_adTag=" + _adTag);

function _getParameterByName(name) {
	name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
	var regexS = "[\\?&]" + name + "=([^&#]*)";
	var regex = new RegExp(regexS);
	var results = regex.exec(window.location.search);
	if (results == null)
		return "";
	else
		return decodeURIComponent(results[1].replace(/\+/g, " "));
}

if (document.head) {
    loadScript(logPvSrc);
}
else {
    document.addEventListener("DOMContentLoaded", function() {
        loadScript(logPvSrc)
    }, false );
}