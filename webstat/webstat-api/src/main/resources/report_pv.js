if (document.head) {
    loadScript(logPvSrc);
}
else {
    document.addEventListener("DOMContentLoaded", function() {
        loadScript(logPvSrc)
    }, false );
}