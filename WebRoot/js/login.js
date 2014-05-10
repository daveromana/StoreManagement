$j = jQuery.noConflict();
$j(document).ready(function() {
		$j("#loginbutton").click(function() {
			$j("#loginerror").css("display", "none");
			$j("#inputerror").css("display", "none");
			var uname = $j("#uname").val();
			var upassword = $j("#pwd").val();
			if (uname == "" || upassword == "") {
				$j("#loginerror").css("display", "none");
				$j("#inputerror").css("display", "inline");
			} else {
				login();
			}
		});
	});
	function login() {
		var uname = $j("#uname").val();
		var upassword = $j("#pwd").val();
		$j.post("LoginServlet.do", {
			username : uname,
			userpassword : upassword
		}, function(data, status) {
			if (data == 0 && status == "success") {
				location.href = "index.jsp";
			} else {
				$j("#loginerror").css("display", "inline");
				$j("#inputerror").css("display", "none");
			}
		});
	}