$j = jQuery.noConflict();
$j(document).ready(function() {
	$j("#loginout").click(function() {
		window.parent.location.href = "LoginServlet.do"
	});
	$j("#img").click(function(){
		menuFrameSlideToggle();
	});
	$j(".imgtext").click(function(){
		menuFrameSlideToggle();
	});
});
function menuFrameSlideToggle(){
//	x = parent.document.getElementsByTagName("frameset")[1];
	x = $j("frameset",parent.document).get(1);
	if(x.value == "none"){
		x.cols="188,*";
		x.value = "inline";
	}else{
		x.cols="0,*";
		x.value = "none";
	}	
}