function loginRegisterSwitch() {
	$('form').animate({
		height : "toggle",
		opacity : "toggle"
	}, "slow");
}

function showAlertAfterRegistration() {
	$('div.alert.alert-success').show();
}

$('.message a').click(function() {
	loginRegisterSwitch();
});

$("button.register").click(function() {
	var firstname = $("form.register-form input.firstname").val();
	var lastname = $("form.register-form input.lastname").val();
	var email = $("form.register-form input.email").val();
	var password = $("form.register-form input.password").val();
	var cpassword = $("form.register-form input.cpassword").val();

	if (firstname == '' || lastname == '' || email == ''
		|| password == '' || cpassword == '') {
		alert("Please fill all fields!");
	} else if ((password.length) < 0) {
		alert("Password should atleast 8 character in length...!!!!!!");
	} else if (!(password).match(cpassword)) {
		alert("Your passwords don't match. Try again?");
	} else {
		var userRegistration = {
			firstname: firstname,
			lastname: lastname,
			email: email,
			password: password
		};
		$.ajax({
			url: "/registration",
			type: "POST",
			dataType: "json",
			data: userRegistration, // <--- HERE
			success: function(data) {
				if (data.result) {
					if (data.result) {
						$("form")[0].reset();
						$("form")[1].reset();
						loginRegisterSwitch();
						showAlertAfterRegistration();
					}
				}
			}
		});

		$.post("registration", userRegistration, function(data) {
			$("form")[0].reset();
			$("form")[1].reset();
			loginRegisterSwitch();
			showAlertAfterRegistration();
		});
	}

});

$("button.login").click(function() {
	var email = $("form.login-form input.email").val();
	var password = $("form.login-form input.password").val();

	if (email == '' || password == '') {
		alert("Please fill login form!");
	} else {
		var userLogin = {
			email: email,
			password: password
		};
		$.post("login", userLogin, function(data) {
			var customUrl = '';
			var urlContent = window.location.href.split('/');
			for (var i = 0; i < urlContent.length - 1; i++) {
				customUrl += urlContent[i] + '/'
			}
			customUrl += data.destinationUrl;
			window.location = customUrl;

			// $("form")[1].reset();
		});
	}
});